package digidigi.mtm.common;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import buildcraft.api.tools.IToolWrench;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import digidigi.mtm.client.RenderFireBeam;
import digidigi.mtm.client.RenderFireBeams;
import digidigi.mtm.MagitekMechs;
import digidigi.mtm.common.MagitekItems;
import digidigi.mtm.common.TileEntityMT;
import digidigi.mtm.utility.MutableTripletInt;
import digidigi.mtm.utility.TripletInt;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.packet.Packet22Collect;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeHooks;

public class EntityMT extends EntityLiving
{        
    // Used for a step sound when up.
    public boolean legDown = false; 

    //Counters that determine the timing of both the animation and movement.
    public float lLegMoveC = 0;
    public float lLegDropC = 0;
    public float lLegMoveE = 10.0F;
    public float rLegMoveC = 0;
    public float rLegDropC = 0;
    public float rLegMoveE = 10.0F;
    public float legSpeed = 1.0F;
    public float strideLength = 0.35F;
    public float currentSpeed = 0.0F;
    public float maxSpeed = 3.0F;
    
    public int walkAnimationStage = 0;
    public boolean isWalking = false;
    public boolean lastfoot = false; // false = left, true = right
    public boolean isReversing = false;
    
    // Mech torso tilt.
    public float tiltUpMax = -0.2F;
    public float tiltDownMax = 0.3F;
    public float mechTilt = 0.0F;
    
    // Toggle state to limit the key press to one packet.
    public boolean canSendForwardPacket = true;
    public boolean canSendBackwardPacket = true;

    public int numUsingPlayers;
    public ItemStack[] chestContents = new ItemStack[12];
    public ContainerMT chestContainer;
    
    // Keeps track of all the players accessing this chest.
    public HashSet<EntityPlayer> chestViewers = new HashSet<EntityPlayer>();

    // Keeps track of if the gui is open or not before closing and cleaning up the entry in chestViewers.
    public boolean guiOpen = false;
    
    // (From TileEntity)
    private String field_94045_s;
    
    // Fuel.
    public int BurnTime = 0;
    public int currentItemBurnTime = 0;
    public boolean BurnActive = false;

    public ItemStack magicitestack = new ItemStack(MagitekItems.magicite);
    public boolean magislot = true;
    public boolean magislotWasFalse = true;
    public boolean auxslot = false;
    public boolean auxslotWasFalse = false;
   
    public double beamSize = 0.0D;
    public double beamDistance = 0.0D;
    public double beamDistanceMax = 14.0D; // Texture is 8, but could use a bit more length.
    public int fireTime = 0;
    public int fireTimeMax = 40;
    public int coolDown = 0;
    public boolean beamFiring = false;

    public RenderFireBeam beamRender;
    public int beamRenderIndex;
    
    public double firerandom1;
    public double firerandom2;
    
     // Set of block coordinates and values. The block at the coordinate will burn after the value exceeds a limit.
    public HashMap<TripletInt, Short> heatingBlocks = new HashMap<TripletInt, Short>();

    private boolean smeltedTarget = false; 

    public boolean blink = false;
    
    public boolean isDocked = false;
    public int dockSettleTimer = -1; // -1 Not docked; do nothing. 0: Create dock block. >0 timer decrements.
    public short dockSide;
    public MutableTripletInt dockBlock = new MutableTripletInt(0,0,0); //Last placed dockBlock location.
    
    // Is undocked if the current position is different from these.
    public double dockLockX = 0;
    public double dockLockY = 0;
    public double dockLockZ = 0;

    private EntityLiving lastAttackingEntity = null;
    private EntityLiving entityLivingToAttack = null;
    private int revengeTimer = 0;

    private int fire;
    
    public InventoryMT inventorymt = new InventoryMT(this);

    public boolean firstUpdate;
    
    //private EntityLivingBase camera;

    
	public EntityMT(World world)
	{
		super(world);
        this.stepHeight = 1.0F;
        this.isImmuneToFire = true;
        this.unDock();
        this.firstUpdate = true;
	}
	
	public EntityMT(World world, Entity entity)
	{
	    
	    this(world);
	    setRotation(entity.rotationYaw, 0);
	}	   
	
	@Override
	protected void entityInit()
	{
	super.entityInit();
    this.dataWatcher.addObject(20, Integer.valueOf(0)); //currentItemBurnTime - The total burn time.
    this.dataWatcher.addObject(21, Integer.valueOf(0)); //BurnTime - The remaining burn time.
    this.dataWatcher.addObject(22, Byte.valueOf((byte) Math.random())); // One of two random values that decides whether the beam ignites a block or not. 
    this.dataWatcher.addObject(23, Byte.valueOf((byte) Math.random())); // One of two random values that decides whether the beam ignites a block or not. 
    this.dataWatcher.addObject(24, Integer.valueOf(0)); // smeltedTarget = false
    this.dataWatcher.addObject(25, Float.valueOf(0)); // this.getHealth() - for damage indication.
	}

	
    public void initCreature(EntityPlayer player) 
    {        
        if (this.magicitestack != null)
        {
          this.chestContents[1] = this.magicitestack;
        }
        else // If spawning in a new mech in creative.
        {
          this.chestContents[1] = new ItemStack(MagitekItems.magicite);
        }
        
        this.rotationYaw = player.rotationYaw;
        
        this.magislot = true;
        this.magislotWasFalse = true;
        this.auxslot = true;
        this.auxslotWasFalse = true;
    }
    
    protected void dropFewItems(boolean par1, int par2)
    {
        this.dropItem(MagitekItems.mecharm.itemID, 2);
        this.dropItem(MagitekItems.mechleg.itemID, 2);
        this.dropItem(MagitekItems.mechtorso.itemID, 1);
        this.dropItem(MagitekItems.mechstack.itemID, 2);
        
        int itemindex;
        for (itemindex = 0; itemindex < this.chestContents.length; ++itemindex)
        {
            if (this.chestContents[itemindex] != null)
            {
                this.entityDropItem(new ItemStack(this.chestContents[itemindex].itemID, this.chestContents[itemindex].stackSize,  this.chestContents[itemindex].getItemDamage()), 0);
            }
        }
        
    }
    
    public float ScaleAmount()
    {
        return 1.0F;
    }
    
  
    // Apparently deals with some basic physics like gravity.
    public void moveEntityWithHeading(float par1, float par2)
    {
        
        if (this.riddenByEntity != null && this.worldObj.isRemote)
        {

            /*
            if (this.worldObj.isRemote)
            {
            this.prevRotationYaw = this.rotationYaw = this.riddenByEntity.prevRotationYaw;
            this.rotationYawHead = this.renderYawOffset = this.prevRotationYaw = this.rotationYaw;
            }
            */

        }
        
        
        super.moveEntityWithHeading(par1, par2);
    }
    
    
    @Override
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
    }
	
    public boolean isAIEnabled()
    {
            return false;
    }

    public void faceEntity(Entity entity, float par2, float par3)
    {
    }
    
    @Override
    public void setMoveForward(float par1)
    {
    }
    
    protected float getSoundVolume()
    {
        return 0.8F;
    }
    
    protected String getHurtSound()
    {
        return "mtm:mechdamaged";
    }
    
    protected String getDeathSound()
    {
        return "mtm:mechdeath";
    }
    
    public void onUpdate()
    {
        if (ForgeHooks.onLivingUpdate(this))
        {

            return;
        }
        this.onLivingUpdate();
        this.onEntityUpdate();
        

        //Initial facing. (RotationYaw is random on initialization?)
        this.renderYawOffset = 0F;
        
        this.renderYawOffset = this.rotationYaw;
        
        //Keep mech facing toward players facing direction.
        if (this.riddenByEntity != null)
        {
            
            this.worldObj.theProfiler.startSection("TurnMech");
                float f5 = MathHelper.wrapAngleTo180_float(this.rotationYaw  - this.renderYawOffset);
                if (true)
                {
                    this.renderYawOffset += f5 * 0.2F;
                    
                }
            this.worldObj.theProfiler.endSection();
            
        }
        
        
        if (!this.chestViewers.isEmpty())
        {
            List<EntityPlayer> cvcopy = new ArrayList<EntityPlayer>(chestViewers);
            boolean breakcrafting = false;
            
            for (int i=cvcopy.size()-1; i> -1; i--) 
            {
                EntityPlayer entityplayer = cvcopy.get(i);
                Vec3 playerpos = Vec3.createVectorHelper(entityplayer.posX, entityplayer.posY, entityplayer.posZ);
                Vec3 mtpos = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
                double distance = playerpos.distanceTo(mtpos);
                
                if (distance > 4 || this.isWalking|this.isReversing == true)
                {
                    entityplayer.closeScreen();
                    //cvcopy.remove(entityplayer);
                    breakcrafting = true;
                }
            }
            
            if (breakcrafting == true)
            {
            chestViewers.clear();
            //chestViewers.addAll(cvcopy);
            }
            
        }
        
        while (this.rotationYaw - this.prevRotationYaw < -180.0F)
        {
            this.prevRotationYaw -= 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
        {
            this.prevRotationYaw += 360.0F;
        }

        while (this.renderYawOffset - this.prevRenderYawOffset < -180.0F)
        {
            this.prevRenderYawOffset -= 360.0F;
        }

        while (this.renderYawOffset - this.prevRenderYawOffset >= 180.0F)
        {
            this.prevRenderYawOffset += 360.0F;
        }

        while (this.rotationPitch - this.prevRotationPitch < -180.0F)
        {
            this.prevRotationPitch -= 360.0F;
        }

        while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
        {
            this.prevRotationPitch += 360.0F;
        }

        while (this.rotationYawHead - this.prevRotationYawHead < -180.0F)
        {
            this.prevRotationYawHead -= 360.0F;
        }

        while (this.rotationYawHead - this.prevRotationYawHead >= 180.0F)
        {
            this.prevRotationYawHead += 360.0F;
        }
    }
    
    public void updateRidden()
    {
        super.updateRidden();
    }
	
	public void onLivingUpdate()
	{
	    super.onLivingUpdate(); //More Movement stuff in here.
	    
        if (this.isWalking)
        {

            doWalkAnimation();
            if (this.currentSpeed < this.maxSpeed)
            {
               this.currentSpeed += 0.02F;
            }
        }
        else if (this.isReversing)
        {
            doReverseAnimation();
            if (this.currentSpeed < this.maxSpeed/2)
            {
               this.currentSpeed += 0.02F;
            }
        }
        else
        {
            this.walkAnimationStage = 0;
            this.lLegMoveC = 0;
            this.rLegMoveC = 0;
            this.legDown = false;
        }
        
        if(this.isDocked)
        {
            if (this.dockSettleTimer > 0)
            {
                this.dockSettleTimer--;
            }
            else if(this.dockSettleTimer == 0)
            {
                this.createBlock(this.dockSide);
                this.dockLockX = this.posX;
                this.dockLockY = this.posY;
                this.dockLockZ = this.posZ;
                this.dockSettleTimer--;
            }
            
            if (this.dockSettleTimer < 0)
            {
                if (this.dockLockX != this.posX || this.dockLockY != this.posY || this.dockLockZ != this.posZ )
                {
                    this.unDock();
                }
            }
        }
        
        boolean magirequired = false;
        if (!(this instanceof EntityPA) && !this.isMagislotted())
            magirequired = true;
        
        if (this.BurnActive == true && magirequired == false)
        {
            
            if (riddenByEntity != null)
            {
    
                EntityPlayer entityRider = (EntityPlayer)riddenByEntity;
                
                //this.setSprinting(entityRider.isSprinting());
                
                /*
                if (this.isSprinting())  
                {
    
                    this.currentSpeed = this.maxSpeed;
                    this.legSpeed = 3.5F;
                }
                else
                {
                    this.legSpeed = 1.0F;
                }
                */
                
                if (!this.worldObj.isRemote)
                {
                    //System.out.println(this.riddenByEntity.rotationPitch);
                    int omechid = this.entityId;
                    float omechpitch = this.rotationPitch;
                    
                    ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                    DataOutputStream outputStream = new DataOutputStream(bos);
                    try 
                    {
                        outputStream.writeInt(omechid);
                        outputStream.writeFloat(omechpitch);
                    } 
                    catch (Exception ex) 
                    {
                        ex.printStackTrace();
                    }
                    Packet250CustomPayload packet = new Packet250CustomPayload();
                    packet.channel = "mechsync";
                    packet.data = bos.toByteArray();
                    packet.length = bos.size();
                    PacketDispatcher.sendPacketToAllAround(this.posX, this.posY, this.posZ, 80, this.dimension, packet);
                }
                
                if (this.worldObj.isRemote && this.getHealth() > 0 && Minecraft.getMinecraft().inGameHasFocus && !this.isDocked) // This is the client.
                {
                    if (entityRider == Minecraft.getMinecraft().thePlayer)
                    {
                        if (Keyboard.isKeyDown(Keyboard.KEY_W))
                        { 
                            if (this.canSendForwardPacket)
                            {
                                int keydata = 1; 
                                ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                                DataOutputStream outputStream = new DataOutputStream(bos);
                                try 
                                {
                                    outputStream.writeInt(keydata);
                                } 
                                catch (Exception ex) 
                                {
                                    ex.printStackTrace();
                                }
                                Packet250CustomPayload packet = new Packet250CustomPayload();
                                packet.channel = "mech";
                                packet.data = bos.toByteArray();
                                packet.length = bos.size();
                                PacketDispatcher.sendPacketToServer(packet);
                                
                                //System.out.println("Set Walking before sending packet to server");
                                this.isWalking = true;
                                this.doForward();
                                
                                this.canSendForwardPacket = false;
                            }
                        }
                        
    
                        if (!Keyboard.isKeyDown(Keyboard.KEY_W))
                        {
                            if (!this.canSendForwardPacket)
                            {                    
                                int keydata = 2; 
                                ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                                DataOutputStream outputStream = new DataOutputStream(bos);
                                try 
                                {
                                    outputStream.writeInt(keydata);
                                } 
                                catch (Exception ex) 
                                {
                                    ex.printStackTrace();
                                }
                                Packet250CustomPayload packet = new Packet250CustomPayload();
                                packet.channel = "mech";
                                packet.data = bos.toByteArray();
                                packet.length = bos.size();
                                PacketDispatcher.sendPacketToServer(packet);
                                
                                //System.out.println("Client telling server to stop Walking.");
                                this.isWalking = false;
                                this.doStop();
                                
                                this.canSendForwardPacket = true;
                            }
                            
    
                        }
                        
                        if (Keyboard.isKeyDown(Keyboard.KEY_S))
                        { 
                            if (this.canSendBackwardPacket)
                            {
                                int keydata = 8; 
                                ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                                DataOutputStream outputStream = new DataOutputStream(bos);
                                try 
                                {
                                    outputStream.writeInt(keydata);
                                } 
                                catch (Exception ex) 
                                {
                                    ex.printStackTrace();
                                }
                                Packet250CustomPayload packet = new Packet250CustomPayload();
                                packet.channel = "mech";
                                packet.data = bos.toByteArray();
                                packet.length = bos.size();
                                PacketDispatcher.sendPacketToServer(packet);
                                
                                this.isReversing = true;
                                this.doBackward();

                                this.canSendBackwardPacket = false;
                            }
                        }
                        
    
                        if (!Keyboard.isKeyDown(Keyboard.KEY_S))
                        {
                            if (!this.canSendBackwardPacket)
                            {                    
                                int keydata = 2; 
                                ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                                DataOutputStream outputStream = new DataOutputStream(bos);
                                try 
                                {
                                    outputStream.writeInt(keydata);
                                } 
                                catch (Exception ex) 
                                {
                                    ex.printStackTrace();
                                }
                                Packet250CustomPayload packet = new Packet250CustomPayload();
                                packet.channel = "mech";
                                packet.data = bos.toByteArray();
                                packet.length = bos.size();
                                PacketDispatcher.sendPacketToServer(packet);
                                
                                this.isReversing = false;
                                this.doStop();
                                
                                this.canSendBackwardPacket = true;
                            }
                            
    
                        }
                        
                        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && this.onGround)
                        {
                            int keydata = 3; 
                            ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                            DataOutputStream outputStream = new DataOutputStream(bos);
                            try 
                            {
                                outputStream.writeInt(keydata);
                            } 
                            catch (Exception ex) 
                            {
                                ex.printStackTrace();
                            }
                            Packet250CustomPayload packet = new Packet250CustomPayload();
                            packet.channel = "mech";
                            packet.data = bos.toByteArray();
                            packet.length = bos.size();
                            PacketDispatcher.sendPacketToServer(packet);
                            this.doJump();
                            
                        }
                        
                        if (!this.beamFiring && this.riddenByEntity.isSneaking() && Mouse.isButtonDown(1))
                            {
                                this.clientFireBeam();
                            }
                        
                    }
                    
    
                }
                
            if (this.isDocked == false)
            {

                if (!this.worldObj.isRemote)
                {
                    // Keeps mech rotated toward rider facing.
                    //rotationYaw = prevRotationYaw = entityRider.rotationYawHead;

                    this.rotationYaw = this.prevRotationYaw = entityRider.rotationYaw;
                    entityRider.renderYawOffset = this.renderYawOffset;
                    
                    
                    // Tilt the mech with the player, slightly.
                    this.mechTilt = entityRider.rotationPitch / 200;
                    if (this.mechTilt > this.tiltDownMax)
                    {
                        this.mechTilt = this.tiltDownMax;
                    }
                    else if (this.mechTilt < this.tiltUpMax)
                    {
                        this.mechTilt = this.tiltUpMax;
                    }
                    this.rotationPitch = this.mechTilt;
                    
                    
                }
                else
                {
                    Minecraft mc = Minecraft.getMinecraft();
                    if (mc.thePlayer == entityRider)
                    {
                        this.rotationYaw = this.prevRotationYaw = entityRider.rotationYaw;
                        
                        entityRider.renderYawOffset = this.renderYawOffset;
                        
                        this.mechTilt = entityRider.rotationPitch / 200;
                        if (this.mechTilt > this.tiltDownMax)
                        {
                            this.mechTilt = this.tiltDownMax;
                        }
                        else if (this.mechTilt < this.tiltUpMax)
                        {
                            this.mechTilt = this.tiltUpMax;
                        }
                        this.rotationPitch = this.mechTilt;
                        

                        //System.out.println("EntMT.rotationpitchAfter:" + this.rotationPitch);
                        //System.out.println("EntMT.rotationpitchAdjusted:" + this.rotationPitch * 200);
                        //System.out.println("Riderrotationpitch:" + this.riddenByEntity.rotationPitch);
                        
                    }
                    else
                    {
                        //System.out.println("EntMT.rotationpitchBefore:" + this.rotationPitch);
                        //System.out.println("Rider.rotationpitchBefore:" + this.riddenByEntity.rotationPitch);
                        
                        this.mechTilt = entityRider.rotationPitch / 200;
                        if (this.mechTilt > this.tiltDownMax)
                        {
                            this.mechTilt = this.tiltDownMax;
                        }
                        else if (this.mechTilt < this.tiltUpMax)
                        {
                            this.mechTilt = this.tiltUpMax;
                        }
                        this.rotationPitch = this.mechTilt;
                        this.riddenByEntity.rotationPitch = this.mechTilt * 200;
                        //System.out.println("EntMT.rotationpitchAfter:" + this.rotationPitch);
                        //System.out.println("Rider.rotationpitchAfter:" + this.riddenByEntity.rotationPitch);
                        
                    }
                }
            }
        
            }
            else // No rider.
            {
                if (this.isWalking == true)
                {
                    //System.out.println("Stopped walking: no rider");
                    this.isWalking = false;
                    this.setSprinting(false);
                    this.doStop();
                }
                
                if (this.isReversing == true)
                {
                    this.isReversing = false;
                    this.setSprinting(false);
                    this.doStop();
                }
            }
        }
        
        
        else
        {
            if (this.isWalking == true)
            {
                //System.out.println("Stopped walking: no fuel");
                this.isWalking = false;
                this.setSprinting(false);
                this.doStop();
            }
            
            if (this.isReversing == true)
            {
                this.isReversing = false;
                this.setSprinting(false);
                this.doStop();
            }
        }

        if (this.BurnTime == 0)
        {
            this.BurnActive = false;
        }
        
        double smokeangle = this.rotationYaw + 180;
        double smokeradians = Math.toRadians(smokeangle);
        double sidedisplacex = Math.cos(smokeradians);
        double sidedisplacez = Math.sin(smokeradians);
        sidedisplacex *= 0.55;
        sidedisplacez *= 0.55;
        smokeradians = Math.toDegrees(smokeradians);
        smokeradians += 90;
        smokeradians = Math.toRadians(smokeradians);
        double fwddisplacex = Math.cos(smokeradians);
        double fwddisplacez = Math.sin(smokeradians);
        
        if (this.BurnTime > 0)
        {
            this.BurnActive = true;
            this.produceSmoke(sidedisplacex, fwddisplacex, sidedisplacez, fwddisplacez);
        }
        
        if (this.getHealth() < 10)
        {
            this.worldObj.spawnParticle("largesmoke", this.posX + fwddisplacex * 0.8, this.posY + 1.8, this.posZ + fwddisplacez * 0.8, 0, 0, 0);
            //this.worldObj.spawnParticle("lava", this.posX + fwddisplacex * 0.8, this.posY + 1.8, this.posZ + fwddisplacez * 0.8, 0, 0, 0);
        }
        
        if (!this.worldObj.isRemote)
        {
            if (this.getHealth() < 10)
            {
            this.dataWatcher.updateObject(25, this.getHealth());
            this.worldObj.spawnParticle("largesmoke", this.posX + fwddisplacex * 0.8, this.posY + 1.8, this.posZ + fwddisplacez * 0.8, 0, 0, 0);
            }
            
        }
        else
        {
            float healthFromServer = this.dataWatcher.getWatchableObjectFloat(25);
            if (healthFromServer < 10 && healthFromServer > 0)
            {
                this.worldObj.spawnParticle("largesmoke", this.posX + fwddisplacex * 0.8, this.posY + 1.8, this.posZ + fwddisplacez * 0.8, 0, 0, 0);
            }
        }
        
        if (this.getHealth() == 0)
        {
            this.worldObj.spawnParticle("lava", this.posX, this.posY, this.posZ, 0, 0, 0);
            this.worldObj.spawnParticle("lava", this.posX, this.posY, this.posZ, 0, 0, 0);
            this.worldObj.spawnParticle("lava", this.posX, this.posY, this.posZ, 0, 0, 0);
        }
        
        this.beamDistance = 0;
        if (this.beamFiring == true)
        {
            
            
            
            this.beamDistance = this.beamDistanceMax;
            if (this.beamSize != 0)
            {
                this.beamSize = this.beamSize / 1.2;
            }
            
            if (this.fireTime == 0)
            {
                this.beamFiring = false;
                this.heatingBlocks.clear();
            }
            
            
            double pitchRadians = Math.toRadians(((this.rotationPitch * 200 + RenderFireBeam.beamPitchModifier) + 180));
            double yawRadians = Math.toRadians(this.rotationYaw + 180);
            double sinPitch = Math.sin(pitchRadians);
            double cosPitch = Math.cos(pitchRadians);
            double sinYaw = Math.sin(yawRadians);
            double cosYaw = Math.cos(yawRadians);
            double bx = this.posX + ((-cosPitch * sinYaw)  * this.beamDistance);
            double by = this.posY + (sinPitch * this.beamDistance) + RenderFireBeam.beamYModifier;
            double bz = this.posZ + -((-cosPitch * cosYaw)  * this.beamDistance);

            
            Vec3 startvec = Vec3.createVectorHelper(this.posX, this.posY + RenderFireBeam.beamYModifier, this.posZ);
            
            Vec3 pointedvec = Vec3.createVectorHelper(bx,
                                                      by, 
                                                      bz);
                                                      
            MovingObjectPosition foundblock = this.worldObj.clip(startvec, pointedvec, true);
            
            
            if (foundblock != null)
            {
                
                this.beamDistance -= foundblock.hitVec.distanceTo(pointedvec);
                if (this.beamDistance < 0 )this.beamDistance = 0;
                
                bx = this.posX + ((-cosPitch * sinYaw)  * this.beamDistance);
                by = this.posY + (sinPitch * this.beamDistance) + RenderFireBeam.beamYModifier;
                bz = this.posZ + -((-cosPitch * cosYaw)  * this.beamDistance);
                
                int fx = foundblock.blockX;
                int fy = foundblock.blockY;
                int fz = foundblock.blockZ;
                int targetblockid = this.worldObj.getBlockId(foundblock.blockX, foundblock.blockY, foundblock.blockZ);
                Material targetblockmaterial = this.worldObj.getBlockMaterial(fx, fy, fz);
                
                if (targetblockmaterial == Material.glass)
                {
                    this.beamDistance = this.beamDistanceMax;                                             
                }
                
                if (targetblockid == 0)
                {
                    --fy;
                }
                
                this.worldObj.spawnParticle("largesmoke", bx + Math.random(), 
                        by + Math.random(),
                        bz + Math.random(), 
                        0, 0, 0);


                //Hit from the top, above is clear.
                if (foundblock.sideHit == 1 && this.worldObj.getBlockId(fx, fy+1, fz) == 0)
                {
                    EntityPlayer player = (EntityPlayer) this.riddenByEntity;
                    ItemStack itemstack = new ItemStack(Item.flint); //Maybe this will help for fire permissions?
                    if (player != null && player.canPlayerEdit(fx, fy+1, fz, foundblock.sideHit, itemstack)) // Crashes sometimes.. when lighting itself?
                    {
                        TripletInt tripletInt = new TripletInt(fx,fy+1,fz);
                        short heatvalue;
                        if (!this.heatingBlocks.isEmpty())
                        {
                            if (this.heatingBlocks.containsKey(tripletInt))
                            {
                                heatvalue = this.heatingBlocks.get(tripletInt);
                                heatvalue += 1;
                                this.heatingBlocks.put(tripletInt, heatvalue);
                                
                                if (this.worldObj.isRemote)
                                {
                                    this.firerandom1 = (double)this.dataWatcher.getWatchableObjectByte(22);
                                    this.firerandom2 = (double)this.dataWatcher.getWatchableObjectByte(23);
                                }
                                else
                                {
                                    this.firerandom1 = Math.random();
                                    this.firerandom2 = Math.random();
                                }
                                
                                
                                if (heatvalue > 4 && this.firerandom1 > 0.6 || heatvalue > 1 && this.firerandom2 > 0.85)
                                {
                                    if (!this.worldObj.isRemote)
                                    {
                                        this.dataWatcher.updateObject(22, (byte)this.firerandom1);
                                        this.dataWatcher.updateObject(23, (byte)this.firerandom2);
                                        
                                        int blockid = this.worldObj.getBlockId(fx, fy, fz);
                                        ItemStack blockstack = new ItemStack(Item.itemsList[blockid]);
                                        FurnaceRecipes fr = FurnaceRecipes.smelting();
                                        ItemStack smeltedstack = fr.getSmeltingResult(blockstack);
                                        if (smeltedstack != null)
                                        {
                                            smeltedstack = smeltedstack.copy();
                                            player.addExperience((int)fr.getExperience(smeltedstack));
                                            this.worldObj.destroyBlock(fx, fy, fz, false);
                                            EntityItem entityitem = new EntityItem(this.worldObj, (double)fx, (double)fy, (double)fz, smeltedstack);
                                            this.worldObj.spawnEntityInWorld(entityitem);
                                            this.smeltedTarget = true;
                                            this.dataWatcher.updateObject(24, 1); //smeltedTarget = true;
                                            
                                            
                                        }
                                        else
                                        {
                                            if (targetblockmaterial == Material.glass)
                                            {       
                                            }
                                            else if (targetblockmaterial.isReplaceable())
                                            {
                                                this.worldObj.setBlock(fx, fy, fz, Block.fire.blockID);   
                                                this.worldObj.playSoundAtEntity(this, "mtm:mechfueled", 0.5F, 1.00F);           
                                            }
                                            else if (targetblockmaterial == Material.ice)
                                            {
                                                this.worldObj.setBlock(fx, fy, fz, Block.waterStill.blockID);
                                                this.worldObj.playAuxSFX(1004, fx, fy, fz, 0);                                             
                                            }
                                            else if (targetblockmaterial == Material.leaves)
                                            {
                                                this.worldObj.setBlock(fx, fy, fz, Block.fire.blockID);   
                                                this.worldObj.playSoundAtEntity(this, "mtm:mechfueled", 0.5F, 1.00F);                                                   
                                            }
                                            else if (targetblockmaterial == Material.wood)
                                            {
                                                this.worldObj.setBlock(fx, fy, fz, Block.fire.blockID);   
                                                this.worldObj.playSoundAtEntity(this, "mtm:mechfueled", 0.5F, 1.00F);                                                
                                            }
                                            else if (targetblockmaterial == Material.cloth)
                                            {
                                                this.worldObj.setBlock(fx, fy, fz, Block.fire.blockID);   
                                                this.worldObj.playSoundAtEntity(this, "mtm:mechfueled", 0.5F, 1.00F);                                                
                                            }
                                            else if (targetblockmaterial == Material.web)
                                            {
                                                this.worldObj.setBlock(fx, fy, fz, Block.fire.blockID);   
                                                this.worldObj.playSoundAtEntity(this, "mtm:mechfueled", 0.5F, 1.00F);                                                
                                            }
                                            else
                                            {
                                                this.worldObj.setBlock(fx, fy+1, fz, Block.fire.blockID);   
                                                this.worldObj.playSoundAtEntity(this, "mtm:mechfueled", 0.5F, 1.00F);
                                                this.dataWatcher.updateObject(24, 0);
                                            }
                                        }
                                    }
                                }
                            }
                            else
                            {
                                this.heatingBlocks.put(tripletInt, (short) 0);
                            }      

                        }
                        else
                        {
                            this.heatingBlocks.put(tripletInt, (short) 0);
                        }      
         
                    }
                    else
                    {

                    }
                }
                
                //Hit from anywhere else.
                else
                {
                    EntityPlayer player = (EntityPlayer) this.riddenByEntity;
                    ItemStack itemstack = new ItemStack(Item.flint); //Maybe this will help for fire permissions?
                    if (player != null && player.canPlayerEdit(fx, fy+1, fz, foundblock.sideHit, itemstack))
                    {
                        TripletInt tripletInt = new TripletInt(fx,fy+1,fz);
                        short heatvalue;
                        if (!this.heatingBlocks.isEmpty())
                        {
                            if (this.heatingBlocks.containsKey(tripletInt))
                            {
                                heatvalue = this.heatingBlocks.get(tripletInt);
                                heatvalue += 1;
                                this.heatingBlocks.put(tripletInt, heatvalue);
                                
                                
                                if (this.worldObj.isRemote)
                                {
                                    this.firerandom1 = (double)this.dataWatcher.getWatchableObjectByte(22);
                                    this.firerandom2 = (double)this.dataWatcher.getWatchableObjectByte(23);
                                }
                                else
                                {
                                    this.firerandom1 = Math.random();
                                    this.firerandom2 = Math.random();
                                }
                                
                                
                                if (heatvalue > 4 && this.firerandom1 > 0.6 || heatvalue > 1 && this.firerandom2 > 0.85)
                                {
                                    if (!this.worldObj.isRemote)
                                    {
                                        this.dataWatcher.updateObject(22, (byte)this.firerandom1);
                                        this.dataWatcher.updateObject(23, (byte)this.firerandom2);
                                        
                                        ItemStack blockstack = new ItemStack(Item.itemsList[targetblockid]);
                                        FurnaceRecipes fr = FurnaceRecipes.smelting();
                                        ItemStack smeltedstack = fr.getSmeltingResult(blockstack);
                                        if (smeltedstack != null)
                                        {
                                            smeltedstack = smeltedstack.copy();
                                            player.addExperience((int)fr.getExperience(smeltedstack));
                                            this.worldObj.destroyBlock(fx, fy, fz, false);
                                            EntityItem entityitem = new EntityItem(this.worldObj, (double)fx, (double)fy, (double)fz, smeltedstack);
                                            this.worldObj.spawnEntityInWorld(entityitem);
                                            this.smeltedTarget = true;
                                            this.dataWatcher.updateObject(24, 1); //smeltedTarget = true;
                                        }
                                        else
                                        {

                                            if (targetblockmaterial == Material.glass)
                                            {       
                                            }
                                            else if (targetblockmaterial.isReplaceable())
                                            {
                                                this.worldObj.setBlock(fx, fy, fz, Block.fire.blockID);   
                                                this.worldObj.playSoundAtEntity(this, "mtm:mechfueled", 0.5F, 1.00F);           
                                            }
                                            else if (targetblockmaterial == Material.ice)
                                            {
                                                this.worldObj.setBlock(fx, fy, fz, Block.waterStill.blockID);
                                                this.worldObj.playAuxSFX(1004, fx, fy, fz, 0);                                             
                                            }
                                            else if (targetblockmaterial == Material.leaves)
                                            {
                                                this.worldObj.setBlock(fx, fy, fz, Block.fire.blockID);   
                                                this.worldObj.playSoundAtEntity(this, "mtm:mechfueled", 0.5F, 1.00F);                                                   
                                            }
                                            else if (targetblockmaterial == Material.wood)
                                            {
                                                this.worldObj.setBlock(fx, fy, fz, Block.fire.blockID);   
                                                this.worldObj.playSoundAtEntity(this, "mtm:mechfueled", 0.5F, 1.00F);                                                
                                            }
                                            else if (targetblockmaterial == Material.cloth)
                                            {
                                                this.worldObj.setBlock(fx, fy, fz, Block.fire.blockID);   
                                                this.worldObj.playSoundAtEntity(this, "mtm:mechfueled", 0.5F, 1.00F);                                                
                                            }
                                            else if (targetblockmaterial == Material.web)
                                            {
                                                this.worldObj.setBlock(fx, fy, fz, Block.fire.blockID);   
                                                this.worldObj.playSoundAtEntity(this, "mtm:mechfueled", 0.5F, 1.00F);                                                
                                            }
                                            else if (targetblockmaterial == Material.tnt)
                                            {
                                                this.worldObj.setBlockToAir(fx, fy, fz);
                                                this.worldObj.createExplosion(player, fx, fy, fz, 3, true);
                                                this.worldObj.playSoundAtEntity(this, "mtm:mechfueled", 0.5F, 1.00F);                                                
                                            }
                                            
                                            this.dataWatcher.updateObject(24, 0);
                                        }
                                    }
                                }
                            }
                            else
                            {
                                this.heatingBlocks.put(tripletInt, (short) 0);
                                if (!this.worldObj.isRemote)
                                {
                                }
                            }      

                        }
                        else
                        {
                            this.heatingBlocks.put(tripletInt, (short) 0);
                            if (!this.worldObj.isRemote)
                            {
                            }
                        }      
         
                    }
                    else
                    {
                        if (!this.worldObj.isRemote)
                        {
                            this.dataWatcher.updateObject(24, 0);
                        }
                    }

                }
                
                if (this.worldObj.isRemote)
                {
                    
                    this.smeltedTarget = this.dataWatcher.getWatchableObjectInt(24) == 1;
                    if (this.smeltedTarget == true)
                    {
                        for (int i = 0; i < 6; i++)
                        {
                            this.worldObj.spawnParticle("flame", fx + Math.random(), 
                                    fy + Math.random(),
                                    fz + Math.random(), 
                                    0, 0, 0);
                        }
                        this.smeltedTarget = false;
                    }
                }
                
            }
            
            /*
            if (this.riddenByEntity!= null && this.fireTime < 10)
            {
                
                EntityPlayer ep = (EntityPlayer)this.riddenByEntity;
                
                double arrowAngle = this.riddenByEntity.rotationYaw + 180;
                double arrowRadians = arrowAngle * Math.PI / 180;
                double displaceX = Math.cos(arrowRadians) * 0.8;
                double displaceZ = Math.sin(arrowRadians) * 0.8;

                double arrowRadians2 = arrowAngle * Math.PI / 180;
                double fwdDisplaceX = Math.cos(arrowRadians2) * -0.96;
                double fwdDisplaceZ = Math.sin(arrowRadians2) * -0.96;

                boolean sideRandom = rand.nextBoolean();
                int sidePolarity = sideRandom ? 0:1;
                
                double sideTweak = rand.nextDouble() / 2;
                

                
                if (ep.inventory.hasItem(Item.arrow.itemID))
                {

                    EntityArrow entityarrow = new EntityArrow(this.worldObj, this, 1.0F);
                    entityarrow.posX += displaceX + fwdDisplaceX;
                    entityarrow.posY -= 0.2;
                    entityarrow.posZ += displaceZ + fwdDisplaceZ;
                    entityarrow.motionY += rand.nextDouble() / 3;
                    entityarrow.rotationPitch = this.rotationPitch;
                    System.out.println(entityarrow.rotationPitch);

                    //entityarrow.setIsCritical(true);
                    
                    this.worldObj.playSoundAtEntity(ep, "mtm:mechbow", 1.0F, 1.0F);

                    entityarrow.canBePickedUp = 2;
                    ep.inventory.consumeInventoryItem(Item.arrow.itemID);

                    if (!this.worldObj.isRemote)
                    {
                        this.worldObj.spawnEntityInWorld(entityarrow);
                    }
                }
                
            }
            */
            
            
            //Cycle through a line of bounding boxes on the way to the current beam distance against target entity bounding boxes.
            for (int dist = 0; dist < this.beamDistance; dist++)
            {
                bx = this.posX + ((-cosPitch * sinYaw)  * dist);
                by = this.posY + (sinPitch * dist) + RenderFireBeam.beamYModifier;
                bz = this.posZ + -((-cosPitch * cosYaw)  * dist);
                
                pointedvec = Vec3.createVectorHelper(bx,
                        by, 
                        bz);
                
                AxisAlignedBB detectAABB = this.boundingBox.copy();
                detectAABB.minX -= this.posX;
                detectAABB.minX += bx;
                detectAABB.maxX -= this.posX;
                detectAABB.maxX += bx;
                detectAABB.minY -= this.posY;
                detectAABB.minY += by;
                detectAABB.maxY -= this.posY;
                detectAABB.maxY += by;
                detectAABB.minZ -= this.posZ;
                detectAABB.minZ += bz;
                detectAABB.maxZ -= this.posZ;
                detectAABB.maxZ += bz;
                List<?> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, detectAABB);
                
                
                for (int i = 0; i < list.size() && this.ticksExisted % 5 == 0; ++i)
                {
                    Entity entity = (Entity)list.get(i);
                    
                    if (entity != null && entity.canBeCollidedWith() && entity.boundingBox != null && entity != this.riddenByEntity)
                    {
                        entity.setFire(5);  

                        entity.attackEntityFrom(DamageSource.causeMobDamage(this), 3);
                        //entity.attackEntityFrom(DamageSource.onFire, 2);
                        Vec3 lv = this.getLookVec();
                        entity.addVelocity(lv.xCoord/15, 0, lv.zCoord/15);
                    }
                
                }
            }
        }
        
        else //!beamFiring
        {
            if (!this.worldObj.isRemote)
            {
                this.dataWatcher.updateObject(24, 0);
            }
        }
        
        if (this.coolDown > 0)
        {
            this.coolDown--;
        }
        
        if (this.fireTime > 0)
        {
            this.fireTime--;
        }
        
        if (!this.worldObj.isRemote && !this.dead && this.ticksExisted % 7 == 0)
        {
            List<?> list = this.worldObj.getEntitiesWithinAABB(EntityItem.class, this.boundingBox.expand(1.0D, 0.0D, 1.0D));
            Iterator<?> iterator = list.iterator();

            while (iterator.hasNext())
            {
                EntityItem entityitem = (EntityItem)iterator.next();

                if (!entityitem.isDead && entityitem.getEntityItem() != null)
                {
                    this.onItemPickup(entityitem, 1);
                }
            }
        }

	}

    public void produceSmoke(double sidedisplacex, double fwddisplacex, double sidedisplacez, double fwddisplacez)
    {   
        if (this.ticksExisted % 4 == 0)
        {
            if (this.blink == true)
            {
            this.worldObj.spawnParticle("smoke", this.posX + sidedisplacex + fwddisplacex / 3, this.posY + 2.8, this.posZ + sidedisplacez + fwddisplacez / 3, 0, 0, 0);
            this.blink = false;
            }
            else
            {
            this.worldObj.spawnParticle("smoke", this.posX + -sidedisplacex + fwddisplacex / 3, this.posY + 2.8, this.posZ + -sidedisplacez + fwddisplacez / 3, 0, 0, 0);
            this.blink = true;
            }
            
        }
    }

    public void onDeathUpdate()
    {
        this.deathTime += 2;
        if (this.deathTime > 20)
        {

            int i;

            if (!this.worldObj.isRemote && (this.recentlyHit > 0 || this.isPlayer()) && !this.isChild() && this.worldObj.getGameRules().getGameRuleBooleanValue("doMobLoot"))
            {
                /*
                i = this.getExperiencePoints(this.attackingPlayer);

                while (i > 0)
                {
                    int j = EntityXPOrb.getXPSplit(i);
                    i -= j;
                    this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
                }
                */
            }

            this.setDead();

            for (i = 0; i < 20; ++i)
            {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                this.worldObj.spawnParticle("explode", this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
            }
        }
    }
	
	@SideOnly(Side.CLIENT)
	public void clientFireBeam()
	{
        if (this.fireTime == 0 && this.coolDown == 0 && this.BurnActive == true) 
        {
            int keydata = 4; 
            ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
            DataOutputStream outputStream = new DataOutputStream(bos);
            try 
            {
                outputStream.writeInt(keydata);
            } 
            catch (Exception ex) 
            {
                ex.printStackTrace();
            }
            Packet250CustomPayload packet = new Packet250CustomPayload();
            packet.channel = "mech";
            packet.data = bos.toByteArray();
            packet.length = bos.size();
            PacketDispatcher.sendPacketToServer(packet);
        }
        

	}
	
    public void fireBeam()
    {
        this.worldObj.playSoundAtEntity(this, "mtm:mechbeam", 0.4F, 1.00F);
        if (this.fireTime == 0 && this.coolDown == 0 && this.BurnActive == true) 
        {
            this.beamSize = -0.12;
            this.beamFiring = true;
            this.fireTime = 40;//fireTimeMax;
            this.coolDown = 50;
            
            if (this.worldObj.isRemote)
            {
                this.beamRender = new RenderFireBeam(this);
                RenderFireBeams.firebeams.add(this.beamRender);
                this.beamRenderIndex = RenderFireBeams.firebeams.size();
            }
            
            
        }
        
        

    }
	
	// Debug dismount. Need to message the server. (Interacting normally would hand this automatically.)
    @SideOnly(Side.CLIENT)
    public void clientDismount()
    {
         int keydata = 5; 
         ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
         DataOutputStream outputStream = new DataOutputStream(bos);
         try 
         {
             outputStream.writeInt(keydata);
         } 
         catch (Exception ex) 
         {
             ex.printStackTrace();
         }
         Packet250CustomPayload packet = new Packet250CustomPayload();
         packet.channel = "mech";
         packet.data = bos.toByteArray();
         packet.length = bos.size();
         PacketDispatcher.sendPacketToServer(packet);
    }

    public void playMechStep()
    {
        if (!this.worldObj.isRemote && this.onGround && this.legDown && this.getHealth() > 0)
        {
            this.worldObj.playSoundAtEntity(this, 
                                            "mtm:mechsteptwo", 
                                            0.4F, 
                                            1.00F + (0.015F * this.currentSpeed));
        }

    }
    
    // Called by beforehand by client, and in the server when it receives a player input packet.
    public void doForward()
    {
        
        if (this.walkAnimationStage == 0)
        {
            this.walkAnimationStage = 1;
            
        }
    }
    
    public void doBackward()
    {
        if (this.walkAnimationStage == 0)
        {
            this.walkAnimationStage = 1;
            
        }
    }
    
    public void doStop()
    {
        this.walkAnimationStage = 0;
        this.lLegMoveC = 0;
        this.rLegMoveC = 0;
        this.legDown = false;
        this.currentSpeed = 0;
    }
    
    public void doJump()
    {
        this.setJumping(false);
        if (this.onGround && riddenByEntity != null)
        {
            this.jump();
            
        }
    }
    
    public void doReverseAnimation()
    {
        double revmulti = 2; // reverse speed multiplier
        if (this.walkAnimationStage == 1)
        {
            this.legDown = false;
            
            // Left rising.
            if (this.lLegMoveC <= 5)
            {
                this.lLegMoveC += this.legSpeed;
                this.moveEntity(-1 * (this.getLookVec().xCoord * ((this.strideLength + (0.05F * this.currentSpeed)) * (this.legSpeed / revmulti))) , this.motionY, -1 * (this.getLookVec().zCoord * ((this.strideLength + (0.05F * this.currentSpeed)) * (this.legSpeed / revmulti))) );
                
            }
            
            // Right receding.
            if (this.rLegMoveC > 0)
            {
                this.rLegMoveC -= this.legSpeed * 2;
            }
            
            // Enough movement for left to drop.
            if (this.lLegMoveC > 5)
            {
                this.legDown = false;
                this.rLegMoveC = 0;
                this.walkAnimationStage = 2;
            }
            
        }
        
        else if (this.walkAnimationStage == 2)
        {
            // Left dropping.
            if (this.lLegMoveC <= this.lLegMoveE)
            {
                this.lLegMoveC += 5.0;
                this.lLegDropC += 5.0;
            }
            
            // Enough movement for left to recede.
            else
            {
                this.lLegDropC = 0;
                this.legDown = true; 
                
                this.playMechStep();
                
                this.walkAnimationStage = 3;
            }
        }
        
        else if (this.walkAnimationStage == 3)
        {

            this.legDown = false;
            
            // Right rising.
            if (this.rLegMoveC <= 5)
            {
                this.rLegMoveC += this.legSpeed;
                this.moveEntity(-1 * (this.getLookVec().xCoord * ((this.strideLength + (0.05F * this.currentSpeed)) * (this.legSpeed / revmulti))) , this.motionY, -1 * (this.getLookVec().zCoord * ((this.strideLength + (0.05F * this.currentSpeed) / revmulti) * (this.legSpeed / revmulti))) );
            }
            
            // Left receding.
            if (this.lLegMoveC > 0)
            {
                this.lLegMoveC -= this.legSpeed * 2;
            }
            
            // Enough movement for right to drop.
            if (this.rLegMoveC > 5)
            {
                this.lLegMoveC = 0;
                this.walkAnimationStage = 4;
            }
        }
        
        else if (this.walkAnimationStage == 4)
        {

            // Right dropping.
            if (this.rLegMoveC <= this.rLegMoveE)
            {
                this.rLegMoveC += 5.0;
                this.rLegDropC += 5.0;
            }
            
            // Enough movement for right to recede.
            else
            {
                this.rLegDropC = 0;
                this.legDown = true; 
                
                this.playMechStep();
                
                this.walkAnimationStage = 1;  
            }
        }
    }
    
	// Parts of the animation are done server side so that the Mech moves with each push of a leg.
	public void doWalkAnimation()
	{

        if (this.walkAnimationStage == 1)
        {
            this.legDown = false;
            
            // Left rising.
            if (this.lLegMoveC <= 5)
            {
                this.lLegMoveC += this.legSpeed;
            }
            
            // Right receding.
            if (this.rLegMoveC > 0)
            {
                this.rLegMoveC -= this.legSpeed * 2;
                this.moveEntity(this.getLookVec().xCoord * ((this.strideLength + (0.05F * this.currentSpeed)) * this.legSpeed) , this.motionY, this.getLookVec().zCoord * ((this.strideLength + (0.05F * this.currentSpeed)) * this.legSpeed) );
            }
            
            // Enough movement for left to drop.
            if (this.lLegMoveC > 5)
            {
                this.legDown = false;
                this.rLegMoveC = 0;
                this.walkAnimationStage = 2;
            }
            
        }
        
        else if (this.walkAnimationStage == 2)
        {
            // Left dropping.
            if (this.lLegMoveC <= this.lLegMoveE)
            {
                this.lLegMoveC += 5.0;
                this.lLegDropC += 5.0;

            }
            
            // Enough movement for left to recede.
            else
            {
                this.lLegDropC = 0;
                this.legDown = true; 
                
                this.playMechStep();

                this.moveEntity(this.getLookVec().xCoord * 0.01 , this.motionY, this.getLookVec().zCoord * 0.01);
                
                this.walkAnimationStage = 3;
            }
        }
        
        else if (this.walkAnimationStage == 3)
        {

            this.legDown = false;
            
            // Right rising.
            if (this.rLegMoveC <= 5)
            {
                this.rLegMoveC += this.legSpeed;
            }
            
            // Left receding.
            if (this.lLegMoveC > 0)
            {
                this.lLegMoveC -= this.legSpeed * 2;
                this.moveEntity(this.getLookVec().xCoord * ((this.strideLength + (0.05F * this.currentSpeed)) * this.legSpeed) , this.motionY, this.getLookVec().zCoord * ((this.strideLength + (0.05F * this.currentSpeed)) * this.legSpeed));
            }
            
            // Enough movement for right to drop.
            if (this.rLegMoveC > 5)
            {
                this.lLegMoveC = 0;
                this.walkAnimationStage = 4;
            }
        }
        
        else if (this.walkAnimationStage == 4)
        {

            // Right dropping.
            if (this.rLegMoveC <= this.rLegMoveE)
            {
                this.rLegMoveC += 5.0;
                this.rLegDropC += 5.0;
            }
            
            // Enough movement for right to recede.
            else
            {
                this.rLegDropC = 0;
                this.legDown = true; 
                
                this.playMechStep();
                this.moveEntity(this.getLookVec().xCoord * 0.01 , this.motionY, this.getLookVec().zCoord * 0.01);
                
                this.walkAnimationStage = 1;  
            }
        }
	}
	
	public double getMountedYOffset() 
	{
		return 1.6D;
	}
    
	@Override
    protected void updateAITasks()
    {
    }
	
	@Override
    protected void updateEntityActionState()
    {   
    }
	
	
    public void updateRiderPosition()
    {
        super.updateRiderPosition();
        if (this.riddenByEntity != null)
        {
            this.riddenByEntity.setPosition(this.posX, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ);
            
            if (this.riddenByEntity instanceof EntityLivingBase)
            {
                ((EntityLivingBase)this.riddenByEntity).renderYawOffset = this.renderYawOffset;
            }
        }
        
        
    }

    public boolean interact(EntityPlayer entityplayer)
    {
        
        // Get the difference of the angle the mech and player are facing.
        double mechangle = Math.atan2(this.getLookVec().xCoord, this.getLookVec().zCoord);
        mechangle = mechangle * 180 / Math.PI;
        mechangle += 180;
        double playerangle = Math.atan2(entityplayer.getLookVec().xCoord, entityplayer.getLookVec().zCoord);
        playerangle = playerangle * 180 / Math.PI;
        playerangle += 180;
        //double angledelta = Math.abs((((int)mechangle - (int)playerangle + 540) % 360) - 180);
        
        Item equipped;
        if (entityplayer.getCurrentEquippedItem() != null)
        {
        equipped = entityplayer.getCurrentEquippedItem().getItem();
        }
        else
        {
            equipped = null;
        }
        
        if (entityplayer.isSneaking()/* && angledelta <= 110*/ && this.riddenByEntity == null && !entityplayer.isRiding())
        {
            if (this.isDocked)
            {
                this.unDock();
                return true;
            }
            else
            {
                if (!this.worldObj.isRemote && !this.chestViewers.contains(entityplayer))
                {
                    this.chestViewers.add(entityplayer);
                }
                entityplayer.openGui(MagitekMechs.instance, this.entityId, this.worldObj, (int)this.posX, (int)this.posY, (int)this.posZ);
                this.worldObj.playSoundAtEntity(this, "mtm:mechopen", 0.6F, 1.00F);
                return true;
            }
            

        }
        
        else if (equipped != null && equipped instanceof IToolWrench && ((IToolWrench) equipped).canWrench(entityplayer, (int)entityplayer.posX, (int)entityplayer.posY, (int)entityplayer.posZ)) 
        {
            System.out.println("wrench");
            return true;
        }
        
        else
        {
            if(entityplayer.isSneaking())
            {
                return false;
            }
            
            if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != entityplayer)
            {
                return true;
            }
            else
            {
                mountInit(entityplayer);
                return true;
            }
        }
        
    }
    
    private void mountInit(EntityPlayer entityplayer)
    {
        entityplayer.rotationYaw = this.rotationYaw;
        entityplayer.rotationPitch = this.rotationPitch;
        if (!this.worldObj.isRemote)
        {
            entityplayer.mountEntity(this);
        }
    }
    
    public boolean shouldRiderFaceForward(EntityPlayer player)
    {
        return true;
    }
    
    public int getPortalCooldown()
    {
        return 20;
    }
    
    public void onEntityUpdate()
    {
        this.prevSwingProgress = this.swingProgress;
        
        //entity//
        this.worldObj.theProfiler.startSection("entityBaseTick");

        if (this.ridingEntity != null && this.ridingEntity.isDead)
        {
            this.ridingEntity = null;
        }

        this.prevDistanceWalkedModified = this.distanceWalkedModified;
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.prevRotationPitch = this.rotationPitch;
        this.prevRotationYaw = this.rotationYaw;
        int i;

        if (!this.worldObj.isRemote && this.worldObj instanceof WorldServer)
        {
            this.worldObj.theProfiler.startSection("portal");
            MinecraftServer minecraftserver = ((WorldServer)this.worldObj).getMinecraftServer();
            i = this.getMaxInPortalTime();

            if (this.inPortal)
            {
                if (minecraftserver.getAllowNether())
                {
                    if (this.ridingEntity == null && this.portalCounter++ >= i)
                    {
                        this.portalCounter = i;
                        this.timeUntilPortal = this.getPortalCooldown();
                        byte b0;

                        if (this.worldObj.provider.dimensionId == -1)
                        {
                            b0 = 0;
                        }
                        else
                        {
                            b0 = -1;
                        }

                        this.travelToDimension(b0);
                    }

                    this.inPortal = false;
                }
            }
            else
            {
                if (this.portalCounter > 0)
                {
                    this.portalCounter -= 4;
                }

                if (this.portalCounter < 0)
                {
                    this.portalCounter = 0;
                }
            }

            if (this.timeUntilPortal > 0)
            {
                --this.timeUntilPortal;
            }

            this.worldObj.theProfiler.endSection();
        }

        if (this.isSprinting() && !this.isInWater())
        {
            int j = MathHelper.floor_double(this.posX);
            i = MathHelper.floor_double(this.posY - 0.20000000298023224D - (double)this.yOffset);
            int k = MathHelper.floor_double(this.posZ);
            int l = this.worldObj.getBlockId(j, i, k);

            if (l > 0)
            {
                this.worldObj.spawnParticle("tilecrack_" + l + "_" + this.worldObj.getBlockMetadata(j, i, k), this.posX + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, this.boundingBox.minY + 0.1D, this.posZ + ((double)this.rand.nextFloat() - 0.5D) * (double)this.width, -this.motionX * 4.0D, 1.5D, -this.motionZ * 4.0D);
            }
        }

        this.handleWaterMovement();

        if (this.worldObj.isRemote)
        {
            this.fire = 0;
        }
        else if (this.fire > 0)
        {
            if (this.isImmuneToFire)
            {
                this.fire -= 4;

                if (this.fire < 0)
                {
                    this.fire = 0;
                }
            }
            else
            {
                if (this.fire % 20 == 0)
                {
                    this.attackEntityFrom(DamageSource.onFire, 1);
                }

                --this.fire;
            }
        }

        if (this.handleLavaMovement())
        {
            this.attackEntityFrom(DamageSource.lava, 1);
            this.fallDistance *= 0.5F;
        }

        if (this.posY < -64.0D)
        {
            this.kill();
        }

        if (!this.worldObj.isRemote)
        {
            this.setFlag(0, this.fire > 0);
            this.setFlag(2, this.ridingEntity != null && ridingEntity.shouldRiderSit());
        }

        this.firstUpdate = false;
        
        this.worldObj.theProfiler.endSection();
        //
        
        
        //entity living//
        this.worldObj.theProfiler.startSection("mobBaseTick");

        if (this.isEntityAlive() && this.rand.nextInt(1000) < this.livingSoundTime++)
        {
            this.livingSoundTime = -this.getTalkInterval();
            this.playLivingSound();
        }

        if (this.isEntityAlive() && this.isEntityInsideOpaqueBlock() && !this.isDocked)
        {
            this.attackEntityFrom(DamageSource.inWall, 1);
        }

        if (this.isImmuneToFire() || this.worldObj.isRemote)
        {
            this.extinguish();
        }
        
        if (this.isEntityAlive() && this.isInsideOfMaterial(Material.water) && !this.canBreatheUnderwater())
        {
            this.setAir(this.decreaseAirSupply(this.getAir()));

            if (this.getAir() == -20)
            {
                this.setAir(0);

                for (int bubbleindex = 0; bubbleindex < 8; ++bubbleindex)
                {
                    float f = this.rand.nextFloat() - this.rand.nextFloat();
                    float f1 = this.rand.nextFloat() - this.rand.nextFloat();
                    float f2 = this.rand.nextFloat() - this.rand.nextFloat();
                    this.worldObj.spawnParticle("bubble", this.posX + (double)f, this.posY + (double)f1, this.posZ + (double)f2, this.motionX, this.motionY, this.motionZ);
                }

                this.attackEntityFrom(DamageSource.drown, 2);
            }

            this.extinguish();
        }
        else
        {
            this.setAir(300);
        }
        

        this.prevCameraPitch = this.cameraPitch;

        if (this.attackTime > 0)
        {
            --this.attackTime;
        }

        if (this.hurtTime > 0)
        {
            if (this.isDocked && this.dockBlock != null)
            {
                this.unDock();
            }
            --this.hurtTime;
        }

        if (this.hurtResistantTime > 0)
        {
            --this.hurtResistantTime;
        }

        if (this.getHealth() <= 0)
        {
            this.onDeathUpdate();
        }

        if (this.recentlyHit > 0)
        {
            --this.recentlyHit;
        }
        else
        {
            this.attackingPlayer = null;
        }

        if (this.lastAttackingEntity != null && !this.lastAttackingEntity.isEntityAlive())
        {
            this.lastAttackingEntity = null;
        }

        if (this.entityLivingToAttack != null)
        {
            if (!this.entityLivingToAttack.isEntityAlive())
            {
                this.setRevengeTarget((EntityLiving)null);
            }
            else if (this.revengeTimer > 0)
            {
                --this.revengeTimer;
            }
            else
            {
                this.setRevengeTarget((EntityLiving)null);
            }
        }

        //this.updatePotionEffects(); 1.5.2
        this.field_70763_ax = this.field_70764_aw;
        this.prevRenderYawOffset = this.renderYawOffset;
        this.prevRotationYawHead = this.rotationYawHead;
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
        this.worldObj.theProfiler.endSection();
        //
        
        
        if (this.BurnTime > 0)
        {
            --this.BurnTime;
        }

        if (this.magislot == false)
        {
            if (this.chestContents[1] != null && this.chestContents[1].itemID == MagitekItems.magicite.itemID) 
            {
                this.worldObj.playSoundAtEntity(this, "mtm:mechslotted", 0.6F, 1.00F);
            }
            
        }
        
        if (!this.worldObj.isRemote && this.ticksExisted == 10)
        {
            int omechid = this.entityId; 
            int okeydata = this.BurnTime;
            int okeydata2 = this.currentItemBurnTime;
            boolean okeydata3 = this.isMagislotted();
            boolean okeydata4 = this.isAuxslotted();
            ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
            DataOutputStream outputStream = new DataOutputStream(bos);
            try 
            {
                outputStream.writeInt(omechid);
                outputStream.writeInt(okeydata);
                outputStream.writeInt(okeydata2);
                outputStream.writeBoolean(okeydata3);
                outputStream.writeBoolean(okeydata4);
            } 
            catch (Exception ex) 
            {
                ex.printStackTrace();
            }
            Packet250CustomPayload fuelpacket = new Packet250CustomPayload();
            fuelpacket.channel = "mechinvupdate";
            fuelpacket.data = bos.toByteArray();
            fuelpacket.length = bos.size();
            PacketDispatcher.sendPacketToAllAround(this.posX, this.posY, this.posZ, 80, this.dimension, fuelpacket);
        }
        
        if (this.worldObj.isRemote && this.ticksExisted == 50)
        {
            int omechid = this.entityId;
            ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
            DataOutputStream outputStream = new DataOutputStream(bos);
            try 
            {
                outputStream.writeInt(omechid);
            } 
            catch (Exception ex) 
            {
                ex.printStackTrace();
            }
            Packet250CustomPayload invreqpacket = new Packet250CustomPayload();
            invreqpacket.channel = "mechinvreq"; //Packet requesting all inv updates.
            invreqpacket.data = bos.toByteArray();
            invreqpacket.length = bos.size();
            PacketDispatcher.sendPacketToServer(invreqpacket);
            
        }
        
        
        if (!this.worldObj.isRemote)
        {
            if (this.magislot == false)
            {
                if (this.chestContents[1] != null && this.chestContents[1].itemID == MagitekItems.magicite.itemID) 
                {
                    this.magislot = true;
                    if (this.magislotWasFalse == true)
                    {
                        //System.out.println("sending packet");
                        int omechid = this.entityId; 
                        boolean okeydata = this.isMagislotted();
                        ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                        DataOutputStream outputStream = new DataOutputStream(bos);
                        try 
                        {
                            outputStream.writeInt(omechid);
                            outputStream.writeBoolean(okeydata);
                        } 
                        catch (Exception ex) 
                        {
                            ex.printStackTrace();
                        }
                        Packet250CustomPayload magislotpacket = new Packet250CustomPayload();
                        magislotpacket.channel = "mechmagi";
                        magislotpacket.data = bos.toByteArray();
                        magislotpacket.length = bos.size();
                        PacketDispatcher.sendPacketToAllAround(this.posX, this.posY, this.posZ, 80, this.dimension, magislotpacket);
                        
                        this.magislotWasFalse = false;
                    }
                }
            }
            else
            {
                if (this.chestContents[1] == null || this.chestContents[1].itemID != MagitekItems.magicite.itemID) 
                {
                    this.magislot = false;                    
                    if (this.magislotWasFalse == false)
                    {
                        //System.out.println("sending packet");
                        int omechid = this.entityId; 
                        boolean okeydata = this.isMagislotted();
                        ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                        DataOutputStream outputStream = new DataOutputStream(bos);
                        try 
                        {
                            outputStream.writeInt(omechid);
                            outputStream.writeBoolean(okeydata);
                        } 
                        catch (Exception ex) 
                        {
                            ex.printStackTrace();
                        }
                        Packet250CustomPayload magislotpacket = new Packet250CustomPayload();
                        magislotpacket.channel = "mechmagi";
                        magislotpacket.data = bos.toByteArray();
                        magislotpacket.length = bos.size();
                        PacketDispatcher.sendPacketToAllAround(this.posX, this.posY, this.posZ, 80, this.dimension, magislotpacket);
                        
                        this.magislotWasFalse = true;
                    }
                }                
            }
            if (this.auxslot == false)
            {
                if (this.chestContents[2] != null) 
                {
                    this.auxslot = true;
                    if (this.auxslotWasFalse == true)
                    {
                        //System.out.println("sending packet");
                        int omechid = this.entityId; 
                        boolean okeydata = this.isAuxslotted();
                        ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                        DataOutputStream outputStream = new DataOutputStream(bos);
                        try 
                        {
                            outputStream.writeInt(omechid);
                            outputStream.writeBoolean(okeydata);
                        } 
                        catch (Exception ex) 
                        {
                            ex.printStackTrace();
                        }
                        Packet250CustomPayload auxslotpacket = new Packet250CustomPayload();
                        auxslotpacket.channel = "mechaux";
                        auxslotpacket.data = bos.toByteArray();
                        auxslotpacket.length = bos.size();
                        PacketDispatcher.sendPacketToAllAround(this.posX, this.posY, this.posZ, 80, this.dimension, auxslotpacket);
                        
                        this.auxslotWasFalse = false;
                    }
                }
            }
            else
            {
                if (this.chestContents[2] == null) 
                {
                    this.auxslot = false;                    
                    if (this.auxslotWasFalse == false)
                    {
                        //System.out.println("sending packet");
                        int omechid = this.entityId; 
                        boolean okeydata = this.isAuxslotted();
                        ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                        DataOutputStream outputStream = new DataOutputStream(bos);
                        try 
                        {
                            outputStream.writeInt(omechid);
                            outputStream.writeBoolean(okeydata);
                        } 
                        catch (Exception ex) 
                        {
                            ex.printStackTrace();
                        }
                        Packet250CustomPayload auxslotpacket = new Packet250CustomPayload();
                        auxslotpacket.channel = "mechaux";
                        auxslotpacket.data = bos.toByteArray();
                        auxslotpacket.length = bos.size();
                        PacketDispatcher.sendPacketToAllAround(this.posX, this.posY, this.posZ, 80, this.dimension, auxslotpacket);
                        
                        this.auxslotWasFalse = true;
                    }
                }                
            }

        }
        
        if (this.worldObj.isRemote)
        {
            
            // The client only sees the inventory contents while a container is open.
            // Basically, it should only set the aux/magi slots when the container is open, and when it gets a packet.
            
            Minecraft mc = Minecraft.getMinecraft();
            if (mc.thePlayer.openContainer != null && mc.thePlayer.openContainer == this.chestContainer)
            {
                if (this.chestContents[1] != null && this.chestContents[1].itemID == MagitekItems.magicite.itemID) 
                {
                    this.magislot = true;
                }
                else
                {
                    this.magislot = false;
                }
                if (this.chestContents[2] != null)
                {
                    this.auxslot = true;
                }
                else
                {
                    this.auxslot = false;
                }
            }
            
        }
        
        if (!this.worldObj.isRemote && this.BurnTime == 0)
        {
            boolean burnable = false;
            int fuelsource = 0;
            
            this.currentItemBurnTime = this.BurnTime = getItemBurnTime(this.chestContents[0]);
            
            
            if (this.chestContents[0] != null  && this.checkBurnable(0))
            {
                burnable = true;
            }

            int invindex;
            
            
            for (invindex = 3; invindex < this.inventorymt.getSizeInventory(); ++invindex) //(invindex = 2; invindex < 11; ++invindex)
            {
                
                if (this.chestContents[invindex] != null && this.checkBurnable(invindex))
                {
                    
                    burnable = true;
                    fuelsource = invindex;
                    break;
                }
            }
            if (burnable == true)
            {
                if (this.chestContents[fuelsource] != null)
                {
                    --this.chestContents[fuelsource].stackSize;
                    

                    // Send animation packets back out to clients.
                    int omechid = this.entityId; 
                    int okeydata = this.BurnTime;
                    int okeydata2 = this.currentItemBurnTime;
                    ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                    DataOutputStream outputStream = new DataOutputStream(bos);
                    try 
                    {
                        outputStream.writeInt(omechid);
                        outputStream.writeInt(okeydata);
                        outputStream.writeInt(okeydata2);
                    } 
                    catch (Exception ex) 
                    {
                        ex.printStackTrace();
                    }
                    Packet250CustomPayload fuelpacket = new Packet250CustomPayload();
                    fuelpacket.channel = "mechfuel";
                    fuelpacket.data = bos.toByteArray();
                    fuelpacket.length = bos.size();
                    PacketDispatcher.sendPacketToAllPlayers(fuelpacket);
                    
                    this.inventorymt.onInventoryChanged();
                    
                    if (this.chestContents[fuelsource].stackSize == 0)
                    {
                        this.chestContents[fuelsource] = this.chestContents[fuelsource].getItem().getContainerItemStack(chestContents[fuelsource]);
                    }
                }
            }
        }
        
 
    }
    
    //Burnable fuel.
    private boolean checkBurnable(int index)
    {
        int getburntime = getItemBurnTime(this.chestContents[index]);
        if (getburntime > 0)
        {
            this.currentItemBurnTime = this.BurnTime = getItemBurnTime(this.chestContents[index]);
            return true;
        }
        return false;
    }
    
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
        this.chestContents = new ItemStack[this.inventorymt.getSizeInventory()];

        if (par1NBTTagCompound.hasKey("CustomName"))
        {
            this.field_94045_s = par1NBTTagCompound.getString("CustomName");
        }

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.chestContents.length)
            {
                this.chestContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        
        this.BurnTime = par1NBTTagCompound.getInteger("BurnTime");
        this.currentItemBurnTime = par1NBTTagCompound.getInteger("currentItemBurnTime");
        

        this.dockBlock.x = par1NBTTagCompound.getInteger("dockBlockX");
        this.dockBlock.y = par1NBTTagCompound.getInteger("dockBlockY");
        this.dockBlock.z = par1NBTTagCompound.getInteger("dockBlockZ");
        
        if (this.chestContents[1] != null && this.chestContents[1].itemID == MagitekItems.magicite.itemID) 
        {
            this.magislot = true;
            this.magislotWasFalse = false;
        }
        else
        {
            this.magislot = false;
            this.magislotWasFalse = true;
        }
        
        if (this.chestContents[2] != null)
        {
            this.auxslot = true;
            this.auxslotWasFalse = false;
        }
        else
        {
            this.auxslot = false;
            this.auxslotWasFalse = true;
        }
        
        
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        
        par1NBTTagCompound.setInteger("BurnTime", this.BurnTime);
        
        par1NBTTagCompound.setInteger("currentItemBurnTime", this.currentItemBurnTime);
        
        par1NBTTagCompound.setInteger("dockBlockX", this.dockBlock.x);
        par1NBTTagCompound.setInteger("dockBlockY", this.dockBlock.y);
        par1NBTTagCompound.setInteger("dockBlockZ", this.dockBlock.z);
        
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.chestContents.length; ++i)
        {
            if (this.chestContents[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.chestContents[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);

        if (this.inventorymt.isInvNameLocalized())
        {
            par1NBTTagCompound.setString("CustomName", this.field_94045_s);
        }
        
    }

    public void openChest()
    {
        if (this.numUsingPlayers < 0)
        {
            this.numUsingPlayers = 0;
        }

        ++this.numUsingPlayers;
    }

    public void closeChest()
    {
        --this.numUsingPlayers;
    }
    
    // Fuel //
    
    @SideOnly(Side.CLIENT)
    public int getBurnTimeScaled(int size)
    {
        double scalevalue = 0;
        double cibt = (double)this.currentItemBurnTime;
        double divisor = 1;
        if (cibt > 3200)
        {
            divisor = cibt / 3200;
            cibt = 3200.0;
            scalevalue = size / cibt;
            
            double newburntime = this.BurnTime / divisor;
            return (int) (newburntime * scalevalue);
        }
        else
        {
            scalevalue = size / cibt;
            
            return (int) (this.BurnTime * scalevalue);
        }
        

    }
    
    public boolean isFueled()
    {
        return this.BurnTime > 0;
    }
    
    public boolean isMagislotted()
    {
        return this.magislot;
    }
    
    public boolean isAuxslotted()
    {
        return this.auxslot;
    }
    
    public static int getItemBurnTime(ItemStack par0ItemStack)
    {
        int fuelmultiplier = 2; // More or less bang for the buck out of fuel types.
        if (par0ItemStack == null)
        {
            return 0;
        }
        else
        {
            int i = par0ItemStack.getItem().itemID;
            Item item = par0ItemStack.getItem();

            if (par0ItemStack.getItem() instanceof ItemBlock && Block.blocksList[i] != null)
            {
                Block block = Block.blocksList[i];

                if (block == Block.woodSingleSlab)
                {
                    return 150;
                }

                if (block.blockMaterial == Material.wood)
                {
                    return 300;
                }
            }

            if (item instanceof ItemTool && ((ItemTool) item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemSword && ((ItemSword) item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemHoe && ((ItemHoe) item).getMaterialName().equals("WOOD")) return 200;
            if (i == Item.stick.itemID) return 100;
            if (i == Item.coal.itemID) return 1600 * fuelmultiplier;
            if (i == Item.bucketLava.itemID) return 20000;
            if (i == Block.sapling.blockID) return 100;
            if (i == Item.blazeRod.itemID) return 2400;
            return GameRegistry.getFuelValue(par0ItemStack);
        }
    }
    
    public static boolean isItemFuel(ItemStack itemstack)
    {
        return getItemBurnTime(itemstack) > 0;
    }
    
    @Override
    public void onItemPickup(Entity par1Entity, int par2)
    {
        if (!par1Entity.isDead && !this.worldObj.isRemote)
        {
            EntityTracker entitytracker = ((WorldServer)this.worldObj).getEntityTracker();

            if (par1Entity instanceof EntityItem)
            {
                EntityItem entityitem = (EntityItem)par1Entity;
                ItemStack itemstack = entityitem.getEntityItem();
               
                if (this.chestContainer != null &&this.chestContainer.mergeDroppedItem(itemstack) != null)
                {
                    
                    entityitem.setDead();
                    entitytracker.sendPacketToAllPlayersTrackingEntity(par1Entity, new Packet22Collect(par1Entity.entityId, this.entityId));
                    //this.worldObj.playAuxSFX(1004, (int)this.posX, (int)this.posY, (int)this.posZ, 2);         
    
                    this.worldObj.playSoundAtEntity(this, "random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);  
                    this.chestContainer.detectAndSendChanges();
                    
                }
            }
        }
        

    }
    
    //Cleanup to remove the old dock block.
    public void unDock()
    {

        if (this.dockBlock != null)
        {
            TileEntityMT tileentitymt = (TileEntityMT) this.worldObj.getBlockTileEntity(this.dockBlock.x, this.dockBlock.y, this.dockBlock.z);
            if (tileentitymt == null)
            {

            }
            else if (tileentitymt instanceof TileEntityMT)
            {
                tileentitymt.sendLastContents();
                this.worldObj.removeBlockTileEntity(this.dockBlock.x, this.dockBlock.y, this.dockBlock.z);
            }
            if (this.worldObj.getBlockId(this.dockBlock.x, this.dockBlock.y, this.dockBlock.z) == MagitekBlocks.blockmt.blockID)
            {
                this.worldObj.setBlock(this.dockBlock.x, this.dockBlock.y, this.dockBlock.z, 0);
            }
        }
        this.isDocked = false;
    }
    
    public void dock()
    {
        if (this.dockBlock != null)
        {
            TileEntityMT tileentitymt = (TileEntityMT) this.worldObj.getBlockTileEntity(this.dockBlock.x, this.dockBlock.y, this.dockBlock.z);
            if (tileentitymt == null)
            {

            }
            else if (tileentitymt instanceof TileEntityMT)
            {
                tileentitymt.sendLastContents();
                this.worldObj.removeBlockTileEntity(this.dockBlock.x, this.dockBlock.y, this.dockBlock.z);
            }
            
            if (this.worldObj.getBlockId(this.dockBlock.x, this.dockBlock.y, this.dockBlock.z) == MagitekBlocks.blockmt.blockID)
            {
                this.worldObj.setBlock(this.dockBlock.x, this.dockBlock.y, this.dockBlock.z, 0);
            }
        }
        
        short xn = 0;
        short yn = 0;
        short zn = 0;
        
        if (this.posX < 0)
            xn = -1;
        
        if (this.posY < 0)
            yn = -1;
        
        if (this.posZ < 0)
            zn = -1;
        
        this.dockBlock = new MutableTripletInt((int)this.posX+xn, (int)this.posY+1+yn, (int)this.posZ+zn);
        if (this.worldObj.getBlockId(this.dockBlock.x, this.dockBlock.y, this.dockBlock.z) == 0)
            {
            

            this.isDocked = true;
            this.isWalking = false;
            this.isReversing = false;
            this.motionX = this.motionY = this.motionZ = 0;
            this.closeChest();
            
            //while docked//
            this.rotationPitch = 0.0F;
            //this.riddenByEntity.rotationPitch = 0.0F;
            
            this.mechTilt = 0;
    
            if (this.rotationYawHead < 0)
            {
                this.rotationYawHead = this.rotationYawHead + 360;
            }
            
            if (this.rotationYawHead >= 0 && this.rotationYawHead < 45)
            {
                if (this.riddenByEntity != null) this.riddenByEntity.rotationYaw = 0;
                this.rotationYaw = this.prevRotationYaw = 0;
                this.renderYawOffset = 0;
                this.dockSide = 0;
            }
            else if (this.rotationYawHead >= 45 && this.rotationYawHead < 90)
            {
                if (this.riddenByEntity != null) this.riddenByEntity.rotationYaw = 90;
                this.rotationYaw = this.prevRotationYaw = 90;
                this.renderYawOffset = 90;
                this.dockSide = 1;
            }
            else if (this.rotationYawHead >= 90 && this.rotationYawHead < 135)
            {
                if (this.riddenByEntity != null) this.riddenByEntity.rotationYaw = 90;
                this.rotationYaw = this.prevRotationYaw = 90;
                this.renderYawOffset = 90;
                this.dockSide = 1;
            }
            else if (this.rotationYawHead >= 135 && this.rotationYawHead < 180)
            {
                if (this.riddenByEntity != null) this.riddenByEntity.rotationYaw = 180;
                this.rotationYaw = this.prevRotationYaw = 180;
                this.renderYawOffset = 180;
                this.dockSide = 2;
            }
            else if (this.rotationYawHead >= 180 && this.rotationYawHead < 225)
            {
                if (this.riddenByEntity != null) this.riddenByEntity.rotationYaw = 180;
                this.rotationYaw = this.prevRotationYaw = 180;
                this.renderYawOffset = 180;
                this.dockSide = 2;
            }
            else if (this.rotationYawHead >= 225 && this.rotationYawHead < 270)
            {
                if (this.riddenByEntity != null) this.riddenByEntity.rotationYaw = 270;
                this.rotationYaw = this.prevRotationYaw = 270;
                this.renderYawOffset = 270;
                this.dockSide = 3;
            }
            else if (this.rotationYawHead >= 270 && this.rotationYawHead < 315)
            {
                if (this.riddenByEntity != null) this.riddenByEntity.rotationYaw = 270;
                this.rotationYaw = this.prevRotationYaw = 270;
                this.renderYawOffset = 270;
                this.dockSide = 3;
            }
            else if (this.rotationYawHead > 315)
            {
                if (this.riddenByEntity != null) this.riddenByEntity.rotationYaw = 0;
                this.rotationYaw = this.prevRotationYaw = 0;
                this.renderYawOffset = 0;
                this.dockSide = 0;
            }
            
            if (!this.chestViewers.isEmpty())
            {
                List<EntityPlayer> cvcopy = new ArrayList<EntityPlayer>(chestViewers);
                
                for (int i=cvcopy.size()-1; i> -1; i--) 
                {
                    EntityPlayer entityplayer = cvcopy.get(i);
                    Vec3 playerpos = Vec3.createVectorHelper(entityplayer.posX, entityplayer.posY, entityplayer.posZ);
                    Vec3 mtpos = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
                    double distance = playerpos.distanceTo(mtpos);
                    
                    if (distance > 4 || this.isWalking|this.isReversing == true)
                    {
                        entityplayer.closeScreen();
                        cvcopy.remove(entityplayer);
                    }
                }
                
                chestViewers.clear();
                chestViewers.addAll(cvcopy);
            }
            
            this.dockSettleTimer = 12;
            this.dockAlign();
        }
    }
    
    public void dockAlign()
    {
        switch (this.dockSide)
        {
            case 2:

                this.setPosition(Math.ceil(this.posX)-0.5, Math.round(this.posY), Math.ceil(this.posZ)-0.625);
                break;
            case 3:

                this.setPosition(Math.ceil(this.posX)-0.375, Math.ceil(this.posY), Math.ceil(this.posZ)-0.500);
                break;
            case 0:

                this.setPosition(Math.ceil(this.posX)-0.5, Math.ceil(this.posY), Math.ceil(this.posZ)-0.375);
                break;
            case 1:

                this.setPosition(Math.ceil(this.posX)-0.625, Math.ceil(this.posY), Math.ceil(this.posZ)-0.500);
                break;
        }
    }

    @Override
    public boolean canBeCollidedWith()
    {
        
        return !this.isDead;
    }
    
    protected void collideWithEntity(Entity par1Entity)
    {
        if (!this.isDocked)
        {
            par1Entity.applyEntityCollision(this);
        }
    }
    
    @Override
    public boolean canBePushed()
    {
        if (this.isDocked)
        {
            return false;
        }
        return !this.isDead;
    }
    
    public void createBlock(int side)
    {        

        
        // If negative, these offsets will be added.
        short xn = 0;
        short yn = 0;
        short zn = 0;
        
        if (this.posX < 0)
            xn = -1;
        
        if (this.posY < 0)
            yn = -1;
        
        if (this.posZ < 0)
            zn = -1;

        this.worldObj.playSoundAtEntity(this, "mtm:mechdocked", 0.5F, 1.00F);
        
        switch (side)
        {
            case 2:
                this.worldObj.setBlock((int)this.posX+xn, (int)this.posY+1+yn, (int)this.posZ+zn, MagitekBlocks.blockmt.blockID);
                this.worldObj.setBlockTileEntity((int)this.posX+xn, (int)this.posY+1+yn, (int)this.posZ+zn, new TileEntityMT(this));
                break;
            case 3:
                this.worldObj.setBlock((int)this.posX+xn, (int)this.posY+1+yn, (int)this.posZ+zn, MagitekBlocks.blockmt.blockID);
                this.worldObj.setBlockTileEntity((int)this.posX+xn, (int)this.posY+1+yn, (int)this.posZ+zn, new TileEntityMT(this));
                break;
            case 0:
                this.worldObj.setBlock((int)this.posX+xn, (int)this.posY+1+yn, (int)this.posZ+zn, MagitekBlocks.blockmt.blockID);
                this.worldObj.setBlockTileEntity((int)this.posX+xn, (int)this.posY+1+yn, (int)this.posZ+zn, new TileEntityMT(this));
                break;
            case 1:
                this.worldObj.setBlock((int)this.posX+xn, (int)this.posY+1+yn, (int)this.posZ+zn, MagitekBlocks.blockmt.blockID);
                this.worldObj.setBlockTileEntity((int)this.posX+xn, (int)this.posY+1+yn, (int)this.posZ+zn, new TileEntityMT(this));
                break;
        }
        this.dockBlock = new MutableTripletInt((int)this.posX+xn, (int)this.posY+1+yn, (int)this.posZ+zn);
        


        
    }


}

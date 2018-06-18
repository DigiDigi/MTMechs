package digidigi.mtm.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.MinecraftForgeClient;
import digidigi.mtm.MagitekMechs;
import digidigi.mtm.client.RenderFireBeam;
import digidigi.mtm.client.RenderFireBeams;
import digidigi.mtm.common.Debug;
import digidigi.mtm.common.EntityMT;
import digidigi.mtm.common.EntityPA;

public class PacketHandler implements IPacketHandler
{
    
    public MinecraftServer server;
    
    @Override
    public void onPacketData(INetworkManager manager,
                             Packet250CustomPayload packet, 
                             Player player)
    {
        if (packet.channel.equals("mech")) 
        {
            handleMech(packet, player);
            
        }
        
        if (packet.channel.equals("mechanim")) 
        {
            handleMechAnim(packet, player);
            
        }
        
        if (packet.channel.equals("mechfire")) 
        {
            handleMechFire(packet, player);
        }
        
        if (packet.channel.equals("mechfuel")) 
        {
            handleFuelUpdate(packet, player);
        }
        
        if (packet.channel.equals("mechinvreq")) 
        {
            handleInvRequest(packet, player);
        }
        
        if (packet.channel.equals("mechinvupdate")) 
        {
            handleInvUpdate(packet, player);
        }
        
        if (packet.channel.equals("mechmagi")) 
        {
            handleMagiUpdate(packet, player);
        }
        
        if (packet.channel.equals("mechaux")) 
        {
            handleAuxUpdate(packet, player);
        }
        
        if (packet.channel.equals("mechdock")) 
        {
            handleDock(packet, player);
        }
        
        if (packet.channel.equals("removeviewer")) 
        {
            handleRemoveViewer(packet, player);
        }
        
        if (packet.channel.equals("mechsync")) 
        {
            handleSync(packet, player);
        }
        
        /*
        if (packet.channel.equals("renderFROMSERVER")) 
        {
            renderFROMSERVER(packet, player);
        }
        */
        
    }
    

    /*
    @SideOnly(Side.CLIENT)
    private void renderFROMSERVER(Packet250CustomPayload packet,
            Player player)
    {
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));

        float param;
        float param2;
        int keydata;
        
        try 
        {
            param = inputStream.readFloat();
            param2 = inputStream.readFloat();
            keydata = inputStream.readInt();
        } 
        catch (IOException e) 
        {
                e.printStackTrace();
                return;
        }
        
        if (keydata == 0) //beamPitchModifier
        {
            RenderFireBeam.beamPitchModifier = param;
        }
        
        else if (keydata == 1) //beamYModifier
        {
            RenderFireBeam.beamYModifier = param;
        }
        
        else if (keydata == 2) //beamVertMultiplier
        {
            RenderFireBeam.beamVertMultiplier = param;
        }
        
        else if (keydata == 3) //BlendStyle
        {
            RenderFireBeams.blendstyle1 = (int) param;
            RenderFireBeams.blendstyle2 = (int) param2;
        }
        
    }
    */

    // Messages from the client. 
    private void handleMech(Packet250CustomPayload packet, Player player) 
    {
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
        
        int keydata;
        
        EntityPlayer entityplayer;
        entityplayer = (EntityPlayer) player;
        Entity mechentity = entityplayer.ridingEntity;
        EntityMT entitymt = (EntityMT) entityplayer.ridingEntity;  
        
       
        
        try 
        {
            keydata = inputStream.readInt();
        } 
        catch (IOException e) 
        {
                e.printStackTrace();
                return;
        }
        
        
        
        if (entitymt != null)
        {
            //System.out.println("(Server) Received keydata: " + keydata + " packet");
            if (keydata == 1) // sent input to walk forward
            {
    
                if (!entitymt.isDocked)
                {
                    
                    //System.out.println("Set Walking after receiving a packet from client");
                    entitymt.isWalking = true;
                    entitymt.doForward();
                    
                    // Send animation packets back out to clients.
                    int omechid = entitymt.entityId; 
                    int omechdimension = entitymt.dimension;
                    int okeydata = 1;
                    ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                    DataOutputStream outputStream = new DataOutputStream(bos);
                    try 
                    {
                        outputStream.writeInt(omechid);
                        outputStream.writeInt(omechdimension);
                        outputStream.writeInt(okeydata);
                    } 
                    catch (Exception ex) 
                    {
                        ex.printStackTrace();
                    }
                    
                    Packet250CustomPayload animpacket = new Packet250CustomPayload();
                    animpacket.channel = "mechanim";
                    animpacket.data = bos.toByteArray();
                    animpacket.length = bos.size();
                    
                    PacketDispatcher.sendPacketToAllAround(entitymt.posX, entitymt.posY, entitymt.posZ, 80, entitymt.dimension, animpacket);
                }
            
            }
            else if (keydata == 2) // sent input to stop movement
            {
    
                //System.out.println("(Server) Received mech(Stop) packet from " + mechid);
                //System.out.println("Stopped Walking after receiving a packet from client");
                entitymt.isWalking = false;
                entitymt.isReversing = false;
                entitymt.doStop();
                
                // Send animation packets back out to clients.
                int omechid = entitymt.entityId; 
                int omechdimension = entitymt.dimension;
                int okeydata = 0;
                ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                DataOutputStream outputStream = new DataOutputStream(bos);
                try 
                {
                    outputStream.writeInt(omechid);
                    outputStream.writeInt(omechdimension);
                    outputStream.writeInt(okeydata);
                } 
                catch (Exception ex) 
                {
                    ex.printStackTrace();
                }
                
                Packet250CustomPayload animpacket = new Packet250CustomPayload();
                animpacket.channel = "mechanim";
                animpacket.data = bos.toByteArray();
                animpacket.length = bos.size();
                
                PacketDispatcher.sendPacketToAllAround(entitymt.posX, entitymt.posY, entitymt.posZ, 80, entitymt.dimension, animpacket);
                //    
                
            }
            else if (keydata == 3) // sent input to jump
            {
                
                // Already synced through datawatcher.. maybe.
                if (!entitymt.isDocked)
                {
                    entitymt.doJump();
                }
            }
            
            else if (keydata == 4) // sent input to fire
            {
    
                if (!entitymt.isDocked)
                {
                    //System.out.println("(Server) Received mech(Fire) packet from " + mechid);
                    
                    //Provided the mech can fire a beam, send this to the clients.
                    int omechid = entitymt.entityId; 
                    int omechdimension = entitymt.dimension;
                    int okeydata = 0; //Type of attack.
                    ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                    DataOutputStream outputStream = new DataOutputStream(bos);
                    try 
                    {
                        outputStream.writeInt(omechid);
                        outputStream.writeInt(omechdimension);
                        outputStream.writeInt(okeydata);
                    } 
                    catch (Exception ex) 
                    {
                        ex.printStackTrace();
                    }
                    
                    Packet250CustomPayload firepacket = new Packet250CustomPayload();
                    firepacket.channel = "mechfire";
                    firepacket.data = bos.toByteArray();
                    firepacket.length = bos.size();
                    
                    PacketDispatcher.sendPacketToAllAround(entitymt.posX, entitymt.posY, entitymt.posZ, 80, entitymt.dimension, firepacket);
                
                    entitymt.fireBeam();
                }
                
            }
            
            else if (keydata == 5) // debug dismount
            {
    
                //System.out.println("(Server) Received mech(Dismount) packet from " + mechid);
                
                //entitymt.riddenByEntity.unmountEntity(entitymt); 1.5.2
                entitymt.dismountEntity(entitymt.riddenByEntity); //1.6.2?
                
                entitymt.riddenByEntity = null;
            
            }
            
            else if (keydata == 6)
            {
 
            }
            
            else if (keydata == 7) // mech dock request from client.
            {
                //System.out.println("Got dock (request) from client. ");
                int omechid = entitymt.entityId;
                int odockmode = 0;
                
                ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                DataOutputStream outputStream = new DataOutputStream(bos);
                
                try 
                {
                    if (entitymt.isDocked == true)
                    {
                        entitymt.unDock();
                        odockmode = 0;
                        outputStream.writeInt(omechid);
                        outputStream.writeInt(odockmode); // undock.
                    }
                    else
                    {
                        entitymt.dock();
                        odockmode = 1;
                        outputStream.writeInt(omechid);
                        outputStream.writeInt(odockmode); // dock.
                    }    
                } 
                catch (Exception ex) 
                {
                    ex.printStackTrace();
                }
                
                Packet250CustomPayload dockpacket = new Packet250CustomPayload();
                dockpacket.channel = "mechdock";
                dockpacket.data = bos.toByteArray();
                dockpacket.length = bos.size();
                
                PacketDispatcher.sendPacketToAllAround(entitymt.posX, entitymt.posY, entitymt.posZ, 80, entitymt.dimension, dockpacket);
                //System.out.println(entitymt.isDocked);

            
            }
            else if (keydata == 8) // sent input to reverse
            {
    
                if (!entitymt.isDocked)
                {
                    //System.out.println("(Server) Received mech(Reverse) packet from " + mechid);
            
                    entitymt.isWalking = false;
                    entitymt.isReversing = true;
                    entitymt.doBackward();
                    
                    // Send animation packets back out to clients.
                    int omechid = entitymt.entityId; 
                    int omechdimension = entitymt.dimension;
                    int okeydata = 2;
                    ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                    DataOutputStream outputStream = new DataOutputStream(bos);
                    try 
                    {
                        outputStream.writeInt(omechid);
                        outputStream.writeInt(omechdimension);
                        outputStream.writeInt(okeydata);
                    } 
                    catch (Exception ex) 
                    {
                        ex.printStackTrace();
                    }
                    
                    Packet250CustomPayload animpacket = new Packet250CustomPayload();
                    animpacket.channel = "mechanim";
                    animpacket.data = bos.toByteArray();
                    animpacket.length = bos.size();
                    
                    PacketDispatcher.sendPacketToAllAround(entitymt.posX, entitymt.posY, entitymt.posZ, 80, entitymt.dimension, animpacket);
                }
            
            }
        }
        
    }

    //Message from server.
    @SideOnly(Side.CLIENT)
    private void handleMechAnim(Packet250CustomPayload packet, Player player) 
    {
        
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));


        
        int mechid;
        int mechdimension;
        int keydata;
        EntityMT entitymt;
        

        
        try 
        {
            mechid = inputStream.readInt();
            mechdimension = inputStream.readInt();
            keydata = inputStream.readInt();
        } 
        catch (IOException e) 
        {
                e.printStackTrace();
                return;
        }
        
        EntityPlayer cplayer;
        cplayer =  (EntityPlayer)player;
        //System.out.println("(Client) Received mechanim"+ "(" + keydata + ")" +" packet from " + mechid);
        entitymt = (EntityMT)cplayer.worldObj.getEntityByID(mechid);
        
        if (keydata == 0) // stop
        {
            //System.out.println("Client received Stop Walking in anim packet.");
            entitymt.walkAnimationStage = 0;
            entitymt.isWalking = false;
            //entitymt.isReversing = false;
        }
        else if (keydata == 1) // forward
        {
            entitymt.walkAnimationStage = 1;
            entitymt.isWalking = true;
            //entitymt.isReversing = false;
        }
        else if (keydata == 2) // backward
        {
            entitymt.walkAnimationStage = 1;
            entitymt.isReversing = true;
            entitymt.isWalking = false;
        }
        
    }
    
    //Message from server.
    @SideOnly(Side.CLIENT)
    private void handleMechFire(Packet250CustomPayload packet, Player player) 
    {
        
        
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));

        int mechid;
        int mechdimension;
        int keydata;
        EntityMT entitymt;
        
        try 
        {
            mechid = inputStream.readInt();
            mechdimension = inputStream.readInt();
            keydata = inputStream.readInt();
        } 
        catch (IOException e) 
        {
                e.printStackTrace();
                return;
        }
        
        EntityPlayer cplayer;
        cplayer =  (EntityPlayer)player;
        //System.out.println("(Client) Received mechfire" + "(" + keydata + ")" +" packet from " + mechid);
        entitymt = (EntityMT)cplayer.worldObj.getEntityByID(mechid);

        
        
        if (keydata == 0) // Heat beam.
        {
            entitymt.fireBeam();
        }
        
    }
    
    //Message from server.
    @SideOnly(Side.CLIENT)
    private void handleFuelUpdate(Packet250CustomPayload packet, Player player) 
    {
        
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));

        int mechid;
        int keydata;
        int keydata2;
        EntityMT entitymt;
        try 
        {
            mechid = inputStream.readInt();
            keydata = inputStream.readInt();
            keydata2 = inputStream.readInt();
        } 
        catch (IOException e) 
        {
                e.printStackTrace();
                return;
        }
        
        EntityPlayer cplayer;
        cplayer =  (EntityPlayer)player;
        entitymt = (EntityMT)cplayer.worldObj.getEntityByID(mechid);
        

        //System.out.println("Got fuel packet from server. " + entitymt);
        
        if (entitymt != null)
        {
            entitymt.BurnTime = keydata;
            entitymt.currentItemBurnTime = keydata2;
            if (entitymt.BurnTime > 0)
            {
                entitymt.BurnActive = true;
            }
        }
        
        
    }
    
    //Message from server.
    @SideOnly(Side.CLIENT)
    private void handleInvUpdate(Packet250CustomPayload packet, Player player) 
    {
        //Recieving all the inv and pertinent mech gui data from server.
        
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));

        int mechid;
        int keydata;
        int keydata2;
        boolean keydata3;
        boolean keydata4;
        EntityMT entitymt;
        try 
        {
            mechid = inputStream.readInt();
            keydata = inputStream.readInt();
            keydata2 = inputStream.readInt();
            keydata3 = inputStream.readBoolean();
            keydata4 = inputStream.readBoolean();
        } 
        catch (IOException e) 
        {
                e.printStackTrace();
                return;
        }
        
        EntityPlayer cplayer;
        cplayer =  (EntityPlayer)player;
        entitymt = (EntityMT)cplayer.worldObj.getEntityByID(mechid);
        
        if (entitymt != null)
        {
            entitymt.BurnTime = keydata;
            entitymt.currentItemBurnTime = keydata2;
            entitymt.magislot = keydata3;
            entitymt.auxslot = keydata4;
            if (entitymt.BurnTime > 0)
            {
                entitymt.BurnActive = true;
            }
        }
        
        
    }
    
    //Message from client.
    private void handleInvRequest(Packet250CustomPayload packet, Player player) 
    {
        //System.out.println("Got inventory update (request) from client. ");
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));

        int mechid;
        
        try 
        {
            mechid = inputStream.readInt();
        } 
        catch (IOException e) 
        {
                e.printStackTrace();
                return;
        }
        
        EntityMT entitymt;
        
        WorldServer server = MinecraftServer.getServer().worldServers[0];
        entitymt = (EntityMT) server.getEntityByID(mechid);
        
        if (entitymt != null)
        {
            int omechid = entitymt.entityId; 
            int okeydata = entitymt.BurnTime;
            int okeydata2 = entitymt.currentItemBurnTime;
            boolean okeydata3 = entitymt.isMagislotted();
            boolean okeydata4 = entitymt.isAuxslotted();
            
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
            Packet250CustomPayload invpacket = new Packet250CustomPayload();
            invpacket.channel = "mechinvupdate";
            invpacket.data = bos.toByteArray();
            invpacket.length = bos.size();
            PacketDispatcher.sendPacketToPlayer(invpacket, player);
        }
        
    }
    
    //Message from server.
    @SideOnly(Side.CLIENT)
    private void handleMagiUpdate(Packet250CustomPayload packet, Player player) 
    {
        
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));

        int mechid;
        boolean keydata;
        EntityMT entitymt;
        try 
        {
            mechid = inputStream.readInt();
            keydata = inputStream.readBoolean();
        } 
        catch (IOException e) 
        {
                e.printStackTrace();
                return;
        }
        
        EntityPlayer cplayer;
        cplayer =  (EntityPlayer)player;
        entitymt = (EntityMT)cplayer.worldObj.getEntityByID(mechid);
        
        if (entitymt != null)
        {
            entitymt.magislot = keydata;
        }
        
    }
    
    //Message from server.
    @SideOnly(Side.CLIENT)
    private void handleAuxUpdate(Packet250CustomPayload packet, Player player) 
    {
        
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));

        int mechid;
        boolean keydata;
        EntityMT entitymt;
        try 
        {
            mechid = inputStream.readInt();
            keydata = inputStream.readBoolean();
        } 
        catch (IOException e) 
        {
                e.printStackTrace();
                return;
        }
        
        EntityPlayer cplayer;
        cplayer =  (EntityPlayer)player;
        entitymt = (EntityMT)cplayer.worldObj.getEntityByID(mechid);
        
        if (entitymt != null)
        {
            entitymt.auxslot = keydata;
        }
        
    }
    
    //Message from server.
    @SideOnly(Side.CLIENT)
    private void handleDock(Packet250CustomPayload packet, Player player) 
    {
        
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));

        int mechid;
        int dockmode; //0 undock, 1 dock.
        EntityMT entitymt;
        
        try 
        {
            mechid = inputStream.readInt();
            dockmode = inputStream.readInt();
        } 
        
        catch (IOException e) 
        {
                e.printStackTrace();
                return;
        }
        
        //System.out.println("got dock packet from server" + ":" + dockmode);
        
        EntityPlayer cplayer;
        cplayer =  (EntityPlayer)player;
        entitymt = (EntityMT)cplayer.worldObj.getEntityByID(mechid);
        
        if (dockmode == 0)
        {
            entitymt.unDock();
        }
        else
        {
            entitymt.dock();
        }
        
    }
    
    //Message from server.
    @SideOnly(Side.CLIENT)
    private void handleSync(Packet250CustomPayload packet, Player player) 
    {
        
        
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));

        int mechid;
        float mechpitch;
        EntityMT entitymt;
        
        try 
        {
            mechid = inputStream.readInt();
            mechpitch = inputStream.readFloat();
        } 
        
        catch (IOException e) 
        {
                e.printStackTrace();
                return;
        }

        
        EntityPlayer cplayer;
        cplayer =  (EntityPlayer)player;
        entitymt = (EntityMT)cplayer.worldObj.getEntityByID(mechid);

        if (entitymt != null)
        {
        
        Minecraft mc = Minecraft.getMinecraft();
        
        
        
        EntityPlayer rider;
        rider = (EntityPlayer)entitymt.riddenByEntity;
        
            if (rider != null && rider != mc.thePlayer)
            {

                entitymt.rotationPitch = mechpitch;
                entitymt.prevRotationPitch = mechpitch;
                rider.rotationPitch = mechpitch * 200;
                rider.prevRotationPitch = mechpitch * 200;
            }
        }
        

        //System.out.println("Received yaw/pitch packet from server.");
        
    }
    
    //Message from client.
    private void handleRemoveViewer(Packet250CustomPayload packet, Player player) 
    {
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
    
        int mechid;
        int dimension;
        
        try 
        {
            mechid = inputStream.readInt();
            dimension = inputStream.readInt();
        } 
        catch (IOException e) 
        {
                e.printStackTrace();
                return;
        }
        
        EntityMT entitymt;
        
        WorldServer server = MinecraftServer.getServer().worldServerForDimension(dimension);
        entitymt = (EntityMT) server.getEntityByID(mechid);
        
        //System.out.println("received");
        if (entitymt.chestViewers.contains(player))
        {

            //System.out.println("removed");
            entitymt.chestViewers.remove(player);
        }
    }
    
}

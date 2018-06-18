package digidigi.mtm.common;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class EntityTA extends EntityMT
{
    
    public boolean hasSmokeStacks = false;
    
    public EntityTA(World world)
    {
        super(world);
    }
    
    public EntityTA(World world, Entity entity)
    {
        this(world);
        setRotation(entity.rotationYaw, 0);

        this.legSpeed = 1.0F;
        this.strideLength = 0.50F;
        this.currentSpeed = 1.3F;
        
    }
    
    public double getMountedYOffset() 
    {
        return 1.1D;
    }
    
    protected void dropFewItems(boolean par1, int par2)
    {
        this.dropItem(MagitekItems.mecharm.itemID, 1);
        this.dropItem(MagitekItems.mechleg.itemID, 2);
        this.dropItem(MagitekItems.mechtorso.itemID, 1);
        this.dropItem(Block.fenceIron.blockID, 2);
        
        int itemindex;
        for (itemindex = 0; itemindex < this.chestContents.length; ++itemindex)
        {
            if (this.chestContents[itemindex] != null)
            {
                this.entityDropItem(new ItemStack(this.chestContents[itemindex].itemID, this.chestContents[itemindex].stackSize,  this.chestContents[itemindex].getItemDamage()), 0);
            }
        }
        
    }
    
    @Override
    public void dockAlign()
    {
        switch (this.dockSide)
        {
            case 2:

                this.setPosition(Math.ceil(this.posX)-0.5, Math.round(this.posY), Math.ceil(this.posZ)-0.8125);
                break;
            case 3:

                this.setPosition(Math.ceil(this.posX)-0.1875, Math.ceil(this.posY), Math.ceil(this.posZ)-0.500);
                break;
            case 0:

                this.setPosition(Math.ceil(this.posX)-0.5, Math.ceil(this.posY), Math.ceil(this.posZ)-0.1875);
                break;
            case 1:

                this.setPosition(Math.ceil(this.posX)-0.8125, Math.ceil(this.posY), Math.ceil(this.posZ)-0.500);
                break;
        }
    }
    
    @Override
    public void produceSmoke(double sidedisplacex, double fwddisplacex, double sidedisplacez, double fwddisplacez)
    {
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

                this.moveEntity(this.getLookVec().xCoord * 0.02 , this.motionY, this.getLookVec().zCoord * 0.02);
                
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
                this.moveEntity(this.getLookVec().xCoord * 0.02 , this.motionY, this.getLookVec().zCoord * 0.02);
                
                this.walkAnimationStage = 1;  
            }
        }
    }

    
    public void playMechStep()
    {
        if (!this.worldObj.isRemote && this.onGround && this.legDown && this.getHealth() > 0)
        {
            this.worldObj.playSoundAtEntity(this, 
                                            "mtm:mechstep", 
                                            0.4F, 
                                            1.00F +  0F);
        }

    }
    
}
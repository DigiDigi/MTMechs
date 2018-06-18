package digidigi.mtm.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class EntityTest extends EntityLiving
{

    public EntityTest(World world)
    {
        super(world);
    }

    public EntityTest(World world, Entity entity)
    {
        this(world);
    }
    
    public float ScaleAmount()
    {
        return 1.0F;
    }
    
    @Override
    public void onEntityUpdate()
    {
        
    }
    
    @Override
    public void onUpdate()
    {
        //this.setDead();
        this.motionY = +0.125F;
        
        if (this.ticksExisted % 20 == 0 )
        {
            //this.moveEntity(0, this.motionY, 0);
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
        
        if (this.ticksExisted % 10 == 0)
        {
            this.worldObj.spawnEntityInWorld(new EntityGlimmer(this.worldObj, this.posX + fwddisplacex * 0.8, this.posY + 1.8, this.posZ + fwddisplacez * 0.8, 0, 0, 0));
            this.worldObj.spawnEntityInWorld(new EntityGlimmer(this.worldObj, this.posX + fwddisplacex * 0.8, this.posY + 1.8, this.posZ + fwddisplacez * 0.8, 0, 0, 0));
            this.worldObj.spawnEntityInWorld(new EntityGlimmer(this.worldObj, this.posX + fwddisplacex * 0.8, this.posY + 1.8, this.posZ + fwddisplacez * 0.8, 0, 0, 0));
        }
        
    }


}

package digidigi.mtm.common;

import java.util.Random;

import digidigi.mtm.MagitekMechs;
import digidigi.mtm.config.Sounds;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockMT extends Block implements ITileEntityProvider
{

    public static int blockID;
    //Invisible block for mech docking with pipes and such.
    
    public BlockMT(int id)
    {
        super(id, Material.air);
        this.isBlockContainer = true;
        this.disableStats();
        this.setBlockBounds(0F, 0F, 0F, 0F, 0F, 0F);
    }
    
    @Override
    public int getRenderBlockPass()
    {
        return 1;
    }
    
    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
    }
    
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborblockid)
    { 
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
                    EntityPlayer player, int metadata, float what, float these, float are) 
    {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (tileEntity == null || player.isSneaking()) 
        {
            return false;
        }
        player.openGui(MagitekMechs.instance, 0, world, x, y, z);
        return true;
    }      

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        return null;//new TileEntityMT(new ItemStack[11]);
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) 
    {
            dropItems(world, x, y, z);
            world.removeBlockTileEntity(x, y, z);
            super.breakBlock(world, x, y, z, par5, par6);
    }
    
    private void dropItems(World world, int x, int y, int z)
    {
        Random rand = new Random();

        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (!(tileEntity instanceof IInventory)) 
        {
                return;
        }
        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++) 
        {
            ItemStack item = inventory.getStackInSlot(i);

            if (item != null && item.stackSize > 0) 
            {
                float rx = rand.nextFloat() * 0.8F + 0.1F;
                float ry = rand.nextFloat() * 0.8F + 0.1F;
                float rz = rand.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem(world,
                                x + rx, y + ry, z + rz,
                                new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));

                if (item.hasTagCompound()) 
                {
                        entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                }

                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                item.stackSize = 0;
            }
        }
    }
    
    /*
    @Override
    public boolean isBlockNormalCube(World world, int x, int y, int z)
    {
        return false;
    }
    
    @Override
    public boolean isAirBlock(World world, int x, int y, int z)
    {
        return true;
    }
    */
    
    @Override
    public boolean isCollidable()
    {
    return true;
    }
    
    @Override
    public boolean isOpaqueCube()
    {
    return false;
    }
    
    public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int x, int y, int z)
    {
        //this.canCollideCheck(par1, par2)
        return false;
    }
    
    public boolean canCollideCheck(int par1, boolean par2)
    {
        return false;
    }
    
    @Override
    public void registerIcons(IconRegister reg)
    {
        this.blockIcon = reg.registerIcon("mtm:mtblock");
    }
    
    public boolean onBlockEventReceived(World world, int x, int y, int z, int par5, int par6)
    {
        super.onBlockEventReceived(world, x, y, z, par5, par6);
        TileEntity tileentity = world.getBlockTileEntity(x, y, z);
        return tileentity != null ? tileentity.receiveClientEvent(par5, par6) : false;
    }

}

package digidigi.mtm.item;

import java.util.List;

import digidigi.mtm.MagitekMechs;
import digidigi.mtm.common.EntityMT;
import digidigi.mtm.common.MagitekItems;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemMech extends Item
{
    public ItemStack magicitestack;
    
    public ItemMech(int id)
    {
        super(id);
        this.setCreativeTab(MagitekMechs.tabMTMechs);
        
    }

    @Override
    public void registerIcons(IconRegister reg)
    {
        this.itemIcon = reg.registerIcon("mtm:mechitem");
    }
    
    public Item setMaxStackSize(int par1)
    {
        this.maxStackSize = par1;
        return this;
    }
    
    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean unknown)
    {
        if (itemstack.stackTagCompound != null) 
        {
            //itemstack.stackTagCompound.getString("spell");
        }
    }
    

    @Override
    public void onCreated(ItemStack itemstack, World world, EntityPlayer player) 
    {
    }
    
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer entityplayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (!par3World.isRemote)
        {
            
            int i1 = par3World.getBlockId(par4, par5, par6);
            par4 += Facing.offsetsXForSide[par7];
            par5 += Facing.offsetsYForSide[par7];
            par6 += Facing.offsetsZForSide[par7];
            double d0 = 0.0D;

            if (par7 == 1 && Block.blocksList[i1] != null && Block.blocksList[i1].getRenderType() == 11)
            {
                d0 = 0.5D;
            }
            
            
            EntityMT entitymt = new EntityMT(par3World);
            EntityLiving entity = (EntityMT)entitymt;
            entitymt.setLocationAndAngles((double)(par4+ 0.5D), (double)(par5 + d0), (double)(par6+ 0.5D), MathHelper.wrapAngleTo180_float(par3World.rand.nextFloat() * 360.0F), 0.0F);
            entity.rotationYawHead = entity.rotationYaw;
            entity.renderYawOffset = entity.rotationYaw;

            entitymt.magicitestack = this.magicitestack;
            
            par3World.spawnEntityInWorld(entitymt);
            entitymt.initCreature(entityplayer);

        }

        --par1ItemStack.stackSize;
        return true;
    }
    
}

package digidigi.mtm.item;

import java.util.List;

import digidigi.mtm.MagitekMechs;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemMagicite extends Item
{

    public String spell = "Empty";
    
    public ItemMagicite(int id)
    {
        super(id);
        this.setCreativeTab(MagitekMechs.tabMTMechs);
    }

    @Override
    public void registerIcons(IconRegister reg)
    {
        this.itemIcon = reg.registerIcon("mtm:magicite");
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
            this.spell = itemstack.stackTagCompound.getString("spell");
            if (this.spell == "Fire Beam") 
            {
                list.add(EnumChatFormatting.WHITE + "* " + EnumChatFormatting.RED + "Fire Beam" + EnumChatFormatting.WHITE + " *");
            }
            if (this.spell == "Empty") 
            {
                list.add(EnumChatFormatting.WHITE + "* " + EnumChatFormatting.BLUE + "Empty" + EnumChatFormatting.WHITE + " *");
            }
        }
        /*
        else
        {
            if (this.spell == "Fire Beam") 
            {
                list.add(EnumChatFormatting.WHITE + "* " + EnumChatFormatting.RED + "Fire Beam" + EnumChatFormatting.WHITE + " *");
            }
            if (this.spell == "Empty") 
            {
                list.add(EnumChatFormatting.WHITE + "* " + EnumChatFormatting.BLUE + "Empty" + EnumChatFormatting.WHITE + " *");
            }
        }
        */
    }
    
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        /*
        if (!par3EntityPlayer.capabilities.isCreativeMode)
        {
            --par1ItemStack.stackSize;
        }

        
        par2World.playSoundAtEntity(par3EntityPlayer, Sounds.laser, 0.5F, 1F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!par2World.isRemote)
        {
            par2World.spawnEntityInWorld(new EntityLaser(par2World, par3EntityPlayer));
        }
        */

        return par1ItemStack;
    }
    
    @Override
    public void onCreated(ItemStack itemstack, World world, EntityPlayer player) 
    {
        itemstack.stackTagCompound = new NBTTagCompound();
        itemstack.stackTagCompound.setString("spell", "Fire Beam");
    }
    
}

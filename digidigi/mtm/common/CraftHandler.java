package digidigi.mtm.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.ICraftingHandler;
import digidigi.mtm.item.ItemMagicite;
import digidigi.mtm.item.ItemMech;

public class CraftHandler implements ICraftingHandler
{

    @Override
    public void onCrafting(EntityPlayer player, ItemStack item,
            IInventory craftMatrix)
    {
        // TODO Auto-generated method stub
        //System.out.println(item);
        
        if (item.getItem() instanceof ItemMech)
        {
            ItemMech itemmech = (ItemMech)item.getItem();
            for (int index = 0; index < craftMatrix.getSizeInventory(); index ++)
            {
                //System.out.println(craftMatrix.getStackInSlot(index));
                if (craftMatrix.getStackInSlot(index) != null && craftMatrix.getStackInSlot(index).getItem() instanceof ItemMagicite)
                {
                    itemmech.magicitestack = craftMatrix.getStackInSlot(index);
                }
            }
            
        }
    }

    @Override
    public void onSmelting(EntityPlayer player, ItemStack item)
    {
        //System.out.println("smelt");
        // TODO Auto-generated method stub
        
    }
}
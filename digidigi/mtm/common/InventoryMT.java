package digidigi.mtm.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryMT implements IInventory
{
    public EntityMT entitymt;
    
    public InventoryMT(EntityMT entitymt)
    {
        this.entitymt = entitymt;
    }
    
    @Override
    public int getSizeInventory()
    {
        return 12;
    }

    @Override
    public ItemStack getStackInSlot(int i)
    {
        return this.entitymt.chestContents[i];
    }

    @Override
    public ItemStack decrStackSize(int i, int j)
    {
        
        if (this.entitymt.chestContents[i] != null)
        {
            ItemStack itemstack;

            if (this.entitymt.chestContents[i].stackSize <= j)
            {
                itemstack = this.entitymt.chestContents[i];
                this.entitymt.chestContents[i] = null;
                this.onInventoryChanged();
                return itemstack;
            }
            else
            {
                itemstack = this.entitymt.chestContents[i].splitStack(j);

                if (this.entitymt.chestContents[i].stackSize == 0)
                {
                    this.entitymt.chestContents[i] = null;
                }

                this.onInventoryChanged();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
        
    }
    
    @Override
    public ItemStack getStackInSlotOnClosing(int i)
    {
        
        if (this.entitymt.chestContents[i] != null)
        {
            ItemStack itemstack = this.entitymt.chestContents[i];
            this.entitymt.chestContents[i] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
        
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack)
    {
        this.entitymt.chestContents[i] = itemstack;

        
        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
        {
            itemstack.stackSize = this.getInventoryStackLimit();
        }

        this.onInventoryChanged();
        
        
    }
    
    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public void onInventoryChanged()
    {
        
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        return false;
    }
    

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack)
    {
        return true;
    }

    @Override
    public String getInvName()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isInvNameLocalized()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void openChest()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void closeChest()
    {
        // TODO Auto-generated method stub
        
    }

}

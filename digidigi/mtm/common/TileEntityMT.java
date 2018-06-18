package digidigi.mtm.common;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMT extends TileEntity implements IInventory
{
    private EntityMT entitymt;
    private ItemStack[] mtcargo;
    public int numUsingPlayers;
    
    public TileEntityMT(EntityMT entitymt)
    {
        this.entitymt = entitymt;
        mtcargo = Arrays.copyOfRange(entitymt.chestContents, 2, 11);
    }
    
    public void updateEntity() 
    {
        for (int i = 0; i < this.mtcargo.length; ++i)
        {
            this.entitymt.chestContents[i+2] = mtcargo[i];
        }
    }
    
    // Sends this one last time when undocking, in case we missed any new items.
    public void sendLastContents()
    {
        for (int i = 0; i < this.mtcargo.length; ++i)
        {
            this.entitymt.chestContents[i+2] = mtcargo[i];
        }
    }
    
    @Override
    public int getSizeInventory() 
    {
        return mtcargo.length;
    }
    

    @Override
    public ItemStack getStackInSlot(int slot) 
    {
            return mtcargo[slot];
    }
    
    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) 
    {
        mtcargo[slot] = stack;
        if (stack != null && stack.stackSize > getInventoryStackLimit()) 
        {
            stack.stackSize = getInventoryStackLimit();
        }              
    }
    
    @Override
    public ItemStack decrStackSize(int slot, int amt) 
    {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) 
        {
            if (stack.stackSize <= amt) 
            {
                    setInventorySlotContents(slot, null);
            } 
            else 
            {
                stack = stack.splitStack(amt);
                if (stack.stackSize == 0) 
                {
                    setInventorySlotContents(slot, null);
                }
            }
        }
        return stack;
    }
    
    @Override
    public ItemStack getStackInSlotOnClosing(int slot) 
    {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null) 
        {
            setInventorySlotContents(slot, null);
        }
        return stack;
    }
    
    @Override
    public int getInventoryStackLimit() 
    {
        return 64;
    }
    
    @Override
    public boolean isUseableByPlayer(EntityPlayer player) 
    {
        //return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this &&
        //player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
        return false;
    }
    
    public void updateContainingBlockInfo()
    {
        super.updateContainingBlockInfo();
    }
    
    public boolean receiveClientEvent(int par1, int par2)
    {
        if (par1 == 1)
        {
            this.numUsingPlayers = par2;
            return true;
        }
        else
        {
            return super.receiveClientEvent(par1, par2);
        }
    }
    
    public void openChest()
    {
        if (this.numUsingPlayers < 0)
        {
            this.numUsingPlayers = 0;
        }

        ++this.numUsingPlayers;
        this.entitymt.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType().blockID, 1, this.numUsingPlayers);
        this.entitymt.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType().blockID);
        this.entitymt.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord - 1, this.zCoord, this.getBlockType().blockID);
    }

    public void closeChest()
    {
        if (this.getBlockType() != null && this.getBlockType() instanceof BlockChest)
        {
            --this.numUsingPlayers;
            this.entitymt.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType().blockID, 1, this.numUsingPlayers);
            this.entitymt.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.getBlockType().blockID);
            this.entitymt.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord - 1, this.zCoord, this.getBlockType().blockID);
        }
    }
    
    
    public void readFromNBT(NBTTagCompound tagcompound)
    {
        super.readFromNBT(tagcompound);
        
        NBTTagList nbttaglist = tagcompound.getTagList("Items");
        
        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.mtcargo.length)
            {
                this.mtcargo[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        
        
    }

    public void writeToNBT(NBTTagCompound tagcompound)
    {
        super.writeToNBT(tagcompound);
        
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.mtcargo.length; ++i)
        {
            if (this.mtcargo[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.mtcargo[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        tagcompound.setTag("Items", nbttaglist);
        
    }
    
    @Override
    public String getInvName() 
    {
            return "mtm.TileEntityMT";
    }

    @Override
    public boolean isInvNameLocalized()
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack)
    {
        return true;
    }
    

        
    
}

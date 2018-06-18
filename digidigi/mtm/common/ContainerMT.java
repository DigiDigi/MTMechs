package digidigi.mtm.common;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;

public class ContainerMT extends Container
{

    private EntityMT entitymt;
    private IInventory trunk;
    public int lastBurnTime = 0;
    public int numRows = 3;
    
    public ContainerMT(int ID, InventoryPlayer playerinventory, World world, int x, int y, int z)
    {
        Entity entity = world.getEntityByID(ID);
        
        if (entity instanceof EntityPA)
        {
            entitymt = (EntityMT) world.getEntityByID(ID);
            this.trunk = entitymt.inventorymt;
            entitymt.chestContainer = this;
            entitymt.openChest();
            
            
            // Fuel (BatterySlot)
            addSlotToContainer(new Slot(entitymt.inventorymt, 0, 35, 46));
            
            // Magisource slot.
            addSlotToContainer(new Slot(entitymt.inventorymt, 1, 125, 46));
            
            // Auxillary slot.
            addSlotToContainer(new Slot(entitymt.inventorymt, 2, 147, 46));
            
            //Trunk
            for (int i = 0; i < 3; i++) 
            {
                for (int j = 0; j < 3; j++) 
                {
                    addSlotToContainer(new Slot(entitymt.inventorymt, 3 + j + i * 3, 62 + j * 18, 18 + i * 18));
                }
            }
            
            
            //Player
            for (int i = 0; i < 3; i++) 
            {
                for (int j = 0; j < 9; j++) 
                {
                    addSlotToContainer(new Slot(playerinventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
                }
            }
    
            for (int i = 0; i < 9; i++) 
            {
               addSlotToContainer(new Slot(playerinventory, i, 8 + i * 18, 142));
            }
        }
        else if (entity instanceof EntityMT)
        {
            entitymt = (EntityMT) world.getEntityByID(ID);
            this.trunk = entitymt.inventorymt;
            entitymt.chestContainer = this;
            entitymt.openChest();
            
            
            // Fuel
            addSlotToContainer(new Slot(entitymt.inventorymt, 0, 24, 46));
            
            // Magisource slot.
            addSlotToContainer(new Slot(entitymt.inventorymt, 1, 114, 46));
            
            // Auxillary slot.
            addSlotToContainer(new Slot(entitymt.inventorymt, 2, 136, 46));
            
            //Trunk
            for (int i = 0; i < 3; i++) 
            {
                for (int j = 0; j < 3; j++) 
                {
                    addSlotToContainer(new Slot(entitymt.inventorymt, 3 + j + i * 3, 51 + j * 18, 18 + i * 18));
                }
            }
            
            
            //Player
            for (int i = 0; i < 3; i++) 
            {
                for (int j = 0; j < 9; j++) 
                {
                    addSlotToContainer(new Slot(playerinventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
                }
            }
    
            for (int i = 0; i < 9; i++) 
            {
               addSlotToContainer(new Slot(playerinventory, i, 8 + i * 18, 142));
            }
        }
    }
    
    public void addCraftingToCrafters(ICrafting icrafting)
    {
        super.addCraftingToCrafters(icrafting);
        icrafting.sendProgressBarUpdate(this, 0, this.entitymt.BurnTime);        
    }
    
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastBurnTime != this.entitymt.BurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.entitymt.BurnTime);
            }
        }
        this.lastBurnTime = this.entitymt.BurnTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
        if (par1 == 1)
        {
            this.entitymt.BurnTime = par2;
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return true;//this.entitymt.isUseableByPlayer(entityplayer);
    }
    
    //Called when a player shift-clicks on a slot. 
    public ItemStack transferStackInSlot(EntityPlayer player, int i)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(i);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (i < 12)
            {
                if (!this.mergeItemStack(itemstack1, 12, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            
            else if (!this.mergeItemStack(itemstack1, 0, 12, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }
    
    public ItemStack mergeDroppedItem(ItemStack itemstack)
    {
        ItemStack itemstackm = itemstack.copy();
        
        
        if (this.mergeItemStack(itemstackm, 0, 12, false))
            {
            return itemstackm;
            
            }
        return null;
        
    }
    
    /**
     * Callback for when the crafting gui is closed.
     */
    public void onContainerClosed(EntityPlayer player)
    {
        super.onContainerClosed(player);
        this.trunk.closeChest();
        
        if (entitymt.guiOpen == true)
        {
            //Notifies the server to remove the Player in chestviewers.
            int omtid = entitymt.entityId;
            int odimension = entitymt.dimension;
            ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
            DataOutputStream outputStream = new DataOutputStream(bos);
            try 
            {
                outputStream.writeInt(omtid);
                outputStream.writeInt(odimension);
            } 
            catch (Exception ex) 
            {
                ex.printStackTrace();
            }
            Packet250CustomPayload packet = new Packet250CustomPayload();
            packet.channel = "removeviewer";
            packet.data = bos.toByteArray();
            packet.length = bos.size();
            PacketDispatcher.sendPacketToServer(packet);
        }

        
    }
    
    public void closeGui(EntityPlayer player)
    {
        player.closeScreen();
        this.onContainerClosed(player);
    }
    
}

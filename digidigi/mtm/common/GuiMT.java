package digidigi.mtm.common;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import digidigi.mtm.config.Textures;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class GuiMT extends GuiContainer 
{
    private EntityMT entitymt;
    private ContainerMT containermt;

    public GuiMT (int ID, InventoryPlayer playerinventory, World world, int x, int y, int z)
    {
        super(new ContainerMT(ID, playerinventory, world, x, y, z));
        this.entitymt = (EntityMT) world.getEntityByID(ID);
        this.containermt = this.entitymt.chestContainer;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int param1, int param2) 
    {
        //draw text and stuff here
        //the parameters for drawString are: string, x, y, color
    
        fontRenderer.drawString(this.entitymt.getTranslatedEntityName(), 8, 6, 4210752);
        
        //draws "Inventory" or your regional equivalent
        fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {        
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        
        this.mc.renderEngine.bindTexture(Textures.mtgui);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
        
        int i1;
        if (this.entitymt.isFueled())
        {
            i1 = this.entitymt.getBurnTimeScaled(14);
            this.drawTexturedModalRect(x + 24, y + 27, 176, 23, i1, 14);
        }
        
        if (this.entitymt.isMagislotted())
        {
            this.drawTexturedModalRect(x + 110, y + 21, 176, 0, 22, 22);
        }
        
        if (this.entitymt.isAuxslotted())
        {
            this.drawTexturedModalRect(x + 136, y + 28, 176, 39, 14, 14);
        }
    }
    
    @Override
    public void onGuiClosed()
    {
        super.onGuiClosed();
        
        if (entitymt.guiOpen == false)
        {
            entitymt.guiOpen = true;
        }
        else
        {
            entitymt.guiOpen = false;
        }
    }

}
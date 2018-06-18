package digidigi.mtm.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world,
            int x, int y, int z)
    {
        EntityMT entity = (EntityMT) world.getEntityByID(ID);
        if (entity != null) 
        {
            return new ContainerMT(ID, player.inventory, world, x, y, z);
            
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world,
            int x, int y, int z)
    {
        EntityMT entity = (EntityMT) world.getEntityByID(ID);
        if (entity instanceof EntityMT && !(entity instanceof EntityPA)) 
        {
            return new GuiMT(ID, player.inventory, world, x, y, z);
            
        }
        else if (entity instanceof EntityPA)
        {
            return new GuiPA(ID, player.inventory, world, x, y, z);
        }
        
        else if (entity instanceof EntityTA)
        {
            return new GuiMT(ID, player.inventory, world, x, y, z);
        }
        return null;
    }

}

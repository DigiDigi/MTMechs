package digidigi.mtm.item;

import digidigi.mtm.MagitekMechs;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemMechLeg extends Item
{

    public ItemMechLeg(int id)
    {
        super(id);
        this.setCreativeTab(MagitekMechs.tabMTMechs);
    }

    @Override
    public void registerIcons(IconRegister reg)
    {
        this.itemIcon = reg.registerIcon("mtm:mechleg");
    }
    
    public Item setMaxStackSize(int par1)
    {
        this.maxStackSize = par1;
        return this;
    }
    
}

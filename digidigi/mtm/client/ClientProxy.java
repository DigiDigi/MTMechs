package digidigi.mtm.client;

import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import digidigi.mtm.client.model.ModelMT;
import digidigi.mtm.client.model.ModelPA;
import digidigi.mtm.client.model.ModelMoogle;
import digidigi.mtm.client.model.ModelTA;
import digidigi.mtm.client.model.ModelTest;
import digidigi.mtm.client.RenderFireBeams;
import digidigi.mtm.common.CommonProxy;
import digidigi.mtm.common.EntityGlimmer;
import digidigi.mtm.common.EntityMT;
import digidigi.mtm.common.EntityPA;
import digidigi.mtm.common.EntityMoogle;
import digidigi.mtm.common.EntityTA;
import digidigi.mtm.common.EntityTest;
import digidigi.mtm.client.KeyBindingHandler;

public class ClientProxy extends CommonProxy
{
	
    @Override
	public void registerRenders()
	{

        MinecraftForge.EVENT_BUS.register(new RenderFireBeams());
	    RenderingRegistry.registerEntityRenderingHandler(EntityMT.class, new RenderMT(new ModelMT(), 1.0F));
        RenderingRegistry.registerEntityRenderingHandler(EntityPA.class, new RenderPA(new ModelPA(), 1.0F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTA.class, new RenderTA(new ModelTA(), 1.0F));
        RenderingRegistry.registerEntityRenderingHandler(EntityMoogle.class, new RenderMoogle(new ModelMoogle(), 1.0F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTest.class, new RenderTest(new ModelTest(), 1.0F));
        RenderingRegistry.registerEntityRenderingHandler(EntityGlimmer.class, new RenderGlimmer());
	}

	@Override
    public void registerKeyBindingHandler() 
    {
        KeyBindingRegistry.registerKeyBinding(new KeyBindingHandler());
    }
	
    @Override
    public void registerSoundEvents() 
    {
        MinecraftForge.EVENT_BUS.register(new SoundEvent());
    }

}

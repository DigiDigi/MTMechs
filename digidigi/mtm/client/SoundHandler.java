package digidigi.mtm.client;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import digidigi.mtm.config.Sounds;

public class SoundHandler
{

    @SideOnly(Side.CLIENT)
    @ForgeSubscribe
    public void onSoundLoad(SoundLoadEvent event) 
    {
        System.out.println("SoundLoadEvent Called.");
        for (String soundFile : Sounds.soundfiles)
        {
          try
          {
              System.out.println("Adding sound: " + soundFile);
              event.manager.soundPoolSounds.addSound("mtm" + ":" + soundFile);
          
          }
          catch (Exception e)
          {
            System.err.println("Error adding sound.");
          }
        }            
        
    }
}

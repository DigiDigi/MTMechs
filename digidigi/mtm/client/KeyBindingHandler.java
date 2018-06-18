package digidigi.mtm.client;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.EnumSet;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;
import digidigi.mtm.common.Debug;
import digidigi.mtm.common.EntityMT;

public class KeyBindingHandler extends KeyHandler
{
    public static KeyBinding MagiFire = new KeyBinding("Magitek Ability", Keyboard.KEY_C); 
    public static KeyBinding MTBlock = new KeyBinding("Magitek (Dock)", Keyboard.KEY_B); 
    //public static KeyBinding DebugDismount = new KeyBinding("MTMechs Debug Dismount", Keyboard.KEY_K); 
    //public static KeyBinding DebugRCI = new KeyBinding("MTMechs Debug Render Cycle +", Keyboard.KEY_ADD); 
    //public static KeyBinding DebugRCD = new KeyBinding("MTMechs Debug Render Cycle -", Keyboard.KEY_SUBTRACT); 
    public static KeyBinding[] keybindArray = new KeyBinding[]{MagiFire, MTBlock/*, DebugDismount, DebugRCI, DebugRCD*/};
    public static boolean[] repeatings = new boolean[keybindArray.length];

    public KeyBindingHandler() 
    {
    super(keybindArray, repeatings);
    }

    @Override
    public String getLabel()
    {
        return null;
        
    }

    @Override
    public void keyDown(EnumSet<TickType> types, 
                        KeyBinding kb,
                        boolean tickEnd, 
                        boolean isRepeat)
    {
        if (tickEnd && FMLClientHandler.instance().getClient().inGameHasFocus) 
        {
            EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
            if (player != null) 
            {
                if (kb.keyCode == MagiFire.keyCode)
                {
                    if (player.ridingEntity instanceof EntityMT)
                    {
                        EntityMT entitymt = (EntityMT)player.ridingEntity;
                        entitymt.clientFireBeam();
                    }
                }
                if (kb.keyCode == MTBlock.keyCode)
                {
                    if (player.ridingEntity instanceof EntityMT)
                    {
                        EntityMT entitymt = (EntityMT)player.ridingEntity;

                        int okeydata = 7;
                        ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
                        DataOutputStream outputStream = new DataOutputStream(bos);
                        try 
                        {
                            outputStream.writeInt(okeydata);
                        } 
                        catch (Exception ex) 
                        {
                            ex.printStackTrace();
                        }
                        
                        Packet250CustomPayload dockpacket = new Packet250CustomPayload();
                        dockpacket.channel = "mech";
                        dockpacket.data = bos.toByteArray();
                        dockpacket.length = bos.size();
                        
                        PacketDispatcher.sendPacketToServer(dockpacket);
                    }
                }
                /*
                if (kb.keyCode == DebugDismount.keyCode)
                {
                    if (player.ridingEntity instanceof EntityMT)
                    {
                        EntityMT entitymt = (EntityMT)player.ridingEntity;
                        
                        entitymt.riddenByEntity = null;
                        player.unmountEntity(entitymt);
                        entitymt.clientDismount();
                    }
                }
                
                if (kb.keyCode == DebugRCI.keyCode)
                {
                    RenderFireBeams.blendstyle1 += 1;
                    
                    if (RenderFireBeams.blendstyle1 == RenderFireBeams.glTestMap.size()) 
                    {
                        RenderFireBeams.blendstyle1 = 0; RenderFireBeams.blendstyle2 += 1;
                    }
                    
                    if (RenderFireBeams.blendstyle2 == RenderFireBeams.glTestMap.size()) 
                    {
                        RenderFireBeams.blendstyle2 = 0;
                    }

                    Debug.serverMessage("BlendStyle: " + RenderFireBeams.blendstyle1 + " : " + RenderFireBeams.blendstyle2);
                }
                
                if (kb.keyCode == DebugRCD.keyCode)
                {
                    RenderFireBeams.blendstyle1 -= 1;
                    
                    if (RenderFireBeams.blendstyle1 == -1) 
                    {
                        RenderFireBeams.blendstyle1 = 7; RenderFireBeams.blendstyle2 -= 1;
                    }
                    
                    if (RenderFireBeams.blendstyle2 == -1) 
                    {
                        RenderFireBeams.blendstyle2 = 7;
                    }
                    
                    Debug.serverMessage("BlendStyle: " + RenderFireBeams.blendstyle1 + " : " + RenderFireBeams.blendstyle2);
                }
                */

                

            }
            
        }
        
    }

    @Override
    public void keyUp(EnumSet<TickType> types, 
                      KeyBinding kb, 
                      boolean tickEnd)
    {
        
    }

    @Override
    public EnumSet<TickType> ticks() 
    {
        return EnumSet.of(TickType.CLIENT);
        
    }

}

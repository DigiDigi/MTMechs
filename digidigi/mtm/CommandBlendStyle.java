package digidigi.mtm;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.network.PacketDispatcher;

import digidigi.mtm.client.RenderFireBeams;
import digidigi.mtm.common.Debug;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.network.packet.Packet250CustomPayload;

public class CommandBlendStyle extends CommandBase implements ICommand
{
    
    @Override
    public String getCommandName()
    {
        return "blendstyle";
    }
    
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    @Override
    public String getCommandUsage(ICommandSender icommandsender)
    {
        return "/" + this.getCommandName() + " [1-7 : 1-7]";
    }

    @Override
    public List<String> getCommandAliases()
    {
        List<String> aliaslist = new ArrayList<String>();
        aliaslist.add("blendstyle");
        aliaslist.add("Blendstyle");
        aliaslist.add("blendStyle");
        return aliaslist;
    }
    
    @Override
    public void processCommand(ICommandSender icommandsender, String[] astring)
    {
        if (astring.length >= 2)
        {
            try
            {
                float param = Float.parseFloat(astring[0]);
                float param2 = Float.parseFloat(astring[1]);
                if (param >= 0 && param < 8 && param2 >= 0 && param2 < 8)
                {
                    RenderFireBeams.blendstyle1 = (int) param;
                    RenderFireBeams.blendstyle2 = (int) param2;
                }
                
                this.sendToClients(param, param2);
                
            }
            catch(NumberFormatException e)
            {
            
            Debug.serverMessage("Arguments must be float.");
            
            }
        }
        else if (astring.length == 1)
        {
        Debug.serverMessage("Command requires two numbers 0-7.");
        }
        else
        {
            Debug.serverMessage("blendStyle == " + RenderFireBeams.blendstyle1 + ":" + RenderFireBeams.blendstyle2);
        }
    }
    
    public void sendToClients(float param, float param2)
    {
        System.out.println("(Server) Received DebugRender packet.");
        
        float oparam = param;
        float oparam2 = param2;
        int okeydata = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
        DataOutputStream outputStream = new DataOutputStream(bos);
        try 
        {
            outputStream.writeFloat(oparam);
            outputStream.writeFloat(oparam2);
            outputStream.writeInt(okeydata);
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
        
        Packet250CustomPayload debugpacket = new Packet250CustomPayload();
        debugpacket.channel = "renderFROMSERVER";
        debugpacket.data = bos.toByteArray();
        debugpacket.length = bos.size();
        
        PacketDispatcher.sendPacketToAllPlayers(debugpacket);
        Debug.serverMessage("blendStyle = " + (int) param + ":" + (int) param2);
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender icommandsender)
    {
        return true;
    }

    @Override
    public List<?> addTabCompletionOptions(ICommandSender icommandsender,
            String[] astring)
    {
        return null;
    }

}

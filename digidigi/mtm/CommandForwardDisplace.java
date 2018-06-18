package digidigi.mtm;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.network.PacketDispatcher;

import digidigi.mtm.client.RenderFireBeam;
import digidigi.mtm.common.Debug;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.network.packet.Packet250CustomPayload;

public class CommandForwardDisplace extends CommandBase implements ICommand
{
    
    @Override
    public String getCommandName()
    {
        return "fdisplace";
    }
    
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    @Override
    public String getCommandUsage(ICommandSender icommandsender)
    {
        return "/" + this.getCommandName() + " [multiplier]";
    }

    @Override
    public List<String> getCommandAliases()
    {
        List<String> aliaslist = new ArrayList<String>();
        aliaslist.add("Fdisplace");
        aliaslist.add("FDisplace");
        aliaslist.add("fDisplace");
        return aliaslist;
    }
    
    @Override
    public void processCommand(ICommandSender icommandsender, String[] astring)
    {
        if (astring.length >= 1)
        {
            
            try
            {
                float param = Float.parseFloat(astring[0]);
                float param2 = 0; // Not used here.
                RenderFireBeam.beamFwdMultiplier = param;
                
                this.sendToClients(param, param2);
                
            }
            catch(NumberFormatException e)
            {
                
            Debug.serverMessage("Argument must be float.");
            
            }
        }
        else
        {
        Debug.serverMessage("beamFwdMultiplier == " + RenderFireBeam.beamFwdMultiplier);
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
        Debug.serverMessage("beamFwdMultiplier = " + param);
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

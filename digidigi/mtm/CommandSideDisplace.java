package digidigi.mtm;

import java.util.ArrayList;
import java.util.List;

import digidigi.mtm.client.RenderFireBeam;
import digidigi.mtm.common.Debug;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;

public class CommandSideDisplace extends CommandBase implements ICommand
{
    
    @Override
    public String getCommandName()
    {
        return "sdisplace";
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
        aliaslist.add("Sdisplace");
        aliaslist.add("SDisplace");
        aliaslist.add("sDisplace");
        return aliaslist;
    }

    @Override
    public void processCommand(ICommandSender icommandsender, String[] astring)
    {
        if (astring.length >= 1)
        {
            try
            {
                double param1 = Double.parseDouble(astring[0]);
                
                RenderFireBeam.beamSideMultiplier = param1;
                Debug.serverMessage("Side Displace Multiplier: " + RenderFireBeam.beamSideMultiplier);
            }
            catch(NumberFormatException e)
            {
            
            Debug.serverMessage("Arguments must be a number.");
            
            }
        }
        else
        {
        Debug.serverMessage("Command requires a number argument.");
        }
        
        
        
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

package digidigi.mtm.common;

import java.util.Iterator;

import org.lwjgl.opengl.GL11;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumChatFormatting;

public class Debug
{
    static boolean display = true;
    
    public static void serverMessage(String message)
    {
        if (display == true)
        {
            Iterator<?> iterator = MinecraftServer.getServer().getConfigurationManager().playerEntityList.iterator();
            while (iterator.hasNext())
            {
                EntityPlayerMP entityplayermp = (EntityPlayerMP)iterator.next();
                //entityplayermp.sendChatToPlayer("" + EnumChatFormatting.AQUA + "" + "<Debug>" + "" + EnumChatFormatting.WHITE + "" + message); 1.5.2
            }
        }
    }
    
    public static void ShowVertex(double x, double y, double z)
    {
        GL11.glColor3ub((byte)200,(byte)200,(byte)255);
        

        GL11.glTranslated(x, y, z);
        GL11.glBegin(GL11.GL_LINES);
        

        
        GL11.glVertex3d(  0.250,   0.250,  0.250);
        GL11.glVertex3d( -0.250,   0.250,  0.250);
        GL11.glVertex3d(  0.250,  -0.250,  0.250);
        GL11.glVertex3d( -0.250,  -0.250,  0.250);
        GL11.glVertex3d( -0.250,  -0.250, -0.250);
        GL11.glVertex3d(  0.250,  -0.250, -0.250);
        GL11.glVertex3d( -0.250,   0.250, -0.250);
        GL11.glVertex3d(  0.250,   0.250, -0.250);

        GL11.glVertex3d(  0.250 , -0.250 , 0.250  );
        GL11.glVertex3d(  0.250 ,  0.250 , 0.250  );
        GL11.glVertex3d( -0.250 ,  0.250 , 0.250  );
        GL11.glVertex3d( -0.250 , -0.250 , 0.250  );
        
        GL11.glVertex3d( 0.250 , -0.250 , -0.250  );
        GL11.glVertex3d( 0.250 ,  0.250 , -0.250  );
        GL11.glVertex3d( 0.250 ,  0.250 ,  0.250  );
        GL11.glVertex3d( 0.250 , -0.250 ,  0.250  );
        
        GL11.glVertex3d( -0.250 , -0.250 ,  0.250  );
        GL11.glVertex3d( -0.250 ,  0.250 ,  0.250  );
        GL11.glVertex3d( -0.250 ,  0.250 , -0.250  );
        GL11.glVertex3d( -0.250 , -0.250 , -0.250  );
   
        GL11.glVertex3d(  0.250 ,  0.250 ,  0.250  );
        GL11.glVertex3d(  0.250 ,  0.250 , -0.250  );
        GL11.glVertex3d( -0.250 ,  0.250 , -0.250  );
        GL11.glVertex3d( -0.250 ,  0.250 ,  0.250  );
        
        GL11.glVertex3d(  0.250 , -0.250 , -0.250  );
        GL11.glVertex3d(  0.250 , -0.250 ,  0.250  );
        GL11.glVertex3d( -0.250 , -0.250 ,  0.250  );
        GL11.glVertex3d( -0.250 , -0.250 , -0.250  );


        /*
        GL11.glVertex3d(x +  1.01,y +   1.01,z +  1.01);
        GL11.glVertex3d(x + -1.01,y +   1.01,z +  1.01);
        GL11.glVertex3d(x +  1.01,y +  -1.01,z +  1.01);
        GL11.glVertex3d(x + -1.01,y +  -1.01,z +  1.01);
        GL11.glVertex3d(x + -1.01,y +  -1.01,z + -1.01);
        GL11.glVertex3d(x +  1.01,y +  -1.01,z + -1.01);
        GL11.glVertex3d(x + -1.01,y +   1.01,z + -1.01);
        GL11.glVertex3d(x +  1.01,y +   1.01,z + -1.01);
        

        GL11.glVertex3d(  1.01 + x, -1.01 + y, 1.01 + z );
        GL11.glVertex3d(  1.01 + x,  1.01 + y, 1.01 + z );
        GL11.glVertex3d( -1.01 + x,  1.01 + y, 1.01 + z );
        GL11.glVertex3d( -1.01 + x, -1.01 + y, 1.01 + z );
        
        GL11.glVertex3d( 1.01 + x, -1.01 + y, -1.01 + z );
        GL11.glVertex3d( 1.01 + x,  1.01 + y, -1.01 + z );
        GL11.glVertex3d( 1.01 + x,  1.01 + y,  1.01 + z );
        GL11.glVertex3d( 1.01 + x, -1.01 + y,  1.01 + z );
        
        GL11.glVertex3d( -1.01 + x, -1.01 + y,  1.01 + z );
        GL11.glVertex3d( -1.01 + x,  1.01 + y,  1.01 + z );
        GL11.glVertex3d( -1.01 + x,  1.01 + y, -1.01 + z );
        GL11.glVertex3d( -1.01 + x, -1.01 + y, -1.01 + z );
   
        GL11.glVertex3d(  1.01 + x,  1.01 + y,  1.01 + z );
        GL11.glVertex3d(  1.01 + x,  1.01 + y, -1.01 + z );
        GL11.glVertex3d( -1.01 + x,  1.01 + y, -1.01 + z );
        GL11.glVertex3d( -1.01 + x,  1.01 + y,  1.01 + z );
        
        GL11.glVertex3d(  1.01 + x, -1.01 + y, -1.01 + z );
        GL11.glVertex3d(  1.01 + x, -1.01 + y,  1.01 + z );
        GL11.glVertex3d( -1.01 + x, -1.01 + y,  1.01 + z );
        GL11.glVertex3d( -1.01 + x, -1.01 + y, -1.01 + z );
        */
        
        GL11.glEnd();
    }

}

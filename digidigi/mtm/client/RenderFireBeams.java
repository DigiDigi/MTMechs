package digidigi.mtm.client;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraft.client.Minecraft;

public class RenderFireBeams
{

    private Minecraft mc;
    
    public static int blendstyle1 = 1; // 4 1 7
    public static int blendstyle2 = 5; // 4 5 7
    
    public int blendcounter = 0;
    
    public int timeleft = 90;
    public static List<Short> glTestMap = new ArrayList<Short>();
    
    public static List<RenderFireBeam> firebeams = new ArrayList<RenderFireBeam>();
    
    public RenderFireBeams()
    {
        glTestMap.add((short)GL11.GL_ZERO);
        glTestMap.add((short)GL11.GL_ONE); //b2
        glTestMap.add((short)GL11.GL_SRC_COLOR);
        glTestMap.add((short)GL11.GL_ONE_MINUS_SRC_COLOR);
        glTestMap.add((short)GL11.GL_SRC_ALPHA);
        glTestMap.add((short)GL11.GL_ONE_MINUS_SRC_ALPHA);
        glTestMap.add((short)GL11.GL_DST_COLOR); //b1
        glTestMap.add((short)GL11.GL_ONE_MINUS_DST_COLOR);
        
        mc = Minecraft.getMinecraft();
    }
    
    @ForgeSubscribe
    public void renderWorldLastEvent(RenderWorldLastEvent evt)
    {        
            
    if (mc != null && mc.thePlayer != null)
    {      
        if (firebeams.size() > 0)
        for (int i = 0; i < firebeams.size(); ++i)
        {

            firebeams.get(i).Render();
            if (firebeams.get(i).entitymt.beamFiring == false && firebeams.get(i).entitymt.fireTime == 0 || firebeams.get(i).entitymt.isDead)
            {
                firebeams.remove(i);
            }
            
        }
        

        
        // For testing out blend styles.
        
        this.timeleft--;
        if (this.timeleft == 0) 
        {       
            /*
            // Bright: 1:1 7:1 Dark: 6:0 7:0 0:2 6:2 Interesting :5~:6
            // Cycle between two choices quickly.
            this.timeleft = 1; 
            
            this.blendcounter += 1; if (this.blendcounter > 1) this.blendcounter = 0;
            
            if (this.blendcounter == 0)
            {
                this.blendstyle1 = 1; 
                this.blendstyle2 = 1;
            }
            if (this.blendcounter == 1) 
            {
                this.blendstyle1 = 6; 
                this.blendstyle2 = 2;
            }
            */
            
            /*
            this.timeleft = 1; 
            
            this.blendcounter += 1; if (this.blendcounter > 1) this.blendcounter = 0;
            
            if (this.blendcounter == 0)
            {
                this.blendstyle1 = 6; 
                this.blendstyle2 = 2;
            }
            if (this.blendcounter == 1) 
            {
                this.blendstyle1 = 5; 
                this.blendstyle2 = 6;
            }
            */
            
            /*
            // Cycle through all//
            blendstyle1 +=1 ;
            if (blendstyle1 == glTestMap.size()) 
            {
                blendstyle1 = 0; blendstyle2 += 1;
            }
            
            if (blendstyle2 == glTestMap.size()) 
            {
                blendstyle2 = 0;
            }
            */
            
            this.timeleft = 10;
          
        }
        
        
    }
    
    }
    
}


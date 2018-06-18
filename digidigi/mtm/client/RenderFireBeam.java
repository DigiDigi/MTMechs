package digidigi.mtm.client;

import org.lwjgl.opengl.GL11;

import digidigi.mtm.common.EntityMT;
import digidigi.mtm.common.EntityPA;
import digidigi.mtm.config.Textures;
import digidigi.mtm.client.RenderFireBeams;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;

public class RenderFireBeam
{

    private Minecraft mc;
    public EntityMT entitymt;
    public double uvscroll = 0.0D;
    
    public float beamSpin = 0.0F;
    public static double beamFwdMultiplier = -0.375;
    public static double beamSideMultiplier = -0.125;
    public static double beamVertMultiplier = 1.250;
    public double beamFwdDisplaceX = 0;
    public double beamFwdDisplaceZ = 0;
    public static double beamSideDisplaceX = 0;
    public static double beamSideDisplaceZ = 0;
    public double beamVertDisplace = beamVertMultiplier;
    private double beamAngle;
    public double beamRadians;
    public double beamRadians2;
    
    public double beamSizeMultiplier = -5.0;
    
    public static float beamPitchModifier = 5;
    public static float beamYModifier = 2.5F; //Entity adjustment.
    
    public RenderFireBeam(EntityMT entitymt)
    {
        mc = Minecraft.getMinecraft();
        this.entitymt = entitymt;
        
    }

    public void Render()
    {        
        
        double dx = this.entitymt.posX - mc.thePlayer.posX;
        double dy = this.entitymt.posY - mc.thePlayer.posY;
        double dz = this.entitymt.posZ - mc.thePlayer.posZ;
        Tessellator t = Tessellator.instance;
        
        GL11.glDisable(GL11.GL_CULL_FACE);
        
        // Orienting the beam.
        this.beamAngle = this.entitymt.rotationYaw + 180;
        this.beamRadians = this.beamAngle * Math.PI / 180;
        beamSideDisplaceX = Math.cos(beamRadians);
        beamSideDisplaceZ = Math.sin(beamRadians);
        beamSideDisplaceX *= beamSideMultiplier;
        beamSideDisplaceZ *= beamSideMultiplier;
        
        this.beamAngle = this.entitymt.rotationYaw + 180 + 90;
        if (this.beamAngle > 360) this.beamAngle -= 360;
        if (this.beamAngle < 0) this.beamAngle += 360;
        this.beamRadians2 = this.beamAngle * Math.PI / 180;
        this.beamFwdDisplaceX = Math.cos(beamRadians2);
        this.beamFwdDisplaceZ = Math.sin(beamRadians2);
        this.beamFwdDisplaceX *= beamFwdMultiplier;
        this.beamFwdDisplaceZ *= beamFwdMultiplier;
        this.beamVertDisplace = beamVertMultiplier;

        
        //Textured sides.
        GL11.glPushMatrix();
        GL11.glTranslated(dx + beamSideDisplaceX + this.beamFwdDisplaceX, dy + this.beamVertDisplace, dz + beamSideDisplaceZ + this.beamFwdDisplaceZ);
        GL11.glRotatef(-this.entitymt.rotationYaw-90, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.entitymt.rotationPitch * 200 + beamPitchModifier, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(this.beamSpin, 0.0F, 1.0F, 0.0F);
        this.mc.renderEngine.bindTexture(Textures.firebeam);
        //GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture("/digidigi/mtm/textures/firebeam.png")); 1.5.2
        GL11.glEnable( GL11.GL_BLEND );
        GL11.glBlendFunc( RenderFireBeams.glTestMap.get(RenderFireBeams.blendstyle1),  RenderFireBeams.glTestMap.get(RenderFireBeams.blendstyle2));
        GL11.glColor4f(1.0f,1.0f,1.0f,0.50f);
        t.startDrawingQuads();
        t.addVertexWithUV(0, 0 - this.entitymt.beamSize, 0 - this.entitymt.beamSize, 0, 0);//
        t.addVertexWithUV(0, 0.25 + this.entitymt.beamSize, 0 - this.entitymt.beamSize, 0+uvscroll, 1+uvscroll);//
        t.addVertexWithUV(this.entitymt.beamDistance, 0.5 + this.entitymt.beamSize, -0.25 - this.entitymt.beamSize, 1+uvscroll, 1+uvscroll);//
        t.addVertexWithUV(this.entitymt.beamDistance, -0.25 - this.entitymt.beamSize, -0.25 - this.entitymt.beamSize, 1+uvscroll, 0+uvscroll);//
        t.addVertexWithUV(0, 0 - this.entitymt.beamSize, 0.25 + this.entitymt.beamSize, 0+uvscroll, 0+uvscroll);//
        t.addVertexWithUV(0, 0.25 + this.entitymt.beamSize, 0.25 + this.entitymt.beamSize, 0+uvscroll, 1+uvscroll);//
        t.addVertexWithUV(this.entitymt.beamDistance, 0.5 + this.entitymt.beamSize, 0.5 + this.entitymt.beamSize, 1+uvscroll, 1+uvscroll);//
        t.addVertexWithUV(this.entitymt.beamDistance, -0.25 - this.entitymt.beamSize, 0.5 + this.entitymt.beamSize, 1+uvscroll, 0+uvscroll);//
        t.addVertexWithUV(0, 0.25 + this.entitymt.beamSize, 0 - this.entitymt.beamSize, 0+uvscroll, 0+uvscroll);//
        t.addVertexWithUV(0, 0.25 + this.entitymt.beamSize, 0.25 + this.entitymt.beamSize, 0+uvscroll, 1+uvscroll);//
        t.addVertexWithUV(this.entitymt.beamDistance, 0.5 + this.entitymt.beamSize, 0.5 + this.entitymt.beamSize, 1+uvscroll, 1+uvscroll);//
        t.addVertexWithUV(this.entitymt.beamDistance, 0.5 + this.entitymt.beamSize, -0.25 - this.entitymt.beamSize, 1+uvscroll, 0+uvscroll);//
        t.addVertexWithUV(0, 0 - this.entitymt.beamSize, 0 - this.entitymt.beamSize, 0+uvscroll, 0+uvscroll);//
        t.addVertexWithUV(0, 0 - this.entitymt.beamSize, 0.25 + this.entitymt.beamSize, 0+uvscroll, 1+uvscroll);//
        t.addVertexWithUV(this.entitymt.beamDistance, -0.25 - this.entitymt.beamSize, 0.5 + this.entitymt.beamSize, 1+uvscroll, 1+uvscroll);//
        t.addVertexWithUV(this.entitymt.beamDistance, -0.25 - this.entitymt.beamSize, -0.25 - this.entitymt.beamSize, 1+uvscroll, 0+uvscroll);//
        t.draw();
        //mc.renderEngine.resetBoundTexture(); 1.5.2
        
        GL11.glDisable( GL11.GL_BLEND );
        //Debug.ShowVertex(this.entitymt.posX + this.entitymt.beamRender.beamSideDisplaceX + this.entitymt.beamRender.beamFwdDisplaceX, this.entitymt.posY + this.entitymt.beamRender.beamVertDisplace, this.entitymt.posZ + this.entitymt.beamRender.beamSideDisplaceZ + this.entitymt.beamRender.beamFwdDisplaceZ * 8);
        GL11.glPopMatrix();
        

        this.mc.renderEngine.bindTexture(Textures.firebeam);
        //GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture("/digidigi/mtm/textures/firebeam.png")); 1.5.2
        
        
        // Front face. Test
        GL11.glPushMatrix();
        GL11.glTranslated(dx + beamSideDisplaceX + this.beamFwdDisplaceX, dy + this.beamVertDisplace, dz + beamSideDisplaceZ + this.beamFwdDisplaceZ);
        GL11.glRotatef(-this.entitymt.rotationYaw-90, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.entitymt.rotationPitch * 200 + beamPitchModifier, 0.0F, 0.0F, 1.0F);
        GL11.glDisable( GL11.GL_TEXTURE_2D );
        GL11.glEnable( GL11.GL_BLEND );
        GL11.glBlendFunc( RenderFireBeams.glTestMap.get(RenderFireBeams.blendstyle1),  RenderFireBeams.glTestMap.get(RenderFireBeams.blendstyle2));
        GL11.glColor4f(1.0f,0.0f,0.0f,0.90f);
        GL11.glBegin( GL11.GL_QUADS );
        GL11.glVertex3d(0, 0 - this.entitymt.beamSize, 0 - this.entitymt.beamSize);
        GL11.glVertex3d(0, 0.25 + this.entitymt.beamSize, 0 - this.entitymt.beamSize);
        GL11.glVertex3d(0, 0.25 + this.entitymt.beamSize, 0.25 + this.entitymt.beamSize);
        GL11.glVertex3d(0, 0 - this.entitymt.beamSize, 0.25 + this.entitymt.beamSize);
        GL11.glVertex3d(this.entitymt.beamDistance, -0.125 - this.entitymt.beamSize, -0.125 - this.entitymt.beamSize);
        GL11.glVertex3d(this.entitymt.beamDistance, 0.375 + this.entitymt.beamSize, -0.125 - this.entitymt.beamSize);
        GL11.glVertex3d(this.entitymt.beamDistance, 0.375 + this.entitymt.beamSize, 0.375 + this.entitymt.beamSize);
        GL11.glVertex3d(this.entitymt.beamDistance, -0.125 - this.entitymt.beamSize, 0.375 + this.entitymt.beamSize);
        GL11.glEnd();
        GL11.glEnable( GL11.GL_TEXTURE_2D );
        GL11.glDisable( GL11.GL_BLEND );
        //Debug.ShowVertex(0, 0, 0.05);
        GL11.glPopMatrix();
        
        // Surrounding translucent border.
        GL11.glPushMatrix();
        GL11.glTranslated(dx + beamSideDisplaceX + this.beamFwdDisplaceX, dy + this.beamVertDisplace, dz + beamSideDisplaceZ + this.beamFwdDisplaceZ);
        GL11.glRotatef(-this.entitymt.rotationYaw-90, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.entitymt.rotationPitch * 200 + beamPitchModifier, 0.0F, 0.0F, 1.0F);
        GL11.glDisable( GL11.GL_TEXTURE_2D );
        GL11.glEnable( GL11.GL_BLEND );
        GL11.glBlendFunc( RenderFireBeams.glTestMap.get(RenderFireBeams.blendstyle1),  RenderFireBeams.glTestMap.get(RenderFireBeams.blendstyle2));
        GL11.glColor4f(0.5f,0.0f,0.0f,0.25f);
        GL11.glBegin( GL11.GL_QUADS );
        GL11.glVertex3d(this.entitymt.beamDistance, -0.250 - this.entitymt.beamSize, -0.250 - this.entitymt.beamSize);
        GL11.glVertex3d(this.entitymt.beamDistance, 0.500 + this.entitymt.beamSize, -0.250 - this.entitymt.beamSize);
        GL11.glVertex3d(this.entitymt.beamDistance, 0.500 + this.entitymt.beamSize, 0.500 + this.entitymt.beamSize);
        GL11.glVertex3d(this.entitymt.beamDistance, -0.250 - this.entitymt.beamSize, 0.500 + this.entitymt.beamSize);
        GL11.glEnd();
        GL11.glEnable( GL11.GL_TEXTURE_2D );
        GL11.glDisable( GL11.GL_BLEND );
        GL11.glPopMatrix();
        
        this.uvscroll -= 0.03125D; // (4 / 128)
        if (this.uvscroll <= -1.0) this.uvscroll = 0.0D;
        
        /*
        double pitchRadians = Math.toRadians(((this.entitymt.rotationPitch * 200) + 180));
        double yawRadians = Math.toRadians(this.entitymt.rotationYaw + 180);
        double sinPitch = Math.sin(pitchRadians);
        double cosPitch = Math.cos(pitchRadians);
        double sinYaw = Math.sin(yawRadians);
        double cosYaw = Math.cos(yawRadians);
        */
        
        //(-cosPitch * sinYaw, sinPitch, -cosPitch * cosYaw)
        //Debug.ShowVertex(dx + ((-cosPitch * sinYaw)  * this.entitymt.beamDistance), dz + (sinPitch * this.entitymt.beamDistance) - this.beamVertDisplace, dz + -((-cosPitch * cosYaw)  * this.entitymt.beamDistance));
        
    }
    
}


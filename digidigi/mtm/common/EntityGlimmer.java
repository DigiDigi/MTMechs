package digidigi.mtm.common;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import digidigi.mtm.client.RenderFireBeams;
import digidigi.mtm.config.Textures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class EntityGlimmer extends EntityFX
{
    private static final ResourceLocation particleTextures = new ResourceLocation("textures/particle/particles.png");

    public EntityGlimmer(World par1World, double par2, double par4, double par6, double par8, double par10, double par12)
    {
        this(par1World, par2, par4, par6, par8, par10, par12, 1.0F);
    }

    public EntityGlimmer(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, float par14)
    {
        super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
        this.motionX *= 0.10000000149011612D;
        this.motionY *= 0.10000000149011612D;
        this.motionZ *= 0.10000000149011612D;
        this.motionX += par8;
        this.motionY += par10;
        this.motionZ += par12;
        this.particleScale = 4F;
        this.noClip = true;
        this.particleMaxAge = 20;
        this.setParticleTextureIndex(0);
    }
    
    public int getBrightnessForRender(float par1)
    {
        return 15728880;
    }
    
    public float getBrightness(float par1)
    {
        return 1.0F;
    }

    public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        Minecraft mc = FMLClientHandler.instance().getClient();
        
        GL11.glPushMatrix();
        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        Tessellator tessellator = par1Tessellator;
        tessellator.startDrawingQuads();
        tessellator.setBrightness(getBrightnessForRender(par2));
        mc.renderEngine.bindTexture(Textures.glimmer);
        float f10 = 0.1F * this.particleScale;
        
        //UV
        float f6 = 0.20F * this.particleTextureIndexX;
        float f7 = 0.20F + 0.20F * this.particleTextureIndexX;
        float f8 = 0F;
        float f9 = 1F;
        
        float f11 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)par2 - interpPosX);
        float f12 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)par2 - interpPosY);
        float f13 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)par2 - interpPosZ);
        float f14 = 1.0F;
        tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc( RenderFireBeams.glTestMap.get(1),  RenderFireBeams.glTestMap.get(1));
        tessellator.addVertexWithUV((double)(f11 - par3 * f10 - par6 * f10), (double)(f12 - par4 * f10), (double)(f13 - par5 * f10 - par7 * f10), (double)f7, (double)f9);
        tessellator.addVertexWithUV((double)(f11 - par3 * f10 + par6 * f10), (double)(f12 + par4 * f10), (double)(f13 - par5 * f10 + par7 * f10), (double)f7, (double)f8);
        tessellator.addVertexWithUV((double)(f11 + par3 * f10 + par6 * f10), (double)(f12 + par4 * f10), (double)(f13 + par5 * f10 + par7 * f10), (double)f6, (double)f8);
        tessellator.addVertexWithUV((double)(f11 + par3 * f10 - par6 * f10), (double)(f12 - par4 * f10), (double)(f13 + par5 * f10 - par7 * f10), (double)f6, (double)f9);
        tessellator.draw();
        GL11.glDisable(GL11.GL_BLEND);
        
       // super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6, par7);
        mc.renderEngine.bindTexture(particleTextures);
        GL11.glPopMatrix();

    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        this.particleMaxAge = 40;
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }
        
        //System.out.println((int)Math.ceil(this.particleAge / 4));
        //this.setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
        this.setParticleTextureIndex((int)Math.ceil(this.particleAge / 9));
        //System.out.println(this.particleAge);
        
        
        this.motionY -= 0.004D;
        
        //this.motionY -= (this.rand.nextFloat() - 0.5) / 40;
        //this.motionZ -= (this.rand.nextFloat() - 0.5) / 40;
        //this.motionX -= (this.rand.nextFloat() - 0.5) / 40;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.renderDistanceWeight = 3F;
        
        
        if (this.posY == this.prevPosY)
        {
            this.motionX *= 1.1D;
            this.motionZ *= 1.1D;
        }
        

        this.motionX *= 0.9599999785423279D;
        this.motionY *= 0.9599999785423279D;
        this.motionZ *= 0.9599999785423279D;

        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
        
    }
}

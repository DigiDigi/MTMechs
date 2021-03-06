package digidigi.mtm.client;


import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.relauncher.Side;

import digidigi.mtm.client.model.ModelMT;
import digidigi.mtm.common.EntityMT;
import digidigi.mtm.config.Textures;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderMT extends RenderLiving
{
	private ModelMT model;
    public boolean Empty = false; // Keeping track of whether the mech has magic slotted or not.

	public RenderMT(ModelMT modelmt, float f)
	{
        super(new ModelMT(), f);
        this.model = (ModelMT)super.mainModel;
        this.setRenderPassModel(this.model);
	}
	
	public void renderMT(EntityMT entitymt, double par2, double par4, double par6, float par8, float par9)
	{
        if (!entitymt.isMagislotted())
        {
            this.Empty = true;
        }
        else
        {
            this.Empty = false;
        }
	    super.doRenderLiving(entitymt, par2, par4, par6, par8, par9);
	}
	
	public void doRenderLiving(EntityLivingBase entityliving, double par2, double par4, double par6, float par8, float par9)
	{
	    this.renderMT((EntityMT)entityliving, par2, par4, par6, par8, par9);
	}
	
	public void doRender(Entity entity, double par2, double par4, double par6, float par8, float par9)
	{
	    this.renderMT((EntityMT)entity, par2, par4, par6, par8, par9);
    }
	
    public void rotateCorpse(EntityLivingBase par1EntityLiving, float par2, float par3, float par4)
    {
        GL11.glRotatef(180.0F - par3, 0.0F, 1.0F, 0.0F);

        if (par1EntityLiving.deathTime > 0)
        {
            float f3 = ((float)par1EntityLiving.deathTime + par4 - 1.0F) / 20.0F * 1.6F;
            f3 = MathHelper.sqrt_float(f3);

            if (f3 > 1.0F)
            {
                f3 = 1.0F;
            }

            GL11.glRotatef(f3 * this.getDeathMaxRotation(par1EntityLiving), 0.0F, 1.0F, 0.0F);
        }
    }
	
    public float getDeathMaxRotation(EntityLivingBase par1EntityLiving)
    {
        return 360.0F;
    }
	
    protected int coreGlow(EntityMT entity, int par2, float par3)
    {
        if (par2 != 0)
        {
            return -1;
        }
        else
        {
            if (this.Empty)
            {
                this.bindTexture(Textures.MTCoreEmpty);
            }
            else
            {
                this.bindTexture(Textures.MTCore);
            }
            GL11.glDepthMask(true);
            float f1 = 1.0F;
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
            GL11.glDisable(GL11.GL_LIGHTING);
            char c0 = 61680;
            int j = c0 % 65536;
            int k = c0 / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j / 1.0F, (float)k / 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, f1); 
            
            return 1;
        }
    }
    
    protected void scaleMT(EntityMT entity, float par2)
    {
        float f1 = entity.ScaleAmount();
        GL11.glScalef(f1, f1, f1);
    }
    
    protected void preRenderCallback(EntityLivingBase entity, float par2)
    {
        this.scaleMT((EntityMT)entity, par2);
    }

    protected int shouldRenderPass(EntityLivingBase entity, int par2, float par3)
    {
        return this.coreGlow((EntityMT)entity, par2, par3);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        if (this.Empty == false)
        {
            return Textures.MT;
        }
        else
        {
            return Textures.MTEmpty;
        }
    }
}

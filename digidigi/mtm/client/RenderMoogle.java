package digidigi.mtm.client;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.relauncher.Side;

import digidigi.mtm.client.model.ModelMoogle;
import digidigi.mtm.common.EntityMoogle;
import digidigi.mtm.config.Textures;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderMoogle extends RenderLiving
{

	protected ModelMoogle model;

	public RenderMoogle(ModelMoogle modelmoogle, float f)
	{
        super(new ModelMoogle(), f);
        this.model = (ModelMoogle)super.mainModel;
		
	}
	
	public void renderMT(EntityMoogle entitymt, double par2, double par4, double par6, float par8, float par9)
	{
	    super.doRenderLiving(entitymt, par2, par4, par6, par8, par9);
	}
	
	public void doRenderLiving(EntityLiving entityliving, double par2, double par4, double par6, float par8, float par9)
	{
	    this.renderMT((EntityMoogle)entityliving, par2, par4, par6, par8, par9);
	}
	
	public void doRender(Entity entity, double par2, double par4, double par6, float par8, float par9)
	{
	    this.renderMT((EntityMoogle)entity, par2, par4, par6, par8, par9);
    }
    
	protected int shouldMoogleRenderPass(EntityMoogle par1EntityMoogle, int par2, float par3)
    {
        return -1;
    }

    //protected void renderMoogleEquipedItems(EntityMoogle par1EntityMoogle, float par2)
    //{
    //    super.renderEquippedItems(par1EntityMoogle, par2);
    //}

    protected void preRenderMoogle(EntityMoogle entitymoogle, float par2)
    {
        float f1 = entitymoogle.ScaleAmount();
        GL11.glScalef(f1, f1, f1);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLiving par1EntityLiving, float par2)
    {
        this.preRenderMoogle((EntityMoogle)par1EntityLiving, par2);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3)
    {
        return this.shouldMoogleRenderPass((EntityMoogle)par1EntityLiving, par2, par3);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return Textures.moogle;
    }

    //protected void renderEquippedItems(EntityLiving par1EntityLiving, float par2)
    //{
    //    this.renderVillagerEquipedItems((EntityVillager)par1EntityLiving, par2);
    //}

	
}

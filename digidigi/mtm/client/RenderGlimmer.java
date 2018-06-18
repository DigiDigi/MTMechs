package digidigi.mtm.client;

import java.util.List;

import org.lwjgl.opengl.GL11;

import digidigi.mtm.common.EntityGlimmer;
import digidigi.mtm.config.Textures;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderGlimmer extends Render
{
    @Override
    public void doRender(Entity entity, double d0, double d1, double d2,
            float f, float f1)
    {   
        float rx = ActiveRenderInfo.rotationX;
        float rz = ActiveRenderInfo.rotationZ;
        float ryz = ActiveRenderInfo.rotationYZ;
        float rxy = ActiveRenderInfo.rotationXY;
        float rxz = ActiveRenderInfo.rotationXZ;


        Tessellator tessellator = Tessellator.instance;

        EntityGlimmer entityglimmer = (EntityGlimmer)entity;
        entityglimmer.renderParticle(tessellator, f1, rx, rxz, rz, ryz, rxy);

    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return Textures.glimmer;
    }

}

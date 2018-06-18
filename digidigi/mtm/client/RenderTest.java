package digidigi.mtm.client;

import digidigi.mtm.client.model.ModelTest;
import digidigi.mtm.config.Textures;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderTest extends RenderLiving
{
    private ModelBase model;

    public RenderTest(ModelTest modeltest, float f)
    {
        super(modeltest, f);
        this.model = (ModelTest)super.mainModel;
        this.setRenderPassModel(this.model);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return Textures.MT;
    }
}
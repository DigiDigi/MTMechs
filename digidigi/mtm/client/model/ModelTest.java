// Date: 5/23/2013 6:32:11 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX




package digidigi.mtm.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(Side.CLIENT)
public class ModelTest extends ModelBase
{
    ModelRenderer bot;
    ModelRenderer top;
    ModelRenderer mid;
    ModelRenderer center;
  
  public ModelTest()
  {
      textureWidth = 128;
      textureHeight = 128;
      
      center = new ModelRenderer(this, 0, 0);
      center.setRotationPoint(0F, 0F, 0F);
      setRotation(center, 0F, 0F, 0F);
      
      bot = new ModelRenderer(this, 0, 0);
      bot.addBox(0F, 0F, 0F, 1, 4, 4);
      bot.setRotationPoint(0F, 4F, -2F);
      bot.setTextureSize(64, 64);
      bot.mirror = true;
      setRotation(bot, 0F, 0F, 0F);
      top = new ModelRenderer(this, 0, 0);
      top.addBox(0F, 0F, 0F, 1, 4, 4);
      top.setRotationPoint(0F, -4F, -2F);
      top.setTextureSize(64, 64);
      top.mirror = true;
      setRotation(top, 0F, 0F, 0F);
      mid = new ModelRenderer(this, 0, 0);
      mid.addBox(0F, 0F, 0F, 1, 4, 12);
      mid.setRotationPoint(0F, 0F, -6F);
      mid.setTextureSize(64, 64);
      mid.mirror = true;
      setRotation(mid, 0F, 0F, 0F);
      
      center.addChild(top);
      center.addChild(mid);
      center.addChild(bot);
      

  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
      super.render(entity, f, f1, f2, f3, f4, f5);
      setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      
      
      //center.rotateAngleX = 0F; // Default
      //center.rotateAngleX = (float)Math.toRadians(90);
      
      center.rotateAngleX = center.rotateAngleX + (float)Math.toRadians(2);
      
      if (center.rotateAngleX >= (float)Math.toRadians(360))
      {
          center.rotateAngleX -= (float)Math.toRadians(360);
      }
      
      float cos = MathHelper.cos(center.rotateAngleX);
      float sin = MathHelper.sin(center.rotateAngleX);
      

      center.setRotationPoint(0F, 0F, 0F);
      
      
      center.rotationPointZ = center.rotationPointZ + -(-cos + sin);
      center.rotationPointY = center.rotationPointY + (cos + sin);      
      
      center.rotationPointZ = center.rotationPointZ - (((cos + sin)-1)/1) ;
      center.rotationPointY = center.rotationPointY - (((-(-cos + sin))-1)/1) ;
      
      center.rotationPointY -= (cos + sin) * 2;
      
      center.render(f5);
      
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }
  
  public void setLivingAnimations(EntityLivingBase entitylivingbase, float par2, float par3, float par4) 
  {
      super.setLivingAnimations(entitylivingbase, par2, par3, par4);
  }

}

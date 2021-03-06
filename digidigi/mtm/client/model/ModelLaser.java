// Date: 5/13/2013 10:22:24 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package digidigi.mtm.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class ModelLaser extends ModelBase
{
  public ModelRenderer LaserLine;
  
  public ModelLaser()
  {
      textureWidth = 128;
      textureHeight = 4;
      LaserLine = new ModelRenderer(this, 0, 0);
      LaserLine.addBox(0F, 0F, 0F, 64, 2, 2);
      LaserLine.setRotationPoint(-64.0F, 22.7F, -0.5F);
      LaserLine.setTextureSize(256, 4);
      LaserLine.mirror = true;
      setRotation(LaserLine, 0F, 0F, 0F);
      System.out.println("new model");
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    LaserLine.render(f5);
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
    

    //LaserLine.rotateAngleX = 0F; // Default
    //LaserLine.rotateAngleX = (float)Math.toRadians(90);


    LaserLine.rotateAngleX = LaserLine.rotateAngleX + (float)Math.toRadians(8);
    
    if (LaserLine.rotateAngleX >= (float)Math.toRadians(360))
    {
        LaserLine.rotateAngleX -= (float)Math.toRadians(360);
    }

    
    float cos = MathHelper.cos(LaserLine.rotateAngleX);
    float sin = MathHelper.sin(LaserLine.rotateAngleX);
    

    

    

    LaserLine.setRotationPoint(-64.0F, 22.7F, -0.5F); // Default

    /*
    LaserLine.rotationPointZ = LaserLine.rotationPointZ + -(-cos + sin);
    LaserLine.rotationPointY = LaserLine.rotationPointY + (cos + sin);
    */
    

    LaserLine.rotationPointZ = LaserLine.rotationPointZ - (((cos + sin)-1)/1) ;
    LaserLine.rotationPointY = LaserLine.rotationPointY - (((-(-cos + sin))-1)/1) ;

    //LaserLine.rotationPointY += cos + sin;
    
  }

}

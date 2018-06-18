// Date: 5/23/2013 6:32:11 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX




package digidigi.mtm.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(Side.CLIENT)
public class ModelMoogle extends ModelBase
{
  //fields
    ModelRenderer right_leg;
    ModelRenderer left_leg;
    ModelRenderer body;
    ModelRenderer right_foot;
    ModelRenderer left_foot;
    ModelRenderer head;
    ModelRenderer right_arm;
    ModelRenderer left_arm;
    ModelRenderer nose;
    ModelRenderer right_ear;
    ModelRenderer left_ear;
    ModelRenderer left_wing;
    ModelRenderer right_wing;
    ModelRenderer pompom;
    ModelRenderer pomstring;
  
  public ModelMoogle()
  {
    textureWidth = 128;
    textureHeight = 64;
    
    right_leg = new ModelRenderer(this, 27, 48);
    right_leg.addBox(0F, 0F, 0F, 4, 6, 4);
    right_leg.setRotationPoint(-5F, 18F, -2F);
    right_leg.setTextureSize(128, 64);
    right_leg.mirror = true;
    setRotation(right_leg, 0F, 0F, 0F);
    left_leg = new ModelRenderer(this, 27, 48);
    left_leg.addBox(0F, 0F, 0F, 4, 6, 4);
    left_leg.setRotationPoint(1F, 18F, -2F);
    left_leg.setTextureSize(128, 64);
    left_leg.mirror = true;
    setRotation(left_leg, 0F, 0F, 0F);
    body = new ModelRenderer(this, 27, 26);
    body.addBox(0F, 0F, 0F, 12, 10, 12);
    body.setRotationPoint(-6F, 8F, -6F);
    body.setTextureSize(128, 64);
    body.mirror = true;
    setRotation(body, 0F, 0F, 0F);
    right_foot = new ModelRenderer(this, 43, 48);
    right_foot.addBox(0F, 0F, 0F, 4, 2, 1);
    right_foot.setRotationPoint(0F, 4F, -1F);
    right_foot.setTextureSize(128, 64);
    right_foot.mirror = true;
    setRotation(right_foot, 0F, 0F, 0F);
    left_foot = new ModelRenderer(this, 43, 48);
    left_foot.addBox(0F, 0F, 0F, 4, 2, 1);
    left_foot.setRotationPoint(0F, 4F, -1F);
    left_foot.setTextureSize(128, 64);
    left_foot.mirror = true;
    setRotation(left_foot, 0F, 0F, 0F);
    head = new ModelRenderer(this, 23, 0);
    head.addBox(0F, 0F, 0F, 14, 12, 14);
    head.setRotationPoint(-7F, -4F, -7F);
    head.setTextureSize(128, 64);
    head.mirror = true;
    setRotation(head, 0F, 0F, 0F);
    right_arm = new ModelRenderer(this, 15, 26);
    right_arm.addBox(0F, 0F, 0F, 2, 7, 4);
    right_arm.setRotationPoint(-8F, 8F, -2F);
    right_arm.setTextureSize(128, 64);
    right_arm.mirror = true;
    setRotation(right_arm, 0F, 0F, 0.1396263F);
    left_arm = new ModelRenderer(this, 75, 26);
    left_arm.addBox(0F, 0F, 0F, 2, 7, 4);
    left_arm.setRotationPoint(6F, 8F, -2F);
    left_arm.setTextureSize(128, 64);
    left_arm.mirror = true;
    setRotation(left_arm, 0F, 0F, -0.1396263F);
    nose = new ModelRenderer(this, 79, 0);
    nose.addBox(0F, 0F, 0F, 4, 3, 1);
    nose.setRotationPoint(-2F, 1F, -8F);
    nose.setTextureSize(128, 64);
    nose.mirror = true;
    setRotation(nose, 0F, 0F, 0F);
    right_ear = new ModelRenderer(this, 11, 0);
    right_ear.addBox(0F, 0F, 0F, 2, 3, 4);
    right_ear.setRotationPoint(-7F, -7F, -2F);
    right_ear.setTextureSize(128, 64);
    right_ear.mirror = true;
    setRotation(right_ear, 0F, 0F, 0F);
    left_ear = new ModelRenderer(this, 11, 3);
    left_ear.addBox(0F, 0F, 0F, 2, 3, 4);
    left_ear.setRotationPoint(5F, -7F, -2F);
    left_ear.setTextureSize(128, 64);
    left_ear.mirror = true;
    setRotation(left_ear, 0F, 0F, 0F);
    right_wing = new ModelRenderer(this, 5, 38);
    right_wing.addBox(0F, 0F, 0F, 10, 4, 1);
    right_wing.setRotationPoint(0F, 8F, 5F);
    right_wing.setTextureSize(128, 64);
    right_wing.mirror = true;
    setRotation(right_wing, 0F, -2.094395F, 0F);
    left_wing = new ModelRenderer(this, 5, 38);
    left_wing.addBox(0F, 0F, 0F, 1, 4, 10);
    left_wing.setRotationPoint(0F, 8F, 5F);
    left_wing.setTextureSize(128, 64);
    left_wing.mirror = true;
    setRotation(left_wing, 0F, 0.5235988F, 0F);
    
    pompom = new ModelRenderer(this, 7, 16);
    pompom.addBox(0F, 0F, 0F, 4, 4, 4);
    pompom.setRotationPoint(-2F, -14F, -4F);
    pompom.setTextureSize(128, 64);
    pompom.mirror = true;
    setRotation(pompom, 0F, 0F, 0F);
    pomstring = new ModelRenderer(this, 2, 28);
    pomstring.addBox(0F, 0F, 0F, 1, 6, 4);
    pomstring.setRotationPoint(0F, -10F, -1F);
    pomstring.setTextureSize(128, 64);
    pomstring.mirror = true;
    setRotation(pomstring, 0F, 0F, 0F);
    
    left_leg.addChild(left_foot);
    right_leg.addChild(right_foot);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    
    right_leg.render(f5);
    left_leg.render(f5);
    body.render(f5);
    //right_foot.render(f5);
    //left_foot.render(f5);
    head.render(f5);
    right_arm.render(f5);
    left_arm.render(f5);
    nose.render(f5);
    right_ear.render(f5);
    left_ear.render(f5);
    left_wing.render(f5);
    right_wing.render(f5);
    pompom.render(f5);
    pomstring.render(f5);

    
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    //this.head.rotateAngleY = f3 / (180F / (float)Math.PI);
    //this.head.rotateAngleX = f4 / (180F / (float)Math.PI);
    this.right_arm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
    this.left_arm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
    this.right_arm.rotateAngleZ = 0.1396263F;
    this.left_arm.rotateAngleZ = -0.1396263F;
    this.right_leg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1 * 0.5F;
    this.left_leg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1 * 0.5F;
    this.right_leg.rotateAngleY = 0.0F;
    this.left_leg.rotateAngleY = 0.0F;
  }

}
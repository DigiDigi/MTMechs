package digidigi.mtm.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import digidigi.mtm.common.EntityPA;

@SideOnly(Side.CLIENT)
public class ModelPA extends ModelBase
{
    public ModelRenderer fplate;
    public ModelRenderer fmlplate;
    public ModelRenderer fmrplate;
    public ModelRenderer flplate;
    public ModelRenderer frplate;
    public ModelRenderer lplate;
    public ModelRenderer rplate;
    public ModelRenderer seatfloor;
    public ModelRenderer ControlL;
    public ModelRenderer ControlM;
    public ModelRenderer ControlR;
    public ModelRenderer bottom;
    public ModelRenderer leftcanbot;
    public ModelRenderer rightcanbot;
    public ModelRenderer magicore;
    public ModelRenderer magiplatetop;
    public ModelRenderer magiplatebot;
    public ModelRenderer magiplateleft;
    public ModelRenderer magiplateright;
    public ModelRenderer lefthip;
    public ModelRenderer leftupperleg;
    public ModelRenderer leftlowerleg;
    public ModelRenderer leftlegnode;
    public ModelRenderer leftfoot;
    public ModelRenderer leftfootbase;
    public ModelRenderer leftbackltoe;
    public ModelRenderer leftbackrtoe;
    public ModelRenderer lefttoe;
    public ModelRenderer leftheel;
    public ModelRenderer righthip;
    public ModelRenderer rightupperleg;
    public ModelRenderer rightlowerleg;
    public ModelRenderer rightlegnode;
    public ModelRenderer rightfoot;
    public ModelRenderer rightfootbase;
    public ModelRenderer rightbackltoe;
    public ModelRenderer rightbackrtoe;
    public ModelRenderer righttoe;
    public ModelRenderer rightheel;
    public ModelRenderer leftarmnub;
    public ModelRenderer leftarmnode;
    public ModelRenderer leftupperarm;
    public ModelRenderer rightarmnub;
    public ModelRenderer rightarmnode;
    public ModelRenderer rightupperarm;
    public ModelRenderer conrightbot;
    public ModelRenderer conleftbot;
    public ModelRenderer confrontleft;
    public ModelRenderer confrontright;
    public ModelRenderer confront;
    public ModelRenderer conbackbotleft;
    public ModelRenderer conback;
    public ModelRenderer conbackleft;
    public ModelRenderer conbackright;
    public ModelRenderer conbackmid;
    public ModelRenderer conbackbot;
    public ModelRenderer conbackbotright;
    public ModelRenderer engine;
    public ModelRenderer seatback;
    public ModelRenderer axle;
    public ModelRenderer mech;
  
  public ModelPA()
  {
      textureWidth = 128;
      textureHeight = 128;
    
      //Holds all the pieces.
      mech = new ModelRenderer(this, 0, 0);
      mech.setRotationPoint(0F, 0F, 0F);
      setRotation(mech, 0F, 0F, 0F);
      
      //Holds mainly the torso pieces.
      axle = new ModelRenderer(this, 0, 0);
      axle.setRotationPoint(14F, -9F, -4F);
      setRotation(axle, 0F, 0F, 0F);
      
      //of Axle//
      fplate = new ModelRenderer(this, 0, 69);
      fplate.addBox(0F, 0F, 0F, 2, 8, 8);
      fplate.setRotationPoint(14F, -9F, -4F);
      fplate.setTextureSize(128, 128);
      fplate.mirror = true;
      setRotation(fplate, 0F, 0F, 0F);
      fmlplate = new ModelRenderer(this, 0, 35);
      fmlplate.addBox(-4F, 0F, -4F, 2, 8, 2);
      fmlplate.setRotationPoint(16F, -8F, 8F);
      fmlplate.setTextureSize(128, 128);
      fmlplate.mirror = true;
      setRotation(fmlplate, 0F, 0F, 0F);
      fmrplate = new ModelRenderer(this, 0, 35);
      fmrplate.addBox(-4F, 0F, -4F, 2, 8, 2);
      fmrplate.setRotationPoint(16F, -8F, -2F);
      fmrplate.setTextureSize(128, 128);
      fmrplate.mirror = true;
      setRotation(fmrplate, 0F, 0F, 0F);
      flplate = new ModelRenderer(this, 0, 35);
      flplate.addBox(-4F, 0F, -4F, 2, 8, 2);
      flplate.setRotationPoint(14F, -7F, 10F);
      flplate.setTextureSize(128, 128);
      flplate.mirror = true;
      setRotation(flplate, 0F, 0F, 0F);
      frplate = new ModelRenderer(this, 0, 35);
      frplate.addBox(-4F, 0F, -4F, 2, 8, 2);
      frplate.setRotationPoint(14F, -7F, -4F);
      frplate.setTextureSize(128, 128);
      frplate.mirror = true;
      setRotation(frplate, 0F, 0F, 0F);
      lplate = new ModelRenderer(this, 0, 26);
      lplate.addBox(-4F, 0F, -4F, 12, 7, 2);
      lplate.setRotationPoint(2F, -6F, 12F);
      lplate.setTextureSize(128, 128);
      lplate.mirror = true;
      setRotation(lplate, 0F, 0F, 0F);
      rplate = new ModelRenderer(this, 0, 26);
      rplate.addBox(-4F, 0F, -4F, 12, 7, 2);
      rplate.setRotationPoint(2F, -6F, -6F);
      rplate.setTextureSize(128, 128);
      rplate.mirror = true;
      setRotation(rplate, 0F, 0F, 0F);
      seatback = new ModelRenderer(this, 88, 77);
      seatback.addBox(0F, 0F, 0F, 6, 14, 10);
      seatback.setRotationPoint(-8F, -9F, -5F);
      seatback.setTextureSize(128, 128);
      seatback.mirror = true;
      setRotation(seatback, 0F, 0F, 0F);
      ControlL = new ModelRenderer(this, 24, 118);
      ControlL.addBox(-4F, 0F, -4F, 2, 8, 2);
      ControlL.setRotationPoint(14F, -10F, 8F);
      ControlL.setTextureSize(128, 128);
      ControlL.mirror = true;
      setRotation(ControlL, 0F, 0F, 0F);
      ControlM = new ModelRenderer(this, 24, 106);
      ControlM.addBox(-4F, 0F, -4F, 4, 4, 8);
      ControlM.setRotationPoint(14F, -6F, 0F);
      ControlM.setTextureSize(128, 128);
      ControlM.mirror = true;
      setRotation(ControlM, 0F, 0F, 0F);
      ControlR = new ModelRenderer(this, 24, 118);
      ControlR.addBox(-4F, 0F, -4F, 2, 8, 2);
      ControlR.setRotationPoint(14F, -10F, -2F);
      ControlR.setTextureSize(128, 128);
      ControlR.mirror = true;
      setRotation(ControlR, 0F, 0F, 0F);
      engine = new ModelRenderer(this, 94, 0);
      engine.addBox(-4F, 0F, -4F, 5, 14, 10);
      engine.setRotationPoint(-9F, -11F, -1F);
      engine.setTextureSize(128, 128);
      engine.mirror = true;
      setRotation(engine, 0F, 0F, 0F);
      bottom = new ModelRenderer(this, 82, 24);
      bottom.addBox(-4F, 0F, -4F, 13, 7, 10);
      bottom.setRotationPoint(0F, 0F, -1F);
      bottom.setTextureSize(128, 128);
      bottom.mirror = true;
      setRotation(bottom, 0F, 0F, 0F);
      leftcanbot = new ModelRenderer(this, 28, 13);
      leftcanbot.addBox(-4F, 0F, -4F, 6, 14, 6);
      leftcanbot.setRotationPoint(-4F, -9F, 9F);
      leftcanbot.setTextureSize(128, 128);
      leftcanbot.mirror = true;
      setRotation(leftcanbot, 0F, 0F, 0F);
      rightcanbot = new ModelRenderer(this, 28, 13);
      rightcanbot.addBox(-4F, 0F, -4F, 6, 14, 6);
      rightcanbot.setRotationPoint(-4F, -9F, -7F);
      rightcanbot.setTextureSize(128, 128);
      rightcanbot.mirror = true;
      setRotation(rightcanbot, 0F, 0F, 0F);
      magicore = new ModelRenderer(this, 55, 120);
      magicore.addBox(-4F, 0F, -4F, 4, 4, 4);
      magicore.setRotationPoint(14F, 0F, 2F);
      magicore.setTextureSize(128, 128);
      magicore.mirror = true;
      setRotation(magicore, 0F, 0F, 0F);
      magiplatetop = new ModelRenderer(this, 42, 112);
      magiplatetop.addBox(-4F, 0F, -4F, 6, 1, 6);
      magiplatetop.setRotationPoint(13F, -1F, 1F);
      magiplatetop.setTextureSize(128, 128);
      magiplatetop.mirror = true;
      setRotation(magiplatetop, 0F, 0F, 0F);
      magiplatebot = new ModelRenderer(this, 48, 112);
      magiplatebot.addBox(-4F, 0F, -4F, 6, 1, 6);
      magiplatebot.setRotationPoint(13F, 4F, 1F);
      magiplatebot.setTextureSize(128, 128);
      magiplatebot.mirror = true;
      setRotation(magiplatebot, 0F, 0F, 0F);
      magiplateleft = new ModelRenderer(this, 53, 119);
      magiplateleft.addBox(-4F, 0F, -4F, 6, 4, 1);
      magiplateleft.setRotationPoint(13F, 0F, 6F);
      magiplateleft.setTextureSize(128, 128);
      magiplateleft.mirror = true;
      setRotation(magiplateleft, 0F, 0F, 0F);
      magiplateright = new ModelRenderer(this, 51, 119);
      magiplateright.addBox(-4F, 0F, -4F, 6, 4, 1);
      magiplateright.setRotationPoint(13F, 0F, 1F);
      magiplateright.setTextureSize(128, 128);
      magiplateright.mirror = true;
      setRotation(magiplateright, 0F, 0F, 0F);
      leftarmnub = new ModelRenderer(this, 20, 73);
      leftarmnub.addBox(-11F, 0F, -4F, 3, 4, 9);
      leftarmnub.setRotationPoint(17F, 1F, 9F);
      leftarmnub.setTextureSize(128, 128);
      leftarmnub.mirror = true;
      setRotation(leftarmnub, 0F, 0F, 0F);
      leftarmnode = new ModelRenderer(this, 24, 96);
      leftarmnode.addBox(0F, 0F, 0F, 6, 6, 4);
      leftarmnode.setRotationPoint(-2F, -6F, 10F);
      leftarmnode.setTextureSize(128, 128);
      leftarmnode.mirror = true;
      setRotation(leftarmnode, 0F, 0F, 0F);
      leftupperarm = new ModelRenderer(this, 25, 108);
      leftupperarm.addBox(0F, 0F, 0F, 7, 2, 2);
      leftupperarm.setRotationPoint(1F, -2F, 11F);
      leftupperarm.setTextureSize(128, 128);
      leftupperarm.mirror = true;
      setRotation(leftupperarm, 0F, 0F, 0.5576792F);
      rightarmnub = new ModelRenderer(this, 20, 73);
      rightarmnub.addBox(0F, 0F, 0F, 3, 4, 9);
      rightarmnub.setRotationPoint(6F, 1F, -14F);
      rightarmnub.setTextureSize(128, 128);
      rightarmnub.mirror = true;
      setRotation(rightarmnub, 0F, 0F, 0F);
      rightarmnode = new ModelRenderer(this, 24, 86);
      rightarmnode.addBox(0F, 0F, 0F, 6, 6, 4);
      rightarmnode.setRotationPoint(-2F, -6F, -14F);
      rightarmnode.setTextureSize(128, 128);
      rightarmnode.mirror = true;
      setRotation(rightarmnode, 0F, 0F, 0F);
      rightupperarm = new ModelRenderer(this, 25, 108);
      rightupperarm.addBox(0F, 0F, 0F, 7, 2, 2);
      rightupperarm.setRotationPoint(1F, -2F, -13F);
      rightupperarm.setTextureSize(128, 128);
      rightupperarm.mirror = true;
      setRotation(rightupperarm, 0F, 0F, 0.5576792F);
      confront = new ModelRenderer(this, 48, 103);
      confront.addBox(0F, 0F, 0F, 1, 1, 8);
      confront.setRotationPoint(19F, -3F, -4F);
      confront.setTextureSize(128, 128);
      confront.mirror = true;
      setRotation(confront, 0F, 0F, 0F);
      confrontleft = new ModelRenderer(this, 54, 96);
      confrontleft.addBox(0F, 0F, 0F, 1, 6, 1);
      confrontleft.setRotationPoint(19F, -2F, 4F);
      confrontleft.setTextureSize(128, 128);
      confrontleft.mirror = true;
      setRotation(confrontleft, 0F, 0F, 0F);
      confrontright = new ModelRenderer(this, 54, 96);
      confrontright.addBox(0F, 0F, 0F, 1, 6, 1);
      confrontright.setRotationPoint(19F, -2F, -5F);
      confrontright.setTextureSize(128, 128);
      confrontright.mirror = true;
      setRotation(confrontright, 0F, 0F, 0F);
      conback = new ModelRenderer(this, 44, 83);
      conback.addBox(0F, 0F, 0F, 1, 1, 10);
      conback.setRotationPoint(-12F, -14F, -5F);
      conback.setTextureSize(128, 128);
      conback.mirror = true;
      setRotation(conback, 0F, 0F, 0F);
      conbackbotleft = new ModelRenderer(this, 68, 113);
      conbackbotleft.addBox(0F, 0F, 0F, 1, 4, 1);
      conbackbotleft.setRotationPoint(-5F, -13F, 4F);
      conbackbotleft.setTextureSize(128, 128);
      conbackbotleft.mirror = true;
      setRotation(conbackbotleft, 0F, 0F, -0.2443461F);
      conbackleft = new ModelRenderer(this, 44, 94);
      conbackleft.addBox(0F, 0F, 0F, 6, 1, 1);
      conbackleft.setRotationPoint(-11F, -14F, 4F);
      conbackleft.setTextureSize(128, 128);
      conbackleft.mirror = true;
      setRotation(conbackleft, 0F, 0F, 0F);
      conbackright = new ModelRenderer(this, 44, 94);
      conbackright.addBox(0F, 0F, 0F, 6, 1, 1);
      conbackright.setRotationPoint(-11F, -14F, -5F);
      conbackright.setTextureSize(128, 128);
      conbackright.mirror = true;
      setRotation(conbackright, 0F, 0F, 0F);
      conbackmid = new ModelRenderer(this, 44, 83);
      conbackmid.addBox(0F, 0F, 0F, 1, 1, 10);
      conbackmid.setRotationPoint(-5F, -14F, -5F);
      conbackmid.setTextureSize(128, 128);
      conbackmid.mirror = true;
      setRotation(conbackmid, 0F, 0F, 0F);
      conbackbot = new ModelRenderer(this, 44, 83);
      conbackbot.addBox(0F, 0F, 0F, 1, 1, 10);
      conbackbot.setRotationPoint(-4F, -10F, -5F);
      conbackbot.setTextureSize(128, 128);
      conbackbot.mirror = true;
      setRotation(conbackbot, 0F, 0F, 0F);
      conbackbotright = new ModelRenderer(this, 68, 113);
      conbackbotright.addBox(0F, 0F, 0F, 1, 4, 1);
      conbackbotright.setRotationPoint(-5F, -13F, -5F);
      conbackbotright.setTextureSize(128, 128);
      conbackbotright.mirror = true;
      setRotation(conbackbotright, 0F, 0F, -0.2443461F);
      conleftbot = new ModelRenderer(this, 44, 94);
      conleftbot.addBox(0F, 0F, 0F, 10, 1, 1);
      conleftbot.setRotationPoint(9F, 4F, 4F);
      conleftbot.setTextureSize(128, 128);
      conleftbot.mirror = true;
      setRotation(conleftbot, 0F, 0F, 0F);
      conrightbot = new ModelRenderer(this, 44, 94);
      conrightbot.addBox(0F, 0F, 0F, 10, 1, 1);
      conrightbot.setRotationPoint(9F, 4F, -5F);
      conrightbot.setTextureSize(128, 128);
      conrightbot.mirror = true;
      setRotation(conrightbot, 0F, 0F, 0F);
      seatfloor = new ModelRenderer(this, 72, 111);
      seatfloor.addBox(-4F, 0F, -4F, 12, 1, 16);
      seatfloor.setRotationPoint(2F, -2F, -4F);
      seatfloor.setTextureSize(128, 128);
      seatfloor.mirror = true;
      setRotation(seatfloor, 0F, 0F, 0F);
      //of Axle//

      leftfoot = new ModelRenderer(this, 50, 0);
      leftfoot.addBox(0F, 0F, 0F, 6, 4, 6);
      leftfoot.setRotationPoint(-2F, 16F, 6F);
      leftfoot.setTextureSize(128, 128);
      leftfoot.mirror = true;
      setRotation(leftfoot, 0F, 0F, 0F);
      leftlegnode = new ModelRenderer(this, 0, 116);
      leftlegnode.addBox(0F, 0F, 0F, 6, 6, 6);
      leftlegnode.setRotationPoint(-6F, 8F, 6F);
      leftlegnode.setTextureSize(128, 128);
      leftlegnode.mirror = true;
      setRotation(leftlegnode, 0F, 0F, 0F);
      leftupperleg = new ModelRenderer(this, 120, 89);
      leftupperleg.addBox(0F, 0F, 0F, 2, 10, 2);
      leftupperleg.setRotationPoint(3F, 4F, 8F);
      leftupperleg.setTextureSize(128, 128);
      leftupperleg.mirror = true;
      setRotation(leftupperleg, 0F, 0F, 0.9599311F);
      righthip = new ModelRenderer(this, 104, 40);
      righthip.addBox(0F, 0F, 0F, 6, 4, 6);
      righthip.setRotationPoint(0F, 2F, -12F);
      righthip.setTextureSize(128, 128);
      righthip.mirror = true;
      setRotation(righthip, 0F, 0F, 0F);
      rightupperleg = new ModelRenderer(this, 120, 89);
      rightupperleg.addBox(0F, 0F, 0F, 2, 10, 2);
      rightupperleg.setRotationPoint(3F, 4F, -10F);
      rightupperleg.setTextureSize(128, 128);
      rightupperleg.mirror = true;
      setRotation(rightupperleg, 0F, 0F, 0.9599311F);
      leftbackrtoe = new ModelRenderer(this, 0, 58);
      leftbackrtoe.addBox(0F, 0F, 0F, 4, 2, 4);
      leftbackrtoe.setRotationPoint(-6F, 22F, 3F);
      leftbackrtoe.setTextureSize(128, 128);
      leftbackrtoe.mirror = true;
      setRotation(leftbackrtoe, 0F, 0F, 0F);
      rightfootbase = new ModelRenderer(this, 16, 0);
      rightfootbase.addBox(0F, 0F, 0F, 9, 4, 8);
      rightfootbase.setRotationPoint(-2F, 20F, -13F);
      rightfootbase.setTextureSize(128, 128);
      rightfootbase.mirror = true;
      setRotation(rightfootbase, 0F, 0F, 0F);
      righttoe = new ModelRenderer(this, 14, 56);
      righttoe.addBox(0F, 0F, 0F, 5, 2, 6);
      righttoe.setRotationPoint(7F, 22F, -12F);
      righttoe.setTextureSize(128, 128);
      righttoe.mirror = true;
      setRotation(righttoe, 0F, 0F, 0F);
      lefttoe = new ModelRenderer(this, 14, 56);
      lefttoe.addBox(0F, 0F, 0F, 5, 2, 6);
      lefttoe.setRotationPoint(7F, 22F, 6F);
      lefttoe.setTextureSize(128, 128);
      lefttoe.mirror = true;
      setRotation(lefttoe, 0F, 0F, 0F);
      rightlowerleg = new ModelRenderer(this, 88, 101);
      rightlowerleg.addBox(0F, 0F, 0F, 10, 2, 2);
      rightlowerleg.setRotationPoint(-2F, 12F, -10F);
      rightlowerleg.setTextureSize(128, 128);
      rightlowerleg.mirror = true;
      setRotation(rightlowerleg, 0F, 0F, 0.9599311F);
      leftfootbase = new ModelRenderer(this, 16, 0);
      leftfootbase.addBox(0F, 0F, 0F, 9, 4, 8);
      leftfootbase.setRotationPoint(-2F, 20F, 5F);
      leftfootbase.setTextureSize(128, 128);
      leftfootbase.mirror = true;
      setRotation(leftfootbase, 0F, 0F, 0F);
      rightbackltoe = new ModelRenderer(this, 0, 58);
      rightbackltoe.addBox(0F, 0F, 0F, 4, 2, 4);
      rightbackltoe.setRotationPoint(-6F, 22F, -7F);
      rightbackltoe.setTextureSize(128, 128);
      rightbackltoe.mirror = true;
      setRotation(rightbackltoe, 0F, 0F, 0F);
      rightfoot = new ModelRenderer(this, 50, 0);
      rightfoot.addBox(0F, 0F, 0F, 6, 4, 6);
      rightfoot.setRotationPoint(-2F, 16F, -12F);
      rightfoot.setTextureSize(128, 128);
      rightfoot.mirror = true;
      setRotation(rightfoot, 0F, 0F, 0F);
      rightbackrtoe = new ModelRenderer(this, 0, 58);
      rightbackrtoe.addBox(0F, 0F, 0F, 4, 2, 4);
      rightbackrtoe.setRotationPoint(-6F, 22F, -15F);
      rightbackrtoe.setTextureSize(128, 128);
      rightbackrtoe.mirror = true;
      setRotation(rightbackrtoe, 0F, 0F, 0F);
      leftlowerleg = new ModelRenderer(this, 88, 101);
      leftlowerleg.addBox(0F, 0F, 0F, 10, 2, 2);
      leftlowerleg.setRotationPoint(-2F, 12F, 8F);
      leftlowerleg.setTextureSize(128, 128);
      leftlowerleg.mirror = true;
      setRotation(leftlowerleg, 0F, 0F, 0.9599311F);
      lefthip = new ModelRenderer(this, 104, 40);
      lefthip.addBox(0F, 0F, 0F, 6, 4, 6);
      lefthip.setRotationPoint(0F, 2F, 6F);
      lefthip.setTextureSize(128, 128);
      lefthip.mirror = true;
      setRotation(lefthip, 0F, 0F, 0F);
      leftheel = new ModelRenderer(this, 74, 0);
      leftheel.addBox(0F, 0F, 0F, 2, 4, 6);
      leftheel.setRotationPoint(-4F, 18F, 6F);
      leftheel.setTextureSize(128, 128);
      leftheel.mirror = true;
      setRotation(leftheel, 0F, 0F, 0F);
      leftbackltoe = new ModelRenderer(this, 0, 58);
      leftbackltoe.addBox(0F, 0F, 0F, 4, 2, 4);
      leftbackltoe.setRotationPoint(-6F, 22F, 11F);
      leftbackltoe.setTextureSize(128, 128);
      leftbackltoe.mirror = true;
      setRotation(leftbackltoe, 0F, 0F, 0F);
      rightlegnode = new ModelRenderer(this, 0, 116);
      rightlegnode.addBox(0F, 0F, 0F, 6, 6, 6);
      rightlegnode.setRotationPoint(-6F, 8F, -12F);
      rightlegnode.setTextureSize(128, 128);
      rightlegnode.mirror = true;
      setRotation(rightlegnode, 0F, 0F, 0F);
      rightheel = new ModelRenderer(this, 74, 0);
      rightheel.addBox(0F, 0F, 0F, 2, 4, 6);
      rightheel.setRotationPoint(-4F, 18F, -12F);
      rightheel.setTextureSize(128, 128);
      rightheel.mirror = true;
      setRotation(rightheel, 0F, 0F, 0F);
      
      //Relative positions added after the add childs. Everything that isn't leg stuff.
      axle.addChild(fplate);
      axle.addChild(fmlplate);
      axle.addChild(fmrplate);
      axle.addChild(flplate);
      axle.addChild(frplate);
      axle.addChild(lplate);
      axle.addChild(rplate);
      axle.addChild(seatfloor);
      axle.addChild(seatback);
      axle.addChild(ControlL);
      axle.addChild(ControlM);
      axle.addChild(ControlR);
      axle.addChild(engine);
      axle.addChild(bottom);
      axle.addChild(leftcanbot);
      axle.addChild(rightcanbot);
      axle.addChild(magicore);
      axle.addChild(magiplatetop);
      axle.addChild(magiplatebot);
      axle.addChild(magiplateleft);
      axle.addChild(magiplateright);
      axle.addChild(leftarmnode);
      axle.addChild(leftarmnub);
      axle.addChild(leftupperarm);
      axle.addChild(rightarmnode);
      axle.addChild(rightarmnub);
      axle.addChild(rightupperarm);
      axle.addChild(confront);
      axle.addChild(confrontleft);
      axle.addChild(confrontright);
      axle.addChild(conback);
      axle.addChild(conbackleft);
      axle.addChild(conbackright);
      axle.addChild(conbackmid);
      axle.addChild(conbackbot);
      axle.addChild(conbackbotleft);
      axle.addChild(conbackbotright);
      axle.addChild(conleftbot);
      axle.addChild(conrightbot);
      axle.setRotationPoint(-4F, 0F, -4F);
      fplate.setRotationPoint(18F, -9F, 0F);
      fmlplate.setRotationPoint(20F, -8F, 12F);
      fmrplate.setRotationPoint(20F, -8F, 2F);
      flplate.setRotationPoint(18F, -7F, 14F);
      frplate.setRotationPoint(18F, -7F, 0F);
      lplate.setRotationPoint(6F, -6F, 16F);
      rplate.setRotationPoint(6F, -6F, -2F);
      seatback.setRotationPoint(-4F, -9F, -1F);
      ControlL.setRotationPoint(18F, -10F, 12F);
      ControlM.setRotationPoint(18F, -6F, 4F);
      ControlR.setRotationPoint(18F, -10F, 2F);
      engine.setRotationPoint(-5F, -11F, 3F);
      bottom.setRotationPoint(4F, 0F, 3F);
      leftcanbot.setRotationPoint(0F, -9F, 13F);
      rightcanbot.setRotationPoint(0F, -9F, -3F);
      magicore.setRotationPoint(18F, 0F, 6F);
      magiplatetop.setRotationPoint(17F, -1F, 5F);
      magiplatebot.setRotationPoint(17F, 4F, 5F);
      magiplateleft.setRotationPoint(17F, 0F, 10F);
      magiplateright.setRotationPoint(17F, 0F, 5F);
      leftarmnub.setRotationPoint(21F, 1F, 13F);
      leftarmnode.setRotationPoint(2F, -6F, -10F);
      leftupperarm.setRotationPoint(5F, -2F, 15F);
      rightarmnub.setRotationPoint(10F, 1F, -10F);
      rightarmnode.setRotationPoint(2F, -6F, 14F);
      rightupperarm.setRotationPoint(5F, -2F, -9F);
      confront.setRotationPoint(23F, -3F, 0F);
      confrontleft.setRotationPoint(23F, -2F, 8F);
      confrontright.setRotationPoint(23F, -2F, -1F);
      conback.setRotationPoint(-8F, -14F, -1F);
      conbackbotleft.setRotationPoint(-1F, -13F, 8F);
      conbackleft.setRotationPoint(-7F, -14F, 8F);
      conbackright.setRotationPoint(-7F, -14F, -1F);
      conbackmid.setRotationPoint(-1F, -14F, -1F);
      conbackbot.setRotationPoint(0F, -10F, -1F);
      conbackbotright.setRotationPoint(-1F, -13F, -1F);
      conleftbot.setRotationPoint(13F, 4F, 8F);
      conrightbot.setRotationPoint(13F, 4F, -1F);
      seatfloor.setRotationPoint(6F, -2F, 0F);
      
      mech.addChild(axle);
      mech.addChild(lefthip);
      mech.addChild(leftupperleg);
      mech.addChild(leftlegnode);
      mech.addChild(leftlowerleg);
      mech.addChild(leftfoot);
      mech.addChild(leftfootbase);
      mech.addChild(lefttoe);
      mech.addChild(leftbackltoe);
      mech.addChild(leftbackrtoe);
      mech.addChild(leftheel);
      mech.addChild(righthip);
      mech.addChild(rightupperleg);
      mech.addChild(rightlegnode);
      mech.addChild(rightlowerleg);
      mech.addChild(rightfoot);
      mech.addChild(rightfootbase);
      mech.addChild(righttoe);
      mech.addChild(rightbackltoe);
      mech.addChild(rightbackrtoe);
      mech.addChild(rightheel);
      setRotation(mech, 0F, 1.570796F, 0F);  
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    mech.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e1)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, e1);
  }
  
  public void setLivingAnimations(EntityLivingBase entitylivingbase, float par2, float par3, float par4) 
  {
      
      // (Left) Leg parts move up a unit to the air.
      // (Left) Upper leg bends and the node and lower leg move to follow the bottom of the upper leg.
      // (Left) Leg parts move down a unit to the ground.
      // While the (Left) Upper leg bends back and the lower leg parts move back, a motion is put on the model.
      // Also while that is happening the (Right) Leg parts do the same as the (Left) leg parts were doing at the beginning.
      
      // Stage 0. Default.
      // Stage 1. Left leg rises.              Right leg pulls. (entity move)
      // Stage 2. Left leg drops.               Right leg stalls. 
      // Stage 3. Left leg pulls. (entity move) Right leg rises.
      // Stage 4. Left leg stalls.              Right leg drops.
      // Stage 5. Pull back legs to default.
      
      
      EntityPA entity = (EntityPA)entitylivingbase;
      float vweight = 2.0F;
      
      this.axle.rotateAngleZ = entity.rotationPitch;
      
      Entity rider = null;
      if (entity.riddenByEntity != null)
      {
          rider = entity.riddenByEntity;
      }
      
      if (rider != null && entity.onGround)
      {
          if (entity.isWalking)
              {
              if (entity.walkAnimationStage == 0)
              {
                  //Defaults
                  this.leftlegnode.setRotationPoint(-6F, 8F, 6F);
                  this.leftfoot.setRotationPoint(-2F, 16F, 6F);
                  this.leftfootbase.setRotationPoint(-2F, 20F, 5F);
                  this.lefttoe.setRotationPoint(7F, 22F, 6F);
                  this.leftbackltoe.setRotationPoint(-6F, 22F, 11F);
                  this.leftbackrtoe.setRotationPoint(-6F, 22F, 3F);
                  this.leftheel.setRotationPoint(-4F, 18F, 6F);
                  this.leftlowerleg.setRotationPoint(-2F, 12F, 8F);
                  
                  this.rightlegnode.setRotationPoint(-6F, 8F, -12F);
                  this.rightfoot.setRotationPoint(-2F, 16F, -12F);
                  this.rightfootbase.setRotationPoint(-2F, 20F, -13F);
                  this.righttoe.setRotationPoint(7F, 22F, -12F);
                  this.rightbackltoe.setRotationPoint(-6F, 22F, -15F);
                  this.rightbackrtoe.setRotationPoint(-6F, 22F, -7F);
                  this.rightheel.setRotationPoint(-4F, 18F, -12F);
                  this.rightlowerleg.setRotationPoint(-2F, 12F, -10F);
                  
                  
                  setRotation(this.leftupperleg, 0F, 0F, 0.7853982F);
                  setRotation(this.rightupperleg, 0F, 0F, 0.7853982F);
                  
                  this.leftupperleg.setRotationPoint(3F, 4F, 8F);
                  this.rightupperleg.setRotationPoint(3F, 4F, -10F);
                  
        
              }
              else if (entity.walkAnimationStage == 1)
              {               
                  
                  this.leftupperleg.rotateAngleZ = 0.7853982F - (0.7853982F / 7) * entity.lLegMoveC;
                  this.leftlegnode.setRotationPoint(-6F + entity.lLegMoveC, 8F - (entity.lLegMoveC / vweight), 6F);
                  this.leftlowerleg.setRotationPoint(-2F + entity.lLegMoveC, 12F - (entity.lLegMoveC / vweight), 8F);
                  this.leftfoot.setRotationPoint(-2F + entity.lLegMoveC, 16F - (entity.lLegMoveC / vweight), 6F);
                  this.leftfootbase.setRotationPoint(-2F + entity.lLegMoveC, 20F - (entity.lLegMoveC / vweight), 5F);
                  this.lefttoe.setRotationPoint(7F + entity.lLegMoveC, 22F - (entity.lLegMoveC / vweight), 6F);
                  this.leftbackltoe.setRotationPoint(-6F + entity.lLegMoveC, 22F - (entity.lLegMoveC / vweight), 11F);
                  this.leftbackrtoe.setRotationPoint(-6F + entity.lLegMoveC, 22F - (entity.lLegMoveC / vweight), 3F);
                  this.leftheel.setRotationPoint(-4F + entity.lLegMoveC, 18F - (entity.lLegMoveC / vweight), 6F);
                  this.rightupperleg.rotateAngleZ = 0.7853982F - (0.7853982F / 7) * entity.rLegMoveC;
                  this.rightlegnode.setRotationPoint(-6F + entity.rLegMoveC, 8F, 6F - 18);
                  this.rightlowerleg.setRotationPoint(-2F + entity.rLegMoveC, 12F, 8F - 18);
                  this.rightfoot.setRotationPoint(-2F + entity.rLegMoveC, 16F, 6F - 18);
                  this.rightfootbase.setRotationPoint(-2F + entity.rLegMoveC, 20F, 5F - 18);
                  this.righttoe.setRotationPoint(7F + entity.rLegMoveC, 22F, -12F);
                  this.rightbackltoe.setRotationPoint(-6F + entity.rLegMoveC, 22F, -15F);
                  this.rightbackrtoe.setRotationPoint(-6F + entity.rLegMoveC, 22F, -7F);
                  this.rightheel.setRotationPoint(-4F + entity.rLegMoveC, 18F, 6F - 18);
                  
        
              }
              else if (entity.walkAnimationStage == 2)
              {
        
                  this.leftupperleg.rotateAngleZ = 0.7853982F - (0.7853982F / 7) * entity.lLegMoveC;
                  this.leftlegnode.setRotationPoint(-6F + entity.lLegMoveC, 8F - (5 / vweight) + (entity.lLegDropC / vweight), 6F);
                  this.leftlowerleg.setRotationPoint(-2F + entity.lLegMoveC, 12F - (5 / vweight) + (entity.lLegDropC / vweight), 8F);
                  this.leftfoot.setRotationPoint(-2F + entity.lLegMoveC, 16F - (5 / vweight) + (entity.lLegDropC / vweight), 6F);
                  this.leftfootbase.setRotationPoint(-2F + entity.lLegMoveC, 20F - (5 / vweight) + (entity.lLegDropC / vweight), 5F);
                  this.lefttoe.setRotationPoint(7F + entity.lLegMoveC, 22F - (5 / vweight) + (entity.lLegDropC / vweight), 6F);
                  this.leftbackltoe.setRotationPoint(-6F + entity.lLegMoveC, 22F - (5 / vweight) + (entity.lLegDropC / vweight), 11F);
                  this.leftbackrtoe.setRotationPoint(-6F + entity.lLegMoveC, 22F - (5 / vweight) + (entity.lLegDropC / vweight), 3F);
                  this.leftheel.setRotationPoint(-4F + entity.lLegMoveC, 18F - (5 / vweight) + (entity.lLegDropC / vweight), 6F);
        
              }
              else if (entity.walkAnimationStage == 3)
              {
                  
                  this.leftupperleg.rotateAngleZ = 0.7853982F - (0.7853982F / 7) * entity.lLegMoveC;
                  this.leftlegnode.setRotationPoint(-6F + entity.lLegMoveC, 8F, 6F);
                  this.leftlowerleg.setRotationPoint(-2F + entity.lLegMoveC, 12F, 8F);
                  this.leftfoot.setRotationPoint(-2F + entity.lLegMoveC, 16F, 6F);
                  this.leftfootbase.setRotationPoint(-2F + entity.lLegMoveC, 20F, 5F);
                  this.lefttoe.setRotationPoint(7F + entity.lLegMoveC, 22F, 6F);
                  this.leftbackltoe.setRotationPoint(-6F + entity.lLegMoveC, 22F, 11F);
                  this.leftbackrtoe.setRotationPoint(-6F + entity.lLegMoveC, 22F, 3F);
                  this.leftheel.setRotationPoint(-4F + entity.lLegMoveC, 18F, 6F);
                  this.rightupperleg.rotateAngleZ = 0.7853982F - (0.7853982F / 7) * entity.rLegMoveC;
                  this.rightlegnode.setRotationPoint(-6F + entity.rLegMoveC, 8F - (entity.rLegMoveC / vweight), 6F - 18);
                  this.rightlowerleg.setRotationPoint(-2F + entity.rLegMoveC, 12F - (entity.rLegMoveC / vweight), 8F - 18);
                  this.rightfoot.setRotationPoint(-2F + entity.rLegMoveC, 16F - (entity.rLegMoveC / vweight), 6F - 18);
                  this.rightfootbase.setRotationPoint(-2F + entity.rLegMoveC, 20F - (entity.rLegMoveC / vweight), 5F - 18);
                  this.righttoe.setRotationPoint(7F + entity.rLegMoveC, 22F - (entity.rLegMoveC / vweight), -12F);
                  this.rightbackltoe.setRotationPoint(-6F + entity.rLegMoveC, 22F - (entity.rLegMoveC / vweight), -15F);
                  this.rightbackrtoe.setRotationPoint(-6F + entity.rLegMoveC, 22F - (entity.rLegMoveC / vweight), -7F);
                  this.rightheel.setRotationPoint(-4F + entity.rLegMoveC, 18F - (entity.rLegMoveC / vweight), 6F - 18 );
              }
              
              else if (entity.walkAnimationStage == 4)
              {
                  
                  this.rightupperleg.rotateAngleZ = 0.7853982F - (0.7853982F / 7) * entity.rLegMoveC;
                  this.rightlegnode.setRotationPoint(-6F + entity.rLegMoveC, 8F - (5 / vweight) + (entity.rLegDropC / vweight), 6F - 18);
                  this.rightlowerleg.setRotationPoint(-2F + entity.rLegMoveC, 12F - (5 / vweight) + (entity.rLegDropC / vweight), 8F - 18);
                  this.rightfoot.setRotationPoint(-2F + entity.rLegMoveC, 16F - (5 / vweight) + (entity.rLegDropC / vweight), 6F - 18);
                  this.rightfootbase.setRotationPoint(-2F + entity.rLegMoveC, 20F - (5 / vweight) + (entity.rLegDropC / vweight), 5F - 18);
                  this.righttoe.setRotationPoint(7F + entity.rLegMoveC, 22F - (5 / vweight) + (entity.rLegDropC / vweight), -12F);
                  this.rightbackltoe.setRotationPoint(-6F + entity.rLegMoveC, 22F - (5 / vweight) + (entity.rLegDropC / vweight), -15F);
                  this.rightbackrtoe.setRotationPoint(-6F + entity.rLegMoveC, 22F - (5 / vweight) + (entity.rLegDropC / vweight), -7F);
                  this.rightheel.setRotationPoint(-4F + entity.rLegMoveC, 18F - (5 / vweight) + (entity.rLegDropC / vweight), -12F);
              }
          }
          else if (entity.isReversing)
          {
              if (entity.walkAnimationStage == 0)
              {
                  //Defaults
                  this.leftlegnode.setRotationPoint(-6F, 8F, 6F);
                  this.leftfoot.setRotationPoint(-2F, 16F, 6F);
                  this.leftfootbase.setRotationPoint(-2F, 20F, 5F);
                  this.lefttoe.setRotationPoint(7F, 22F, 6F);
                  this.leftbackltoe.setRotationPoint(-6F, 22F, 11F);
                  this.leftbackrtoe.setRotationPoint(-6F, 22F, 3F);
                  this.leftheel.setRotationPoint(-4F, 18F, 6F);
                  this.leftlowerleg.setRotationPoint(-2F, 12F, 8F);
                  
                  this.rightlegnode.setRotationPoint(-6F, 8F, -12F);
                  this.rightfoot.setRotationPoint(-2F, 16F, -12F);
                  this.rightfootbase.setRotationPoint(-2F, 20F, -13F);
                  this.righttoe.setRotationPoint(7F, 22F, -12F);
                  this.rightbackltoe.setRotationPoint(-6F, 22F, -15F);
                  this.rightbackrtoe.setRotationPoint(-6F, 22F, -7F);
                  this.rightheel.setRotationPoint(-4F, 18F, -12F);
                  this.rightlowerleg.setRotationPoint(-2F, 12F, -10F);
                  
                  
                  setRotation(this.leftupperleg, 0F, 0F, 0.7853982F);
                  setRotation(this.rightupperleg, 0F, 0F, 0.7853982F);
                  
                  this.leftupperleg.setRotationPoint(3F, 4F, 8F);
                  this.rightupperleg.setRotationPoint(3F, 4F, -10F);
                  
        
              }
              else if (entity.walkAnimationStage == 1)
              {
                  
                  this.leftupperleg.rotateAngleZ = 0.7853982F - (0.7853982F / 7) * entity.lLegMoveC;
                  this.leftlegnode.setRotationPoint(-6F + entity.lLegMoveC, 8F, 6F);
                  this.leftlowerleg.setRotationPoint(-2F + entity.lLegMoveC, 12F, 8F);
                  this.leftfoot.setRotationPoint(-2F + entity.lLegMoveC, 16F, 6F);
                  this.leftfootbase.setRotationPoint(-2F + entity.lLegMoveC, 20F, 5F);
                  this.lefttoe.setRotationPoint(7F + entity.lLegMoveC, 22F, 6F);
                  this.leftbackltoe.setRotationPoint(-6F + entity.lLegMoveC, 22F, 11F);
                  this.leftbackrtoe.setRotationPoint(-6F + entity.lLegMoveC, 22F, 3F);
                  this.leftheel.setRotationPoint(-4F + entity.lLegMoveC, 18F, 6F);
                  this.rightupperleg.rotateAngleZ = 0.7853982F - (0.7853982F / 7) * entity.rLegMoveC;
                  this.rightlegnode.setRotationPoint(-6F + entity.rLegMoveC, 8F, 6F - 18);
                  this.rightlowerleg.setRotationPoint(-2F + entity.rLegMoveC, 12F, 8F - 18);
                  this.rightfoot.setRotationPoint(-2F + entity.rLegMoveC, 16F, 6F - 18);
                  this.rightfootbase.setRotationPoint(-2F + entity.rLegMoveC, 20F, 5F - 18);
                  this.righttoe.setRotationPoint(7F + entity.rLegMoveC, 22F, -12F);
                  this.rightbackltoe.setRotationPoint(-6F + entity.rLegMoveC, 22F, -15F);
                  this.rightbackrtoe.setRotationPoint(-6F + entity.rLegMoveC, 22F, -7F);
                  this.rightheel.setRotationPoint(-4F + entity.rLegMoveC, 18F, 6F - 18);
                  
        
              }
              else if (entity.walkAnimationStage == 2)
              {
        
                  this.leftupperleg.rotateAngleZ = 0.7853982F - (0.7853982F / 7) * entity.lLegMoveC;
                  this.leftlegnode.setRotationPoint(-6F + entity.lLegMoveC, 8F, 6F);
                  this.leftlowerleg.setRotationPoint(-2F + entity.lLegMoveC, 12F, 8F);
                  this.leftfoot.setRotationPoint(-2F + entity.lLegMoveC, 16F, 6F);
                  this.leftfootbase.setRotationPoint(-2F + entity.lLegMoveC, 20F, 5F);
                  this.lefttoe.setRotationPoint(7F + entity.lLegMoveC, 22F, 6F);
                  this.leftbackltoe.setRotationPoint(-6F + entity.lLegMoveC, 22F, 11F);
                  this.leftbackrtoe.setRotationPoint(-6F + entity.lLegMoveC, 22F, 3F);
                  this.leftheel.setRotationPoint(-4F + entity.lLegMoveC, 18F, 6F);
        
              }
              else if (entity.walkAnimationStage == 3)
              {
                  
                  this.leftupperleg.rotateAngleZ = 0.7853982F - (0.7853982F / 7) * entity.lLegMoveC;
                  this.leftlegnode.setRotationPoint(-6F + entity.lLegMoveC, 8F, 6F);
                  this.leftlowerleg.setRotationPoint(-2F + entity.lLegMoveC, 12F, 8F);
                  this.leftfoot.setRotationPoint(-2F + entity.lLegMoveC, 16F, 6F);
                  this.leftfootbase.setRotationPoint(-2F + entity.lLegMoveC, 20F, 5F);
                  this.lefttoe.setRotationPoint(7F + entity.lLegMoveC, 22F, 6F);
                  this.leftbackltoe.setRotationPoint(-6F + entity.lLegMoveC, 22F, 11F);
                  this.leftbackrtoe.setRotationPoint(-6F + entity.lLegMoveC, 22F, 3F);
                  this.leftheel.setRotationPoint(-4F + entity.lLegMoveC, 18F, 6F);
                  this.rightupperleg.rotateAngleZ = 0.7853982F - (0.7853982F / 7) * entity.rLegMoveC;
                  this.rightlegnode.setRotationPoint(-6F + entity.rLegMoveC, 8F, 6F - 18);
                  this.rightlowerleg.setRotationPoint(-2F + entity.rLegMoveC, 12F, 8F - 18);
                  this.rightfoot.setRotationPoint(-2F + entity.rLegMoveC, 16F, 6F - 18);
                  this.rightfootbase.setRotationPoint(-2F + entity.rLegMoveC, 20F, 5F - 18);
                  this.righttoe.setRotationPoint(7F + entity.rLegMoveC, 22F, -12F);
                  this.rightbackltoe.setRotationPoint(-6F + entity.rLegMoveC, 22F, -15F);
                  this.rightbackrtoe.setRotationPoint(-6F + entity.rLegMoveC, 22F, -7F);
                  this.rightheel.setRotationPoint(-4F + entity.rLegMoveC, 18F, 6F - 18 );
              }
              
              else if (entity.walkAnimationStage == 4)
              {
                  
                  this.rightupperleg.rotateAngleZ = 0.7853982F - (0.7853982F / 7) * entity.rLegMoveC;
                  this.rightlegnode.setRotationPoint(-6F + entity.rLegMoveC, 8F, 6F - 18);
                  this.rightlowerleg.setRotationPoint(-2F + entity.rLegMoveC, 12F, 8F - 18);
                  this.rightfoot.setRotationPoint(-2F + entity.rLegMoveC, 16F, 6F - 18);
                  this.rightfootbase.setRotationPoint(-2F + entity.rLegMoveC, 20F, 5F - 18);
                  this.righttoe.setRotationPoint(7F + entity.rLegMoveC, 22F, -12F);
                  this.rightbackltoe.setRotationPoint(-6F + entity.rLegMoveC, 22F, -15F);
                  this.rightbackrtoe.setRotationPoint(-6F + entity.rLegMoveC, 22F, -7F);
                  this.rightheel.setRotationPoint(-4F + entity.rLegMoveC, 18F, -12F);
              }
              
          }
          else
          {
              //defaults
              this.leftlegnode.setRotationPoint(-6F, 8F, 6F);
              this.leftfoot.setRotationPoint(-2F, 16F, 6F);
              this.leftfootbase.setRotationPoint(-2F, 20F, 5F);
              this.lefttoe.setRotationPoint(7F, 22F, 6F);
              this.leftbackltoe.setRotationPoint(-6F, 22F, 11F);
              this.leftbackrtoe.setRotationPoint(-6F, 22F, 3F);
              this.leftheel.setRotationPoint(-4F, 18F, 6F);
              this.leftlowerleg.setRotationPoint(-2F, 12F, 8F);
              
              this.rightlegnode.setRotationPoint(-6F, 8F, -12F);
              this.rightfoot.setRotationPoint(-2F, 16F, -12F);
              this.rightfootbase.setRotationPoint(-2F, 20F, -13F);
              this.righttoe.setRotationPoint(7F, 22F, -12F);
              this.rightbackltoe.setRotationPoint(-6F, 22F, -15F);
              this.rightbackrtoe.setRotationPoint(-6F, 22F, -7F);
              this.rightheel.setRotationPoint(-4F, 18F, -12F);
              this.rightlowerleg.setRotationPoint(-2F, 12F, -10F);
              
              
              setRotation(this.leftupperleg, 0F, 0F, 0.7853982F);
              setRotation(this.rightupperleg, 0F, 0F, 0.7853982F);
              
              this.leftupperleg.setRotationPoint(3F, 4F, 8F);
              this.rightupperleg.setRotationPoint(3F, 4F, -10F);

          }
      }
      else
      {
          //defaults
          this.leftlegnode.setRotationPoint(-6F, 8F, 6F);
          this.leftfoot.setRotationPoint(-2F, 16F, 6F);
          this.leftfootbase.setRotationPoint(-2F, 20F, 5F);
          this.lefttoe.setRotationPoint(7F, 22F, 6F);
          this.leftbackltoe.setRotationPoint(-6F, 22F, 11F);
          this.leftbackrtoe.setRotationPoint(-6F, 22F, 3F);
          this.leftheel.setRotationPoint(-4F, 18F, 6F);
          this.leftlowerleg.setRotationPoint(-2F, 12F, 8F);
          
          this.rightlegnode.setRotationPoint(-6F, 8F, -12F);
          this.rightfoot.setRotationPoint(-2F, 16F, -12F);
          this.rightfootbase.setRotationPoint(-2F, 20F, -13F);
          this.righttoe.setRotationPoint(7F, 22F, -12F);
          this.rightbackltoe.setRotationPoint(-6F, 22F, -15F);
          this.rightbackrtoe.setRotationPoint(-6F, 22F, -7F);
          this.rightheel.setRotationPoint(-4F, 18F, -12F);
          this.rightlowerleg.setRotationPoint(-2F, 12F, -10F);
          
          
          setRotation(this.leftupperleg, 0F, 0F, 0.7853982F);
          setRotation(this.rightupperleg, 0F, 0F, 0.7853982F);
          
          this.leftupperleg.setRotationPoint(3F, 4F, 8F);
          this.rightupperleg.setRotationPoint(3F, 4F, -10F);
      }

      
  }

}

package digidigi.mtm.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import digidigi.mtm.common.EntityTA;


@SideOnly(Side.CLIENT)
public class ModelTA extends ModelBase
{
    public ModelRenderer btopwheel;
    public ModelRenderer topmidwheel;
    public ModelRenderer botmidwheel;
    public ModelRenderer topfwheel;
    public ModelRenderer topbwheel;
    public ModelRenderer botfwheel;
    public ModelRenderer botbwheel;
    public ModelRenderer bottom;
    public ModelRenderer rightwheelside;
    public ModelRenderer leftdrillplatebot;
    public ModelRenderer topwheel;
    public ModelRenderer rightwirecoverl;
    public ModelRenderer controlR;
    public ModelRenderer leftfwheelside;
    public ModelRenderer leftbwheelside;
    public ModelRenderer topfwheelcover;
    public ModelRenderer rightwirecoverb;
    public ModelRenderer rightwirecoverr;
    public ModelRenderer rightwheelcover;
    public ModelRenderer leftwirecoverr;
    public ModelRenderer leftwirecoverl;
    public ModelRenderer leftwirecoverb;
    public ModelRenderer leftwirecovert;
    public ModelRenderer leftwheelcover;
    public ModelRenderer rightwirecovert;
    public ModelRenderer leftwiref;
    public ModelRenderer leftwiret;
    public ModelRenderer leftlowernode;
    public ModelRenderer bleftwirecoverf;
    public ModelRenderer bleftwirecoverb;
    public ModelRenderer bleftwirecovertop;
    public ModelRenderer rightwiref;
    public ModelRenderer seatleft;
    public ModelRenderer brightwirecoverb;
    public ModelRenderer leftuppernode;
    public ModelRenderer brightwirecoverf;
    public ModelRenderer brightwirecoverbot;
    public ModelRenderer rightwireb;
    public ModelRenderer brightwirecovertop;
    public ModelRenderer rightupperarm;
    public ModelRenderer back;
    public ModelRenderer rightuppernode;
    public ModelRenderer rightwiret;
    public ModelRenderer leftwireb;
    public ModelRenderer leftwirenub;
    public ModelRenderer rightfwheelside;
    public ModelRenderer leftdrillbase;
    public ModelRenderer leftdrillbotbitpoint;
    public ModelRenderer leftdrillplatetop;
    public ModelRenderer leftdrillbotbit;
    public ModelRenderer leftdrillleftbit;
    public ModelRenderer leftdrillleftbitpoint;
    public ModelRenderer leftdrillbaseleft;
    public ModelRenderer leftdrillplate;
    public ModelRenderer leftdrillbaseright;
    public ModelRenderer leftdrilltopbit;
    public ModelRenderer leftdrilltopbitpoint;
    public ModelRenderer leftdrillrightbit;
    public ModelRenderer leftdrillrightbitpoint;
    public ModelRenderer leftpipe3;
    public ModelRenderer rightdrilltopbit;
    public ModelRenderer rightdrilltopbitpoint;
    public ModelRenderer rightdrillleftbit;
    public ModelRenderer rightdrillleftbitpoint;
    public ModelRenderer rightdrillrightbit;
    public ModelRenderer rightdrillrightbitpoint;
    public ModelRenderer rightdrillbotbit;
    public ModelRenderer rightdrillbotbitpoint;
    public ModelRenderer rightdrillplatebot;
    public ModelRenderer rightdrillplatetop;
    public ModelRenderer rightdrillplate;
    public ModelRenderer rightdrillbaseright;
    public ModelRenderer rightdrillbaseleft;
    public ModelRenderer midwheel;
    public ModelRenderer botwheel;
    public ModelRenderer bbotwheel;
    public ModelRenderer leftpipebrace;
    public ModelRenderer bmidwheel;
    public ModelRenderer rightdrillbase;
    public ModelRenderer rightnodestack;
    public ModelRenderer leftpipe1;
    public ModelRenderer leftpipe2;
    public ModelRenderer rightwirenub;
    public ModelRenderer rightlowerarm;
    public ModelRenderer rightlowernode;
    public ModelRenderer rightmidnode;
    public ModelRenderer topwheelcover;
    public ModelRenderer rightpipebrace;
    public ModelRenderer rightpipe1;
    public ModelRenderer rightpipe2;
    public ModelRenderer rightpipe3;
    public ModelRenderer leftlowerarm;
    public ModelRenderer leftmidnode;
    public ModelRenderer rightnodestackb;
    public ModelRenderer leftupperarm;
    public ModelRenderer leftnodestack;
    public ModelRenderer rightbackstack;
    public ModelRenderer leftnodestackb;
    public ModelRenderer leftbackstack;
    public ModelRenderer bleftwirecoverbot;
    public ModelRenderer seatfloor;
    public ModelRenderer seatright;
    public ModelRenderer control;
    public ModelRenderer controlL;
   
  public ModelTA()
  {
      textureWidth = 128;
      textureHeight = 128;

      btopwheel = new ModelRenderer(this, 0, 20);
      btopwheel.addBox(0F, 0F, 0F, 10, 2, 10);
      btopwheel.setRotationPoint(-5F, 10F, 2F);
      btopwheel.setTextureSize(128, 128);
      btopwheel.mirror = true;
      setRotation(btopwheel, 0F, 0F, 0F);
      topmidwheel = new ModelRenderer(this, 72, 28);
      topmidwheel.addBox(0F, 0F, 0F, 10, 2, 18);
      topmidwheel.setRotationPoint(-5F, 7F, -30F);
      topmidwheel.setTextureSize(128, 128);
      topmidwheel.mirror = true;
      setRotation(topmidwheel, 0F, 0F, 0F);
      botmidwheel = new ModelRenderer(this, 72, 28);
      botmidwheel.addBox(0F, 0F, 0F, 10, 2, 18);
      botmidwheel.setRotationPoint(-5F, 19F, -30F);
      botmidwheel.setTextureSize(128, 128);
      botmidwheel.mirror = true;
      setRotation(botmidwheel, 0F, 0F, 0F);
      topfwheel = new ModelRenderer(this, 58, 26);
      topfwheel.addBox(0F, 0F, 0F, 10, 2, 2);
      topfwheel.setRotationPoint(-5F, 5F, -28F);
      topfwheel.setTextureSize(128, 128);
      topfwheel.mirror = true;
      setRotation(topfwheel, 0F, 0F, 0F);
      topbwheel = new ModelRenderer(this, 58, 26);
      topbwheel.addBox(0F, 0F, 0F, 10, 2, 2);
      topbwheel.setRotationPoint(-5F, 5F, -16F);
      topbwheel.setTextureSize(128, 128);
      topbwheel.mirror = true;
      setRotation(topbwheel, 0F, 0F, 0F);
      botfwheel = new ModelRenderer(this, 58, 26);
      botfwheel.addBox(0F, 0F, 0F, 10, 2, 2);
      botfwheel.setRotationPoint(-5F, 21F, -28F);
      botfwheel.setTextureSize(128, 128);
      botfwheel.mirror = true;
      setRotation(botfwheel, 0F, 0F, 0F);
      botbwheel = new ModelRenderer(this, 58, 26);
      botbwheel.addBox(0F, 0F, 0F, 10, 2, 2);
      botbwheel.setRotationPoint(-5F, 21F, -16F);
      botbwheel.setTextureSize(128, 128);
      botbwheel.mirror = true;
      setRotation(botbwheel, 0F, 0F, 0F);
      bottom = new ModelRenderer(this, 70, 76);
      bottom.addBox(0F, 0F, 0F, 18, 9, 11);
      bottom.setRotationPoint(-9F, 10F, -11F);
      bottom.setTextureSize(128, 128);
      bottom.mirror = true;
      setRotation(bottom, 0F, 0F, 0F);
      rightwheelside = new ModelRenderer(this, 92, 48);
      rightwheelside.addBox(0F, 0F, 0F, 4, 10, 14);
      rightwheelside.setRotationPoint(-9F, 9F, -25F);
      rightwheelside.setTextureSize(128, 128);
      rightwheelside.mirror = true;
      setRotation(rightwheelside, 0F, 0F, 0F);
      leftdrillplatebot = new ModelRenderer(this, 64, 0);
      leftdrillplatebot.addBox(0F, 0F, 0F, 8, 2, 3);
      leftdrillplatebot.setRotationPoint(12F, 18F, -25F);
      leftdrillplatebot.setTextureSize(128, 128);
      leftdrillplatebot.mirror = true;
      setRotation(leftdrillplatebot, 0F, 0F, 0F);
      topwheel = new ModelRenderer(this, 34, 55);
      topwheel.addBox(0F, -1F, 0F, 10, 3, 10);
      topwheel.setRotationPoint(-5F, 5F, -26F);
      topwheel.setTextureSize(128, 128);
      topwheel.mirror = true;
      setRotation(topwheel, 0F, 0F, 0F);
      rightwirecoverl = new ModelRenderer(this, 69, 76);
      rightwirecoverl.addBox(0F, 0F, 0F, 1, 3, 5);
      rightwirecoverl.setRotationPoint(-8F, 5F, -16F);
      rightwirecoverl.setTextureSize(128, 128);
      rightwirecoverl.mirror = true;
      setRotation(rightwirecoverl, 0F, 0F, 0F);
      controlR = new ModelRenderer(this, 0, 9);
      controlR.addBox(0F, 0F, 0F, 2, 4, 2);
      controlR.setRotationPoint(-6F, -3F, -11F);
      controlR.setTextureSize(128, 128);
      controlR.mirror = true;
      setRotation(controlR, 0F, 0F, 0F);
      leftfwheelside = new ModelRenderer(this, 0, 83);
      leftfwheelside.addBox(0F, 0F, 0F, 4, 6, 3);
      leftfwheelside.setRotationPoint(5F, 11F, -29F);
      leftfwheelside.setTextureSize(128, 128);
      leftfwheelside.mirror = true;
      setRotation(leftfwheelside, 0F, 0F, 0F);
      leftbwheelside = new ModelRenderer(this, 92, 48);
      leftbwheelside.addBox(0F, 0F, 0F, 4, 10, 14);
      leftbwheelside.setRotationPoint(5F, 9F, -26F);
      leftbwheelside.setTextureSize(128, 128);
      leftbwheelside.mirror = true;
      setRotation(leftbwheelside, 0F, 0F, 0F);
      topfwheelcover = new ModelRenderer(this, 62, 5);
      topfwheelcover.addBox(0F, 0F, 0F, 10, 2, 2);
      topfwheelcover.setRotationPoint(-5F, 2F, -28F);
      topfwheelcover.setTextureSize(128, 128);
      topfwheelcover.mirror = true;
      setRotation(topfwheelcover, 0F, 0F, 0F);
      rightwirecoverb = new ModelRenderer(this, 108, 110);
      rightwirecoverb.addBox(0F, 0F, 0F, 5, 1, 5);
      rightwirecoverb.setRotationPoint(-12F, 8F, -16F);
      rightwirecoverb.setTextureSize(128, 128);
      rightwirecoverb.mirror = true;
      setRotation(rightwirecoverb, 0F, 0F, 0F);
      rightwirecoverr = new ModelRenderer(this, 69, 76);
      rightwirecoverr.addBox(0F, 0F, 0F, 1, 3, 5);
      rightwirecoverr.setRotationPoint(-12F, 5F, -16F);
      rightwirecoverr.setTextureSize(128, 128);
      rightwirecoverr.mirror = true;
      setRotation(rightwirecoverr, 0F, 0F, 0F);
      rightwheelcover = new ModelRenderer(this, 36, 70);
      rightwheelcover.addBox(0F, 0F, 0F, 2, 7, 15);
      rightwheelcover.setRotationPoint(-7F, 2F, -26F);
      rightwheelcover.setTextureSize(128, 128);
      rightwheelcover.mirror = true;
      setRotation(rightwheelcover, 0F, 0F, 0F);
      leftwirecoverr = new ModelRenderer(this, 69, 76);
      leftwirecoverr.addBox(0F, 0F, 0F, 1, 3, 5);
      leftwirecoverr.setRotationPoint(7F, 5F, -16F);
      leftwirecoverr.setTextureSize(128, 128);
      leftwirecoverr.mirror = true;
      setRotation(leftwirecoverr, 0F, 0F, 0F);
      leftwirecoverl = new ModelRenderer(this, 69, 76);
      leftwirecoverl.addBox(0F, 0F, 0F, 1, 3, 5);
      leftwirecoverl.setRotationPoint(11F, 5F, -16F);
      leftwirecoverl.setTextureSize(128, 128);
      leftwirecoverl.mirror = true;
      setRotation(leftwirecoverl, 0F, 0F, 0F);
      leftwirecoverb = new ModelRenderer(this, 108, 110);
      leftwirecoverb.addBox(0F, 0F, 0F, 5, 1, 5);
      leftwirecoverb.setRotationPoint(7F, 8F, -16F);
      leftwirecoverb.setTextureSize(128, 128);
      leftwirecoverb.mirror = true;
      setRotation(leftwirecoverb, 0F, 0F, 0F);
      leftwirecovert = new ModelRenderer(this, 108, 110);
      leftwirecovert.addBox(0F, 0F, 0F, 5, 1, 5);
      leftwirecovert.setRotationPoint(7F, 4F, -16F);
      leftwirecovert.setTextureSize(128, 128);
      leftwirecovert.mirror = true;
      setRotation(leftwirecovert, 0F, 0F, 0F);
      leftwheelcover = new ModelRenderer(this, 36, 70);
      leftwheelcover.addBox(0F, 0F, 0F, 2, 7, 15);
      leftwheelcover.setRotationPoint(5F, 2F, -26F);
      leftwheelcover.setTextureSize(128, 128);
      leftwheelcover.mirror = true;
      setRotation(leftwheelcover, 0F, 0F, 0F);
      rightwirecovert = new ModelRenderer(this, 108, 110);
      rightwirecovert.addBox(0F, 0F, 0F, 5, 1, 5);
      rightwirecovert.setRotationPoint(-12F, 4F, -16F);
      rightwirecovert.setTextureSize(128, 128);
      rightwirecovert.mirror = true;
      setRotation(rightwirecovert, 0F, 0F, 0F);
      leftwiref = new ModelRenderer(this, 30, 18);
      leftwiref.addBox(0F, 0F, 0F, 3, 3, 9);
      leftwiref.setRotationPoint(8F, 5F, -17F);
      leftwiref.setTextureSize(128, 128);
      leftwiref.mirror = true;
      setRotation(leftwiref, 0F, 0F, 0F);
      leftwiret = new ModelRenderer(this, 98, 13);
      leftwiret.addBox(0F, 0F, 0F, 3, 3, 12);
      leftwiret.setRotationPoint(8F, 2F, -11F);
      leftwiret.setTextureSize(128, 128);
      leftwiret.mirror = true;
      setRotation(leftwiret, 0F, 0F, 0F);
      leftlowernode = new ModelRenderer(this, 86, 17);
      leftlowernode.addBox(0F, 0F, 0F, 8, 4, 4);
      leftlowernode.setRotationPoint(12F, 12F, -2F);
      leftlowernode.setTextureSize(128, 128);
      leftlowernode.mirror = true;
      setRotation(leftlowernode, 0F, 0F, 0F);
      bleftwirecoverf = new ModelRenderer(this, 0, 20);
      bleftwirecoverf.addBox(0F, 0F, 0F, 4, 3, 1);
      bleftwirecoverf.setRotationPoint(8F, 5F, 1F);
      bleftwirecoverf.setTextureSize(128, 128);
      bleftwirecoverf.mirror = true;
      setRotation(bleftwirecoverf, 0F, 0F, 0F);
      bleftwirecoverb = new ModelRenderer(this, 0, 20);
      bleftwirecoverb.addBox(0F, 0F, 0F, 4, 3, 1);
      bleftwirecoverb.setRotationPoint(8F, 5F, 5F);
      bleftwirecoverb.setTextureSize(128, 128);
      bleftwirecoverb.mirror = true;
      setRotation(bleftwirecoverb, 0F, 0F, 0F);
      bleftwirecovertop = new ModelRenderer(this, 110, 28);
      bleftwirecovertop.addBox(0F, 0F, 0F, 4, 1, 5);
      bleftwirecovertop.setRotationPoint(8F, 4F, 1F);
      bleftwirecovertop.setTextureSize(128, 128);
      bleftwirecovertop.mirror = true;
      setRotation(bleftwirecovertop, 0F, 0F, 0F);
      rightwiref = new ModelRenderer(this, 30, 18);
      rightwiref.addBox(0F, 0F, 0F, 3, 3, 9);
      rightwiref.setRotationPoint(-11F, 5F, -17F);
      rightwiref.setTextureSize(128, 128);
      rightwiref.mirror = true;
      setRotation(rightwiref, 0F, 0F, 0F);
      seatleft = new ModelRenderer(this, 114, 48);
      seatleft.addBox(0F, 0F, 0F, 1, 3, 6);
      seatleft.setRotationPoint(7F, 2F, 0F);
      seatleft.setTextureSize(128, 128);
      seatleft.mirror = true;
      setRotation(seatleft, 0F, 0F, 0F);
      brightwirecoverb = new ModelRenderer(this, 0, 20);
      brightwirecoverb.addBox(0F, 0F, 0F, 4, 3, 1);
      brightwirecoverb.setRotationPoint(-12F, 5F, 5F);
      brightwirecoverb.setTextureSize(128, 128);
      brightwirecoverb.mirror = true;
      setRotation(brightwirecoverb, 0F, 0F, 0F);
      leftuppernode = new ModelRenderer(this, 51, 30);
      leftuppernode.addBox(0F, 0F, 0F, 8, 8, 8);
      leftuppernode.setRotationPoint(8F, -4F, 1F);
      leftuppernode.setTextureSize(128, 128);
      leftuppernode.mirror = true;
      setRotation(leftuppernode, 0F, 0F, 0F);
      brightwirecoverf = new ModelRenderer(this, 0, 20);
      brightwirecoverf.addBox(0F, 0F, 0F, 4, 3, 1);
      brightwirecoverf.setRotationPoint(-12F, 5F, 1F);
      brightwirecoverf.setTextureSize(128, 128);
      brightwirecoverf.mirror = true;
      setRotation(brightwirecoverf, 0F, 0F, 0F);
      brightwirecoverbot = new ModelRenderer(this, 110, 28);
      brightwirecoverbot.addBox(0F, 0F, 0F, 4, 1, 5);
      brightwirecoverbot.setRotationPoint(-12F, 8F, 1F);
      brightwirecoverbot.setTextureSize(128, 128);
      brightwirecoverbot.mirror = true;
      setRotation(brightwirecoverbot, 0F, 0F, 0F);
      rightwireb = new ModelRenderer(this, 79, 66);
      rightwireb.addBox(0F, 0F, 0F, 3, 3, 3);
      rightwireb.setRotationPoint(-11F, 5F, -2F);
      rightwireb.setTextureSize(128, 128);
      rightwireb.mirror = true;
      setRotation(rightwireb, 0F, 0F, 0F);
      brightwirecovertop = new ModelRenderer(this, 110, 28);
      brightwirecovertop.addBox(0F, 0F, 0F, 4, 1, 5);
      brightwirecovertop.setRotationPoint(-12F, 4F, 1F);
      brightwirecovertop.setTextureSize(128, 128);
      brightwirecovertop.mirror = true;
      setRotation(brightwirecovertop, 0F, 0F, 0F);
      rightupperarm = new ModelRenderer(this, 116, 13);
      rightupperarm.addBox(0F, 0F, 0F, 2, 10, 2);
      rightupperarm.setRotationPoint(-14F, -1F, 6F);
      rightupperarm.setTextureSize(128, 128);
      rightupperarm.mirror = true;
      setRotation(rightupperarm, 0.6108652F, 0F, 0.3490659F);
      back = new ModelRenderer(this, 42, 96);
      back.addBox(0F, 0F, 0F, 16, 12, 8);
      back.setRotationPoint(-8F, -2F, 6F);
      back.setTextureSize(128, 128);
      back.mirror = true;
      setRotation(back, 0F, 0F, 0F);
      rightuppernode = new ModelRenderer(this, 51, 30);
      rightuppernode.addBox(0F, 0F, 0F, 8, 8, 8);
      rightuppernode.setRotationPoint(-16F, -4F, 1F);
      rightuppernode.setTextureSize(128, 128);
      rightuppernode.mirror = true;
      setRotation(rightuppernode, 0F, 0F, 0F);
      rightwiret = new ModelRenderer(this, 98, 13);
      rightwiret.addBox(0F, 0F, 0F, 3, 3, 12);
      rightwiret.setRotationPoint(-11F, 2F, -11F);
      rightwiret.setTextureSize(128, 128);
      rightwiret.mirror = true;
      setRotation(rightwiret, 0F, 0F, 0F);
      leftwireb = new ModelRenderer(this, 79, 66);
      leftwireb.addBox(0F, 0F, 0F, 3, 3, 3);
      leftwireb.setRotationPoint(8F, 5F, -2F);
      leftwireb.setTextureSize(128, 128);
      leftwireb.mirror = true;
      setRotation(leftwireb, 0F, 0F, 0F);
      leftwirenub = new ModelRenderer(this, 55, 76);
      leftwirenub.addBox(0F, 0F, 0F, 3, 3, 3);
      leftwirenub.setRotationPoint(10F, 5F, 2F);
      leftwirenub.setTextureSize(128, 128);
      leftwirenub.mirror = true;
      setRotation(leftwirenub, 0F, 0F, 0F);
      rightfwheelside = new ModelRenderer(this, 0, 83);
      rightfwheelside.addBox(0F, 0F, 0F, 4, 6, 3);
      rightfwheelside.setRotationPoint(-9F, 11F, -28F);
      rightfwheelside.setTextureSize(128, 128);
      rightfwheelside.mirror = true;
      setRotation(rightfwheelside, 0F, 0F, 0F);
      leftdrillbase = new ModelRenderer(this, 71, 96);
      leftdrillbase.addBox(0F, 0F, 0F, 8, 12, 20);
      leftdrillbase.setRotationPoint(12F, 8F, -22F);
      leftdrillbase.setTextureSize(128, 128);
      leftdrillbase.mirror = true;
      setRotation(leftdrillbase, 0F, 0F, 0F);
      leftdrillbotbitpoint = new ModelRenderer(this, 36, 78);
      leftdrillbotbitpoint.addBox(0F, 0F, 0F, 2, 1, 3);
      leftdrillbotbitpoint.setRotationPoint(15F, 18F, -31F);
      leftdrillbotbitpoint.setTextureSize(128, 128);
      leftdrillbotbitpoint.mirror = true;
      setRotation(leftdrillbotbitpoint, 0F, 0F, 0F);
      leftdrillplatetop = new ModelRenderer(this, 64, 0);
      leftdrillplatetop.addBox(0F, 0F, 0F, 8, 2, 3);
      leftdrillplatetop.setRotationPoint(12F, 8F, -25F);
      leftdrillplatetop.setTextureSize(128, 128);
      leftdrillplatetop.mirror = true;
      setRotation(leftdrillplatetop, 0F, 0F, 0F);
      leftdrillbotbit = new ModelRenderer(this, 0, 54);
      leftdrillbotbit.addBox(0F, 0F, 0F, 4, 2, 3);
      leftdrillbotbit.setRotationPoint(14F, 18F, -28F);
      leftdrillbotbit.setTextureSize(128, 128);
      leftdrillbotbit.mirror = true;
      setRotation(leftdrillbotbit, 0F, 0F, 0F);
      leftdrillleftbit = new ModelRenderer(this, 0, 59);
      leftdrillleftbit.addBox(0F, 0F, 0F, 2, 4, 3);
      leftdrillleftbit.setRotationPoint(20F, 12F, -28F);
      leftdrillleftbit.setTextureSize(128, 128);
      leftdrillleftbit.mirror = true;
      setRotation(leftdrillleftbit, 0F, 0F, 0F);
      leftdrillleftbitpoint = new ModelRenderer(this, 40, 98);
      leftdrillleftbitpoint.addBox(0F, 0F, 0F, 1, 2, 3);
      leftdrillleftbitpoint.setRotationPoint(20F, 13F, -31F);
      leftdrillleftbitpoint.setTextureSize(128, 128);
      leftdrillleftbitpoint.mirror = true;
      setRotation(leftdrillleftbitpoint, 0F, 0F, 0F);
      leftdrillbaseleft = new ModelRenderer(this, 55, 52);
      leftdrillbaseleft.addBox(0F, 0F, 0F, 2, 4, 20);
      leftdrillbaseleft.setRotationPoint(20F, 12F, -22F);
      leftdrillbaseleft.setTextureSize(128, 128);
      leftdrillbaseleft.mirror = true;
      setRotation(leftdrillbaseleft, 0F, 0F, 0F);
      leftdrillplate = new ModelRenderer(this, 98, 2);
      leftdrillplate.addBox(0F, 0F, 0F, 12, 8, 3);
      leftdrillplate.setRotationPoint(10F, 10F, -25F);
      leftdrillplate.setTextureSize(128, 128);
      leftdrillplate.mirror = true;
      setRotation(leftdrillplate, 0F, 0F, 0F);
      leftdrillbaseright = new ModelRenderer(this, 55, 52);
      leftdrillbaseright.addBox(0F, 0F, 0F, 2, 4, 20);
      leftdrillbaseright.setRotationPoint(10F, 12F, -22F);
      leftdrillbaseright.setTextureSize(128, 128);
      leftdrillbaseright.mirror = true;
      setRotation(leftdrillbaseright, 0F, 0F, 0F);
      leftdrilltopbit = new ModelRenderer(this, 0, 54);
      leftdrilltopbit.addBox(0F, 0F, 0F, 4, 2, 3);
      leftdrilltopbit.setRotationPoint(14F, 8F, -28F);
      leftdrilltopbit.setTextureSize(128, 128);
      leftdrilltopbit.mirror = true;
      setRotation(leftdrilltopbit, 0F, 0F, 0F);
      leftdrilltopbitpoint = new ModelRenderer(this, 36, 78);
      leftdrilltopbitpoint.addBox(0F, 0F, 0F, 2, 1, 3);
      leftdrilltopbitpoint.setRotationPoint(15F, 9F, -31F);
      leftdrilltopbitpoint.setTextureSize(128, 128);
      leftdrilltopbitpoint.mirror = true;
      setRotation(leftdrilltopbitpoint, 0F, 0F, 0F);
      leftdrillrightbit = new ModelRenderer(this, 0, 59);
      leftdrillrightbit.addBox(0F, 0F, 0F, 2, 4, 3);
      leftdrillrightbit.setRotationPoint(10F, 12F, -28F);
      leftdrillrightbit.setTextureSize(128, 128);
      leftdrillrightbit.mirror = true;
      setRotation(leftdrillrightbit, 0F, 0F, 0F);
      leftdrillrightbitpoint = new ModelRenderer(this, 40, 98);
      leftdrillrightbitpoint.addBox(0F, 0F, 0F, 1, 2, 3);
      leftdrillrightbitpoint.setRotationPoint(11F, 13F, -31F);
      leftdrillrightbitpoint.setTextureSize(128, 128);
      leftdrillrightbitpoint.mirror = true;
      setRotation(leftdrillrightbitpoint, 0F, 0F, 0F);
      leftpipe3 = new ModelRenderer(this, 58, 20);
      leftpipe3.addBox(0F, 0F, 0F, 8, 3, 3);
      leftpipe3.setRotationPoint(11F, 16F, 8F);
      leftpipe3.setTextureSize(128, 128);
      leftpipe3.mirror = true;
      setRotation(leftpipe3, 0F, 0F, 0.122173F);
      rightdrilltopbit = new ModelRenderer(this, 0, 54);
      rightdrilltopbit.addBox(0F, 0F, 0F, 4, 2, 3);
      rightdrilltopbit.setRotationPoint(-18F, 8F, -28F);
      rightdrilltopbit.setTextureSize(128, 128);
      rightdrilltopbit.mirror = true;
      setRotation(rightdrilltopbit, 0F, 0F, 0F);
      rightdrilltopbitpoint = new ModelRenderer(this, 36, 78);
      rightdrilltopbitpoint.addBox(0F, 0F, 0F, 2, 1, 3);
      rightdrilltopbitpoint.setRotationPoint(-17F, 9F, -31F);
      rightdrilltopbitpoint.setTextureSize(128, 128);
      rightdrilltopbitpoint.mirror = true;
      setRotation(rightdrilltopbitpoint, 0F, 0F, 0F);
      rightdrillleftbit = new ModelRenderer(this, 0, 59);
      rightdrillleftbit.addBox(0F, 0F, 0F, 2, 4, 3);
      rightdrillleftbit.setRotationPoint(-12F, 12F, -28F);
      rightdrillleftbit.setTextureSize(128, 128);
      rightdrillleftbit.mirror = true;
      setRotation(rightdrillleftbit, 0F, 0F, 0F);
      rightdrillleftbitpoint = new ModelRenderer(this, 40, 98);
      rightdrillleftbitpoint.addBox(0F, 0F, 0F, 1, 2, 3);
      rightdrillleftbitpoint.setRotationPoint(-12F, 13F, -31F);
      rightdrillleftbitpoint.setTextureSize(128, 128);
      rightdrillleftbitpoint.mirror = true;
      setRotation(rightdrillleftbitpoint, 0F, 0F, 0F);
      rightdrillrightbit = new ModelRenderer(this, 0, 59);
      rightdrillrightbit.addBox(0F, 0F, 0F, 2, 4, 3);
      rightdrillrightbit.setRotationPoint(-22F, 12F, -28F);
      rightdrillrightbit.setTextureSize(128, 128);
      rightdrillrightbit.mirror = true;
      setRotation(rightdrillrightbit, 0F, 0F, 0F);
      rightdrillrightbitpoint = new ModelRenderer(this, 40, 98);
      rightdrillrightbitpoint.addBox(0F, 0F, 0F, 1, 2, 3);
      rightdrillrightbitpoint.setRotationPoint(-21F, 13F, -31F);
      rightdrillrightbitpoint.setTextureSize(128, 128);
      rightdrillrightbitpoint.mirror = true;
      setRotation(rightdrillrightbitpoint, 0F, 0F, 0F);
      rightdrillbotbit = new ModelRenderer(this, 0, 54);
      rightdrillbotbit.addBox(0F, 0F, 0F, 4, 2, 3);
      rightdrillbotbit.setRotationPoint(-18F, 18F, -28F);
      rightdrillbotbit.setTextureSize(128, 128);
      rightdrillbotbit.mirror = true;
      setRotation(rightdrillbotbit, 0F, 0F, 0F);
      rightdrillbotbitpoint = new ModelRenderer(this, 36, 78);
      rightdrillbotbitpoint.addBox(0F, 0F, 0F, 2, 1, 3);
      rightdrillbotbitpoint.setRotationPoint(-17F, 18F, -31F);
      rightdrillbotbitpoint.setTextureSize(128, 128);
      rightdrillbotbitpoint.mirror = true;
      setRotation(rightdrillbotbitpoint, 0F, 0F, 0F);
      rightdrillplatebot = new ModelRenderer(this, 64, 0);
      rightdrillplatebot.addBox(0F, 0F, 0F, 8, 2, 3);
      rightdrillplatebot.setRotationPoint(-20F, 18F, -25F);
      rightdrillplatebot.setTextureSize(128, 128);
      rightdrillplatebot.mirror = true;
      setRotation(rightdrillplatebot, 0F, 0F, 0F);
      rightdrillplatetop = new ModelRenderer(this, 64, 0);
      rightdrillplatetop.addBox(0F, 0F, 0F, 8, 2, 3);
      rightdrillplatetop.setRotationPoint(-20F, 8F, -25F);
      rightdrillplatetop.setTextureSize(128, 128);
      rightdrillplatetop.mirror = true;
      setRotation(rightdrillplatetop, 0F, 0F, 0F);
      rightdrillplate = new ModelRenderer(this, 98, 2);
      rightdrillplate.addBox(0F, 0F, 0F, 12, 8, 3);
      rightdrillplate.setRotationPoint(-22F, 10F, -25F);
      rightdrillplate.setTextureSize(128, 128);
      rightdrillplate.mirror = true;
      setRotation(rightdrillplate, 0F, 0F, 0F);
      rightdrillbaseright = new ModelRenderer(this, 55, 52);
      rightdrillbaseright.addBox(0F, 0F, 0F, 2, 4, 20);
      rightdrillbaseright.setRotationPoint(-22F, 12F, -22F);
      rightdrillbaseright.setTextureSize(128, 128);
      rightdrillbaseright.mirror = true;
      setRotation(rightdrillbaseright, 0F, 0F, 0F);
      rightdrillbaseleft = new ModelRenderer(this, 55, 52);
      rightdrillbaseleft.addBox(0F, 0F, 0F, 2, 4, 20);
      rightdrillbaseleft.setRotationPoint(-12F, 12F, -22F);
      rightdrillbaseleft.setTextureSize(128, 128);
      rightdrillbaseleft.mirror = true;
      setRotation(rightdrillbaseleft, 0F, 0F, 0F);
      midwheel = new ModelRenderer(this, 0, 98);
      midwheel.addBox(0F, 0F, 0F, 10, 10, 20);
      midwheel.setRotationPoint(-5F, 9F, -31F);
      midwheel.setTextureSize(128, 128);
      midwheel.mirror = true;
      setRotation(midwheel, 0F, 0F, 0F);
      botwheel = new ModelRenderer(this, 34, 55);
      botwheel.addBox(0F, 0F, 0F, 10, 3, 10);
      botwheel.setRotationPoint(-5F, 21F, -26F);
      botwheel.setTextureSize(128, 128);
      botwheel.mirror = true;
      setRotation(botwheel, 0F, 0F, 0F);
      bbotwheel = new ModelRenderer(this, 0, 20);
      bbotwheel.addBox(0F, 0F, 0F, 10, 2, 10);
      bbotwheel.setRotationPoint(-5F, 22F, 2F);
      bbotwheel.setTextureSize(128, 128);
      bbotwheel.mirror = true;
      setRotation(bbotwheel, 0F, 0F, 0F);
      leftpipebrace = new ModelRenderer(this, 75, 2);
      leftpipebrace.addBox(0F, 0F, 0F, 6, 4, 11);
      leftpipebrace.setRotationPoint(5F, 15F, 0F);
      leftpipebrace.setTextureSize(128, 128);
      leftpipebrace.mirror = true;
      setRotation(leftpipebrace, 0F, 0F, 0F);
      bmidwheel = new ModelRenderer(this, 0, 54);
      bmidwheel.addBox(0F, 0F, 0F, 10, 10, 14);
      bmidwheel.setRotationPoint(-5F, 12F, 0F);
      bmidwheel.setTextureSize(128, 128);
      bmidwheel.mirror = true;
      setRotation(bmidwheel, 0F, 0F, 0F);
      rightdrillbase = new ModelRenderer(this, 71, 96);
      rightdrillbase.addBox(0F, 0F, 0F, 8, 12, 20);
      rightdrillbase.setRotationPoint(-20F, 8F, -22F);
      rightdrillbase.setTextureSize(128, 128);
      rightdrillbase.mirror = true;
      setRotation(rightdrillbase, 0F, 0F, 0F);
      rightnodestack = new ModelRenderer(this, 34, 54);
      rightnodestack.addBox(0F, 0F, 0F, 2, 8, 2);
      rightnodestack.setRotationPoint(-13F, -12F, 4F);
      rightnodestack.setTextureSize(128, 128);
      rightnodestack.mirror = true;
      setRotation(rightnodestack, 0F, 0F, 0F);
      leftpipe1 = new ModelRenderer(this, 58, 20);
      leftpipe1.addBox(0F, 0F, 0F, 8, 3, 3);
      leftpipe1.setRotationPoint(11F, 16F, 0F);
      leftpipe1.setTextureSize(128, 128);
      leftpipe1.mirror = true;
      setRotation(leftpipe1, 0F, 0F, 0.122173F);
      leftpipe2 = new ModelRenderer(this, 58, 20);
      leftpipe2.addBox(0F, 0F, 0F, 8, 3, 3);
      leftpipe2.setRotationPoint(11F, 16F, 4F);
      leftpipe2.setTextureSize(128, 128);
      leftpipe2.mirror = true;
      setRotation(leftpipe2, 0F, 0F, 0.122173F);
      rightwirenub = new ModelRenderer(this, 55, 76);
      rightwirenub.addBox(0F, 0F, 0F, 3, 3, 3);
      rightwirenub.setRotationPoint(-13F, 5F, 2F);
      rightwirenub.setTextureSize(128, 128);
      rightwirenub.mirror = true;
      setRotation(rightwirenub, 0F, 0F, 0F);
      rightlowerarm = new ModelRenderer(this, 40, 0);
      rightlowerarm.addBox(0F, 0F, 0F, 4, 2, 13);
      rightlowerarm.setRotationPoint(-18F, 14F, 0F);
      rightlowerarm.setTextureSize(128, 128);
      rightlowerarm.mirror = true;
      setRotation(rightlowerarm, 0.6806784F, 0F, 0F);
      rightlowernode = new ModelRenderer(this, 86, 17);
      rightlowernode.addBox(0F, 0F, 0F, 8, 4, 4);
      rightlowernode.setRotationPoint(-20F, 12F, -2F);
      rightlowernode.setTextureSize(128, 128);
      rightlowernode.mirror = true;
      setRotation(rightlowernode, 0F, 0F, 0F);
      rightmidnode = new ModelRenderer(this, 86, 17);
      rightmidnode.addBox(0F, 0F, 0F, 8, 4, 4);
      rightmidnode.setRotationPoint(-20F, 5F, 10F);
      rightmidnode.setTextureSize(128, 128);
      rightmidnode.mirror = true;
      setRotation(rightmidnode, 0F, 0F, 0F);
      topwheelcover = new ModelRenderer(this, 0, 78);
      topwheelcover.addBox(0F, 0F, 0F, 10, 4, 15);
      topwheelcover.setRotationPoint(-5F, 0F, -26F);
      topwheelcover.setTextureSize(128, 128);
      topwheelcover.mirror = true;
      setRotation(topwheelcover, 0F, 0F, 0F);
      rightpipebrace = new ModelRenderer(this, 75, 2);
      rightpipebrace.addBox(0F, 0F, 0F, 6, 4, 11);
      rightpipebrace.setRotationPoint(-11F, 15F, 0F);
      rightpipebrace.setTextureSize(128, 128);
      rightpipebrace.mirror = true;
      setRotation(rightpipebrace, 0F, 0F, 0F);
      rightpipe1 = new ModelRenderer(this, 58, 20);
      rightpipe1.addBox(0F, 0F, 0F, 8, 3, 3);
      rightpipe1.setRotationPoint(-19F, 17F, 0F);
      rightpipe1.setTextureSize(128, 128);
      rightpipe1.mirror = true;
      setRotation(rightpipe1, 0F, 0F, -0.122173F);
      rightpipe2 = new ModelRenderer(this, 58, 20);
      rightpipe2.addBox(0F, 0F, 0F, 8, 3, 3);
      rightpipe2.setRotationPoint(-19F, 17F, 4F);
      rightpipe2.setTextureSize(128, 128);
      rightpipe2.mirror = true;
      setRotation(rightpipe2, 0F, 0F, -0.122173F);
      rightpipe3 = new ModelRenderer(this, 58, 20);
      rightpipe3.addBox(0F, 0F, 0F, 8, 3, 3);
      rightpipe3.setRotationPoint(-19F, 17F, 8F);
      rightpipe3.setTextureSize(128, 128);
      rightpipe3.mirror = true;
      setRotation(rightpipe3, 0F, 0F, -0.122173F);
      leftlowerarm = new ModelRenderer(this, 40, 0);
      leftlowerarm.addBox(0F, 0F, 0F, 4, 2, 13);
      leftlowerarm.setRotationPoint(14F, 14F, 0F);
      leftlowerarm.setTextureSize(128, 128);
      leftlowerarm.mirror = true;
      setRotation(leftlowerarm, 0.6806784F, 0F, 0F);
      leftmidnode = new ModelRenderer(this, 86, 17);
      leftmidnode.addBox(0F, 0F, 0F, 8, 4, 4);
      leftmidnode.setRotationPoint(12F, 5F, 10F);
      leftmidnode.setTextureSize(128, 128);
      leftmidnode.mirror = true;
      setRotation(leftmidnode, 0F, 0F, 0F);
      rightnodestackb = new ModelRenderer(this, 34, 54);
      rightnodestackb.addBox(0F, 0F, 0F, 2, 8, 2);
      rightnodestackb.setRotationPoint(-13F, -10F, 11F);
      rightnodestackb.setTextureSize(128, 128);
      rightnodestackb.mirror = true;
      setRotation(rightnodestackb, -0.6632251F, 0F, 0F);
      leftupperarm = new ModelRenderer(this, 116, 13);
      leftupperarm.addBox(0F, 0F, 0F, 2, 10, 2);
      leftupperarm.setRotationPoint(12F, -1F, 6F);
      leftupperarm.setTextureSize(128, 128);
      leftupperarm.mirror = true;
      setRotation(leftupperarm, 0.6108652F, 0F, -0.3490659F);
      leftnodestack = new ModelRenderer(this, 34, 54);
      leftnodestack.addBox(0F, 0F, 0F, 2, 8, 2);
      leftnodestack.setRotationPoint(11F, -12F, 4F);
      leftnodestack.setTextureSize(128, 128);
      leftnodestack.mirror = true;
      setRotation(leftnodestack, 0F, 0F, 0F);
      rightbackstack = new ModelRenderer(this, 34, 54);
      rightbackstack.addBox(0F, 0F, 0F, 2, 8, 2);
      rightbackstack.setRotationPoint(-6F, -8F, 16F);
      rightbackstack.setTextureSize(128, 128);
      rightbackstack.mirror = true;
      setRotation(rightbackstack, -0.6632251F, 0F, 0F);
      leftnodestackb = new ModelRenderer(this, 34, 54);
      leftnodestackb.addBox(0F, 0F, 0F, 2, 8, 2);
      leftnodestackb.setRotationPoint(11F, -10F, 11F);
      leftnodestackb.setTextureSize(128, 128);
      leftnodestackb.mirror = true;
      setRotation(leftnodestackb, -0.6632251F, 0F, 0F);
      leftbackstack = new ModelRenderer(this, 34, 54);
      leftbackstack.addBox(0F, 0F, 0F, 2, 8, 2);
      leftbackstack.setRotationPoint(4F, -8F, 16F);
      leftbackstack.setTextureSize(128, 128);
      leftbackstack.mirror = true;
      setRotation(leftbackstack, -0.6632251F, 0F, 0F);
      bleftwirecoverbot = new ModelRenderer(this, 110, 28);
      bleftwirecoverbot.addBox(0F, 0F, 0F, 4, 1, 5);
      bleftwirecoverbot.setRotationPoint(8F, 8F, 1F);
      bleftwirecoverbot.setTextureSize(128, 128);
      bleftwirecoverbot.mirror = true;
      setRotation(bleftwirecoverbot, 0F, 0F, 0F);
      seatfloor = new ModelRenderer(this, 0, 32);
      seatfloor.addBox(0F, 0F, 0F, 16, 5, 17);
      seatfloor.setRotationPoint(-8F, 5F, -11F);
      seatfloor.setTextureSize(128, 128);
      seatfloor.mirror = true;
      setRotation(seatfloor, 0F, 0F, 0F);
      seatright = new ModelRenderer(this, 114, 48);
      seatright.addBox(0F, 0F, 0F, 1, 3, 6);
      seatright.setRotationPoint(-8F, 2F, 0F);
      seatright.setTextureSize(128, 128);
      seatright.mirror = true;
      setRotation(seatright, 0F, 0F, 0F);
      control = new ModelRenderer(this, 0, 0);
      control.addBox(0F, 0F, 0F, 16, 4, 2);
      control.setRotationPoint(-8F, 1F, -11F);
      control.setTextureSize(128, 128);
      control.mirror = true;
      setRotation(control, 0F, 0F, 0F);
      controlL = new ModelRenderer(this, 0, 9);
      controlL.addBox(0F, 0F, 0F, 2, 4, 2);
      controlL.setRotationPoint(4F, -3F, -11F);
      controlL.setTextureSize(128, 128);
      controlL.mirror = true;
      setRotation(controlL, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    
    btopwheel.render(f5);
    topmidwheel.render(f5);
    botmidwheel.render(f5);
    topfwheel.render(f5);
    topbwheel.render(f5);
    botfwheel.render(f5);
    botbwheel.render(f5);
    bottom.render(f5);
    rightwheelside.render(f5);
    leftdrillplatebot.render(f5);
    topwheel.render(f5);
    rightwirecoverl.render(f5);
    controlR.render(f5);
    leftfwheelside.render(f5);
    leftbwheelside.render(f5);
    topfwheelcover.render(f5);
    rightwirecoverb.render(f5);
    rightwirecoverr.render(f5);
    rightwheelcover.render(f5);
    leftwirecoverr.render(f5);
    leftwirecoverl.render(f5);
    leftwirecoverb.render(f5);
    leftwirecovert.render(f5);
    leftwheelcover.render(f5);
    rightwirecovert.render(f5);
    leftwiref.render(f5);
    leftwiret.render(f5);
    leftlowernode.render(f5);
    bleftwirecoverf.render(f5);
    bleftwirecoverb.render(f5);
    bleftwirecovertop.render(f5);
    rightwiref.render(f5);
    seatleft.render(f5);
    brightwirecoverb.render(f5);
    leftuppernode.render(f5);
    brightwirecoverf.render(f5);
    brightwirecoverbot.render(f5);
    rightwireb.render(f5);
    brightwirecovertop.render(f5);
    rightupperarm.render(f5);
    back.render(f5);
    rightuppernode.render(f5);
    rightwiret.render(f5);
    leftwireb.render(f5);
    leftwirenub.render(f5);
    rightfwheelside.render(f5);
    leftdrillbase.render(f5);
    leftdrillbotbitpoint.render(f5);
    leftdrillplatetop.render(f5);
    leftdrillbotbit.render(f5);
    leftdrillleftbit.render(f5);
    leftdrillleftbitpoint.render(f5);
    leftdrillbaseleft.render(f5);
    leftdrillplate.render(f5);
    leftdrillbaseright.render(f5);
    leftdrilltopbit.render(f5);
    leftdrilltopbitpoint.render(f5);
    leftdrillrightbit.render(f5);
    leftdrillrightbitpoint.render(f5);
    leftpipe3.render(f5);
    rightdrilltopbit.render(f5);
    rightdrilltopbitpoint.render(f5);
    rightdrillleftbit.render(f5);
    rightdrillleftbitpoint.render(f5);
    rightdrillrightbit.render(f5);
    rightdrillrightbitpoint.render(f5);
    rightdrillbotbit.render(f5);
    rightdrillbotbitpoint.render(f5);
    rightdrillplatebot.render(f5);
    rightdrillplatetop.render(f5);
    rightdrillplate.render(f5);
    rightdrillbaseright.render(f5);
    rightdrillbaseleft.render(f5);
    midwheel.render(f5);
    botwheel.render(f5);
    bbotwheel.render(f5);
    leftpipebrace.render(f5);
    bmidwheel.render(f5);
    rightdrillbase.render(f5);
    rightnodestack.render(f5);
    leftpipe1.render(f5);
    leftpipe2.render(f5);
    rightwirenub.render(f5);
    rightlowerarm.render(f5);
    rightlowernode.render(f5);
    rightmidnode.render(f5);
    topwheelcover.render(f5);
    rightpipebrace.render(f5);
    rightpipe1.render(f5);
    rightpipe2.render(f5);
    rightpipe3.render(f5);
    leftlowerarm.render(f5);
    leftmidnode.render(f5);
    rightnodestackb.render(f5);
    leftupperarm.render(f5);
    leftnodestack.render(f5);
    rightbackstack.render(f5);
    leftnodestackb.render(f5);
    leftbackstack.render(f5);
    bleftwirecoverbot.render(f5);
    seatfloor.render(f5);
    seatright.render(f5);
    control.render(f5);
    controlL.render(f5);
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
      
  }

}
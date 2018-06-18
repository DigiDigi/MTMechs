package digidigi.mtm.utility;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import digidigi.mtm.common.EntityMT;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class SimpleTrace 
{
   
    public static Double rotateyaw;
    public static Double rotateyawradians;
    public static Double rotatepitch;
    public static Double rotatepitchradians;
    public static AxisAlignedBB targetbb;
    public static EntityMT entitymt;

    public static HashMap<Integer, Point2D[]> rects = new HashMap<Integer, Point2D[]>(); // Key corresponds to an entity. Value is a rect/array of points.
    public static List<TripletInt> centers = new ArrayList<TripletInt>(); // Index corresponds to an entity. Value is of the corresponding bounding rect (x,y,z) center.
    public static List<Entity> hits = new ArrayList<Entity>(); // List of hit entities to return.
    
    public static Point2D[] pointifyXY(Entity entity)
    {                      
        
                            //XZ
        Point2D[] points = {new Point2D.Double(entity.boundingBox.minX, entity.boundingBox.minZ),
                            new Point2D.Double(entity.boundingBox.maxX, entity.boundingBox.minZ),
                            new Point2D.Double(entity.boundingBox.maxX, entity.boundingBox.maxZ),
                            new Point2D.Double(entity.boundingBox.minX, entity.boundingBox.maxZ)};

        //Y is vertical in minecraft.
        
        //ZY
        //points[4] = new Point2D.Double(entity.boundingBox.maxZ, entity.boundingBox.minY);
        //points[5] = new Point2D.Double(entity.boundingBox.minZ, entity.boundingBox.minY);
        //points[6] = new Point2D.Double(entity.boundingBox.minZ, entity.boundingBox.maxY);
        //points[7] = new Point2D.Double(entity.boundingBox.maxZ, entity.boundingBox.maxY);
        

        //System.out.println(points[2].getX());
        
        return points;
    }
    
    public static Point2D[] pointifyYZ(Entity entity)
    {
        Point2D[] points = {};
        points[0] = new Point2D.Double(entity.boundingBox.maxY, entity.boundingBox.minZ);
        points[1] = new Point2D.Double(entity.boundingBox.minY, entity.boundingBox.minZ);
        points[2] = new Point2D.Double(entity.boundingBox.minY, entity.boundingBox.maxZ);
        points[3] = new Point2D.Double(entity.boundingBox.maxY, entity.boundingBox.maxZ);
        return points;
    }
    
    public static boolean DetectHit(EntityMT entitymt, Point2D[] entityrect)
    {
        //the points should be the width of the beam instead.
        
        //System.out.println(entityrect);
        if (entityrect[0].getX() < entitymt.boundingBox.minX && entityrect[1].getX() < entitymt.boundingBox.minX ||
            entitymt.boundingBox.minX < entityrect[0].getX() && entitymt.boundingBox.maxX < entityrect[0].getX())
        {
            return false;
        }
        else
        {
            if (entitymt.boundingBox.maxZ + entitymt.beamDistanceMax > entityrect[2].getY())
                return true;
            else
                return false;
        }
        
        /*
        if (entityrect[0].getX() && entityrect[1].getX() < entitymt.boundingBox.minX ||
                entitymt.boundingBox.minX && entitymt.boundingBox.maxX < entityrect[0].getX())
        */
        
    }
    
    public static List<Entity> beamScanForEntities(World world, EntityMT entitymt, AxisAlignedBB mechscanBB)
    {
        rotateyaw = (double) entitymt.rotationYaw + 180;
        rotatepitch = (double) (entitymt.rotationPitch * 200) + 180; // * 200 because pitch in entitymt is divided that way for some reason.
        rotateyawradians = (rotateyaw * Math.PI / 180);
        rotatepitchradians = (rotatepitch * Math.PI / 180);
        List<?> scannedentities = world.getEntitiesWithinAABBExcludingEntity(entitymt.riddenByEntity, mechscanBB);
        rects = new HashMap<Integer, Point2D[]>();
        centers = new ArrayList<TripletInt>();
        hits = new ArrayList<Entity>();
        
        for (int e = 0; e < scannedentities.size(); ++e) // [e] will be an index for entities/bboxes in rects.
        {
            Entity entity = (Entity)scannedentities.get(e);
            
            //Do filter stuff for entity types we don't want here.
            
            int xcenter = (int) (Math.abs(entity.boundingBox.maxX - entity.boundingBox.minX) / 2);
            int ycenter = (int) (Math.abs(entity.boundingBox.maxY - entity.boundingBox.minY) / 2);
            int zcenter = (int) (Math.abs(entity.boundingBox.maxZ - entity.boundingBox.minZ) / 2);
            centers.add(new TripletInt(xcenter, ycenter, zcenter));
            

            Point2D[] pointarray = pointifyXY(entity);
            rects.put(e, pointarray);
            
            //System.out.println(entitymt.boundingBox.minX);
            
            AffineTransform atc = new AffineTransform(); //Rects rotate around their own center.
            atc.rotate(Math.toRadians(rotateyaw), pointarray[0].getX() + xcenter, pointarray[0].getY() + zcenter);
            atc.transform(pointarray[0], null);
            for (int p = 0; p < 4; ++p) //P for each point in the rect.
            {     
                AffineTransform at = new AffineTransform(); // all rects rotate around arbitrary point.
                at.rotate(Math.toRadians(-rotateyaw), entitymt.posX, entitymt.posZ);
                at.transform(rects.get(e)[p], null);
                at.concatenate(atc);
                at.transform(rects.get(e)[p], rects.get(e)[p]);
                
                
                if (DetectHit(entitymt, rects.get(e)))
                {
                    if ((Entity)scannedentities.get(e) instanceof EntityMT)
                    {
                        
                    }
                    else
                    {
                        hits.add((Entity)scannedentities.get(e));
                    }
                }
                
                

            }
            
        }
        
        return hits;
    }
    
    
}


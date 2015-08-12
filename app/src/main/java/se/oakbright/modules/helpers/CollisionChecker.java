package se.oakbright.modules.helpers;

import java.util.List;

import se.oakbright.modules.activatables.updatables.CollisionModule;
import se.oakbright.calculation.DirectionCalculation;

/**
 * Created by hampuse on 2015-06-27.
 */
public final class CollisionChecker {
    private final static CollisionChecker collisionChecker = new CollisionChecker();
    private CollisionModule ob1, ob2;

    private CollisionChecker(){
    }

    public static void checkForCollisionsAmong(List<CollisionModule> battleObjects) {
        for (int i = 0; i < battleObjects.size(); i++) {
            for (int j = i+1; j < battleObjects.size(); j++) {
                checkForCollisionBetween(battleObjects.get(i),battleObjects.get(j));
            }
        }
    }

    private static void checkForCollisionBetween(CollisionModule ob1, CollisionModule ob2) {
        if(ob1.isAbleToCollideWith(ob2) && ob2.isAbleToCollideWith(ob1)) {
            collisionChecker.setup(ob1, ob2);
            collisionChecker.checkForCollision();
        }
    }

    private void setup(CollisionModule ob1, CollisionModule ob2){
        this.ob1 = ob1;
        this.ob2 = ob2;
    }

    private void checkForCollision(){
        if(distanceBetweenCenterPointsOf(ob1.positioner, ob2.positioner) < summedMaxBoundingRadii(ob1.shape, ob2.shape)){
            if(ob1.shape.getMaxBoundingRadii() < ob2.shape.getMaxBoundingRadii()){
                thoroughCheckForCollision(ob1, ob2);
            }else
                thoroughCheckForCollision(ob2, ob1);
        }
    }

    private static int distanceBetweenCenterPointsOf(Positioner ob1, Positioner ob2){
        int ob1X = ob1.getX();
        int ob1Y = ob1.getY();
        int ob2X = ob2.getX();
        int ob2Y = ob2.getY();
        return  (int) Math.hypot(ob1X-ob2X, ob1Y-ob2Y);
    }


    private static int summedMaxBoundingRadii(Shape ob1, Shape ob2) {
        return (ob1.getMaxBoundingRadii() + ob2.getMaxBoundingRadii());
    }

    private static void thoroughCheckForCollision(CollisionModule smallerObject, CollisionModule biggerObject){   //TODO make collisionCheck object
        //--Thorough collision detection --//	//The thorough collision detection compares points from ob1, with the radius of ob2:s border at the angle in question.
        float[] ob1Points = smallerObject.bounding.getMappedBoundingPointsKeyPoints();
        for(int i = 0; i < ob1Points.length; i=i+2){	//For all the bounding points from ob1...
            int smallerObjectPointX = (int) ob1Points[i];
            int smallerObjectPointY = (int) ob1Points[i+1];
            int ob1PointDistToCenter2 = (int) Math.hypot(smallerObjectPointX - biggerObject.positioner.getX(), smallerObjectPointY - biggerObject.positioner.getY()); // calculates the distance between the point to the center of ob2.


            if(ob1PointDistToCenter2 < biggerObject.shape.getMaxBoundingRadii()){			//rough check if the point is able to collide with ob2.
                int angleOb2CenterToPoint = (int) DirectionCalculation.getDirectionDegFromTo(biggerObject.middlePoint.x(), biggerObject.middlePoint.y(), smallerObjectPointX, smallerObjectPointY);		//the angle from center of ob2 to the point.
                int originalAngle = angleOb2CenterToPoint - biggerObject.direction.degrees();	//Convert the angle to match the non-transformed values in icon
                if(originalAngle > 360)
                    originalAngle = originalAngle - 360;
                if(originalAngle < 0)
                    originalAngle = originalAngle + 360;
                if(ob1PointDistToCenter2 < biggerObject.shape.getRadii(originalAngle)){	//Checks if the point is inside the radius of ob2 => collision.
                    biggerObject.collideWith(smallerObject);
                    smallerObject.collideWith(biggerObject);
                    break;	//collision detected, no need to continue;
                }
            }
        }
    }

}

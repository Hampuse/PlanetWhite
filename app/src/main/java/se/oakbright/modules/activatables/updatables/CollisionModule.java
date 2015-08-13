package se.oakbright.modules.activatables.updatables;

import android.os.Build;

import se.oakbright.Buildable;
import se.oakbright.battleobjects.BattleObject;
import se.oakbright.modules.helpers.Collidable;
import se.oakbright.modules.helpers.Direction;
import se.oakbright.modules.helpers.Health;
import se.oakbright.modules.helpers.Positioner;
import se.oakbright.modules.helpers.Shape;
import se.oakbright.modules.internalpoints.InternalPoint;
import se.oakbright.modules.Module;
import se.oakbright.modules.helpers.Bounding;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.ServiceProvider;
import se.oakbright.resources.TypeResource;

/**
 * Created by hampuse on 2015-06-26.
 */
public class CollisionModule extends Module implements Collidable {
    //private boolean isAbleToCollide;
    private int onCollisionGiveDamage;

    private Health health;
    public Positioner positioner;
    public Shape shape;
    public InternalPoint middlePoint;
    public Direction direction;
    public Bounding bounding;

    /*public CollisionModule(){
        this.isAbleToCollide = false;
    }*/

    public void collideWith(CollisionModule other) {
        health.tryDecreaseHp(other.onCollisionGiveDamage());
        //calculate collision energy and direction change...
        //TODO mer intelligent krock
    }

    /*public void setCollisionGiveDamage(int damage){
        this.onCollisionGiveDamage = damage;
    }*/

    protected int onCollisionGiveDamage() {
        return this.onCollisionGiveDamage;
    }

    /*void setIsAbleToCollide(boolean b){
        this.isAbleToCollide = b;
    }*/

    public boolean isAbleToCollideWith(CollisionModule battleObject) {
        return true; //TODO  //this.isAbleToCollide;
    }

    /*public void setHealth(Health health) {
        this.health = health;
    }*/

    /*public void setPositioner(Positioner positioner) {
        this.positioner = positioner;
    }*/

    @Override
    public void activate() {
        getBattleModel().addCollidable(this);
    }

    @Override
    public void deactivate() {
        getBattleModel().addCollidable(this);
    }

    @Override
    public boolean invitationToLand(BattleObject landingPad) {
        return false;
    }

    public static class Builder extends Buildable<CollisionModule>{
        public int onCollisionGiveDamage;
        public Buildable<Health> healthBuilder;
        public Buildable<Positioner> positionerBuilder;
        public Buildable<Shape> shapeBuilder;
        public Buildable<? extends InternalPoint> middlePointBuilder;
        public Buildable<Direction> directionBuilder;
        public Buildable<Bounding> boundingBuilder;

        @Override
        protected CollisionModule buildNew() {
            CollisionModule collisionModule = new CollisionModule();
            collisionModule.onCollisionGiveDamage = onCollisionGiveDamage;
            collisionModule.health = healthBuilder.getBuilt();
            collisionModule.positioner = positionerBuilder.getBuilt();
            collisionModule.shape = shapeBuilder.getBuilt();
            collisionModule.middlePoint = middlePointBuilder.getBuilt();
            collisionModule.direction = directionBuilder.getBuilt();
            collisionModule.bounding = boundingBuilder.getBuilt();
            return collisionModule;
        }
    }
    /*private static void thoroughCheckForCollision(CollisionModule smallerObject1, CollisionModule biggerObject2){
        //--Thorough collision detection --//	//The thorough collision detection compares points from ob1, with the radius of ob2:s border at the angle in question.
        float[] ob1Points = smallerObject1.shape.getBoundingPointsKeyPoints();
        for(int i = 0; i < ob1Points.length; i=i+2){	//For all the bounding points from ob1...
            int ob1PointX = (int) ob1Points[i];
            int ob1PointY = (int) ob1Points[i+1];
            int ob1PointDistToCenter2 = (int) Math.hypot( ob1PointX-biggerObject2.positioner.getX(), ob1PointY-biggerObject2.positioner.getY() ); // calculates the distance between the point to the center of ob2.
            if(ob1PointDistToCenter2 < biggerObject2.shape.getMaxBoundingRadii()){			//rough check if the point is able to collide with ob2.
                int angleOb2CenterToPoint = DirectionCalculation.getDirectionDegBetween(biggerObject2.positioner.getX(), biggerObject2.positioner.getY(), ob1PointX, ob1PointY);		//the angle from center of ob2 to the point.
                int originalAngle = angleOb2CenterToPoint - biggerObject2.positioner.getDirection();	//Convert the angle to match the non-transformed values in icon
                if(originalAngle > 360)
                    originalAngle = originalAngle - 360;
                if(originalAngle < 0)
                    originalAngle = originalAngle + 360;
                if(ob1PointDistToCenter2 < biggerObject2.shape.icon.getRadii(originalAngle)){	//Checks if the point is inside the radius of ob2 => collision.
                    biggerObject2.collideWith(smallerObject1);
                    smallerObject1.collideWith(biggerObject2);
                    break;	//collision detected, no need to continue;
                }
            }
        }
    }*/

    public interface Resource extends TypeResource<CollisionModule>{

    }
}

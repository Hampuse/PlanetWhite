package se.oakbright.modules.activatables.updatables;

import se.oakbright.Frames.Frame;
import se.oakbright.modules.helpers.Direction;
import se.oakbright.modules.helpers.Positioner;
import se.oakbright.modules.helpers.Speed;
import se.oakbright.resource.Resource;
import static se.oakbright.resource.Key.*;


/**
 * Created by hampuse on 2015-06-25.
 */
public class Mover extends UpdatableModule {

    private static final String TAG = Mover.class.getSimpleName();
    //ConcurrentLinkedQueue<Point> path;	//The coordinates for the path that the ship should travel;
    //Point pathTail;	//The coordinate that was added most lately to the path queue. used because one cannot peek at the tail of a COncurrentLInkedQueue (...or?)
    //BattleObject recentlyEjectedFrom;
    //BattleObject trackObject;

    private float xFloat;    //The x coordinate in more precise way than x, that is an int representing the same.
    private float yFloat;    //The decimals of the y coordinate


    //private Paint pathPaint = new Paint();	//

    private Speed speed;
    private Frame movingFrame;
    private int goVelocity;
    private Positioner positioner;
    //private Activatable host;
    //private PathModule pathModule;
    private Direction direction;
    //TODO private TrackerModule trackerModule;

   public Mover(Resource r){
       super(r);
       this.speed = new Speed(r);
       this.positioner = r.getThe(POSITIONER);
      // this.movingFrame = get(MOVING_FRAME);
   }

    public void update() {
        //TODO
       /* FÖRENKLAT NU
        if (this.trackObject != null) {        // track the trackObject if it exists.

            trackerModule.trackTrackObject();
        }

        while (nextPoint != null && distanceToNextPoint < speed.getDistPerUpdate()) {
            updateNextPoint();
        }
        distanceToNextPoint -= speed.getDistPerUpdate();
        */
        int deltaX = (speed.getDeltaXperUpdate());
        int deltaY = (speed.getDeltaYperUpdate());

        this.positioner.changeInPosition(deltaX, deltaY);
        //TODO direction.setDirectionDegrees(Math.round(speed.getDirectionDeg())); //TODO away somehow?
        //TODO checkForBorders();
    }

   /* boolean isOkToCollideWith(BattleObject battleObject) {
        if (this.recentlyEjectedFrom != null)
            if (this.recentlyEjectedFrom.equals(battleObject))
                return false;

        return true;
    }*/


    ///////////////////////////////////////////////////////
    //////////////////// Border methods: //////////////////////
    void checkForBorders() {
        float normalDeg = Mover.normalDegreeOfCrossingBorder(this, this.movingFrame);
        if (Float.isNaN(normalDeg) == false)    //if NaN, no border is being crossed.
            this.handleOnBorder(normalDeg);
    }

    void handleOnBorder(float normalDeg) {
        //TODO cannot have host, cirvular build... listener or something instead host.deactivate();
    }

    public static Float normalDegreeOfCrossingBorder(Mover mover, Frame movingFrame) {
        float normalDeg;
        //int leftBorder, int upBorder, int rightBorder, int downBorder) {
        //--check if the Mover is heading towards the borders of the surface,
        //--upBorder is interpreted as the "visual up", the lowest y-coordinate, typically zero.
        if (mover.getX() > movingFrame.right && mover.getVelocityX() > 0) {
            normalDeg = 270; //normal is 270 for right border  //TODO: make the normal more general...
        } else if (mover.getY() > movingFrame.bottom && mover.getVelocityY() > 0) {
            normalDeg = 0; //normal is 0 for lower border  //TODO: make the normal more general...
        } else if (mover.getX() < movingFrame.left && mover.getVelocityX() < 0) {
            normalDeg = 90; //normal is 90 for left border  //TODO: make the normal more general...
        } else if (mover.getY() < movingFrame.top && mover.getVelocityY() < 0) {
            normalDeg = 180; //normal is 180 for right border  //TODO: make the normal more general...
        } else {
            normalDeg = Float.NaN;
        }
        return normalDeg;
    }

    private int getX() {
        return Math.round(this.xFloat);
    }

    private int getY() {
        return Math.round(this.yFloat);
    }

   /* void setPosition(int x, int y) {
        this.xFloat = x;
        this.yFloat = y;
    }*/

    // ////////// Speed and direction methods: /////////////////////
    float getDirectionDeg() {
        return this.direction.degrees();
    }

    /*void setDirectionTowards(int xTo, int yTo) {
        //TODO pathModule.resetPath();
        //TODO pathModule.resetNextPoint();
        speed.setDirection(this.getX(), this.getY(), xTo, yTo);
    }

    void setDirection(int directionDegrees) {
        //TODO pathModule.resetPath();
        //TODO pathModule.resetNextPoint();
        speed.setDirectionDeg(directionDegrees);
    }

    void setDirection(int xFrom, int yFrom, int xTo, int yTo) {
        speed.setDirection(xFrom, yFrom, xTo, yTo);
    }*/

    public void go() {
        speed.setVelocity(goVelocity);
    }

    public void halt() {
        speed.setVelocity(Speed.STANDING_STILL);
    }

    /*public boolean isHalted() {
        if (speed.getVelocity() == Speed.STANDING_STILL)
            return true;
        return false;
    }*/

    void setVelocity(float f) {
        speed.setVelocity(f);
    }

    private float getVelocityX() {
        return speed.getDeltaXperUpdate();
    }

    private float getVelocityY() {
        return speed.getDeltaYperUpdate();
    }

}
   /* ///////////// Nested classes: //////////////////
    private class Coord {
        public final int x;
        public final int y;

        private Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private Coord(Coord coord) {
            this.x = coord.x;
            this.y = coord.y;
        }
    }
*/

//TODO REPLACE :
/*    public static class Builder extends Buildable<Mover>{
        //public Frame movingFrame;
        public Integer goVelocity;
        public Buildable<Positioner> positionerBuilder;
       //public Buildable<? extends Activatable> hostBuilder;
        public Buildable<PathModule> pathModuleBuilder;
        public Buildable<Direction> directionBuilder;
        //TODO private TrackerModule trackerModule;
        private MoverResource r;
        public Speed.Builder speedBuilder = new Speed.Builder();
        //private final Buildable<Mover> shadowBuilder

        public Builder(MoverResource r){
            this.r = r;
        }

        public Builder(){

        }

        @Override
        protected Mover buildNew() {
            Mover mover = new Mover();
            RuntimeTests.testForNull(goVelocity, "goVelocity");
            //speedBuilder.goVelocity = goVelocity;
            mover.goVelocity = goVelocity;
            speedBuilder.directionBuilder = directionBuilder;
            mover.speed = speedBuilder.getBuilt();
            mover.speed.setVelocity(goVelocity);

           // RuntimeTests.testForNull(movingFrame, "movingFrame");
            mover.movingFrame = ServiceProvider.getMovingFrame();
            mover.positioner = positionerBuilder.getBuilt();
            //mover.positioner = r.getPositioner("mover"); //Builder().getBuilt(); //positionerBuilder.getBuilt();
            //mover.positioner = r.get("positioner","mover");
            //mover.host = hostBuilder.getBuilt();
            //mover.pathModule = pathModuleBuilder.getBuilt();
            mover.direction = directionBuilder.getBuilt();
            //TODO private TrackerModule trackerModule;
            return mover;
        }

        public void setup(MoverBuilderResource r){
            positionerBuilder = r.getPositionerBuilder();
        }

        public static interface MoverResource{
            public Positioner getPositioner();
        }
    }
    public static interface Builder{

    }
}*/
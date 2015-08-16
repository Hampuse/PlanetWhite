package se.oakbright.modules.activatables.updatables;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import se.oakbright.battleobjects.BattleObject;
import se.oakbright.modules.helpers.Positioner;
import se.oakbright.modules.Module;
import se.oakbright.calculation.DirectionCalculation;
import se.oakbright.calculation.Point;

/**
 * Created by hampuse on 2015-06-28.
 */
public class PathModule extends Module {   //TODO NEED TO EXTENDS SOMETHING ELSE?

    ConcurrentLinkedQueue<Point> path;	//The  points for the path that the ship should travel;
    Point pathTail;	//The points that was added most lately to the path queue. used because one cannot peek at the tail of a COncurrentLInkedQueue (...or?)
    BattleObject trackObject;


    private float distanceToNextPoint;
    public Point nextPoint;

    private Mover mover;
    private Positioner positioner;

    public PathModule(){

        this.setPath(new ConcurrentLinkedQueue<Point>());
        this.pathTail = new Point(0,0);
        this.nextPoint = null;
    }


    /////////////////////////////////////////////////////
    // ///////// Path methods: //////////////////////////


   public void updateNextPoint() {
        if (!getPath().isEmpty()) {            //if a path coordinate exist,.
            nextPoint = getPath().poll();                    //set the first coordinate as nextCoord
            //TODO mover.setDirectionTowards(nextPoint.x, nextPoint.y);        //set a new direction towards the nextCoord

            this.distanceToNextPoint = DirectionCalculation.getDirectionDegFromTo(positioner.getX(), positioner.getY(), nextPoint.x, nextPoint.y); //(float) Math.hypot(this.getX() - nextPoint.x, this.getY() - nextPoint.y);
        } else {
            nextPoint = null;
        }

    }

    private void addPathPoint(Point point) {
        getPath().add(point);
        this.pathTail = point;
    }

   public void addPathPoint(int x, int y) {
        addPathPoint(new Point(x, y));
    }


   public void resetPath() {
        this.getPath().clear();
        this.trackObject = null;
   }

    public void resetNextPoint() {
        this.nextPoint = null;
    }

    void setTrackObject(BattleObject trackObject) {
        this.trackObject = trackObject;
    }

    void trackTrackObject() {
        if (trackObject.getX() != this.pathTail.x || trackObject.getY() != this.pathTail.y) {    // ...and if its current positioner is not the last positioner in the path queue...
            this.addPathPoint(new Point(trackObject.getX(), trackObject.getY()));    // ...put its positioner in the last positioner of the path queue.
        }
    }

    public int getPathTailX() {
        return this.pathTail.x;
    }

    public int getPathTailY() {
        return this.pathTail.y;
    }

    private ConcurrentLinkedQueue<Point> getPath() {
        return path;
    }

    private void setPath(ConcurrentLinkedQueue<Point> path) {
        this.path = path;
    }

    public boolean hasTrackObject() {
        if (this.trackObject != null)
            return true;
        else
            return false;
    }

    public boolean hasPath() {
        if (this.getPath().isEmpty())
            return false;
        else
            return true;
    }

    public boolean isEmpty() {
        return path.isEmpty();
    }

    public Queue<Point> getAsQueue() {
        return path;
    }

    @Override
    public void activate() {

    }

    @Override
    public void deactivate() {

    }
//TODO REPLACE:
    /*
    public static class Builder extends Buildable<PathModule> {
        public Buildable<Positioner> positionerBuilder;
        public Buildable<Mover> moverBuilder;

        @Override
        protected PathModule buildNew() {
            PathModule pathModule = new PathModule();
            pathModule.mover = moverBuilder.getBuilt();
            pathModule.positioner = positionerBuilder.getBuilt();
            return pathModule;
        }
    }*/
}

package se.oakbright.modules.helpers;

import se.oakbright.modules.internalpoints.InternalPoint;
import se.oakbright.calculation.DirectionCalculation;

/**
 * Created by hampuse on 2015-07-10.
 */
public class Direction {
    private int direction = 0;  //degrees //TODO would be better in radians no?
    InternalPoint position;

    public void setDirectionDegrees(int direction) {
        this.direction = direction;
    }

    public void setDirectionTowards(int x, int y){this.direction = (int) DirectionCalculation.getDirectionDegFromTo(position.x(), position.y(), x, y);}

    public int degrees() {
        return this.direction;
    }

    public float radians(){
        return (float) ((this.direction -90)*Math.PI/180);
    }

    //TODO
    /*
    public static class Builder extends Buildable<Direction>{
        public Buildable<? extends InternalPoint> positionBuilder;
        @Override
        protected Direction buildNew() {
            Direction direction = new Direction();
            direction.position = positionBuilder.getBuilt();
            return direction;
        }
    }*/
}

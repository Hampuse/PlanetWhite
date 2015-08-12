package se.oakbright.modules.internalpoints;

import se.oakbright.modules.helpers.Positioner;

/**
 * Created by hampuse on 2015-06-27.
 */

public interface InternalPoint{

    public abstract int x();
    public abstract int y();

    //public final void setPositioner(Positioner positioner){
      //  this.positioner = positioner;
    //}

    //public static class Builder extends Buildable<>
}

/*public class Point {
    private int x;
    private int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void set(int x, int y){
        this.x = x;
        this.y = y;
    };

    public int x(){
        return x;
    }

    public int y(){
        return y;
    }
}*/

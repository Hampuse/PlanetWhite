package se.oakbright.modules.helpers;

//import android.graphics.Matrix;

import se.oakbright.Buildable;
import se.oakbright.Frames.Frame;

/**
 * Created by hampuse on 2015-06-24.
 */
public class Positioner {
    private int x;
    private int y;
    private Frame movingFrame;

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void changeInPosition(int deltaX, int deltaY){
        this.x += deltaX;
        this.y += deltaY;
    }

   /* public int howNearPoint(int xDst, int yDst, int minPointingRadius){	//TODO make a check if it even is on screen first.
        int distanceFromCenter = (int) Math.hypot(Math.abs(xDst-this.x), Math.abs(yDst-this.y));
        if(distanceFromCenter <= minPointingRadius){		//Checking if the point is near enough to select
            return distanceFromCenter;
        }else
            return Integer.MAX_VALUE;
    }*/

    public Frame getMovingFrame() {
        return this.movingFrame;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setFrame(Frame movingFrame){
        this.movingFrame = movingFrame;
    }

    public static class Builder extends Buildable<Positioner> {
        @Override
        protected Positioner buildNew() {
            return new Positioner();
        }
    }
}

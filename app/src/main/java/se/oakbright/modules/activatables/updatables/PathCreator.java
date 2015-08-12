package se.oakbright.modules.activatables.updatables;

import se.oakbright.battleobjects.Launchable;
import se.oakbright.modules.helpers.Positioner;
import se.oakbright.modules.Module;

/**
 * Created by hampuse on 2015-06-26.
 */
public class PathCreator extends Module {
    private static final int PATH_START_DRAW_THRESHOLD = 50;
    private boolean isDrawingPath; //if the ship is in writing path mode
    private boolean isReadyToDrawPath;
    private PathModule path;
    private Positioner positioner;
    private Launchable launchable;


    @Override
    public void activate() {
        //TODO
    }

    @Override
    public void deactivate() {
        //TODO
    }

    /////////// Drawing path methods: ////////////////////////
    public void handleActionMove(int x, int y) {
        if(this.isDrawingPath()){
            if((Math.abs(x - path.getPathTailX())>5) || ((Math.abs(y - path.getPathTailY())>5))){
                path.addPathPoint(x, y);
            }
        }else if(this.isReadyToDrawPath()){
            if(Math.abs(x- positioner.getX())> PATH_START_DRAW_THRESHOLD  || Math.abs(y- positioner.getY())> PATH_START_DRAW_THRESHOLD){
                this.setReadyToDrawPath(false);
                this.setDrawingPath(true);
                path.resetPath();
                path.addPathPoint(x,y);
                path.updateNextPoint();
            }
        }
    }

    public void stopDrawingPath(){
        this.setReadyToDrawPath(false);
        this.setDrawingPath(false);
    }

    public void tryDrawPath(int x, int y) {
        if(this.isDrawingPath()){
            if((Math.abs( x - path.getPathTailX() )>5) || ((Math.abs( y - path.getPathTailY())>5))){
                path.addPathPoint(x,y);
            }
        }
        else if(Math.abs(x- positioner.getX())> PATH_START_DRAW_THRESHOLD  || Math.abs(y- positioner.getY())> PATH_START_DRAW_THRESHOLD){		//Check if the user have dragged long enough to start drawing a new path.
            this.setDrawingPath(true);
            path.resetPath();
            path.addPathPoint(x,y);
            path.updateNextPoint();
        }

    }

    public boolean isDrawingPath() {
        return isDrawingPath;
    }

    private boolean isReadyToDrawPath() {
        return isReadyToDrawPath;
    }

    private void setDrawingPath(boolean b) {
        this.isDrawingPath = b;
        if(b){
            launchable.commandLaunch();
        }
    }

    private void setReadyToDrawPath(boolean b) {
        this.isReadyToDrawPath = b;
    }
}

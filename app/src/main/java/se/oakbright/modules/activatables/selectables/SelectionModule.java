package se.oakbright.modules.activatables.selectables;

import se.oakbright.modules.Module;
import se.oakbright.resource.Resource;

/**
 * Created by hampuse on 2015-06-26.
 */
public class SelectionModule extends Module {
    private boolean selected;    // if ship is selected
    private Module highLight;   //TODO maybe highlight should not be a Module, but something else?

   // public void setHighlighted(boolean b) {
   //     this.highLight.activate(); //this.isHighlighted = b;
   // }

    public SelectionModule(Resource r){
        super(r);
    }

    public boolean isSelected() {
        if(selected){
            return true;
        }
        return false;
    }

    /*public void setSelected(boolean selected) {
        this.selected = selected;
    }*/
    public void activate(){
        this.selected = true;
        this.highLight.activate();
    }
    public void deactivate(){
        this.selected = false;
        this.highLight.deactivate();
    }

   /* public int howNearPoint(int xDst, int yDst, int minPointingRadius){	//TODO make a check if it even is on screen first.
        int distanceFromCenter = (int) Math.hypot(Math.abs(xDst-this.x), Math.abs(yDst-this.y));
        if(distanceFromCenter <= minPointingRadius){		//Checking if the point is near enough to select
            return distanceFromCenter;
        }else
            return Integer.MAX_VALUE;
    }*/

    public void setHighlightModule(Module highLight){
        this.highLight = highLight;
    }

}

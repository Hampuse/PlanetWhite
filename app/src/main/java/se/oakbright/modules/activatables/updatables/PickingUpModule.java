package se.oakbright.modules.activatables.updatables;

import se.oakbright.modules.Module;
import se.oakbright.battleobjects.pickupobject.PickUpObject;

/**
 * Created by hampuse on 2015-06-26.
 */
public class PickingUpModule extends Module {
    private PickUpObject pickedUpObject = null;

   //TODO
    /*protected boolean tryPickUpObject(PickUpObject pickedUpObject){
        return false;
    }*/

    @Override
    public void activate() {
        //TODO
    }

    @Override
    public void deactivate() {
        //TODO
    }

    protected boolean tryPickUpObject(PickUpObject pickedUpObject){
        if(this.pickedUpObject == null){
            this.pickedUpObject = pickedUpObject;
            return true;
        }else{
            return false;
        }
    }

    public boolean hasPickUpObject() {
        if(this.pickedUpObject != null){
            return true;
        }
            return false;
    }

    public PickUpObject retrievePickUpObject() {
        PickUpObject temp = pickedUpObject;
        pickedUpObject = null;
        return temp;

    }
}

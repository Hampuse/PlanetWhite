package se.oakbright.modules.activatables.updatables;

import se.oakbright.battleobjects.BattleObject;
import se.oakbright.modules.Module;

/**
 * Created by hampuse on 2015-06-26.
 */
public class PickingUpModule extends Module {
    private BattleObject pickedUpObject = null;

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

    protected boolean tryPickUpObject(BattleObject pickedUpObject){
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

    public BattleObject retrievePickUpObject() {
        BattleObject temp = pickedUpObject;
        pickedUpObject = null;
        return temp;

    }
}

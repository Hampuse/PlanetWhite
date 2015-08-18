package se.oakbright.modules.activatables.updatables;

import se.oakbright.modules.Module;
import se.oakbright.resource.Resource;

/**
 * Created by hampuse on 2015-07-02.
 */
public abstract class UpdatableModule extends Module implements Updatable {

    public UpdatableModule(Resource r){
        super(r);
    }
    @Override
    public void activate() {
        getBattleModel().addUpdatable(this);
    }

    @Override
    public void deactivate() {
        getBattleModel().removeUpdatable(this);
    }
}

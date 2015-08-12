package se.oakbright.modules.activatables.updatables;

import se.oakbright.modules.Module;

/**
 * Created by hampuse on 2015-07-02.
 */
public abstract class UpdatableModule extends Module implements Updatable {
    @Override
    public void activate() {
        getBattleModel().addUpdatable(this);
    }

    @Override
    public void deactivate() {
        getBattleModel().removeUpdatable(this);
    }
}

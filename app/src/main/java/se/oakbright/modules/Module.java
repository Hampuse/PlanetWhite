package se.oakbright.modules;

import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.ServiceProvider;

/**
 * Created by hampuse on 2015-06-27.
 */
public abstract class Module {
    public abstract void activate();
    public abstract void deactivate();
    protected BattleModel getBattleModel(){
        return ServiceProvider.getBattleModel();
    }
}

package se.oakbright.modules;

import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.ServiceProvider;

/**
 * Created by hampuse on 2015-06-27.
 */
public abstract class Module {
    //private boolean hasBeenInitialized;
    public abstract void activate();
    public abstract void deactivate();
    //public abstract void initialize();
    //public abstract void assureIsSetup();
    protected BattleModel getBattleModel(){
        return ServiceProvider.getBattleModel();
    }
}

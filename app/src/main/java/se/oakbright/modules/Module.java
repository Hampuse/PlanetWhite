package se.oakbright.modules;

import android.util.Log;

import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.ServiceProvider;
import se.oakbright.resource.Resource;

/**
 * Created by hampuse on 2015-06-27.
 * To work with recursion in ModuleBlueprint, all classes extending Module must have a public
 * Constructor, on the form:
 * public Constructor(Resource r);
 */
public abstract class Module<K> {
    public abstract void activate();
    public abstract void deactivate();

    public Module(Resource r){ //<K>
    }

    protected BattleModel getBattleModel(){
        return ServiceProvider.getBattleModel();
    }
}

package se.oakbright;

import se.oakbright.battleobjects.BattleObject;

/**
 * Created by hampuse on 2015-08-14.
 */
public abstract class Blueprints<T> { // extends BattleObject> {
    T built;

    public T getBuilt(){
        if(built == null){
            built = buildNew();
            RuntimeTests.testForNull(built,"built from buildNew()");
        }
        return built;
    }

    public final void verifyNotNull(Object setupField){
        if(setupField == null){
            throw new NullPointerException("blueprints not setup properly");
        }
    }
    protected abstract T buildNew();
}

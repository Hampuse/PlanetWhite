package se.oakbright;

import se.oakbright.battleobjects.BattleObject;

/**
 * Created by hampuse on 2015-08-14.
 */
public abstract class Blueprint<B> { // extends BattleObject> {
    //<>
    B built;

    public B getBuilt(){
        if(built == null){
            built = buildNew();
            RuntimeTests.testForNull(built,"buildNew() returns null. " + this);
        }
        return built;
    }

    protected abstract B buildNew();
}

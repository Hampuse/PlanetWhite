package se.oakbright;

import se.oakbright.battleobjects.BuilderResource;

/**
 * Created by hampuse on 2015-07-18.
 */
public abstract class Buildable<T> {
    private T built;
    //BuilderResource<T> builderResource;

    /*public Buildable(BuilderResource<T> builderResource){
        this.builderResource = builderResource;
    }*/

    public T getBuilt(){
        if(built == null){
            built = buildNew();
            RuntimeTests.testForNull(built,"built from buildNew()");
        }
        return built;
    }

    public final void verifyNotNull(Object setupField){
        if(setupField == null){
            throw new NullPointerException("builder not setup properly");
        }
    }
    protected abstract T buildNew();

}

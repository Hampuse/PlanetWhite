package se.oakbright.modules.internalpoints;

import se.oakbright.modules.Module;
import se.oakbright.modules.helpers.Positioner;
import se.oakbright.resource.Key;
import se.oakbright.resource.Resource;

/**
 * Created by hampuse on 2015-06-27.
 */
public class MiddlePoint extends InternalPoint {
    private Positioner positioner;


    public MiddlePoint(Resource r){
        super(r);
        this.positioner = r.getThe(Key.POSITIONER);
    }

    @Override
    public int x() {
        return positioner.getX();
    }

    @Override
    public int y() {
        return positioner.getY();
    }

    @Override
    public void activate() {
    }

    @Override
    public void deactivate() {
    }

    /*
    public static class Builder extends Buildable<MiddlePoint>{
        public Buildable<Positioner> positionerBuilder;
        @Override
        protected MiddlePoint buildNew() {
            MiddlePoint middlePoint = new MiddlePoint();
            middlePoint.positioner = positionerBuilder.getBuilt();
            return middlePoint;
        }
    }*/
}


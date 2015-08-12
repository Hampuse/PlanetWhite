package se.oakbright.modules.internalpoints;

import se.oakbright.Buildable;
import se.oakbright.modules.helpers.Positioner;

/**
 * Created by hampuse on 2015-06-27.
 */
public class MiddlePoint implements InternalPoint {
    private Positioner positioner;

    @Override
    public int x() {
        return positioner.getX();
    }

    @Override
    public int y() {
        return positioner.getY();
    }

    public static class Builder extends Buildable<MiddlePoint>{
        public Buildable<Positioner> positionerBuilder;
        @Override
        protected MiddlePoint buildNew() {
            MiddlePoint middlePoint = new MiddlePoint();
            middlePoint.positioner = positionerBuilder.getBuilt();
            return middlePoint;
        }
    }
}


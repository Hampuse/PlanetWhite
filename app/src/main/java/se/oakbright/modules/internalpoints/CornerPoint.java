package se.oakbright.modules.internalpoints;

import se.oakbright.modules.Module;
import se.oakbright.modules.helpers.Positioner;
import se.oakbright.modules.helpers.Shape;
import se.oakbright.resource.Resource;
import static se.oakbright.resource.Key.*;

/**
 * Created by hampuse on 2015-06-27.
 */

public class CornerPoint extends InternalPoint{
    public Shape shape;
    private Positioner positioner;

    public CornerPoint(Resource r){
        super(r);
        this.shape = r.getThe(SHAPE);
        this.positioner = r.getThe(POSITIONER);
    }

    @Override
    public int x() {
        return (int)( positioner.getX() - ((float) shape.getWidthDst()/2) );
    }

    @Override
    public int y() {
        return (int)( positioner.getY() - ((float) shape.getHeightDst()/2) );
    }

    @Override
    public void activate() {
    }

    @Override
    public void deactivate() {
    }

    /*public final void setShape(Shape shape){
        this.shape = shape;
    }*/

    /*
    public static class Builder extends Buildable<CornerPoint>{
        public Buildable<Shape> shapeBuilder;
        public Buildable<Positioner> positionerBuilder;

        @Override
        protected CornerPoint buildNew() {
            CornerPoint cornerPoint = new CornerPoint();
            cornerPoint.shape = shapeBuilder.getBuilt();
            cornerPoint.positioner = positionerBuilder.getBuilt();
            return cornerPoint;
        }
    }*/

}

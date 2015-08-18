package se.oakbright.modules.helpers;


import se.oakbright.modules.Module;
import se.oakbright.resource.Key;
import se.oakbright.resource.Resource;

/**
 * Created by hampuse on 2015-07-24.
 */
public class Bounding extends Module {
    private OrientationMatrix orientationMatrix;
    private Shape shape;

    public Bounding(Resource r){
        super(r);
        this.shape = r.getThe(Key.SHAPE);
        this.orientationMatrix = r.getThe(Key.ORIENTATION_MATRIX);
    }

    public float[] getMappedBoundingPointsKeyPoints(){
        return orientationMatrix.mapPointsAccordingToOrientationMatrix(shape.getBoundingPointsKeyPoints());
    }

    @Override
    public void activate() {

    }

    @Override
    public void deactivate() {

    }
    /*
    public static class Builder extends Buildable<Bounding> {
        public Buildable<OrientationMatrix> orientationMatrixBuilder;
        public Buildable<Shape> shapeBuilder;

        @Override
        protected Bounding buildNew() {
            Bounding bounding = new Bounding();
            bounding.orientationMatrix = orientationMatrixBuilder.getBuilt();
            bounding.shape = shapeBuilder.getBuilt();
            return bounding;
        }
    }*/

}

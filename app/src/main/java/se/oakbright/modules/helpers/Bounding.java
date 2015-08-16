package se.oakbright.modules.helpers;


/**
 * Created by hampuse on 2015-07-24.
 */
public class Bounding {
    private OrientationMatrix orientationMatrix;
    private Shape shape;

    public float[] getMappedBoundingPointsKeyPoints(){
        return orientationMatrix.mapPointsAccordingToOrientationMatrix(shape.getBoundingPointsKeyPoints());
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

package se.oakbright.modules.helpers;

import android.graphics.Matrix;

import se.oakbright.modules.internalpoints.InternalPoint;

/**
 * Created by hampuse on 2015-07-10.
 */
public class OrientationMatrix {
    private Matrix matrix = new Matrix();
    private InternalPoint cornerPoint;
    private InternalPoint pivotPoint;
    private Direction direction;

    private Matrix getOrientationMatrix(){
        this.matrix.reset();
        this.matrix.postTranslate(cornerPoint.x(), cornerPoint.y());
        this.matrix.postRotate(direction.degrees(), pivotPoint.x(), pivotPoint.y());
        return this.matrix;
    }

    //public point mapPoint(p)

    public float mapPointsGetX(int x, int y){
        float[] pointsTemp = new float[]{x,y};
        this.getOrientationMatrix().mapPoints(pointsTemp);
        return pointsTemp[0];
    }

    public float mapPointsGetY(int x, int y){
        float[] pointsTemp = new float[]{x,y};
        this.getOrientationMatrix().mapPoints(pointsTemp);
        return pointsTemp[1];
    }

    public final float[] mapPointsAccordingToOrientationMatrix(float[] points) {
        float[] pointsTemp = points.clone();
        this.getOrientationMatrix().mapPoints(pointsTemp);
        return pointsTemp;
    }

    /*
    public static class Builder extends Buildable<OrientationMatrix> {
        public Buildable<? extends InternalPoint> cornerPointBuilder;
        public Buildable<? extends InternalPoint> pivotPointBuilder;
        public Buildable<Direction> directionBuilder;

        @Override
        protected OrientationMatrix buildNew() {
            OrientationMatrix orientationMatrix = new OrientationMatrix();
            orientationMatrix.cornerPoint = cornerPointBuilder.getBuilt();
            orientationMatrix.pivotPoint = pivotPointBuilder.getBuilt();
            orientationMatrix.direction = directionBuilder.getBuilt();
            return orientationMatrix;
        }
    }*/
}

package se.oakbright.modules.helpers;

import android.graphics.Matrix;

import se.oakbright.modules.Module;
import se.oakbright.modules.internalpoints.InternalPoint;
import se.oakbright.resource.Key;
import se.oakbright.resource.Resource;

/**
 * Created by hampuse on 2015-07-10.
 */
public class OrientationMatrix extends Module {
    private Matrix matrix = new Matrix();
    private InternalPoint cornerPoint;
    private InternalPoint pivotPoint;
    private Direction direction;

    public OrientationMatrix(Resource r){
        super(r);
        this.cornerPoint = r.getThe(Key.CORNER_POINT);
        this.pivotPoint = r.getThe(Key.PIVOT_POINT);
        this.direction = r.getThe(Key.DIRECTION);
    }
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

    @Override
    public void activate() {

    }

    @Override
    public void deactivate() {

    }
}

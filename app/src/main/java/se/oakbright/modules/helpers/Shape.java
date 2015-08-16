package se.oakbright.modules.helpers;

import android.util.Log;

import se.oakbright.RuntimeTests;
import se.oakbright.icons.IconCreater;
import se.oakbright.icons.InIcon;

/**
 * Created by hampuse on 2015-06-26.
 */
public class Shape {
    private InIcon icon;
    private Positioner positioner;

    public float[] getBoundingPointsKeyPoints(){
        return this.icon.getBoundingPointsKeyCoords();
    }

    public final int getBoundingSquareSide(){
        return this.icon.getBoundingSquareSide();
    }

    public int getMaxBoundingRadii() {
        return icon.getMaxBoundingRadii();
    }

    public int getRadii(int angle){
        return this.icon.getRadii(angle);
    }

    public int getWidthDst(){
        return icon.getWidthDst();
    }

    public int getHeightDst(){
        return icon.getHeightDst();
    }

    //TODO REPLACE:
    /*
    public static class Builder extends Buildable<Shape> {
        public IconCreater iconCreater;
        public Buildable<Positioner> positionerBuilder;

        @Override
        protected Shape buildNew() {
            Shape shape = new Shape();
            shape.icon = iconCreater.getBuilt();
            shape.positioner = positionerBuilder.getBuilt();

            RuntimeTests.testForNull(shape, "shape");
            RuntimeTests.testForNull(shape.icon, "icon");
            RuntimeTests.testForNull(shape.positioner, "positioner");

            return shape;
        }
    }*/
}

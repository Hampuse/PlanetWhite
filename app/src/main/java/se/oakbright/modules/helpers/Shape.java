package se.oakbright.modules.helpers;

import se.oakbright.icons.InIcon;
import se.oakbright.modules.Module;
import se.oakbright.resource.Resource;
import static se.oakbright.resource.Key.*;


/**
 * Created by hampuse on 2015-06-26.
 */
public class Shape extends Module {
    private InIcon icon;
    private Positioner positioner;

    /*public Shape(){ //TODO AWAY, just to try
        super(new Resource());
    }*/

    public Shape(Resource r){
        super(r);
        //throw new RuntimeException("test");
        icon = r.getThe(ICON);
        positioner = r.getThe(POSITIONER);
    }

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

    @Override
    public void activate() {

    }

    @Override
    public void deactivate() {

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

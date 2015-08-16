package se.oakbright.modules.internalpoints;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import se.oakbright.modules.helpers.Positioner;
import se.oakbright.modules.helpers.Shape;

import static junit.framework.Assert.assertEquals;

/**
 * Created by hampuse on 2015-07-10.
 */
//TODO:
    /*
public class CornerPointTest {
    CornerPoint cornerPoint;

    @Mock Shape shape;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        CornerPoint.Builder cornerPointBuilder = new CornerPoint.Builder();

        Positioner positioner = new Positioner();
        positioner.setPosition(3,3);
        cornerPointBuilder.positionerBuilder = mock(Positioner.Builder.class);
        when(cornerPointBuilder.positionerBuilder.getBuilt()).thenReturn(positioner);
        cornerPointBuilder.shapeBuilder = mock(Shape.Builder.class);
        when(cornerPointBuilder.shapeBuilder.getBuilt()).thenReturn(shape);

        cornerPoint = cornerPointBuilder.getBuilt();
    }
*/
    /**
     * Position of
     * cornerpoint and middlepoint:
     *
     *  01234
     * 0
     * 1 c
     * 2
     * 3   m
     * 4
     */

/*
    @Test
    public void testX(){
        when(shape.getWidthDst()).thenReturn(4);
        assertEquals(1, cornerPoint.x());
    }

    @Test
    public void testY(){
        when(shape.getHeightDst()).thenReturn(4);
        assertEquals(1, cornerPoint.y());
    }
}*/

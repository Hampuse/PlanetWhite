package se.oakbright.modules.internalpoints;

import org.junit.Before;
import org.junit.Test;

import se.oakbright.modules.internalpoints.MiddlePoint;
import se.oakbright.modules.helpers.Positioner;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by hampuse on 2015-07-08.
 */
public class MiddlePointTest {
    MiddlePoint middlePoint;

    @Before
    public void setup(){
        MiddlePoint.Builder middlePointBuilder = new MiddlePoint.Builder();
        Positioner positioner = new Positioner();
        positioner.setPosition(5,4);
        middlePointBuilder.positionerBuilder = mock(Positioner.Builder.class);
        when(middlePointBuilder.positionerBuilder.getBuilt()).thenReturn(positioner);

        middlePoint = middlePointBuilder.getBuilt();
    }

    @Test
    public void testX(){
        assertEquals(5, middlePoint.x());
    }

    @Test
    public void testY(){
        assertEquals(4, middlePoint.y());
    }
}

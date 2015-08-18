package se.oakbright.modules.helpers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;

import se.oakbright.modules.internalpoints.CornerPoint;
import se.oakbright.modules.internalpoints.MiddlePoint;
import se.oakbright.modules.helpers.Positioner;
import se.oakbright.resource.Resource;

import static junit.framework.Assert.assertEquals;

/**
 * Created by hampuse on 2015-07-06.
 */
public class PositionerTest {

    Positioner positioner;
    @Mock CornerPoint cornerPointMock;
    @Mock MiddlePoint middlePointMock;
    //@Mock Shape shapeStub;

    @Before
    public void setUp(){
        //MockitoAnnotations.initMocks(this);
        positioner = new Positioner(new Resource());
       // positioner.setCornerPoint(cornerPointMock);
       // positioner.setPivotPoint(middlePointMock);
    }

    @Test
    public void testSetAndGetXandY(){
        int x = 0;
        int y = 0;
        setAndGet(x,y);

        x = -50;
        y = -100;
        setAndGet(x,y);

        x = 5090823;
        y = 243823;
        setAndGet(x,y);
    }

    private void setAndGet(int x, int y){
        positioner.setPosition(x,y);
        assertEquals(x, positioner.getX());
        assertEquals(y, positioner.getY());
    }
}

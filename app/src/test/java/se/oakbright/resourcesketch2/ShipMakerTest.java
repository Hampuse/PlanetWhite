package se.oakbright.resourcesketch2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by hampuse on 2015-08-16.
 */
public class ShipMakerTest {
    @Before
    public void setup(){

    }

    @Test
    public void try_make_ship(){
        ShipMaker shipMaker = new ShipMaker();
        Bob<ShipInterface> ship = shipMaker.make();
        assertNotNull(ship);
        assertNotNull(ship.aModule1);
    }
}

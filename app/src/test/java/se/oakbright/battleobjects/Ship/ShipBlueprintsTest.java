package se.oakbright.battleobjects.ship;

import org.junit.Before;
import org.junit.Test;

import se.oakbright.battleobjects.BattleObject;
import se.oakbright.battleobjects.BattleObjectTest;

/**
 * Created by hampuse on 2015-08-16.
 */
public class ShipBlueprintsTest {
    BattleObject<ShipCommands> ship;

    @Before
    public void setup(){
        ShipBlueprints shipBlueprints = new ShipBlueprints();
        ship = shipBlueprints.getBuilt();
    }

    @Test
    public void test_correct_battleObject(){
        BattleObjectTest.Test(ship);
    }
}

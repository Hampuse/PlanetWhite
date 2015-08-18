package se.oakbright.battleobjects.ship;

import org.junit.Before;
import org.junit.Test;

import se.oakbright.battleobjects.BattleObject;
import se.oakbright.battleobjects.BattleObjectTest;
import se.oakbright.icons.IconId;
import se.oakbright.icons.InIcon;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.BattleTeam;
import se.oakbright.planetwhite.ServiceProvider;
import se.oakbright.resource.Resource;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static se.oakbright.resource.Key.*;

/**
 * Created by hampuse on 2015-08-16.
 */
public class ShipBlueprintTest {
    BattleTeam team = mock(BattleTeam.class);
    ShipBlueprint shipBlueprints;
    Resource shipResource;
    BattleModel battleModel = mock(BattleModel.class);
    InIcon icon = mock(InIcon.class);

    @Before
    public void setup(){
        ServiceProvider.setBattleModel(battleModel);
        when(battleModel.getIcon(any(IconId.class))).thenReturn(icon);

        shipBlueprints = new ShipBlueprint(team);
        shipResource = shipBlueprints.shipResource;
    }

    @Test
    public void test_correct_battleObject(){
        BattleObject<ShipCommands> ship = shipBlueprints.getBuilt();
        BattleObjectTest.Test(ship);
    }

    @Test
    public void test_can_get_Icon(){
        assertNotNull(shipResource.getThe(ICON));
    }

    @Test
    public void test_can_get_Shape(){
        assertNotNull(shipResource.getThe(SHAPE));
    }

    @Test
    public void test_can_get_Positioner(){
        assertNotNull(shipResource.getThe(POSITIONER));
    }

    @Test
    public void test_can_get_Direction(){
        assertNotNull(shipResource.getThe(DIRECTION));
    }

    @Test
    public void test_can_get_Health(){
        assertNotNull(shipResource.getThe(HEALTH));
    }

    @Test
    public void test_can_get_commandHandler(){
        assertNotNull(shipResource.getThe(COMMAND_HANDLER));
    }
}

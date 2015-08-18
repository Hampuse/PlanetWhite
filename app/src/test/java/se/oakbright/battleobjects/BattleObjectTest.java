package se.oakbright.battleobjects;

import org.junit.Before;
import org.junit.Test;

import se.oakbright.battleobjects.statemachine.CommandReceiverHolder;
import se.oakbright.modules.helpers.Health;
import se.oakbright.resource.Resource;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static se.oakbright.resource.Key.*;

/**
 * Created by hampuse on 2015-07-12.
 */

public class BattleObjectTest {
    BattleObject battleObject;
    CommandReceiverHolder commandHandler = mock(CommandReceiverHolder.class);
    Health health;

    @Before
    public void setup(){
        health = new Health(getMockedResource());
        health.setFullHp(1);
        battleObject = new BattleObject(getMockedResource());
    }

    private Resource getMockedResource(){
        Resource r = mock(Resource.class);
        when(r.getThe(COMMAND_HANDLER)).thenReturn(commandHandler);
        when(r.getThe(HEALTH)).thenReturn(health);
        return r;
    }

    public static void Test(BattleObject ob){    //To be called by other test files.
        assertNotNull(ob.shape);
        assertNotNull(ob.health);
        assertNotNull(ob.positioner);
        assertNotNull(ob.direction);
        assertNotNull(ob.team);
        assertNotNull(ob.commandHandler);
    }

    @Test
    public void test_OutOfHpObserver_deactivates_battleobject(){
        health.tryDecreaseHp(1);    //Should make the health zero, which should call the notify on
                                    // the observer injected by battleobject, which should result in a deactivate() call on the commandHandler. and IAOBservers
        verify(commandHandler).deactivate();
        //TODO verify(iAObserver.notified);
    }
/*
    public class FakeResourceImpl implements BattleObject.BattleObjectResource{
        @Override
        public Shape getShape() {
            return null;
        }

        @Override
        public Health getHealth() {
            return health;
        }

        @Override
        public Positioner getPositioner() {
            return null;
        }

        @Override
        public Direction getDirection() {
            return null;
        }

        @Override
        public BattleTeam getTeam() {
            return null;
        }

        @Override
        public CommandReceiverHolder getCommandHandler() {
            return commandHandler;
        }
    }*/
}

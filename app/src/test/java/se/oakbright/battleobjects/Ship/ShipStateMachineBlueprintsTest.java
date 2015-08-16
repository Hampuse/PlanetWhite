package se.oakbright.battleobjects.ship;

import org.junit.Before;
import org.junit.Test;

import se.oakbright.battleobjects.statemachine.CommandReceiverHolder;
import se.oakbright.battleobjects.BattleObject;
import se.oakbright.battleobjects.statemachine.State;
import se.oakbright.battleobjects.statemachine.StateMachine;
import se.oakbright.modules.Module;
import se.oakbright.modules.helpers.Direction;
import se.oakbright.modules.helpers.Health;
import se.oakbright.modules.helpers.Positioner;
import se.oakbright.modules.helpers.Shape;
import se.oakbright.planetwhite.BattleTeam;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertSame;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Created by hampuse on 2015-07-12.
 */
public class ShipStateMachineBlueprintsTest {
    BattleObject<ShipCommands> ship;
    Module moduleInHidden = mock(Module.class);
    Module moduleInReadyToLaunch = mock(Module.class);
    Module moduleInOutThere = mock(Module.class);

    StateMachine stateMachine;
    State initState;
    State hiddenState;

    BattleTeam team;

    @Before
    public void setup(){
        team = mock(BattleTeam.class);
        ship = new BattleObject<ShipCommands>(new FakeResourceImpl());
    }

    public class FakeResourceImpl implements BattleObject.BattleObjectResource<ShipCommands>{

        @Override
        public Shape getShape() {
            return null;
        }

        @Override
        public Health getHealth() {
            return null;
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
        public CommandReceiverHolder<ShipCommands> getCommandHandler() {
            ShipStateMachineBlueprints stateMachineBlueprints = new ShipStateMachineBlueprints();
            initState = stateMachineBlueprints.init;
            hiddenState = stateMachineBlueprints.hidden;
            stateMachineBlueprints.hidden.addActiveModule(moduleInHidden);
            stateMachineBlueprints.readyToLaunch.addActiveModule(moduleInReadyToLaunch);
            stateMachineBlueprints.outThere.addActiveModule(moduleInOutThere);
            stateMachine = stateMachineBlueprints.getBuilt();
            return stateMachine;
        }
    }

    @Test
    public void  test_that_stateMachine_is_the_right_one(){
        //CommandReceiverHolder<ShipCommands> fromResource = new FakeResourceImpl().getCommandHandler();
        assertSame(stateMachine, initState.transitionObserver);
    }

    @Test
    public void test_for_null_pointers(){
        assertNotNull(ship);
        assertNotNull(stateMachine);
        assertNotNull(initState.transitionObserver);
    }

    @Test
    public void test_that_command_returns_correct_commandReceiver(){
        assertSame(initState.getCommandReceiver(), ship.command());
    }

    @Test
    public void verify_that_states_activeModules_are_correct(){
        //starts in init State, no modules should have been activated
        verifyZeroInteractions(moduleInHidden);
        verifyZeroInteractions(moduleInOutThere);
        verifyZeroInteractions(moduleInReadyToLaunch);

        ship.command().activate();    //should enter hidden
        verify(moduleInHidden).activate();
        verifyZeroInteractions(moduleInOutThere);
        verifyZeroInteractions(moduleInReadyToLaunch);

        ship.command().getReadyToLaunch();     //enter readyToLaunch
        verify(moduleInHidden).deactivate();
        verify(moduleInReadyToLaunch).activate();
        verifyZeroInteractions(moduleInOutThere);

        ship.command().launch();   //enter outThere
        verifyZeroInteractions(moduleInHidden);
        verify(moduleInReadyToLaunch).deactivate();
        verify(moduleInOutThere).activate();
    }
}

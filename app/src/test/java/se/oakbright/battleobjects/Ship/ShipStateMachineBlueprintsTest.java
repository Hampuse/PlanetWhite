package se.oakbright.battleobjects.Ship;

import org.junit.Before;
import org.junit.Test;

import se.oakbright.Blueprints;
import se.oakbright.CommandReceiver;
import se.oakbright.CommandReceiverHolder;
import se.oakbright.battleobjects.BattleObject;
import se.oakbright.battleobjects.Ship.ShipStateMachineBlueprints;
import se.oakbright.battleobjects.ShipCommands;
import se.oakbright.battleobjects.statemachine.State;
import se.oakbright.battleobjects.statemachine.StateMachine;
import se.oakbright.modules.Module;
import se.oakbright.modules.helpers.Direction;
import se.oakbright.modules.helpers.Health;
import se.oakbright.modules.helpers.IconModule;
import se.oakbright.modules.helpers.Positioner;
import se.oakbright.modules.helpers.Shape;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.BattleTeam;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertSame;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Created by hampuse on 2015-07-12.
 */
public class ShipStateMachineBlueprintsTest {
    BattleObject<ShipCommands> ship;
    //Buildable<Module> moduleInHiddenBuilder, moduleInReadyToLaunchBuilder, moduleInOutThereBuilder;
    Module moduleInHidden = mock(Module.class);
    Module moduleInReadyToLaunch = mock(Module.class);
    Module moduleInOutThere = mock(Module.class);

    StateMachine stateMachine;
    State initState;
    State hiddenState;

    IconModule.Builder IconModuleBuilder = mock(IconModule.Builder.class);
    BattleModel battleModel;
    BattleTeam team;
    //@Mock IconId iconId;

    @Before
    public void setup(){
        team = mock(BattleTeam.class);
        //IconModule iconModule = mock(IconModule.class);
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
            //stateMachine = spy(stateMachineBlueprints.getBuilt());
            stateMachine = stateMachineBlueprints.getBuilt();
            return stateMachine;
        }
    }

    @Test
    public void  test_that_stateMachine_is_the_right_one(){
        //CommandReceiverHolder<ShipCommands> fromResource = new FakeResourceImpl().getCommandHandler();
        assertSame(stateMachine,ship.commandHandler);
        assertSame(stateMachine, initState.transitionObserver);
    }

    @Test
    public void test_for_null_pointers(){
        assertNotNull(ship);
        assertNotNull(stateMachine);
        assertNotNull(initState.transitionObserver);
        assertSame(stateMachine, initState.transitionObserver);
    }

    @Test
    public void test_that_command_returns_correct_commandReceiver(){
        ShipCommands commandReceiver = ship.command();
        assertSame(initState.getCommandReceiver(), ship.command());

        assertSame(stateMachine, initState.transitionObserver);
        ship.command().activate();
        //verify(commandReceiver).activate();
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

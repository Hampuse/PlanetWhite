package se.oakbright.battleobjects.ship;

import org.junit.Before;
import org.junit.Test;


import se.oakbright.battleobjects.BattleObject;
import se.oakbright.battleobjects.statemachine.State;
import se.oakbright.battleobjects.statemachine.StateMachine;
import se.oakbright.modules.Module;

import se.oakbright.planetwhite.BattleTeam;
import se.oakbright.resource.Resource;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static se.oakbright.resource.Key.*;

/**
 * Created by hampuse on 2015-07-12.
 */
public class ShipStateMachineBlueprintTest {
    //BattleObject<ShipCommands> ship;
    Module moduleInHidden = mock(Module.class);
    Module moduleInReadyToLaunch = mock(Module.class);
    Module moduleInOutThere = mock(Module.class);

    StateMachine<ShipCommands> stateMachine;
    State initState;
    State hiddenState;

    BattleTeam team;

    //final Resource fakeResource = getFakeResource();

    @Before
    public void setup() {
        team = mock(BattleTeam.class);
        stateMachine = getStateMachineBlueprints().getBuilt();
    }

    private ShipStateMachineBlueprint getStateMachineBlueprints() {
        ShipStateMachineBlueprint stateMachineBlueprints = new ShipStateMachineBlueprint(new Resource());   //
        initState=stateMachineBlueprints.init;
        hiddenState=stateMachineBlueprints.hidden;

        // Adding modules directly to modules, in non-tests modules are retreived throw Resource.
        stateMachineBlueprints.hidden.addActiveModule(moduleInHidden);
        stateMachineBlueprints.readyToLaunch.addActiveModule(moduleInReadyToLaunch);
        stateMachineBlueprints.outThere.addActiveModule(moduleInOutThere);

        stateMachine = stateMachineBlueprints.getBuilt();
        return stateMachineBlueprints;
    }

    @Test
    public void  test_that_stateMachine_is_the_right_one(){
        //CommandReceiverHolder<ShipCommands> fromResource = new FakeResourceImpl().getCommandHandler();
        assertSame(stateMachine, initState.transitionObserver);
    }

    @Test
    public void test_for_null_pointers(){
        assertNotNull(stateMachine);
        assertNotNull(initState.transitionObserver);
    }

    @Test
    public void test_that_command_returns_correct_commandReceiver(){
        assertSame(initState.getCommandReceiver(), stateMachine.getCommandReceiver());
    }

    @Test
    public void verify_that_states_activeModules_are_correct(){
        //starts in init State, no modules should have been activated
        verifyZeroInteractions(moduleInHidden);
        verifyZeroInteractions(moduleInOutThere);
        verifyZeroInteractions(moduleInReadyToLaunch);

        stateMachine.getCommandReceiver().activate();    //should enter hidden
        verify(moduleInHidden).activate();
        verifyZeroInteractions(moduleInOutThere);
        verifyZeroInteractions(moduleInReadyToLaunch);

        stateMachine.getCommandReceiver().getReadyToLaunch();     //enter readyToLaunch
        verify(moduleInHidden).deactivate();
        verify(moduleInReadyToLaunch).activate();
        verifyZeroInteractions(moduleInOutThere);

        stateMachine.getCommandReceiver().launch();   //should enter outThere
        verifyZeroInteractions(moduleInHidden);
        verify(moduleInReadyToLaunch).deactivate();
        verify(moduleInOutThere).activate();
    }
}

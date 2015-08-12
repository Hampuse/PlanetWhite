package se.oakbright.battleobjects.statemachine;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertSame;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by hampuse on 2015-07-12.
 */
public class ShipStateMachineBuilderTest {
    State<ShipCommandHandler> stateInit, stateHidden, stateReadyToLaunch, stateOutThere;
    ShipCommandHandler commandHandlerInit;
    StateMachine<State<ShipCommandHandler>> stateMachine;

    @Before
    public void setup(){
        ShipStateMachineBuilder stateMachineBuilder = new ShipStateMachineBuilder();
        stateMachine = stateMachineBuilder.getBuilt();

        stateInit = stateMachine.getCurrentState();

        commandHandlerInit = stateInit.getCommandHandler();

        currentCommandHandler().activate();
        stateHidden = stateMachine.getCurrentState();

        currentCommandHandler().commandGetReadyToLaunch();
        stateReadyToLaunch = stateMachine.getCurrentState();

        currentCommandHandler().commandLaunch();
        stateOutThere = stateMachine.getCurrentState();

        currentCommandHandler().deactivate();
    }

    @Test
    public void testGetStandard(){
        assertNotNull(commandHandlerInit);
        assertNotNull(stateInit);
        assertNotNull(stateMachine);

        assertNotSame(stateInit,stateHidden);
        assertNotSame(stateInit, stateReadyToLaunch);
        assertNotSame(stateInit, stateOutThere);
        assertNotSame(stateHidden, stateReadyToLaunch);
        assertNotSame(stateHidden, stateOutThere);
        assertNotSame(stateReadyToLaunch, stateOutThere);
    }

    private ShipCommandHandler currentCommandHandler(){
        State<ShipCommandHandler> currentState = stateMachine.getCurrentState();
        return currentState.getCommandHandler();
    }

    @Test
    public void verifyInitState() {
        assertSame(stateInit, stateMachine.getCurrentState());
        currentCommandHandler().deactivate();
        currentCommandHandler().commandGetReadyToLaunch();
        currentCommandHandler().commandLaunch();
        assertSame(stateInit, stateMachine.getCurrentState());  //Should still be in same state

        currentCommandHandler().activate();
        assertSame(stateHidden, stateMachine.getCurrentState());

        verifyThatJumpsToInitOnDeactivate();
    }

    @Test
    public void verifyHiddenState(){
        currentCommandHandler().activate();
        assertSame(stateHidden, stateMachine.getCurrentState());
        currentCommandHandler().commandLaunch();
        currentCommandHandler().activate();
        assertSame(stateHidden, stateMachine.getCurrentState()); //Should not have changed state

        currentCommandHandler().commandGetReadyToLaunch();
        assertSame(stateReadyToLaunch, stateMachine.getCurrentState());

        verifyThatJumpsToInitOnDeactivate();
    }

    @Test
    public void verifyReadyToLaunchState(){
        currentCommandHandler().activate();
        currentCommandHandler().commandGetReadyToLaunch();
        assertSame(stateReadyToLaunch, stateMachine.getCurrentState());
        currentCommandHandler().activate();
        currentCommandHandler().commandGetReadyToLaunch();
        assertSame(stateReadyToLaunch, stateMachine.getCurrentState()); //Should not have  changed

        currentCommandHandler().commandLaunch();
        assertSame(stateOutThere, stateMachine.getCurrentState());

        verifyThatJumpsToInitOnDeactivate();
    }

    @Test
    public void verifyOutThereState(){
        currentCommandHandler().activate();
        currentCommandHandler().commandGetReadyToLaunch();
        currentCommandHandler().commandLaunch();
        assertSame(stateOutThere, stateMachine.getCurrentState());
        currentCommandHandler().activate();
        currentCommandHandler().commandGetReadyToLaunch();
        currentCommandHandler().commandLaunch();
        assertSame(stateOutThere, stateMachine.getCurrentState());  //Should not have changed

        verifyThatJumpsToInitOnDeactivate();
    }

    private void verifyThatJumpsToInitOnDeactivate(){
        currentCommandHandler().deactivate();
        assertSame(stateInit, stateMachine.getCurrentState());
    }
}

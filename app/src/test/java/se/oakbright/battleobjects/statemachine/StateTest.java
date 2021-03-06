package se.oakbright.battleobjects.statemachine;

import org.junit.Before;
import org.junit.Test;

import se.oakbright.modules.Module;

import static junit.framework.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Created by hampuse on 2015-07-13.
 */
public class StateTest {
    CommandHandler commandHandler1;
    CommandHandler commandHandler2;
    State state1;
    State state2;
    StateMachine stateMachine = mock(StateMachine.class);

    @Before
    public void setup(){
        commandHandler1 = new ShipCommandHandler();// mock(CommandHandler.class);
        state1 = new State(commandHandler1);
        state1.setTransitionObserver(stateMachine); //Is normally done by stateMachine.

        commandHandler2 = new ShipCommandHandler();
        state2 = new State(commandHandler2);

        commandHandler1.setTransitionOnActivate(state2);
    }

    @Test
    public void verifyTransitionOnActivateCommand(){
        commandHandler1.activate();
        verify(stateMachine).newTransition(state1, state2);
    }

    @Test
    public void transitionToTest(){
        Module module1 = mock(Module.class);
        Module module2 = mock(Module.class);
        Module module12 = mock(Module.class);
        state1.addActiveModule(module1);
        state1.addActiveModule(module12);
        state2.addActiveModule(module12);
        state2.addActiveModule(module2);

        state1.transitionTo(state2);
        verify(module1).deactivate();
        verify(module2).activate();
        verifyZeroInteractions(module12);   //module12 is active in both, and should not have been changed

    }
}

package se.oakbright.battleobjects.statemachine;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertSame;
import static junit.framework.Assert.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by hampuse on 2015-07-13.
 */
public class StateMachineTest {
    State state1 = mock(State.class);
    State state2 = mock(State.class);
    StateMachine stateMachine;

    @Before
    public void setup(){
        stateMachine = new StateMachine(state1);
    }
    @Test
    public void testConstructor(){
        assertSame(state1, stateMachine.getCurrentState());
    }

    @Test
    public void testNewTransition(){
        stateMachine.newTransition(state1, state2);
        assertSame(state2, stateMachine.getCurrentState());

        try{
            stateMachine.newTransition(state1, state2);
            fail("IllegalStateException should have been thrown");
        }catch (IllegalStateException e){
        }
    }
}

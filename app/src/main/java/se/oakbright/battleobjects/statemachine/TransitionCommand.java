package se.oakbright.battleobjects.statemachine;

import se.oakbright.battleobjects.statemachine.State;

/**
 * Created by hampuse on 2015-08-14.
 */
public class TransitionCommand {
    private State hostingState;
    private State transitionToState;

    public TransitionCommand(State hostingState){
        this.hostingState = hostingState;
    }

    public void execute(){
        this.hostingState.transitionTo(transitionToState);
    }

    public void setTransition(State state){
        this.transitionToState = state;
    }
}

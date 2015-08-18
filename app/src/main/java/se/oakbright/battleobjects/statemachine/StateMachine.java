package se.oakbright.battleobjects.statemachine;

import se.oakbright.modules.Module;

/**
 * Created by hampuse on 2015-06-26.
 */
public class StateMachine<C> implements CommandReceiverHolder<C> {
    private State<C> currentState;
    private State<C> initState;

    public StateMachine(State<C> initState){
        this.initState = initState;
        setCurrentState(initState);
    }

    private void setCurrentState(State<C> state){
        currentState = state;
        currentState.setTransitionObserver(this);
    }

    public void newTransition(State oldState, State<C> newState){
        if(oldState != currentState){
            throw new IllegalStateException();
        }
        currentState.removeTransitionObserver(this);
        setCurrentState(newState);
    }

    @Override
    public C getCommandReceiver() {
        return currentState.getCommandReceiver();
    }

    public void deactivate(){
        currentState.transitionTo(initState);
    }
}
package se.oakbright.battleobjects.statemachine;

import java.util.Set;

import se.oakbright.Buildable;
import se.oakbright.modules.Module;

/**
 * Created by hampuse on 2015-06-26.
 */
public class StateMachine<S extends State> {
    private S currentState;

    public StateMachine(S initState){
        setCurrentState(initState);
    }

    private void setCurrentState(S state){
        currentState = state;
        currentState.setTransitionObserver(this);
    }

    public S getCurrentState(){
        return currentState;
    }

    public void newTransition(State oldState, S newState){
        if(oldState != currentState){
            throw new IllegalStateException();
        }
        currentState.removeTransitionObserver(this);
        setCurrentState(newState);
    }

    public abstract static class Builder<I extends BattleObjectInterface> extends Buildable<StateMachine<State<I>>>{
        //Set<State> states; //TODO SEt
        public State initialState;

        @Override
        public StateMachine<State<I>> buildNew() {
            Set<State> states = getStates();
            StateMachine stateMachine = new StateMachine(initialState);
            /*for (State state : states) {
                buildModules(state);
            }*/
            return stateMachine;
        }

        protected abstract Set<State> getStates();

       /* private void buildModules(State state){
            Set<Buildable<Module>> moduleBuilders = state.getModuleBuilders();
            for(Buildable<Module> moduleBuilder: moduleBuilders){
                state.addActiveModule(moduleBuilder.getBuilt());
            }
        }*/
    }
}

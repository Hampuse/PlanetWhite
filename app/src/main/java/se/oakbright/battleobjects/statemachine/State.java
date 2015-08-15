package se.oakbright.battleobjects.statemachine;

import java.util.HashSet;
import java.util.Set;

import se.oakbright.BattleObjectCommands;
import se.oakbright.Buildable;
import se.oakbright.TransitionCommand;
import se.oakbright.battleobjects.BattleObject;
import se.oakbright.modules.Module;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by hampuse on 2015-06-26.
 */
public class State<C> {
    public StateMachine transitionObserver;
    private Set<Module> activeModules = new HashSet<Module>();
    public C commandReceiver;

    /*public State(C commandReceiver){
        this.commandReceiver = commandReceiver;
    }*/

    public C getCommandReceiver(){
        return commandReceiver;
    }

    public void setTransitionObserver(StateMachine transitionObserver){
        this.transitionObserver = transitionObserver;
    }

    public void removeTransitionObserver(StateMachine transitionObserver){
        this.transitionObserver = null;
    }

    public void transitionTo(State newState){
        changeState(this, newState);
        transitionObserver.newTransition(this,newState);
    }

    private static void changeState(State from, State to){
        Set<Module> oldActives = from.activeModules;
        Set<Module> newActives = to.activeModules;
        for(Module newActive: newActives){   // Activates all modules in new state, that was not active in old state
            if( ! oldActives.contains(newActive)) {
                newActive.activate();
            }
        }
        for(Module oldActive : oldActives){ // Deactivates all modules in old state that is not active in new state
            if( ! newActives.contains(oldActive)){
                oldActive.deactivate();
            }
        }
        //The rest of the modules are active in both old and new, no need to do anything with them.
    }

    public void addActiveModule(Module module){
        if(module == null){
            throw new IllegalArgumentException();
        }
        this.activeModules.add(module);
    }
}

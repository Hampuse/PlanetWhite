package se.oakbright.battleobjects.statemachine;

import android.util.Log;

/**
 * Created by hampuse on 2015-06-28.
 */
/*public abstract class CommandHandler implements BattleObjectInterface{
    private boolean isInAnActiveState = false;
    State hostingState;
    private Transition onActivate, onDeactivate;


    public CommandHandler(State hostingState){
        this.hostingState = hostingState;
        hostingState.setCommandHandler(this);
        onActivate = new NullTransition();
        onDeactivate = new NullTransition();
    }

    public void setHostingState(State state){
        this.hostingState = state;
    }

    public void activate(){
        onActivate.execute();
        this.isInAnActiveState = true;
    }

    public void deactivate(){ onDeactivate.execute();
        this.isInAnActiveState = false;
    }

    public  boolean isInAnActiveState(){
        return isInAnActiveState;
    }

    void setTransitionOnActivate(State toState){
        onActivate = new Transition(toState);
    }

    void setTransitionOnDeactivate(State toState){
        onDeactivate = new Transition(toState);
    }

    public class Transition {   //TODO put in seperate file?
        private State toState;

        private Transition(){
        }

        public Transition(State toState){
            this.toState = toState;
        }

        public void execute(){
            hostingState.transitionTo(toState);
        }
    }

    class NullTransition extends Transition{
        NullTransition(){
        }
        @Override
        public void execute(){
        }
    }
}*/

package se.oakbright.battleobjects.Ship;

import android.util.Log;

import se.oakbright.battleobjects.statemachine.ShipInterface;
import se.oakbright.battleobjects.statemachine.State;

/**
 * Created by hampuse on 2015-06-28.
 */
/*public class ShipCommandHandler extends CommandHandler implements ShipInterface {
    private boolean isLaunched = false;
    private Transition onGetReadyToLaunch, onLaunch;

    public ShipCommandHandler(State state){
        super(state);
        this.onGetReadyToLaunch = new NullTransition();
        this.onLaunch = new NullTransition();
    }

    public void commandLaunch() {
        isLaunched = true;
        onLaunch.execute();
    }

    public void commandGetReadyToLaunch() {
        onGetReadyToLaunch.execute();
    }

    @Override
    public void deactivate(){
        isLaunched = false;
        super.deactivate();
    }

    public void setTransitionOnGetReadyToLaunch(State toState){
        this.onGetReadyToLaunch = new Transition(toState);
    }

    public void setTransitionOnLaunch(State toState){
        this.onLaunch = new Transition(toState);
    }

    public boolean isLaunched(){
            return isLaunched;
    }


    /*public static final ShipCommandHandler wrapper(StateMachine<State<ShipCommandHandler>> stateMachine) {
        return new ShipCommandHandlerWrapper(stateMachine);
    }

   /* private final static class ShipCommandHandlerWrapper extends ShipCommandHandler{   // implements ShipCommands
        private StateMachine<State<ShipCommandHandler>> stateMachine;

        private ShipCommandHandlerWrapper(StateMachine<State<ShipCommandHandler>> stateMachine){
            this.stateMachine = stateMachine;
        }

        private ShipCommandHandler currentStateCommandHandler(){
            State<ShipCommandHandler> currentState = stateMachine.getCurrentState();
            return currentState.getCommandHandler();
        }

        public void commandLaunch(){
            currentStateCommandHandler().commandLaunch();
        }

    }*/
//}
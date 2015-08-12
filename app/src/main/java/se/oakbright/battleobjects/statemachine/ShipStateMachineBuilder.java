package se.oakbright.battleobjects.statemachine;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hampuse on 2015-07-12.
 */
public class ShipStateMachineBuilder extends StateMachine.Builder<ShipCommandHandler>{
    public State init, hidden, readyToLaunch, outThere;

    public ShipStateMachineBuilder(){
        init = new State();
        ShipCommandHandler commandHandlerInit = new ShipCommandHandler(init);
        //ShipCommandHandler commandHandlerInit = new ShipCommandHandler();
        //init = new State(commandHandlerInit); // new InitCommandHandler());

        hidden = new State();
        ShipCommandHandler commandHandlerHidden = new ShipCommandHandler(hidden);
        //hidden = new State(commandHandlerHidden);

        readyToLaunch = new State();
        ShipCommandHandler commandHandlerReadyToLaunch = new ShipCommandHandler(readyToLaunch);
        //readyToLaunch = new State(commandHandlerReadyToLaunch);

        outThere = new State();
        ShipCommandHandler commandHandlerOutThere = new ShipCommandHandler(outThere);
        //outThere = new State(commandHandlerOutThere);

        // CONNECTIONS
        commandHandlerInit.setTransitionOnActivate(hidden);

        commandHandlerHidden.setTransitionOnGetReadyToLaunch(readyToLaunch);
        commandHandlerHidden.setTransitionOnDeactivate(init);

        commandHandlerReadyToLaunch.setTransitionOnLaunch(outThere);
        commandHandlerReadyToLaunch.setTransitionOnDeactivate(init);

        commandHandlerOutThere.setTransitionOnDeactivate(init);

        super.initialState = init;
    }

    @Override
    protected Set<State> getStates() {
        Set<State> states = new HashSet<State>();
        states.add(init);
        states.add(hidden);
        states.add(readyToLaunch);
        states.add(outThere);
        return states;
    }

    /* @Override
    public StateMachine<State<ShipCommandHandler>> build(){
        //Build all components in states:
        init.buildAllModules

        // STATE MACHINE
        StateMachine stateMachine = new StateMachine(init);
        return stateMachine;
    }*/
}

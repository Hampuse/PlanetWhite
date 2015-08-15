package se.oakbright.battleobjects.Ship;

import se.oakbright.Blueprints;
import se.oakbright.battleobjects.ShipCommands;
import se.oakbright.battleobjects.statemachine.State;
import se.oakbright.battleobjects.statemachine.StateMachine;

/**
 * Created by hampuse on 2015-08-15.
 */
public class ShipStateMachineBlueprints extends Blueprints<StateMachine<ShipCommands>>{
    State<ShipCommands> init = new State<ShipCommands>(); //new ShipCommandReceiver());
    State<ShipCommands> hidden = new State<ShipCommands>();
    State<ShipCommands> readyToLaunch = new State<ShipCommands>();
    State<ShipCommands> outThere = new State<ShipCommands>();

    @Override
    protected StateMachine<ShipCommands> buildNew() {
        StateMachine<ShipCommands> stateMachine = new StateMachine<ShipCommands>(init);
        createInitCommandReceiver();
        createHiddenCommandReceiver();
        createReadyToLaunchCommandReceiver();
        createOutThereCommandReceiver();
        return stateMachine;
    }

    private void hej(){
        throw new RuntimeException("Hej");
    }

    private void createInitCommandReceiver(){
        ShipCommandReceiver c = new ShipCommandReceiver(init);  // The CommandReceiver will also register itself as commandReceiver at its State.
        c.onActivate.setTransition(hidden);
    }

    private void createHiddenCommandReceiver(){
        ShipCommandReceiver c = new ShipCommandReceiver(hidden);
        c.getReadyToLaunch.setTransition(readyToLaunch);
        c.onDeactivate.setTransition(init);
    }

    private void createReadyToLaunchCommandReceiver(){
        ShipCommandReceiver c = new ShipCommandReceiver(readyToLaunch);
        c.launch.setTransition(outThere);
        c.onDeactivate.setTransition(init);
    }

    private void createOutThereCommandReceiver(){
        ShipCommandReceiver c = new ShipCommandReceiver(outThere);
        c.onDeactivate.setTransition(init);
    }
}

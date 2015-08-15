package se.oakbright;

import java.util.HashSet;
import java.util.Set;

import se.oakbright.battleobjects.BattleObject;
import se.oakbright.battleobjects.ShipCommands;
import se.oakbright.battleobjects.statemachine.State;
import se.oakbright.battleobjects.statemachine.StateMachine;
import se.oakbright.modules.Module;

/**
 * Created by hampuse on 2015-08-14.
 */
/*public class ShipState extends State implements ShipCommands {
    TransitionCommand getReadyToLaunch = new TransitionCommand(this);
    TransitionCommand launch;
    public boolean isLaunched;

    @Override
    public void launch() {
        launch.execute();
    }

    @Override
    public void getReadyToLaunch() {
        getReadyToLaunch.execute();
    }

    @Override
    public boolean isLaunched() {
        return isLaunched;
    }
}*/

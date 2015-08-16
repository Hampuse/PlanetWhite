package se.oakbright.battleobjects.ship;

import se.oakbright.battleobjects.statemachine.CommandReceiver;
import se.oakbright.battleobjects.statemachine.TransitionCommand;
import se.oakbright.battleobjects.statemachine.State;

/**
 * Created by hampuse on 2015-08-15.
 */
public class ShipCommandReceiver extends CommandReceiver implements ShipCommands {
    final TransitionCommand getReadyToLaunch;
    final TransitionCommand launch;
    public boolean isLaunched;

    public ShipCommandReceiver(State hostingState){
        super(hostingState);
        getReadyToLaunch = new TransitionCommand(hostingState);
        launch = new TransitionCommand(hostingState);
    }

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
}

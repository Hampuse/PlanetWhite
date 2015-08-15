package se.oakbright;

import se.oakbright.battleobjects.statemachine.State;

/**
 * Created by hampuse on 2015-08-15.
 */
public class CommandReceiver implements BattleObjectCommands{
    public TransitionCommand onActivate;
    public TransitionCommand onDeactivate;

    public CommandReceiver(State hostingState){
        hostingState.commandReceiver = this;
        onActivate = new TransitionCommand(hostingState);
        onDeactivate = new TransitionCommand(hostingState);
    }

    @Override
    public void activate() {
        onActivate.execute();
    }

    @Override
    public void deactivate() {
        onDeactivate.execute();
    }
}

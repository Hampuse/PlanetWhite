package se.oakbright.battleobjects.statemachine;

import se.oakbright.battleobjects.BattleObjectCommands;
import se.oakbright.battleobjects.statemachine.State;
import se.oakbright.battleobjects.statemachine.TransitionCommand;

/**
 * Created by hampuse on 2015-08-15.
 */
public class CommandReceiver implements BattleObjectCommands {
    public TransitionCommand onActivate;

    public CommandReceiver(State hostingState){
        hostingState.commandReceiver = this;
        onActivate = new TransitionCommand(hostingState);
    }

    @Override
    public void activate() {
        onActivate.execute();
    }
}

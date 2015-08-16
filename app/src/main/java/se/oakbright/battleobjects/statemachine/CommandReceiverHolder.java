package se.oakbright.battleobjects.statemachine;

/**
 * Created by hampuse on 2015-08-15.
 */
public interface CommandReceiverHolder<C> {
    C getCommandReceiver();
    void deactivate();
}

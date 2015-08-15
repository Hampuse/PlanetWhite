package se.oakbright;

/**
 * Created by hampuse on 2015-08-15.
 */
public interface CommandReceiverHolder<C> {
    public C getCommandReceiver();
}

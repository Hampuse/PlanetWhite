package se.oakbright.battleobjects;

/**
 * Created by hampuse on 2015-06-28.
 */
public interface Launchable {

    public void commandLaunch();

    public void commandGetReadyToLaunch();

    public boolean isLaunched();
}

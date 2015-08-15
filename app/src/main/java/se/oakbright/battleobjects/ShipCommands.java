package se.oakbright.battleobjects;

import se.oakbright.BattleObjectCommands;

/**
 * Created by hampuse on 2015-06-28.
 */
public interface ShipCommands extends BattleObjectCommands {

    public void launch();

    public void getReadyToLaunch();

    public boolean isLaunched();
}

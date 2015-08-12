package se.oakbright.battleobjects.statemachine;

/**
 * Created by hampuse on 2015-08-06.
 */
public interface ShipInterface extends BattleObjectInterface{
    public void commandLaunch();
    public void commandGetReadyToLaunch();
}

package se.oakbright.battlecontroller;

import se.oakbright.battleobjects.pickupobject.PickUpObject;
import se.oakbright.planetwhite.BattleTeam;

public interface PickUpController {
	public void reportThatPickUpObjectIsSpent(PickUpObject pickUpObject);
	public BattleTeam getTeam();
}

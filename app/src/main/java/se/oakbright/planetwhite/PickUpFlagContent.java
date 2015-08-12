package se.oakbright.planetwhite;

import se.oakbright.battleobjects.BattleObject;

public class PickUpFlagContent implements PickUpContent {
	public PickUpFlagContent(){
	}

	@Override
	public void execute(BattleObject pickedUpBy) {
		pickedUpBy.getTeam().addPoints(1);
	}
	
	
}

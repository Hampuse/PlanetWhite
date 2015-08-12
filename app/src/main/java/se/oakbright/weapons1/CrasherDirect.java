package se.oakbright.weapons1;

import se.oakbright.battleobjects.BattleObject;
import se.oakbright.modules.activatables.updatables.Weapon;
import se.oakbright.calculation.DirectionCalculation;
import se.oakbright.modules.helpers.Direction;

public class CrasherDirect extends Weapon {
	Direction shipDirection;
	public CrasherDirect(BattleObject host) {
		super(180, 0);
	}

	@Override
	protected void fireAt(int xAim, int yAim) {
		shipDirection.setDirectionTowards(xAim,yAim );	// "fire" the ship as a weapon by setting its direction towards the AIM
	}

}

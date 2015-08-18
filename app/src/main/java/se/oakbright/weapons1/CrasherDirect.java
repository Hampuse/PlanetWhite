package se.oakbright.weapons1;

import se.oakbright.battleobjects.BattleObject;
import se.oakbright.modules.activatables.updatables.Weapon;
import se.oakbright.calculation.DirectionCalculation;
import se.oakbright.modules.helpers.Direction;
import se.oakbright.resource.Resource;

public class CrasherDirect extends Weapon {
	Direction shipDirection;
	public CrasherDirect(Resource r) {
		super(r);
		//TODOsuper(180, 0);
	}

	@Override
	protected void fireAt(int xAim, int yAim) {
		shipDirection.setDirectionTowards(xAim,yAim );	// "fire" the ship as a weapon by setting its direction towards the AIM
	}

}

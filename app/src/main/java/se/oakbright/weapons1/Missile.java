package se.oakbright.weapons1;

import se.oakbright.battleobjects.BattleObject;
import se.oakbright.battleobjects.Projectile;
import se.oakbright.icons.IconId;
import se.oakbright.icons.IconIdFactory;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.BattleTeam;
import se.oakbright.planetwhite.R;

public class Missile  extends Projectile{
	private final static IconId iconIdDefault = IconIdFactory.valueOf(R.drawable.missile,25000,100000);
	public Missile(BattleModel battleModel, BattleTeam team,
			BattleObject recentlyEjectedFrom) {
		super(battleModel, team, recentlyEjectedFrom, iconIdDefault);
		// TODO Auto-generated constructor stub
	}

	//TODO
	// @Override
	//protected int onCollisionGiveDamage(){
	//	return 200; // hp (health points)
	//}

}

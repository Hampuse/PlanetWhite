package se.oakbright.weapons1;

import se.oakbright.battleobjects.BattleObject;
import se.oakbright.modules.activatables.updatables.Weapon;
import se.oakbright.calculation.DirectionCalculation;

public class BulletLauncher extends Weapon{
	public final static int aimScope = 45;
	public final static int reloadTime = 200;
	private int spread;

	public BulletLauncher(BattleObject host) {
		super(aimScope, reloadTime);
		this.spread = 6;
	}

	@Override
	protected void fireAt(int xAim, int yAim) {
		/* Bullet bullet = new Bullet(host.getBattleModel(), host.getTeam(), host);
		bullet.setPosition(host.getX(), host.getY());
		int direction = (int) DirectionCalculation.getDirectionDegFromTo(host.getX(), host.getY(), xAim, yAim);
		direction = direction + (int)(this.spread*(Math.random()-1));
		bullet.setDirection(direction);*/
	}

}

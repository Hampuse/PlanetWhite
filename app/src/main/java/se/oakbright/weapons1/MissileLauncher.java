package se.oakbright.weapons1;

import android.util.Log;

import se.oakbright.battleobjects.BattleObject;
import se.oakbright.modules.activatables.updatables.Weapon;

public class MissileLauncher extends Weapon {

	public MissileLauncher() {
		super(45,1500);
	}
	
	@Override
	protected void fireAt(int xAim, int yAim){
		Log.d("shoot", "in MISSILE LAUNCHer.fireAt("+xAim + ", " + yAim);
		/*Projectile newlyFiredProjectile = new Missile(host.getBattleModel(), host.getTeam(), host);
		newlyFiredProjectile.setPosition(host.getX(), host.getY());
		newlyFiredProjectile.setDirectionTowards(xAim, yAim);
		//TODO activate it*/
	}

	public static class Builder extends Weapon.Builder{
		@Override
		protected Weapon getType() {
			return new MissileLauncher();
		}
	}

}

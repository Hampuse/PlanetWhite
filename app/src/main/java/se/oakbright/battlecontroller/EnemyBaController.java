package se.oakbright.battlecontroller;

import se.oakbright.battleobjects.BattleObject;
import se.oakbright.battleobjects.ShipCommands;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.BattleTeam;
import android.util.Log;

public class EnemyBaController extends BaController{
	private static final String TAG = EnemyBaController.class.getSimpleName();
	private static int shipReleaseInterval = 3000;
	private long timeAtLastReleaseShip;
	private BattleObject<ShipCommands> newlyReleasedShip;
	
	
	public EnemyBaController(BattleModel battleModel, BattleTeam team){
		super(battleModel, team);
		timeAtLastReleaseShip = System.currentTimeMillis();
	}

	
	
	@Override
	public void update() {
		if (System.currentTimeMillis() - timeAtLastReleaseShip > shipReleaseInterval){
			this.releaseShip();
		}
	}
	private void releaseShip() {
		timeAtLastReleaseShip = System.currentTimeMillis();
		int x;
		int y;
		int shipSizeMargin = 50; //TODO ska vara storleken på skeppet, 50 var bara ett godtyckligt tal.
		if(Math.random() > 0.5){ // 50% chance that y will be either in the top or bottom of the surface.
			y = 0 - shipSizeMargin; 
		}
		else{
			y = battleModel.trackWidth + shipSizeMargin;
		}
		x = (int) ((Math.random()) * battleModel.trackWidth);
		
		newlyReleasedShip.setDirectionTowards((int) (Math.random() * battleModel.trackWidth), (int) (Math.random() * battleModel.trackHeight));
	}
	
	public BattleObject<ShipCommands> pollNewlyReleasedShip() {	//return the ship that was most newly released, and forgets it when it has been handed over to the caller.
		BattleObject<ShipCommands> ship = newlyReleasedShip;
		newlyReleasedShip = null;
		return ship;
	}
	
	public void informThatShipIsDead(BattleObject ship) {
		// TODO do something??
	}
}

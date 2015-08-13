package se.oakbright.ships1;

import android.graphics.Region;
import android.util.Log;

import se.oakbright.battleobjects.Ship;
import se.oakbright.icons.IconChooser;
import se.oakbright.icons.IconId;
import se.oakbright.icons.IconIdFactory;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.BattleTeam;
import se.oakbright.planetwhite.R;

/*public class RobotShipTracker extends Ship {
	public static final IconId iconIdGreen = IconIdFactory.valueOf(R.drawable.deltangreen, 100000, 100000);	//default icons if no other icon is set by the extending class (in the constructor)
	public static final IconId iconIdRed= IconIdFactory.valueOf(R.drawable.deltanred, 100000, 100000);
	public static final IconId iconIdGrey = iconIdRed;	//TODO 
	private final static IconChooser iconChooser = new IconChooser(iconIdGreen, iconIdRed, iconIdGrey);
	private Region scope = new Region();
	
	public RobotShipTracker(BattleModel battleModel, BattleTeam team,
			IconId iconId) {
		super(); //TODO...battleModel, team, iconId);
		//TODO this.mover = new ObMoverBouncer(battleModel.trackFrame,this, 6000000);
		//TODO this.hp = 50;
	}

	private static final String TAG = RobotShipTracker.class.getSimpleName();


	public RobotShipTracker(BattleModel battleModel, BattleTeam team) {
		this(battleModel, team, RobotShipTracker.iconChooser.chooseIconId(team.teamColor));
		
	}

	//TODO
	/*@Override
	public void update() {
		Log.d("tracker","update()");
		super.update();
		if(this.isLaunched){
			if (!this.mover.hasTrackObject()){
			updateScope();
			this.setTrackObject(battleModel.findShipInside(scope, this.getTeam()));
			}
		}
	}
	*/
	/*protected void updateScope(){
		Log.d(TAG,"updateScope()");
		scope.set(this.getX() - 500, this.getY() - 500, this.getX() + 500, this.getY() + 500);	
	}
	public static Ship.Blueprint getBlueprint(){
		return new Ship.Blueprint(RobotShipTracker.class);
	}
	
}*/

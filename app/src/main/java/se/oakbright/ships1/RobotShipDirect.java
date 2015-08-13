package se.oakbright.ships1;

import android.graphics.Region;

import se.oakbright.battleobjects.Ship;
import se.oakbright.icons.IconChooser;
import se.oakbright.icons.IconId;
import se.oakbright.icons.IconIdFactory;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.BattleTeam;
import se.oakbright.planetwhite.R;
import se.oakbright.weapons1.CrasherDirect;

/*public class RobotShipDirect extends Ship {
	public static final IconId iconIdGreen = IconIdFactory.valueOf(R.drawable.bollengreen, 150000, 100000);	//default icons if no other icon is set by the extending class (in the constructor)
	public static final IconId iconIdRed= IconIdFactory.valueOf(R.drawable.bollenred, 150000, 100000);
	public static final IconId iconIdGrey = iconIdRed;	//TODO 
	private final static IconChooser iconChooser = new IconChooser(iconIdGreen, iconIdRed, iconIdGrey);
	private final static int VELOCITY = 300000;
	private final static int HEALTH_POINTS = 200;
	
	public RobotShipDirect(BattleModel battleModel, BattleTeam team) {
		super(); //TODO ...battleModel, team, iconChooser.chooseIconId(team.teamColor));
		//TODO this.weapon = new CrasherDirect(this);
		//TODO this.hp = RobotShipDirect.HEALTH_POINTS;
		//TODO this.mover = new ObMoverBouncer(battleModel.trackFrame, this, RobotShipDirect.VELOCITY);
	}	// A ship that tracks an enemy ship if it comes too close. by 

	//TODO
	/*
	@Override
	public void update() {
		super.update();
		if(this.isLaunched){
			if(!this.weapon.hasAim()){
				BattleObject aim = this.battleModel.findShipInside(new Region(this.getX() - 200, this.getY() -200, this.getX() + 200, this.getY() + 200), this.getTeam());
				if(aim != null)
					this.weapon.setAim(aim, 0, 0);
			} else
				this.weapon.tryFire();
		}	
	}*/
			
			/*
		this.speed.setVelocity(1);
	}

	@Override
	protected void trackTrackObject() {
		this.setDirection(trackObject.getX(), trackObject.getY()); */
//}

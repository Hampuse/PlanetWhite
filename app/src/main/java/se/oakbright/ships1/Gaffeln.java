package se.oakbright.ships1;

import se.oakbright.battleobjects.pickupobject.PickUpObject;
import se.oakbright.battleobjects.Ship;
import se.oakbright.icons.IconChooser;
import se.oakbright.icons.IconId;
import se.oakbright.icons.IconIdFactory;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.BattleTeam;
import se.oakbright.planetwhite.R;
import se.oakbright.weapons1.BulletLauncher;

/*
 * hunter ship. bullets, fast, weak.
 */
/*public class Gaffeln extends Ship{
	public static final IconId iconIdGreen = IconIdFactory.valueOf(R.drawable.gaffelngreen, 100000, 250000);	//default icons if no other icon is set by the extending class (in the constructor)
	public static final IconId iconIdRed= IconIdFactory.valueOf(R.drawable.gaffelnred, 100000, 250000);
	public static final IconId iconIdGrey = iconIdRed;	//TODO 
	private final static IconChooser iconChooser = new IconChooser(iconIdGreen, iconIdRed, iconIdGrey);
	private final static int VELOCITY = 500000;
	private static final int START_HP = 150;
	
	public Gaffeln(BattleModel battleModel, BattleTeam team) {
		super(); //TODO ::: battleModel, team, Gaffeln.iconChooser.chooseIconId(team.teamColor));
		//TODO this.mover = new ObMoverBouncer(battleModel.trackFrame, this, Gaffeln.VELOCITY);
		//TODO this.weapon = new BulletLauncher(this);
		//super.setupValues(START_HP);
	}
	public static Ship.Blueprint getBlueprint(){
		return new Ship.Blueprint(Gaffeln.class);
	}
	
	/*@Override
	public void update() {
		if(this.isLaunched){
			this.mover.update(battleSurface);
			if(!this.mover.hasPath()){		//If the ship doesn't have a path to follow, try "fire" the weapon, which is a CrasherShip weapon, that makes the ship charge directly towards the target
				this.tryFireWeapon();	
			}
		} else{
			super.update();
		}
		
	}*/

	//TODO
	/*@Override
	protected boolean tryPickUpObject(PickUpObject pickedUpObject){
		return false;			// cannot pick up object;
	}
	*/
	
//}

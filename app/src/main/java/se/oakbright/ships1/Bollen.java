package se.oakbright.ships1;

import se.oakbright.modules.activatables.updatables.MoverBouncer;
import se.oakbright.modules.activatables.updatables.PathModule;
import se.oakbright.icons.IconChooser;
import se.oakbright.icons.IconId;
import se.oakbright.icons.IconIdFactory;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.BattleTeam;
import se.oakbright.planetwhite.R;
import se.oakbright.weapons1.CrasherDirect;
/*
 * New version of crasherShip. no weapon, except self detonation. slow, weak, cheap
 */
/*public class Bollen extends Ship{
	public static final IconId iconIdGreen = IconIdFactory.valueOf(R.drawable.bollengreen, 200000, 250000);	//default icons if no other icon is set by the extending class (in the constructor)
	public static final IconId iconIdRed= IconIdFactory.valueOf(R.drawable.bollenred, 200000, 250000);
	public static final IconId iconIdGrey = iconIdRed;	//TODO 
	private final static IconChooser iconChooser = new IconChooser(iconIdGreen, iconIdRed, iconIdGrey);
	private final static int VELOCITY = 200000;
	private final static int START_HP = 500;
	PathModule path;

	
	public Bollen(BattleModel battleModel, BattleTeam team) {
		super(); //TODO ... battleModel, team, Bollen.iconChooser.chooseIconId(team.teamColor));
		//TODO this.mover = new MoverBouncer(this.positioner, this, battleModel.trackFrame, Bollen.VELOCITY);
		//TODO this.weapon = new CrasherDirect(this);
		//super.setupValues(START_HP);
	}
	public static Ship.Blueprint getBlueprint(){
		return new Ship.Blueprint(Bollen.class);
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
// 	@Override
//	protected void tryFireWeapon(){
////		if(this.weapon != null && !this.path.hasPath())
//			this.weapon.tryFire();
//	}

	//TODO:
	/*
	@Override
	protected boolean tryPickUpObject(PickUpObject pickedUpObject){
		return false;			//CrasherShip cannot pick up object;
	}
	
	@Override
	protected int onCollisionGiveDamage() {
		return 700;
	}
*/

//}

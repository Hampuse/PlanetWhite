package se.oakbright.battleobjects.mothership;

import se.oakbright.icons.IconId;
import se.oakbright.icons.IconIdFactory;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.BattleTeam;
//TODO fix with components
/*public class LandingPad extends BattleObject{
private static final String TAG = LandingPad.class.getSimpleName();
private final MotherShip host;
public static final IconId ICON_LANDINGPAD = IconIdFactory.valueOfInvisibleIcon(150000,150000);
private enum State {ENTER_INACTIVE, ENTER_ACTIVE}
private State state = State.ENTER_ACTIVE;
	
	public LandingPad(BattleModel battleModel, BattleTeam team, MotherShip host){
		super(); //TODO ...battleModel, team, LandingPad.ICON_LANDINGPAD);
		this.host = host;
		//TODO this.updateOrientationMatrix(host.getDirection());
	}
	/* FÖRENKLAT JUST NU

	@Override
	public void update() {
		switch(state){
		case ENTER_ACTIVE:
			battleModel.addCollidable(this);
			//battleModel.removeUpdatable(this);	//after one update, the LandingPad doesn't need to get updated any more. only collidable;
			this.setFlagActive(true);
			break;
			
		case ENTER_INACTIVE:
			battleModel.removeCollidable(this);
			//battleModel.removeUpdatable(this);
			this.setFlagActive(false);
		}
	}
	
	@Override
	void collideWith(Collidable battleObject) {
		if(battleObject.invitationToLand(this)){
			this.receiveShip( (Ship) battleObject);	//TODO gör inget med return valu
		}
	}
	*/
/*
	public boolean receivePickUpObject(PickUpObject pickedUpObject) {
		if(host.receivePickUpObject(pickedUpObject)){
			return true;
		}
		else{
			return false;
		}
	}
	
	private boolean receiveShip(Ship ship){
		if(host.receiveShip(ship)){
			return true;
		}
		else{
			return false;
		}
	}

}*/

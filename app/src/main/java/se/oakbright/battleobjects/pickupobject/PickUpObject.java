package se.oakbright.battleobjects.pickupobject;

import se.oakbright.battlecontroller.PickUpController;
import se.oakbright.battleobjects.BattleObject;
import se.oakbright.modules.helpers.Shape;
import se.oakbright.icons.IconId;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.PickUpContent;
import se.oakbright.planetwhite.PickUpFlagContent;

public abstract class PickUpObject extends BattleObject {
	private static final int GIVE_DAMAGE = 0;
	//private final static int ICON_ID_NORMAL = R.drawable.star;
	//private final InIcon iconNormal;
	//private final InIcon iconPickedUp;
	private final IconId iconIdNormal;
	private final IconId iconIdPickedUp; 
	private BattleObject isPickedUpBy = null;
	private BattleObject recentlyReleasedFrom = null;
	private final PickUpContent pickUpContent = new PickUpFlagContent();
	private final PickUpController controller;
	private enum Status { HIDDEN, ENTER_OUT_THERE, OUT_THERE, ENTER_PICKED_UP, PICKED_UP, ENTER_SPENT, SPENT;}
	private Status status;

	private Shape shape;
	
	public PickUpObject(BattleModel battleModel, IconId iconIdNormal, IconId iconIdPickedUp, PickUpController controller) {
		super(); //TODO ...battleModel, controller.getTeam(), iconIdNormal);
	// tuu	this.updateOrientationMatrix();
		this.status = Status.OUT_THERE;
		this.iconIdNormal = iconIdNormal;
		this.iconIdPickedUp = iconIdPickedUp; 
		this.controller = controller;
	}
	/* FÖRENKLAT SÅ LÄNGE
	@Override
	public void update(){
		switch(this.status){
		case HIDDEN:
			break;
		case ENTER_OUT_THERE:
			battleModel.addRenderableLayer2(this);
			battleModel.addCollidable(this);
			//battleModel.addUpdatable(this); //TODO fix?
			this.status = Status.OUT_THERE;
			this.setFlagActive(true);
			break;
		case OUT_THERE:
			break;
		case ENTER_PICKED_UP:
			this.status = Status.PICKED_UP;
			break;
		case PICKED_UP:
			this.setPositioner(this.isPickedUpBy.getX(),this.isPickedUpBy.getY());
			break;
		case ENTER_SPENT:
			this.controller.reportThatPickUpObjectIsSpent(this);
			battleModel.removeRenderableLayer2(this);
			battleModel.removeCollidable(this);
			//battleModel.removeUpdatable(this); //TODO
			this.status = Status.SPENT;
			this.setFlagActive(false);
			break;
		case SPENT:
			break;
		}
		if(this.isPickedUpBy != null){
			// tuu this.updateOrientationMatrix();
		}
	}
	
	@Override
	public void activate() {
		//this.battleModel.addUpdatable(this); //TODO
		this.status = Status.ENTER_OUT_THERE;
	}
	
	@Override
	public void deactivate() {
		this.status = Status.ENTER_SPENT;
	}

	
	@Override
	protected int onCollisionGiveDamage(){
		return GIVE_DAMAGE; // hp (health points)
	}
	
	
	
	@Override
	boolean isAbleToCollideWith(BattleObject battleObject){
		if(this.isAbleToCollide && battleObject != this.recentlyReleasedFrom)
			return true;
		else
			return false;
	}
	
	@Override
	void collideWith(BattleObject battleObject) {	
			if( battleObject.tryPickUpObject(this) ){
				this.setIsPickedUpBy(battleObject);
			}
	}

	*/

	void beReleased(){
		/* FÖRENKTLAT
		shape.setIcon(this.iconIdNormal);
		this.setPositioner(this.isPickedUpBy.getX(), this.isPickedUpBy.getY());
		//tuu super.updateOrientationMatrix(this.isPickedUpBy.getDirection()); //Must update, since update() wont update it after this.isPickedUpBy=null 
		this.recentlyReleasedFrom = this.isPickedUpBy;
		this.isPickedUpBy = null;
		
		//this.updateOrientationMatrix();
		//this.updateBoundingRegion();
		super.setIsAbleToCollide(true);
		this.status = Status.OUT_THERE;
		*/
	}

	private final void setIsPickedUpBy(BattleObject battleObject){
		/*FÖRENKLAT
		this.isPickedUpBy = battleObject;	//The flag is captured by a ship that collides with it.
		super.setIsAbleToCollide(false);
		this.setIcon(this.iconIdPickedUp);
		this.status = Status.ENTER_PICKED_UP;
		*/
	}
	
	public PickUpContent openAndGetPickUpContent() {
		/*FÖRENKLAT
		this.status = Status.ENTER_SPENT;
		return this.pickUpContent;
		*/
		return null;
	}
}

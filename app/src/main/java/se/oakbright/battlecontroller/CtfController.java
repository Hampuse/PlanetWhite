package se.oakbright.battlecontroller;

import se.oakbright.Frames.Frame;
import se.oakbright.battleobjects.pickupobject.PickUpFlag;
import se.oakbright.battleobjects.pickupobject.PickUpObject;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.BattleTeam;
import android.util.Log;

public class CtfController extends BaController implements PickUpController{
Frame movingFrame;

	public CtfController(BattleModel battleModel, BattleTeam team) {
		super(battleModel, team);
		this.movingFrame = battleModel.trackFrame;
		this.releaseNewStar();
	}
	
	/*@Override
	public void onObjectIsGone(BattleObject battleObject){
		if(battleObject instanceof PickUpObject){
			//releaseNewStar();
		}
	}*/
	
	void releaseNewStar(){
		int x = (int) (this.movingFrame.left + Math.random() * (this.movingFrame.right - this.movingFrame.left));	//TODO göra om med Frame, spawn general
		int y = (int) (this.movingFrame.top + Math.random() * (this.movingFrame.bottom - this.movingFrame.top));
		this.newlyReleasedObject = new PickUpFlag(this.battleModel, this); //TODO kanske ej bör skapa en ny varje release?
		this.newlyReleasedObject.setPosition(x,y);
		this.newlyReleasedObject.activate();
	}

	@Override
	public void reportThatPickUpObjectIsSpent(PickUpObject pickUpObject) {//TODO DETTA GÅR EJ FRAM
		//TODO omladdning mm?
		releaseNewStar();
	}
	
}

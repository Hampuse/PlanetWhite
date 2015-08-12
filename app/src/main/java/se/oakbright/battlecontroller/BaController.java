package se.oakbright.battlecontroller;

import java.util.ArrayList;

import android.graphics.Rect;

import se.oakbright.battleobjects.BattleObject;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.BattleTeam;


public class BaController {
	private static final int UPDATE_INTERVAL = 500; // ms
	final Rect spawnRegionBattleField;// = new Rect(0,0,battle,200);
	private long timeAtLastUpdate;
	protected BattleTeam team;
	protected BattleModel battleModel;
	protected BattleObject newlyReleasedObject;
	protected ArrayList<BattleObject> startingObjects = new ArrayList<BattleObject>();
	
	public BaController(BattleModel battleModel,BattleTeam team){
		this.battleModel = battleModel;
		this.team = team;
		this.spawnRegionBattleField = new Rect(50,50,battleModel.trackFrame.right-50, battleModel.trackFrame.bottom-50);
	}
	
	public void tryUpdate() {
		 if (System.currentTimeMillis() - timeAtLastUpdate > UPDATE_INTERVAL){
			 this.update();
		 }
	}
	
	public void update() {
		//Abstract override!
	}
	
	public void onObjectIsGone(BattleObject object) {
		// Abstract, Override!
		
	}
	
	/*protected boolean tryPlaceObject(BattleObject bobject, int xPos, int yPos){
		bobject.setPositioner(xPos, yPos);
		return true;
	}*/
	
	public BattleObject pollNewlyReleasedObject() {	//return the ship that was most newly released, and forgets it when it has been handed over to the caller. 
		BattleObject bobject  = this.newlyReleasedObject;	
		this.newlyReleasedObject = null;
		return bobject;
		
	} 
	public ArrayList<BattleObject> PollStartingObjects(){
		ArrayList<BattleObject> bobjects = new ArrayList<BattleObject>(this.startingObjects);	
		this.startingObjects.clear();
		return bobjects;
	 }

	public BattleTeam getTeam() {
		return this.team;
	}
	
	
}

package se.oakbright.setup;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import se.oakbright.BattleObjectCommands;
import se.oakbright.Blueprints;
import se.oakbright.battlecontroller.TeamAiController;
import se.oakbright.battleobjects.BattleObject;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.BattleSurface;
import se.oakbright.planetwhite.BattleTeam;

public class TeamSetup implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final String TAG = BattleTeam.class.getSimpleName();

	private final Team team;

	//TODO private MotherShip.Blueprint motherShipBP = null;
	private Blueprints<BattleObject<BattleObjectCommands>> motherShipBP = null;

	private int motherShipX;
	private int motherShipY;
	private int motherShipDirection;

	private TeamAiController.Blueprint teamAiControllerBlueprint;
	
	public TeamSetup(Team team){
		this.team = team;
	}

	/*public void setMotherShip(MotherShip.Blueprint motherShip){
		this.motherShipBP = motherShip;
	}*/
	
	public void setTeamAiController(TeamAiController.Blueprint blueprint){
		this.teamAiControllerBlueprint = blueprint;
	}

	public Team getTeam() {
			return this.team;
	}
	
	public boolean hasMotherShip() {
		if(this.motherShipBP != null)
			return true;
		else
			return false;
	}
	
	public void addMotherShipPosition(int x, int y, int direction){
		this.motherShipX = x;
		this.motherShipY = y;
		this.motherShipDirection = direction;
	}
		
	public void setup(BattleModel battleModel, ControllerRes commonRes){//, int x, int y, Integer integer) {
		BattleTeam battleTeam = BattleTeam.newTeam(this.team.name, this.team.teamColor, this.team.isAiControlled);
		battleModel.addTeam(battleTeam);
		if(this.motherShipBP != null){
			try{
				BattleObject<BattleObjectCommands> motherShip = this.motherShipBP.getBuilt(); //TODO (battleModel, this.motherShipX, this.motherShipY, battleModel.trackFrame, battleTeam, this.motherShipDirection);
				//TODO commonRes.invalidSpawnAreas.add(motherShip.getInvalidSpawnFrameAround());
				motherShip.command().activate();
			}catch(NullPointerException e){
				Log.e(TAG,"Nullpointer when creating new motherShip in TeamSetup. MotherShip not created. Possibly no positioner is set by addMotherShipPosition(... ) ");
				throw (e); 
			}
		}
		if(this.teamAiControllerBlueprint != null){
			battleModel.addController(teamAiControllerBlueprint.create(battleModel, battleTeam));
		}
		
	}

}
	

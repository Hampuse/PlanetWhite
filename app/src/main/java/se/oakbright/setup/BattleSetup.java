package se.oakbright.setup;

import java.io.Serializable;
import java.util.ArrayList;
import android.graphics.Point;
import se.oakbright.Frames.Frame;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.TurnHandler;

public class BattleSetup implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	//private static final long serialVersionUID = 1L;
	public Simpel simpelTest = new Simpel();
	private static final String TAG = BattleSetup.class.getSimpleName();
	public String test = "hmm";
	public ArrayList<TeamSetup> teamSetups = new ArrayList<TeamSetup>();	//maybe as hashset?
	//TeamSetup t = new TeamSetup();
	public MissionSetup missionSetup;
	public TrackSetup trackSetup;
	public SceneEffectSetup sceneEffects; 
	public TurnHandler.Blueprint turnHandler;
	//transient private final BattleTeam neutralTeam;
	public enum ScreenType{ONESCREEN, SPLITSCREEN2P}
	public ScreenType screenType = ScreenType.ONESCREEN;
	private int surfaceHeight;
	private int surfaceWidth;

	
	public BattleSetup(){	
		//-- False setup instead of in user menu --// //TODO 
		
		//motherShips.add(new MotherShip(this, 0, this.battleSurface.getHeight(), this.battleSurface.getBattleFieldFrame(), teamA, 45));
		//--Real constructor part --//
		
		
	}
	
	public void addTeamSetup(TeamSetup teamSetup) {
		teamSetups.add(teamSetup);
	}
	
	public void setup(BattleModel battleModel){
		ArrayList<Frame> freeSpawnAreas = new ArrayList<Frame>();
		ArrayList<Frame> invalidSpawnAreas = new ArrayList<Frame>();
		freeSpawnAreas.add(battleModel.trackFrame);
		
		ArrayList<Point> motherShipPositions = new ArrayList<Point>();
		ArrayList<Integer> motherShipDirections = new ArrayList<Integer>();
		ControllerRes commonRes = new ControllerRes(freeSpawnAreas,invalidSpawnAreas,motherShipPositions,motherShipDirections);
		
		//--trackSetup --//
		if(this.trackSetup != null){
			this.trackSetup.setup(battleModel,commonRes);
		}
		
		//--Mission setup --//
		if(this.missionSetup != null){
			this.missionSetup.setup(battleModel, this, commonRes);
		}
		
		//--Team setups --//s
		for(TeamSetup teamSetup: teamSetups){
			if(teamSetup.hasMotherShip() && !commonRes.motherShipPositions.isEmpty() && !commonRes.motherShipDirections.isEmpty()){
				teamSetup.addMotherShipPosition(commonRes.motherShipPositions.get(0).x,commonRes.motherShipPositions.get(0).y, commonRes.motherShipDirections.get(0)); 
				commonRes.motherShipPositions.remove(0);
				commonRes.motherShipDirections.remove(0);
			}
			teamSetup.setup(battleModel, commonRes);
		}
		
		//-- Scene Effects setup --// 
		if(this.sceneEffects != null){
			this.sceneEffects.setup(battleModel, this, commonRes);
		}
		///if(this.turnHandler != null)
			//Log.w(TAG,"turnhandler bortkommenterad!");
		//battleSurface.setTurnHandler(this.turnHandler.create(battleSurface));	//TODO CORRECT? ljhaskdfhue?
	}
	/*public class Initialized{
		private final BattleSurface battleSurface;
		private final BattleTeam teamScene;
		//ArrayList<MotherShip> motherShips = new ArrayList<MotherShip>;
		ArrayList<Point> motherShipPositions = new ArrayList<Point>();
		ArrayList<Integer> motherShipDirections = new ArrayList<Integer>();
		
		public Initialized(BattleSurface battleSurface){
			this.battleSurface = battleSurface;
			Log.e("fan","in this.teamScene = " + BattleTeam.TeamColor.BROWN);
			this.teamScene = BattleTeam.newTeam("SCENE", BattleTeam.TeamColor.BROWN, true); // .getNewBattleTeam(); //, null, false);		TeamSetup.getTeamScene();
			Log.e("fan","efter teamScene = " + this.teamScene + "hjd:"+ battleSurface.getHeight());
			motherShipPositions.add(new Point(0, battleSurface.getHeight()));
			motherShipDirections.add(45);
			motherShipPositions.add(new Point(battleSurface.getWidth(),0));
			motherShipDirections.add(225);
			//this.motherShips = teamSetup
			//hej = 5;
		}
	
		/*public ArrayList<BaController> getBaControllers() {
			// TODO Auto-generated method stub
			//while(trackSetup.hasControllers())
			SceneBaController sceneController =  new SceneBaController(this.battleSurface, this.teamScene);
			//EnemyBaController enemyController = null;
			StarBaController starController = new StarBaController(this.battleSurface, this.teamScene);
			ArrayList<BaController> controllers = new ArrayList<BaController>();
			controllers.add(sceneController);
			//controllers.add(enemyController);
			controllers.add(starController);
			return controllers;
		}*/
	/*
		public ArrayList<BattleTeam> getTeams() {
			// TODO Auto-generated method stub
			//this.teamScene = BattleTeam.newTeam("SCENE", BattleTeam.TeamColor.BROWN, true);
			//BattleTeam teamA = BattleTeam.newTeam("GBG", BattleTeam.TeamColor.GREEN, false);
			//BattleTeam teamB = BattleTeam.newTeam("STHLM", BattleTeam.TeamColor.RED, false);
			ArrayList<BattleTeam> teams = new ArrayList<BattleTeam>();
			for(TeamSetup teamSetup: teamSetups){
				teams.add(teamSetup.getTeam());
			}
			return teams;
		}
	
		public ArrayList<MotherShip> getMotherShips() {
			// TODO Auto-generated method stub
			ArrayList<MotherShip> motherShips = new ArrayList<MotherShip>();
			for(TeamSetup teamSetup: teamSetups){
				if(teamSetup.hasMotherShip() != null){
					if(!motherShipPositions.isEmpty() && !motherShipDirections.isEmpty()){
						motherShips.add(teamSetup.getMotherShip(battleSurface, this.motherShipPositions.get(0).x, this.motherShipPositions.get(0).y, (int) motherShipDirections.get(0)));
						this.motherShipPositions.remove(0);
						this.motherShipDirections.remove(0);
					} else
						Log.e(TAG,"No more available motherShipPositions or directions, the motherSHip is not retrieved");
				}
			}
			/*for(MotherShip motherShip:motherShips){
				if(!motherShipPositions.isEmpty() && !motherShipDirections.isEmpty()){
					motherShip.initPosition(this.motherShipPositions.get(0).x, this.motherShipPositions.get(0).y, (int) motherShipDirections.get(0));
					this.motherShipPositions.remove(0);
					this.motherShipDirections.remove(0);*/
	/*	
			return motherShips;
		}
		
		public int getHej(){
			return hej;
		}
	}
	*/

	public void setSurfaceHeight(int surfaceHeight) {
		this.surfaceHeight = surfaceHeight;
	}
	
	public void setSurfaceWidth(int surfaceWidth){
		this.surfaceWidth = surfaceWidth;
	}
	

	/*public BattleTeam getNeutralTeam() {
		return this.neutralTeam;
	}*/
	

	
	/*//--Parcelable mathods --//
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(test);
		dest.writeParcelable(simpelTest, 0);
		//TeamSetup[] teamSetupsArr = new TeamSetup[teamSetups.size()];
		//this.teamSetups.toArray(teamSetupsArr);
		//dest.writeParcelableArray(teamSetupsArr, 0);
	}
	
	private BattleSetup (Parcel in){
		this.neutralTeam = BattleTeam.newTeam("SCENE", BattleTeam.TeamColor.BROWN, true); 
		this.test = in.readString();
		this.simpelTest = in.readParcelable(Simpel.class.getClassLoader());
		//TeamSetup[] teamSetupsArr = (TeamSetup[]) in.readSerializable() readSerializableArray(TeamSetup.class.getClassLoader());
		//this.teamSetups = new ArrayList<TeamSetup>(Arrays.asList(teamSetupsArr));
		//for(TeamSetup team: teamSetups){
			//in.readSerializable();
		//}
	}

	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
	    public BattleSetup createFromParcel(Parcel in) {
	        return new BattleSetup(in);
	    }

		@Override
		public BattleSetup[] newArray(int size) {
			return new BattleSetup[size];
		}

	    /*public Worker[] newArray(int size) {
	        return new Worker[size];
	    }*/
//	};


	
	

}
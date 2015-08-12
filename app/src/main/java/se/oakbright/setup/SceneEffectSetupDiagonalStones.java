package se.oakbright.setup;

import android.graphics.Point;
import se.oakbright.battlecontroller.SceneBaController;
import se.oakbright.battlecontroller.SpawnLane;
import se.oakbright.battleobjects.BattleObject;
import se.oakbright.planetwhite.BattleModel;

public class SceneEffectSetupDiagonalStones extends SceneEffectSetup {
	public SceneEffectSetupDiagonalStones(){
		
	}

	@Override
	public void setup(BattleModel battleModel, BattleSetup battleSetup, ControllerRes commonRes) {
		//int directionOfField = BattleObject.getDirectionDegBetween(battleModel.getBattleFieldFrame().left, battleModel.getBattleFieldFrame().top, battleModel.getBattleFieldFrame().right, battleModel.getBattleFieldFrame().bottom);
		
		int MO_MARGIN = 200; //temporary debug
		//Point enterPoint0;
		//Point enterPoint1;
		Point exitPoint0 = new Point(MO_MARGIN, battleModel.trackFrame.bottom);
		Point exitPoint1 = new Point(battleModel.trackFrame.right, MO_MARGIN);
		
		//SpawnLane lane = SpawnLane.fromLeft(0, -50, battleModel.getBattleFieldFrame().bottom - MO_MARGIN, exitPoint0, exitPoint1 );//enterPoint0, enterPoint1, exitPoint0, exitPoint1);
		SpawnLane lane = SpawnLane.betweenPoints(new Point(0,-50), new Point(0,battleModel.trackFrame.bottom - MO_MARGIN), exitPoint0, exitPoint1);
		SceneBaController sceneController =  new SceneBaController(battleModel, commonRes.neutralTeam,lane , 4);	//TODO ? klona free spawnAreas och det?
		battleModel.addController(sceneController);
		
		//lane = SpawnLane.fromTop(-50, 0, battleModel.getBattleFieldFrame().right - MO_MARGIN, exitPoint0, exitPoint1);
		lane = SpawnLane.betweenPoints(new Point(0,0), new Point(battleModel.trackFrame.right - MO_MARGIN, 0), exitPoint0, exitPoint1);
		sceneController = new SceneBaController(battleModel, commonRes.neutralTeam, lane, 5);
		battleModel.addController(sceneController);
		//TODO lane = StoneSpawnLane.fromTop(left, top, right, exitPoint0, exitPoint1 );
	}
}

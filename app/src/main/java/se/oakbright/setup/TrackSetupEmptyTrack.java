package se.oakbright.setup;

import se.oakbright.planetwhite.BattleModel;
import android.graphics.Point;

public class TrackSetupEmptyTrack extends TrackSetup {
	public TrackSetupEmptyTrack(){
	}
	
	@Override
	public void setup(BattleModel battleModel, ControllerRes commonRes) {
		commonRes.motherShipPositions.add(new Point(0, battleModel.trackHeight));
		commonRes.motherShipDirections.add(45);
		commonRes.motherShipPositions.add(new Point(battleModel.trackWidth,0));
		commonRes.motherShipDirections.add(225);		
	}
}

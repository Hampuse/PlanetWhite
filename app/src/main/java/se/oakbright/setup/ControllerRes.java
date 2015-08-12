package se.oakbright.setup;

import java.util.ArrayList;

import se.oakbright.Frames.Frame;
import se.oakbright.planetwhite.BattleTeam;
import android.graphics.Point;

public class ControllerRes{
	final ArrayList<Frame> freeSpawnAreas;
	final ArrayList<Frame> invalidSpawnAreas;
	public final ArrayList<Point> motherShipPositions;
	public final ArrayList<Integer> motherShipDirections;
	public final BattleTeam neutralTeam;
	
	ControllerRes(ArrayList<Frame> freeSpawnAreas, ArrayList<Frame> invalidSpawnAreas, ArrayList<Point> motherShipPositions, ArrayList<Integer> motherShipDirections){
		this.freeSpawnAreas = freeSpawnAreas;
		this.invalidSpawnAreas = invalidSpawnAreas;
		this.motherShipPositions = motherShipPositions;
		this.motherShipDirections = motherShipDirections;
		this.neutralTeam = BattleTeam.newTeam("SCENE", TeamColor.BROWN, true); 
	}
}

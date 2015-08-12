package se.oakbright.setup;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import android.graphics.Color;
import android.util.Log;



public class Team implements Serializable{	
	private static final String TAG = Team.class.getSimpleName();
	//public static enum TeamColor{GREEN, RED, BLUE, GREY, BROWN};
	//public static int turn = -1; //which team has the turn when playing turn-based mode.
	public final boolean isAiControlled;	//If an AI, or some kind of controller is running the team.
	public final String name;
	public final TeamColor teamColor;
	
	
	public Team(String name, TeamColor teamColor, boolean isAiControlled){
		this.name = name;
		this.teamColor = teamColor;
		this.isAiControlled = isAiControlled;	
	}

	@Override
	public String toString(){
		return name;
	}

	public int getIntColor() {
		int returnColor;
		switch (this.teamColor){
			case GREEN :
				returnColor = Color.GREEN;
				break;
			case RED :
				returnColor = Color.RED;
				break;
			default :
				returnColor = Color.GRAY;
		}
		return returnColor;
	}
	
}

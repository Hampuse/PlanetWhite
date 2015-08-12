package se.oakbright.planetwhite;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import android.graphics.Color;
import android.util.Log;
import se.oakbright.setup.TeamColor;


public class BattleTeam implements Serializable{		 //TODO ska jag ha denna klassen p detta tt, mste kunna cleara
	private static final String TAG = BattleTeam.class.getSimpleName();
	private static LinkedHashMap<BattleTeam, Status> teams = new LinkedHashMap<BattleTeam, Status>(5); //A list of all the teams in the game. maximum capacity 5 is arbitrary
	//public static enum TeamColor{GREEN, RED, BLUE, GREY, BROWN};
	public static int turn = -1; //which team has the turn when playing turn-based mode.
	public final boolean isAiControlled;	//If an AI, or some kind of controller is running the team.
	public final String name;
	public final TeamColor teamColor;
	private int points = 0;
	private String pointsString = String.valueOf(points);
	private static boolean hasGoneThroughAllTurns = false;
	//private final MotherShip motherShip;
	
	
	private BattleTeam(String name, TeamColor teamColor, boolean isAiControlled){
		this.name = name;
		this.teamColor = teamColor;
		this.isAiControlled = isAiControlled;	
		//this.motherShip = motherShip;	//Can be null;
	}

	public static BattleTeam newTeam(String name, TeamColor teamColor, boolean isAiControlled){
		Log.d(TAG, "newTeam(String), name: "+ name);
		boolean teamAlreadyExist = false;
		if(!teams.isEmpty()){
			Set<Entry<BattleTeam, Status>> s = teams.entrySet();
			for(Map.Entry<BattleTeam, Status> e: s){		// Check if a team already exists that have the same name as the one demanded to be created.
				if(((BattleTeam) e.getKey()).name.equals(name)){
					teamAlreadyExist = true;
					Log.w(TAG, "Trying to create a team with name that already exist: \""+ name + "\". No new team created, returning the original team with same name.");
					return e.getKey();
				}
			}
		}
		
		if(!teamAlreadyExist){
			BattleTeam battleTeam = new BattleTeam(name, teamColor, isAiControlled);	
			teams.put(battleTeam, new Status());
			Log.e(TAG,"return an new team: " + battleTeam);
			return battleTeam;
		}
		Log.e(TAG,"This should not happen, returning null");
		return null;	
	}
	
	/*@Override 
	public boolean equals(Object o){
		if (!(o instanceof BattleTeam))
				return false;
		BattleTeam p = (BattleTeam) o;
		return (this.name.equals(p.name));
	}*/
	
	private static class Status{
		private static enum BattleStatus{IN_ACTION, ELIMINATED};
		private BattleStatus battleStatus;
		
		private Status() {
			this.battleStatus = BattleStatus.IN_ACTION;
		}
	}
	
	public static BattleTeam getTeam(String name){
	    for (BattleTeam team : teams.keySet()){
	    	if (team.name == name)
	    		return team;
	    }
	    return null; //team not Found.
	}
	
	public static BattleTeam changeTurn(){	 
		Log.d(TAG,"changeTurn()");
		if(!teams.isEmpty()){
			Set<BattleTeam> s = teams.keySet();
			BattleTeam array[] = s.toArray(new BattleTeam[5]);
			//for(Map.Entry<BattleTeam, Status> e: s){
			boolean in_action;
			boolean wrapWatcher = false;
			hasGoneThroughAllTurns = false;
			do{
				turn++;
				if (array[turn] == null){
					if(wrapWatcher == true){	//second wrap, means infinite loop, send out ERROR
						Log.e(TAG, "Second wrap, infinite loop in changeTurn()");
					}
					turn = 0;
					wrapWatcher = true;	//set wrapWatcher true the first (and hopefully only) time it wraps while in the do-while loop.
					hasGoneThroughAllTurns = true;
				}
			in_action =(teams.get(array[turn]).battleStatus == Status.BattleStatus.IN_ACTION);
			} while(!in_action || array[turn].isAiControlled);
			
			Log.d("turn", "turn:"+ turn);
			return(array[turn]);
		}
		else{
			Log.e(TAG, "Request to change turn when teams map is empty");
			return null;
		}
		
	}
	public static boolean wrappedBackToFirstTeamTurn(){
		return hasGoneThroughAllTurns;
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

	void addPoints(int p) {
		this.points = this.points + p;
		this.pointsString = String.valueOf(this.points);
		//TODO some observer event thing
	}
	
	public int getPoints(){
		return this.points;
	}

	public String getPointsString() {
		return pointsString;
	}
	
	
	
}

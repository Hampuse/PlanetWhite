package se.oakbright.planetwhite;

import java.io.Serializable;

public class TurnHandler {
	protected final int TIME_BETWEEN_TURNS;
	protected final int TIME_DURING_TURNS;
	private long timeAtLastStartTurn = 0;	//the time when the turn switched most recently, at the time when the new turn break started.
	private long timeAtLastFinishTurn = 0;
	private boolean turnBreak = false;
	//private int turnCountDown;
	BattleTeam currentTeam; 
	protected BattleSurface battleSurface;
	//private enum State {UNINITIALIZED, ENTER_PLAYER_TURN, PLAYER_TURN, RUN};
	//private TurnHandler.State state = State.UNINITIALIZED;
	//private int numOfPlayers;
	
	public TurnHandler(BattleSurface battleSurface){
		this.battleSurface = battleSurface;
		TIME_BETWEEN_TURNS = 1000;	//The time that the game is run between the turns.
		TIME_DURING_TURNS = 100000;//2000; //The time that the game is frozen during ones turn.
		//turnBreak = false;
	}
	TurnHandler(BattleSurface battleSurface, int timeBetweenTurns, int timeDuringTurns){
		this.battleSurface = battleSurface;
		TIME_BETWEEN_TURNS = timeBetweenTurns;	//The time that the game is run between the turns.
		TIME_DURING_TURNS = timeDuringTurns;//2000; //The time that the game is frozen during ones turn.
	}
	
	/*public void initTurnHandler(BattleSurface battleSurface){
		this.state = State.RUN;
		this.battleSurface = battleSurface;
	}*/

	public void updateTurnHandler() {
		//Log.v(TAG, "updateTurnHandler(BattleSurface)");
		long timeSinceLastStartTurn = (System.currentTimeMillis() - timeAtLastStartTurn);
		long timeSinceLastFinishTurn = System.currentTimeMillis() - timeAtLastFinishTurn;
		
		
		if (turnBreak == true){
			if(timeSinceLastStartTurn > TIME_DURING_TURNS){	//  begin Phase: Game running in between the turns. 
				finishTurnPhase();	//If Finish turn phase, and begin run phase.
			}
		}
		
		else{ 
			if(timeSinceLastFinishTurn > TIME_BETWEEN_TURNS){	
				startTurnPhase();
			}
		}
	}

	/*public void udateTurnHandler(){
		switch(this.state){
		case INACTIVE:
			break;
		case ENTER_PLAYER_TURN:
			break;
		case PLAYER_TURN:
			break;
		case RUN:
			break;
		default:
			break;
		}
	}*/
	
	private void startTurnPhase(){	//// Start turn Phase: Game stopped running during turns, when player can control their ships.
		this.timeAtLastStartTurn = System.currentTimeMillis();
		this.turnBreak = true;
		this.battleSurface.haltBattleClock();
		this.currentTeam = BattleTeam.changeTurn();	// change the turn to the next player.
		this.battleSurface.setPlayerTeam(currentTeam);
		this.battleSurface.activateHiliBorder(currentTeam.getIntColor());
		this.battleSurface.setPositionFinishTurnButton();//this.currentTeam.getPositionForFinishTurnButton());
		this.battleSurface.setVisibilityFinishTurnButton(true);
		
	}
	
	private void finishTurnPhase(){
		if(turnBreak = true){
			this.timeAtLastFinishTurn = System.currentTimeMillis();
			this.turnBreak = false;							
			this.battleSurface.resumeBattleClock();
			this.battleSurface.deactivateHiliBorder();
			this.battleSurface.setPlayerTeam(null);	//This together with resetTouchState disables the possibility for user input on the ships. Is enabled when a new playerTeam is set.
			this.battleSurface.setVisibilityFinishTurnButton(false);
		}
	}
	void finishTurnCommand(){
		finishTurnPhase();
	}
	
	public static abstract class Blueprint implements Serializable{
		protected int TIME_BETWEEN_TURNS = 2000;	//default values, may be changed
		protected int TIME_DURING_TURNS = 100000;
		public Blueprint(){
		}
		
		public void setTimeBetweenTurns(int millis){
			this.TIME_BETWEEN_TURNS = millis;
		}
		public void setTimeDuringTurns(int millis){
			this.TIME_DURING_TURNS = millis;
		}
		public abstract TurnHandler create(BattleSurface battleSurface);
	}
}

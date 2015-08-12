package se.oakbright.planetwhite;

import java.io.Serializable;

/*
 * Like the normal TurnHandler, but instead of having a Go state between the turns, both turns are made, and then a Go state is run.
 */
public class TurnHandlerBoth extends TurnHandler{

	TurnHandlerBoth(BattleSurface battleSurface, int timeBetweenTurns,
			int timeDuringTurns) {
		super(battleSurface, timeBetweenTurns, timeDuringTurns);
	}

	private enum State {INIT, ENTER_PLAYER_TURN, TRY_CHANGE_PLAYER_TURN, PLAYER_TURN, ENTER_RUN, RUN};
	private TurnHandlerBoth.State state = State.INIT;
	private long timeAtStartTurn;
	private long timeAtStartRun;
	private boolean finishTurnCommand;
	
	@Override
	public void updateTurnHandler(){
		switch(this.state){
		case INIT:
			this.currentTeam = BattleTeam.changeTurn();
			this.state = State.ENTER_RUN;
			break;
		case ENTER_PLAYER_TURN:
			this.timeAtStartTurn = System.currentTimeMillis();
			this.battleSurface.haltBattleClock();//setSlowMotionBattleClock();
			this.battleSurface.setPlayerTeam(currentTeam);
			this.battleSurface.activateHiliBorder(currentTeam.getIntColor());
			this.battleSurface.setPositionFinishTurnButton();//this.currentTeam.getPositionForFinishTurnButton());
			this.battleSurface.setVisibilityFinishTurnButton(true);
			this.state = State.PLAYER_TURN;
			
			break;
		case PLAYER_TURN:
			if(System.currentTimeMillis() - this.timeAtStartTurn > TIME_DURING_TURNS){
				this.state = State.TRY_CHANGE_PLAYER_TURN;
			}
			break;
			
		case TRY_CHANGE_PLAYER_TURN:
			this.currentTeam = BattleTeam.changeTurn();
			if (!BattleTeam.wrappedBackToFirstTeamTurn()){
				this.state = State.ENTER_PLAYER_TURN;
			}else{
				this.state = State.ENTER_RUN;
			}
			break;
		case ENTER_RUN:
			this.timeAtStartRun = System.currentTimeMillis();
			this.battleSurface.resumeBattleClock();
			this.battleSurface.deactivateHiliBorder();
			this.battleSurface.setPlayerTeam(null);	//This together with resetTouchState disables the possibility for user input on the ships. Is enabled when a new playerTeam is set.
			this.battleSurface.setVisibilityFinishTurnButton(false);
			this.state = State.RUN;
		case RUN:
			if(System.currentTimeMillis() - this.timeAtStartRun > this.TIME_BETWEEN_TURNS){
				this.state = State.ENTER_PLAYER_TURN;
			}
			break;
		default:
			break;
		}
	}
	
	@Override
	void finishTurnCommand(){
		this.state = State.TRY_CHANGE_PLAYER_TURN;// sets the time falsely to a small value to make the state machine jump forward. 
	}
	
	public static class Blueprint extends TurnHandler.Blueprint implements Serializable{
		public Blueprint(){
			super();
		}
		
		@Override
		public TurnHandler create(BattleSurface battleSurface){
			return new TurnHandlerBoth(battleSurface, TIME_BETWEEN_TURNS, TIME_DURING_TURNS);
		}
		
	}
}

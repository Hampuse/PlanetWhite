package se.oakbright.planetwhite;

import java.io.Serializable;

import android.util.Log;
import se.oakbright.setup.Team;

public class TurnHandlerSingle extends TurnHandler{
	private static final String TAG = TurnHandlerSingle.class.getSimpleName();

	public TurnHandlerSingle(BattleSurface battleSurface, BattleTeam team){
		super(battleSurface);
		this.battleSurface.setPlayerTeam(team);
		this.battleSurface.setVisibilityFinishTurnButton(false);
	}
	
	@Override
	public void updateTurnHandler(){
		//DO nothing;
	}
	
	
	public static class Blueprint extends TurnHandler.Blueprint implements Serializable{
		private Team team;
		public Blueprint(Team team){
			this.team = team;
		}
		
		public Blueprint() {
		}

		public TurnHandler create(BattleSurface battleSurface){
			if(team == null){
				Log.e(TAG, "Team is not set, returning null");
				return null;
			} else{
				BattleTeam bTeam = BattleTeam.getTeam(this.team.name);
				if(bTeam == null){
					Log.e(TAG, "Team doesn't exist as a BattleTeam, returning null");
					return null;
				}
				return new TurnHandlerSingle(battleSurface, bTeam);
			}
		}
		
		public TurnHandler create(BattleSurface battleSurface, BattleTeam team){
			return new TurnHandlerSingle(battleSurface, team);
		}
	}
}

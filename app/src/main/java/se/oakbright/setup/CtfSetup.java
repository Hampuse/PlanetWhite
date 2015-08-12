package se.oakbright.setup;

import se.oakbright.battlecontroller.CtfController;
import se.oakbright.planetwhite.BattleModel;

public class CtfSetup extends MissionSetup{
	//private final CtfController ctfController;

	/**
	 * 
	 */
	private static final long serialVersionUID = -8652403330037696955L;

	public CtfSetup() {
		//this.ctfController = new CtfController();
	}

	@Override
	public void setup(BattleModel battleModel, BattleSetup mainSetup, ControllerRes commonRes) {
		CtfController ctfController = new CtfController(battleModel, commonRes.neutralTeam);
		battleModel.addController(ctfController);
		
	}

}

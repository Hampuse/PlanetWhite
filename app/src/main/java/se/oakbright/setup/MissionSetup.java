package se.oakbright.setup;

import java.io.Serializable;

import se.oakbright.planetwhite.BattleModel;

public abstract class MissionSetup implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8096778448576454983L;

	public MissionSetup(){
		
	}

	public abstract void setup(BattleModel battleModel, BattleSetup battleSetup, ControllerRes commonRes);
}

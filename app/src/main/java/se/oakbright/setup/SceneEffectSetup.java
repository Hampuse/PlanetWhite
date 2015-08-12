package se.oakbright.setup;

import java.io.Serializable;

import se.oakbright.planetwhite.BattleModel;

public abstract class SceneEffectSetup implements Serializable{

	public abstract void setup(BattleModel battleModel, BattleSetup battleSetup, ControllerRes commonRes);
	
}

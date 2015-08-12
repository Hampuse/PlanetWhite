package se.oakbright.setup;

import java.io.Serializable;

import android.graphics.Point;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.BattleSurface;

public abstract class TrackSetup implements Serializable{

	public TrackSetup() {
		// TODO Auto-generated constructor stub
	}

	public abstract void setup(BattleModel battleModel, ControllerRes commonRes); 

}

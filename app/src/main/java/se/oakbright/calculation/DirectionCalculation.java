package se.oakbright.calculation;

import android.graphics.Region;

import java.util.ArrayList;

import se.oakbright.battleobjects.BattleObject;
import se.oakbright.planetwhite.BattleTeam;

public class DirectionCalculation {

	public static float convertFromDirectionToRadians(int direction){
		direction = 270 + direction;	//adjust the direction to the coordinate system used bu cos and sin
		if(direction < 0)
			direction = direction+360;
		else if (direction>360)
			direction = direction-360;
	
		return (float) (direction*Math.PI/180);
	}

	public final static float getDirectionDegFromTo(int x0, int y0, int x1, int y1){
		int deltaX = x1 - x0;
		int deltaY = y1 - y0;
		return (float) ((Math.atan2(deltaY,deltaX))*180/Math.PI + 90);
	}
}

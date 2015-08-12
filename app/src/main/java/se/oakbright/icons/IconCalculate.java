package se.oakbright.icons;

import java.util.Arrays;

import android.content.res.Resources;
import android.util.Log;

public class IconCalculate {
	private static final String TAG = IconCalculate.class.getSimpleName();
	public static void calculateAndDisplayBoundingPath(Resources res, IconId iconId, int numOfPoints){
		int[] points = calculateBoundingPath(res,iconId,numOfPoints);
	}
	
	static int[] calculateBoundingPath(Resources res, IconId iconId, int numOfPoints){
		//TODO if this is the way to do?
		return new int[] {1,2,3,4,5,6};
	}
}

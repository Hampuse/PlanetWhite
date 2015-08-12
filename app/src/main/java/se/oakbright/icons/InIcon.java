package se.oakbright.icons;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Region;

public interface InIcon {

	public Bitmap getBitmap();
	
	public int getBoundingSquareSide();
	
	public int getWidthDst();
	
	public int getHeightDst();

	public float[] getBoundingPointsKeyCoords();

	public int getMaxBoundingRadii();
	
	public int getRadii(int angle);

}

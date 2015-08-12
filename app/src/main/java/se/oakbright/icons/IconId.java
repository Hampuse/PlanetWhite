package se.oakbright.icons;

import android.graphics.Path;

public interface IconId {

	public int getId();
	
	public int getWidthDst();
	
	public int getHeightDst();
	
	public int[] getBoundingPointsFull();

	public float[] getBoundingPointsKey();
	
	public InIcon getIcon(IconFactory iconFactory);//, int widthPxl, int heightPxl);
}

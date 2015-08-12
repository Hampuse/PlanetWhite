package se.oakbright.icons;

import se.oakbright.planetwhite.GameActivity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Region;

public class InvisibleIcon implements InIcon{	//TODO extenda Icon i stället och ta bort InIcon?
	private final int boundingSquareSide;
	//private final Region boundingPrecise;
	private final float[] boundingPointsCoords;
	//private final int[] boundingPointsAngle;	// the bounding points must start with a point on 0 and end with a point on 360;
	//private final int[] boundingPointsRadii;
	private final int maxBoundingRadii;
	public final int widthDst;
	public final int heightDst;
	
	InvisibleIcon(int widthDst, int heightDst){	//assume width and height in Pxls
		this.widthDst = widthDst;//*GameActivity.PXL_TO_DST;
		this.heightDst = heightDst;// *GameActivity.PXL_TO_DST;
		this.boundingSquareSide = (int) Math.hypot(widthDst/2, heightDst/2);
		//this.boundingPrecise = new Region(0,0,widthDst,heightDst);
		
		this.boundingPointsCoords = new float[] {widthDst/2,heightDst/2,		widthDst/2,heightDst/2, 	widthDst/2,heightDst/2}; 	//TODO now only sets a triangle .
		//this.boundingPointsAngle = new int[] {0, 180, 360};
		//this.boundingPointsRadii = new int[] {0, 0, 0};
		this.maxBoundingRadii = heightDst; //TODO NOT LIKE THIS...
	}
	public Bitmap getBitmap() {
		return null;
	}
	public int getBoundingSquareSide() {
		return this.boundingSquareSide;
	}
	/*public Region getBoundingPrecise() {
		return this.boundingPrecise;
	}*/
	
	/*public Region getBoundingPreciseTransformed(Matrix transformMatrix, Region clipRegion) {
		Region transformedRegion = new Region();
		android.graphics.Path path = this.getBoundingPrecise().getBoundaryPath();
		path.transform(transformMatrix);
		transformedRegion.setPath(path, clipRegion);
		return transformedRegion;
	}*/
	
	@Override
	public float[] getBoundingPointsKeyCoords() {
		return this.boundingPointsCoords; //TODO 	null
	}
	@Override
	public int getMaxBoundingRadii() {
		return maxBoundingRadii;
	}
	@Override
	public int getRadii(int angle) {
		return maxBoundingRadii;
	}

	/*@Override
	public int getWidthPxl() {
		return this.heightPxl;
	}
	
	@Override
	public int getHeightPxl() {
		return this.heightPxl;
	}
	*/
	@Override
	public int getWidthDst(){
		return this.widthDst;
	}
	
	@Override
	public int getHeightDst(){
		return this.heightDst;
	}
	
	/*@Override
	public void drawHighlightMark(Canvas canvas, int xDst, int yDst) {
		// Do nothing
		
	}
	@Override
	public void drawIcon(Canvas canvas) {
		// do nothing
		
	}
	@Override
	public void updateBitmapMatrix(int cornerXDst, int cornerYDst) {
		// do nothing
		
	}
	@Override
	public void updateBitmapMatrix(int xCorner, int yCorner, float angle,
			int xPivot, int yPivot) {
		// do nothing
		
	}*/
}

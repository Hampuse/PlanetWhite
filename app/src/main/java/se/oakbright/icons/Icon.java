package se.oakbright.icons;

import java.util.ArrayList;
import java.util.Arrays;

import se.oakbright.battleobjects.BattleObject;
import se.oakbright.planetwhite.BattleSurface;
import se.oakbright.planetwhite.GameActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.Log;

/**
 * Icon implements InIcon
 *
 * Icon(Bitmap bitmap, Path BoundingPath)
 *		*boundingPath specifies the rough silhouette of the bitmap. May be used for calculating collisions with other objects
 *		If no boundingPath is specified ( == null) the boundingPath will be specified as the bitmap rectangular border.
 * 		A boundingPath can be created 
 * The Region specified with this path can be retrieved with 
 * @author hampuse
 *
 */
class Icon implements InIcon{
	private static final String TAG = Icon.class.getSimpleName();
	private final Bitmap bitmap;
	private final int boundingSquareSide;	//The biggest possible distance from the center of the bitmap, where an intersection might occur with other objects. taking rotation in concern.
	private final int[] boundingPointsFullCoords;
	private final float[] boundingPointsKeyCoords;
	private final int[] boundingPointsAngle;	//TODO! the bounding points must start with a point on 0 and end with a point on 360;
	private final int[] boundingPointsRadii;
	private final int maxBoundingRadii;
	public final int widthDst;
	public final int heightDst;
	private Matrix bitmapMatrixPxl = new Matrix();
	
	protected Icon(Bitmap bitmap, int widthDst, int heightDst, int[] boundingPointsFull, float[] boundingPointsKey){	
		this.bitmap = bitmap; //BoundingPointsGeneratorTest.TEST1;// bitmap; 
		this.boundingSquareSide = (int) Math.hypot(widthDst/2, heightDst/2);
		BoundingPointsGeneratorDst pointsGenerator;
		pointsGenerator = new BoundingPointsGeneratorDst(this.bitmap, widthDst, heightDst);
		this.boundingPointsFullCoords = null;
		if(boundingPointsKey != null){
			this.boundingPointsKeyCoords = boundingPointsKey;//pointsGenerator.getKeyCoords//convertToDst(boundingPointsKey, bitmap, widthDst, heightDst);
		} else{
			this.boundingPointsKeyCoords = pointsGenerator.getKeyCoords(10);
		}
		this.boundingPointsAngle = pointsGenerator.getAngles();
		this.boundingPointsRadii = pointsGenerator.getRadiis();
		this.maxBoundingRadii = pointsGenerator.getMaxRadii();
		this.widthDst = widthDst; //bitmap.getWidth()*GameActivity.PXL_TO_DST;
		this.heightDst = heightDst; //bitmap.getHeight()*GameActivity.PXL_TO_DST;
	}
	
	public Bitmap getBitmap(){
		return this.bitmap;
	}
	
	public int getBoundingSquareSide(){
		return this.boundingSquareSide;
	}

	
	@Override
	public int getWidthDst(){
		return this.widthDst;
	}
	
	@Override
	public int getHeightDst(){
		return this.heightDst;
	}

	@Override
	public float[] getBoundingPointsKeyCoords() {
		return this.boundingPointsKeyCoords.clone();
	}

	@Override
	public int getMaxBoundingRadii() {
		return this.maxBoundingRadii;
	}

	@Override
	public int getRadii(int angle) {	//Returns the Radius of the bounding points at the specified angle. angle inputs in between the angles specified in the boundingPointsAngle field, return interpolated values, which forms arcs between the specified angles.
		int jump = (this.boundingPointsAngle.length - 1)/2; //set the jump that the search will use. start to search at half of the angle field. 
		int iLow = jump;
		
		while(jump >= 2){	//As long as jump can be divided by two, search through the field of angles by dividing the field in two each jump.
			jump = jump/2;
			if(angle < this.boundingPointsAngle[iLow]){	//determine if the search should jump up or down in the field.
				iLow = iLow -jump;
			}else{
				iLow = iLow + jump;
			}
		}
		if(angle < this.boundingPointsAngle[iLow])	//In case the remaining angles are an odd number, check in case the index should be adjusted one up or down. 
				iLow--;
		else if(angle > this.boundingPointsAngle[iLow + 1])
			iLow++;

		return (int)(this.boundingPointsRadii[iLow] + ( this.boundingPointsRadii[iLow+1] - this.boundingPointsRadii[iLow] ) * ((double)(angle-this.boundingPointsAngle[iLow]) / (this.boundingPointsAngle[iLow+1]-this.boundingPointsAngle[iLow])));
	}
}

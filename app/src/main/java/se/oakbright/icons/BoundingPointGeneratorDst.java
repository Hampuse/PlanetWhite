package se.oakbright.icons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import se.oakbright.battleobjects.BattleObject;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

class BoundingPointsGeneratorDst {
	private static final Bitmap ERROR_BITMAP = Bitmap.createBitmap(new int[]{-65536,-1,-1,-1,-65536, -1,-65536,-1,-65536,-1, -1,-1,-65536,-1,-1, -1,-65536,-1,-65536,-1, -65536,-1,-1,-1,-65536}, 5, 5, Bitmap.Config.ARGB_4444);
	private static final int MIN_ANGLE_DIFF_PART_OF_SEGMENT = 2; //The constant used by getKeyPoints(..). Min angular difference = segment/MIN_...	A high value will result in points closer to each other. typically the value is 2 or 3;
	private static final int MIN_FRONT_ANGLE = 10;	//for getKeyPoints, The minimum angle from 0 for the keypoint in front. if the other values are under this value, an extra point will be added at 0 degrees.(in front of the bitmap)
	private static final String TAG = BoundingPointsGeneratorDst.class.getSimpleName();
	private final int[] angles;
	private final int[] radiis;
	private final int maxRadii;
	private static final int NUMBER_OF_ANGLE_VALUES = 10;
	private final int widthDst;
	private final int heightDst;
	private final Bitmap bitmap;
	private final int bitmapToDst;	//THe conversion ratio from bitmap's pxls to Dst
	
	public BoundingPointsGeneratorDst(Bitmap bitmap, int widthDst, int heightDst){
		this.widthDst = widthDst;
		this.heightDst = heightDst;
		this.bitmap = bitmap;
		this.bitmapToDst = widthDst / bitmap.getWidth();  
		if(Math.abs(widthDst/bitmap.getWidth() - heightDst/bitmap.getHeight()) > 1) //check so that ratios are correct
			Log.e(TAG, "Dst height/width ratio is not the same as bitmap");
		
		this.angles = new int[NUMBER_OF_ANGLE_VALUES];	//Make the array that will get evenly spread out angles from 0 up to 360
		this.radiis = new int[NUMBER_OF_ANGLE_VALUES];	//Make the array of radiis corresponding to the angles.
		float jump = 360/NUMBER_OF_ANGLE_VALUES;
		int maxRadiiTemp = 0;
		for(int i=0; i < NUMBER_OF_ANGLE_VALUES-1; i++){
			this.angles[i] = (int) jump*i;
			this.radiis[i] = bitmapToDst*BoundingPointsGeneratorDst.getOuterRadii(bitmap, angles[i]); //calculate radius and convert to dst
			//Log.d("radil", "radius= " this.radiis)
			if (this.radiis[i] > maxRadiiTemp){
				maxRadiiTemp = radiis[i];
			}
		}
		this.angles[NUMBER_OF_ANGLE_VALUES-1] = 360;
		this.radiis[NUMBER_OF_ANGLE_VALUES-1] = this.radiis[0];	//the radii at 360 degrees is the same as for 0 degrees. 
		this.maxRadii = maxRadiiTemp;
	}
	
	private static int[] castToint(ArrayList<Integer> collection){
		int[] array = new int[collection.size()];
		for(int i = 0; i < collection.size(); i++){
			if(collection.get(i) != null){
			array[i] = (int) collection.get(i);
			} else {
				Log.e(TAG, "Error in Icon.calculateBitmapPxls, Integer is null, setting value to 0");
				array[i] = 0;
			}
		}
		return array;
	}
	
	private static float[] castTofloat(ArrayList<Float> collection){
		float[] array = new float[collection.size()];
		for(int i = 0; i < collection.size(); i++){
			if(collection.get(i) != null && collection.get(i) != Float.NaN){
			array[i] = (float) collection.get(i);
			} else {
				Log.e(TAG, "Error in castToFloat(...), invalid float value, setting value to 0");
				array[i] = 0;
			}
		}
		return array;
	}
	
	private static int getOuterRadii(Bitmap bitmap, int angle) {	//Returns the outer radii of the bitmap at the specified angle (in pxl from bitmap). That is, at the last non transparent pixel when searching from the middle point and out along the given angle.
		angle = 270 + angle;	//adjust the angle to the coordinate system used bu cos and sin
		if(angle < 0)
			angle = angle+360;
		else if (angle>360)
			angle = angle-360;
		
		int middleX = bitmap.getWidth()/2;
		int middleY = bitmap.getHeight()/2;
		int x = middleX;
		int y = middleY;
		int radii = 0;
		int maxRadiiTemp = 0;
		while(x>=0 && x<bitmap.getWidth() && y>=0 && y< bitmap.getHeight()){

			if(Color.alpha(bitmap.getPixel(x, y)) != 0){
				maxRadiiTemp = radii;
			}
			x = middleX + Math.round((float)(Math.cos(angle*Math.PI/180)*radii));
			y = middleY + Math.round((float)(Math.sin(angle*Math.PI/180)*radii));
			radii++;
		}
		
		return maxRadiiTemp;
	}
	public int[] getAngles() {
		return this.angles;
	}
	public int[] getRadiis() {
		return this.radiis;
	}
	public int getMaxRadii() {
		return this.maxRadii;
	}
	
	public float[] getKeyCoords(int numOfPoints) {	//returns the keyPoints for collision detection. which are the most protruding points of the bitmap's non-transparent boundary.The returned array will have length equal to 2*numOfPoints or less
		ArrayList<Integer> radiis = new ArrayList<Integer>();
		ArrayList<Integer> angles = new ArrayList<Integer>();
		ArrayList<Float> coords = new ArrayList<Float>();
		int middleX = this.widthDst/2; //bitmap.getWidth()/2;
		int middleY = this.heightDst/2; //bitmap.getHeight()/2;
		int angle = 0;
		int segment = 360/numOfPoints;
		int nextSegment = segment;
		int angleAtMaxRadiiInSegment;
		int maxRadiiInSegment;
		while(nextSegment<360+segment){				//Search around the bitmap in angular segments, 
			angleAtMaxRadiiInSegment = angle;
			maxRadiiInSegment = 0;
			while(angle<=nextSegment && angle<360){		//Find the largest radii in each segment, and the corresponding angle.  
				int radii = this.bitmapToDst*getOuterRadii(bitmap,angle);	//convert the radius to Dst.
				if(radii > maxRadiiInSegment){
					maxRadiiInSegment = radii;
					angleAtMaxRadiiInSegment = angle;
				}
				angle++;
			}

				if(!angles.isEmpty() && (angleAtMaxRadiiInSegment - angles.get(angles.size() -1) < segment/MIN_ANGLE_DIFF_PART_OF_SEGMENT)){	//If the angle is just a bit bigger than the former, only keep the highest value for radii,
					if(maxRadiiInSegment > radiis.get(radiis.size()-1)){
						radiis.set(radiis.size()-1, maxRadiiInSegment);
						angles.set(angles.size()-1, angleAtMaxRadiiInSegment);
					} //else, do nothing
				} else{	//if the angles are spread out, just add to the list.
					radiis.add(maxRadiiInSegment);
					angles.add(angleAtMaxRadiiInSegment);
				}
			nextSegment = nextSegment + segment;
		}
		
		if(angles.get(0) > MIN_FRONT_ANGLE && (angles.get(angles.size()-1)) < (360-MIN_FRONT_ANGLE) ){		//If there is no point right in front of the icon, this should be added. Tests if the first angle is too high, and if the last angle is too low: in that case adds values for 0 angle. 
			angles.add(0,0);
			radiis.add(0, BoundingPointsGeneratorDst.getOuterRadii(bitmap,0));
		}
		
		Iterator<Integer> radiiIt = radiis.iterator();
		for (Iterator<Integer> angleIt = angles.iterator(); angleIt.hasNext();) {	//create coord values from the list of angels and radiis
			int iangle = angleIt.next();
			int iradii = radiiIt.next();
			iangle = 270 + iangle;	//convert the angle to fit cos and sin
			if(iangle > 360)
				iangle = iangle - 360;
			if(iangle < 0)
				iangle = iangle + 360;
			coords.add(middleX + (float)(iradii*Math.cos(iangle*Math.PI/180)));	//add the x value;
			coords.add(middleY + (float)(iradii*Math.sin(iangle*Math.PI/180)));	//and the y value;
		}
		
		return BoundingPointsGeneratorDst.castTofloat(coords);		//return coords in float[] form.
	}
}

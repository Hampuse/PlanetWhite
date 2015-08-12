package se.oakbright.Frames;

import android.graphics.Point;
import android.graphics.Region;
import android.util.Log;

/* A frame that an object should be inside */
public class Frame{
	private static final String TAG = Frame.class.getSimpleName();
	
	public final int left;
	public final int top;
	public final int right;
	public final int bottom;
	//private final Region region;
	
	public Frame(int leftBorder, int upBorder, int rightBorder, int downBorder) {
		this.left = leftBorder;
		this.top = upBorder;
		this.right = rightBorder;
		this.bottom = downBorder;
		//this.region = new Region(leftBorder,upBorder,rightBorder,downBorder);
	}

	public Point getRandomSpawnPoint(int xMargin, int yMargin) {	//TODO some check that it's possible to give point
		int x = this.left+ xMargin + Math.round((float)Math.random()*((this.right-xMargin)-(this.left + xMargin)));
		int y = this.top + yMargin + Math.round((float)Math.random()*((this.bottom-yMargin) - (this.top+yMargin)));
		//Log.d("cstone", " random point=("+x +","+y+") ... where right = "+ this.right);
		return new Point(x,y);
	}
	
	public static boolean isSpawnPointOutside(Frame[] invalidFrames, Point spawnPoint, int xMargin, int yMargin){
		int spawnX = spawnPoint.x;
		int spawnY = spawnPoint.y;
		for(Frame invalid: invalidFrames){
			if(spawnX > (invalid.left - xMargin) && spawnX < (invalid.right + xMargin) && spawnY > (invalid.top - yMargin) && spawnY < (invalid.bottom + yMargin)){
				return false;
			}
		}
		return true;
	}
	
	/*public static Frame box(int leftBorder, int upBorder, int rightBorder, int downBorder){
		return new Frame(leftBorder, upBorder, rightBorder, downBorder);
	}*/
	
	/*public static Frame enlargedFrame(Frame frame, int addOnSides){
		return new Frame(frame.leftBorder - addOnSides, frame.upBorder - addOnSides, frame.rightBorder + addOnSides, frame.downBorder + addOnSides);
	}*/
	
	//public getRect() 
	/*public int getLeftBorder(){
		return this.leftBorder;
	}
	
	public int getUpBorder(){
		return this.upBorder;
	}
	
	public int getRightBorder() {
		return this.rightBorder;
	}
	
	public int getDownBorder(){
		return this.downBorder;
	}
	
	public Region getRegion(){
		return this.region;
	}*/
	public static Point getRandomSpawnPoint(int xMargin, int yMargin, Frame[] spawnFrames){ //invalidFrames, Frame[] wishedFrames){
		Frame spawnFrame = spawnFrames[Math.round((float)Math.random()*(spawnFrames.length-1))];
		//Log.d("cstone", " getRandomSpawnPoint, in spawnFrame=" +spawnFrame);
		//int x = (int) (wished.left + Math.round(Math.random()*(wished.right - wished.left)));
		//int y = (int) (wished.top + Math.round(Math.random()*(wished.bottom - wished.top)));
		return spawnFrame.getRandomSpawnPoint(xMargin, yMargin);
	}
	
	
}
	

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

	public Frame(int leftBorder, int upBorder, int rightBorder, int downBorder) {
		this.left = leftBorder;
		this.top = upBorder;
		this.right = rightBorder;
		this.bottom = downBorder;
	}

	public Point getRandomSpawnPoint(int xMargin, int yMargin) {	//TODO some check that it's possible to give point
		int x = this.left+ xMargin + Math.round((float)Math.random()*((this.right-xMargin)-(this.left + xMargin)));
		int y = this.top + yMargin + Math.round((float)Math.random()*((this.bottom-yMargin) - (this.top+yMargin)));
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

	public static Point getRandomSpawnPoint(int xMargin, int yMargin, Frame[] spawnFrames){ //invalidFrames, Frame[] wishedFrames){
		Frame spawnFrame = spawnFrames[Math.round((float)Math.random()*(spawnFrames.length-1))];
		return spawnFrame.getRandomSpawnPoint(xMargin, yMargin);
	}
	
	
}
	

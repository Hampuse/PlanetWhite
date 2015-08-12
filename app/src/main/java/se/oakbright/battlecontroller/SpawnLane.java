package se.oakbright.battlecontroller;

import se.oakbright.Frames.UnboundedFrame;
import se.oakbright.calculation.DirectionCalculation;

import android.graphics.Point;

public abstract class SpawnLane{
	public int spawnX = 0;
	public int spawnY = 0;
	public int spawnDirection = 0;
	
	private final Point exitPoint0;
	private final Point exitPoint1;
	private final float radiansBetweenExit0to1;
	private final float radiansBetweenExit1to0;
	protected UnboundedFrame startFrame;
	/*
	 * SpawnLane, is a lane which calculates a spawnPoint for an object, and the direction needed to enter between two enter points, and exit between two exit points.
	 * A new SpawnLane is created with:
	 *  SpawnLane spawnLane = SpawnLane.betweenPoints(Point enterPoint0, Point enterPoint1, Point exitPoint0, Point exitPoint1);
	 * To generate a spawnPoint, call:
	 * 	spawnLane.createRandomSpawnPoint(int margin);
	 * and right after, get the spawn Point and direction with:
	 * 	spawnLane.spawnX
	 * 	spawnLane.spawnY
	 * 	spawnLane.spawnDirection
	 */
	private SpawnLane( Point exitPoint0, Point exitPoint1){//Point enterPoint0, Point enterPoint1, Point exitPoint0, Point exitPoint1){
		this.exitPoint0 = exitPoint0;
		this.exitPoint1 = exitPoint1;
		int dirBetweenExit0to1 = (int) DirectionCalculation.getDirectionDegFromTo(exitPoint0.x, exitPoint0.y, exitPoint1.x, exitPoint1.y);
		this.radiansBetweenExit0to1 = DirectionCalculation.convertFromDirectionToRadians(dirBetweenExit0to1);
		int dirBetweenExit1to0 = (int) DirectionCalculation.getDirectionDegFromTo(exitPoint1.x, exitPoint1.y, exitPoint0.x, exitPoint0.y);
		this.radiansBetweenExit1to0 = DirectionCalculation.convertFromDirectionToRadians(dirBetweenExit1to0);
	}
	
	public static SpawnLane fromLeft(int left, int top, int bottom, Point exitPoint0, Point exitPoint1){
		return new FromLeft(left,top, bottom, exitPoint0, exitPoint1);
	}
	
	public static SpawnLane fromTop(int leftEnterBorder, int horizontalEnterLine, int rightEnterBorder, Point exitPoint0,Point exitPoint1) {
		return new FromTop(leftEnterBorder, horizontalEnterLine, rightEnterBorder, exitPoint0, exitPoint1);
	}
	
	public static SpawnLane betweenPoints(Point enterPoint0, Point enterPoint1, Point exitPoint0, Point exitPoint1){	
		//creates a spawnLane, where the objects' directions are set to enter between the enterPoints and drift out between the exitPoints.
		
		if(enterPoint0.x == enterPoint1.x){	//enter line is vertical
			int vertical = enterPoint0.x;
			int top;
			int bottom;
			if(enterPoint0.y > enterPoint1.y){	//check which point is most to the top/bottom
				top = enterPoint1.y;
				bottom = enterPoint0.y;	
			} else{
				top = enterPoint0.y;
				bottom = enterPoint1.y;
			}		
			if(enterPoint0.x + enterPoint1.x > exitPoint0.x + exitPoint1.x){	//from right
				return new SpawnLane.FromRight(top, vertical, bottom, exitPoint0, exitPoint1);
			} else{	// From left
				return new SpawnLane.FromLeft(vertical, top, bottom, exitPoint0, exitPoint1);
			}
			
		}else if(enterPoint0.y == enterPoint1.y){	//enter line is horizontal
			int horizontal = enterPoint0.y;
			int left;
			int right;
			if(enterPoint0.x > enterPoint1.x){	//check which point is most to the left/right
				left = enterPoint1.x;
				right = enterPoint0.x;
			}else{
				left = enterPoint0.x;
				right = enterPoint1.x;
			}
			if(enterPoint0.y + enterPoint1.y > exitPoint0.y + exitPoint1.y){
				return new SpawnLane.FromBottom(left, right, horizontal, exitPoint1, exitPoint1);
			} else{
				return SpawnLane.fromTop(left, horizontal, right, exitPoint0, exitPoint1);
			}
		}else{
			throw new IllegalArgumentException();	//Enter line is not horizontal or vertical
		}
		
	}
	//public abstract void createRandomSpawnPoint(int margin);
	
	public void createRandomSpawnPoint(int margin){//int xMargin, int yMargin) {
		Point spawnPoint = this.startFrame.getRandomSpawnPoint(margin, margin);
		spawnX = spawnPoint.x;
		spawnY = spawnPoint.y;

		int exit0X = (int) (this.exitPoint0.x + margin*Math.cos(this.radiansBetweenExit0to1));
		int exit0Y = (int) (this.exitPoint0.y + margin*Math.sin(this.radiansBetweenExit0to1));
		
		int exit1X = (int) (this.exitPoint1.x + margin*Math.cos(this.radiansBetweenExit1to0));
		int exit1Y = (int) (this.exitPoint1.y + margin*Math.sin(this.radiansBetweenExit1to0));
		int direction0 = (int) DirectionCalculation.getDirectionDegFromTo(spawnX, spawnY, exit0X, exit0Y);
		int direction1 = (int) DirectionCalculation.getDirectionDegFromTo(spawnX, spawnY, exit1X, exit1Y);
		
		this.spawnDirection = (int) (direction0 + Math.random()*(direction1 - direction0) +1.5);	//+1.5 just tried and seems to be perfect
		//Log.d("randomDirection=", "spawnDirection= "+this.spawnDirection);
	}
	
	
	//-- FromLeft --//
	//TODO private:
	static class FromLeft extends SpawnLane{
			
		FromLeft(int verticalLine, int top, int bottom, Point exitPoint0, Point exitPoint1){
			super(exitPoint0, exitPoint1);
			this.startFrame  = UnboundedFrame.getUnboundedLeft(top, verticalLine, bottom);
		}
	}
	
	//-- class FromTop --//
	static class FromTop extends SpawnLane{
		//private final UnboundedFrame startFrame;
		
		FromTop(int leftEnterBorder, int horizontalEnterLine, int rightEnterBorder, Point exitPoint0, Point exitPoint1){
			super(exitPoint0, exitPoint1);
			startFrame = UnboundedFrame.getUnboundedTop(leftEnterBorder, rightEnterBorder, horizontalEnterLine);
		}
	}
	
	//-- class FromRight --//
	static class FromRight extends SpawnLane{
		
		FromRight( int topEnterborder, int verticalLine, int bottomEnterBorder, Point exitPoint0, Point exitPoint1){
			super(exitPoint0, exitPoint1);
			//TODO not written yet
			startFrame = null; //UnboundedFrame.getUnboundedTop(leftEnterBorder, rightEnterBorder, horizontalEnterLine);
		}
	}
	
	//-- class FromBottom --//
	static class FromBottom extends SpawnLane{
		
		FromBottom(int leftEnterBorder, int rightEnterBorder, int horizontalEnterLine, Point exitPoint0, Point exitPoint1){
			super(exitPoint0, exitPoint1);
			//TODO not written yet
			startFrame = null; //UnboundedFrame.getUnboundedTop(leftEnterBorder, rightEnterBorder, horizontalEnterLine);
		}
	}


	
}

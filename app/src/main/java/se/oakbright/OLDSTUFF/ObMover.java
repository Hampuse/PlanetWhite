package se.oakbright.OLDSTUFF;
import java.util.concurrent.ConcurrentLinkedQueue;

import se.oakbright.Frames.Frame;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.BattleSurface;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;

/**
 * ObMover is an object taking care of the moving part of a BattleObject that implements InMover.
 * @author hampuse
 *
 */
class ObMover{
	/*
private static final String TAG = ObMover.class.getSimpleName();
    ConcurrentLinkedQueue<Coord> path;	//The coordinates for the path that the ship should travel;
    Coord pathTail;	//The coordinate that was added most lately to the path queue. used because one cannot peek at the tail of a COncurrentLInkedQueue (...or?)
    BattleObject recentlyEjectedFrom;
    BattleObject trackObject;
    private float xFloat;	//The x coordinate in more precise way than x, that is an int representing the same.
    private float yFloat;	//The decimals of the y coordinate
    private Frame movingFrame;
    private BattleObject host;
    private final Speed speed;
    private Paint pathPaint = new Paint();	//
    private float distanceToNextCoord;
    protected ObMover.Coord nextCoord;
    private final int goVelocity;
    
    public ObMover(Frame movingFrame, BattleObject host, int velocity) {
        //%this.xFloat = xPos;
        //%this.yFloat = yPos;
        this.movingFrame = movingFrame;
        this.host = host;
        this.goVelocity = velocity;
        this.speed = new Speed(velocity);
        this.setPath(new ConcurrentLinkedQueue<Coord>());
        this.pathTail = new Coord(0,0);
        this.nextCoord = null;
        
        this.pathPaint.setColor(Color.WHITE); 
        this.pathPaint.setStyle(Style.STROKE);
        this.pathPaint.setStrokeWidth(2);
        this.pathPaint.setAntiAlias(true);
        //this.pathPaint.setStrokeJoin(Join.ROUND); //TODO if the width is increased the joins look ugly, but this doesent affect it, cause it is drawn without joins - fix 
        
    }

    public void update(){ 
		if (this.trackObject != null){		// track the trackObject if it exists.
			trackTrackObject();	
		}
		
		while(nextCoord != null && distanceToNextCoord < speed.getDistPerUpdate()){
			updateNextCoord();
		}
		distanceToNextCoord -= speed.getDistPerUpdate();
		// Update the x and y coordinates:
		this.xFloat += (speed.getDeltaXperUpdate()); 
		this.yFloat += (speed.getDeltaYperUpdate());
		host.updateX(this.getX());
		host.updateY(this.getY());
		host.updateDirection(Math.round(speed.getDirectionDeg()));
	// tuu	host.updateOrientationMatrix(speed.getDirectionDeg());
		
		checkForBorders();
	}	
    
	boolean isOkToCollideWith(BattleObject battleObject){	
		if(this.recentlyEjectedFrom != null)
			if(this.recentlyEjectedFrom.equals(battleObject))
				return false;
			
		return true;
	}
	
    /////////////////////////////////////////////////////
    // ///////// Path methods: //////////////////////////
	public void renderPath(Canvas canvas, BattleSurface battleSurface){	//draws the ship's path on canvas. called from render() in MainGameSurface.
		int xPrevious;
		int yPrevious;
		if(nextCoord != null){
			canvas.drawLine(battleSurface.coordToPxl(this.getX()), battleSurface.coordToPxl(this.getY()),battleSurface.coordToPxl(nextCoord.x), battleSurface.coordToPxl(nextCoord.y), pathPaint);	//Draw the first line from the ship to nextCoord
			if(!getPath().isEmpty()){	//check if a path exists, and draws it:
				xPrevious = nextCoord.x;
				yPrevious = nextCoord.y;
				for(Coord coord: getPath()){			//draw the lines in the path, 
					canvas.drawLine(battleSurface.coordToPxl(xPrevious), battleSurface.coordToPxl(yPrevious), battleSurface.coordToPxl(coord.x), battleSurface.coordToPxl(coord.y), pathPaint);
					xPrevious = coord.x;
					yPrevious = coord.y;
				}
			}
		}
	}
	
	void updateNextCoord() {
		if(!getPath().isEmpty()){			//if a path coordinate exist,.
			nextCoord = getPath().poll();					//set the first coordinate as nextCoord
			speed.setDirection(this.getX(), this.getY(), nextCoord.x, nextCoord.y);		//set a new direction towards the nextCoord
			this.distanceToNextCoord = (float) Math.hypot(this.getX() - nextCoord.x, this.getY() - nextCoord.y);
		} else{
			nextCoord = null;
		}
		
	}
	
	private void addPathCoord(Coord coord) {
		getPath().add(coord);
		this.pathTail = coord;
	}
	
	void addPathCoord(int x, int y){
		addPathCoord(new Coord(x,y));
	}
	

	void resetPath() {
		Log.d(TAG,"resetPath()");
		this.getPath().clear();
		this.trackObject = null;
	}
	
	void resetNextCoord(){
		Log.d(TAG,"resetNextCoord()");
		this.nextCoord = null;
	}
	
	void setTrackObject(BattleObject trackObject) {
		Log.d(TAG,"trackObject( "+ trackObject +" )");
		this.trackObject = trackObject;
	}
	
	void trackTrackObject() {
		if(trackObject.getX() != this.pathTail.x || trackObject.getY() != this.pathTail.y){	// ...and if its current positioner is not the last positioner in the path queue...
			this.addPathCoord(new Coord(trackObject.getX(), trackObject.getY()));	// ...put its positioner in the last positioner of the path queue.
		}
	}

	int getPathTailX() {
		return this.pathTail.x;
	}
	int getPathTailY() {
		return this.pathTail.y;
	}

	private ConcurrentLinkedQueue<Coord> getPath() {
		return path;
	}

	private void setPath(ConcurrentLinkedQueue<Coord> path) {
		this.path = path;
	}
	
	public boolean hasTrackObject(){
		if(this.trackObject != null)
			return true;
		else
			return false;
	}
	
	public boolean hasPath() {
		if(this.getPath().isEmpty())
			return false;
		else
			return true;
	}   
	///////////////////////////////////////////////////////
	//////////////////// Border methods: //////////////////////
	void checkForBorders(){
		float normalDeg = ObMover.normalDegreeOfCrossingBorder(this, this.movingFrame);
		if(Float.isNaN(normalDeg) == false)	//if NaN, no border is being crossed.
			this.handleOnBorder(normalDeg);
	}
	
	void handleOnBorder(float normalDeg) {
		host.deactivate(); //Kan detta lösas snyggare?
	}

	public static Float normalDegreeOfCrossingBorder(ObMover mover,Frame movingFrame) {
		float normalDeg;
		//int leftBorder, int upBorder, int rightBorder, int downBorder) {
		//--check if the Mover is heading towards the borders of the surface, 
		//--upBorder is interpreted as the "visual up", the lowest y-coordinate, typically zero.
		if(mover.getX() > movingFrame.right && mover.getVelocityX() > 0){
			normalDeg = 270; //normal is 270 for right border  //TODO: make the normal more general... 
		}
		else if(mover.getY() > movingFrame.bottom && mover.getVelocityY() > 0){
			normalDeg = 0; //normal is 0 for lower border  //TODO: make the normal more general... 
		}
		else if(mover.getX() < movingFrame.left && mover.getVelocityX() < 0){
			normalDeg = 90; //normal is 90 for left border  //TODO: make the normal more general... 
		}
		else if(mover.getY() < movingFrame.top && mover.getVelocityY() < 0){
			normalDeg = 180; //normal is 180 for right border  //TODO: make the normal more general... 
		}
		else{
			normalDeg = Float.NaN;
		}
		return normalDeg;
	}

	private int getX() {
		return Math.round(this.xFloat);
	}
	
	private int getY() {
		return Math.round(this.yFloat);
	}
	
	void setPositioner(int x, int y){
		this.xFloat = x;
		this.yFloat = y;
	}
	
	// ////////// Speed and direction methods: /////////////////////
	float getDirectionDeg(){
		return this.speed.getDirectionDeg();
	}
	
	void setDirection(int xTo, int yTo) {
		resetPath();
		resetNextCoord();
		this.speed.setDirection(this.getX(), this.getY(), xTo, yTo);
	}
	
	void setDirection(int directionDegrees) {
		resetPath();
		resetNextCoord();
		this.speed.setDirectionDeg(directionDegrees);
	}
	
	void setDirection(int xFrom, int yFrom, int xTo, int yTo){
		this.speed.setDirection(xFrom, yFrom, xTo, yTo);
	}
	
	void go(){
		this.speed.setVelocity(goVelocity);
	}
	
	void halt(){
		this.speed.setVelocity(Speed.STANDING_STILL);
	}
	
	public boolean isHalted(){
		if(this.speed.getVelocity() == Speed.STANDING_STILL)
			return true;
		return false;
			
	}
	void setVelocity(float f) {
		this.speed.setVelocity(f);
	}
	
	private float getVelocityX(){
		return this.speed.getXv();
	}
	private float getVelocityY(){
		return this.speed.getYv();
	}
	

	///////////// Nested classes: //////////////////
	private class Coord {
		public final int x;
		public final int y;
		
		private Coord(int x, int y){
			this.x = x;
			this.y = y;
		}

		private Coord(Coord coord) {
			this.x = coord.x;
			this.y = coord.y;
		}
	}


	*/
	
}
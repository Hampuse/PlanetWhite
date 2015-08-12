package se.oakbright.planetwhite;
	 

import java.util.HashSet;
import java.util.Set;

import se.oakbright.Frames.Frame;
import se.oakbright.modules.helpers.Direction;
import se.oakbright.modules.activatables.selectables.Selectable;
import se.oakbright.modules.internalpoints.InternalPoint;
import se.oakbright.modules.activatables.renderables.Renderable;
import se.oakbright.setup.BattleSetup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class BattleSurface extends SurfaceView implements SurfaceHolder.Callback, SurfaceObservable{
	
	private static final String TAG = BattleSurface.class.getSimpleName();
	private final int PXL_TO_DST;
	
	private final static int BACKGROUND_ID = R.drawable.akvarell_space;
	public static final float POINTING_RADIUS_inch = (float) 0.4;
	public final int POINTING_RADIUS_DST;// = 250000; // Dst the min distance around an object that can be pointed to select it. //TODO make it depend on pxlsize
	public static final int HILI_FEATHER = 10;	//pxl
    public static final int OBJECT_HILI_MAX_ALPHA = 255; //alpha value for the most opaque part of the highlights. A number between [0,255] where 255 is fully opaque.
	
	private static final int DOUBLE_TAP_MILLIS = 500;
	private static final int HILI_BORDER_WIDTH = 50;	//*1/1000 of battleSurface height. How borad the highlighted borders are, that shows with a color which team has the turn.

	
	private boolean isCreated = false;
	private Frame battleFieldFrame; //TODO should be final somehow and not ... Use this one instead of the Frame? frame only good for enlarged moving frame
	private int hiliBorderColor = Color.BLACK;	//The Color of the highlighted border around the screen.
	private boolean hiliBorderActive = false;
	private BattleModel model;
	
	
	private String avgFps;	// the fps to be displayed
	private TouchState touchState;
	private TurnHandler turnHandler;
	BattleTeam playerTeam;	//The player's team, or the team that holds the turn for the moment
	private int widthDST;
	private int heightDST;
	

	private Set<SurfaceObserver> surfaceObservers = new HashSet<SurfaceObserver>();
	
	public void setAvgFps(String avgFps) {
		this.avgFps = avgFps;
	}
	
	
	private Bitmap background;// = BitmapFactory.decodeResource(this.getResources(), R.drawable.emissionnebula4); //TODO scale it already here?
	 
	public BattleSurface(Context context, BattleSetup battleSetup, BattleModel model, int pxlToDst, float inchToDst) {
		super(context);
		this.POINTING_RADIUS_DST = (int) (inchToDst*BattleSurface.POINTING_RADIUS_inch);
		this.model = model;
		this.PXL_TO_DST = pxlToDst;
		getHolder().addCallback(this);		// adding the callback (this) to the surface holder to intercept events
		
		touchState = new TouchState();
		setFocusable(true); // TODO BORT VA ...make the GamePanel focusable so it can handle events
	}

	@Override	//TODO Ska den vara här?
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {	//Happens after addView in GameActivity
		this.battleFieldFrame = new Frame(0, 0, getWidth(), getHeight());	//TODO HUR FUNGKAR DENNA NU?
		this.background = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), BattleSurface.BACKGROUND_ID), this.getWidth(), this.getHeight(), false);
		this.widthDST = this.getWidth()*this.PXL_TO_DST;
		this.heightDST = this.getHeight()*this.PXL_TO_DST;
		//this.PXL_TO_DST = this.widthDST/getWidth();
		GameActivity.PXL_TO_DST = this.PXL_TO_DST;	//TODO bort
		this.isCreated = true;
		this.notifySurfaceObserversThatCreated();
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.v(TAG,"surfaceDestroyed(SurfaceHolder)");
		this.notifySurfaceObserversThatDestroyed();
	}
	
	public void update() {
		if (this.turnHandler != null) {
			turnHandler.updateTurnHandler();
		}
	}
	 
	public void setTurnHandler(TurnHandler turnHandler) {
		this.turnHandler = turnHandler;		
	}
	
	public Frame getBattleFieldFrame(){
		return this.battleFieldFrame;
	}

	public BattleTeam getMainPlayerTeam() {
		return this.playerTeam;
	}
	
	
	//////////////-- RENDER --///////////////
	public void render(Canvas canvas) {
		Paint paint = new Paint();
		canvas.drawBitmap(this.background, 0, 0, paint);
		paint.setColor(Color.MAGENTA);
		paint.setStyle(Style.STROKE);
		canvas.drawRect(coordToPxl(0)+1, coordToPxl(0)+1, coordToPxl(this.widthDST)-1, coordToPxl(this.heightDST)-1, paint); //debug, show the boundairies

		for (Renderable renderable: this.model.renderablesBackgroundLayer)
			//render(canvas, renderable);//.getBitmap(), this.createBitmapMatrixPxl(renderable);
			renderable.render(canvas, this);
		
		for (Renderable renderable: this.model.renderablesMiddleLayer)
			//render(canvas, renderable);//
			renderable.render(canvas, this);
		
		for (Renderable renderable: this.model.renderablesForegroundLayer)
			renderable.render(canvas, this); //renderable.drawIcon(canvas);
		
		


		
		if(hiliBorderActive){	//Draw the colored frame if it exists. This is meant to tell which team's turn it is at the moment.
			paint.setColor(this.hiliBorderColor);
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeWidth(0);
			int left, top, right, bottom;
			int borderWidth = this.getHeight() * HILI_BORDER_WIDTH / 1000; //gives the width of the border in number of pixels ( and also the number of lines to draw to make up the border.)
			for(int i=0; i < borderWidth; i++){	//makes many thin lined rectangles, that make a thicker one, with shading towards the middle.
				left = 0 + i;
				top = 0 + i;
				right = this.getWidth() - i;
				bottom = this.getHeight() - i;
				paint.setAlpha(150 - (150*i/borderWidth));	// Gradually making the paint more transparent towards the middle.
				canvas.drawRect(left, top, right, bottom, paint);
			}
		}
		displayFps(canvas, avgFps);			// display fps
		
		//-- Display score --//
		paint.setColor(this.playerTeam.getIntColor());
		canvas.drawText(("Points: " +this.playerTeam.getPointsString()) , 20, 20 , paint);
	}
	
	public float coordToPxl(int dst) {	
		return (float) dst/this.PXL_TO_DST;		//TODO  bort....beher gras om ifall rrlig frame ska tilltas.
	}
	
	public Point coordToPxl(int x, int y) {
		return new Point(x/this.PXL_TO_DST, y/this.PXL_TO_DST); //TODO behver gas om ifall rrlig frame ska tiltas. Och olika pixelstorlek i x och y!
	}
	
	public float dimensionToPxl(int dst){
		return (float) dst/this.PXL_TO_DST;	
	}


	public Matrix createBitmapMatrixPxl(InternalPoint cornerPoint, InternalPoint pivotPoint, Direction direction){
		Matrix matrix = new Matrix();
		matrix.postTranslate(coordToPxl(cornerPoint.x()), coordToPxl(cornerPoint.y()) );
		matrix.postRotate(direction.degrees(), coordToPxl(pivotPoint.x()), coordToPxl(pivotPoint.y()));
		return matrix;
	}

	void activateHiliBorder(int color){
		this.hiliBorderActive = true;
		this.hiliBorderColor = color;
	}

	void deactivateHiliBorder(){
		this.hiliBorderActive = false;
	}
	

	private void displayFps(Canvas canvas, String fps) {
		if (canvas != null && fps != null) {
			Paint paint = new Paint();
			paint.setARGB(255, 255, 255, 255);
			Point point = coordToPxl(this.getWidth() - 50000, 20000);
			canvas.drawText(fps, point.x, point.y , paint);
		}
	}
	
	
	//////////////////-- USER INPUT --//////////////////////
	@Override
	public boolean onTouchEvent(MotionEvent event) { 
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if(touchState.isDoubleTap(event.getDownTime()))
				touchState.handleActionQuick2ndDown((int) event.getX(), (int) event.getY());
			else
				touchState.handleActionDown((int) event.getX(), (int) event.getY());
			touchState.setLastDownMillis(event.getDownTime());
		}	
		else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			touchState.handleActionMove((int) event.getX(), (int) event.getY());
		} 
		else if (event.getAction() == MotionEvent.ACTION_UP) {
			touchState.handleActionUp((int) event.getX(), (int) event.getY());
		}
		return true; 
	}
	
	private class TouchState {
		private long lastDownMillis;
		private Selectable S; //selectedShip;
		//private Ship P; //pointedShip;
		private Selectable B; //selectedObject; //TODO AWAY
		private Selectable T; //targetedObject;
		private boolean SP;	//Ship Pressed Mode (motion event down has occured on S, and no up-event has occured yet. (ready to start Draw path);
		private boolean CP; //Coord draw target path mode, selected with doubleeTapHold on null object
		
		TouchState(){
			S = null;
			B = null;
			T = null;
			SP = false;
			CP = false;
		}
		
		private void reset(){
			setS(null);
			//B = null;
			//T = null;
			SP = false;
			CP = false;
			lastDownMillis = 0;
		}
		
		void setS(Selectable newS){	//Set selected ship (S)
			if (S==null){	// When S == null:
				if(newS != null){
					S = newS;
					S.select();//Highlighted(true);
				}
			}
			else{	// When S != null:
				if (newS == null){	// When newS == null:
					S.deselect();
					S.stopDrawingPath();
					S = null;
				}
				else{	// When newS != null:
					if(newS != S){
						S.deselect();
						S.stopDrawingPath();
						S = newS;
						S.select();
					}
				}
			}
		}
		
		protected void handleActionDown(int x,int y){
			Selectable tempObject = findSelectableCloseTo( x, y);
			if (tempObject != null){
				//if(tempObject instanceof Ship){
					if(( tempObject.getTeam() ).equals(playerTeam)){
						this.setS(tempObject); //S = (Ship) tempObject;
						this.B = null;	//null out any selected object, since a ship have been selected instead.
						this.T = null;
						//S.handleDownOnSelf();
						this.SP = true;
					}
					else{
						this.T = tempObject;	//The ship was not in the players team, so the ship is marked as target.
					}
				//}
			}
			else{ //Search for other possible objects to select or target.
					//TODO B = findClosObject(something else selectable...);
			}
				
			//-- Check states: --//
			if (tempObject == null){	//If still null, no objects could be selected at the coordinate.
				this.T = null;	
			} 
		}
		//TODO change PXL_TO_DST to "toPxl()" right?
		protected void handleActionQuick2ndDown(int eX,int eY){	
			if (S!=null && T==null){
				int distance = S.howNearPoint(eX*PXL_TO_DST, eY*PXL_TO_DST, POINTING_RADIUS_DST);
				if (distance != Integer.MAX_VALUE){
					S.deleteAim();
				}
				else {
					S.setAim(eX*PXL_TO_DST, eY*PXL_TO_DST);
					CP = true;
				}
				
			}
			else if(S!=null && T!=null){	//If a target is aimed at with a double tap, use the second tap as an offset point relative to the target. use it as the aim.
				int offsetX = toDst(eX)-T.getX();	
				int offsetY = toDst(eY)-T.getY();
				S.setAim(T, offsetX, offsetY);	
			}
		}
		
		protected void handleActionMove(int eX, int eY){
			if(S!=null && T==null && SP==true && CP==false){
				S.tryCreatePath(eX*PXL_TO_DST ,eY*PXL_TO_DST);	//TODO gra om PXL_TO_DST till en fderlig variabel, som uppdateras ifall scopet fr battleSurface r p sig? I BattleSurface i s� fall, och skicka den variabeln till BattleObject.icon n�r denna skall rendereas
			}
			else if(S!=null && T==null && CP==true){
				S.tryCreateAimPath(toDst(eX), toDst(eY));
			}
			else if(S!=null && T!=null && SP==false && CP==false){
				//TODO: aimpath f�r target och offset S.tryDrawAimPath(coord)
			}
		}	
		
		protected void handleActionUp(int eX, int eY){
			if(S!=null && T==null && S.isCreatingPath()){	//SP == true){
				Selectable trackObject = findSelectableCloseTo( eX, eY);
				/*if(trackObject == null){
					//TODO find in another collection: trackObject = findCloseObject(new ArrayList<BattleObject>(shipsA), new Coord(eX,eY));
				}*/
				if(trackObject != null){	//IF not null anymore:
					S.setTrackObject(trackObject);
				}
			}
			SP = false;
			CP = false;
			if(S != null){
				S.stopDrawingPath();
				//S.handleUpFromSelf();
			}
		}	
		protected void setLastDownMillis(long downMillis){
			this.lastDownMillis = downMillis;
		}
		
		protected boolean isDoubleTap(long downMillis){
			//Log.v(TAG,"isDoubleTap("+ downMillis +")");
			if((downMillis - this.lastDownMillis) < DOUBLE_TAP_MILLIS)
				return true;
			else
				return false;
		}


		private Selectable findSelectableCloseTo(int pointedXpxl, int pointedYpxl){	//TODO could be in model instead?
			//--Search through the ships in shipsA for a ship that is enough near the pointed at coordinates, and return the ship that is closest:
			Selectable closeObject = null;
			int distanceDst = Integer.MAX_VALUE;
			int nearestDistance = Integer.MAX_VALUE;
			if( !model.selectables.isEmpty()){
				for(Selectable s : model.selectables){
					distanceDst = s.howNearPoint(pointedXpxl*PXL_TO_DST, pointedYpxl*PXL_TO_DST, POINTING_RADIUS_DST);	//convert to Dst
					if(distanceDst != Integer.MAX_VALUE){
						if(distanceDst < nearestDistance){
							nearestDistance = distanceDst;
							closeObject = s;
						}
					}
				}
			}

			return closeObject;
		}
	}
		
	void setPlayerTeam(BattleTeam team){
		this.playerTeam = team;
		this.touchState.reset();
	}
	
	int toDst(int screenPxl){
		return screenPxl*this.PXL_TO_DST;		//simple for now, but can be changed if battleSurface is supposed to be moveable
	}

	////////////////////--TURN HANDLER --////////////////////////
	void haltBattleClock(){
		this.model.haltBattleClock();
	}
	
	void resumeBattleClock(){
		this.model.resumeBattleClock();
	}
	
	public void finishTurn() {
		if(this.turnHandler != null)
			this.turnHandler.finishTurnCommand();
	}
	void setPositionFinishTurnButton(){
		final Context c = this.getContext();	
		if(c instanceof se.oakbright.planetwhite.GameActivityOneScreen){
			((GameActivityOneScreen) c).finishTurnButton.post(new Runnable(){	//Post the command to the thread that created the button (Created in main activity)
			    public void run() {
			    	((GameActivityOneScreen) c).setPositionFinishTurnButton();
			    }
			});
		}
	}
	
	public void setVisibilityFinishTurnButton(final boolean b) {	//Turns on or off the visibility of the finish turn "GO!" button.
		final Context c = this.getContext();	
		//Log.v(TAG, "setVisibilityFinishTurnButton( " + b +" )");
		if(c instanceof se.oakbright.planetwhite.GameActivityOneScreen){
			((GameActivityOneScreen) c).finishTurnButton.post(new Runnable(){	//Post the command to the thread that created the button (Created in main activity)
			    public void run() {
			    	((GameActivityOneScreen) c).setVisibilityFinishTurnButton(b);	
			    }
			});
		}
		else{
			Log.w(TAG,"Not the right context, should be se.oakbright.planetwhite.GameActivity");
		}
		
	}

	@Override
	public void registerSurfaceObserver(SurfaceObserver observer) {
		this.surfaceObservers.add(observer);
		Log.d("svart", "i registerSurfaceObserver");
	}

	@Override
	public void unRegisterSurfaceObserver(SurfaceObserver observer) {
		this.surfaceObservers.remove(observer);
		
	}

	@Override
	public void notifySurfaceObserversThatCreated() {
		for(SurfaceObserver observer: surfaceObservers){
			observer.notifySurfaceCreated(this);
		}
	}
	
	@Override
	public void notifySurfaceObserversThatDestroyed() {
		for(SurfaceObserver observer: surfaceObservers){
			observer.notifySurfaceDestroyed(this);
		}
	}

		
	public boolean isCreated(){
		return isCreated;
	}

	
}
package se.oakbright.battlecontroller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import se.oakbright.Frames.Frame;
import se.oakbright.Frames.UnboundedFrame;
import se.oakbright.battlecontroller.SceneBaControllerState.InitState;
import se.oakbright.battlecontroller.SceneBaControllerState.Res;
import se.oakbright.battlecontroller.SceneBaControllerState.RunningState;
import se.oakbright.battleobjects.BattleObject;
import se.oakbright.battleobjects.IsActiveObservable;
import se.oakbright.battleobjects.IsActiveObserver;
import se.oakbright.battleobjects.Stone;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.BattleTeam;


import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.Log;

public class SceneBaController extends BaController implements IsActiveObserver{
	private static final String TAG = BattleTeam.class.getSimpleName();
	//private final int directionOfField;
	//private final Rect spawnRegion1;// = new Rect(0,0,battle,200);
	//private long timeAtLastReleaseStone = 0;
	//private int amount;
	//private Frame spawnFrame1;
	/*private Frame[]	spawnFramesINIT
	private Frame[] invalidSpawnFramesINIT;
	private Frame[] spawnFramesRUNNING;
	private Frame[] invalidSpawnFramesRUNNING;*/
	//private enum State {INIT, RUNNING}
	//private State state = State.INIT;
	//private HashSet<Stone> stones;// = new HashSet
	private SceneBaControllerState state;
	private SceneBaControllerState.InitState INIT;
	private SceneBaControllerState.RunningState RUNNING;
	private SceneBaControllerState.Res commonResources;
	
	
	public SceneBaController(BattleModel battleModel, BattleTeam team, SpawnLane spawnLane, int capacity){// ArrayList<Frame> commonInvalidSpawnFrames, int capacity, int directionOfField) {
		super(battleModel, team);
		//spawnRegion1 = new Rect(-1,-1,0,,);
		// int directionOfField = BattleObject.getDirectionDegBetween(battleSurface.getBattleFieldFrame().left, battleSurface.getBattleFieldFrame().top, battleSurface.getBattleFieldFrame().right, battleSurface.getBattleFieldFrame().bottom);
		Map<IsActiveObservable,Stone> stones = new IdentityHashMap<IsActiveObservable, Stone>(capacity);
		for(int i = 0; i<capacity; i++){
			Stone stone = new Stone(battleModel, team);
			stone.registerIAObserver(this);
			stones.put(stone, stone);// (stone);
		}
		this.commonResources = new Res(stones);//,directionOfField);
		//%Frame[] invalidSpawnFrames = new Frame[commonInvalidSpawnFrames.size()];
		//%commonInvalidSpawnFrames.toArray(invalidSpawnFrames);
		
		//--state RUNNING --// 
		Frame spawnFrameWished1 = UnboundedFrame.getUnboundedLeft(0,0,battleModel.trackFrame.bottom - 200);	//the line at the left border
		Frame spawnFrameWished2 = UnboundedFrame.getUnboundedTop(0,battleModel.trackFrame.right-200,0);	//the line at the top border;
		//Frame[] spawnFrames = SceneBaController.getSpawnFramesFromEnterPoints(); //new Frame[]{spawnFrameWished1, spawnFrameWished2};
		//ArrayList<Frame> invalidList = new ArrayList<Frame>();
		//invalidList.addAll(commonInvalidSpawnFrames);
		 //= new Frame[]{battleSurface.getBattleFieldFrame(), commonInvalidSpawnFrames};
		this.RUNNING = new RunningState(this.commonResources, spawnLane);//, invalidSpawnFrames);
		
		//--state INIT --// 
		spawnFrameWished1 = battleModel.trackFrame;	
		Frame[] spawnFrames = new Frame[]{spawnFrameWished1};
		//Frame[] invalidSpawnFrames2;
		// .toArray(Frame); //new Frame[]{new Frame(0,0,800,2000), new Frame(1000,0,3000,3000)}; //TODO nonsense. battleSurface.getOccupied..adsf... eller något som sätts i setup};
		this.INIT = new InitState(this.commonResources, spawnFrames);//, invalidSpawnFrames);
		//set the state to begin at INIT;
		this.state = this.INIT;	//
	}
	
	
	/*private class StoneMove1 extends Stone{	
		public StoneMover1(BattleSurface battleSurface, Coord coord,
				Frame movingFrame, BattleTeam team,
				BattleObject recentlyEjectedFrom) {
			super(battleSurface, coord, movingFrame, team, recentlyEjectedFrom);
			this.speed.setVelocity((float)0.4);
		}
		@Override
		protected void setupBitmap(BattleTeam team){
			this.setBitmap(battleSurface.BM_STONE2);
		}
	}*/
	
	/*public SceneBaController(BattleSurface battleSurface, BattleTeam team, int amount){
		super(battleSurface, team);
		this.amount = amount;
		releaseStone();
	}*/
	
	/*private static Frame[] getSpawnFramesFromEnterPoints(int x0, int y0, int x1, int y1) {
		
	}*/


	/*@Override
	void onCreate(){
		
	}*/
	void tryChangeState(){
		if(this.state == this.INIT)
			this.state = this.RUNNING;
	}
	
	@Override
	public void update() {
		state.update(this);
	}
	
	/*//TODO a sdfasdfae
	@Override
	public void onObjectIsGone(BattleObject bobject){
		Log.d("stejn", "onObjectIsGone(BattleObject)");
		if(bobject instanceof Stone && bobject.getTeam() == this.team){
			releaseRandomStone(); //TODO, respawn instead to save object creations
		}
	}*/
	
	/*private void releaseRandomStone() {
		//Log.d(TAG, "releaseStone()");
		//timeAtLastReleaseStone = battleSurface.getBattleTime();
		int x;
		int y;
		Point spawnPoint = new Point(5,5); //getRandomSpawnPoint(10,10, this.spawnFrames); //this.invalidSpawnFrames, this.spawnFramesWished); //this.spawnRegion1.top + (int) ((Math.random()) * this.spawnRegion1.height());//*this.battleSurface.getHeight());
		x = spawnPoint.x;
		y = spawnPoint.y;
		//x = this.spawnRegion1.left + (int) ((Math.random()) * this.spawnRegion1.width());
		//Log.d("random", "top=" + this.spawnRegion1.top + " height()="+ this.spawnRegion1.height());
		
		this.newlyReleasedObject = new Stone(battleSurface, x, y ,battleSurface.getBattleFieldFrame(), this.team, null);
		this.newlyReleasedObject.registerIAObserver(this);
		x = x + this.newlyReleasedObject.getBoundingSquareSide();	// making the x coordinate larger so that the mover first appear outside the screen.
		((Stone) this.newlyReleasedObject).setDirection(directionOfField);
		
	}*/
	
	/*void spawnRandomStone(Frame[] spawnFrames) {
		Log.d("cstone", " spawnRandomStone(..) in object="+ this);
		//timeAtLastReleaseStone = battleSurface.getBattleTime();
		int x;
		int y;
		Point spawnPoint = Frame.getRandomSpawnPoint(10,10, spawnFrames); //this.invalidSpawnFrames, this.spawnFramesWished); //this.spawnRegion1.top + (int) ((Math.random()) * this.spawnRegion1.height());//*this.battleSurface.getHeight());
		x = spawnPoint.x;
		y = spawnPoint.y;
		Stone newStone = new Stone(battleSurface, x, y ,battleSurface.getBattleFieldFrame(), this.team, null);
		newStone.registerIAObserver(this);
		//x = x + newStone.getBoundingSquareSide();	// making the x coordinate larger so that the mover first appear outside the screen.
		newStone.setDirection(directionOfField);
		newStone.activate();
		Log.d("cstone", " end of spawn... newStone= "+newStone);
	}*/
	
	/*Point getRandomReSpawnForStone(Stone stone, Frame[] spawnFrames){
		int x;
		int y;
		
		Point spawnPoint = getRandomSpawnPoint(stone.getBoundingSquareSide(),stone.getBoundingSquareSide(), spawnFrames); //this.invalidSpawnFrames, this.spawnFramesWished); //this.spawnRegion1.top + (int) ((Math.random()) * this.spawnRegion1.height());//*this.battleSurface.getHeight());
		/*x = spawnPoint.x;
		y = spawnPoint.y;
		stone.setPositioner(x, y);*/
	/*	return spawnPoint
	}*/
	
	/*static Stone spawnRandomStone(BattleSurface battleSurface, Frame[] spawnFrames, int direction,Team team){
		//timeAtLastReleaseStone = battleSurface.getBattleTime();
		int x;
		int y;
		Point spawnPoint = getRandomSpawnPoint(10,10, spawnFrames); //this.invalidSpawnFrames, this.spawnFramesWished); //this.spawnRegion1.top + (int) ((Math.random()) * this.spawnRegion1.height());//*this.battleSurface.getHeight());
		x = spawnPoint.x;
		y = spawnPoint.y;
		//x = this.spawnRegion1.left + (int) ((Math.random()) * this.spawnRegion1.width());
		//Log.d("random", "top=" + this.spawnRegion1.top + " height()="+ this.spawnRegion1.height());
		
		Stone newStone = new Stone(battleSurface, x, y , battleSurface.getBattleFieldFrame(), team, null);
		x = x + newStone.getBoundingSquareSide();	// making the x coordinate larger so that the mover first appear outside the screen.
		newStone.setDirection(direction);*/
	
	

	@Override
	public void notifyIsActiveChangeIn(IsActiveObservable subject) {
			state.notifyIsActiveChangeIn(subject);	
	}
	
	
	
	
}

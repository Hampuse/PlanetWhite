package se.oakbright.battlecontroller;

import java.util.Map;
import java.util.Set;

import android.graphics.Point;
import android.util.Log;

import se.oakbright.Frames.Frame;
import se.oakbright.battleobjects.IsActiveObservable;

public abstract class SceneBaControllerState {
	public abstract void update(SceneBaController controller);
	public abstract void notifyIsActiveChangeIn(IsActiveObservable subject);
	
	//////////-- INIT--////////////////////////////
	public static class InitState extends SceneBaControllerState implements RespawnDemander{
		private Frame[] spawnFrames;
		//private Frame[] invalidSpawnFrames;
		private Res res;
		public InitState(Res res, Frame[] spawnFrames){ //, Frame[] invalidSpawnFrames){
			this.spawnFrames = spawnFrames;
			//this.invalidSpawnFrames = invalidSpawnFrames; 
			this.res = res;
		}
		
		@Override
		public void update(SceneBaController controller){
			//TODO redo
			/* for(Stone stone: res.stones.values()){
				stone.setDirection(130);
				while(!RespawnHandler.tryRespawnOnce(stone, this)){	//TODO safer not to get stuck in loop
				}
			}*/
			controller.tryChangeState();
		}

		@Override
		public void notifyIsActiveChangeIn(IsActiveObservable subject) {
			//don't care	//TODO consume it somehow?
		}

		@Override
		public Frame[] getSpawnFrames() {
			return this.spawnFrames;
		}

		/*@Override
		public Frame[] getInvalidSpawnFrames() {
			return this.invalidSpawnFrames;
		}*/
	}

	//////////-- RUNNING--////////////////////////////
	public static class RunningState extends SceneBaControllerState{ // implements RespawnDemander{
		//private Frame[] spawnFrames;
		//private Frame[] invalidSpawnFrames;
		private SceneBaController controller;
		private Res res;
		private SpawnLane spawnLane;
		//private PointFinder pf;
		public RunningState(Res res, SpawnLane spawnLane){ //Frame[] spawnFrames, Frame[] invalidSpawnFrames,
			//this.spawnFrames = spawnFrames;
			//this.invalidSpawnFrames = invalidSpawnFrames;
			this.res = res;
			this.spawnLane = spawnLane;
		}
		
		@Override
		public void update(SceneBaController controller) {			//TODO något?
			//if(pf != null){
			//Log.d("cstone", " UPDATE");
		}

		@Override
		public void notifyIsActiveChangeIn(IsActiveObservable subject) {
			if (subject.isActive() == false){
				//TODO FIX if( res.stones.containsKey(subject)){
					//TODO FIX Stone stone = res.stones.get(subject);
					//TODO FIX RespawnHandler.respawnObject(stone, this.spawnLane);// this);//spawnFrames, invalidSpawnFrames) ; //Respawnable obj, )
				//}
			}
		}

		/*@Override
		public Frame[] getSpawnFrames() {
			return this.spawnFrames;
		}

		@Override
		public Frame[] getInvalidSpawnFrames() {
			return this.invalidSpawnFrames;
		}*/
	}
	
	//-- common resources object --//
	public static class Res{
		//TODO FIX public Map<IsActiveObservable, Stone> stones;
		//TODO FIX public Res(Map<IsActiveObservable, Stone> stones){// int directionOfField){
			//TODO FIX this.stones = stones;
		//}
	}
}

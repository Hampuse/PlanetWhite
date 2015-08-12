package se.oakbright.battlecontroller;

import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import android.graphics.Point;
import android.util.Log;
import se.oakbright.Frames.Frame;

public final class RespawnHandler {
	public final static RespawnHandler theRespawnHandler = new RespawnHandler(); //TODO göra så att den kollar med battleSurface så nytt spawn inte krockar med något!
	private IdentityHashMap<Respawnable, RespawnDemander> unSpawned = new IdentityHashMap<Respawnable, RespawnDemander>();
	private IdentityHashMap<Respawnable, SpawnLane> unSpawnedWithLane = new IdentityHashMap<Respawnable, SpawnLane>();
	
	public static RespawnHandler getInstance(){
		return theRespawnHandler;
	}
	public static boolean tryRespawnOnce(Respawnable object, RespawnDemander demander){
		Frame[] spawnFrames = demander.getSpawnFrames();
		int xMargin = object.getBoundingSquareSide();	//TODO todo width height
		int yMargin = object.getBoundingSquareSide();
		Point spawnPoint = Frame.getRandomSpawnPoint(xMargin, yMargin, spawnFrames);
		//%if(Frame.isSpawnPointOutside(demander.getInvalidSpawnFrames(), spawnPoint, xMargin, yMargin)){
			object.respawn(spawnPoint.x, spawnPoint.y);
			return true;
		//%}
		//%return false;
	}
	
	public static void respawnObject(Respawnable object, RespawnDemander demander){ //Frame[] spawnFrames, Frame[] invalidSpawnFrames) {
		if(!RespawnHandler.tryRespawnOnce(object, demander)){
			theRespawnHandler.unSpawned.put(object, demander);
		}
	}
	
	public void update(){
		if(!this.unSpawned.isEmpty()){
			Iterator<Entry<Respawnable, RespawnDemander>> it = this.unSpawned.entrySet().iterator();
			while (it.hasNext()) {
	        	Map.Entry<Respawnable, RespawnDemander> entry = (Map.Entry<Respawnable, RespawnDemander>)it.next();
	        	if( tryRespawnOnce((Respawnable) entry.getKey(), (RespawnDemander) entry.getValue()))	// try respawn the object, and remove it if succeded
	        		it.remove(); 
	    	}
		}
		//TODO update for lane 
	}
	
	public static boolean tryRespawnOnce(Respawnable object, SpawnLane spawnLane){
		int xMargin = object.getMaxBoundingRadii();	//TODO todo width height
		int yMargin = object.getMaxBoundingRadii();
		spawnLane.createRandomSpawnPoint(xMargin);	//TODO not x just round
		int x = spawnLane.spawnX;
		int y = spawnLane.spawnY;
		int direction = spawnLane.spawnDirection;
		
		//Point spawnPoint = spawnLane.getSpawnPoint(); enterFrame.getRandomSpawnPoint(xMargin, yMargin);
		//TODO check if colliding 
		object.respawn(x, y, direction);
		return true;
	}
	
	public static void respawnObject(Respawnable object, SpawnLane spawnLane){
		if(!tryRespawnOnce(object, spawnLane))
			theRespawnHandler.unSpawnedWithLane.put(object, spawnLane);
	}
	
	public static boolean tryRespawnOnce(Respawnable object, Frame[] spawnFrames){
		int xMargin = object.getMaxBoundingRadii();	
		int yMargin = object.getMaxBoundingRadii();
		Point spawnPoint = Frame.getRandomSpawnPoint(xMargin, yMargin, spawnFrames);
		Log.d("aicontrol", " respawn at ("+ spawnPoint.x + ", " +spawnPoint.y);
		object.respawn(spawnPoint.x, spawnPoint.y);	//TODO check for collision before spawning.
		return true;
	}
	
	public static void respawnObject(Respawnable object, Frame[] spawnFrames){
		if(!tryRespawnOnce(object, spawnFrames));
			//TODO: theRespawnHandler.unSpawnedWithFrames.put(object, spawnFrames);
	}
}

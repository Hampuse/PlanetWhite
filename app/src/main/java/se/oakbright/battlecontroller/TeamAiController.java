package se.oakbright.battlecontroller;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import android.util.Log;
import se.oakbright.Frames.Frame;
import se.oakbright.battleobjects.Ship;
import se.oakbright.planetwhite.BattleClock.BaTimer;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.BattleTeam;
import se.oakbright.ships1.RobotShipDirect;

public class TeamAiController extends BaController{
	private int timeBetweenReleases = 2000; //ms
	private final int timeBetweenReleaseTimeDecrease = 5000; //ms
	private final int timeDecrease = 50; //ms;
	private final BaTimer releaseTimer = new BaTimer();
	private final BaTimer timeDecTimer = new BaTimer();

	public TeamAiController(BattleModel battleModel, BattleTeam team){
		super(battleModel, team);
		this.releaseTimer.setInterval(2000);
		this.timeDecTimer.setInterval(5000);
		this.releaseTimer.reset();
		this.timeDecTimer.reset();
		
	}
	
	public static TeamAiController.Blueprint getBlueprint(){
		return new TeamAiController.Blueprint(TeamAiController.class);
	}
	
	@Override
	public void update(){
		if(releaseTimer.isFinished()){
			Ship ship = new RobotShipDirect(this.battleModel, this.team);
			RespawnHandler.respawnObject(ship, new Frame[]{battleModel.trackFrame});
			if(timeDecTimer.isFinished()){
				timeDecTimer.reset();
				releaseTimer.decreaseInterval(50);
			} 
			releaseTimer.reset();
		}
	}
	
	
	
	
	public static class Blueprint implements Serializable{
		private Class controllerClass;

		public Blueprint(Class controllerClass){
			//super(controllerClass);
			this.controllerClass = controllerClass;
		}
		
		public TeamAiController create(	BattleModel battleModel, BattleTeam team){
			//return new bpClass.g (battleModel, xPos, yPos, movingFrame, team, recentlyEjectedFrom);
			//Constructor constructor;
			try {
				Constructor constructor = this.controllerClass.getConstructor(new Class[]{BattleModel.class, BattleTeam.class});
				try {
					return (TeamAiController)constructor.newInstance(battleModel, team);
				} catch (IllegalArgumentException e) {
					Log.e("TAG","error when using reflection constructor");
					e.printStackTrace();
				} catch (InstantiationException e) {
					Log.e("TAG","error when using reflection constructor");
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					Log.e("TAG","error when using reflection constructor");
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					Log.e("TAG","error when using reflection constructor");
					e.printStackTrace();
				}
			} catch (NoSuchMethodException e) {
				Log.e("TAG","The constructor seem to not exist. reflection constructor");
				e.printStackTrace();
			}
			Log.e("TAG","unable to create object of class, returning null. Possibly the class is not subclass of TeamAiController, or it's an abstract class ");
			return null;
		}
	}
}

package se.oakbright.planetwhite;

import se.oakbright.modules.helpers.Speed;

public class BattleClock {
	private static final int NORMAL_PACE = 1;
	private float pace;
	private int time;
	private long timeAtLastUpdate;
	private int microsPerUpdate;

	public BattleClock(){
		this.pace = NORMAL_PACE;	//TODO kunna variera? eller välja?
		this.time = 0;
		timeAtLastUpdate = System.currentTimeMillis();	//ms
	}
	
	public void update(){	//updates the time.
		long timeSinceUpdate = System.currentTimeMillis() - timeAtLastUpdate;
		this.timeAtLastUpdate = System.currentTimeMillis();
		this.time = (int) (this.time + timeSinceUpdate*pace);
		microsPerUpdate = (int) (1000*timeSinceUpdate*this.pace);		//TODO kanske ändra endast när det är stora ändringar typ skifte av pace.
		
	}
	
	public void halt() {
		this.pace = 0;
		
	}

	public void resume() {
		this.pace = NORMAL_PACE;
	}
	
	public boolean isHalted(){
		return(pace == 0);
	}
	
	public long getBattleClockTime(){
		long time = this.time;
	 	return time;
	}
	
	public static class BaTimer{	//simple not optimized.
		private int interval;
		public long startTime;
		public BaTimer(){
			this.startTime = System.currentTimeMillis();
		}
		
		public void setInterval(int interval){
			this.interval = interval;
		}
		
		public void reset(){
			this.startTime = System.currentTimeMillis();
		}
		
		public void decreaseInterval(int dec){
			this.interval = this.interval - dec;
			if(this.interval < 0)
				this.interval = 0;
		}
		
		public boolean isFinished(){
			if (System.currentTimeMillis() - this.startTime > interval )
				return true;
			else
				return false;
		}
		
	}
	public int getMicroSecondsPerUpdate(){
		return microsPerUpdate;
	}
}

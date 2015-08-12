package se.oakbright.planetwhite;


import java.text.DecimalFormat;



import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;



public class SurfaceThread extends Thread {
	
	private static final String TAG = SurfaceThread.class.getSimpleName();
	
	//fps
	private final static int 	MAX_FPS = 50;	// desired fps
	private final static int	MAX_FRAME_SKIPS = 5;	
	private final static int	FRAME_PERIOD = 1000 / MAX_FPS;	// the frame period
	/* Stuff for stats */
    private DecimalFormat df = new DecimalFormat("0.##");  // 2 dp 
	private final static int 	STAT_INTERVAL = 1000; //ms 		// we'll be reading the stats every second
	private final static int	FPS_HISTORY_NR = 10;	// the average will be calculated by storing the last n FPSs
	private long lastStatusStore = 0;			// last time the status was stored
	private long statusIntervalTimer	= 0l; 			// the status time counter
	private long totalFramesSkipped			= 0l;		// number of frames skipped since the game started
	private long framesSkippedPerStatCycle 	= 0l;		// number of frames skipped in a store cycle (1 sec)
	private int frameCountPerStatCycle = 0;// number of rendered frames in an interval
	private long totalFrameCount = 0l;
	private double 	fpsStore[];		// the last FPS values
	private long 	statsCount = 0;		// the number of times the stat has been read
	private double 	averageFps = 0.0;		// the average FPS since the game started

	
	private SurfaceHolder surfaceHolder;	// Surface holder that can access the physical surface
	private BattleSurface gameSurface;	// The actual view that handles inputs and draws to the surface 

	
	private boolean running;			// flag to hold game state
	public void setRunning(boolean running) {
		this.running = running;
	}

	public SurfaceThread(SurfaceHolder surfaceHolder, BattleSurface gameSurface) {
		super();
		this.surfaceHolder = surfaceHolder;
		this.gameSurface = gameSurface;
	}

	@Override
	public void run() {
		Canvas canvas;
		//Log.d(TAG, "Setting up gameSurface");
		//this.gameSurface.setup();
		
		Log.d(TAG, "Starting game loop");
		initTimingElements();		// initialise timing elements for stat gathering
		
		long beginTime;		// the time when the cycle begun
		long timeDiff;		// the time it took for the cycle to execute
		int sleepTime;		// ms to sleep (<0 if we're behind)
		int framesSkipped;	// number of frames being skipped 

		sleepTime = 0;
		
		while (running) {
			canvas = null;		
			try {											// try locking the canvas for exclusive pixel editing in the surface
				canvas = this.surfaceHolder.lockCanvas();
				synchronized (surfaceHolder) {
					beginTime = System.currentTimeMillis();
					framesSkipped = 0;	// resetting the frames skipped
					this.gameSurface.update(); 	
					this.gameSurface.render(canvas);		// render state to the screen, draws the canvas on the panel			
					
					timeDiff = System.currentTimeMillis() - beginTime;		// calculate how long did the cycle take
					sleepTime = (int)(FRAME_PERIOD - timeDiff);					// calculate sleep time
					
					if (sleepTime > 0) {			// if sleepTime > 0 we're OK
						try {
							Thread.sleep(sleepTime);	
						} catch (InterruptedException e) {}
					}
					
					while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {

						this.gameSurface.update();  	// we need to catch up. update without rendering
						sleepTime += FRAME_PERIOD;	// add frame period to check if in next frame
						framesSkipped++;
					}

					if (framesSkipped > 0) {
						Log.d(TAG, "Skipped:" + framesSkipped);
					}
					framesSkippedPerStatCycle += framesSkipped;			// for statistics
					storeStats();		// calling the routine to store the gathered statistics
				}
			} finally {
				if (canvas != null) {			// in case of an exception the surface is not left in an inconsistent state
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}	// end finally
		}
	}

	/**
	 * The statistics - it is called every cycle, it checks if time since last
	 * store is greater than the statistics gathering period (1 sec) and if so
	 * it calculates the FPS for the last period and stores it.
	 * 
	 *  It tracks the number of frames per period. The number of frames since 
	 *  the start of the period are summed up and the calculation takes part
	 *  only if the next period and the frame count is reset to 0.
	 */
	private void storeStats() {
		frameCountPerStatCycle++;
		totalFrameCount++;
		// assuming that the sleep works each call to storeStats
		// happens at 1000/FPS so we just add it up
//		statusIntervalTimer += FRAME_PERIOD;
		
		// check the actual time
		statusIntervalTimer += (System.currentTimeMillis() - statusIntervalTimer);
		
		if (statusIntervalTimer >= lastStatusStore + STAT_INTERVAL) {
			// calculate the actual frames pers status check interval
			double actualFps = (double)(frameCountPerStatCycle / (STAT_INTERVAL / 1000));
			
			//stores the latest fps in the array
			fpsStore[(int) statsCount % FPS_HISTORY_NR] = actualFps;
			
			// increase the number of times statistics was calculated
			statsCount++;
			
			double totalFps = 0.0;
			// sum up the stored fps values
			for (int i = 0; i < FPS_HISTORY_NR; i++) {
				totalFps += fpsStore[i];
			}
			
			// obtain the average
			if (statsCount < FPS_HISTORY_NR) {
				// in case of the first 10 triggers
				averageFps = totalFps / statsCount;
			} else {
				averageFps = totalFps / FPS_HISTORY_NR;
			}
			// saving the number of total frames skipped
			totalFramesSkipped += framesSkippedPerStatCycle;
			// resetting the counters after a status record (1 sec)
			framesSkippedPerStatCycle = 0;
			statusIntervalTimer = 0;
			frameCountPerStatCycle = 0;

			statusIntervalTimer = System.currentTimeMillis();
			lastStatusStore = statusIntervalTimer;
//			Log.d(TAG, "Average FPS:" + df.format(averageFps));
			gameSurface.setAvgFps("FPS: " + df.format(averageFps));
		}
	}

	private void initTimingElements() {
		// initialise timing elements
		fpsStore = new double[FPS_HISTORY_NR];
		for (int i = 0; i < FPS_HISTORY_NR; i++) {
			fpsStore[i] = 0.0;
		}
		Log.d(TAG + ".initTimingElements()", "Timing elements for stats initialised");
	}

}
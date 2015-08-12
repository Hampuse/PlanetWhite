package se.oakbright.planetwhite;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public abstract class GameActivity extends Activity implements SurfaceObserver{ 
	public static int PXL_TO_DST;	//The ratio from the number of pixels on the screen to the number of distances. Determines resolution together with the actual pixel size. Is set automatically.
	public static final float SIZE_ADJUST = 1;		//The global coeff from the sizes of objects to the number of distances. 1 is the original standard
	public static final float VELOCITY_ADJUST = 1;		//The global coeff from the velocities of objects to the number of distances. 1 isthe original standard.
	
	LinearLayout mainFrame;
	BattleSurface[] battleSurfaces;
	BattleModel battleModel;
	BattleThread thread;
	//FrameLayout gameFrame;
	RelativeLayout gameWidgets;
	
	///Button finishTurnButton;
    private static final String TAG = GameActivity.class.getSimpleName();
    
       
	/*public static int getPxlToDst(){
		return battleSurface[0].getPxlToDst();
	}*/
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
	}
	public int getAvailableHeight(){
		Rect rect = new Rect(); 
        Window win = getWindow();  // Get the Window
        win.getDecorView().getWindowVisibleDisplayFrame(rect); 
        // Get the height of Status Bar 
        int statusBarHeight = rect.top; 
        // Get the height occupied by the decoration contents 
        int contentViewTop = win.findViewById(Window.ID_ANDROID_CONTENT).getTop(); 
        // Calculate titleBarHeight by deducting statusBarHeight from contentViewTop  
        int titleBarHeight = contentViewTop - statusBarHeight; 
        //   Log.i("MY", "titleHeight = " + titleBarHeight + " statusHeight = " + statusBarHeight + " contentViewTop = " + contentViewTop); 
 
        // By now we got the height of titleBar & statusBar
        // Now lets get the screen size
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);   
        int screenHeight = metrics.heightPixels;
       
        //Log.i("MY", "Actual Screen Height = " + screenHeight + " Width = " + screenWidth);   
 
        // Now calculate the height that our layout can be set
        // If you know that your application doesn't have statusBar added, then don't add here also. Same applies to application bar also 
        int layoutHeight = screenHeight - (titleBarHeight + statusBarHeight);
          // Log.i("MY", "Layout Height = " + layoutHeight);   
        return layoutHeight;
        // Lastly, set the height of the layout       
       // LinearLayout.LayoutParams rootParams = (android.widget.LinearLayout.LayoutParams)root.getLayoutParams();
        // rootParams.height = layoutHeight;
       // root.setLayoutParams(rootParams);      
    } 

	public int getAvailableWidth(){
		//Rect rect = new Rect(); 
        //Window win = getWindow();  // Get the Window
        //win.getDecorView().getWindowVisibleDisplayFrame(rect); 
        // Get the height of Status Bar 
       
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);   
        int screenWidth = metrics.widthPixels;
        //Log.i("MY", "Actual Screen Height = " + screenHeight + " Width = " + screenWidth);   
 
        // Now calculate the height that our layout can be set
        // If you know that your application doesn't have statusBar added, then don't add here also. Same applies to application bar also 
 
        // Lastly, set the height of the layout       
       // LinearLayout.LayoutParams rootParams = (android.widget.LinearLayout.LayoutParams)root.getLayoutParams();
        // rootParams.height = layoutHeight;
       // root.setLayoutParams(rootParams);      
       return screenWidth;
     } 
	protected float getInchToDst(int pxlToDst){
		float xdpi = getResources().getDisplayMetrics().xdpi;
		float ydpi = getResources().getDisplayMetrics().ydpi;	//TODO should be depending on ydpi as well!
		return (xdpi*pxlToDst);
	}
    //MASSA OVERRIDE FR NOTIFICATIONER:
	@Override
	protected void onRestart() {
		super.onRestart();
		// Notification that the activity will be started
		Log.i(TAG, "onRestart");
	}

	@Override
	protected void onStart() {
		super.onStart();
		// Notification that the activity is starting
		Log.i(TAG, "onStart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		// Notification that the activity will interact with the user
		Log.i(TAG, "onResume");
	}

	protected void onPause() {
		super.onPause();
		// Notification that the activity will stop interacting with the user
		Log.i(TAG, "onPause" + (isFinishing() ? " Finishing" : ""));
	}

	@Override
	protected void onStop() {
		super.onStop();
		// Notification that the activity is no longer visible
		Log.i(TAG, "onStop");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// Notification the activity will be destroyed
		Log.i(TAG,
				"onDestroy "
				// Log which, if any, configuration changed
				+ Integer.toString(getChangingConfigurations(), 16));
	}

// ////////////////////////////////////////////////////////////////////////////
// Called during the lifecycle, when instance state should be saved/restored
// ////////////////////////////////////////////////////////////////////////////

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// Save instance-specific state
		//&outState.putString("answer", state);
		super.onSaveInstanceState(outState);
		Log.i(TAG, "onSaveInstanceState");
		
	}

	@Override
	public Object onRetainNonConfigurationInstance() {
		Log.i(TAG, "onRetainNonConfigurationInstance");
		return new Integer(getTaskId());
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedState) {
		super.onRestoreInstanceState(savedState);
		// Restore state; we know savedState is not null
		//&String answer = null != savedState ? savedState.getString("answer") : "";
		//Object oldTaskObject = getLastNonConfigurationInstance();
		//if (null != oldTaskObject) {
		//	int oldtask = ((Integer) oldTaskObject).intValue();
		//	int currentTask = getTaskId();
			// Task should not change across a configuration change
		//assert oldtask == currentTask;
		//}
		Log.i(TAG, "onRestoreInstanceState");
		//+ (null == savedState ? "" : RESTORE) + " " + answer);
	}

// ////////////////////////////////////////////////////////////////////////////
// These are the minor lifecycle methods, you probably won't need these
// ////////////////////////////////////////////////////////////////////////////

	@Override
	protected void onPostCreate(Bundle savedState) {
		super.onPostCreate(savedState);
		//String answer = null;
		// savedState could be null
		//if (null != savedState) {
		//answer = savedState.getString("answer");
		//}
		Log.i(TAG, "onPostCreate");
		//+ (null == savedState ? "" : (RESTORE + " " + answer)));
	}

	@Override
	protected void onPostResume() {
		super.onPostResume();
		Log.i(TAG, "onPostResume");
	}

	@Override
	protected void onUserLeaveHint() {
		super.onUserLeaveHint();
		Log.i(TAG, "onUserLeaveHint");
	}

	@Override
	public void notifySurfaceCreated(BattleSurface surface) {
		//Check if all surfaces are created, and if so, have the battleModel to be setup.
		Log.d("svart", "i notify created");
		boolean allCreated = true;
		for(int i = 0; i < battleSurfaces.length;i++){	
			if (! battleSurfaces[i].isCreated())
				allCreated = false;
		}
		if(allCreated){
			Log.d("svart", "i notify allCreated");
			this.battleModel.setup();
			setupSurfaces();	//setup surfaces with stuff that requires the battleModel to be setup, (for now, only playerTeams)
			this.thread.setRunning(true);
			this.thread.start();
		}
	}

	@Override
	public void notifySurfaceDestroyed(BattleSurface surface) {
		// TODO Auto-generated method stub
		
	}
	
	abstract void setupSurfaces();
}


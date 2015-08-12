package se.oakbright.planetwhite;

import java.util.List;

import se.oakbright.setup.BattleSetup;
import se.oakbright.setup.SimpelW;
import se.oakbright.setup.Team;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class GameActivityOneScreen extends GameActivity{ 
	
	RelativeLayout gameWidgets;
	Button finishTurnButton;
    private static final String TAG = GameActivityOneScreen.class.getSimpleName();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	final BattleSetup battleSetup = (BattleSetup) getIntent().getSerializableExtra("battleSetup");		//getParcelableExtra("battleSetup");
    	
    	// Calculate available width and height, and create widthDst and heightDst that fills the space;
    	int availableWidth = super.getAvailableWidth();
    	int availableHeight = super.getAvailableHeight();
       	final int widthDST = 16777216/2;
       	int pxlToDst = widthDST/availableWidth;
    	final int heightDST = availableHeight*pxlToDst; 
    	
    	
    	
    	battleModel = new BattleModel(this, battleSetup, widthDST, heightDST);	
    	this.battleSurfaces = new BattleSurface[1];
    	
    	setContentView(R.layout.activity_battle_onescreen);
    	mainFrame = (LinearLayout) findViewById(R.id.mainframe);
    	//mainFrame.setLayoutParams(new FrameLayout.LayoutParams(1500,1500));//FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

    	final FrameLayout surfaceFrame = (FrameLayout) findViewById(R.id.surface); //new FrameLayout(this);
    	
    	//final Context context = this;
    	//mainFrame.post(new Runnable() { 
          //  public void run() { 
            //	int surfaceWidth = getAvailableWidth();
            	//int surfaceHeight = getAvailableHeight();
            	//GameActivity.PXL_TO_DST = widthDST/surfaceWidth;
            	//battleSetup.setSurfaceHeight(surfaceHeight);
            	//battleSetup.setSurfaceWidth(surfaceWidth);
            	//battleModel.setSurfaceHeight(surfaceHeight);
            	//battleModel.setSurfaceWidth(surfaceWidth);
            	
            	this.battleSurfaces = new BattleSurface[1];
            	
            	final BattleSurface battleSurface = new BattleSurface(this, battleSetup, battleModel, pxlToDst, this.getInchToDst(pxlToDst));
            	this.battleSurfaces[0] = battleSurface; 
            	surfaceFrame.addView(battleSurface);
            	battleSurface.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));	//kanske ska gras om till dimensioner? eller inte...
            	battleSurface.registerSurfaceObserver(this);
            	battleSurface.setTurnHandler(battleSetup.turnHandler.create(battleSurface));
            	this.thread = new BattleThread(battleModel, this.battleSurfaces);		// create the game loop thread
            	
          //  }
        //});
    	
    	gameWidgets = new RelativeLayout(this);
    	surfaceFrame.addView(gameWidgets);
    	finishTurnButton = new Button(this);
    	finishTurnButton.setWidth(300);
    	finishTurnButton.setText("GO!");
    	gameWidgets.addView(finishTurnButton);
    	
    	
    	
    	//setContentView(gameFrame);
    	
    	
    	//finishTurnButton.setOnClickListener((OnClickListener) this);
    	
    	finishTurnButton.setOnClickListener( 	//En lyssnare som lyssnar p main, allts hela layouten
    			new View.OnClickListener() {
              		@Override public void onClick(View arg0){
              			Log.d(TAG, "finishTurnButton - OnClick() start");
              			battleSurfaces[0].finishTurn(); 
              			Log.d(TAG, "finishTurnButton - OnClick() end");
              		}
              	}
         );

        Log.d(TAG, "View added");
    	
    }
    

	
    public void setPositionFinishTurnButton() {
		//finishTurnButton.setX(50);
		//finishTurnButton.setY(50);
    	//gameWidgets.setRotation(180);
		//finishTurnButton.setRotation(19);
    	//TODO fixa
	}
    
    public void setVisibilityFinishTurnButton(boolean b){
    	Log.d("parent", "Activity setVisibilityFinishTurnButton( " + b + " )");
    	if(b == false){
    		Log.d("parent", "false: gone");
    		finishTurnButton.setVisibility(View.GONE);
    	}
    	else{
    		Log.d("parent", "true, visible");
    		finishTurnButton.setVisibility(View.VISIBLE);
    	}
    }



	@Override
	void setupSurfaces() {
		//nothing needs to be done here in oneScreen
	}
}


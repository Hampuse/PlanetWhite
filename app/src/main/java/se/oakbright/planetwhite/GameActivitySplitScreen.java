package se.oakbright.planetwhite;

import java.util.List;

import se.oakbright.setup.BattleSetup;
import se.oakbright.setup.SimpelW;
import se.oakbright.setup.Team;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class GameActivitySplitScreen extends GameActivity{ 
	//private static int PXL_TO_DST;	//The ratio from the number of pixels on the screen to the number of distances. Determines resolution together with the actual pixel size. Is set automatically.
	//public static final int SIZE_TO_DST = 5000;		//The global coeff from the sizes of objects to the number of distances. 8388 is ok
	//public static final int VELOCITY_TO_DST = 7000;		//The global coeff from the velocities of objects to the number of distances. 8388 is ok
	
	FrameLayout gameFrame;
	RelativeLayout gameWidgets;
	Button finishTurnButton;
    private static final String TAG = GameActivitySplitScreen.class.getSimpleName();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	BattleSetup battleSetup = (BattleSetup) getIntent().getSerializableExtra("battleSetup");		//getParcelableExtra("battleSetup");
    	
    	// Calculate available width and height, and create widthDst and heightDst so that two surfaces fills the space;
    	int availableWidth = super.getAvailableWidth();
    	int availableHeight = super.getAvailableHeight();
       	final int widthDST = (int) (16777216/4);
       	int pxlToDst = 2*widthDST/(availableWidth);
    	final int heightDST = availableHeight*pxlToDst;
    	
    	//int widthDST = 	(int)(16777216/2.5);
    	//int heightDST = (int)(16777216/2.5);

    	battleModel = new BattleModel(this, battleSetup, widthDST, heightDST);
    	this.battleSurfaces = new BattleSurface[2];
    	
    	setContentView(R.layout.activity_battle_split2p);
    	FrameLayout gameFrame1 = (FrameLayout) findViewById(R.id.splitscreen1); //new FrameLayout(this);
    	FrameLayout gameFrame2 = (FrameLayout) findViewById(R.id.splitscreen2); //new FrameLayout(this);
    	
    	
    	//RelativeLayout splitScreens = new RelativeLayout(this);
    	
   
    	
    	//--splitscreen1 --//
    	battleSurfaces[0] = new BattleSurface(this, battleSetup, battleModel, pxlToDst, this.getInchToDst(pxlToDst));//, widthDST, heightDST);
    	gameFrame1.addView(battleSurfaces[0]);
    	battleSurfaces[0].setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
    	battleSurfaces[0].registerSurfaceObserver(this);
    	
    	//--splitscreen2 --//
    	battleSurfaces[1] = new BattleSurface(this, battleSetup, battleModel,  pxlToDst, this.getInchToDst(pxlToDst));
    	gameFrame2.addView(battleSurfaces[1]);
    	battleSurfaces[1].setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
    	battleSurfaces[1].registerSurfaceObserver(this);
    	
    	this.thread = new BattleThread(battleModel, battleSurfaces);		// create the game loop thread
    }

	@Override
	public void notifySurfaceDestroyed(BattleSurface surface) {
		// TODO Auto-generated method stub
		
	}	
	
	@Override
	void setupSurfaces(){	//setup stuff that is setup after battleModel is fully setup.
		List<BattleTeam> playerTeams = battleModel.getPlayerTeams();	
		battleSurfaces[0].setPlayerTeam(playerTeams.get(0));
		battleSurfaces[1].setPlayerTeam(playerTeams.get(1));
	}
}


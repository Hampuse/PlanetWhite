package se.oakbright.planetwhite;

import java.util.ArrayList;
import java.util.LinkedList;

import se.oakbright.battlecontroller.DebugBaController;
import se.oakbright.battlecontroller.TeamAiController;
import se.oakbright.battleobjects.MotherShip;
import se.oakbright.battleobjects.Ship;
import se.oakbright.setup.BattleSetup;
import se.oakbright.setup.CtfSetup;
import se.oakbright.setup.SceneEffectSetupDiagonalStones;
import se.oakbright.setup.Team;
import se.oakbright.setup.TeamSetup;
import se.oakbright.setup.TrackSetupEmptyTrack;
import se.oakbright.setup.TeamColor;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import se.oakbright.setups1.CircleTrack;

public class MainActivity extends Activity {
	private static final String TAG = MainActivity.class.getSimpleName();
	public  final static String SER_KEY = "se.oakbright.planetwhite.ser";	//TODO defineiera denna?
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Button button1 = (Button) findViewById(R.id.redgogreengo);
        button1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startBattle(setupRedGoGreenGo());
			}
		});
        
        final Button button1_1 = (Button) findViewById(R.id.redgogreengobullets);
        button1_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	startBattle(setupRedGoGreenGoBullets());
            }
        });
        
        final Button button1_2 = (Button) findViewById(R.id.redgogreengobullets_splitscreen);
        button1_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	startBattle(setupRedGoGreenGoBulletsSplitScreen());
            }
        });
        
        final Button button3 = (Button) findViewById(R.id.circlesingle);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	startBattle(setupCircleSingle());
            }
        });

        final Button button4 = (Button) findViewById(R.id.circlesingledirect);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	startBattle(setupCircleSingleDirect());
            }
        });
        
        final Button button5 = (Button) findViewById(R.id.flagtrack1);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	startBattle(setupFlagTrack1());	//ModelGeneratorOne.flagTrack1(getApplicationContext()));
            }
        });

		final Button button6 = (Button) findViewById(R.id.debugBattle);
		button6.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startBattle(setupDebugBattle());	//ModelGeneratorOne.flagTrack1(getApplicationContext()));
			}
		});
		/*ArrayList<TeamSetup> teamSetups = new ArrayList<TeamSetup>();
		teamSetups.add(new TeamSetup());
		teamSetups.add(new TeamSetup());
		MissionSetup missionSetup = new CtfSetup();
		TrackSetup trackSetup = new TrackSetup();*/
		
		//BattleSetup battleSetup = new BattleSetup();//missionSetup,teamSetups, trackSetup);
		//Jump to GameActivity	
		//
         
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	void startBattle(BattleSetup battleSetup){
		 Log.d(TAG,"start gameActivity");
		 Intent nextScreen;
		 if(battleSetup.screenType == BattleSetup.ScreenType.SPLITSCREEN2P)
			 nextScreen = new Intent(getApplicationContext(), GameActivitySplitScreen.class); 	    	
		 else
			 nextScreen = new Intent(getApplicationContext(), GameActivityOneScreen.class); 
   
         Bundle mBundle = new Bundle(); 
         mBundle.putSerializable(SER_KEY, battleSetup);
       
         nextScreen.putExtra("battleSetup",battleSetup);	//TODO send the gameSetup with parceable: http://stackoverflow.com/questions/2139134/how-to-send-an-object-from-one-android-activity-to-another-using-intents
         startActivity(nextScreen);
	}
	
	//private void startBattle(BattleModel battleModel){}
	
	private BattleSetup setupRedGoGreenGo(){
		BattleSetup battleSetup = new BattleSetup();
    	/*battleSetup.test = "hejsan";
    	battleSetup.simpelTest.s = "jojojo";
		battleSetup.addTeamSetup(new TeamSetup( new Team("GBG", TeamColor.GREEN, false)));
		battleSetup.addTeamSetup(new TeamSetup(new Team("STHLM", TeamColor.RED, false)));
		
		ArrayList<LinkedList<Ship.Blueprint>> listOfQueues = new ArrayList<LinkedList<Ship.Blueprint>>();
		LinkedList<Ship.Blueprint> shipQ1 = new LinkedList<Ship.Blueprint>();
		shipQ1.add(VasenShip.getBlueprint());
		shipQ1.add(VasenShip.getBlueprint());
		shipQ1.add(VasenShip.getBlueprint());
		shipQ1.add(VasenShip.getBlueprint());
		shipQ1.add(VasenShip.getBlueprint());
		listOfQueues.add(shipQ1);
		LinkedList<Ship.Blueprint> shipQ2 = new LinkedList<Ship.Blueprint>();
		shipQ2.add(CrasherShip.getBlueprint());
		shipQ2.add(CrasherShip.getBlueprint());
		shipQ2.add(CrasherShip.getBlueprint());
		shipQ2.add(CrasherShip.getBlueprint());
		shipQ2.add(CrasherShip.getBlueprint());
		listOfQueues.add(shipQ2);

		MotherShip.Blueprint blueprint = new MotherShip.Blueprint(MotherShip.class);
		blueprint.initListOfQueues(listOfQueues);
		battleSetup.teamSetups.get(0).setMotherShip(blueprint);	
		battleSetup.teamSetups.get(1).setMotherShip(blueprint);
		battleSetup.missionSetup = new CtfSetup();
		battleSetup.trackSetup = new TrackSetupEmptyTrack();
		battleSetup.sceneEffects = new SceneEffectSetupDiagonalStones();
		battleSetup.turnHandler = new TurnHandlerBoth.Blueprint();*/
		return battleSetup;
	}
	
	private BattleSetup setupRedGoGreenGoBullets(){
		BattleSetup battleSetup = setupRedGoGreenGo();
		/*ArrayList<LinkedList<Ship.Blueprint>> listOfQueues = new ArrayList<LinkedList<Ship.Blueprint>>();
		LinkedList<Ship.Blueprint> shipQ1 = new LinkedList<Ship.Blueprint>();
		shipQ1.add(BulletVasen.getBlueprint());
		shipQ1.add(BulletVasen.getBlueprint());
		shipQ1.add(BulletVasen.getBlueprint());
		shipQ1.add(BulletVasen.getBlueprint());
		shipQ1.add(BulletVasen.getBlueprint());
		listOfQueues.add(shipQ1);
		LinkedList<Ship.Blueprint> shipQ2 = new LinkedList<Ship.Blueprint>();
		shipQ2.add(CrasherShip.getBlueprint());
		shipQ2.add(CrasherShip.getBlueprint());
		shipQ2.add(CrasherShip.getBlueprint());
		shipQ2.add(CrasherShip.getBlueprint());
		shipQ2.add(CrasherShip.getBlueprint());
		listOfQueues.add(shipQ2);

		MotherShip.Blueprint blueprint = new MotherShip.Blueprint(MotherShip.class);
		blueprint.initListOfQueues(listOfQueues);
		battleSetup.teamSetups.get(0).setMotherShip(blueprint);	
		battleSetup.teamSetups.get(1).setMotherShip(blueprint);*/
		return battleSetup;
	}

	private BattleSetup setupRedGoGreenGoBulletsSplitScreen(){
		BattleSetup battleSetup = setupPokalenGetFlag();
		battleSetup.screenType = BattleSetup.ScreenType.SPLITSCREEN2P;
		battleSetup.turnHandler = new TurnHandlerSingle.Blueprint();
		return battleSetup;
	}
	private BattleSetup setupPokalenGetFlag(){
		BattleSetup battleSetup = new BattleSetup();
		/*battleSetup.addTeamSetup(new TeamSetup( new Team("GBG", TeamColor.GREEN, false)));
		battleSetup.addTeamSetup(new TeamSetup(new Team("STHLM", TeamColor.RED, false)));
		
		ArrayList<LinkedList<Ship.Blueprint>> listOfQueues = new ArrayList<LinkedList<Ship.Blueprint>>();
		LinkedList<Ship.Blueprint> shipQ1 = new LinkedList<Ship.Blueprint>();
		shipQ1.add(Pokalen.getBlueprint());
		shipQ1.add(Pokalen.getBlueprint());
		shipQ1.add(Pokalen.getBlueprint());
		shipQ1.add(Pokalen.getBlueprint());
		shipQ1.add(Pokalen.getBlueprint());
		shipQ1.add(Pokalen.getBlueprint());
		listOfQueues.add(shipQ1);
		
		LinkedList<Ship.Blueprint> shipQ2 = new LinkedList<Ship.Blueprint>();
		shipQ2.add(Gaffeln.getBlueprint());
		shipQ2.add(Gaffeln.getBlueprint());
		shipQ2.add(Gaffeln.getBlueprint());
		shipQ2.add(Gaffeln.getBlueprint());
		shipQ2.add(Gaffeln.getBlueprint());
		shipQ2.add(Gaffeln.getBlueprint());
		shipQ2.add(Gaffeln.getBlueprint());
		shipQ2.add(Gaffeln.getBlueprint());
		shipQ2.add(Gaffeln.getBlueprint());
		listOfQueues.add(shipQ2);
		
		LinkedList<Ship.Blueprint> shipQ3 = new LinkedList<Ship.Blueprint>();
		shipQ3.add(Bollen.getBlueprint());
		shipQ3.add(Bollen.getBlueprint());
		shipQ3.add(Bollen.getBlueprint());
		shipQ3.add(Bollen.getBlueprint());
		shipQ3.add(Bollen.getBlueprint());
		shipQ3.add(Bollen.getBlueprint());
		shipQ3.add(Bollen.getBlueprint());
		shipQ3.add(Bollen.getBlueprint());
		listOfQueues.add(shipQ3);
		

		MotherShip.Blueprint blueprint = new MotherShip.Blueprint(MotherShip.class);
		blueprint.initListOfQueues(listOfQueues);
		battleSetup.teamSetups.get(0).setMotherShip(blueprint);	
		battleSetup.teamSetups.get(1).setMotherShip(blueprint);
		battleSetup.missionSetup = new CtfSetup();
		battleSetup.trackSetup = new TrackSetupEmptyTrack();
		battleSetup.sceneEffects = new SceneEffectSetupDiagonalStones();*/
		return battleSetup;
	}
	
	private BattleSetup setupCircleSingle(){
		BattleSetup battleSetup = new BattleSetup();
		/*Team teamPlayer = new Team("GBG", TeamColor.GREEN, false);
		TeamSetup teamPlayerSetup = new TeamSetup( teamPlayer);
		battleSetup.addTeamSetup(teamPlayerSetup);
		TeamSetup teamEnemy = new TeamSetup(new Team("THE ENEMY", TeamColor.RED, true));
		teamEnemy.setTeamAiController(TeamAiController.getBlueprint());
		battleSetup.addTeamSetup(teamEnemy);
		
		ArrayList<LinkedList<Ship.Blueprint>> listOfQueues = new ArrayList<LinkedList<Ship.Blueprint>>();
		LinkedList<Ship.Blueprint> shipQ1 = new LinkedList<Ship.Blueprint>();
		shipQ1.add(BulletVasen.getBlueprint());
		shipQ1.add(BulletVasen.getBlueprint());
		shipQ1.add(BulletVasen.getBlueprint());
		shipQ1.add(BulletVasen.getBlueprint());
		shipQ1.add(BulletVasen.getBlueprint());
		listOfQueues.add(shipQ1);
		LinkedList<Ship.Blueprint> shipQ2 = new LinkedList<Ship.Blueprint>();
		shipQ2.add(CrasherShip.getBlueprint());
		shipQ2.add(CrasherShip.getBlueprint());
		shipQ2.add(CrasherShip.getBlueprint());
		shipQ2.add(CrasherShip.getBlueprint());
		shipQ2.add(CrasherShip.getBlueprint());
		listOfQueues.add(shipQ2);

		MotherShip.Blueprint blueprint = new MotherShip.Blueprint(MotherShip.class);
		blueprint.initListOfQueues(listOfQueues);
		battleSetup.teamSetups.get(0).setMotherShip(blueprint);	
		//battleSetup.teamSetups.get(1).setMotherShip(blueprint);
		//battleSetup.missionSetup = new CtfSetup();
		battleSetup.trackSetup = new CircleTrack();
		//battleSetup.sceneEffects = new SceneEffectSetupDiagonalStones();
		battleSetup.turnHandler = new TurnHandlerSingle.Blueprint(teamPlayer);*/
		return battleSetup;
		
	}
	
	private BattleSetup setupCircleSingleDirect(){
		BattleSetup battleSetup = setupCircleSingle();
		TeamSetup teamEnemy = new TeamSetup(new Team("THE ENEMY", TeamColor.RED, true));
		TeamAiController.Blueprint ai = TeamAiController.getBlueprint();
		//ai.set
		//TODOteamEnemy.setTeamAiController(ai);
	//	battleSetup.addTeamSetup(teamEnemy);
		return battleSetup;
	}
	
	private BattleSetup setupFlagTrack1(){
		BattleSetup battleSetup = new BattleSetup();
		/*battleSetup.screenType = BattleSetup.ScreenType.SPLITSCREEN2P;	//splitscreen
		battleSetup.addTeamSetup(new TeamSetup( new Team("GBG", TeamColor.GREEN, false)));
		battleSetup.addTeamSetup(new TeamSetup(new Team("STHLM", TeamColor.RED, false)));
		
		ArrayList<LinkedList<Ship.Blueprint>> listOfQueues = new ArrayList<LinkedList<Ship.Blueprint>>();
		LinkedList<Ship.Blueprint> shipQ1 = new LinkedList<Ship.Blueprint>();
		shipQ1.add(Pokalen.getBlueprint());
		shipQ1.add(Pokalen.getBlueprint());
		shipQ1.add(Pokalen.getBlueprint());
		shipQ1.add(Pokalen.getBlueprint());
		shipQ1.add(Pokalen.getBlueprint());
		shipQ1.add(Pokalen.getBlueprint());
		listOfQueues.add(shipQ1);
		
		LinkedList<Ship.Blueprint> shipQ2 = new LinkedList<Ship.Blueprint>();
		shipQ2.add(Gaffeln.getBlueprint());
		shipQ2.add(Gaffeln.getBlueprint());
		shipQ2.add(Gaffeln.getBlueprint());
		shipQ2.add(Gaffeln.getBlueprint());
		shipQ2.add(Gaffeln.getBlueprint());
		shipQ2.add(Gaffeln.getBlueprint());
		shipQ2.add(Gaffeln.getBlueprint());
		listOfQueues.add(shipQ2);
		
		LinkedList<Ship.Blueprint> shipQ3 = new LinkedList<Ship.Blueprint>();
		shipQ3.add(Bollen.getBlueprint());
		shipQ3.add(Bollen.getBlueprint());
		shipQ3.add(Bollen.getBlueprint());
		shipQ3.add(Bollen.getBlueprint());
		shipQ3.add(Bollen.getBlueprint());
		shipQ3.add(Bollen.getBlueprint());
		shipQ3.add(Bollen.getBlueprint());
		shipQ3.add(Bollen.getBlueprint());
		listOfQueues.add(shipQ3);
		

		MotherShip.Blueprint blueprint = new MotherShip.Blueprint(MotherShip.class);
		blueprint.initListOfQueues(listOfQueues);
		battleSetup.teamSetups.get(0).setMotherShip(blueprint);	
		battleSetup.teamSetups.get(1).setMotherShip(blueprint);
		battleSetup.missionSetup = new CtfSetup();
		battleSetup.trackSetup = new CircleTrack();
		//battleSetup.sceneEffects = new SceneEffectSetupDiagonalStones();*/
		return battleSetup;
	}

	 static BattleSetup setupDebugBattle(){
		BattleSetup battleSetup = new BattleSetup();
		TeamSetup gbgSetup = new TeamSetup( new Team("GBG", TeamColor.GREEN, false));
		battleSetup.addTeamSetup(gbgSetup);
		TeamSetup sthlmSetup = new TeamSetup(new Team("STHLM", TeamColor.RED, false));
		battleSetup.addTeamSetup(sthlmSetup);
/*
		ArrayList<LinkedList<Ship.Blueprint>> listOfQueues = new ArrayList<LinkedList<Ship.Blueprint>>();
		LinkedList<Ship.Blueprint> shipQ1 = new LinkedList<Ship.Blueprint>();
		//shipQ1.add(Pokalen.getBlueprint());
		listOfQueues.add(shipQ1);

		LinkedList<Ship.Blueprint> shipQ2 = new LinkedList<Ship.Blueprint>();
		//shipQ2.add(Gaffeln.getBlueprint());

		listOfQueues.add(shipQ2);

		LinkedList<Ship.Blueprint> shipQ3 = new LinkedList<Ship.Blueprint>();
		//shipQ3.add(Bollen.getBlueprint());
		listOfQueues.add(shipQ3);


		MotherShip.Blueprint blueprint = new MotherShip.Blueprint(MotherShip.class);
		//blueprint.initListOfQueues(listOfQueues);
		//battleSetup.teamSetups.get(0).setMotherShip(blueprint);
		//battleSetup.teamSetups.get(1).setMotherShip(blueprint);
		*/
		//battleSetup.missionSetup = new CtfSetup();
		battleSetup.trackSetup = new TrackSetupEmptyTrack();
		//battleSetup.sceneEffects = new SceneEffectSetupDiagonalStones();

		battleSetup.screenType = BattleSetup.ScreenType.SPLITSCREEN2P;
		battleSetup.turnHandler = new TurnHandlerSingle.Blueprint();
		DebugBaController.Blueprint controller = DebugBaController.getBlueprint();
		gbgSetup.setTeamAiController(controller);

		return battleSetup;
	}
}

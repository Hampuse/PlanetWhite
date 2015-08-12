package se.oakbright.planetwhite;

import android.content.Context;

public class ModelGeneratorOne {
	static BattleModel flagTrack1(Context context){
		BattleModel model = new BattleModel(context, null, 0, 0);	//TODO awaymed null
		
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
		return model;

	}
}

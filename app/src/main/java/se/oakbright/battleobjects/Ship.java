package se.oakbright.battleobjects;


import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import se.oakbright.battlecontroller.Respawnable;
import se.oakbright.battleobjects.statemachine.ShipInterface;
import se.oakbright.modules.activatables.updatables.CollisionModule;
import se.oakbright.modules.activatables.renderables.HighlightRenderer;
import se.oakbright.modules.activatables.updatables.Mover;
import se.oakbright.modules.activatables.updatables.PathCreator;
import se.oakbright.modules.activatables.updatables.PickingUpModule;
import se.oakbright.modules.activatables.selectables.SelectionModule;
import se.oakbright.modules.activatables.updatables.Weapon;
import se.oakbright.battleobjects.statemachine.ShipCommandHandler;
import se.oakbright.icons.IconId;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.BattleTeam;

import android.util.Log;

public class Ship extends BattleObject implements Respawnable, Launchable, ShipInterface {		//IweaponHost, IBattleObject //ShipCommands
private static final String TAG = Ship.class.getSimpleName();
	//protected static final int VELOCITY = 10000;

   // protected PathCreator pathCreator;
    //protected PickingUpModule pickingUpModule;	//TODO SHOULD not need to be here? now many of them are in modules in BattleObject as well.
   // protected Mover mover;
    //private StateMachine< State<ShipCommandHandler> > stateMachine;
	//protected CollisionModule collisionModule;
	//protected SelectionModule selectionModule;
	//protected HighlightRenderer highlight;


	/*public Ship(Builder builder){
		super(builder);
		Log.w("good", "in Ship constructor ,builder");
	}*/

	/*public Ship(BattleModel battleModel, BattleTeam team,IconId iconId){//, BattleObject recentlyEjectedFrom){
		super(battleModel, team, iconId);
		/*this.mover = new Mover(battleModel.trackFrame, Ship.VELOCITY);

		positioner = new Positioner();
		//positioner.setCornerPoint(new CornerPoint());
		//positioner.setPivotPoint(new MiddlePoint());
		positioner.setFrame(battleModel.trackFrame);

		this.selectionModule = new SelectionModule();
		this.highlight = new HighlightRenderer();

		this.shape = new Shape();
		//shape.setIcon(iconId, battleModel);

		this.collisionModule = new CollisionModule();

		//this.health = new Health(100);

		this.pickingUpModule = new PickingUpModule();

		this.pathCreator = new PathCreator();
	}*/

	/*protected void connectModules(){	//Should be called by Blueprint right after calling its constructor.
		/*highlight.setPositioner(this.positioner);
		selectionModule.setHighlightModule(highlight);
		shape.setPositioner(positioner);
		collisionModule.setHealth(health);
		collisionModule.setPositioner(positioner);
		//collisionModule.setShape(shape);
		this.health.setHost(this);

		this.modules.add(pickingUpModule);
		this.modules.add(pathCreator);*/
	//}

	public void commandLaunch(){
		ShipCommandHandler commandHandler = currentStateCommandHandler();
		commandHandler.commandLaunch();
	}

	public void commandGetReadyToLaunch(){
		ShipCommandHandler commandHandler = currentStateCommandHandler();
		commandHandler.commandGetReadyToLaunch();
	}


	//TODO
	/*boolean invitationToLand(LandingPad landingPad) {   //TODO ska den vara här?
		if (this.pickingUpModule.hasPickUpObject()) {
			landingPad.receivePickUpObject(this.pickingUpModule.retrievePickUpObject());
		}
        /*if (this.pickedUpObject != null){
            Boolean accepted = landingPad.receivePickUpObject(this.pickedUpObject);
            if(accepted){
                this.pickedUpObject = null;
            }
        }*/
		/*this.deactivate();
		return true;
	}*/


	@Override
	public boolean isLaunched() {
		ShipCommandHandler commandHandler = currentStateCommandHandler();
		return commandHandler.isLaunched();
	}

	@Override
	public void respawn(int x, int y) {
		//TODO
	}

	@Override
	public void respawn(int x, int y, int direction) {
		//TODO
	}

	public static class Blueprint implements Serializable{
		protected Class<? extends Ship> objectClass;
		public Blueprint(Class<? extends Ship> objectClass){
			this.objectClass = objectClass;
		}

		public Ship create(	BattleModel battleModel, BattleTeam team){
			Ship ship;
			try {
				Constructor<? extends Ship> constructor = this.objectClass.getConstructor(new Class[]{BattleModel.class, BattleTeam.class});
				try {
					ship = constructor.newInstance(battleModel, team);
					//ship.connectModules();
					return ship;
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
			Log.e("TAG","unable to create object of class, returning null. Possibly the class is not subclass of Ship, or it's an abstract class ");
			return null;
		}
	}

	public static class Builder extends BattleObject.Builder<Ship,ShipInterface>{
		//private List<Builder> moduleBuilders;
		//public ShipStateMachineBuilder stateMachineBuilder;
		//public Builder(){
		//	super();
		//}

		//public Ship build(){
		//	super.stateMachineBuilder = this.stateMachineBuilder;
			//modulesToBuild.add
			//super.onBuild();
			//assertCorrectStateToBuild();
		//	Ship ship = new Ship(this);
			//ship.stateMachine = stateMachine;
		//	return ship;
		//}

		//private void assertCorrectStateToBuild(){

		//}

		@Override
		protected Ship getType() {
			return new Ship();
		}

		@Override
		protected void beforeBuildNew() {
			//stateMachine?
		}
	}

}


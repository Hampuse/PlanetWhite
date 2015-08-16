package se.oakbright.planetwhite;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import android.content.Context;
import android.graphics.Region;
import android.util.Log;

import se.oakbright.Frames.Frame;
import se.oakbright.battlecontroller.BaController;
import se.oakbright.battlecontroller.RespawnHandler;
import se.oakbright.battleobjects.BattleObject;
import se.oakbright.modules.helpers.CollisionChecker;
import se.oakbright.modules.activatables.selectables.Selectable;
import se.oakbright.modules.activatables.updatables.CollisionModule;
import se.oakbright.modules.activatables.updatables.Updatable;
import se.oakbright.modules.activatables.renderables.Renderable;
import se.oakbright.icons.IconFactory;
import se.oakbright.icons.IconId;
import se.oakbright.icons.InIcon;
import se.oakbright.setup.BattleSetup;


public class BattleModel {
	private static final String TAG = BattleModel.class.getSimpleName();
	private final float NORMAL_GLOBAL_VELOCITY = (float) 2;
	private List<BattleTeam> teams = new ArrayList<BattleTeam>();
	private List<BaController> baControllers = new ArrayList<BaController>();
	//TODO Set<Selectable> shipsA = new HashSet<Selectable>();
	Set<Selectable> selectables = new HashSet<Selectable>();
	private Set<Updatable> updatables = new HashSet<Updatable>();
	Set<Renderable> renderablesBackgroundLayer = new HashSet<Renderable>();	//background layer, motherSHips etc
	Set<Renderable> renderablesMiddleLayer = new HashSet<Renderable>();	//middel layer, ships etc
	Set<Renderable> renderablesForegroundLayer = new HashSet<Renderable>();	//foreground layer, projectiles explosions etc
	List<CollisionModule> collidables = new ArrayList<CollisionModule>();
	private LinkedList<AppearStatus> appearChanges = new LinkedList<AppearStatus>();
	private Map<Updatable,Boolean> updatablesChanges = new IdentityHashMap<Updatable, Boolean>();

	private boolean turnBasedGame;
	private BattleClock battleClock;

	private boolean isSetup = false;
	private IconFactory iconFactory;
	private BattleSetup battleSetup;
	private RespawnHandler respawnHandler = RespawnHandler.getInstance();
	
	public final Frame trackFrame;
	public final int trackWidth;
	public final int trackHeight;
	
	public BattleModel(Context context, BattleSetup battleSetup, int trackWidth, int trackHeight){
		//globalVelocity = NORMAL_GLOBAL_VELOCITY;
		//turnHandler = new TurnHandlerBoth(this,3000, 10000);//TODO hjhjhjhkh
		this.turnBasedGame = true; //TODO kunna välja? eller göra om med inheritance kanske?
		battleClock = new BattleClock();
		this.iconFactory = new IconFactory(context.getResources());
		this.battleSetup = battleSetup;
		this.trackFrame = new Frame(0, 0, trackWidth, trackHeight);
		this.trackWidth = trackWidth;
		this.trackHeight = trackHeight;
		ServiceProvider.reset();
		ServiceProvider.setBattleModel(this);
		ServiceProvider.setBattleClock(battleClock);
	}
	
	///////-- SETUP -- ////////// //let the battleSetup set up the battelSurface
	public void setup(){
		if(this.isSetup != true){
			battleSetup.setup(this);
			this.isSetup = true;	
		}else
			Log.w(TAG, "a try to call was made to setup(), but battleSurface is already setup (isSetup == true)");
	}
	
	public void addController(BaController controller){
		if(this.isSetup == true)
			Log.e(TAG,"controller added during a non-setup phase. Should this be permitted? Ok right now");
		this.baControllers.add(controller);
	}
	
	
	
	public InIcon getIcon(IconId iconId){
		return iconId.getIcon(this.iconFactory);//, iconId.getWidthDst()/GameActivity.PXL_TO_DST, iconId.getHeightDst()/GameActivity.PXL_TO_DST);	//TODO should be called from surface or somehting maybe? to enable different sized icons
	}
	
	public void recievePickUpContent(PickUpContent pickUpContent, BattleObject pickedUpBy) {
		pickUpContent.execute(pickedUpBy);
	}
	/**
	 * This is the game update method. It iterates through all the objects
	 * and calls their update method if they have one or calls specific
	 * engine's update method.
	 */
	 public void update() {
		 for(BaController controller: baControllers){	//TODO put in updatable list instead?
			 controller.update();
		 }
		 
		 battleClock.update();
		 if(!battleClock.isHalted()){

			for(Updatable updatable: this.updatables){
				updatable.update();
			}
			
			Iterator<Entry<Updatable, Boolean>> it = this.updatablesChanges.entrySet().iterator();
			 while (it.hasNext()) {
				 Entry<Updatable, Boolean> entry = it.next();
				 if(entry.getValue()){		//If the bool is true, add the object.
					 this.updatables.add(entry.getKey());
				 } else{		//If the bool is false, remove the object.
					this.updatables.remove(entry.getKey()); 
				 }
				 it.remove();
			 }
			
			 //-- CHECK FOR COLLISIONS: --//
			CollisionChecker.checkForCollisionsAmong(collidables);	//maybe have different lists for semicollidables(kan endast krocka med andra men inte inom samma grupp)
					
			//-- update respawnHandler --//
			this.respawnHandler.update();
		 }
	 }

	
	public BattleObject findShipInside(Region region, BattleTeam exceptTeam) {
		// TODO BattleObject obj = FindObject.findObjectInside(region, new ArrayList<BattleObject>(shipsA), exceptTeam);
		//TODO check so the obj is "out there" and not in the mothership;
		//TODO return obj;
		Log.e("BattleModel", "in findShipInside, method not implemented");
		return null;
	}
	
	void haltBattleClock(){
		this.battleClock.halt();
	}
	
	void resumeBattleClock(){
		this.battleClock.resume();
	}
	
	public void addUpdatable(Updatable obj){
		this.updatablesChanges.put(obj, true);
	}
	
	public void removeUpdatable(Updatable obj){
		this.updatablesChanges.put(obj,false);
	}
	
	public void addCollidable(CollisionModule obj){
		this.collidables.add(obj);
	}

	public void removeCollidable(BattleObject obj){
		this.collidables.remove(obj);
	}
	
	public void addRenderableBackgroundLayer(Renderable obj){
		this.renderablesBackgroundLayer.add(obj);
	}
	
	public void removeRenderableBackgroundLayer(Renderable obj){
		this.renderablesBackgroundLayer.remove(obj);
	}
	
	public void addRenderableMiddleLayer(Renderable obj){
		this.renderablesMiddleLayer.add(obj);
	}
	
	public void removeRenderableMiddleLayer(Renderable obj){
		this.renderablesMiddleLayer.remove(obj);
	}
	
	public void addRenderableForegroundLayer(Renderable obj){
		this.renderablesForegroundLayer.add(obj);
	}

	public void removeRenderableForegroundLayer(Renderable obj){
		this.renderablesForegroundLayer.remove(obj);
	}

	public void addTeam(BattleTeam team){
		this.teams.add(team);
	}
	
	public ArrayList<BattleTeam> getPlayerTeams() {
		ArrayList<BattleTeam> playerTeams = new ArrayList<BattleTeam>();
		for(BattleTeam team: teams){
			if (!team.isAiControlled)
				playerTeams.add(team);
		}
		return playerTeams;
		
	}
}

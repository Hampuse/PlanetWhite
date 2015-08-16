package se.oakbright.battleobjects.projectile;

import se.oakbright.modules.activatables.updatables.Mover;
import se.oakbright.icons.IconId;
import se.oakbright.modules.helpers.Health;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.BattleTeam;

/*public abstract class Projectile extends BattleObject {
	static final int VELOCITY = 3600000;
	private static final String TAG = Projectile.class.getSimpleName();
	private Mover mover;
	private BattleObject recentlyEjectedFrom;	//göra om med state istället? 
	private static enum Status {INACTIVE, TRANS_LAUNCHED, LAUNCHED, EXPLODE}
	private Projectile.Status status;

	Health health;

	/*public Projectile(ProjectileResource r){
		this.mover = r.mover;
		this.stateMachine = r.stateMachine;

	}*/

	/*public static ProjectileResource getStandardResource(){
		ProjectileResource
	}*/
/*
	public Projectile(BattleModel battleModel, BattleTeam team, BattleObject recentlyEjectedFrom, IconId icon) {
		super(); //TODO :...battleModel, team, icon);
		//setBitmap(battleSurface.BM_MISSILE);
		//TODO this.enlargeMovingFrame();	//Objects that should disseapear outside the screen before handling their handleOnBorder() should have an enlarged movingFrame.
		this.health = new Health(1);
		this.recentlyEjectedFrom = recentlyEjectedFrom;
		//TODO this.mover = new Mover(battleModel.trackFrame,  Projectile.VELOCITY);
		this.battleModel.addUpdatable(this.mover);
		this.status = Status.TRANS_LAUNCHED;
		
	}
	/* FÖRENKLAT JUST NU
	@Override
	public void update(){ 
		//super.update();
		switch(this.status){
		case INACTIVE:	//NOThing NOW
			break;
			
		case TRANS_LAUNCHED:
			this.battleModel.addCollidable(this);
			this.battleModel.addRenderableLayer2(this);
			//this.mover.setVelocity(10);
			this.status = Status.LAUNCHED;
			this.setFlagActive(true);
			break;
		case LAUNCHED:
			this.mover.update();
			break;
		case EXPLODE:
			this.battleModel.removeCollidable(this);
			this.battleModel.removeRenderableLayer2(this);
			this.battleModel.removeUpdatable(this.mover);
			this.status = Status.INACTIVE; 
			this.setFlagActive(false);
			break;
		}	
	}

	@Override
	public void activate() {
		this.battleModel.addUpdatable(this.mover);
		this.status = Status.TRANS_LAUNCHED;
	}

	@Override
	public void deactivate() {
		this.status = Status.EXPLODE;	//TODO JUST NU SKIT SAMMA om det är inactive eller explode, men kanske inte sen?
	}
	
	/*@Override
	public boolean isActive(){
		if(this.status != Status.INACTIVE)
			return true;
		else
			return false;
	}*/
	/*
	@Override
	boolean isAbleToCollideWith(BattleObject battleObject){
		if(this.isAbleToCollide != true)
			return false;
		if(this.recentlyEjectedFrom != null)
			if(this.recentlyEjectedFrom.equals(battleObject))
				return false;
		return true;
	}
	

	
	@Override
	void collideWith(BattleObject battleObject) {
		this.deactivate();	//should be explode or something
	}
	
	@Override
	public void setPositioner(int x, int y){
		this.mover.setPositioner(x, y);
		this.mover.update();
	}

	@Override 
	public void setDirection(int direction){
		this.mover.setDirection(direction);
		super.setDirection(direction);
	}
	
	/////////////// inMover Functions: 	//////////////////////
	public void go() {
		this.mover.go();
	}

	public void halt() {
		this.mover.go();
	}
	
	public void renderPath(Canvas canvas, BattleSurface battleSurface){
		mover.renderPath(canvas, battleSurface);
	}

	public void checkForBorders(){
		mover.checkForBorders();
	}

	public void setTrackObject(BattleObject trackObject) {
		Log.d(TAG,"trackObject( "+ trackObject +" )");
		mover.setTrackObject(trackObject);
	}

	/*public ConcurrentLinkedQueue<Coord> getPath(){
	return mover.getPath();
	}*/

	/*public void setPath(ConcurrentLinkedQueue<Coord> path){
	mover.setPath(path);
	}*/

	/* FÖRENKLAT
	public void setDirection(int x, int y){
		mover.setDirection(x,y);
	}
	
	
	public void setDirection(int xFrom, int yFrom, int xTo, int yTo){
		mover.setDirection(xFrom, yFrom, xTo, yTo);
	}
	*/

//	public void setSpeedLevel(int speedLevel) {
//		this.mover.setSpeedLevel(speedLevel);
//	}
//}

package se.oakbright.battleobjects;

import se.oakbright.battlecontroller.Respawnable;
import se.oakbright.modules.activatables.updatables.Mover;
import se.oakbright.icons.IconId;
import se.oakbright.icons.IconIdFactory;
import se.oakbright.modules.helpers.Direction;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.BattleTeam;
import se.oakbright.planetwhite.GameActivity;
import se.oakbright.planetwhite.R;

public class Stone extends BattleObject implements Respawnable{
	final static int VELOCITY = (int)(16000*GameActivity.VELOCITY_ADJUST);//(float) 0.01;
	private static final String TAG = Stone.class.getSimpleName();
	private final static IconId iconId = IconIdFactory.valueOf(R.drawable.stone2, 500000,500000);
	private Mover mover;
	private static enum Status{ENTER_INACTIVE, INACTIVE, ENTER_OUT_THERE, OUT_THERE, OUTSIDE_SCREEN};
	Stone.Status status = Status.INACTIVE;
	private Direction direction;

	public Stone(BattleModel battleModel, BattleTeam team) {
		super(); //TODO ...battleModel, team, Stone.iconId);
		//TODO this.mover = new Mover(battleModel.trackFrame, Stone.VELOCITY);
		//this.mover.setVelocity((float) 0.3);
		//TODO this.battleModel.addUpdatable(this.mover);
		//this.status = Status.ENTER_OUT_THERE;
	}

	/* FÖRENKLAT JUST NU
	@Override
	public void update(){
		//super.update();
		
		switch(this.status){
		case ENTER_OUT_THERE:
			this.battleModel.addCollidable(this);
			this.battleModel.addRenderableLayer1(this);
			this.status = Status.OUT_THERE;
			this.setFlagActive(true);
			break;
		case OUT_THERE:
			this.mover.update();
			break;
		case ENTER_INACTIVE:
			this.battleModel.removeUpdatable(this.mover);
			this.battleModel.removeCollidable(this);
			this.battleModel.removeRenderableLayer1(this);
			this.status = Status.INACTIVE;
			this.setFlagActive(false);
			Log.d("cstone", "efter setFlag active false in enter inactive");
			break;
		case INACTIVE:
			break;
		case OUTSIDE_SCREEN:	//NOT USED YET
			break;
		}
	}
	@Override
	public void activate() {
		this.battleModel.addUpdatable(this.mover);
		this.status = Status.ENTER_OUT_THERE;
		//this.battleSurface.addCollidable(this);
		//this.battleSurface.addRenderableLayer1(this);
	}

	@Override
	public void deactivate() {
		this.status = Status.ENTER_INACTIVE;
	}
	
	@Override
	public boolean isActive() {
		if (this.status != Stone.Status.INACTIVE)
			return true;
		else
			return false;
	}
	
	@Override
	void collideWith(BattleObject battleObject) {
		//TODO mer intelligent krock, tills vidare ingenting. hård som berget
	}
	
	@Override
	public void setPositioner(int x, int y){
		this.mover.setPositioner(x, y);
		super.setPositioner(x, y);
	}
	
	@Override
	public void setDirection(int direction){
		this.mover.setDirection(direction);
		super.setDirection(direction);
	}
	
/////////////// inMover Functions: 	//////////////////////
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


	public void setDirection(int x, int y){
		mover.setDirection(x,y);
	}
	
	public void setDirection(int xFrom, int yFrom, int xTo, int yTo){
		mover.setDirection(xFrom, yFrom, xTo, yTo);
	}

//	public void setSpeedLevel(int speedLevel) {
//		this.mover.setSpeedLevel(speedLevel);
//	}

	@Override
	boolean isAbleToCollideWith(BattleObject battleObject) {
		return this.isAbleToCollide;
	}



	@Override
	protected int onCollisionGiveDamage() {
		return BattleObject.GIVE_DAMAGE_MUCH;
	}

	/*@Override
	public void reset()) {	
		if(this.status != Status.INACTIVE)
			this.deactivate();
		this.mover.setDirection(direction);
		this.mover.setPositioner(x, y);
		//this.clearIAObservers();	//can't clear unless force sceneBaControllerState to register again when reincarnate.
	}*/

	
	@Override
	public void respawn(int x, int y) {
			this.positioner.setPosition(x, y);
			this.mover.update();
			this.activate();
	}
	
	@Override
	public void respawn(int x, int y, int direction) {
		this.direction.setDirectionDegrees(direction);
		this.respawn(x, y);
	}

	@Override
	public int getBoundingSquareSide() {
		return shape.getBoundingSquareSide();
	}

	@Override
	public int getMaxBoundingRadii() {
		return shape.getMaxBoundingRadii();
	}

	public void go() {
		this.mover.go();
	}

	public void halt() {
		this.mover.go();
	}

}

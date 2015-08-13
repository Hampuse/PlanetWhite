package se.oakbright.modules.activatables.updatables;

import android.util.Log;

import se.oakbright.Buildable;
import se.oakbright.battleobjects.BattleObject;
import se.oakbright.modules.activatables.renderables.DebugInternalPointRenderer;
import se.oakbright.modules.helpers.Direction;
import se.oakbright.battleobjects.IsActiveObservable;
import se.oakbright.battleobjects.IsActiveObserver;
import se.oakbright.calculation.DirectionCalculation;
import se.oakbright.modules.internalpoints.Aim;
import se.oakbright.modules.internalpoints.InternalPoint;
import se.oakbright.resources.TypeResource;

public abstract class Weapon extends UpdatableModule implements IsActiveObserver{
	private static final String TAG = Weapon.class.getSimpleName();
	private final int AIM_SCOPE; //degrees. how far on each side along the ships direction the weapon is able to fire.
	private final int RELOAD_TIME; //ms

	private Aim aim;
	private long timeAtLastReload;
	protected Direction direction;
	protected InternalPoint mouthPosition;

	/*public Weapon(Weapon.Resource r){
	asdfa
	}*/

	public Weapon( int aimScope, int reloadTime){
		timeAtLastReload = 0;
		this.aim = null;
		this.AIM_SCOPE = aimScope;
		this.RELOAD_TIME = reloadTime;
	}
	
	public void tryFire(){
		if(this.aim != null){
			if(tryReload()){
				Log.d("shoot", "reaload succesful");
				if(inAimScope()){
					Log.d("shoot", "aim in scope");
					fireAt(aim.x(), aim.y());
				}
			}
		}
	}
	
	private boolean inAimScope() {
		if(this.AIM_SCOPE >= 180)	//quick check if the aimScope is all the way, just return true.
			return true;
		int directionAim = (int) DirectionCalculation.getDirectionDegFromTo(mouthPosition.x(), mouthPosition.y(), aim.x(), aim.y());
		int directionShip =  direction.degrees();
		int deltaDirection = Math.abs(directionShip - directionAim);
		while(deltaDirection > 360){
			deltaDirection = deltaDirection - 360;
		}
		if (deltaDirection < AIM_SCOPE){
			return true;
		}
		else
		return false;
	}
	
	protected abstract void fireAt(int xAim, int yAim);
	
	private boolean tryReload(){
		if(System.currentTimeMillis() - this.timeAtLastReload > RELOAD_TIME){	//TODO, this does not take the battleCLock time in to concern
			this.timeAtLastReload = System.currentTimeMillis();
			return true;
		}
		else
			return false;
	}
	
	public void setAim(BattleObject targetObject, int xOffsetAimFromTargetObject, int yOffsetAimFromTargetObject){
		if(this.aim == null || this.aim.targetObject == null)	//If the former aim did not contain a targetObject, just register at the new one
			targetObject.registerIAObserver(this);
		else if(this.aim.targetObject != null && this.aim.targetObject != targetObject){		//If the former object
			this.aim.targetObject.unRegisterIAObserver(this);
			targetObject.registerIAObserver(this);
		}	
		this.aim = new Aim( targetObject,  xOffsetAimFromTargetObject,  yOffsetAimFromTargetObject);
		targetObject.registerIAObserver(this);
	}

	void setAim(int x, int y){
		if(this.aim != null && this.aim.targetObject != null)
			this.aim.targetObject.unRegisterIAObserver(this);
		this.aim = new Aim(x, y);
	}
	
	public void deleteAim(){
		if( this.aim != null && this.aim.targetObject != null)
			this.aim.targetObject.unRegisterIAObserver(this);
		this.aim = null;
	}

	@Override
	public void notifyIsActiveChangeIn(IsActiveObservable subject) {
		if( !subject.isActive() ){		//If an observed object get inactivated
			if(aim.targetObjectEquals(subject)){	// If the aim has that object as a target, delete the aim.
				this.aim = null;
			}
		}
	}

	public boolean hasAim() {
		if(this.aim != null)
			return true;
		else
			return false;
	}

	@Override
	public void update() {
		this.setAim(700000,500000);	//TODO Away just for test
		tryFire();
	}

	public abstract static class Builder extends Buildable<Weapon> {
		//public int AIM_SCOPE; //degrees. how far on each side along the ships direction the weapon is able to fire.
		//public int RELOAD_TIME; //ms
		public Buildable<? extends InternalPoint> mouthPositionBuilder;
		public Buildable<Direction> directionBuilder;

		@Override
		protected Weapon buildNew() {
			Weapon weapon = getType(); //new Weapon(AIM_SCOPE, RELOAD_TIME);
			weapon.direction = directionBuilder.getBuilt();
			weapon.mouthPosition = mouthPositionBuilder.getBuilt();
			return weapon;
		}

		/*private Direction setupDirection(){
			Direction.Builder builder = new Direction.Builder();
			builder.positionBuilder = mouthPositionBuilder;
			return builder.getBuilt();
		}*/

		protected abstract Weapon getType();
	}

	public interface Resource {
		void getTest();
	}

	/* From Ship:
	//////////// Weapon methods: ///////////////////
	protected void tryFireWeapon(){
		if(this.weapon != null)
			this.weapon.tryFire();
	}

	public void setAim(int x, int y){
		if(this.weapon != null)
			this.weapon.setAim(x,y);
	}

	public void setAim(BattleObject target, int offsetX, int offsetY){
		if(this.weapon != null)
			this.weapon.setAim(target, offsetX, offsetY);
	}

	public void deleteAim(){
		if(this.weapon != null)
			this.weapon.deleteAim();
	}

	 */
}

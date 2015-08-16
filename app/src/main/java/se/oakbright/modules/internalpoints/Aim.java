package se.oakbright.modules.internalpoints;

import se.oakbright.battleobjects.BattleObject;

/**
 * Created by hampuse on 2015-08-06.
 */
public class Aim implements InternalPoint {

    private final int x;
    private final int y;
    public final BattleObject targetObject; //TODO SHOULD NOT BE public actually
    private final int xOffsetFromTargetObject;
    private final int yOffsetFromTargetObject;

    public Aim(int x, int y){
        this.x = x;
        this.y = y;
        this.targetObject = null;
        this.xOffsetFromTargetObject = 0;
        this.yOffsetFromTargetObject = 0;
    }
    public Aim(BattleObject targetObject, int xOffsetFromTargetObject, int yOffsetFromTargetObject){
        this.x = 0;
        this.y = 0;
        this.targetObject = targetObject;
        this.xOffsetFromTargetObject = xOffsetFromTargetObject;
        this.yOffsetFromTargetObject = yOffsetFromTargetObject;
    }
	/*	public void notifyIsDeactivated(IsActiveObservable subject) {
			if( !subject.isActive() && this.targetObject == subject ){	//If the targetObject get inactivated, delete this aim
				this = null;
			}
		}*/

    public boolean targetObjectEquals(Object object){
        if( targetObject != null){
            if( targetObject == object ){
                return true;
            }
        }
        return false; //if targetObject is null or not equal to battleObject.
    }

    @Override
    public int x(){
        if( targetObject != null){
            return ( targetObject.getX() + xOffsetFromTargetObject );
        }else{
            return this.x;
        }
    }

    @Override
    public int y(){
        if( targetObject != null ){
            return (targetObject.getY() + yOffsetFromTargetObject );
        }else{
            return this.y;
        }
    }
}



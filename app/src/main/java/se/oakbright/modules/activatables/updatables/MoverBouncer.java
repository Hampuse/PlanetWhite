package se.oakbright.modules.activatables.updatables;

import se.oakbright.Frames.Frame;
import se.oakbright.battleobjects.BattleObject;
import se.oakbright.modules.helpers.Positioner;
import se.oakbright.resource.Resource;

/**
 * ObMoverBouncer extends ObMover to "bounce" (change direction) on borders of the moving frame, so that they don't go outside the field.
 * @author hampuse
 *
 */
public class MoverBouncer extends Mover {
private static final String TAG = MoverBouncer.class.getSimpleName();

    public MoverBouncer(Resource r){
        super(r);
    }

   //TODO
   /* public MoverBouncer(Positioner positionerPart, BattleObject host, Frame movingFrame, int velocity) {
		super(movingFrame, velocity);
    }*/
	
    @Override
	void handleOnBorder(float normalDeg) {
		//TODO this.setDirection((int) (normalDeg*2 - 180 - this.getDirectionDeg()) );	//This formula make the mover "bounce" on the border
		//TODO this.resetPath();
		//TODO this.resetNextCoord();
	}


}

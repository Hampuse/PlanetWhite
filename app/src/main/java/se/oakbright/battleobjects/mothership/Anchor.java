package se.oakbright.battleobjects.mothership;

/**
 * Anchor works as a pivot point for the host BattleObject.
 * It is attached to both the host object, and the world coordinate system, with points and direction.
 * Hence specifying the positioner and rotation of the host object, which is retrieved by getters.
 * 
 * @author hampuse
 *
 */
class Anchor {
	private final int xLocal; 	//the local point on the host object where the anchor is attached, to be aligned with the world pivot point.
	private final int yLocal;	
	private final int xWorld;	//the world pivot point, where the anchor is placed. Which the host object may be able to pivot around.
	private final int yWorld;
	private final int directionLocal; 	// The "direction arrow" on the host object to be aligned with directionWorld 
	private final int directionWorld; // the direction in the world coordinate system.
	
	private Anchor(int xWorld, int yWorld, int directionWorld, int xLocal, int yLocal, int directionLocal){ //TODO, create possibility to specify where the local anchor point should be. Now it's assumed to be at (0,0)
		this.xWorld = xWorld;
		this.yWorld = yWorld;
		this.directionWorld = directionWorld;
		this.xLocal = xLocal;
		this.yLocal = yLocal;
		this.directionLocal = directionLocal;
	}
	
	public Anchor(int xWorld, int yWorld, int directionWorld){
		this(xWorld, yWorld, directionWorld, 0, 0, 135);
	}


	int getXworld() {
		return xWorld;
	}

	int getYworld() {
		return yWorld;
	}

	float getRotationAngle() {	//Returns how much the host object has to turn to get the same world angle as the anchor.
		return directionWorld - directionLocal;
	}
	
}

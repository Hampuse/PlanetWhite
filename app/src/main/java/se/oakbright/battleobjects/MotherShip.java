package se.oakbright.battleobjects;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import se.oakbright.Frames.Frame;
import se.oakbright.battleobjects.pickupobject.PickUpFlag;
import se.oakbright.battleobjects.pickupobject.PickUpObject;
import se.oakbright.calculation.DirectionCalculation;
import se.oakbright.icons.IconId;
import se.oakbright.icons.IconIdFactory;
import se.oakbright.modules.helpers.OrientationMatrix;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.BattleTeam;
import se.oakbright.planetwhite.GameActivity;
import se.oakbright.planetwhite.R;
import android.util.Log;

public class MotherShip extends BattleObject {	//TODO SIMPLIFY AND ADD MODULES INSTEAD
	private static final String TAG = MotherShip.class.getSimpleName();
	public static final int NUMBER_OF_LAUNCHPADS = 2;
	public static final int WIDTH = (int) (750000*GameActivity.SIZE_ADJUST);
	public static final int HEIGHT = (int) (750000*GameActivity.SIZE_ADJUST);
	public static final IconId ICON_MOTHERSHIP1 = IconIdFactory.valueOf(R.drawable.mothership1, WIDTH, HEIGHT);
	public static final int SHIPLAUNCHER_POINT1_X = 550000;
	public static final int SHIPLAUNCHER_POINT1_Y = 135000;
	public static final int SHIPLAUNCHER_POINT2_X = 80000;
	public static final int SHIPLAUNCHER_POINT2_Y = 500000;
	public static final int SHIPLAUNCHER_POINT3_X = 250000;
	public static final int SHIPLAUNCHER_POINT3_Y = 450000;
	public static final int LANDINGPAD_POINT_X = 50000;
	public static final int LANDINGPAD_POINT_Y = 50000;
	public static final IconId ICON_LANDINGPAD = IconIdFactory.valueOfInvisibleIcon(50000,50000);
	private Anchor anchor;
	private final ArrayList<ShipLauncher> shipLaunchers = new ArrayList<ShipLauncher>();
	private final LandingPad landingPad;
	private final ArrayList<BattleObject> startingObjects = new ArrayList<BattleObject>();
	private enum Status{ENTER_INACTIVE, INACTIVE, ENTER_ACTIVE, ACTIVE};
	private MotherShip.Status status = Status.INACTIVE;
	private OrientationMatrix orientationMatrix;

	
	public MotherShip(BattleModel battleModel, int x, int y, BattleTeam team, int direction, ArrayList<LinkedList<Ship.Blueprint>> listOfQueues) {
		//TODO super(battleModel, team, MotherShip.ICON_MOTHERSHIP1);
		super();
		//-- anchor and middle point --//
		this.anchor = new Anchor(x, y, direction);
		float mX = shape.getWidthDst() / 2;
		float mY = shape.getHeightDst() / 2;
		float points[] = new float[]{mX, mY};
		points = orientationMatrix.mapPointsAccordingToOrientationMatrix(points);
		mX = points[0];
		mY = points[1];
		
		//--ShipQueues --//	
		LinkedList<Ship> shipQ1;
		LinkedList<Ship> shipQ2;
		LinkedList<Ship> shipQ3;
		//Queue1
		if(listOfQueues != null && listOfQueues.size()>=1 && listOfQueues.get(0) != null){
			shipQ1 = MotherShip.createShipQFromBlueprints(listOfQueues.get(0), battleModel, this.battleModel.trackFrame, team,  this);	//TODO this är väl null nu i constructorn? 	
		} else{
			shipQ1 = new LinkedList<Ship>();
		}
		//Queue2
		if(listOfQueues != null && listOfQueues.size()>=2 && listOfQueues.get(1) != null){
			shipQ2 = MotherShip.createShipQFromBlueprints(listOfQueues.get(1), battleModel, this.battleModel.trackFrame, team,  this);	//TODO this är väl null nu i constructorn? 	
		} else{
			shipQ2 = new LinkedList<Ship>();
		}
		//Queue3
		if(listOfQueues != null && listOfQueues.size()>=3 && listOfQueues.get(2) != null){
			shipQ3 = MotherShip.createShipQFromBlueprints(listOfQueues.get(2), battleModel, this.battleModel.trackFrame, team,  this);	//TODO this är väl null nu i constructorn? 	
		} else{
			shipQ3 = new LinkedList<Ship>();
		}
		
		//-- shipLauncher1 --//
		//int[] shipLauncherPoint1 = MotherShip.MOTHERSHIP1_SHIPLAUNCHER_POINT1;
		int xTemp = (int) this.orientationMatrix.mapPointsGetX(SHIPLAUNCHER_POINT1_X, SHIPLAUNCHER_POINT1_Y);
		int yTemp = (int) this.orientationMatrix.mapPointsGetY(SHIPLAUNCHER_POINT1_X, SHIPLAUNCHER_POINT1_Y);
		int directionTemp = (int) DirectionCalculation.getDirectionDegFromTo(this.anchor.getXworld(), this.anchor.getYworld(), xTemp, yTemp);

		ShipLauncher shipLauncher1 = new ShipLauncher(xTemp, yTemp, directionTemp, this, shipQ1);
		this.shipLaunchers.add(shipLauncher1);
		
		//-- shipLauncher2 --//
		//MotherShip.PointData shipLauncherPoint2 = MotherShip.MOTHERSHIP1_SHIPLAUNCHER_POINT2;
		//xTemp = (int) shipLauncherPoint2.getX(this);
		//yTemp = (int) shipLauncherPoint2.getY(this);
		xTemp = (int) this.orientationMatrix.mapPointsGetX(SHIPLAUNCHER_POINT2_X, SHIPLAUNCHER_POINT2_Y);
		yTemp = (int) this.orientationMatrix.mapPointsGetY(SHIPLAUNCHER_POINT2_X, SHIPLAUNCHER_POINT2_Y);
		directionTemp = (int) DirectionCalculation.getDirectionDegFromTo(this.anchor.getXworld(), this.anchor.getYworld(), xTemp, yTemp);
		ShipLauncher shipLauncher2 = new ShipLauncher(xTemp, yTemp, directionTemp, this, shipQ2);
		this.shipLaunchers.add(shipLauncher2);
		
		//--shipLauncher3 --//
		xTemp = (int) this.orientationMatrix.mapPointsGetX(SHIPLAUNCHER_POINT3_X, SHIPLAUNCHER_POINT3_Y);
		yTemp = (int) this.orientationMatrix.mapPointsGetY(SHIPLAUNCHER_POINT3_X, SHIPLAUNCHER_POINT3_Y);
		directionTemp = (int) DirectionCalculation.getDirectionDegFromTo(this.anchor.getXworld(), this.anchor.getYworld(), xTemp, yTemp);
		ShipLauncher shipLauncher3 = new ShipLauncher(xTemp, yTemp, directionTemp, this, shipQ3);
		this.shipLaunchers.add(shipLauncher3);
		
		//-- landingPad --//
		//MotherShip.PointData landingPadPoint = MotherShip.MOTHERSHIP1_LANDINGPAD_POINT;
		xTemp = (int) this.orientationMatrix.mapPointsGetX(LANDINGPAD_POINT_X, LANDINGPAD_POINT_Y);
		yTemp = (int) this.orientationMatrix.mapPointsGetY(LANDINGPAD_POINT_X, LANDINGPAD_POINT_Y);
		landingPad = new LandingPad(this.battleModel, this.getTeam(), this);
		landingPad.setPosition(xTemp,yTemp);
		startingObjects.add(landingPad);
		landingPad.activate();
		
		
	}	
	//TODO FÖRENKLAT SÅ LÄNGE:
	/*
			@Override
	public void update(){
		switch(this.status){
		case ENTER_ACTIVE:
			battleModel.addRenderableBackgroundLayer(this);
			this.status = Status.ACTIVE;
			this.setFlagActive(true);
			break;
			
		case ACTIVE:
			for(ShipLauncher shipLauncher: shipLaunchers){
				shipLauncher.update();
			}
			break;
			
		case ENTER_INACTIVE:
			battleModel.removeRenderableBackgroundLayer(this);
			battleModel.removeUpdatable(this);
			this.status = Status.INACTIVE;
			this.setFlagActive(false);
			break;
			
		case INACTIVE:
			break;
		}
	}

	@Override
	public int getPivotX() {	//pivot point is in general middle point. For other pivot points, this should be overridden.
		return this.anchor.getXworld();	
	}

	@Override
	public int getPivotY() {
		return this.anchor.getYworld();
	}
	
	@Override
	public int getCornerX() {
		return this.anchor.getXworld();
	}
	
	@Override
	public int getCornerY() {
		return this.anchor.getYworld();
	}
	*/

	//TODO:
	/*@Override
	public int getDirection() {
		return (int) this.anchor.getRotationAngle();
	}*/
	
	private static LinkedList<Ship> createShipQFromBlueprints(List<Ship.Blueprint> blueprints, BattleModel battleModel, Frame movingFrame, BattleTeam team, MotherShip host){
		LinkedList<Ship> shipQ = new LinkedList<Ship>();
		for(Ship.Blueprint blueprint: blueprints){
			shipQ.add(blueprint.create(battleModel, team));
		}
		return shipQ;
	}

	/*private static LinkedList<Ship> createShipQFromBuilders(List<Ship.Builder> builders, BattleModel battleModel, BattleTeam team){
		LinkedList<Ship> shipQ = new LinkedList<Ship>();
		for(Ship.Blueprint blueprint: blueprints){
			shipQ.add(blueprint.create(battleModel, team));
		}
		return shipQ;
	}*/

	boolean receiveShip(Ship ship){	//get called by a landingPad, when a ship is about to land.
		if(ship.getTeam() == this.getTeam()){
			//TODO SKicka skepp på lagning och sen till rätt ramp 	
			return true;
		}
		else{
			return false;
		}
	}
	
	boolean receivePickUpObject(PickUpObject pickedUpObject){ //get called by landing pad . only?
		if(pickedUpObject instanceof PickUpFlag){ //TODO instance not needed
			battleModel.recievePickUpContent(pickedUpObject.openAndGetPickUpContent(), this);
			return true;
		}
		else{
			return false;
		}
	}

	/*
	@Override
	boolean isAbleToCollideWith(BattleObject battleObject) {
		return false;
	}
	
	
	@Override
	public void activate() {
		battleModel.addUpdatable(this);
		this.status = MotherShip.Status.ENTER_ACTIVE;
	}
	@Override
	public void deactivate() {
		this.status = MotherShip.Status.ENTER_INACTIVE;
	}

	@Override	//Should not even collide. could be solved with interface.
	protected int onCollisionGiveDamage() {
		return 0;
	}
*/
	public Frame getInvalidSpawnFrameAround() {
		int anchorX = this.anchor.getXworld();
		int anchorY = this.anchor.getYworld();
		int left = anchorX - 2*shape.getBoundingSquareSide();
		int top = anchorY - 2*shape.getBoundingSquareSide();
		int right = anchorX + 2*shape.getBoundingSquareSide();
		int bottom = anchorY + 2*shape.getBoundingSquareSide();


		return new Frame(left, top, right, bottom);
	}
	
	
	
	public static class Blueprint implements Serializable{
		private Class<? extends MotherShip> objectClass;
		private ArrayList<LinkedList<Ship.Blueprint>> listOfQueues;
		public Blueprint(Class<? extends MotherShip> objectClass){
			this.objectClass = objectClass;
		}
		
		public int getMaxNumberOfQueues(){
			try {
				Field field = objectClass.getField("NUMBER_OF_LAUNCHPADS");
				try {
					int maxNumberOfQueues = field.getInt(field);
					return maxNumberOfQueues;
				} catch (IllegalArgumentException e) {
					Log.e(TAG,"error when trying to reach field through reflection");
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					Log.e(TAG,"error when trying to reach field through reflection");
					e.printStackTrace();
				}
			} catch (NoSuchFieldException e) {
				Log.e(TAG,"error when trying to reach field through reflection");
				e.printStackTrace();
			}
			Log.e(TAG, "error, returning 0");
			return 0;
		}
		
		public void initListOfQueues(ArrayList<LinkedList<Ship.Blueprint>> listOfQueues){
			this.listOfQueues = listOfQueues;
		}
		
		public MotherShip create(BattleModel battleModel, int x, int y,		
				Frame movingFrame, BattleTeam team, int direction){
			//this.decreaseAmount();
			MotherShip motherShip;
			if(this.listOfQueues != null){
				motherShip = new MotherShip(battleModel, x,y, team, direction, this.listOfQueues);
			} else{
				motherShip = new MotherShip(battleModel, x,y, team, direction, null);
			}

			return motherShip;
		}
	}

}

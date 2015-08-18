package se.oakbright.modules.helpers;

import android.util.Log;

import se.oakbright.modules.Module;
import se.oakbright.planetwhite.GameActivity;
import se.oakbright.planetwhite.ServiceProvider;
import se.oakbright.resource.Key;
import se.oakbright.resource.Resource;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class Speed extends Module {	//TODO maybe don't need to be a Module?
	private static final String TAG = Speed.class.getSimpleName();
	public static final int STANDING_STILL = 0;

	//private
    int velocity;
	//private
    int xv = 0;	// velocity value on the X axis
	//private
	int yv = 0;	// velocity value on the Y axis
//	private int speedLevel = 1;	//To be able to set temporary speeds. 0= standing still, 1=normal speed. 2...3 =???
	Direction direction;
	//TODO direction.addChangeListener(this);
	//private float directionRad = 0; //TODO GONE
	//public static int microsPerUpdate = 0;
	
	/*public Speed() {
		new Speed(STANDING_STILL);
	}*/
	
	/*public Speed(int velocity){
		this.directionRad = 0;
		this.setVelocity(velocity);
	}*/

	public Speed(Resource r){
		super(r);
		this.direction = r.getThe(Key.DIRECTION);
		this.velocity = 100000;
	}
	private void updateVelocityComponents(){
		this.yv = (int) (Math.sin(direction.radians())*velocity);
        this.xv = (int) (Math.cos(direction.radians())*velocity);
	}

	/*public final float getXv() {
		return xv;
	}

	public float getYv() {
		return yv;
	}*/
	
	public float getDistPerUpdate() {
		return (((float)microSecondsPerUpdate()/1000000)*this.velocity);
	}

	private int microSecondsPerUpdate(){
		return ServiceProvider.getMicrosPerUpdate();
	}

	public int getDeltaXperUpdate() {
		updateVelocityComponents(); //TODO should be done with listener instead
		return (int) (((float)microSecondsPerUpdate()/1000000)*xv); ///BattleSurface.pxlWidthDistances/1000000); //((float)xv*10000/BattleSurface.pxlWidthDistances)*((float)Speed.microsPerUpdate/1000000);
	}

	public int getDeltaYperUpdate() {
		updateVelocityComponents();
		return (int) (((float)microSecondsPerUpdate()/1000000)*yv); //BattleSurface.pxlWidthDistances/1000000); // trying with not floating point, yv*10000 to convert the yv into kDistances/s ~= microm/s; original:((float)yv*50000/BattleSurface.pxlWidthDistances)*((float)Speed.microsPerUpdate/1000000);
	}

	public void setVelocity(float velocity){
		this.velocity = (int)(velocity*GameActivity.VELOCITY_ADJUST);
		updateVelocityComponents();
	}
	/*public void setDirection(int xFrom, int yFrom, int xTo, int yTo){		//sets the x and y direction
		int deltaX = xTo - xFrom;
		int deltaY = yTo - yFrom; 
		this.directionRad = (float) (Math.atan2(deltaY,deltaX));//*180/Math.PI);
		updateVelocityComponents();
	}*/

	/*public float getVelocity() {
		return this.velocity;
	}*/

	/*public float getDirectionDeg() {		//return the direction of the speed, in degrees, and converted to "zero is up" degrees
		return (float) ((directionRad*180/Math.PI) + 90);
	}*/

	/*public void setDirectionDeg(float directionDegrees) {
		this.directionRad = (float) ((directionDegrees - 90) * Math.PI / 180);
		updateVelocityComponents();
	}*/

	/*
	public static class Builder extends Buildable<Speed>{
		public Buildable<Direction> directionBuilder;

		@Override
		protected Speed buildNew() {
			assertNotNull(directionBuilder);
			Speed speed = new Speed();
			speed.direction = directionBuilder.getBuilt();
			return speed;
		}
	}*/

	@Override
	public void activate() {

	}

	@Override
	public void deactivate() {

	}
}
package se.oakbright.battleobjects;

import java.util.HashSet;
import java.util.Set;

import se.oakbright.battleobjects.statemachine.CommandReceiverHolder;
import se.oakbright.modules.ModuleObserver;
import se.oakbright.modules.helpers.Direction;
import se.oakbright.modules.helpers.Health;
import se.oakbright.modules.helpers.Positioner;
import se.oakbright.modules.helpers.Shape;
import se.oakbright.planetwhite.BattleTeam;

/**
 * The basic object type that all the visible objects in this package share.
 * @author hampuse
 *
 */

public class BattleObject<C extends BattleObjectCommands> implements IsActiveObservable, IsActiveObserver{
	private static final String TAG = BattleObject.class.getSimpleName();
	final Shape shape;
	final Health health;
	final Positioner positioner;
	final Direction direction;
    final BattleTeam team;
	final CommandReceiverHolder<C> commandHandler;

	private Set<IsActiveObserver> isActiveObservers = new HashSet<IsActiveObserver>();

	public BattleObject(BattleObjectResource<C> r){
		shape = r.getShape();
		health = r.getHealth();
		//TODO addHpObserver();
		positioner = r.getPositioner();
		direction = r.getDirection();
		team = r.getTeam();
		commandHandler = r.getCommandHandler();
	}

	private void addHpObserver(){
		health.addOutOfHpObserver(new ModuleObserver<Health>(){
			@Override
			public void notify(Health subject) {
				deactivate();
			}
		});
	}

	public void deactivate(){
		commandHandler.deactivate();
		notifyIAObserversThatThisIsDeactivated();
	}

    public final BattleTeam getTeam(){
        return this.team;
    }

    public final int getX() {
        return this.positioner.getX();
    }

    public final int getY() {
        return this.positioner.getY();
    }

    public final void setPosition(int x, int y){ //To be overriden if changes need to be done higher up
		this.positioner.setPosition(x, y);
	}

	public final void setDirectionTowards(int x, int y){
		this.direction.setDirectionTowards(x, y);
	}

	public final void setDirection(int directionDegrees){
		direction.setDirectionDegrees(directionDegrees);
	}

	public int getBoundingSquareSide(){
		return shape.getBoundingSquareSide();
	}

	public int getMaxBoundingRadii() {	//TODO delete and fix. is here only because stone and ship implements respawnable.
		return 0;
	}

	public C command(){
		return commandHandler.getCommandReceiver();
	}

    //--IsActiveObservable interface--//
	/*@Override
	public final boolean isActive(){
		command().isActive()//TODO	refactor so that notify if active or not, so this is not needed	return currentStateCommandHandler().isInAnActiveState();
		return true;
	}*/

	@Override
	public void registerIAObserver(IsActiveObserver observer){
		this.isActiveObservers.add(observer);
	}
	
	@Override
	public void unRegisterIAObserver(IsActiveObserver observer){
		this.isActiveObservers.remove(observer);
	}

	private void notifyIAObserversThatThisIsDeactivated(){	//Must be called by the subject when a change occur
		for(IsActiveObserver observer: isActiveObservers){
			observer.notifyIsDeactivated(this);
		}
	}
	
	private void clearIAObservers(){
		this.isActiveObservers.clear();
	}

	//-- isActiveObserver interface --//
	@Override
	public void notifyIsDeactivated(IsActiveObservable subject){
		// Override for functionality
	}

	public interface BattleObjectResource<C>{
		Shape getShape();
		Health getHealth();
		Positioner getPositioner();
		Direction getDirection();
		BattleTeam getTeam();
		CommandReceiverHolder<C> getCommandHandler();
	}

	public abstract static class ResourceImpl<C> implements BattleObjectResource<C>{	//TODO what
		@Override
		public Shape getShape() {
			return null;
		}

		@Override
		public Health getHealth() {
			return null;
		}

		@Override
		public Positioner getPositioner() {
			return null;
		}

		@Override
		public Direction getDirection() {
			return null;
		}

		@Override
		public BattleTeam getTeam() {
			return null;
		}
	}
}

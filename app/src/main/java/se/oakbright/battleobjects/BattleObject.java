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
import se.oakbright.resource.Resource;
import static se.oakbright.resource.Key.*;

/**
 * The basic object type that all the visible objects in this package share.
 */
public class BattleObject<C extends BattleObjectCommands> implements IsActiveObservable, IsActiveObserver {
	private static final String TAG = BattleObject.class.getSimpleName();
	final Shape shape;
	final Health health;
	final Positioner positioner;
	final Direction direction;
	final BattleTeam team;
	final CommandReceiverHolder<C> commandHandler;


	private Set<IsActiveObserver> isActiveObservers = new HashSet<IsActiveObserver>();

	public BattleObject(Resource r) {
		shape = r.getThe(SHAPE);//null;//r.get("shape");
		health = r.getThe(HEALTH);
		addHpObserver();
		positioner = r.getThe(POSITIONER);
		direction = r.getThe(DIRECTION);
		team = r.getThe(TEAM);
		commandHandler = r.getThe(COMMAND_HANDLER);
	}

	private void addHpObserver() {
		health.addOutOfHpObserver(new ModuleObserver<Health>() {
			@Override
			public void notify(Health subject) {
				deactivate();
			}
		});
	}

	public void deactivate() {
		commandHandler.deactivate();
		notifyIAObserversThatThisIsDeactivated();
	}

	public final BattleTeam getTeam() {
		return this.team;
	}

	public final int getX() {
		return this.positioner.getX();
	}

	public final int getY() {
		return this.positioner.getY();
	}

	public final void setPosition(int x, int y) { //To be overriden if changes need to be done higher up
		this.positioner.setPosition(x, y);
	}

	public final void setDirectionTowards(int x, int y) {
		this.direction.setDirectionTowards(x, y);
	}

	public final void setDirection(int directionDegrees) {
		direction.setDirectionDegrees(directionDegrees);
	}

	public int getBoundingSquareSide() {
		return shape.getBoundingSquareSide();
	}

	public int getMaxBoundingRadii() {    //TODO delete and fix. is here only because stone and ship implements respawnable.
		return 0;
	}

	public C command() {
		return commandHandler.getCommandReceiver();
	}

	@Override
	public void registerIAObserver(IsActiveObserver observer) {
		this.isActiveObservers.add(observer);
	}

	@Override
	public void unRegisterIAObserver(IsActiveObserver observer) {
		this.isActiveObservers.remove(observer);
	}

	private void notifyIAObserversThatThisIsDeactivated() {    //Must be called by the subject when a change occur
		for (IsActiveObserver observer : isActiveObservers) {
			observer.notifyIsDeactivated(this);
		}
	}

	private void clearIAObservers() {
		this.isActiveObservers.clear();
	}

	//-- isActiveObserver interface --//
	@Override
	public void notifyIsDeactivated(IsActiveObservable subject) {
		// Override for functionality
	}

}
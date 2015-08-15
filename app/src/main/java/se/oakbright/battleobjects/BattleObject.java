package se.oakbright.battleobjects;

import java.util.HashSet;
import java.util.Set;

import se.oakbright.BattleObjectCommands;
import se.oakbright.CommandReceiverHolder;
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

	//List<Module> modules = new ArrayList<Module>();

	protected Shape shape;
	protected Health health;
	protected Positioner positioner;
	protected Direction direction;
	//protected StateMachine stateMachine;
    //protected BattleModel battleModel;
    protected BattleTeam team;
	public CommandReceiverHolder<C> commandHandler;	//TODO private

	private Set<IsActiveObserver> isActiveObservers = new HashSet<IsActiveObserver>();

	public BattleObject(BattleObjectResource<C> r){
		shape = r.getShape();
		health = r.getHealth();
		positioner = r.getPositioner();
		direction = r.getDirection();
		team = r.getTeam();
		commandHandler = r.getCommandHandler();
	}

	public void afterBuild(){	//Is called by Builder, just before returning the built BattleObject.
		health.addOutOfHpObserver(new ModuleObserver<Health>(){
			@Override
			public void notify(Health subject) {
				command().deactivate();
			}
		});
	}

    /*public Frame getMovingFrame() {
        return this.positioner.getMovingFrame();
    }*/

    /*public final BattleModel getBattleModel(){
        return this.battleModel;
    }*/

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

	/*public BattleObject(R r){
		//TODO
	}*/

	/*@Override
	public void activate(){
		CommandHandler commandHandler = currentStateCommandHandler();
		commandHandler.activate();
	}

	@Override
	public void deactivate(){
		CommandHandler commandHandler = currentStateCommandHandler();
		commandHandler.deactivate();
	}*/

	public C command(){
		return commandHandler.getCommandReceiver();
	}

	//protected abstract <U extends CommandHandler> U currentStateCommandHandler();
	/*protected <U extends CommandHandler> U currentStateCommandHandler(){
		State<U> currentState = stateMachine.getCurrentState();
		return currentState.getCommandHandler();
	}*/

    //--IsActiveObservable interface--//
	@Override
	public final boolean isActive(){
		//TODO	refactor so that notify if active or not, so this is not needed	return currentStateCommandHandler().isInAnActiveState();
		return true;
	}

	@Override
	public void registerIAObserver(IsActiveObserver observer){
		this.isActiveObservers.add(observer);
	}
	
	@Override
	public void unRegisterIAObserver(IsActiveObserver observer){
		this.isActiveObservers.remove(observer);
	}
	@Override
	public void notifyIAObservers(){	//Must be called by the subject when a change occur
		for(IsActiveObserver observer: isActiveObservers){
			observer.notifyIsActiveChangeIn(this);
		}
	}
	
	protected void clearIAObservers(){
		this.isActiveObservers.clear();
	}

	//-- isActiveObserver interface --//
	@Override
	public void notifyIsActiveChangeIn(IsActiveObservable subject){
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

	public abstract static class ResourceImpl<C> implements BattleObjectResource<C>{
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
	//public static <T extends BattleObject> T getOfType(ObjectResource<T> resource){

	//TODO class OutOfHpObserver extends Observer {
	//}
/*
	public abstract static class Builder<T extends BattleObject, I extends BattleObjectInterface> extends Buildable<T>{
		public Shape.Builder shapeBuilder;
		public Health.Builder healthBuilder;
		public Buildable<Positioner> positionerBuilder;
		public Direction.Builder directionBuilder;
		public BattleTeam team;
		public StateMachine.Builder<I> stateMachineBuilder;
		//public ShipStateMachineBuilder stateMachineBuilder; //TODO SHould not be marked with all spec ship types. Would be solved if only have TypeInterface
		//public T type;

		public Builder(){
			healthBuilder = new Health.Builder();	//TODO should be Health.Inbreakable() or something?
			positionerBuilder = new Positioner.Builder();
			directionBuilder = new Direction.Builder();
			MiddlePoint.Builder middlePointBuilder = new MiddlePoint.Builder();
			middlePointBuilder.positionerBuilder = positionerBuilder;
			directionBuilder.positionBuilder = middlePointBuilder;
		}



		@Override
		public T buildNew(){
			beforeBuildNew();
			T type = getType();
			type.stateMachine = stateMachineBuilder.getBuilt();
			type.shape = shapeBuilder.getBuilt();
			type.health = healthBuilder.getBuilt();
			type.positioner = positionerBuilder.getBuilt();
			type.direction = directionBuilder.getBuilt();
			type.battleModel = ServiceProvider.getBattleModel(); //TODO should not be here right?
			type.team = team;
			RuntimeTests.testForNull(team, "team");
			type.afterBuild();
			return type;
		}

		protected abstract T getType();
		protected abstract void beforeBuildNew();

	}*/

	/*public static interface Resource{
		public void test();
	}*/

	/*public static class TypeBuilder<T extends BattleObject<R>, R extends BattleObject.Resource>{
		R resource;
		public TypeBuilder(R resource){
			this.resource = resource;
		}

		public T getBuilt(){
			recursion...
			//TODO
			return null;
		}
	}*/

/*
	public static class TypeBuilder<T>{
		Resource resource;
		public TypeBuilder(Resource<T> resource){
			this.resource = resource;
		}

		public T getBuilt(){
			return resource.getType();
			//recursion...
			//TODO
			return null;
		}
	}*/
}

package se.oakbright.resources;


/**
 * Created by hampuse on 2015-08-13.
 */
/*
public class ShipResource implements TypeResource<Ship>, Ship.Resource, Weapon.Resource, Mover.Resource, IconRenderer.Resource{
    public BattleTeam team;
    public IconRenderer iconRenderer;
    private Mover mover;
    private Weapon weapon;

    private StateMachine<State<ShipCommandHandler>> stateMachine;

    public ShipResource(BattleTeam team){
        this.team = team;
    }

    @Override
    public Ship createNewOfType() {
        ShipResource r = new ShipResource(team);
        r.stateMachine = createStateMachine();
        Ship ship = new Ship(r);
        return ship;
    }

    public StateMachine<State<ShipCommandHandler>> getStateMachine(){
        return null;
    }

    private StateMachine<State<ShipCommandHandler>> createStateMachine(){
        ShipStateMachineBuilder stateMachineBuilder = new ShipStateMachineBuilder();
        stateMachineBuilder.readyToLaunch.addActiveModule(getIconRenderer());
        //stateMachineBuilder.outThere.addActiveModule(iconRenderer);
        //stateMachineBuilder.outThere.addActiveModule(mover);
        //stateMachineBuilder.outThere.addActiveModule(pathModuleBuilder);
        //stateMachineBuilder.outThere.addActiveModule(collisionModule);
        //stateMachineBuilder.outThere.addActiveModule(getWeapon());
        return stateMachineBuilder.getBuilt();
    }

    public Weapon getWeapon(){
        if(weapon == null){
         //TODO   this.weapon = new Weapon(this);
        }
        return weapon;
    }

    public IconRenderer getIconRenderer(){
        if(iconRenderer == null){
            this.iconRenderer = new IconRenderer(getIconRendererResource());
        }
        return iconRenderer;
    }

    public Mover getMover(){
        if(mover == null){
            //TODO        this.mover = new Mover(this);
        }
        return mover;
    }

    private IconRenderer.Resource getIconRendererResource(){
        return this;
         /* new IconRenderer.Resource(){
            @Override
            public InternalPoint getPivotPoint() {
                return getPivotPoint();
            }

            @Override
            public IconModule getIconModule() {
                return null;
            }
        };*/
    /*}

    @Override
    public InternalPoint getPivotPoint() {
        return null;
    }

    @Override
    public IconModule getIconModule() {
        return null;
    }

    @Override
    public void getTest() {

    }
}*/

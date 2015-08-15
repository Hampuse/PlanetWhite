package se.oakbright.battleobjects;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by hampuse on 2015-07-12.
 */
//TODO make
    /*
public class BattleObjectTest {
    BattleObject battleObject;
    static Health health;
    static StateMachine<State<BattleObjectInterface>> stateMachine = mock(StateMachine.class);
    //static StateMachine<State<BattleObjectInterface>> stateMachine2 = stateMachine;

    @Before
    public void setup(){
        TypeBuilder<BattleObjectFake> battleObjectBuilder = new TypeBuilder<BattleObjectFake>(getBattleObjectFakeResource());
        //BattleObjectFake.Builder battleObjectBuilder = new BattleObjectFake.Builder();
       battleObject = battleObjectBuilder.getBuilt();
    }

    public BattleObjectFake.Resource getBattleObjectFakeResource(){
        return new BattleObjectFake.Resource(){

        }
    }

    @Test
    public void test_OutOfHpObserver_deactivates_battleobject(){
        State state = mock(State.class);
        CommandHandler commandHandler = mock(CommandHandler.class);
        when(stateMachine.getCurrentState()).thenReturn(state);
        when(state.getCommandHandler()).thenReturn(commandHandler);

        health.tryDecreaseHp(1);    //Should make the health zero, which should call the notify on
        // the observer injected by battleobject, which should call deactivate() on the current commandHandler.
        //verify(health).addOutOfHpObserver(Matchers.<ModuleObserver<Health>>any());

        verify(commandHandler).deactivate();
    }

    public static class BattleObjectFake extends BattleObject{

        public class TResource extends BattleObject.BattleObjectResource implements TypeResource<BattleObjectFake> {
            @Override
            public BattleObjectFake createNewOfType() {
                return new BattleObjectFake(this);
            }
        }

        public BattleObjectFake(TypeResource<TResource> r){}

        //TODO resource
        /*public static class Builder extends BattleObject.Builder<BattleObjectFake,BattleObjectInterface>{
            public Builder(){
                super();

                healthBuilder = new Health.Builder(); //mock(Health.Builder.class);
                healthBuilder.startHp = 1;
                BattleObjectTest.health = healthBuilder.getBuilt();
                //when(healthBuilder.getBuilt()).thenReturn(BattleObjectTest.health);
                stateMachineBuilder = mock(StateMachine.Builder.class); //TODO NOT SHIP//StateMachine.Builder.class);
                BattleObjectTest.stateMachine = stateMachineBuilder.getBuilt();
                when(stateMachineBuilder.getBuilt()).thenReturn(BattleObjectTest.stateMachine);
                shapeBuilder = mock(Shape.Builder.class);
                when(shapeBuilder.getBuilt()).thenReturn(mock(Shape.class));
                team = mock(BattleTeam.class);
            }


            @Override
            protected BattleObjectFake getType() {
                return new BattleObjectFake();
            }

            @Override
            protected void beforeBuildNew() {

            }

        }*/
    //}
//}

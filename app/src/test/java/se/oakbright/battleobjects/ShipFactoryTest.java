package se.oakbright.battleobjects;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by hampuse on 2015-07-14.
 */
/*public class ShipFactoryTest {
    TypeBuilder<Ship> shipBuilder;
    //Ship.Builder<ShipCommandHandler> shipBuilder;
    Ship ship;

    @Before
    public void init(){
        BattleModel battleModel = mock(BattleModel.class);
        ServiceProvider.reset();
        ServiceProvider.setBattleModel(battleModel);
        InIcon icon = mock(InIcon.class);
        when(battleModel.getIcon(any(IconId.class))).thenReturn(icon);
        shipBuilder = ShipResourcnew ShipFactory().getStandard();

        //shipBuilder.battleModel = battleModel;
        shipBuilder.team = mock(BattleTeam.class);
    }

    @Test
    public void test_ship() {
        ship = shipBuilder.getBuilt();
        assertNotNull(ship);
        //test_mover();
    }




    @Test
    public void test_that_all_modules_get_built(){
        ShipFactory f = new ShipFactory();

        f.shipBuilder = spy(new Ship.Builder());
        f.iconCreater = mock(IconCreater.class);
        InIcon icon = mock(InIcon.class);
        when(f.iconCreater.getBuilt()).thenReturn(icon);    //to not get null check failure
        f.iconModuleBuilder = spy(new IconModule.Builder());
        f.cornerPointBuilder = spy(new CornerPoint.Builder());
        f.middlePointBuilder = spy(new MiddlePoint.Builder());
        f.shapeBuilder = spy(new Shape.Builder());
        f.iconRendererBuilder = spy(new IconRenderer.Builder());
        f.orientationMatrixBuilder = spy(new OrientationMatrix.Builder());
        f.moverBuilder = spy(new Mover.Builder());
        f.pathModuleBuilder = spy(new PathModule.Builder());

        Ship.Builder shipBuilder = f.getStandard();
        shipBuilder.team = mock(BattleTeam.class);
        shipBuilder.getBuilt();

        verify_is_built(f.shipBuilder);
        verify_is_built(f.iconCreater);
        verify_is_built(f.iconModuleBuilder);
        verify_is_built(f.cornerPointBuilder);
        verify_is_built(f.middlePointBuilder);
        verify_is_built(f.shapeBuilder);
        verify_is_built(f.iconRendererBuilder);
        //TODO verify_is_built(f.orientationMatrixBuilder);
        verify_is_built(f.moverBuilder);
        verify_is_built(f.pathModuleBuilder);

        //verify(f.moverBuilder, atLeastOnce()).getBuilt();
        //verify(f.pathModuleBuilder, atLeastOnce()).getBuilt();

        //f.iconCreater = spy(f.iconCreater);
    }

    private void verify_is_built(Buildable<?> builder){
        verify(builder, atLeastOnce()).getBuilt();
    }

    @Test
    public void test_that_shipBuilder_is_setup_correctly_in_ShipFactory(){

        //IconModule iconModule = shipBuilder.iconModule;
        //assertNotNull(iconModule);
        //assertNotNull(iconModule.iconIdBlueprint);

        // asserttions for null should be redundant, should be detected when buildNew() is run
        assertNotNull("shapeBuilder", shipBuilder.shapeBuilder);
        assertNotNull("healthBuilder", shipBuilder.healthBuilder);
        assertNotNull("shipBuilder", shipBuilder.positionerBuilder);
        assertNotNull("directionBuilder", shipBuilder.directionBuilder);
        assertNotNull("team",shipBuilder.team);
        assertNotNull("stateMachineBuilder", shipBuilder.stateMachineBuilder);

        //test_that_ShapeBuilder_is_setup_correctly(shipBuilder.shapeBuilder);
    }

    public static Ship.Builder getShipBuilderFake(){
        ShipFactoryTest shipFactoryTest = new ShipFactoryTest();
        shipFactoryTest.init();
        return shipFactoryTest.shipBuilder;
    }
   /* @Test
    public void verify_that_builder_components_are_not_null(){
        assertNotNull("shipBuilder",shipBuilder);
        assertNotNull("stateMachineBuil",shipBuilder.stateMachine);
        assertNotNull("positioner", shipBuilder.positioner);
        assertNotNull("health",shipBuilder.health);
        assertNotNull("shape",shipBuilder.shape);
        assertNotNull("direction", shipBuilder.direction);

        assertNull("battleModel", shipBuilder.battleModel);
        assertNull("team", shipBuilder.team);
    }


}*/

package se.oakbright.modules.activatables.updatables;

import org.junit.Before;
import org.junit.Test;

import se.oakbright.Frames.Frame;
import se.oakbright.battleobjects.BattleObject;
import se.oakbright.modules.helpers.Direction;
import se.oakbright.modules.helpers.Positioner;
import se.oakbright.modules.helpers.Speed;
import se.oakbright.planetwhite.BattleClock;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.ServiceProvider;
import se.oakbright.resources.MoverBuilderResource;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertSame;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by hampuse on 2015-07-25.
 */
public class MoverTest {
    Mover.Builder moverBuilder = new Mover.Builder();
    Mover mover;

    Positioner.Builder positionerBuilder = new Positioner.Builder();
    Positioner positioner = spy(positionerBuilder.getBuilt());


    //TODO BattleObject.Builder hostBuilder = mock(BattleObject.Builder.class);
    BattleObject host = mock(BattleObject.class);
    PathModule.Builder pathModuleBuilder = mock(PathModule.Builder.class);
    PathModule pathModule = mock(PathModule.class);
    Direction.Builder directionBuilder = mock(Direction.Builder.class);
    Direction direction = mock(Direction.class);

    Speed speed = mock(Speed.class);

    @Before
    public void setup(){
        moverBuilder.goVelocity = 1;
        //moverBuilder.movingFrame = mock(Frame.class);
        setupMocks(moverBuilder);

        mover = moverBuilder.getBuilt();
        ServiceProvider.reset();
        ServiceProvider.setBattleModel(mock(BattleModel.class));
    }

    private void setupMocks(Mover.Builder moverBuilder){
        moverBuilder.positionerBuilder = mock(Positioner.Builder.class);
        when(moverBuilder.positionerBuilder.getBuilt()).thenReturn(positioner);

        //TODO when(hostBuilder.getBuilt()).thenReturn(host);

        moverBuilder.pathModuleBuilder = pathModuleBuilder;
        when(pathModuleBuilder.getBuilt()).thenReturn(pathModule);

        moverBuilder.directionBuilder = directionBuilder;
        when(directionBuilder.getBuilt()).thenReturn(direction);

        Speed.Builder speedBuilder = mock(Speed.Builder.class);
        moverBuilder.speedBuilder = speedBuilder;
        when(speedBuilder.getBuilt()).thenReturn(speed);
    }

    /*@Test
    public void test_mover(){

        test_that_mover_is_setup_properly(mover, positioner, moverBuilder.goVelocity);
    }*/

    /*public static void test_mover(Mover mover, Positioner expectedPositioner, int expectedVelocity){
        assertNotNull("mover", mover);
        test_that_positioner_gets_moved(mover, expectedPositioner, expectedVelocity);

    }

    private static void test_that_positioner_gets_moved(Mover mover, Positioner expectedPositioner, int expectedVelocity) {
        mover.setDirection(180);
        expectedPositioner.setPosition(0, 0);
        mover.update();
        int expectedX = 0;
        int expectedY = 0 + expectedVelocity;
        assertEquals(expectedX, expectedPositioner.getX());
        assertEquals(expectedY, expectedPositioner.getY());
    }*/


    @Test
    public void testThatSpeedIsSetupProperly(){
        verify(speed).setVelocity(1);
    }

    @Test
    public void test_that_start_moving_on_activate(){
        mover.activate();
    }


    @Test
    public void test_that_mover_moves_positioner(){
        //direction.setDirectionTowards(100, 200);
        test_zero_speed_does_not_move();
        test_plus_integers();
        test_minus_integers();
    }



    private void test_zero_speed_does_not_move(){
        positioner.setPosition(100, 100);
        when(speed.getDeltaXperUpdate()).thenReturn(0);
        when(speed.getDeltaYperUpdate()).thenReturn(0);
        for(int i = 0; i < 1000; i++) {
            mover.update();
            assertSame(positioner.getX(), 100);
            assertSame(positioner.getY(), 100);
        }
    }

    private void test_plus_integers(){
        positioner.setPosition(100, 100);
        when(speed.getDeltaXperUpdate()).thenReturn(1);
        when(speed.getDeltaYperUpdate()).thenReturn(2);
        mover.update();
        assertSame(positioner.getX(), 101);
        assertSame(positioner.getY(), 102);
    }

    private void test_minus_integers(){
        positioner.setPosition(100, 100);
        when(speed.getDeltaXperUpdate()).thenReturn(-1);
        when(speed.getDeltaYperUpdate()).thenReturn(-2);
        mover.update();
        assertSame(positioner.getX(), 99);
        assertSame(positioner.getY(), 98);
    }

    //TODO
    /*@Test
    public void test_update(){
    }*/

    //TODO test_isAbleTOCollideWith()

}

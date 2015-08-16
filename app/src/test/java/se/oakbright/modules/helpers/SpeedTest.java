package se.oakbright.modules.helpers;

import org.junit.Before;
import org.junit.Test;

import se.oakbright.planetwhite.BattleClock;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.ServiceProvider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by hampuse on 2015-07-28.
 */
//TODO:
    /*
public class SpeedTest {
    Speed speed;// = new Speed();

    @Before
    public void setup(){
        ServiceProvider.reset();
        ServiceProvider.setBattleModel(mock(BattleModel.class));
        setup_battleclock_1_second_per_update();

        Speed.Builder speedBuilder = new Speed.Builder();
        Direction.Builder directionBuilder = mock(Direction.Builder.class);
        //speedBuilder.directionBuilder = mock(Direction.Builder.class);
        speedBuilder.directionBuilder = directionBuilder;
        when(directionBuilder.getBuilt()).thenReturn(mock(Direction.class));
        speed = speedBuilder.getBuilt();
        speed.setVelocity(100);
    }

    private void setup_battleclock_1_second_per_update(){
        BattleClock battleClock = mock(BattleClock.class);
        when(battleClock.getMicroSecondsPerUpdate()).thenReturn(1000000); // 1 second.
        ServiceProvider.setBattleClock(battleClock);
    }

    /*

    @Test
    public void test_direction_180(){
        when(speed.direction.radians()).thenReturn((float) ((180-90)*Math.PI/180));
        assertEquals(0, speed.getDeltaXperUpdate());
        assertEquals(100, speed.getDeltaYperUpdate(), 1); // diff of 1 is ok since using truncation.
    }

    @Test
    public void test_direction_135(){
        when(speed.direction.radians()).thenReturn((float) (Math.PI*(135-90)/180));
        assertEquals(71, speed.getDeltaXperUpdate(), 1);
        assertEquals(71, speed.getDeltaYperUpdate(), 1); // diff of 1 is ok since using truncation.
    }

    @Test
    public void test_direction_315(){
        when(speed.direction.radians()).thenReturn((float) ((315-90)*Math.PI/180));
        assertEquals(-71, speed.getDeltaXperUpdate(), 1);
        assertEquals(-71, speed.getDeltaYperUpdate(), 1); // diff of 1 is ok since using truncation.

    }
}*/

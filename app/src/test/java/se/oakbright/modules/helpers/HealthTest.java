package se.oakbright.modules.helpers;

import org.hamcrest.core.AnyOf;
import org.junit.Before;
import org.junit.Test;

import se.oakbright.modules.ModuleObserver;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Created by hampuse on 2015-08-03.
 */
//TODO:
    /*
public class HealthTest {
    Health health;

    @Before
    public void setup(){
        Health.Builder healthBuilder = new Health.Builder();
        healthBuilder.startHp = 10;
        health = healthBuilder.getBuilt();
    }

    @Test
    public void test_decrease_hp(){
        health.tryDecreaseHp(3);
        assertEquals(70, health.getPercentOfHealthLeft());
    }

    @Test
    public void test_that_outOfHpObservers_get_notified(){
        ModuleObserver testObserver = mock(ModuleObserver.class);
        health.addOutOfHpObserver(testObserver);
        health.tryDecreaseHp(5);
        verify(testObserver, times(0)).notify(health);
        health.tryDecreaseHp(10);
        verify(testObserver, times(1)).notify(health);
    }
}*/

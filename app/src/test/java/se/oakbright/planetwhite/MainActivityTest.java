package se.oakbright.planetwhite;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

/**
 * Created by hampuse on 2015-07-22.
 */
public class MainActivityTest {
    MainActivity main;

    @Before
    public void setup(){
        main = new MainActivity();
        main.startBattle(MainActivity.setupDebugBattle());
    }

    @Test
    public void test(){
        assertNotNull(main);
    }
}

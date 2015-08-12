package se.oakbright.planetwhite;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by hampuse on 2015-07-11.
 */
public class BattleModelTest {
    BattleModel battleModel;

 /*
    @Test
    public void test_that_added_updatable_gets_updated(){

    }

 /* HARD TO test, error in init, probably because of IconFactory
   BattleModel battleModel;

    Updatable updatable = mock(Updatable.class);
    Resources res = mock(Resources.class);

    @Before
    public void setup(){
        Context context = mock(Context.class);
        BattleSetup battleSetup = mock(BattleSetup.class);
        int trackWidth = 1000;
        int trackHeight = 1000;
        when(context.getResources()).thenReturn(res);
        this.battleModel = new BattleModel(context, battleSetup, trackWidth, trackHeight);
       // this.battleModel.setup();
        //MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdate(){
       /* verify(updatable).update();
        battleModel.addUpdatable(updatable);
        battleModel.update();
        verify(updatable).update();
        verifyZeroInteractions(updatable);
        battleModel.update();
        verifyZeroInteractions(updatable);
        verify(updatable).update();*/

//    }



}

package se.oakbright.modules.activatables.updatables;

import org.junit.Before;
import org.junit.Test;

import se.oakbright.modules.activatables.renderables.IconRenderer;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.ServiceProvider;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by hampuse on 2015-07-31.
 */
public class UpdatableModuleTest {
    UpdatableModule updatableModule;
    BattleModel battleModel = mock(BattleModel.class);

    @Before
    public void setup(){
        updatableModule = new UpdatableModule(){
            @Override
            public void update() {
            }
        };

        ServiceProvider.reset();
        ServiceProvider.setBattleModel(battleModel);
    }

    @Test
    public void test_that_activate_reaches_battleModel(){
        updatableModule.activate();
        verify(battleModel).addUpdatable(updatableModule);
    }
}

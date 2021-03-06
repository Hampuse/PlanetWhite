package se.oakbright.modules.activatables.renderables;

import org.junit.Before;
import org.junit.Test;

import se.oakbright.modules.activatables.renderables.IconRenderer;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.ServiceProvider;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by hampuse on 2015-07-17.
 */
public class IconRendererTest {
    IconRenderer iconRenderer;
    BattleModel battleModel = mock(BattleModel.class);

    @Before
    public void setup(){
        iconRenderer = new IconRenderer();
        ServiceProvider.reset();
        ServiceProvider.setBattleModel(battleModel);
    }

    @Test
    public void test_that_activate_reaches_battleModel(){
        iconRenderer.activate();
        verify(battleModel).addRenderableMiddleLayer(iconRenderer);
    }

    //public void test_that
}

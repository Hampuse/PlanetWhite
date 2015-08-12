package se.oakbright.modules.activatables.renderables;

import se.oakbright.modules.Module;

/**
 * Created by hampuse on 2015-07-02.
 */
public abstract class RenderableModuleBackgroundLayer extends Module implements Renderable{

    @Override
    public void activate() {
        getBattleModel().addRenderableBackgroundLayer(this);
    }

    @Override
    public void deactivate() {
        getBattleModel().removeRenderableBackgroundLayer(this);
    }
}

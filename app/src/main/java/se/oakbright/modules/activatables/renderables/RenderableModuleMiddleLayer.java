package se.oakbright.modules.activatables.renderables;

import android.util.Log;

import se.oakbright.modules.Module;

/**
 * Created by hampuse on 2015-07-02.
 */
public abstract class RenderableModuleMiddleLayer extends Module implements Renderable{

    @Override
    public void activate() {
        getBattleModel().addRenderableMiddleLayer(this);
    }

    @Override
    public void deactivate() {
        getBattleModel().removeRenderableMiddleLayer(this);
    }
}

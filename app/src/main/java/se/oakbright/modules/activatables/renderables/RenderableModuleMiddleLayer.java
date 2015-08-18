package se.oakbright.modules.activatables.renderables;

import android.util.Log;

import se.oakbright.modules.Module;
import se.oakbright.resource.Resource;

/**
 * Created by hampuse on 2015-07-02.
 */
public abstract class RenderableModuleMiddleLayer extends Module implements Renderable{

    public RenderableModuleMiddleLayer(Resource r){
        super(r);
    }
    @Override
    public void activate() {
        getBattleModel().addRenderableMiddleLayer(this);
    }

    @Override
    public void deactivate() {
        getBattleModel().removeRenderableMiddleLayer(this);
    }
}

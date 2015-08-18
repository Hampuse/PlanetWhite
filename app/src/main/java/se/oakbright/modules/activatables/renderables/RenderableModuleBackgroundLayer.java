package se.oakbright.modules.activatables.renderables;

import se.oakbright.modules.Module;
import se.oakbright.resource.Resource;

/**
 * Created by hampuse on 2015-07-02.
 */
public abstract class RenderableModuleBackgroundLayer extends Module implements Renderable{

    RenderableModuleBackgroundLayer(Resource r){
        super(r);
    }
    @Override
    public void activate() {
        getBattleModel().addRenderableBackgroundLayer(this);
    }

    @Override
    public void deactivate() {
        getBattleModel().removeRenderableBackgroundLayer(this);
    }
}

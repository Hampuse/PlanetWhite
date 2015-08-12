package se.oakbright.modules.activatables.renderables;

import android.graphics.Canvas;

import se.oakbright.planetwhite.BattleSurface;

/**
 * Created by hampuse on 2015-06-27.
 */
public interface Renderable {
    public void render(Canvas canvas, BattleSurface battleSurface);
}

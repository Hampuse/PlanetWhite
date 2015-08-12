package se.oakbright.modules.activatables.renderables;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import se.oakbright.Buildable;
import se.oakbright.modules.internalpoints.InternalPoint;
import se.oakbright.planetwhite.BattleSurface;

/**
 * Created by hampuse on 2015-08-06.
 */
public class DebugInternalPointRenderer extends RenderableModuleForegroundLayer{
    private InternalPoint point;

    public DebugInternalPointRenderer(InternalPoint point){
        this.point = point;
    }

    @Override
    public void render(Canvas canvas, BattleSurface battleSurface) {
        int x = point.x();
        int y = point.y();
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawPoint(battleSurface.coordToPxl(x), battleSurface.coordToPxl(y), paint);
    }

    public static class Builder extends Buildable<DebugInternalPointRenderer> {
        private Buildable<? extends InternalPoint> pointBuilder;

        public Builder(Buildable<? extends InternalPoint> pointBuilder){
            this.pointBuilder = pointBuilder;
        }

        @Override
        protected DebugInternalPointRenderer buildNew() {
            DebugInternalPointRenderer pointRenderer = new DebugInternalPointRenderer(pointBuilder.getBuilt());
            return pointRenderer;
        }
    }
}

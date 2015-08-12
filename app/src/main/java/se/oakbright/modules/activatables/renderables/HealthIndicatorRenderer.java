package se.oakbright.modules.activatables.renderables;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import se.oakbright.modules.helpers.Health;
import se.oakbright.modules.helpers.Positioner;
import se.oakbright.planetwhite.BattleSurface;

/**
 * Created by hampuse on 2015-06-26.
 */
public class HealthIndicatorRenderer extends RenderableModuleForegroundLayer {
    Health health;
    Positioner positioner;

    public void render(Canvas canvas, BattleSurface battleSurface) {
        //-- HEALTH INDICATOR --//
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6);
        int HEALTH_BAR_LENGTH = 30; // pxl
        int HEALTH_BAR_PADDING = 2;
        Point midPoint = battleSurface.coordToPxl(positioner.getX(), positioner.getY());
        int startX = midPoint.x - HEALTH_BAR_LENGTH / 2;
        int barY = midPoint.y + 20;
        canvas.drawLine(startX, barY, startX + HEALTH_BAR_LENGTH, barY, paint);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(6 - HEALTH_BAR_PADDING);
        canvas.drawLine(startX, barY, startX + (int) (HEALTH_BAR_LENGTH * health.getPercentOfHealthLeft() / 100), barY, paint);
    }

    @Override
    public void activate() {

    }

    @Override
    public void deactivate() {

    }
}

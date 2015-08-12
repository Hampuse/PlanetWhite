package se.oakbright.modules.activatables.renderables;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import se.oakbright.modules.helpers.Positioner;
import se.oakbright.modules.helpers.Shape;
import se.oakbright.planetwhite.BattleSurface;

/**
 * Created by hampuse on 2015-06-27.
 */
public final class HighlightRenderer extends RenderableModuleBackgroundLayer implements Renderable {
    private Positioner positioner;
    private Shape shape;

    public void setPositioner(Positioner positioner){
        this.positioner = positioner;
    }

    @Override
    public void render(Canvas canvas, BattleSurface battleSurface) {
        Paint paint = new Paint();
        paint.setColor( Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        int x = positioner.getX();
        int y = positioner.getY();
        int radius = shape.getBoundingSquareSide();	// Start from the biggest radius

        for(int i = 1; i < BattleSurface.HILI_FEATHER;  i++){	//paint smaller and smaller circles, that are less transparent.
            paint.setAlpha( (BattleSurface.OBJECT_HILI_MAX_ALPHA) * i / BattleSurface.HILI_FEATHER );
            canvas.drawCircle(battleSurface.coordToPxl(x), battleSurface.coordToPxl(y), radius -i, paint);
        }
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(BattleSurface.OBJECT_HILI_MAX_ALPHA);
        canvas.drawCircle(battleSurface.coordToPxl(x), battleSurface.coordToPxl(y), radius - BattleSurface.HILI_FEATHER, paint);
    }
}

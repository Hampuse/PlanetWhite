package se.oakbright.modules.activatables.renderables;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import se.oakbright.modules.helpers.Positioner;
import se.oakbright.modules.activatables.updatables.PathModule;
import se.oakbright.calculation.Point;
import se.oakbright.planetwhite.BattleSurface;
import se.oakbright.resource.Resource;
import static se.oakbright.resource.Key.*;

/**
 * Created by hampuse on 2015-06-28.
 */
public class PathRenderer extends RenderableModuleBackgroundLayer {
    private Paint pathPaint = new Paint();
    private PathModule path;
    private Positioner positioner;

    public PathRenderer(Resource r){
        super(r);
        //TODO path = r.get(PATH);
        positioner = r.getThe(POSITIONER);
        this.pathPaint.setColor(Color.WHITE);
        this.pathPaint.setStyle(Paint.Style.STROKE);
        this.pathPaint.setStrokeWidth(2);
        this.pathPaint.setAntiAlias(true);
        //this.pathPaint.setStrokeJoin(Join.ROUND); //TODO if the width is increased the joins look ugly, but this doesent affect it, cause it is drawn without joins - fix

    }

    public void render(Canvas canvas, BattleSurface battleSurface) {    //draws the ship's path on canvas. called from render() in MainGameSurface.
        int xPrevious;
        int yPrevious;
        if (path.nextPoint != null) {
            canvas.drawLine(battleSurface.coordToPxl(positioner.getX()), battleSurface.coordToPxl(positioner.getY()), battleSurface.coordToPxl(path.nextPoint.x), battleSurface.coordToPxl(path.nextPoint.y), pathPaint);    //Draw the first line from the ship to nextCoord
            if (!path.isEmpty()) {    //check if a path exists, and draws it:
                xPrevious = path.nextPoint.x;
                yPrevious = path.nextPoint.y;
                for (Point point : path.getAsQueue()) {            //draw the lines in the path,
                    canvas.drawLine(battleSurface.coordToPxl(xPrevious), battleSurface.coordToPxl(yPrevious), battleSurface.coordToPxl(point.x), battleSurface.coordToPxl(point.y), pathPaint);
                    xPrevious = point.x;
                    yPrevious = point.y;
                }
            }
        }
    }
}

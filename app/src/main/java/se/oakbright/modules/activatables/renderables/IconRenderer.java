package se.oakbright.modules.activatables.renderables;

import android.graphics.Canvas;
import android.graphics.Paint;

import se.oakbright.modules.helpers.Direction;
import se.oakbright.modules.helpers.IconModule;
import se.oakbright.modules.internalpoints.InternalPoint;
import se.oakbright.planetwhite.BattleSurface;
import static se.oakbright.resource.Key.*;
import se.oakbright.resource.Resource;

/**
 * Created by hampuse on 2015-06-24.
 */
public class IconRenderer extends RenderableModuleMiddleLayer implements Renderable {
	private final IconModule iconModule;
	private final InternalPoint pivotPoint, cornerPoint;
	private final Direction direction;

	public IconRenderer(Resource r){
		super(r);
		iconModule = r.getThe(ICON_MODULE);
		pivotPoint = r.getThe(PIVOT_POINT);
		cornerPoint = r.getThe(CORNER_POINT);
		direction = r.getThe(DIRECTION);
	}

    public void render(Canvas canvas, BattleSurface battleSurface) {		//draws the ship on canvas. called from render() in MainGameSurface.
        if(iconModule.getBitmap() != null){
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setFilterBitmap(true);
            canvas.drawBitmap(iconModule.getBitmap(), battleSurface.createBitmapMatrixPxl(cornerPoint, pivotPoint, direction), paint);
        }
    }

	/*public void setIconModule(IconModule iconModule){
		this.iconModule = iconModule;
	}*/


	/*
	// DEBUG FUNCTIONS: //
	private void debugDrawBoundingCircleAndSquare(int x, int y, Canvas canvas, BattleSurface battleSurface){
        //Log.d("vaer","draw object at x=" +this.x+ " y="+ this.y + "matrix="+this.orientationMatrixPxl);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        //FIX canvas.drawCircle(battleSurface.coordToPxl(x), battleSurface.coordToPxl(x), battleSurface.dimensionToPxl(this.icon.getMaxBoundingRadii()), paint);
       //FIX: canvas.drawRect(x - this.icon.getBoundingSquareSide(), y - this.icon.getBoundingSquareSide(), x + this.icon.getBoundingSquareSide(), y + this.icon.getBoundingSquareSide(), paint);
        paint.setColor(Color.RED);
    }

    private void debugDrawMiddlePoint(int x, int y, Canvas canvas, BattleSurface battleSurface){
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawPoint(battleSurface.coordToPxl(x), battleSurface.coordToPxl(y), paint);
    }

    private void debugDrawBoundingPointsIfShip(Canvas canvas, BattleSurface battleSurface){
		/*Paint paint = new Paint();
		if(this instanceof Ship) {
			int OFFSET = 200;
			float[] ob1Points = this.getBoundingPointsCoords();
			for (int i = 0; i < ob1Points.length; i = i + 2) {
				int ob1PointX = (int) ob1Points[i] + OFFSET;
				int ob1PointY = (int) ob1Points[i + 1] + OFFSET;
				int ob1PointDistToCenter2 = (int) Math.hypot( ob1PointX-ob2.getX(), ob1PointY-ob2.getY() );
				if (ob1PointDistToCenter2 < ob2.getMaxBoundingRadii()) {
					int angleOb2CenterToPoint = Speed.getDirectionDegBetween(ob2.getX(), ob2.getY(), ob1PointX, ob1PointY);
					int originalAngle = angleOb2CenterToPoint - ob2.getDirection();
					if (originalAngle > 360)
						originalAngle = originalAngle - 360;
					if (originalAngle < 0)
						originalAngle = originalAngle + 360;
				}
				//--draw the key boundary points --//
				canvas.drawLines(this.getBoundingPointsKeyPoints(), paint);
				if (this.getBoundingPointsKeyPoints().length >= 6)
					canvas.drawLines(this.getBoundingPointsKeyPoints(), 2, this.getBoundingPointsKeyPoints().length - 2, paint);
				paint.setColor(Color.CYAN);
				canvas.drawPoints(this.getBoundingPointsKeyPoints(), paint);
				//--Draw the border of getRadii --//
				paint.setColor(Color.YELLOW);
				for (int i = 0; i <= 360; i++) {    //Outputs all the bounding points around the icon. Showing the border that is valid for different angles when testing if points intersect in the checkForCollision method.(for debug)
					int xp = (int) (this.getX() + Math.cos((-90 + this.getDirection() + i) * (Math.PI / 180)) * this.icon.getRadii(i));
					int yp = (int) (this.getY() + Math.sin((-90 + this.getDirection() + i) * (Math.PI / 180)) * this.icon.getRadii(i));
					canvas.drawPoint(xp, yp, paint);
				}
			}
		}
		*/
    //}

	/*public static class Builder extends Buildable<IconRenderer> {
		public IconModule.Builder iconModuleBuilder;
		public Buildable<? extends InternalPoint> cornerPointBuilder;
		public Buildable<? extends InternalPoint> pivotPointBuilder;
		public Buildable<Direction> directionBuilder;

		@Override
		public IconRenderer buildNew() {
			IconRenderer iconRenderer = new IconRenderer();
			iconRenderer.iconModule = iconModuleBuilder.getBuilt();
			iconRenderer.cornerPoint = cornerPointBuilder.getBuilt();//Builder.getBuilt();
			iconRenderer.pivotPoint = pivotPointBuilder.getBuilt(); //Builder.getBuilt();
			iconRenderer.direction = directionBuilder.getBuilt(); //Builder.getBuilt();
			//TODO check if any module is null?
			return iconRenderer;
		}
	}*/
}

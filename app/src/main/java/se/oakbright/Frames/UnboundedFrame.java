package se.oakbright.Frames;

import android.graphics.Point;
import android.util.Log;

public abstract class UnboundedFrame extends Frame{
	private static final String TAG = UnboundedFrame.class.getSimpleName();
	
	private UnboundedFrame(int left, int top, int right, int bottom){
		super(left,top,right,bottom);
	}
	public static UnboundedFrame getUnboundedLeft(int top, int right, int bottom){
		return new UnboundedLeft(top, right, bottom);
	}
	public static UnboundedFrame getUnboundedTop(int left, int right, int bottom){
		return new UnboundedTop(left, right, bottom);
	}
	
	//-- UnboundedLeft --//
	private static final class UnboundedLeft extends UnboundedFrame{
		private UnboundedLeft(int top, int right, int bottom){
			super(Integer.MAX_VALUE, top, right, bottom);
		}
		@Override
		public Point getRandomSpawnPoint(int xMargin, int yMargin){
			if(top+yMargin > bottom-yMargin){
				Log.e(TAG, "unable to create a valid point with the specified margins, throwing IllegalArgumentException");
				throw new IllegalArgumentException();
			}
			int x = this.right - xMargin;
			int y = (int) ( this.top+yMargin + Math.round( Math.random() * ((this.bottom-yMargin) - (this.top+yMargin)) ));
			return new Point(x, y);
		}
	}
	
	//-- UnboundedTop --//
	private static final class UnboundedTop extends UnboundedFrame{
		private UnboundedTop(int left, int right, int bottom){
			super(left, Integer.MAX_VALUE, right, bottom);
		}
		@Override
		public Point getRandomSpawnPoint(int xMargin, int yMargin){
			int x = (int) (this.left + Math.round(Math.random()*(this.right - this.left)));
			int y = this.bottom - yMargin;
			return new Point(x, y);//TODO SAMMA CHECK SOM OVAN 
		}
	}
}



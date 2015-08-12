package se.oakbright.icons;

import java.util.concurrent.ConcurrentHashMap;

import se.oakbright.planetwhite.GameActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Region;
import android.util.Log;

public final class IconFactory{
	private static final String TAG = IconFactory.class.getSimpleName();
	private Resources res;
	private static final Bitmap ERROR_BITMAP = Bitmap.createBitmap(new int[]{-65536,-1,-1,-1,-65536, -1,-65536,-1,-65536,-1, -1,-1,-65536,-1,-1, -1,-65536,-1,-65536,-1, -65536,-1,-1,-1,-65536}, 5, 5, Bitmap.Config.ARGB_4444);

	private ConcurrentHashMap<IconId, InIcon> icons = new ConcurrentHashMap<IconId, InIcon>();
	
	public IconFactory(Resources res) {
		this.res = res;
	}

	InIcon getIcon(IconId iconId){ 
		if(!icons.containsKey(iconId)){
			icons.put(iconId, new Icon(IconFactory.createBitmap(res,iconId), iconId.getWidthDst(), iconId.getHeightDst(), iconId.getBoundingPointsFull(), iconId.getBoundingPointsKey()));
		}
		return icons.get(iconId);
	}
	
	private static Bitmap createBitmap(Resources res, IconId iconId){
		int width = iconId.getWidthDst()/GameActivity.PXL_TO_DST;	//in pxl	//TODO should not be used. the bitmap should maybe be demanded from the battleobject by the surface, which will hold bitmaps for each icon.
		int height = iconId.getHeightDst()/GameActivity.PXL_TO_DST;	//in pxl
		
		// If resulting width or height is less than zero DST, send an error, and output an error bitmap.
		if(width <= 0 || height <= 0)	{
			Log.e(TAG, "In CreateBitmap, resulting Width or height is less than zero. outputs an 50x50 error bitmap");
			return Bitmap.createScaledBitmap(IconFactory.ERROR_BITMAP, 50, 50, true);
		}else{
			try{
			return Bitmap.createScaledBitmap(BitmapFactory.decodeResource(res, iconId.getId()), width, height, true);
			} catch (NullPointerException e){
				Log.e(TAG, "Caught NullPointerException when creating bitmap");
				return Bitmap.createScaledBitmap(IconFactory.ERROR_BITMAP, width, height, true);
			}
		}
	}
}

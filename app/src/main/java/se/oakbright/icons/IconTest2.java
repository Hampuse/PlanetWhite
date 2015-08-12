package se.oakbright.icons;

//import static org.junit.Assert.assertEquals;

//import org.junit.Test;

import android.graphics.Bitmap;
import junit.framework.TestCase;

public class IconTest2 extends TestCase {
private static final Bitmap ERROR_BITMAP = Bitmap.createBitmap(new int[]{-65536,-1,-1,-1,-65536, -1,-65536,-1,-65536,-1, -1,-1,-65536,-1,-1, -1,-65536,-1,-65536,-1, -65536,-1,-1,-1,-65536}, 5, 5, Bitmap.Config.ARGB_4444);
	
/*	@Test
	public void testGetRadii() {
		//FOR this.boundingPointsAngle = new int[] {0, 90, 270, 360};
		Bitmap bitmap = Bitmap.createScaledBitmap(ERROR_BITMAP, 60, 40, true);
		int[] boundingPointsFullCoords = new int[] {bitmap.getWidth()/2,0,		bitmap.getWidth(),bitmap.getHeight()/2, 	0,bitmap.getHeight()/2, 	bitmap.getWidth()/2,0,}; 	// only sets a triangle .

		Icon tester = new Icon(bitmap,boundingPointsFullCoords,null);
		assertEquals("0 angle", bitmap.getHeight()/2, tester.getRadii(0));
		assertEquals("90 angle", bitmap.getWidth()/2, tester.getRadii(90));
		assertEquals("270 angle", bitmap.getWidth()/2, tester.getRadii(270));
		assertEquals("360 angle", bitmap.getHeight()/2, tester.getRadii(360));
		assertEquals("180 angle", bitmap.getWidth()/2, tester.getRadii(180));
		assertEquals("45 angle", 25, tester.getRadii(45));
		assertEquals("135 angle", 30, tester.getRadii(135));
		
		bitmap = Bitmap.createScaledBitmap(ERROR_BITMAP, 2, 6, false);
		boundingPointsFullCoords = new int[] {bitmap.getWidth()/2,0,		bitmap.getWidth(),bitmap.getHeight()/2, 	0,bitmap.getHeight()/2, 	bitmap.getWidth()/2,0,}; 	// only sets a triangle .
		tester = new Icon(bitmap, boundingPointsFullCoords, null);
		assertEquals("0 angle", bitmap.getHeight()/2, tester.getRadii(0));
		assertEquals("90 angle", bitmap.getWidth()/2, tester.getRadii(90));
		assertEquals("270 angle", bitmap.getWidth()/2, tester.getRadii(270));
		assertEquals("360 angle", bitmap.getHeight()/2, tester.getRadii(360));
		assertEquals("180 angle", bitmap.getWidth()/2, tester.getRadii(180));
		assertEquals("45 angle", 2, tester.getRadii(45));*/
		
}


/*
 * <instrumentation android:name="android.test.InstrumentationTestRunner"
    		android:targetPackage="se.oakbright.icons"
   			android:label="test_icons" />
   			*/

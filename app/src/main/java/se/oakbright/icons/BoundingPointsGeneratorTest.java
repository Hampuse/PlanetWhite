package se.oakbright.icons;

import android.graphics.Bitmap;
import android.graphics.Color;
import junit.framework.TestCase;

public class BoundingPointsGeneratorTest extends TestCase {
	public static Bitmap TEST_BITMAP_TRANSP = Bitmap.createBitmap(new int[]{Color.GREEN,Color.TRANSPARENT,Color.TRANSPARENT,		 Color.GREEN,Color.GREEN,Color.GREEN,		 Color.GREEN,Color.GREEN,Color.GREEN}, 3, 3, Bitmap.Config.ARGB_4444);
	public static Bitmap TEST1 = Bitmap.createScaledBitmap(TEST_BITMAP_TRANSP, 100, 100, false);

	public void testBoundingPointsGeneratorBitmap() {
		//fail("Not yet implemented");
	}

	/*
	public void testBoundingPointsGeneratorIntArrayIntInt() {
		int[] boundingPointsFull = new int[]{50,0, 100,0, 20,20, 30,50};
		int middleX = 50;
		int middleY = 50;
		BoundingPointsGeneratorDst testGenerator = new BoundingPointsGeneratorDst(boundingPointsFull, middleX, middleY);
		int[] angles = testGenerator.getAngles();
		int[] radiis = testGenerator.getRadiis();
		assertEquals("array Size, angles", 5, angles.length);
		assertEquals("array size radiis", 5, radiis.length);
		
		assertEquals("angle", 0, angles[0]);
		assertEquals("radii", 50, radiis[0]);
		
		assertEquals("angle", 45, angles[1]);
		assertEquals("radii", 71, radiis[1]);
			
		assertEquals("angle", 360, angles[4]);	//an extra item should be added that is 360.
		assertEquals("radii", 50, radiis[4]);	//the radii should be same as first item (0 degrees)
		
		assertEquals("maxRadii", 71, testGenerator.getMaxRadii());
		
		
		
		//--test2 triangle from IconTest2--//
		Bitmap ERROR_BITMAP = Bitmap.createBitmap(new int[]{-65536,-1,-1,-1,-65536, -1,-65536,-1,-65536,-1, -1,-1,-65536,-1,-1, -1,-65536,-1,-65536,-1, -65536,-1,-1,-1,-65536}, 5, 5, Bitmap.Config.ARGB_4444);
		Bitmap bitmap = Bitmap.createScaledBitmap(ERROR_BITMAP, 60, 40, true);
		middleX = bitmap.getWidth()/2;
		middleY = bitmap.getHeight()/2;
		boundingPointsFull = new int[] {bitmap.getWidth()/2,0,		bitmap.getWidth(),bitmap.getHeight()/2, 	0,bitmap.getHeight()/2, 	bitmap.getWidth()/2,0,}; 	// only sets a triangle .
		testGenerator = new BoundingPointsGenerator(boundingPointsFull, middleX, middleY);
		angles = testGenerator.getAngles();
		radiis = testGenerator.getRadiis();
		
		assertEquals("array Size, angles", 5, angles.length);
		assertEquals("array size radiis", 5, radiis.length);
		
		assertEquals("270angle ", 270, angles[2]);
		assertEquals("270angle radii", 30, radiis[2]);
		assertEquals("over270", 270, angles[3]);		//a value of 270 should have been duplicated since the 3rd element was wrongly input as 0.
		assertEquals("over270 radii", 30, radiis[3]);
		assertEquals("last angle", 360, angles[4]);
		assertEquals("last radii", 20, radiis[4]);

		
	}
	
	public void testGetKeyCoords(){
		//Bitmap TEST_BITMAP = Bitmap.createBitmap(new int[]{-65536,Color.TRANSPARENT,Color.TRANSPARENT,Color.TRANSPARENT, Color.TRANSPARENT,		 1,Color.TRANSPARENT,Color.TRANSPARENT,Color.TRANSPARENT,1,		 -1,-1,-65536,-1,-1, -1,-65536,-1,-65536,-1, -65536,-1,-1,-1,-65536}, 5, 5, Bitmap.Config.ARGB_4444);
		Bitmap bitmap = TEST1;
		float[] testArr = BoundingPointsGenerator.getKeyCoords(bitmap, 10);
		//assertEquals("size of Array", 8, testArr.length);
		//assertEquals("xCoord0", 100, Math.round(testArr[0]));
		//assertEquals

	}
*/
}

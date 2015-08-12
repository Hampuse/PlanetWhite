package se.oakbright.battlecontroller;

//import static org.junit.Assert.assertEquals;
import android.graphics.Point;
import junit.framework.TestCase;

public class SpawnLaneTest extends TestCase {

	public void testFromLeft() {
		SpawnLane.FromLeft testLane = new SpawnLane.FromLeft(0,50,550,new Point(1000,0), new Point(1000,300));
		//assertEquals("radians0to1", (float) (Math.PI/2), (float) testLane.radiansBetweenExit0to1);
		//assertEquals("radians1to0", (float) (3*Math.PI/2), (float) testLane.radiansBetweenExit1to0);

	}

	public void testCreateRandomSpawnPoint() {
		SpawnLane testLane = SpawnLane.fromLeft(0,0,10,new Point(1000,0), new Point(1000,1010));
		
		try{
		testLane.createRandomSpawnPoint(6);
		}catch(IllegalArgumentException e){
			testLane = null;
		}
		assertNull(testLane);
		
		testLane = SpawnLane.fromLeft(0,0,10,new Point(1000,0), new Point(1000,1010));
		testLane.createRandomSpawnPoint(5);
		assertEquals("spawnX", -5, testLane.spawnX);
		assertEquals("spawnY", 5, testLane.spawnY);
		
		//for(int i =)
		
		boolean flag90 = false;
		boolean flag135 = false;
		
		for(int i = 0; i<10000; i++ ){
			testLane.createRandomSpawnPoint(5);
			if(testLane.spawnDirection < 90 || testLane.spawnDirection > 135)	//should theoretically be >90 <135 but this is good enough
				fail("direction not as expected, direction is:"+ testLane.spawnDirection);
			if(testLane.spawnDirection == 90)
				flag90 = true;
			if(testLane.spawnDirection == 135)	//should reach 135
				flag135 = true;
		}
		if(!flag90 )
			fail("Edge value 90 not found in 10000 random runs. This is only an error if it happens all the time.");
		if(!flag135)
			fail("Edge value 134 not found in 10000 random runs. This is only an error if it happens all the time.");

		
		
		
		
		
		//--test the start positioner generation: --//
		testLane = SpawnLane.fromLeft(0,100,1100,new Point(1000,0), new Point(1000,1010));
		boolean flag200 = false;
		boolean flag1000 = false;
		for(int i = 0; i<10000; i++ ){
			testLane.createRandomSpawnPoint(100);
			assertEquals("spawnX",-100, testLane.spawnX );
			if(testLane.spawnY < 200 || testLane.spawnY > 1000)
				fail("spawnY outside valid interval");
			if(testLane.spawnY == 200)
				flag200 = true;
			if(testLane.spawnY == 1000)
				flag1000 = true;
		}
		if(!flag200)
			fail("edge value 200 not found in 10000 random runs, try again, only error if happens all the time");
		if(!flag1000)
			fail("edge value 1000 not found in 10000 random runs, try again, only error if happens all the time");
	
	}
	
	public void testBetweenPoints(){
		SpawnLane testLane = SpawnLane.betweenPoints(new Point(0,0), new Point(0,100), new Point(10,1000), new Point(100, 100));
		if(! (testLane instanceof SpawnLane.FromLeft))
			fail("wrong type");
		
		testLane = SpawnLane.betweenPoints(new Point(100,60), new Point(1000,60), new Point(10,1000), new Point(100, 100));
		if(! (testLane instanceof SpawnLane.FromTop))
			fail("wrong type");
		
		testLane = SpawnLane.betweenPoints(new Point(100,60), new Point(1000,60), new Point(10,0), new Point(100, 0));
		if(! (testLane instanceof SpawnLane.FromBottom))
			fail("wrong type");
		
		testLane = SpawnLane.betweenPoints(new Point(100,60), new Point(100,0), new Point(0,0), new Point(0, 100));
		if(! (testLane instanceof SpawnLane.FromRight))
			fail("wrong type");
		
		try{
			testLane = SpawnLane.betweenPoints(new Point(100,60), new Point(10,5), new Point(0,0), new Point(0, 100));
		}catch(IllegalArgumentException e){
			testLane = null;
		}
		assertNull(testLane);
	}

	
}

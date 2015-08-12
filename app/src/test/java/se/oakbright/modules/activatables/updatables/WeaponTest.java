package se.oakbright.modules.activatables.updatables;

import org.junit.Before;
import org.junit.Test;

import se.oakbright.Buildable;
import se.oakbright.modules.helpers.Direction;
import se.oakbright.modules.internalpoints.Aim;
import se.oakbright.modules.internalpoints.InternalPoint;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by hampuse on 2015-08-06.
 */
public class WeaponTest {
    //Aim aim = mock(Aim.class);
    Weapon.Builder weaponBuilder = new GenericWeapon.Builder();
    Weapon weapon;
    InternalPoint mouthPosition;
    static boolean fireAtCalled = false;

    @Before
    public void setup(){
        setupMouthPosition(100,100);    //the position of the weapon
        setupDirection(90);
        weapon = weaponBuilder.getBuilt();
    }

    private void setupMouthPosition(int x, int y){
        weaponBuilder.mouthPositionBuilder = mock(Buildable.class);
        mouthPosition = mock(InternalPoint.class);
        when(weaponBuilder.mouthPositionBuilder.getBuilt()).thenReturn(mouthPosition);
        when(mouthPosition.x()).thenReturn(x);    //The position of the weapon
        when(mouthPosition.y()).thenReturn(y);
    }

    private void setupDirection(int degrees){
        weaponBuilder.directionBuilder = mock(Direction.Builder.class);
        Direction direction = mock(Direction.class);
        when(weaponBuilder.directionBuilder.getBuilt()).thenReturn(direction);
        when(direction.degrees()).thenReturn(degrees);
    }

    @Test
    public void test_tryFire_aim_straight_ahead(){
        weapon.setAim(200, 100);    //should be straight ahead when weapon position is (100,100) and facing direction 90 degrees
        assertFalse(fireAtCalled);
        weapon.tryFire();
        assertTrue(fireAtCalled);
    }

    private static class GenericWeapon extends Weapon{
        public GenericWeapon(int aimScope, int reloadTime){
            super(aimScope, reloadTime);
        }

        @Override
        protected void fireAt(int xAim, int yAim) {
            fireAtCalled = true;
        }

        private static class Builder extends Weapon.Builder {
            @Override
            protected Weapon getType() {
                return new GenericWeapon(10, 1000);
            }
        }
    }
}

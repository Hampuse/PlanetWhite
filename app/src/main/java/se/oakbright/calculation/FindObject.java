package se.oakbright.calculation;

import android.graphics.Region;

import java.util.ArrayList;

import se.oakbright.battleobjects.BattleObject;
import se.oakbright.planetwhite.BattleTeam;

/**
 * Created by hampuse on 2015-06-27.
 */
public class FindObject {

    public static BattleObject findObjectInside(Region region, ArrayList<BattleObject> battleObjects, BattleTeam exceptTeam){
        for (int i = 0; i < battleObjects.size(); i++) {
            if(battleObjects.get(i).getTeam() != exceptTeam){		// If not the exception object...
                if(objectIsInside(battleObjects.get(i), region)){
                    return battleObjects.get(i);
                }
                int x = battleObjects.get(i).getX();
                int y = battleObjects.get(i).getY();
                if (region.contains(x, y)){		// ...check if the object is inside the region.

                }
            }
        }
        return null;	//If no object was found in the region, return null
    }
    private static boolean objectIsInside(BattleObject object, Region region){
        int x = object.getX();
        int y = object.getY();
        if (region.contains(x, y)){		// ...check if the object is inside the region.
            return true;
        }
        return false;
    }
}

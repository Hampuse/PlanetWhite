package se.oakbright.battlecontroller;

import android.util.Log;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import se.oakbright.battleobjects.Ship;
import se.oakbright.battleobjects.ShipFactory;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.BattleTeam;
import se.oakbright.planetwhite.ServiceProvider;

/**
 * Created by hampuse on 2015-07-19.
 */
public class DebugBaController extends TeamAiController {
    Ship ship1;
    Ship ship2;

    public DebugBaController(BattleModel battleModel,BattleTeam team){
        super(battleModel,team);
    }
    private boolean firstTime = true;

    public static DebugBaController.Blueprint getBlueprint(){
        return new DebugBaController.Blueprint(DebugBaController.class);
    }

    @Override
    public void update(){
        if(firstTime){
            ship1 = createNewShip();
            ship1.setPosition(500000,500000);
            ship1.setDirection(90);
            launchShip(ship1);

            ship2 = createNewShip();
            ship2.setPosition(1000000, 500000);
            ship2.setDirection(270);
            launchShip(ship2);

            firstTime = false;
        }
    }

    /*private Ship createNewShip(){
        ShipFactory shipFactory = new ShipFactory();
        Ship.Builder shipBuilder = shipFactory.getStandard();
        shipBuilder.team = this.team;
        Ship ship = shipBuilder.getBuilt();
        return ship;
    }*/

    private Ship createNewShip(){
        Ship ship = ShipResource.getNewShip(team);
        return ship;
    }

    private void launchShip(Ship ship){
        ship.activate();
        ship.commandGetReadyToLaunch();
        ship.commandLaunch();
    }
/*    public static class Blueprint implements Serializable {
        private Class controllerClass;

        public Blueprint(Class controllerClass){
            //super(controllerClass);
            this.controllerClass = controllerClass;
        }

        public DebugBaController create(	BattleModel battleModel, BattleTeam team){
            //return new bpClass.g (battleModel, xPos, yPos, movingFrame, team, recentlyEjectedFrom);
            //Constructor constructor;
            try {
                Constructor constructor = this.controllerClass.getConstructor(new Class[]{BattleModel.class, BattleTeam.class});
                try {
                    return (DebugBaController)constructor.newInstance(battleModel, team);
                } catch (IllegalArgumentException e) {
                    Log.e("TAG", "error when using reflection constructor");
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    Log.e("TAG","error when using reflection constructor");
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    Log.e("TAG","error when using reflection constructor");
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    Log.e("TAG","error when using reflection constructor");
                    e.printStackTrace();
                }
            } catch (NoSuchMethodException e) {
                Log.e("TAG","The constructor seem to not exist. reflection constructor");
                e.printStackTrace();
            }
            Log.e("TAG","unable to create object of class, returning null. Possibly the class is not subclass of TeamAiController, or it's an abstract class ");
            return null;
        }
    }
*/
}

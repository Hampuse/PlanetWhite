package se.oakbright.battlecontroller;

import se.oakbright.battleobjects.ship.ShipBlueprint;
import se.oakbright.battleobjects.BattleObject;
import se.oakbright.battleobjects.ship.ShipCommands;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.BattleTeam;

/**
 * Created by hampuse on 2015-07-19.
 */
public class DebugBaController extends TeamAiController {
    BattleObject<ShipCommands> ship1;
    BattleObject<ShipCommands> ship2;
    //BattleTeam team;

    public DebugBaController(BattleModel battleModel,BattleTeam team){
        super(battleModel,team);
    }
    private boolean firstTime = true;

    public static Blueprint getBlueprint(){
        return new Blueprint(DebugBaController.class);
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
        //COMMENTED AWAY TO TRY RESOURC
        // new comment to test commit
    }*/

 /*   private Ship createNewShip(){
        BattleObject.TypeBuilder<Ship, Ship.Builder> shipBuildable = new BattleObject.TypeBuilder<Ship, Ship.>(new ShipResource(team));
        Ship ship = shipBuildable.getBuilt();
        //Ship ship = ShipResource.getNewShip(team);
        return null; //ship;
    }*/

    private BattleObject<ShipCommands> createNewShip(){
        se.oakbright.Blueprint<BattleObject<ShipCommands>> shipBlueprint = new ShipBlueprint(this.team);
        return shipBlueprint.getBuilt();
    }

    private void launchShip(BattleObject<ShipCommands> ship){
        ship.command().activate();
        ship.command().getReadyToLaunch();
        ship.command().launch();
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

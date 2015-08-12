package se.oakbright.planetwhite;

import se.oakbright.Frames.Frame;

/**
 * Created by hampuse on 2015-07-14.
 */
public final class ServiceProvider {
    private static ServiceProvider s = new ServiceProvider(); //singleton
    private BattleModel battleModel;
    private BattleClock battleClock;

   /* private ServiceProvider(BattleModel battleModel){
        this.battleModel = battleModel;
    }
    public static void create(BattleModel battleModel){
        //if(serviceProvider == null){
            serviceProvider = new ServiceProvider(battleModel);
        //} else {
            //TODO SHOULD IT throw this? causes error when running app twice: throw new UnsupportedOperationException("Trying to create serviceProvider more than once");
       // }
    }*/

    public static void reset(){
        s.battleModel = null;
        s.battleClock = null;
    }

    public static void setBattleModel(BattleModel battleModel){
        s.battleModel = battleModel;
    }

    public static void setBattleClock(BattleClock battleClock){
        s.battleClock = battleClock;
    }

    public static BattleModel getBattleModel(){
        return s.battleModel;
    }

    public static Frame getMovingFrame(){
        return s.battleModel.trackFrame;
    }

    public static int getMicrosPerUpdate(){
        return s.battleClock.getMicroSecondsPerUpdate();
    }
}

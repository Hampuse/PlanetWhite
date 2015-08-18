package se.oakbright.modules.helpers;

import android.util.Log;

import java.util.LinkedList;
import java.util.List;


import se.oakbright.battleobjects.BattleObject;
import se.oakbright.modules.Module;
import se.oakbright.modules.ModuleObserver;
import se.oakbright.modules.activatables.Activatable;
import se.oakbright.resource.Resource;

/**
 * Created by hampuse on 2015-06-26.
 */
public class Health extends Module{
    private int maxHp;
    private int hp;
    private List<ModuleObserver<Health>> outOfHpObservers = new LinkedList<ModuleObserver<Health>>();
    //private Activatable host;

    public Health(Resource r){
        super(r);
        this.maxHp = 1;
        this.hp = 1;
    }

    public void setFullHp(int hp){
        this.maxHp = hp;
        this.hp = hp;
    }

    public void tryDecreaseHp(int damage){
        if ( this.hp != Integer.MAX_VALUE){	// If the object has maximum value hp, it is treated as infinite, and the object should not be damaged.
            this.hp -= damage;
            if( this.hp <= 0 ){
                notifyOutOfHpObservers();
            }
        }
    }

    public int getPercentOfHealthLeft() {
        return 100*this.hp/this.maxHp;
    }

    /*public void setHost(Activatable host){
        this.host = host;
    }*/

    @Override
    public void activate() {
    }

    @Override
    public void deactivate() {
    }

    public void addOutOfHpObserver(ModuleObserver<Health> observer){
        this.outOfHpObservers.add(observer);
    }

    private void notifyOutOfHpObservers(){
        for(ModuleObserver observer: outOfHpObservers){
            observer.notify(this);
        }
    }

    //TODO:
    /*
    public static class Builder extends Buildable<Health>{
        //public Buildable<? extends BattleObject> hostBuilder;
        public int startHp;
        @Override
        protected Health buildNew() {
            Health health = new Health(startHp);  //TODO be able to change
           // health.host = hostBuilder.getBuilt();
            return health;
        }
    }*/


}

package se.oakbright.resource;

import se.oakbright.battleobjects.statemachine.CommandReceiverHolder;
import se.oakbright.icons.InIcon;
import se.oakbright.modules.Module;
import se.oakbright.modules.activatables.updatables.Mover;
import se.oakbright.modules.helpers.Bounding;
import se.oakbright.modules.helpers.Direction;
import se.oakbright.modules.helpers.Health;
import se.oakbright.modules.helpers.IconModule;
import se.oakbright.modules.helpers.OrientationMatrix;
import se.oakbright.modules.helpers.Positioner;
import se.oakbright.modules.helpers.Shape;
import se.oakbright.modules.internalpoints.InternalPoint;
import se.oakbright.planetwhite.BattleTeam;

/**
 * Created by hampuse on 2015-08-17.
 */
public final class Key<T> {
    //BASIC KEYS
    public static final Key<Positioner> POSITIONER = new Key<Positioner>("POSITIONER"); // Is used to set the position of the BO
    public static final Key<Shape> SHAPE = new Key<Shape>("SHAPE");
    public static final Key<OrientationMatrix> ORIENTATION_MATRIX = new Key<OrientationMatrix>("ORIENTATION_MATRIX");
    public static final Key<CommandReceiverHolder> COMMAND_HANDLER = new Key<CommandReceiverHolder>("COMMAND_HANDLER");
    public static final Key<Health> HEALTH = new Key<Health>("HEALTH");
    public static final Key<Direction> DIRECTION = new Key<Direction>("DIRECTION");
    public static final Key<InternalPoint> MIDDLE_POINT = new Key<InternalPoint>("MIDDLE_POINT");   // returns the middlepoint of the BO
    public static final Key<InternalPoint> PIVOT_POINT = new Key<InternalPoint>("PIVOT_POINT"); // ?CHECK returns the point which a Module rotates around
    public static final Key<InternalPoint> CORNER_POINT = new Key<InternalPoint>("CORNER_POINT");   //Is always the corner point of the BO
    public static final Key<Bounding> BOUNDING = new Key<Bounding>("BOUNDING");
    public static final Key<InIcon> ICON = new Key<InIcon>("ICON");
    public static final Key<IconModule> ICON_MODULE = new Key<IconModule>("ICON_MODULE");
    public static final Key<BattleTeam> TEAM = new Key<BattleTeam>("TEAM");

    public static final Key<Mover> MOVER = new Key<Mover>("MOVER");

    public static final Key<Module> MODULE_IN_INIT = new Key<Module>("MODULE_IN_INIT");
    public static final Key<Module> MODULE_IN_HIDDEN = new Key<Module>("MODULE_IN_HIDDEN");
    public static final Key<Module> MODULE_IN_READY_TO_LAUNCH = new Key<Module>("MODULE_IN_READY_TO_LAUNCH");
    public static final Key<Module> MODULE_IN_OUT_THERE = new Key<Module>("MODULE_IN_OUT_THERE");


    final private String nameForToString;

    private Key(String nameForToString){
        this.nameForToString = nameForToString;
    }

    public String toString(){
        return this.nameForToString;
    }
   /* POSITIONER(Positioner.class),
    HEALTH(Health.class);
    //COMMAND_HANDLER;

    private final Class<T> classe;
    Keys(Class<?> classe){
        this.classe = classe;
    }

    public Class getClass(){

    }*/
}

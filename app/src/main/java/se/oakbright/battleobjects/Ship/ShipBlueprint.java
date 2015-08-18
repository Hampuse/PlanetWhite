package se.oakbright.battleobjects.ship;

import se.oakbright.Blueprint;
import se.oakbright.battleobjects.BattleObject;
import se.oakbright.icons.IconCreater;
import se.oakbright.icons.IconId;
import se.oakbright.icons.IconIdFactory;
import se.oakbright.modules.activatables.renderables.IconRenderer;
import se.oakbright.modules.activatables.updatables.CollisionModule;
import se.oakbright.modules.activatables.updatables.Mover;
import se.oakbright.modules.helpers.Health;
import se.oakbright.modules.helpers.IconModule;
import se.oakbright.modules.helpers.OrientationMatrix;
import se.oakbright.modules.internalpoints.CornerPoint;
import se.oakbright.modules.internalpoints.MiddlePoint;
import se.oakbright.planetwhite.BattleTeam;
import se.oakbright.planetwhite.R;
import se.oakbright.resource.BasicResource;
import se.oakbright.resource.Resource;

import static se.oakbright.resource.Key.*;

/**
 * Created by hampuse on 2015-08-14.
 */
public class ShipBlueprint extends Blueprint<BattleObject<ShipCommands>> {
    Resource shipResource = new Resource();

    public ShipBlueprint(BattleTeam team) {
        shipResource.add(TEAM, team);
        shipResource.add(BasicResource.withEntriesInitializedWith(shipResource));
        shipResource.add(ICON, new IconCreaterImpl());
        shipResource.add(ICON_MODULE, IconModule.class, shipResource);
        shipResource.add(PIVOT_POINT, MiddlePoint.class, shipResource);
        shipResource.add(CORNER_POINT, CornerPoint.class, shipResource);
        shipResource.add(MIDDLE_POINT, MiddlePoint.class, shipResource);
        shipResource.add(ORIENTATION_MATRIX, OrientationMatrix.class, shipResource);
        shipResource.add(COMMAND_HANDLER, new ShipStateMachineBlueprint(stateMachineResources()));
        Health health = shipResource.getThe(HEALTH);
        health.setFullHp(2);
    }

    Resource stateMachineResources() {
        Resource r = new Resource();
        r.add(shipResource);

        r.add(MODULE_IN_READY_TO_LAUNCH, IconRenderer.class, r);    //TODO now we duplicate different renderers to each states...

        r.add(MODULE_IN_OUT_THERE, IconRenderer.class, r);
        r.add(MODULE_IN_OUT_THERE, Mover.class, r);
        r.add(MODULE_IN_OUT_THERE, CollisionModule.class, r);
        return r;
    }

    @Override
    protected BattleObject<ShipCommands> buildNew() {
        return new BattleObject<ShipCommands>(shipResource);
    }

    private static class IconCreaterImpl extends IconCreater {
        @Override
        public IconId getIconId() {
            return IconIdFactory.valueOf(R.drawable.bollengreen, 200000, 250000);
        }
    }
}


















    /*
    public class ResourceImpl extends BattleObject.ResourceImpl<ShipCommands> implements IconRenderer.Resource{
        StateMachine<ShipCommands> stateMachine;

        @Override
        public CommandReceiverHolder<ShipCommands> getCommandHandler() {
            if (stateMachine == null) {
                createStateMachine();
            }
            return stateMachine;
        }

        private void createStateMachine(){

            ShipStateMachineBlueprint stateMachineBlueprints = new ShipStateMachineBlueprint();

            // ACTIVE MODULES
            stateMachineBlueprints.readyToLaunch.addActiveModule(getIconRenderer());
            /*outThere.addActiveModule(iconRendererBuilder);
            outThere.addActiveModule(moverBuilder);
            outThere.addActiveModule(pathModuleBuilder);
            outThere.addActiveModule(collisionModuleBuilder);
            outThere.addActiveModule(weaponBuilder);*/
/*
            stateMachine = stateMachineBlueprints.getBuilt();
        }

        public IconRenderer getIconRenderer(){
            return new IconRenderer(this);
        }

        @Override
        public InternalPoint getPivotPoint() {
            return null;
        }

        @Override
        public IconModule getIconModule() {
            return null;
        }
    }*/
//}

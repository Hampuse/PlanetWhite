package se.oakbright.battleobjects.Ship;

import se.oakbright.Blueprints;
import se.oakbright.CommandReceiverHolder;
import se.oakbright.battleobjects.BattleObject;
import se.oakbright.battleobjects.ShipCommands;
import se.oakbright.battleobjects.statemachine.StateMachine;
import se.oakbright.modules.activatables.renderables.IconRenderer;
import se.oakbright.modules.helpers.IconModule;
import se.oakbright.modules.internalpoints.InternalPoint;

/**
 * Created by hampuse on 2015-08-14.
 */
public class ShipBlueprints extends Blueprints<BattleObject<ShipCommands>> {
    ResourceImpl shipResource = new ResourceImpl();

    @Override
    protected BattleObject<ShipCommands> buildNew() {
        return new BattleObject(shipResource);
    }


    public class ResourceImpl extends BattleObject.ResourceImpl<ShipCommands> implements IconRenderer.Resource{
        //StateMachineR stateMachineR;
        StateMachine<ShipCommands> stateMachine;

        @Override
        public CommandReceiverHolder<ShipCommands> getCommandHandler() {
            if (stateMachine == null) {
                createStateMachine();
            }
            return stateMachine;
        }

        private void createStateMachine(){

            ShipStateMachineBlueprints stateMachineBlueprints = new ShipStateMachineBlueprints();

            // ACTIVE MODULES
            stateMachineBlueprints.readyToLaunch.addActiveModule(getIconRenderer());
            /*outThere.addActiveModule(iconRendererBuilder);
            outThere.addActiveModule(moverBuilder);
            outThere.addActiveModule(pathModuleBuilder);
            outThere.addActiveModule(collisionModuleBuilder);
            outThere.addActiveModule(weaponBuilder);*/

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
    }
}

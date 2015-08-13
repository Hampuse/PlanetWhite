package se.oakbright.battleobjects;

import se.oakbright.Frames.Frame;
import se.oakbright.RuntimeTests;
import se.oakbright.battleobjects.statemachine.ShipCommandHandler;
import se.oakbright.modules.activatables.renderables.DebugInternalPointRenderer;
import se.oakbright.modules.activatables.updatables.CollisionModule;
import se.oakbright.modules.activatables.updatables.Mover;
import se.oakbright.modules.activatables.updatables.PathModule;
import se.oakbright.modules.activatables.updatables.Weapon;
import se.oakbright.modules.helpers.Bounding;
import se.oakbright.modules.helpers.Positioner;
import se.oakbright.modules.helpers.Speed;
import se.oakbright.modules.internalpoints.CornerPoint;
import se.oakbright.modules.internalpoints.MiddlePoint;
import se.oakbright.modules.activatables.renderables.IconRenderer;
import se.oakbright.battleobjects.statemachine.ShipStateMachineBuilder;
import se.oakbright.icons.IconCreater;
import se.oakbright.icons.IconId;
import se.oakbright.icons.IconIdFactory;
import se.oakbright.modules.helpers.IconModule;
import se.oakbright.modules.helpers.OrientationMatrix;
import se.oakbright.modules.helpers.Shape;
import se.oakbright.planetwhite.R;
import se.oakbright.resources.ShipFactoryResource;
import se.oakbright.weapons1.MissileLauncher;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by hampuse on 2015-07-13.
 */
public class ShipFactory {
    Ship.Builder<ShipCommandHandler> shipBuilder = new Ship.Builder<ShipCommandHandler>();
    IconCreater iconCreater = new ShipFactory.IconCreaterImpl();
    IconModule.Builder iconModuleBuilder = new IconModule.Builder();
    CornerPoint.Builder cornerPointBuilder = new CornerPoint.Builder();
    MiddlePoint.Builder middlePointBuilder = new MiddlePoint.Builder();
    Shape.Builder shapeBuilder = new Shape.Builder();
    IconRenderer.Builder iconRendererBuilder = new IconRenderer.Builder();
    OrientationMatrix.Builder orientationMatrixBuilder = new OrientationMatrix.Builder();
    Mover.Builder moverBuilder = new Mover.Builder();
    PathModule.Builder pathModuleBuilder = new PathModule.Builder();
    CollisionModule.Builder collisionModuleBuilder = new CollisionModule.Builder();
    Bounding.Builder boundingBuilder = new Bounding.Builder();
    Weapon.Builder weaponBuilder = new MissileLauncher.Builder();
    ShipStateMachineBuilder stateMachineBuilder = new ShipStateMachineBuilder();

    public Ship.Builder<ShipCommandHandler> getStandard(){
        //ShipFactoryResource r = new ShipFactoryResource();
        //r.positionerBuilder = shipBuilder.positionerBuilder;
        //r.moverBuilder = new Mover.Builder(r);
        //r.add("positioner",positionerBuilder);

        iconModuleBuilder.iconCreater = new ShipFactory.IconCreaterImpl();
        setupMover(moverBuilder);
        setupShape(shapeBuilder);
        setupOrientationMatrixBuilder();
        setupCornerPoint();
        setupMiddlePoint();
        setupIconRenderer();
        setupBounding();
        setupCollisionModule();

        setupStateMachine();
        setupPathModule();
        setupWeapon();
        setupDebugStuff();

        return shipBuilder;
    }

    private void setupMover(Mover.Builder moverBuilder){
        moverBuilder.goVelocity = 10000;
        moverBuilder.positionerBuilder = shipBuilder.positionerBuilder;
        moverBuilder.pathModuleBuilder = pathModuleBuilder;
        moverBuilder.directionBuilder = shipBuilder.directionBuilder;
    }

    private void setupShape(Shape.Builder shapeBuilder){
        shapeBuilder.positionerBuilder = shipBuilder.positionerBuilder;
        shapeBuilder.iconCreater = iconCreater;
        shipBuilder.shapeBuilder = shapeBuilder;
    }

    private void setupOrientationMatrixBuilder(){
        orientationMatrixBuilder.cornerPointBuilder = cornerPointBuilder;
        orientationMatrixBuilder.pivotPointBuilder = middlePointBuilder;
        orientationMatrixBuilder.directionBuilder = shipBuilder.directionBuilder;
    }
    private void setupCornerPoint(){
        cornerPointBuilder.positionerBuilder = shipBuilder.positionerBuilder;
        cornerPointBuilder.shapeBuilder = shipBuilder.shapeBuilder;
    }

    private void setupMiddlePoint(){
        middlePointBuilder.positionerBuilder = shipBuilder.positionerBuilder;
        RuntimeTests.testForNull(middlePointBuilder.positionerBuilder,"positionerBuilder");
    }

    private void setupIconRenderer(){
        iconRendererBuilder.iconModuleBuilder = iconModuleBuilder;
        iconRendererBuilder.cornerPointBuilder = cornerPointBuilder;
        iconRendererBuilder.pivotPointBuilder = middlePointBuilder;
        iconRendererBuilder.directionBuilder = shipBuilder.directionBuilder;
    }

    private void setupStateMachine(){
        stateMachineBuilder.readyToLaunch.addActiveModule(iconRendererBuilder);
        stateMachineBuilder.outThere.addActiveModule(iconRendererBuilder);
        stateMachineBuilder.outThere.addActiveModule(moverBuilder);
        stateMachineBuilder.outThere.addActiveModule(pathModuleBuilder);
        stateMachineBuilder.outThere.addActiveModule(collisionModuleBuilder);
        stateMachineBuilder.outThere.addActiveModule(weaponBuilder);
        shipBuilder.stateMachineBuilder = stateMachineBuilder;
    }

    private void setupPathModule(){
        pathModuleBuilder.positionerBuilder = shipBuilder.positionerBuilder;
        pathModuleBuilder.moverBuilder = moverBuilder;
    }

    private void setupBounding(){
        boundingBuilder.orientationMatrixBuilder = orientationMatrixBuilder;
        boundingBuilder.shapeBuilder = shapeBuilder;
    }

    private void setupCollisionModule(){
        collisionModuleBuilder.onCollisionGiveDamage = 10;
        collisionModuleBuilder.healthBuilder = shipBuilder.healthBuilder;
        collisionModuleBuilder.positionerBuilder = shipBuilder.positionerBuilder;
        collisionModuleBuilder.shapeBuilder = shipBuilder.shapeBuilder;
        collisionModuleBuilder.middlePointBuilder = middlePointBuilder;
        collisionModuleBuilder.directionBuilder = shipBuilder.directionBuilder;
        collisionModuleBuilder.boundingBuilder = boundingBuilder;
    }

    private void setupWeapon(){
        weaponBuilder.mouthPositionBuilder = middlePointBuilder;
        weaponBuilder.directionBuilder = shipBuilder.directionBuilder;
    }

    private void setupDebugStuff(){
        DebugInternalPointRenderer.Builder middlePointRendererBuilder =
                new DebugInternalPointRenderer.Builder(middlePointBuilder);
        stateMachineBuilder.outThere.addActiveModule(middlePointRendererBuilder);

    }

    private static class IconCreaterImpl extends IconCreater {
        @Override
        public IconId getIconId() {
            return IconIdFactory.valueOf(R.drawable.bollengreen, 200000, 250000);
        }
    }

}

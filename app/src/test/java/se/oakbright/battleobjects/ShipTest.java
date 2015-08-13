package se.oakbright.battleobjects;

import org.junit.Before;
import org.junit.Test;

import se.oakbright.Buildable;
import se.oakbright.battleobjects.statemachine.ShipCommandHandler;
import se.oakbright.battleobjects.statemachine.ShipStateMachineBuilder;
import se.oakbright.modules.Module;
import se.oakbright.modules.helpers.IconModule;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.BattleTeam;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertSame;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by hampuse on 2015-07-12.
 */
public class ShipTest {
    Ship ship;
    Buildable<Module> moduleInHiddenBuilder, moduleInReadyToLaunchBuilder, moduleInOutThereBuilder;
    Module moduleInHidden, moduleInReadyToLaunch, moduleInOutThere;

    IconModule.Builder IconModuleBuilder = mock(IconModule.Builder.class);
    BattleModel battleModel;
    BattleTeam team;
    //@Mock IconId iconId;

    @Before
    public void setup(){
        team = mock(BattleTeam.class);
        IconModule iconModule = mock(IconModule.class);

        Ship.Builder<ShipCommandHandler> shipBuilder = ShipFactoryTest.getShipBuilderFake();

        moduleInHiddenBuilder = mock(Buildable.class);
        moduleInReadyToLaunchBuilder = mock(Buildable.class);
        moduleInOutThereBuilder = mock(Buildable.class);
        moduleInHidden = mock(Module.class);
        moduleInReadyToLaunch = mock(Module.class);
        moduleInOutThere = mock(Module.class);

        when(moduleInHiddenBuilder.getBuilt()).thenReturn(moduleInHidden);
        when(moduleInReadyToLaunchBuilder.getBuilt()).thenReturn(moduleInReadyToLaunch);
        when(moduleInOutThereBuilder.getBuilt()).thenReturn(moduleInOutThere);

        ShipStateMachineBuilder stateMachineBuilder = new ShipStateMachineBuilder();
        stateMachineBuilder.hidden.addActiveModule(moduleInHiddenBuilder);
        stateMachineBuilder.readyToLaunch.addActiveModule(moduleInReadyToLaunchBuilder);
        stateMachineBuilder.outThere.addActiveModule(moduleInOutThereBuilder);
        shipBuilder.stateMachineBuilder = stateMachineBuilder;

        shipBuilder.team = team;
        ship = shipBuilder.getBuilt();
    }

    @Test
    public void verify_that_states_activeModules_are_correct(){
        //starts in init State, no modules should have been activated
        verifyZeroInteractions(moduleInHidden);
        verifyZeroInteractions(moduleInOutThere);
        verifyZeroInteractions(moduleInReadyToLaunch);

        ship.activate();    //should enter hidden
        verify(moduleInHidden).activate();
        verifyZeroInteractions(moduleInOutThere);
        verifyZeroInteractions(moduleInReadyToLaunch);

        ship.commandGetReadyToLaunch();     //enter readyToLaunch
        verify(moduleInHidden).deactivate();
        verify(moduleInReadyToLaunch).activate();
        verifyZeroInteractions(moduleInOutThere);

        ship.commandLaunch();   //enter outThere
        verifyZeroInteractions(moduleInHidden);
        verify(moduleInReadyToLaunch).deactivate();
        verify(moduleInOutThere).activate();
    }

    @Test
    public void verify_that_iconModule_buildNew(){
        verify(moduleInHiddenBuilder).getBuilt();
        verify(moduleInOutThereBuilder).getBuilt();
        verify(moduleInReadyToLaunchBuilder).getBuilt();

    }
}

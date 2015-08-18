package se.oakbright.resource;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import se.oakbright.Blueprint;
import se.oakbright.modules.Module;
import se.oakbright.modules.helpers.Direction;
import se.oakbright.modules.helpers.Positioner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by hampuse on 2015-08-18.
 */
public class ResourceTest {
    Resource r;
    Blueprint<Positioner> positionerBlueprint = mock(Blueprint.class);
    Positioner positioner = mock(Positioner.class);

    @Before
    public void setup(){
        r = new Resource();

        //Add a mocked positioner just to have a module to get:
        Blueprint<Positioner> positionerBlueprint = mock(Blueprint.class);
        Positioner positioner = mock(Positioner.class);
        when(positionerBlueprint.getBuilt()).thenReturn(positioner);
        r.add(Key.POSITIONER, positionerBlueprint);

        //Add three mocked MODULE_IN_INIT:, where two of them refer to same
        Blueprint<Module> moduleBlueprint = mock(Blueprint.class);
        Module module = mock(Module.class);
        when(moduleBlueprint.getBuilt()).thenReturn(module);
        r.add(Key.MODULE_IN_INIT, moduleBlueprint);
        r.add(Key.MODULE_IN_INIT, moduleBlueprint);
        r.add(Key.MODULE_IN_INIT, positionerBlueprint);
    }

    @Test
    public void test_getThe_should_throw_exception_when_doesnt_find(){
        try {
            r.getThe(Key.DIRECTION);
            fail(); // no exception was thrown if reached this
        }catch (RuntimeException e){
        }
    }

    @Test
    public void test_getThe_should_throw_exception_when_several_options_exist(){
        try{
            r.getThe(Key.MODULE_IN_INIT);
            fail();
        }catch(RuntimeException e){        //TODO MORE SPECIFIC
        }
    }

    @Test
    public void test_getThe_should_find(){
        r.getThe(Key.POSITIONER);
    }

    @Test
    public void test_getAllIfAny_should_not_find() {
        List<Direction> list = r.getAllIfAny(Key.DIRECTION);
        assertTrue(list.isEmpty());
    }

    @Test
    public void test_getAllIfAny_should_find(){
        // getAllIfAny should find the three Modules tagged with MODULE_IN_INIT
        List<Module> list = r.getAllIfAny(Key.MODULE_IN_INIT);
        assertEquals(3, list.size());
    }

}

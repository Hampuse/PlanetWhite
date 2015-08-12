package se.oakbright.modules;

import se.oakbright.Buildable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by hampuse on 2015-07-24.
 */
public class ModuleTest {
    public static void setupFakeModuleBuilderWithModule(Buildable<Module> builder, Module module){
        builder = mock(Buildable.class);
        module = mock(Module.class);
        when(builder.getBuilt()).thenReturn(module);
    }
}

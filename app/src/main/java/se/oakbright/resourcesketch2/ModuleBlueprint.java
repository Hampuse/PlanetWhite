package se.oakbright.resourcesketch2;

import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import se.oakbright.Blueprints;

/**
 * Created by hampuse on 2015-08-16.
 */
public class ModuleBlueprint<T> extends Blueprints<Module> {
    final Class<? extends Module> objectClass;
    final Resource resource;

    public ModuleBlueprint(Class<? extends Module> objectClass,Resource resource) {
        this.objectClass = objectClass;
        this.resource = resource;
    }

    @Override
    public Module buildNew() {
        Module module;
        try {
            Constructor<? extends Module> constructor = objectClass.getConstructor(new Class[]{Resource.class});
            try {
                module = constructor.newInstance(resource);
                return module;
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
        Log.e("TAG","unable to create object of class, returning null. Possibly the class is not subclass of Module, or it's an abstract class ");
        return null;
    }
}

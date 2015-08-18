package se.oakbright.modules;

import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import se.oakbright.Blueprint;
import se.oakbright.battleobjects.BattleObject;
import se.oakbright.resource.Resource;

/**
 * Created by hampuse on 2015-08-16.
 */
public class ModuleBlueprint<T extends Module> extends Blueprint<T> {
    Class<? extends T> objectClass;
    final Resource resource;

    public ModuleBlueprint(Class<? extends T> objectClass,Resource resource) {
        this.objectClass = objectClass;
        this.resource = resource;
    }

    @Override
    public String toString(){
      return "Blueprint<"+ objectClass + ">";
    }

    @Override
    public T buildNew() {

        T module;
        try {
            //Depends on that all extending Module provide a constructor with Resource as only parameter.
            Class<?> klass = Resource.class;
            Class<?> classes[] = new Class[] { klass};
            Constructor<? extends T> constructor = objectClass.getConstructor(classes);
            try {
                module = constructor.newInstance(resource);
                return module;

            } catch (IllegalArgumentException e) {
                Log.e("TAG", "reflection error: IllegalArgumentException");
                e.printStackTrace();
            } catch (InstantiationException e) {
                Log.e("TAG","reflection error: InstantiationException");
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                Log.e("TAG","reflection error: IllegalAccesException");
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                Log.e("TAG","reflection error: InvocationTargetException");
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            Log.e("TAG","The constructor seem to not exist. reflection constructor");
            e.printStackTrace();
        }
        Log.e("TAG","unable to create object of class. Possibly the class is not subclass of Module, or it's an abstract class ");
        throw new NullPointerException(""+ this + " failed to build with recursion.");
        //return null;

    }
}

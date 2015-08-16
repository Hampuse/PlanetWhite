package se.oakbright.resourcesketch2;

import java.util.HashMap;
import java.util.Map;

import se.oakbright.Blueprints;

/**
 * Created by hampuse on 2015-08-16.
 */
public class Resource {
    private Map<String, Blueprints<Module>> builders = new HashMap<String, Blueprints<Module>>();

    private Resource(){
    }

    public static Resource empty(){
        return new Resource();
    }

    public <T> T getResource(String resourceRequest){
        Blueprints<Module> blueprint = builders.get(resourceRequest);
        Object ob = blueprint.getBuilt();
        T t = (T) ob;
        return t;
    }

    public void add(String key, Blueprints<Module> blueprint){
        builders.put(key, blueprint);
    }

    public void add(String key, Class<? extends Module> objectClass,Resource resource){
        builders.put(key, new ModuleBlueprint(objectClass, resource));
    }

    public void add(Resource resource){
        //TODO
    }
}

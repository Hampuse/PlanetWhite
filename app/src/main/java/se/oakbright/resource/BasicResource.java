package se.oakbright.resource;

import se.oakbright.modules.helpers.Bounding;
import se.oakbright.modules.helpers.Direction;
import se.oakbright.modules.helpers.Health;
import se.oakbright.modules.helpers.Positioner;
import se.oakbright.modules.helpers.Shape;
import static se.oakbright.resource.Key.*;
/**
 * Created by hampuse on 2015-08-16.
 */
public final class BasicResource {

    private BasicResource(){
    }

    public static Resource withEntriesInitializedWith(Resource resourceForBlueprints){
        Resource r = new Resource();
        r.add(SHAPE, Shape.class, resourceForBlueprints);
        r.add(HEALTH, Health.class, resourceForBlueprints);
        r.add(POSITIONER, Positioner.class, resourceForBlueprints);
        r.add(DIRECTION, Direction.class, resourceForBlueprints);
        r.add(BOUNDING, Bounding.class, resourceForBlueprints);
        return r;
    }

    /*public static Resource getNew(){
        final Resource r = Resource.empty();



        return r;
    }*/
}

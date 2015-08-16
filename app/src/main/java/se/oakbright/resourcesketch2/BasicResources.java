package se.oakbright.resourcesketch2;

/**
 * Created by hampuse on 2015-08-16.
 */
public final class BasicResources  {

    private BasicResources(){}

    public static Resource getNew(){
        final Resource r = Resource.empty();

        r.add("bModule", BModule.class, r);

        r.add("cModule", CModule.class, r);

        return r;
    }
}

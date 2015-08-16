package se.oakbright.resourcesketch2;

/**
 * Created by hampuse on 2015-08-16.
 */
public class Bob<I> {
    AModule aModule1;

    public Bob(Resource r){
        aModule1 = r.getResource("aModule");
    }
}

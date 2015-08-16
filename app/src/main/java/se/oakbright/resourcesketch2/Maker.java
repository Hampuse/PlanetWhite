package se.oakbright.resourcesketch2;

/**
 * Created by hampuse on 2015-08-16.
 */
public abstract class Maker<I> {
    protected Resource primaryResource = Resource.empty();
    public abstract Bob<I> make();

}

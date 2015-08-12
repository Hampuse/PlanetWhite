package se.oakbright.resources;

import se.oakbright.Buildable;
import se.oakbright.battleobjects.BuilderResource;
import se.oakbright.modules.helpers.Positioner;

/**
 * Created by hampuse on 2015-07-25.
 */
public interface MoverBuilderResource extends BuilderResource {
    public Buildable<Positioner> getPositionerBuilder();
}

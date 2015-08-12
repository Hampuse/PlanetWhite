package se.oakbright.resources;

import se.oakbright.Buildable;
import se.oakbright.modules.activatables.updatables.Mover;
import se.oakbright.modules.helpers.Positioner;

/**
 * Created by hampuse on 2015-07-25.
 */
public class ShipFactoryResource implements MoverBuilderResource {
    public Buildable<Positioner> positionerBuilder;
    public Mover.Builder moverBuilder;

    @Override
    public Buildable<Positioner> getPositionerBuilder() {
        return positionerBuilder;
    }
}

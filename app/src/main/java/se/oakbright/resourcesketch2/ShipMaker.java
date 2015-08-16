package se.oakbright.resourcesketch2;

import se.oakbright.battleobjects.ship.ShipCommands;

/**
 * Created by hampuse on 2015-08-16.
 */
public class ShipMaker extends Maker<ShipCommands>{
Resource resource2;
    public ShipMaker(){
        setupPrimaryResource();
        setupResource2();
    }
    private void setupPrimaryResource(){
        primaryResource.add(BasicResources.getNew());
        //primaryResource.add("aModule", getAModuleResource());
        primaryResource.add("aModule", AModule.class, resource2);
    }

    private void setupResource2(){
        resource2 = Resource.empty();
        resource2.add("aModule", AModule.class, resource2);
    }

    public Bob<ShipCommands> make(){
        return new Bob(primaryResource);
    }
}

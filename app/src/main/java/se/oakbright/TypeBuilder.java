package se.oakbright;

import se.oakbright.battleobjects.BattleObject;

import se.oakbright.resources.TypeResource;

/**
 * Created by hampuse on 2015-08-13.
 */
public class TypeBuilder<T>{
    TypeResource<T> resource;
    public TypeBuilder(TypeResource<T> resource){
        this.resource = resource;
    }


    public T getBuilt(){
        //TODO NEED to save the new?
        return resource.createNewOfType();
    }
}
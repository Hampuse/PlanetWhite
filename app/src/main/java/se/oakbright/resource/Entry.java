package se.oakbright.resource;

import se.oakbright.Blueprint;

/**
 * Created by hampuse on 2015-08-18.
 */
abstract class Entry<T>{
    final Key<T> key;

    static <T> Entry<T> instanceOf(Key<T> key, Blueprint<? extends T> blueprint) {
        return new BlueprintEntry<T>(key, blueprint);
    }

    static <T> Entry<T> instanceOf(Key<T> key, T object) {
        return new ObjectEntry<T>(key, object);
    }

    private Entry(Key<T> key){
        if(key == null){
            throw new IllegalArgumentException("Argument key is null, in Entry(Key)");
        }
        this.key = key;
    }

    public boolean hasKey(Key key){
        return key.equals(this.key);
    }

    public abstract T getObject();

    final static class BlueprintEntry<T> extends Entry<T>{
        private final Blueprint<? extends T> blueprint;
        private BlueprintEntry(Key<T> key, Blueprint<? extends T> blueprint){
            super(key);
            if(blueprint == null){
                throw new IllegalArgumentException("Argument blueprint is null, in BlueprintEntry(Key, Blueprint)");
            }
            this.blueprint = blueprint;
        }

        public T getObject(){
            return blueprint.getBuilt();
        }
    }

    final static class ObjectEntry<T> extends Entry<T>{
        private final T object;
        private ObjectEntry(Key<T> key, T object){
            super(key);
            if(object == null){
                throw new IllegalArgumentException("Argument object is null, in ObjectEntry(Key, object)");
            }
            this.object = object;
        }
        public T getObject(){
            return object;
        }
    }
}

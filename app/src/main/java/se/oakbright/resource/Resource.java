package se.oakbright.resource;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import se.oakbright.Blueprint;
import se.oakbright.modules.Module;
import se.oakbright.modules.ModuleBlueprint;

/**
 * Created by hampuse on 2015-08-16.
 */
public final class Resource {
    private List<Entry> entries = new ArrayList<Entry>();

    /**
     * Searches for Key request, and returns the Any-class-object that is stored together whit it.
     * @throws RuntimeException if no entry with such Key is found,
     *                          or if more than one entry with such Key is found
     */
    public <T> T getThe(Key<T> request) {
        Entry<T> foundEntry = null;
        for (Entry entry : entries) {
            if (entry.hasKey(request)) {
                if (foundEntry != null) {
                    throw new RuntimeException("ERROR: Several entries with Key:\"" + request + "\" was found in this Resource. unable to decide which one to return. ");
                }
                foundEntry = entry; //Unchecked, but should be ok, since entry must be Entry<T> if key is Key<T>
            }
        }
        if (foundEntry == null) {
            throw new RuntimeException("ERROR; No Key:\"" + request + "\" was found in this Resource");
        }
        return foundEntry.getObject();
    }

    /**
     * Searches for Key request, and returns all Any-type-object stored in entries with such key.
     * Returns an empty list if no entry with such Key is found.
     */
    public <T> List<T> getAllIfAny(Key<T> request) {
        List<T> found = new LinkedList<T>();
        for (Entry entry : entries) {
            if (entry.hasKey(request)) {
                Entry<T> entryT = entry; //Unchecked, but should be safe, since entry must be Entry<T> when key is Key<T> in Entry
                T object = entryT.getObject();
                found.add(object);
            }
        }
        return found;
    }


    public <T> void add(Key<T> key, Blueprint<? extends T> blueprint) {
        Entry<T> entry = Entry.instanceOf(key, blueprint);
        entries.add(entry);
    }


    public <T extends Module> void add(Key<T> key, Class<? extends T> objectClass, Resource resourceToCreateNewModule) {
        Blueprint<T> blueprint = new ModuleBlueprint<T>(objectClass, resourceToCreateNewModule);
        add(key, blueprint);
    }


    public void add(Resource resource) {
        entries.addAll(resource.entries);
    }

    public <T> void add(Key<T> key, T object) {
        Entry<T> entry = Entry.instanceOf(key, object);
        entries.add(entry);
    }
}
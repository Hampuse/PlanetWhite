package se.oakbright.modules;

/**
 * Created by hampuse on 2015-08-02.
 */
public abstract class ModuleObserver<T extends Module> {
    public abstract void notify(T subject);
}

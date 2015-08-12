package se.oakbright.planetwhite;

public interface SurfaceObservable {
	public void registerSurfaceObserver(SurfaceObserver observer);
	public void unRegisterSurfaceObserver(SurfaceObserver observer);
	public void notifySurfaceObserversThatCreated();
	public void notifySurfaceObserversThatDestroyed();
}

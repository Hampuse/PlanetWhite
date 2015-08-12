package se.oakbright.battleobjects;

public interface IsActiveObservable {
	public void registerIAObserver(IsActiveObserver observer);
	public void unRegisterIAObserver(IsActiveObserver observer);
	public void notifyIAObservers();
	public boolean isActive();
}

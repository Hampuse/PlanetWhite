package se.oakbright.planetwhite;

public interface SurfaceObserver {
	public void notifySurfaceCreated(BattleSurface surface);
	public void notifySurfaceDestroyed(BattleSurface surface);
}

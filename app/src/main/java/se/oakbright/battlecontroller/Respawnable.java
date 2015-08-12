package se.oakbright.battlecontroller;

public interface Respawnable {

	void respawn(int x, int y);

	int getBoundingSquareSide();

	void respawn(int x, int y, int direction);

	int getMaxBoundingRadii();
}

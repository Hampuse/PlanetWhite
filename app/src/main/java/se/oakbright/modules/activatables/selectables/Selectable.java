package se.oakbright.modules.activatables.selectables;

import se.oakbright.setup.Team;

/**
 * Created by hampuse on 2015-06-27.
 */
public interface Selectable {
    public void select();
    public void deselect();
    public void stopDrawingPath();
    public int howNearPoint(int x, int y, int maxPointingRadius);
    public void deleteAim();
    public void setAim(int x, int y);
    public int getX();
    public int getY();
    public void setAim(Selectable target, int offsetX, int offsetY);
    public void tryCreatePath(int x, int y);
    public void tryCreateAimPath(int x, int y);
    public boolean isCreatingPath();
    public void setTrackObject(Selectable trackObject);
    public Team getTeam();
}

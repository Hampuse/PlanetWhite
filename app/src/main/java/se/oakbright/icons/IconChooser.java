package se.oakbright.icons;

import se.oakbright.setup.TeamColor;

public class IconChooser {
	private final IconId green;
	private final IconId red;
	private final IconId grey;
	
	public IconChooser(IconId iconIdGreen, IconId iconIdRed, IconId iconIdGrey){
		this.green = iconIdGreen;
		this.red = iconIdRed;
		this.grey = iconIdGrey;
	}
	
	public final IconId chooseIconId(TeamColor teamColor){	// Maybe redo with just team as input, or general Color
		switch (teamColor){
		case GREEN: 
			return this.green; //IconFactory.getIcon(battleSurface.getResources(), BattleSurface.BM_VASEN_GREEN));
		case RED:
			return this.red; //IconFactory.getIcon(battleSurface.getResources(), BattleSurface.BM_VASEN_RED));
		default:
			return this.grey; //IconFactory.getIcon(battleSurface.getResources(), BattleSurface.BM_VASEN_RED)); //BOorde vara grey, men finns ej
		}
	}
}

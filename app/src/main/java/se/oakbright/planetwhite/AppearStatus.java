package se.oakbright.planetwhite;

public class AppearStatus {
	public final boolean updatable;
	public final boolean renderable;
	public final boolean collidable;
	public final boolean pathDrawable;
	
	
	public AppearStatus(boolean updatable, boolean renderable, boolean collidable, boolean pathDrawable){
		this.updatable = updatable;
		this.renderable = renderable;
		this.collidable = collidable;
		this.pathDrawable = pathDrawable;
				
	}
}

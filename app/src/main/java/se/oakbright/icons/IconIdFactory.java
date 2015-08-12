package se.oakbright.icons;

import java.util.HashSet;

import android.graphics.Path;

public final class IconIdFactory {
	private static HashSet<IconId> iconIds = new HashSet<IconId>();

	private IconIdFactory(){
	}
	
	public static IconId valueOf(int id, int widthDst, int heightDst, int[] boundingPointsFull, float[] boundingPointsKey){
		for (IconId s : iconIds) {
			if (s.getId() == id && s.getWidthDst() == widthDst && s.getHeightDst() == heightDst){	//TODO compare also bounding?
					return s;			
			}
		}
		return new IconIdReal(id, widthDst, heightDst, boundingPointsFull, boundingPointsKey);	// only create a new IconId if no equal instance exists in iconIds.
	}
	
	public static IconId valueOf(int id, int widthDst, int heightDst){	
		return IconIdFactory.valueOf(id, widthDst, heightDst, null, null);//
	}
	
	public static IconId valueOfInvisibleIcon(int width, int height){
		return new IconIdInvisible(width, height);
	}

	private static final class IconIdReal implements IconId{
		private final int id;
		private final int widthDst;
		private final int heightDst;
		private final int[] boundingPointsFull;	//in DST
		private final float[] boundingPointsKey; //in DST
		
		private IconIdReal(int id, int widthDst, int heightDst, int[] boundingPointsFull, float[] boundingPointsKey){
			this.id = id;
			this.widthDst = widthDst;
			this.heightDst = heightDst;
			this.boundingPointsFull = boundingPointsFull;
			this.boundingPointsKey = boundingPointsKey;
		}
		
		@Override
		public int getId() {
			return this.id;
		}
		@Override
		public int getWidthDst() {
			return this.widthDst;
		}
		@Override
		public int getHeightDst() {
			return this.heightDst;
		}
		@Override
		public int[] getBoundingPointsFull(){
			return this.boundingPointsFull;
		}
		@Override
		public float[] getBoundingPointsKey(){
			return this.boundingPointsKey;
		}
		@Override
		public InIcon getIcon(IconFactory iconFactory) {
			return iconFactory.getIcon(this);
		}
		
	}
	
	private static final class IconIdInvisible implements IconId{
		private final int id;
		private final int widthDst;
		private final int heightDst;

		private IconIdInvisible(int widthDst, int heightDst){
			this.id = 0;
			this.widthDst = widthDst;
			this.heightDst = heightDst;
		}
		@Override
		public int getId() {
			return this.id;
		}
		@Override
		public int getWidthDst() {
			return this.widthDst;
		}
		@Override
		public int getHeightDst() {
			return this.heightDst;
		}

		@Override
		public InIcon getIcon(IconFactory iconFactory){ //, int widthPxl, int heightPxl) {
			return new InvisibleIcon(this.widthDst, this.heightDst);
		}
		@Override
		public int[] getBoundingPointsFull() {
			return null;
		}
		@Override
		public float[] getBoundingPointsKey() {
			return null;
		}
	}
}


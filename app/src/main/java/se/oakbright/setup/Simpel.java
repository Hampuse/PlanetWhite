package se.oakbright.setup;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class Simpel implements Parcelable, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	public String s = "simpelutan";
	public Simpel(){
	
	}
	private Simpel(Parcel in) {
		s = in.readString();
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(s);
	}
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
	    public Simpel createFromParcel(Parcel in) {
	        return new Simpel(in);
	    }

		@Override
		public Simpel[] newArray(int size) {
			return new Simpel[size];
		}
	};
}

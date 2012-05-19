package depricated;

import sprite.Orientation;

public class Marker extends Orientation{
	private MarkerType type;
	private String name;
	
	public Marker(String s, float x, float y, float z, MarkerType t){
		super(x, y, z);
		type = t;
		name = s;
	}
	
	public MarkerType getType(){
		return type;
	}
	
	public String getName(){
		return name;
	}
}

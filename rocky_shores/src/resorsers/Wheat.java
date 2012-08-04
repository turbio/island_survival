package resorsers;

import model.Model;

public class Wheat extends Resource{
	public static float scale = 0.035f;
	
	public Wheat(Model m, float x, float z, float w, float h){
	super(m.getModel("wheat"), scale);
	super.setX(x);
	super.setZ(z);
	}
}
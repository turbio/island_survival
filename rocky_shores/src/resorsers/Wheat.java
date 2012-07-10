package resorsers;

import model.Model;

public class Wheat extends Resource{
	public Wheat(Model m, float x, float z, float w, float h){
	super(m.getModel("wheat"), 0.035f);
	super.setX(x);
	super.setZ(z);
	}
}
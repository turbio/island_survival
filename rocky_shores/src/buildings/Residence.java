package buildings;

import mesh.Mesh;
import model.Model;
import mesh.Material;

public class Residence extends Building{
	
	private final static float scale = 0.03f;
	public Residence(Mesh m, float x, float z, Material mat, Model model){
		super(BuildingTypes.TOWN_HALL, m, x, z, model, scale);
		super.setWidth(scale);
		super.setHeight(scale);
		super.setDepth(scale);
	}
}

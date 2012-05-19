package buildings;

import mesh.Mesh;
import model.Model;

public class WorkShop extends Building{
	private final static float scale = 0.025f;
	
	public WorkShop(Mesh m, float x, float z, Model model) {
		super(BuildingTypes.WORKSHOP, x, z, scale, model,  m);
		super.setWidth(scale);
		super.setHeight(scale);
		super.setDepth(scale);
	}

}

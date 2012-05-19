package buildings;

import mesh.Mesh;
import model.Model;

public class Barraks extends Building{
	private final static float scale = 0.01f;
	
	public Barraks(Mesh m, float x, float z, Model model) {
		super(BuildingTypes.BARRAKS, m, x, z, model, scale);
		
		super.setWidth(scale);
		super.setHeight(scale);
		super.setDepth(scale);
	}
}

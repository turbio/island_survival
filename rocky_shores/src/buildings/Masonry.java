package buildings;

import mesh.Mesh;
import model.Model;

public class Masonry extends Building{
	private final static float scale = 0.023f;
	public Masonry(Mesh m, float x, float z, Model model){
		super(BuildingTypes.MASONRY, m, x, z, model, scale);
		super.setWidth(scale);
		super.setHeight(scale);
		super.setDepth(scale);
	}
}

package buildings;

import mesh.Mesh;
import model.Model;

public class TownHall extends Building{
	private final static float scale = 0.023f;
	
	public TownHall(Mesh m, float x, float z, Model model){
		super(BuildingTypes.TOWN_HALL, m, x, z, model, scale);
		//m.getFaces().get(0).getVertex().get(0).
		super.setWidth(scale);
		super.setHeight(scale);
		super.setDepth(scale);
	}
}

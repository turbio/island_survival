package buildings;

import mesh.Mesh;
import model.Model;

public class Dock extends Building{
	private final static float scale = 0.05f;
	
	public Dock(Mesh m, float x, float z, Model model){
		super(BuildingTypes.DOCK, m, x, z, model, scale);
		
		super.setWidth(scale);
		super.setHeight(scale);
		super.setDepth(scale);
		
		super.setY(0.01f);
		
		if(x > 0){
			
		}else if(x < 0){
			super.setYRot(180);
		}
	}
}

package buildings;

import sprite.Sprite;
import mesh.Mesh;
import model.Model;

public class Farm extends Building{
	private final static float scale = 0.027f;
	
	private Sprite fence;
	
	public Farm(Mesh m, float x, float z, float x2, float z2, Model model) {
		super(BuildingTypes.FARM, m, x, z, model, scale);
		
		super.setWidth(scale);
		super.setHeight(scale);
		super.setDepth(scale);
		
		//if(x2 < (m.getLeft() * scale)){
		//	
		//}
		
		//System.out.println(x2);
		//x2 = (m.getLeft() * scale);
		//z2 = (m.getBack() * scale);
		//System.out.println(x2);
		//x = m.getLeft() * scale;
		
		if(x2 - x > 0){
			fence = new Fence((m.getLeft() * scale) + x, z, (m.getLeft() * scale) + x, z2, x2, z2, x2, z, model.getMaterial("fence_farm"));
		}else{
			fence = new Fence((m.getRight() * scale) + x, z, (m.getRight() * scale) + x, z2, x2, z2, x2, z, model.getMaterial("fence_farm"));
		}
		//fence.setVisible(false);
		fence.setY(-fence.getHeight() * .05f);
		
		model.getSpriteList().add(fence);
	}
	
	public void update(long d){
		super.update(d);
		
		if(super.isBuilt()){
			fence.setVisible(true);
			
			if(fence.getY() >= 0.0f){
				fence.setY(0.0f);
				fence.setYVel(0.0f);
				
			}else{
				fence.setYVel(0.001f);
			}
		}
	}
}
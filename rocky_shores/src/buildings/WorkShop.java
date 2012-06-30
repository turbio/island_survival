package buildings;

import sprite.Sprite;
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

	public void addResource(Sprite m){
		super.addResource(m);
		
		super.getResource().get(super.getResource().size() - 1).setX(super.getX() - 0.1f);
		super.getResource().get(super.getResource().size() - 1).setY(0.04f);
		super.getResource().get(super.getResource().size() - 1).setXVel(0.0005f);
		super.getResource().get(super.getResource().size() - 1).setYRot(90.0f);
		super.getResource().get(super.getResource().size() - 1).setWidth(super.getResource().get(super.getResource().size() - 1).getWidth() + 0.005f);
		super.getResource().get(super.getResource().size() - 1).setDepth(super.getResource().get(super.getResource().size() - 1).getDepth() + 0.005f);
		super.getResource().get(super.getResource().size() - 1).setHeight(super.getResource().get(super.getResource().size() - 1).getHeight() + 0.005f);
		//super.getResource().setYVel(0.001f);
	}
	
	public void update(long d){
		super.update(d);
		
		for(int i = 0; i < super.getResource().size(); i++){
			if(super.getResource().get(i).getX() > super.getX() - 0.04f){
				super.getResource().get(i).setAlive(false);
				super.addQue();
				super.getResource().remove(i);
			}
		}
	}
}

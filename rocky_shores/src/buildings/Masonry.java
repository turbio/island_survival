package buildings;

import sprite.Sprite;
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
	
	public void addResource(Sprite m){
		super.addResource(m);
		
		super.getResource().get(super.getResource().size() - 1).setX(super.getX() - 0.08f);
		super.getResource().get(super.getResource().size() - 1).setY(0.03f);
		super.getResource().get(super.getResource().size() - 1).setXVel(0.0005f);
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

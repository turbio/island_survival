package buildings;

import sprite.Sprite;
import mesh.Mesh;
import model.Model;

public class Masonry extends Building{
	private final static float scale = 0.023f;
	private int que = 0, timeRemain = 50;
	
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
		//super.getResource().setYVel(0.001f);
	}
	
	public void update(){
		super.update();
		
		for(int i = 0; i < super.getResource().size(); i++){
			if(super.getResource().get(i).getX() > super.getX() - 0.04f){
				super.getResource().get(i).setAlive(false);
				que++;
				
				super.getResource().remove(i);
			}
		}
		
		if(que > 0){
			timeRemain--;
			if(timeRemain <= 0){
				timeRemain = 200;
				
			}
		}
	}
}

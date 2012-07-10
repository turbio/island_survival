package buildings;

import java.util.*;

import resorsers.Wheat;
import sprite.Sprite;
import mesh.Mesh;
import model.Model;

public class Farm extends Building{
	private final static float scale = 0.027f;
	private ArrayList<Wheat> wheatlist = new ArrayList<Wheat>();
	private Sprite fence;
	private Model model;
	private long elapsed;
	private float width, height;

	public Farm(Mesh m, float x, float z, float x2, float z2, Model mod) {
		super(BuildingTypes.FARM, m, x, z, mod, scale);
		model = mod;
		
		width = x2;
		height = z2;

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

			elapsed += d;
			if(elapsed % 100 == 0){
				addWheat();
			}

			if(fence.getY() >= 0.0f){
				fence.setY(0.0f);
				fence.setYVel(0.0f);
				
			}else{
				fence.setYVel(0.001f);
			}
		}
	}

	private void addWheat(){
		Wheat w = new Wheat(model, fence.getX(), super.getY(), width, height);
		wheatlist.add(w);
		
		model.getSpriteList().add(w);
	}
}
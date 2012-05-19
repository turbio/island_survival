package resorsers;

import mesh.Mesh;
import mob.Peasant;
import sprite.Sprite;

public abstract class Resource extends Sprite{
	private int health = 500, amount = 5;
	private boolean harvested = false, harvestable = true;
	private Peasant owner = null;
	private float scale;
	
	
	public Resource(Mesh m, float s){
		super(m);
		scale = s;
		super.setWidth(scale);
		super.setHeight(scale);
		super.setDepth(scale);
	}
	
	public void claim(Peasant p){
		owner = p;
	}
	
	public boolean harvest(){
		health--;
		
		//super.setWidth(interpolate(0.0f, scale, ((float)health + (500.0f * ((float)amount))) + 100.0f) / ((float)500 + (500.0f * ((float)5))));
		//super.setHeight(interpolate(0.0f, scale, ((float)health + (500.0f * ((float)amount))) + 100.0f) / ((float)500 + (500.0f * ((float)5))));
		//super.setDepth(interpolate(0.0f, scale, ((float)health + (500.0f * ((float)amount))) + 100.0f) / ((float)500 + (500.0f * ((float)5))));
		
		if(health <= 0){
			amount--;
			health = 300;
			
			if(amount <= 0){
				super.setVisible(false);
				super.setAlive(false);
				harvestable = false;
			}
			
			return true;
		}
		
		return false;
	}
	
	public boolean isClaimed(){
		if(owner == null){
			return false;
		}
		
		return true;
	}
	
	public boolean isClaimed(Peasant p){
		if(owner == null){
			return false;
		}
		
		if(owner == p){
			return false;
		}
		
		return true;
	}
	
	public boolean isHarvested(){
		return harvested;
	}
	
	public boolean isHarvestable(){
		return harvestable;
	}
	
	public float interpolate(float a, float b, double t){
		return (float) (a * (1 - t) + b * t);
	}
}

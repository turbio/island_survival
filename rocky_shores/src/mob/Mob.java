package mob;

import mesh.Mesh;
import sprite.Sprite;



public abstract class Mob extends Sprite{
	private Sprite target;
	
	private int health;
	
	public Mob(Mesh... m){
		super(m);
	}
	
	public Sprite getTarget(){
		return target;
	}
	
	public void setTarget(Sprite s){
		target = s;
	}
	
	public int getHealth(){
		return health;
	}
	
	public void update(){
		super.update();
	}
}

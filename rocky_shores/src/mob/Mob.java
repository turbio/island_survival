package mob;

import mesh.Mesh;
import sprite.Sprite;



public abstract class Mob extends Sprite{
	public final static int PEASANT = 0, SOLDIER = 1, MONSTER = 2;
	private int type = -1;
	
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
	
	public void update(long d){
		super.update(d);
	}
	
	public void setMobType(int i){
		type = i;
	}
	
	public int getMobType(){
		return type;
	}
}

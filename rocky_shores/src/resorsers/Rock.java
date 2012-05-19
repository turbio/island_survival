package resorsers;
import java.util.Random;

import particles.Emitter;

import mesh.Mesh;
import mob.Peasant;
import model.Model;

import sprite.SpriteTypes;


public class Rock extends Resource{
	
	private long growthTime, growth, lifeTime, reprodCool, reprodTime, reprodChance;
	private boolean dead, reprod = false, maxGrow = false;
	private Emitter miter;
	
	public Rock(Mesh mesh, float x, float y, float z, Model m){	//takes textures and x y z
		super(m.getModel("rock" + (new Random().nextInt(3) + 1)), 0.008f);
		//System.out.println((new Random().nextInt(3) + 1));
		super.setType(SpriteTypes.ROCK);
		super.setX(x);
		super.setY(y);
		super.setZ(z);
		super.setBoundWidth(1.02f);
		super.setBoundDepth(1.02f);
		super.setSubClass(this);
		super.setYRot((float)Math.random() * 360);
		growthTime = 100;
		lifeTime = 0;
		reprodCool = 0;
		reprodChance = 0;
		growth = growthTime;
		reprodTime = reprodCool;
		
		miter = new Emitter(x, 0.015f, z, 0.0f, 0.002f, 0.0f, 0.001f, 0.001f, 0, 0, mesh.getMat().getTexture());
		miter.setActive(true);
		miter.setPhys(true);
		miter.setLife(175);
		miter.setDieOnAnimation(false);
		miter.setBounce(0.4f);
		miter.setGrav(0.0005f);
		miter.setSingelPix(true);
		miter.setRandomXPos(0.015f);
		//miter.setRandomYPos(0.01f);
		miter.setRandomZPos(0.015f);
		m.getMiters().add(miter);
	}
	
	public void update(){
		super.update();
		if(maxGrow){
			if(reprodTime > 0){
				reprodTime--;
			}else{
				reprodTime = reprodCool;
				Random random = new Random();
				if(random.nextInt(99) + reprodChance >= 100){
					reprod = true;
				}
			}
		}
		
		if(maxGrow && !dead){
			lifeTime--;
			if(lifeTime <= 0){
				dead = true;
			}
		}else{
			if(growth == 0){
				growth = growthTime;
				//super.nextTexture(false);
				
			}else{
				growth--;
			}
		}
	}
	
	public boolean isDead(){
		return dead;
	}
	
	public boolean isReprod(){
		return reprod;
	}
	
	public void setReptrod(boolean b){
		reprod = b;
	}
	
	public void claim(Peasant p){
		//miter.setInterval(1);
		//miter.setRate(2);
		super.claim(p);
	}
}

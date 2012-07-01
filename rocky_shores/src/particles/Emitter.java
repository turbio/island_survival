package particles;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.opengl.Texture;

public class Emitter{
	private ArrayList<Particle> particles;
	private float xPos, yPos, zPos, xVel = 0, yVel = 0, zVel = 0, xScale, yScale,
			randomXPos, randomYPos, randomZPos, scaleVel, bounce = 1, grav = 0.001f;
	private int emitRate = 0;	//particles added per update
	private double emitInterval = 0, emitTime = emitInterval, particleLife = -1, animateSpeed;
	private boolean randomTex, animate, dieOnAnimation = true, phys, dieOnStop = false,
			alive = true, pendingDeath, active = true, singlePix = false;
	private Texture[] texture;
	private Random random = new Random();
	
	public Emitter(Emitter e){
		particles = new ArrayList<Particle>();
		xPos = e.xPos;
		yPos = e.yPos;
		zPos = e.zPos;
		xVel = e.xVel;
		yVel = e.yVel;
		zVel = e.zVel;
		xScale = e.xScale;
		yScale = e.yScale;
		randomXPos = e.randomXPos;
		randomYPos = e.randomYPos;
		randomZPos = e.randomZPos;
		scaleVel = e.scaleVel;
		bounce = e.bounce;
		grav = e.grav;
		emitRate = e.emitRate;
		emitInterval = e.emitInterval;
		emitTime = emitInterval;
		particleLife = e.particleLife;
		animateSpeed = e.animateSpeed;
		randomTex = e.randomTex;
		animate = e.animate;
		dieOnAnimation = e.dieOnAnimation;
		phys = e.phys;
		dieOnStop = e.dieOnStop;
		texture = e.texture;
	}
	
	public Emitter(float x, float y, float z, float xs, float ys, Texture... t){
		particles = new ArrayList<Particle>();
		xPos = x;
		yPos = y;
		zPos = z;
		xScale = xs;
		yScale = ys;
		texture = t;
	}
	
	public Emitter(float x, float y, float z, float xv, float yv, float zv, float xs, float ys, int rate, long interval, Texture... t){
		particles = new ArrayList<Particle>();
		xPos = x;
		yPos = y;
		zPos = z;
		xVel = xv;
		yVel = yv;
		zVel = zv;
		emitRate = rate;
		emitInterval = interval;
		emitTime = interval;
		xScale = xs;
		yScale = ys;
		texture = t;
	}
	
	public void addParticle(){
		for(int i = 0; i < emitRate; i++){
			Particle p = new Particle(xPos, yPos, zPos, xVel, yVel, zVel, xScale, yScale, texture, 0, animate, animateSpeed);
			p.setLife(particleLife);
			p.setDieOnAnimation(dieOnAnimation);
			p.setSingPix(singlePix);
			
			if(phys){
				p.setCollision(true);
				p.setGravity(grav);
				p.setBounce(bounce);
			}
			p.setDieOnStop(dieOnStop);
			if(randomTex){
				p.setTexture(random.nextInt(texture.length));
			}
			if(randomXPos > 0){
				//p.setX((random.nextFloat() % randomXPos) + xPos);
				if(random.nextBoolean()){
					p.setX(((random.nextFloat() - randomXPos) % (randomXPos * 1)) + xPos);
				}else{
					p.setX(((random.nextFloat() + randomXPos) % (randomXPos * 1)) + xPos);
				}
			}
			if(randomYPos > 0){
				if(random.nextBoolean()){
					p.setY(((random.nextFloat() - randomYPos) % (randomYPos * 1)) + yPos);
				}else{
					p.setY(((random.nextFloat() + randomYPos) % (randomYPos * 1)) + yPos);
				}
			}
			if(randomZPos > 0){
				if(random.nextBoolean()){
					p.setZ(((random.nextFloat() - randomZPos) % (randomZPos * 1)) + zPos);
				}else{
					p.setZ(((random.nextFloat() + randomZPos) % (randomZPos * 1)) + zPos);
				}
			}
			p.setScaleVel(scaleVel);
			particles.add(p);
		}
	}
	
	public void addParticle(Particle p){
		particles.add(p);
	}
	
	public ArrayList<Particle> getParticle(){
		return particles;
	}
	
	public Particle getParticle(int index){
		return particles.get(index);
	}
	
	//set methods
	public void setDieOnAnimation(boolean b){
		dieOnAnimation = b;
	}
	
	public void setX(float x){
		xPos = x;
	}
	public void setY(float y){
		yPos = y;
	}
	public void setZ(float z){
		zPos = z;
	}
	public void setXVel(float xv){
		xVel = xv;
	}
	public void setYVel(float yv){
		yVel = yv;
	}
	public void setZVel(float zv){
		zVel = zv;
	}
	public void setLife(long l){
		particleLife = l;
	}
	public void setRandomXPos(float f){
		randomXPos = f;
	}
	public void setRandomYPos(float f){
		randomYPos = f;
	}
	public void setRandomZPos(float f){
		randomZPos = f;
	}
	public void setScaleVel(float f){
		scaleVel = f;
	}
	
	public void setRandomTex(boolean b){
		randomTex = b;
	}
	
	public void setAnimate(boolean b){
		animate = b;
	}
	
	public void setAnimateSpeed(long l){
		animateSpeed = l;
	}
	
	public void setBounce(float f){
		bounce = f;
	}
	
	public void setGrav(float f){
		grav = f;
	}
	
	public void setPhys(boolean b){
		phys = b;
	}
	
	public void setDieOnStop(boolean b){
		dieOnStop = b;
	}
	
	public void setAlive(boolean b){
		if(!b){
			if(particles.size() > 0){
				pendingDeath = true;
			}else{
				alive = b;
			}
		}
	}
	
	public void forceKill(){
		alive = false;
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public void setActive(boolean b){
		active = b;
	}
	
	public void setRate(int i){
		emitRate = i;
	}
	
	public void setInterval(long l){
		emitInterval = l;
	}
	
	public void setSingelPix(boolean b){
		singlePix = b;
	}
	
	//update all particles and variables
	public void update(long d){
		for(int i = 0; i < particles.size(); i++){
			if(!particles.get(i).isAlive()){
				particles.remove(i);
			}else{
				particles.get(i).update(d);
			}
		}
		if(pendingDeath && particles.size() == 0){
			alive = false;
		}
		if(emitRate > 0 && !pendingDeath && active){
			if(emitTime <= 0){
				emitTime = emitInterval;
				addParticle();
			}else{
				emitTime -= d;
			}
		}
	}
}

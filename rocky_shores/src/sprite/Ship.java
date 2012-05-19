package sprite;

import mesh.Mesh;
import model.Model;

import particles.Emitter;

public class Ship extends Sprite{
	//texture 0 = normal 1 = crack_left 2 = crack_right 3 = alpha
	private final float scale = 0.06f;
	private float shipCrashX = -0.6f;
	private float bob = 0;
	
	//tilt
	private float tilt = 0, maxTilt = 1f, minTilt = 0f, tiltinc = 0.01f;
	private boolean tiltf = true;
	
	//partileEmitter
	Emitter miterRight, miterLeft, miterBack;
	
	public Ship(Mesh ship, Mesh shipRight, Mesh shipLeft, float x, float y, float z, float xVel, float yVel, float zVel, Model m){
		super(ship);
		super.setX(x);
		super.setY(y);
		super.setZ(z);
		super.setXVel(xVel);
		super.setYVel(yVel);
		super.setZVel(zVel);
		super.setWidth(scale);
		super.setHeight(scale);
		super.setDepth(scale);
		super.setYRot(90);
		super.cullFace(false);
		
		miterRight = new Emitter(x, 0.1f, z, 0.0f, 0.0f, 0.004f, 0.008f, 0.008f, 1, 5, m.getTexture("particles/water1", "particles/water2", "particles/water3"));
		miterRight.setRandomTex(true);
		miterRight.setPhys(true);
		miterRight.setBounce(0.6f);
		miterRight.setY(0.000001f);
		miterRight.setRandomXPos(0.2f);
		miterRight.setRandomZPos(0.02f);
		miterRight.setGrav(0.0008f);
		miterRight.setRandomYPos(0.01f);
		miterRight.setZVel(0.0015f);
		miterRight.setYVel(0.005f);
		miterRight.setLife(45);
		
		miterLeft = new Emitter(miterRight);
		miterLeft.setZVel(-0.0015f);
		
		miterBack = new Emitter(miterLeft);
		miterBack.setXVel(-0.0025f);
		miterBack.setZVel(-0.0015f);
		miterBack.setRandomXPos(0.0f);
		miterBack.setRandomZPos(0.08f);
		miterBack.setYVel(0.005f);
		
		m.getMiters().add(miterRight);
		m.getMiters().add(miterLeft);
		m.getMiters().add(miterBack);
	}

	
	public void update(){
		super.update();
		miterRight.setX(super.getX() - 0.08f);
		miterRight.setZ(super.getZ() + 0.05f);
		miterLeft.setX(super.getX() - 0.08f);
		miterLeft.setZ(super.getZ() - 0.05f);
		miterBack.setX(super.getX() - 0.18f);
		miterBack.setZ(super.getZ() - 0.03f);
		if(super.getXVel() == 0 && super.getZVel() == 0){
			miterRight.setInterval(15);
			miterRight.setRate(2);
			miterRight.setBounce(0.5f);
			miterLeft.setInterval(15);
			miterLeft.setRate(2);
			miterLeft.setBounce(0.5f);
			miterBack.setInterval(15);
			miterBack.setRate(2);
			miterBack.setBounce(0.5f);
		}else{
			miterRight.setBounce(0.6f);
			miterRight.setInterval(0);
			miterRight.setRate(1);
			miterLeft.setBounce(0.6f);
			miterLeft.setInterval(0);
			miterLeft.setRate(1);
			miterBack.setBounce(0.6f);
			miterBack.setInterval(0);
			miterBack.setRate(1);
		}
		if(super.getX() > shipCrashX){
			super.setXVel(0.0f);
		}
		
		if(tilt > maxTilt){
			tiltf = false;
		}
		if(tilt < minTilt){
			tiltf = true;
		}
		
		if(tiltf){
			tilt += tiltinc;
		}else{
			tilt -= tiltinc;
		}
		
		bob += 0.06f;
		if(bob > 360){
			bob = 0;
		}
		
		super.setY(((float)Math.sin(bob) * 0.008f) + 0.018f);
	}
}

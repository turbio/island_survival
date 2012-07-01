package sprite;

import mesh.Mesh;
import model.Model;

import particles.Emitter;

public class Ship extends Sprite{
	//texture 0 = normal 1 = crack_left 2 = crack_right 3 = alpha
	private final float scale = 0.06f;
	private float shipCrashX = -0.6f;
	private float bob = 0;
	private boolean crash = false;
	private Mesh shipFront, shipBack;
	private Sprite shipb;
	private Model model;
	
	//tilt
	private float tilt = 0, maxTilt = 1f, minTilt = 0f, tiltinc = 0.01f;
	private boolean tiltf = true;
	
	//partileEmitter
	Emitter miterRight, miterLeft, miterBack;
	
	public Ship(Mesh ship, Mesh shipF, Mesh shipB, float x, float y, float z, float xVel, float yVel, float zVel, Model m){
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
		
		shipFront = shipF;
		shipBack = shipB;
		
		miterRight = new Emitter(x, 0.1f, z, 0.0f, 0.0f, 0.004f, 0.008f, 0.008f, 1, 50, m.getTexture("particles/water1", "particles/water2", "particles/water3"));
		miterRight.setRandomTex(true);
		miterRight.setPhys(true);
		miterRight.setBounce(0.6f);
		miterRight.setY(0.000001f);
		miterRight.setRandomXPos(0.2f);
		miterRight.setRandomZPos(0.02f);
		miterRight.setGrav(0.002f);
		miterRight.setRandomYPos(0.01f);
		miterRight.setZVel(0.0015f);
		miterRight.setYVel(0.005f);
		miterRight.setLife(1500);
		
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
		
		model = m;
	}

	
	public void update(long d){
		super.update(d);
		miterRight.setX(super.getX() - 0.08f);
		miterRight.setZ(super.getZ() + 0.05f);
		miterLeft.setX(super.getX() - 0.08f);
		miterLeft.setZ(super.getZ() - 0.05f);
		miterBack.setX(super.getX() - 0.18f);
		miterBack.setZ(super.getZ() - 0.03f);
		if(crash){
			
			miterRight.setInterval(100);
			miterRight.setRate(1);
			miterRight.setBounce(0.5f);
			miterLeft.setInterval(100);
			miterLeft.setRate(1);
			miterLeft.setBounce(0.5f);
			miterBack.setInterval(100);
			miterBack.setRate(1);
			miterBack.setBounce(0.5f);
			
			if(shipb.getXRot() > 10){
				shipb.setXRotVel(0.0f);
				super.setXRotVel(0.0f);
				
				shipb.setYVel(0.0f);
				super.setYVel(0.0f);
				
				shipb.setY(-((float)Math.sin(bob * .5) * 0.008f) - 0.010f);
				super.setY(((float)Math.sin(bob) * 0.008f) - 0.010f);
			}
		}else{
			super.setY(((float)Math.sin(bob) * 0.008f) + 0.018f);
			miterRight.setBounce(0.6f);
			miterRight.setInterval(30);
			miterRight.setRate(1);
			miterLeft.setBounce(0.6f);
			miterLeft.setInterval(30);
			miterLeft.setRate(1);
			miterBack.setBounce(0.6f);
			miterBack.setInterval(30);
			miterBack.setRate(1);
		}
		if(super.getX() > shipCrashX && !crash){
			crash = true;
			super.setXVel(0.0f);
			super.setMesh(shipFront);
			super.setXRotVel(-0.1f);
			super.setYVel(-0.00017f);
			
			shipb = new Sprite(shipBack);
			shipb.setX(super.getX());
			shipb.setZ(super.getZ());
			shipb.setYRot(super.getYRot());
			shipb.setWidth(super.getWidth());
			shipb.setHeight(super.getHeight());
			shipb.setDepth(super.getDepth());
			shipb.setXRotVel(0.1f);
			shipb.setYVel(-0.0002f);
			
			model.getSpriteList().add(shipb);
			bob = 0;
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
		
		bob += (float)d / 500f;
		if(bob > 360){
			bob = 0;
		}
	}
}

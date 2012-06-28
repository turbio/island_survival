package world;

import sprite.Orientation;

public class Camera extends Orientation{
	private float FOV, maxX = 90, minX = -90;
	private float right, left, forward, backward, up, down;
	
	public Camera(float x, float y, float z, float xRot, float yRot, float zRot, float fov){
		super(x, y, z, xRot, yRot, zRot);
	}
	
	public void setFov(float f){
		FOV = f;
	}
	
	public float getFov(){
		return FOV;
	}
	
	public void update(){
		
		//setZVel();
		//setXVel(-(float)(Math.sin(Math.toRadians(camera.getYRot()))) * 0.03f);
		
		super.setZ(super.getZ() + ((float)(Math.cos(Math.toRadians(super.getYRot()))) * super.getZVel()));
		super.setX(-(-super.getX() + ((float)(Math.sin(Math.toRadians(super.getYRot()))) * super.getXVel())));
		
		if(super.getXRot() > maxX){
			super.setXRot(maxX);
		}else if(super.getXRot() < minX){
			super.setXRot(minX);
		}
	}
	
	public void setXRot(float f){
		if(f > maxX){
			f = maxX;
		}else if(f < minX){
			f = minX;
		}
		super.setXRot(f);
	}
	
	public void setForward(float f){
		forward = f;
	}
	
	public void setBackward(float f){
		backward = f;
	}
	
	public void setRight(float f){
		right = f;
	}
	
	public void setLeft(float f){
		left = f;
	}
}

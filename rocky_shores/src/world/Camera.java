package world;

import sprite.Orientation;

public class Camera extends Orientation{
	private float FOV;
	
	public Camera(float x, float y, float z, float xRot, float yRot, float zRot, float fov){
		super(x, y, z, xRot, yRot, zRot);
	}
	
	public void setFov(float f){
		FOV = f;
	}
	
	public float getFov(){
		return FOV;
	}
}

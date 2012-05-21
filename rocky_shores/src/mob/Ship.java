package mob;
import java.awt.image.BufferedImage;

import mesh.Material;
import sprite.Sprite;



public class Ship extends Sprite{
	//texture 0 = normal 1 = crack_left 2 = crack_right 3 = alpha
	private boolean crash = false;
	private BufferedImage alphakey;
	
	//bobing
	private float bobbing = 0, maxBob = .0018f, minBob = -.0015f, bobinc = 0.0001f;
	private boolean bobup = true;
	
	//tilt
	private float tilt = 0, maxTilt = 1f, minTilt = 0f, tiltinc = 0.01f;
	private boolean tiltf = true;
	
	public Ship(Material[] tex, float x, float y, float z, float xVel, float yVel, float zVel){
		super(tex, x, y, z, 0, 0, 0, xVel, yVel, zVel, 0, 0, 0, 0.2f, 0.2f, 1.0f);
		//alphakey = alpha;
	}
	public Ship(Material[] tex, int startTex, float x, float y, float z, float xVel, float yVel, float zVel){
		super(tex, x, y, z, 0, 0, 0, xVel, yVel, zVel, 0, 0, 0, 0.2f, 0.2f, 1.0f);
		crash = true;
		super.setTexture(startTex);
		if(startTex == 1){
			bobbing = 0;
		}else{
			bobbing = maxBob;
		}
	}
	
	public Ship() {
		
	}
	public boolean isCrashed(){
		return crash;
	}
	
	public void setCrash(boolean b){
		crash = b;
	}
	
	public BufferedImage getTexAlpha(){
		return alphakey;
	}
	
	public void update(){
		super.update();
		
		if(crash){
			super.setXVel(0.0f);
			super.setYVel(-0.002f);
			
			if(super.getCurrentTexture() == 2){
				super.setZRotVel(0.1f);
			}else{
				super.setZRotVel(-0.1f);
			}
			
			if(super.getY() < -0.03 && super.getCurrentTexture() == 1){
				super.setYVel(0);
			}
			if(super.getY() < -0.05 && super.getCurrentTexture() == 2){
				super.setYVel(0);
			}
			if(super.getZRot() < -20){
				super.setZRotVel(0);
			}
			
			if(super.getZRot() > 20){
				super.setZRotVel(0);
			}
		}
		
		if(bobbing > maxBob){
			bobup = false;
		}
		if(bobbing < minBob){
			bobup = true;
		}
		
		if(bobup){
			bobbing += bobinc;
		}else{
			bobbing -= bobinc;
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
		
		super.setY(super.getY() + bobbing);
	}
}

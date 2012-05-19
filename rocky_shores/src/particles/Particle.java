package particles;

import java.util.Random;

import org.newdawn.slick.opengl.Texture;

import sprite.Orientation;

public class Particle extends Orientation{
	private int currentTexture;
	private float scaleVel = 0, bounce = 1.0f, gravStrength = 0.001f, textureX1 = 0, textureY1 = 0, textureX2 = 1, textureY2 = 1;
	private long life = -1, speed, frameTime; //if -1 it will not decrese and there is no life
	private boolean alive, animate, dieEnd, grav, dieStill, singlePix = false;// isTextured, isSolide;
	private Random random = new Random();
	private Texture[] texture;
	
	public Particle(float x, float y, float z, float xs, float ys, Texture[] t, int currentTex, boolean anim, long s){
		super(x, y, z);
		super.setWidth(xs);
		super.setHeight(xs);
		alive = true;
		texture = t;
		animate = anim;
		currentTexture = currentTex;
		speed = s;
		frameTime = s;
	}
	
	public Particle(float x, float y, float z, float xv, float yv, float zv, float xs, float ys, Texture[] t, int currentTex, boolean anim, long s){
		super(x, y, z);
		super.setXVel(xv);
		super.setYVel(yv);
		super.setZVel(zv);
		super.setWidth(xs);
		super.setHeight(ys);
		alive = true;
		texture = t;
		animate = anim;
		currentTexture = currentTex;
		speed = s;
		frameTime = s;
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public void update(){
		if(dieStill){
			if(super.getXVel() == 0 && super.getYVel() == 0 
					&& super.getZVel() == 0 && super.getXRotVel() == 0
					&& super.getYRotVel() == 0 && super.getZRotVel() == 0){
				alive = false;
			}
		}
		
		if(grav){
			super.setYVel(super.getYVel() - gravStrength);
			if(super.getY() <= 0){
				super.setYVel(super.getYVel() * -bounce);
			}
			
			if(super.getZVel() > 0){
				super.setZVel(super.getZVel() - 0.0001f);
			}else if(super.getZVel() < 0){
				super.setZVel(super.getZVel() + 0.0001f);
			}
			if(super.getXVel() > 0){
				super.setXVel(super.getXVel() - 0.0001f);
			}else if(super.getZVel() < 0){
				super.setXVel(super.getXVel() + 0.0001f);
			}
		}
		
		if(animate){
			if(frameTime > 0){
				frameTime--;
			}else{
				frameTime = speed;
				if(currentTexture < texture.length - 1 && texture[currentTexture + 1] != null){
					currentTexture++;
				}else{
					if(dieEnd){
						alive = false;
					}else{
						currentTexture = 0;
					}
				}
			}
		}
		
		super.update();
		setWidth(getWidth() + scaleVel);
		setHeight(getHeight() + scaleVel);
		if(getWidth() < 0){
			alive = false;
		}
		
		if(life > 0){
			life--;
		}
		if(life == 0){
			alive = false;
		}
	}
	
	public int getTextureLength(){
		int i = 0;
		for(int x = 0; x < texture.length; x++){
			if(texture[x] != null){
				i++;
			}
		}
		 return i;
	}
	
	//set methods
	public void setSingPix(boolean b){
		singlePix = b;
		if(b){
			int xOffset = random.nextInt() % texture[0].getImageWidth(), yOffset = random.nextInt() % texture[0].getImageHeight();
			
			float pixelWidth = texture[0].getHeight() / texture[0].getImageHeight(), pixelHeight = texture[0].getWidth() / texture[0].getImageWidth();
			
			textureX1 = pixelWidth * xOffset;
			textureY1 = pixelHeight * yOffset;
			textureX2 = (pixelWidth * xOffset) + pixelWidth;
			textureY2 = (pixelHeight * yOffset) + pixelHeight;
		}else{
			textureX1 = 0;
			textureY1 = 0;
			textureX2 = 1;
			textureY2 = 1;
		}
	}
	
	public void setGravity(float f){
		gravStrength = f;
	}
	
	public void setCollision(boolean c){
		grav = c;
	}
	
	public void setBounce(float b){
		bounce = b;
	}
	
	public void setSpeed(long s){
		speed = s;
	}
	
	public void setLife(long l){
		life = l;
	}
	
	public void setTexture(Texture[] t){
		texture = t;
	}
	
	public void setTexture(int t){
		currentTexture = t;
	}
	
	public Texture getTexture(){
		return texture[currentTexture];
	}
	
	public void setScaleVel(float sv){
		scaleVel = sv;
	}
	
	public void setDieOnAnimation(boolean b){
		dieEnd = b;
	}
	
	public void setDieOnStop(boolean b){
		dieStill = b;
	}
	
	public boolean isSinglePix(){
		return singlePix;
	}
	
	public float getTextureX1(){
		return textureX1;
	}
	public float getTextureY1(){
		return textureY1;
	}
	public float getTextureX2(){
		return textureX2;
	}
	public float getTextureY2(){
		return textureY2;
	}
}

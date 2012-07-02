package sprite;
import mesh.Material;
import mesh.Mesh;

public class Sprite extends Orientation{
	private Material[] texture;
	private int currentTex = 0;
	private SpriteTypes type;
	private Sprite parent = null;
	private boolean hasMesh = false, alive = true, visible = true, cull = true;
	private Mesh[] mesh;
	private float originalX, originalY, originalZ, originalXR, originalYR, originalZR;
	private Object subClass;
	
	private boolean flipX = false, flipY  = false;
	
	private boolean autoFrame, loop;
	long frameTime, frameTimeRemain;
	
	private float boundWidth, boundHeight, boundDepth;
	
	public Sprite(){
		texture = null;
		
	}
	
	public Sprite(Mesh... m){
		mesh = m;
		texture = null;
		hasMesh = true;
	}
	
	public Sprite(Material[] t, float... f){
		super(f);
		texture = t;
		if(texture.length > 0){
			currentTex = 0;
		}
		autoFrame = false;
	}
	
	public Sprite(Material t, float... f){
		super(f);
		texture = new Material[1];
		texture[0] = t;
		if(texture.length > 0){
			currentTex = 0;
		}
		autoFrame = false;
	}
	
	public Sprite(SpriteTypes ty, Material[] t, float... f){
		super(f);
		texture = t;
		if(texture.length > 0){
			currentTex = 0;
		}
		autoFrame = false;
		type = ty;
	}
	
	public Sprite(float... f){
		super(f);
		autoFrame = false;
	}
	
	//for auto animation
	public Sprite(long frameTime, boolean loop, Material[] t, float... f){
		super(f);
		texture = t;
		if(texture.length > 0){
			currentTex = 0;
		}
		autoFrame = true;
		this.loop = loop;
		this.frameTime = frameTime;
		frameTimeRemain = frameTime;
	}
	
	public void update(long d){
		if(parent == null){
			super.update(d);
		}else{
			super.setX(parent.getX() + originalX);
			super.setY(parent.getY() + originalY);
			super.setZ(parent.getZ() + originalZ);
			super.setXRot(parent.getXRot() + originalXR);
			super.setYRot(parent.getYRot() + originalYR);
			super.setZRot(parent.getZRot() + originalZR);
		}
		
		if(autoFrame){
			if(frameTimeRemain < 0){
				frameTimeRemain = frameTime;
				nextTexture(loop);
			}else{
				frameTimeRemain -= d;
			}
		}
	}
	
	public void autoframe(boolean b, boolean l, long time){
		autoFrame = b;
		frameTime = time;
		frameTimeRemain = time;
		loop = l;
	}
	
	//collision detection
	public boolean isCollision(Sprite s){
		return isCollision(super.getX(), super.getZ(), this.boundWidth, this.boundDepth, s.getX(), s.getZ(), s.getBoundWidth(), s.getBoundHeight());
	}
	
	public boolean isCollision (float b2_x, float b2_y, float b2_w, float b2_h ){
	    if ( ( super.getX() > b2_x + b2_w - 1 ) 
	    		|| (super.getZ() > b2_y + b2_h - 1) 
	    		|| (b2_x > super.getX() + this.getBoundWidth() - 1) 
	    		|| (b2_y > this.getZ() + this.getBoundDepth() - 1)){
	        return false;
	    }else{
	    	return true;
	    }
	}
	
	public boolean isCollision (float b1_x, float b1_y, float b1_w, float b1_h, float b2_x, float b2_y, float b2_w, float b2_h ){
	    if ( ( b1_x > b2_x + b2_w - 1 ) 
	    		|| (b1_y > b2_y + b2_h - 1) 
	    		|| (b2_x > b1_x + b1_w - 1) 
	    		|| (b2_y > b1_y + b1_h - 1)){
	        return false;
	    }else{
	    	return true;
	    }
	}
	
	public Material getTexture(){
		if(texture != null){
			return texture[currentTex];
		}
		return null;
	}
	
	public Material[] getTextureList(){
		return texture;
	}
	
	public Material getTexture(int index){
		return texture[index];
	}
	
	public SpriteTypes getType(){
		return type;
	}
	
	public void setType(SpriteTypes ty){
		type = ty;
	}
	
	public void nextTexture(boolean loop){
		if(loop){
			//System.out.println(currentTex + "::" + (texture.length - 1));
			if(currentTex < texture.length - 1){
				if(texture[currentTex + 1] != null){
					currentTex++;
				}else{
					currentTex = 0;
				}
			}else{
				currentTex = 0;
			}
		}else{
			if(currentTex < texture.length - 1){
				if(texture[currentTex + 1] != null){
					currentTex++;
				}
			}
		}
	}
	
	public void prevTexture(boolean loop){
		if(currentTex <= 0 && loop && texture[texture.length - 1] != null){
			currentTex = texture.length - 1;
		}else if(currentTex < 0 && !loop){
		
		}else if(currentTex > 0){
			if(texture[currentTex - 1] != null){
				currentTex--;
			}
		}
	}
	
	public void setTexture(int index){
		if(index < texture.length){
			currentTex = index;
		}
	}
	
	public int getCurrentTexture(){
		return currentTex;
	}
	
	public int getTextureCount(){
		int c = 0;
		for(int i = 0; i < texture.length; i++){
			if(texture[i] != null){
				c++;
			}
		}
		return c;
	}
	
	//get methods
	public boolean isVisible(){
		return visible;
	}
	public Mesh[] getMesh(){
		return mesh;
	}
	
	public float getBoundWidth(){
		return boundWidth;
	}
	
	public float getBoundHeight(){
		return boundHeight;
	}
	
	public float getBoundDepth(){
		return boundDepth;
	}
	
	public boolean isFlipX(){
		return flipX;
	}
	
	public boolean isFlipY(){
		return flipY;
	}
	
	public Sprite getParent(){
		return parent;
	}
	
	public boolean hasMesh(){
		//System.out.println(hasMesh);
		return hasMesh;
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	//set methods
	public void setAlive(boolean b){
		alive = b;
	}
	
	public void setMesh(Mesh... m){
		mesh = m;
		texture = null;
		hasMesh = true;
	}
	
	public void setBoundWidth(float w){
		boundWidth = w;
	}
	
	public void setBoundHeight(float h){
		boundHeight = h;
	}
	
	public void setBoundDepth(float d){
		boundDepth = d;
	}
	
	public void setTex(Material[] tex){
		texture = tex;
	}
	
	public void setTex(Material tex){
		texture = new Material[1];
		texture[0] = tex;
	}
	
	public void setFlipX(boolean fx){
		flipX = fx;
	}
	
	public void setFlipY(boolean fy){
		flipY = fy;
	}
	
	public void setParent(Sprite p){
		parent = p;
	}
	
	public void setVisible(boolean b){
		visible = b;
	}
	
	public void cullFace(boolean b){
		cull = b;
	}
	
	public void setX(float x){
		super.setX(x);
		originalX = x;
	}
	
	public void setY(float y){
		super.setY(y);
		originalY = y;
	}
	
	public void setZ(float z){
		super.setZ(z);
		originalZ = z;
	}
	
	public void setXRot(float x){
		super.setXRot(x);
		originalXR = x;
	}
	
	public void setYRot(float x){
		super.setYRot(x);
		originalYR = x;
	}
	
	public void setZRot(float x){
		super.setZRot(x);
		originalZR = x;
	}
	
	public void setSubClass(Object o){
		subClass = o;
	}
	
	public Object getSubClass(){
		return subClass;
	}
	
	public boolean cullFace(){
		return cull;
	}
}

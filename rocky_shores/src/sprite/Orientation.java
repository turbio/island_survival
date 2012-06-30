package sprite;

public class Orientation{
	private float x = 0, y = 0, z = 0, xVel = 0, yVel = 0, zVel = 0;
	private float xRot = 0, yRot = 0, zRot = 0, xRotVel = 0, yRotVel = 0, zRotVel = 0;
	private float width = 1, height = 1, depth = 1;
	private float pivotX = 0, pivotY, pivotZ = 0;
	
	public Orientation(float... f){
		for(int i = 0; i < f.length; i++){
			switch(i){
			case 0: x = f[i];	break;
			case 1:	y = f[i];	break;
			case 2:	z = f[i];	break;
			case 3:	xRot = f[i];	break;
			case 4:	yRot = f[i];	break;
			case 5: zRot = f[i];	break;
			case 6:	xVel = f[i];	break;
			case 7: yVel = f[i];	break;
			case 8: zVel = f[i];	break;
			case 9: xRotVel = f[i];	break;
			case 10: yRotVel = f[i];	break;
			case 11: zRotVel = f[i];	break;
			case 12: width = f[i]; break;
			case 13: height = f[i]; break;
			case 14: depth = f[i]; break;
			}
		}
	}
	
	//set methods
	public void setXRot(float xRot){
		this.xRot = xRot;
	}
	
	public void setYRot(float yRot){
		this.yRot = yRot;
	}
	
	public void setZRot(float zRot){
		this.zRot = zRot;
	}
	
	public void setXRotVel(float xRotVel){
		this.xRotVel = xRotVel;
	}
	
	public void setYRotVel(float yRotVel){
		this.yRotVel = yRotVel;
	}
	
	public void setZRotVel(float zRotVel){
		this.zRotVel = zRotVel;
	}
	
	public void setWidth(float width){
		this.width = width;
	}
	
	public void setHeight(float height){
		this.height = height;
	}
	
	public void setDepth(float depth){
		this.depth = depth;
	}
	
	//get methods
	public float getXRot(){
		return xRot;
	}
	
	public float getYRot(){
		return yRot;
	}
	
	public float getZRot(){
		return zRot;
	}
	
	public float getXRotVel(){
		return xRotVel;
	}
	
	public float getYRotVel(){
		return yRotVel;
	}
	
	public float getZRotVel(){
		return zRotVel;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public float getZ(){
		return z;
	}
	
	public float getXVel(){
		return xVel;
	}
	
	public float getYVel(){
		return yVel;
	}
	
	public float getZVel(){
		return zVel;
	}
	
	public float getWidth(){
		return width;
	}
	
	public float getHeight(){
		return height;
	}
	
	public float getDepth(){
		return depth;
	}
	
	public float getPivotX(){
		return pivotX;
	}
	
	public float getPivotY(){
		return pivotY;
	}
	
	public float getPivotZ(){
		return pivotZ;
	}
	
	//set methods
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public void setZ(float z){
		this.z = z;
	}
	
	public void setXVel(float xVel){
		 this.xVel = xVel;
	}
	
	public void setYVel(float yVel){
		this.yVel = yVel;
	}
	
	public void setZVel(float zVel){
		this.zVel = zVel;
	}
	
	public void setPivotX(float x){
		pivotX = x;
	}
	
	public void setPivotY(float y){
		pivotY = y;
	}
	
	public void setPivotZ(float z){
		pivotZ = z;
	}
	
	//update
	public void update(long d){
		x += xVel * ((float)d / 20f);
		y += yVel * ((float)d / 20f);
		z += zVel * ((float)d / 20f);
		xRot += xRotVel * ((float)d / 20f);
		yRot += yRotVel * ((float)d / 20f);
		zRot += zRotVel * ((float)d / 20f);
	}
}

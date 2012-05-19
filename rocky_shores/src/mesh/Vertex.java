package mesh;

import org.newdawn.slick.Color;

public class Vertex {
	private float xPos, yPos, zPos, textureX, textureY;
	private Color color = null;
	private boolean isTextured;
	
	public Vertex(float x, float y, float z){
		xPos = x;
		yPos = y;
		zPos = z;
		isTextured = false;
	}
	
	public Vertex(float x, float y, float z, float texX, float texY){
		xPos = x;
		yPos = y;
		zPos = z;
		textureX = texX;
		textureY = texY;
		isTextured = true;
	}
	
	public Vertex(float x, float y, float z, Color c){
		xPos = x;
		yPos = y;
		zPos = z;
		color = c;
		isTextured = false;
	}
	
	public float getX(){
		return xPos;
	}
	
	public float getY(){
		return yPos;
	}
	
	public float getZ(){
		return zPos;
	}
	
	public float getTexX(){
		return textureX;
	}
	
	public float getTexY(){
		return textureY;
	}
	
	public Color getColor(){
		return color;
	}
	
	public boolean isTextured(){
		return isTextured;
	}
	
	//set
	public void setTexX(float f){
		textureX = f;
	}
	
	public void setTexY(float f){
		textureY = f;
	}
}

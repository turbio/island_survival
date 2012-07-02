package mesh;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class Material {
	private Color color;
	private Texture texture;
	private String name = "";
	
	public Material(){
		
	}
	
	public Material(Color c, String n){
		color = c;
		name = n;
	}
	
	public Material(Texture t){
		texture = t;
	}
	
	public Material(Texture t, String n){
		texture = t;
		name = n;
	}
	
	
	public Texture getTexture(){
		return texture;
	}
	
	public String getName(){
		return name;
	}
	
	public void setTexture(Texture t){
		texture = t;
	}
	
	public void setName(String s){
		name = s;
	}
	
	public Color getColor(){
		return color;
	}
}

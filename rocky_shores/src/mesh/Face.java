package mesh;

import java.util.ArrayList;

public class Face {
	private ArrayList<Vertex> vertex;
	private Boolean isQuad = false, isTri = false, isLine = false, isPoint = false;
	private float normalX = 0.0f, normalY = 0.0f, normalZ = 0.0f;
	private boolean hasNormal;
	
	public Face(){
		
	}
	
	public Face(Vertex... v){
		vertex = new ArrayList<Vertex>();
		if(v.length > 0){
			for(int i = 0; i < v.length; i++){
				vertex.add(v[i]);
			}
		}
		switch(v.length){
		case 0:{
			
		}break;
		case 1:{
			isPoint = true;
		}break;
		case 2:{
			isLine = true;
		}break;
		case 3:{
			isTri = true;
		}break;
		case 4:{
			isQuad = true;
		}break;
		}
		hasNormal = false;
	}
	
	public boolean hasNormal(){
		return hasNormal;
	}
	
	public float getNormalX(){
		return normalX;
	}
	
	public float getNormalY(){
		return normalY;
	}
	
	public float getNormalZ(){
		return normalZ;
	}
	
	public Vertex getVertex(int index){
		return vertex.get(index);
	}
	
	public ArrayList<Vertex> getVertex(){
		return vertex;
	}
	
	public boolean isQuad(){
		return isQuad;
	}
	
	public boolean isTri(){
		return isTri;
	}
	
	public boolean isLine(){
		return isLine;
	}
	
	public boolean isPoint(){
		return isPoint;
	}
	
	//public void setMaterial(String m){
	//	material = m;
	//}
	
	//add methods
	public void addVertex(Vertex v){
		
	}
	
	//set methods
	public void setNormalX(float x){
		normalX = x;
	}
	public void setNormalY(float y){
		normalY = y;
	}
	public void setNormalZ(float z){
		normalZ = z;
	}
	
	public void offsetTexture(float x, float y){
		for(int i = 0; i < vertex.size(); i++){
			vertex.get(i).setOffsetTexX(vertex.get(i).getTexX() + x);
			vertex.get(i).setOffsetTexY(vertex.get(i).getTexY() + y);
		}
	}
}

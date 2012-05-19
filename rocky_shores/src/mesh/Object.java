package mesh;

import java.util.ArrayList;

public class Object {
	private ArrayList<float[]> vertices;
	private ArrayList<float[]> texCoords;
	private ArrayList<float[]> normals;
	private String name;
	
	public Object(String n){
		name = n;
		vertices = new ArrayList<float[]>();
		texCoords = new ArrayList<float[]>();
		normals = new ArrayList<float[]>();
	}
	
	public void addVertex(float x, float y, float z){
		float[] tempVert = {x, y, z};
		vertices.add(tempVert);
	}
	public void addTexCoord(float x, float y){
		float[] tempCoord = {x, y};
		texCoords.add(tempCoord);
	}
	public void addNormal(float x, float y, float z){
		float [] norm = {x, y, z};
		normals.add(norm);
	}
	
	public float[] getVertex(int index){
		return vertices.get(index);
	}
	public float[] getTexCoord(int index){
		return texCoords.get(index);
	}
	public float[] getNormal(int index){
		return normals.get(index);
	}
	public String getName(){
		return name;
	}
	
	public ArrayList<float[]> getVertexList(){
		return vertices;
	}
	public ArrayList<float[]> getTexCoordList(){
		return texCoords;
	}
	public ArrayList<float[]> getNormalList(){
		return normals;
	}
	
}

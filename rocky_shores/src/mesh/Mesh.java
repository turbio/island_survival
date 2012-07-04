package mesh;

import java.util.ArrayList;

import view.DrawModel;

public class Mesh {
	private ArrayList<Face> faces;
	private String name;
	private Material mat;
	private float top, bottom, right, left, front, back;
	private int index = -1;
	
	public Mesh(){
		faces = new ArrayList<Face>();
		setEdges();
	}
	
	public Mesh(String n){
		faces = new ArrayList<Face>();
		name = n;
		setEdges();
	}
	
	public ArrayList<Face> getFaces(){
		return faces;
	}
	
	public void setFaces(ArrayList<Face> f){
		faces = f;
	}
	
	public void addFace(Face f){
		faces.add(f);
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String n){
		name = n;
	}
	
	private void setEdges(){	//return edges of model
		float t = 0, b = 0, r = 0, l = 0, fr = 0, ba = 0;
		boolean _b = false;
		for(int f = 0; f < faces.size(); f++){
			for(int v = 0; v < faces.get(f).getVertex().size(); v++){
				if(faces.get(f).getVertex().get(v).getY() > t){
					t = faces.get(f).getVertex().get(v).getY();
				}
				if(faces.get(f).getVertex().get(v).getY() != b){
					if(_b){
						if(faces.get(f).getVertex().get(v).getY() < b){
							b = faces.get(f).getVertex().get(v).getY();
						}
					}else{
						_b = true;
						b = faces.get(f).getVertex().get(v).getY();
					}
				}
				if(faces.get(f).getVertex().get(v).getX() > r){
					r = faces.get(f).getVertex().get(v).getX();
				}
				if(faces.get(f).getVertex().get(v).getX() < l){
					l = faces.get(f).getVertex().get(v).getX();
				}
				if(faces.get(f).getVertex().get(v).getZ() > fr){
					fr = faces.get(f).getVertex().get(v).getZ();
				}
				if(faces.get(f).getVertex().get(v).getZ() < ba){
					ba = faces.get(f).getVertex().get(v).getZ();
				}
			}
		}
		
		front = fr;
		back = ba;
		right = r;
		left = l;
		top = t;
		bottom = b;
	}
	
	public void setMat(Material m){
		mat = m;
	}
	
	public Material getMat(){
		return mat;
	}
	
	public float getTop(){
		setEdges();
		return top;
	}
	public float getBottom(){
		setEdges();
		return bottom;
	}
	public float getRight(){
		setEdges();
		return right;
	}
	public float getLeft(){
		setEdges();
		return left;
	}
	public float getFront(){
		setEdges();
		return front;
	}
	public float getBack(){
		setEdges();
		return back;
	}
	
	public int getIndex(){
		/*
		if(index == -1){
			DrawModel draw;
			if(mat != null){
				draw = new DrawModel(mat.getTexture());
			}else{
				draw = new DrawModel(null);
			}
			index = draw.compileList(this);
		}
		*/
		return index;
	}
	
	public void setIndex(int i){
		index = i;
	}
}

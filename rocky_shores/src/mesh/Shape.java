package mesh;

public class Shape {
	private Vertex[] vertex;
	private Boolean isQuad = false, isTri = false, isLine = false, isPoint = false;
	
	public Shape(){
		
	}
	
	public Shape(Vertex... v){
		vertex = v;
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
		}
		case 4:{
			isQuad = true;
		}
		}
	}
	
	public Vertex getVertex(int index){
		return vertex[index];
	}
	
	public Vertex[] getVertex(){
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
	
}

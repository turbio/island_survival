package buildings;


import mesh.Face;
import mesh.Material;
import mesh.Mesh;
import mesh.Vertex;
import sprite.Sprite;

public class Fence extends Sprite{
	private float tile = 0.03f;
	
	public Fence(float x1, float z1, float x2, float z2, float x3, float z3, float x4, float z4, Material mat){
		Mesh m = new Mesh();
		Face face = new Face(new Vertex(x1, tile, z1, 0.0f, 1.0f),
				new Vertex(x2, tile, z2, (Math.abs(x1 - x2) + Math.abs(z1 - z2)) / tile, 1.0f),
				new Vertex(x2, 0.0f, z2, (Math.abs(x1 - x2) + Math.abs(z1 - z2)) / tile, 0.0f),
				new Vertex(x1, 0.0f, z1, 0.0f, 0.0f));
		m.addFace(face);
		
		face = new Face(new Vertex(x2, tile, z2, 0.0f, 1.0f),
				new Vertex(x3, tile, z3, (Math.abs(x2 - x3) + Math.abs(z2 - z3)) / tile, 1.0f),
				new Vertex(x3, 0.0f, z3, (Math.abs(x2 - x3) + Math.abs(z2 - z3)) / tile, 0.0f),
				new Vertex(x2, 0.0f, z2, 0.0f, 0.0f));
		m.addFace(face);
		
		face = new Face(new Vertex(x3, tile, z3, 0.0f, 1.0f),
				new Vertex(x4, tile, z4, (Math.abs(x3 - x4) + Math.abs(z3 - z4)) / tile, 1.0f),
				new Vertex(x4, 0.0f, z4, (Math.abs(x3 - x4) + Math.abs(z3 - z4)) / tile, 0.0f),
				new Vertex(x3, 0.0f, z3, 0.0f, 0.0f));
		m.addFace(face);
		
		face = new Face(new Vertex(x4, tile, z4, 0.0f, 1.0f),
				new Vertex(x1, tile, z1, (Math.abs(x4 - x1) + Math.abs(z4 - z1)) / tile, 1.0f),
				new Vertex(x1, 0.0f, z1, (Math.abs(x4 - x1) + Math.abs(z4 - z1)) / tile, 0.0f),
				new Vertex(x4, 0.0f, z4, 0.0f, 0.0f));
		m.addFace(face);
		m.setMat(mat);
		
		super.setMesh(m);
		super.cullFace(false);
	}
	
	public void setAlive(boolean b){
		
	}
}

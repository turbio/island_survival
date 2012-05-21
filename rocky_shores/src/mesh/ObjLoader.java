package mesh;

import static org.lwjgl.opengl.GL11.GL_NEAREST;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class ObjLoader {
	private ArrayList<Mesh> mesh = new ArrayList<Mesh>();
	
	public Mesh[] loadMesh(String s){
		try {
			return loadObject(new BufferedReader(new FileReader(s)), s);
		} catch (FileNotFoundException e) {
			System.out.println("fiel not found");
		}
		return null;
	}
	
	private Mesh[] loadObject(BufferedReader r, String fileLocation){
		mesh = new ArrayList<Mesh>();
		Mesh currentMesh = null;
		
		
		String tempLoc = "";
		for(int i = 0; i < fileLocation.length() - (fileLocation.length() - fileLocation.lastIndexOf("/")); i++){
			tempLoc += fileLocation.charAt(i);
		}
		fileLocation = tempLoc;
		
		
		String line = "";	//holds text from line
		
		ArrayList<Material> texList = new ArrayList<Material>();
		Material mat = null;	//the active texture
		
		////all possible materials
		///ArrayList<Material> materials = new ArrayList<Material>();
		//Material currentMaterial = null;	//the current material to use on all faces
		
		Object currentObject = new Object("null");
		
		try{
			while((line = r.readLine()) != null){	//loop to read every line
				line = line.trim();	//remove dead space from line being read
				
				if(line.length() > 0){	//skip blank lines
					if(line.charAt(0) == 'o' && line.charAt(1) == ' '){	//add another object
						Mesh m = new Mesh();
						mesh.add(m);
						m.setName(line.split("\\s")[1]);
						currentMesh = m;
					}else if(line.charAt(0) == 'v' && line.charAt(1) == ' '){	//add a vertex to the current object
						float[] coord = new float[3];
						String coordText[] = line.split("\\s+");
						for(int i = 1; i <coordText.length; i++){
							coord[i - 1] = Float.parseFloat(coordText[i]);
						}
						currentObject.addVertex(coord[0], coord[1], coord[2]);
					}else if(line.charAt(0) == 'v' && line.charAt(1) == 't'){
						float[] coords = new float[2];
						String[] coordText = line.split("\\s+");
						
						coords[0] = Float.parseFloat(coordText[1]);
						coords[1] = Float.parseFloat(coordText[2]);
						
						currentObject.addTexCoord(coords[0], coords[1]);
					}else if(line.charAt(0) == '#'){
						//System.out.println(line);
					}else if(line.charAt(0) == 'v' && line.charAt(1) == 'n'){
						float[] coords = new float[3];
						String[] coordText = line.split("\\s+");
						
						coords[0] = Float.parseFloat(coordText[1]);
						coords[1] = Float.parseFloat(coordText[2]);
						coords[2] = Float.parseFloat(coordText[3]);
						
						currentObject.addNormal(coords[0], coords[1], coords[2]);
					}if(line.charAt(0) == 'f' && line.charAt(1) == ' '){
						boolean hasNormal = false;
						String[] coordText = line.split("\\s+");
						
						int[] tex = new int[coordText.length - 1];
						int[] vert = new int[coordText.length - 1];
						int[] norm = new int[coordText.length - 1];
						
						Vertex[] vertices = new Vertex[vert.length];
						
						for(int i = 0; i < coordText.length; i++){
							if(coordText[i].contains("/")){
								vert[i - 1] = Integer.parseInt(coordText[i].split("/")[0]);
								tex[i - 1] = Integer.parseInt(coordText[i].split("/")[1]);
								if(coordText[i].split("/").length == 3){
									norm[i - 1] = Integer.parseInt(coordText[i].split("/")[2]);
									hasNormal = true;
								}
							}
						}
						
						for(int i = 0 ; i < vertices.length; i++){
							vertices[i] = new Vertex(currentObject.getVertex(vert[i] - 1)[0],
									currentObject.getVertex(vert[i] - 1)[1],
									currentObject.getVertex(vert[i] - 1)[2], currentObject.getTexCoord(tex[i] - 1)[0], currentObject.getTexCoord(tex[i] - 1)[1]);
						}
						Face f = new Face(vertices);
						if(hasNormal){
							f.setNormalX(currentObject.getNormal(norm[0] - 1)[0]);
							f.setNormalY(currentObject.getNormal(norm[0] - 1)[1]);
							f.setNormalZ(currentObject.getNormal(norm[0] - 1)[2]);
						}
						currentMesh.addFace(f);
						currentMesh.setMat(mat);
						
					}else if(line.startsWith("mtllib")){
						texList = loadMaterial(fileLocation + "/" + line.split(" ")[1]);
					}else if(line.startsWith("usemtl")){
						for(int i = 0; i < texList.size(); i++){
							if(texList.get(i).getName().equalsIgnoreCase(line.split(" ")[1])){
								mat = texList.get(i);
							}
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		Mesh[] m = new Mesh[mesh.size()];
		for(int i = 0; i < m.length; i++){
			m[i] = mesh.get(i);
		}
		
		return m;
	}
	
	public ArrayList<Material> loadMaterial(String file){
		BufferedReader reader = null;
		ArrayList<Material> materials = new ArrayList<Material>();
		String line;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.println("mtl file not found");
			System.exit(0);
		}
		
		try {
			while((line = reader.readLine()) != null){
				if(line.startsWith("newmtl")){
					materials.add(new Material());
					materials.get(materials.size() - 1).setName(line.split(" ")[1]);
				}else if(line.toLowerCase().startsWith("map_kd")){
					String s = "";
					
					for(int i = 0; i < file.split("/").length - 1; i++){
						s += file.split("/")[i] + "/";
					}
					
					materials.get(materials.size() - 1).setTexture(TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(s + line.split("\\\\")[1]), GL_NEAREST));
					//System.exit(0);
				}
			}
		} catch (IOException e) {System.out.println("bad");}
		return materials;
	}
}
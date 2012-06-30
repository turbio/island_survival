package view;

import static org.lwjgl.opengl.GL11.GL_COMPILE;
import static org.lwjgl.opengl.GL11.glEndList;
import static org.lwjgl.opengl.GL11.glNewList;

import org.lwjgl.opengl.GL11;

public class DrawModel {
	public void Draw(){
		
	}
	
	public int compileList(){
		int index = GL11.glGenLists(1);
		
		glNewList(index, GL_COMPILE);
		
		glEndList();
		
		return index; 
	}
}

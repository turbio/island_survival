package view;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

public class InitGL {
	
	public InitGL(int WIDTH, int HEIGHT, float[] ambientLight, float[] diffuseLight, float lightPosition[]){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		
		gluPerspective(45.0f, WIDTH / HEIGHT, 0.1f, 100.0f);
		
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		
		glShadeModel(GL_SMOOTH);
		
		glClearDepth(1.0f);
		glEnable(GL_DEPTH_TEST);
		glDepthFunc(GL_LEQUAL);
		
		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
		
		ByteBuffer temp = ByteBuffer.allocateDirect(16);
		temp.order(ByteOrder.nativeOrder());
		glLight(GL_LIGHT1, GL_AMBIENT, (FloatBuffer)temp.asFloatBuffer().put(ambientLight).flip());
		glLight(GL_LIGHT1, GL_DIFFUSE, (FloatBuffer)temp.asFloatBuffer().put(diffuseLight).flip());
		glLight(GL_LIGHT1, GL_POSITION, (FloatBuffer)temp.asFloatBuffer().put(lightPosition).flip());
		glEnable(GL_LIGHT1);
		glEnable(GL_LIGHTING);
		
		glEnable(GL_TEXTURE_2D);
		
		glColor4f(1.0f,1.0f,1.0f,0.5f);         // Full Brightness, 50% Alpha ( NEW )
		glBlendFunc(GL_SRC_ALPHA, GL_SRC_COLOR);
		
		glEnable(GL_ALPHA_TEST);
		glAlphaFunc(GL_GREATER, 0.0f);
		
		FloatBuffer fogColor = BufferUtils.createFloatBuffer(4);
		fogColor.put(0.0f).put(.8f).put(0.0f).put(1.0f).flip();
		glFogi(GL_FOG_MODE, GL_LINEAR); 	// Fog Mode	//GL_EXP, GL_EXP2, GL_LINEAR
		glFog(GL_FOG_COLOR, fogColor);	// Set Fog Color
		glFogf(GL_FOG_START, 6.0f);	// Fog Start Depth
		glFogf(GL_FOG_END, 10.0f);	// Fog End Depth
		glEnable(GL_FOG);
		
		glEnable(GL_DEPTH);
	}
}

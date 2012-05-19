/*
package view;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

import particles.Particle;

import sprite.Sprite;
import world.Camera;
import mesh.Mesh;
import model.Model;

public class Render3d {
	private Model controll;
	private Sprite water;
	private Camera camera;
	
	private Texture[][] texture;
	
	public Render3d(Model c, Sprite w, Texture[][] t, Camera cam){
		controll = c;
		water = w;
		texture = t;
		camera = cam;
	}
	
	public void Render(){
		drawspec();	//for stuff i don't want to port
		
		for(int i = 0; i < controll.getMobList().size(); i++){
			glShadeModel(GL_SMOOTH);
			glDisable(GL_LIGHTING);
			glEnable(GL_BLEND);
			glLineWidth(1.0f);
			glColor3f(1.0f, 0.0f, 0.0f);
			glBegin(GL_LINES);
				glVertex3f(controll.getMobList().get(i).getX(), 0.005f, controll.getMobList().get(i).getZ());
				glVertex3f(controll.getMobList().get(i).getDesX(), 0.005f, controll.getMobList().get(i).getDesZ());
			glEnd();
		}
		glDisable(GL_BLEND);
		glEnable(GL_LIGHTING);
		
		//draw sprites
		for(int i = 0; i < controll.getSpriteList().size(); i++){
			glLoadIdentity();	//reset
			if(controll.getSpriteList().get(i).getTexture() != null){
				controll.getSpriteList().get(i).getTexture().bind();	//set texture
			}
			if(controll.getSpriteList().get(i).getMaterial() != null){
				controll.getSpriteList().get(i).getMaterial().getTexture().bind();
			}
			if(controll.getSpriteList().get(i).isVisible()){
				
				glTranslatef(camera.getX(), camera.getY(), camera.getZ());	//align cords with floor		
				glRotatef(camera.getXRot(), 1.0f, 0.0f, 0.0f);	//align x rot to camera
				glRotatef(camera.getYRot(), 0.0f, 1.0f, 0.0f);	//align y rot to camera
				glTranslatef(controll.getSpriteList().get(i).getX(), controll.getSpriteList().get(i).getY(), controll.getSpriteList().get(i).getZ());	//translate the object
				if(!controll.getSpriteList().get(i).hasMesh()){
					glRotatef(-camera.getYRot(), 0.0f, 1.0f, 0.0f);	//align y rot to camera
					glRotatef(-camera.getXRot(), 1.0f, 0.0f, 0.0f);	//align x rot to floor
				}
				glRotatef(controll.getSpriteList().get(i).getYRot(), 0.0f, 1.0f, 0.0f);
				glRotatef(controll.getSpriteList().get(i).getXRot(), 1.0f, 0.0f, 0.0f);
				glRotatef(controll.getSpriteList().get(i).getZRot(), 0.0f, 0.0f, 1.0f);
				glScalef(controll.getSpriteList().get(i).getWidth(), controll.getSpriteList().get(i).getHeight(), controll.getSpriteList().get(i).getDepth());
				
				
				//draw to screen
				if(controll.getSpriteList().get(i).hasMesh()){
					Mesh[] m = controll.getSpriteList().get(i).getMesh();
					//glTranslatef(i * 0.3f, 0.0f, 0.0f);
					
					//glScalef(0.025f, 0.025f, 0.025f);
					//glScalef(0.1f, 0.1f, 0.1f);
					
					for(int sm = 0; sm < m.length; sm++){	//loop through meshes
						for(int f = 0; f < controll.getSpriteList().get(i).getMesh()[sm].getFaces().size(); f++){	//loop througth faces
							//glColor3f(1 - (f / 64f), 1 - (f / 64f), 1 - (f / 64f));
							if(m[sm].getFaces().get(f).getMaterial() != null){
								m[sm].getFaces().get(f).getMaterial().getTexture().bind();
							}
							if(controll.getSpriteList().get(i).getMesh()[sm].getFaces().get(f).isQuad()){	
								glNormal3f(controll.getSpriteList().get(i).getMesh()[sm].getFaces().get(f).getNormalX(),
										controll.getSpriteList().get(i).getMesh()[sm].getFaces().get(f).getNormalY(),
										controll.getSpriteList().get(i).getMesh()[sm].getFaces().get(f).getNormalZ());
								glBegin(GL_QUADS);
									for(int v = 0; v < m[sm].getFaces().get(f).getVertex().size(); v++){	//loop throught vertices
										glTexCoord2f(m[sm].getFaces().get(f).getVertex().get(v).getTexX(), -m[sm].getFaces().get(f).getVertex().get(v).getTexY());
										glVertex3f(m[sm].getFaces().get(f).getVertex(v).getX(), m[sm].getFaces().get(f).getVertex(v).getY(), m[sm].getFaces().get(f).getVertex(v).getZ());
									}
								glEnd();
							}else if(m[sm].getFaces().get(f).isTri()){
								glNormal3f(controll.getSpriteList().get(i).getMesh()[sm].getFaces().get(f).getNormalX(),
										controll.getSpriteList().get(i).getMesh()[sm].getFaces().get(f).getNormalY(),
										controll.getSpriteList().get(i).getMesh()[sm].getFaces().get(f).getNormalZ());
								glBegin(GL_TRIANGLES);
									for(int v = 0; v < m[sm].getFaces().get(f).getVertex().size(); v++){
										glTexCoord2f(m[sm].getFaces().get(f).getVertex().get(v).getTexX(), -m[sm].getFaces().get(f).getVertex().get(v).getTexY());
										glVertex3f(m[sm].getFaces().get(f).getVertex(v).getX(), m[sm].getFaces().get(f).getVertex(v).getY(), m[sm].getFaces().get(f).getVertex(v).getZ());
									}
								glEnd();
							}
						}
					}
					
				}else{
					glEnable(GL_LIGHTING);
					glEnable(GL_ALPHA_TEST);
					if(controll.getSpriteList().get(i).isFlipX()){
						glNormal3f(0.0f, 0.0f, 1.0f);
						glBegin(GL_QUADS);
						glTexCoord2f(1.0f, 0.0f);	glVertex3f(-1.0f, 1.0f, 0.0f);	//top left
						glTexCoord2f(0.0f, 0.0f);	glVertex3f(1.0f, 1.0f, 0.0f);	//top right
						glTexCoord2f(0.0f, 1.0f);	glVertex3f(1.0f, -1.0f, 0.0f);	//bottom right
						glTexCoord2f(1.0f, 1.0f);	glVertex3f(-1.0f, -1.0f, 0.0f);	//bottom left
					glEnd();
					}else if(!controll.getSpriteList().get(i).isFlipX()){
						glNormal3f(0.0f, 0.0f, 1.0f);
						glBegin(GL_QUADS);
						glTexCoord2f(0.0f, 0.0f);	glVertex3f(-1.0f - controll.getSpriteList().get(i).getPivotX(), 1.0f - controll.getSpriteList().get(i).getPivotY(), 0.0f);	//top left
						glTexCoord2f(1.0f, 0.0f);	glVertex3f(1.0f - controll.getSpriteList().get(i).getPivotX(), 1.0f - controll.getSpriteList().get(i).getPivotY(), 0.0f);	//top right
						glTexCoord2f(1.0f, 1.0f);	glVertex3f(1.0f - controll.getSpriteList().get(i).getPivotX(), -1.0f - controll.getSpriteList().get(i).getPivotY(), 0.0f);	//bottom right
						glTexCoord2f(0.0f, 1.0f);	glVertex3f(-1.0f - controll.getSpriteList().get(i).getPivotX(), -1.0f - controll.getSpriteList().get(i).getPivotY(), 0.0f);	//bottom left
					glEnd();
					}
				}
			}
		}
		
		//draw particles
		for(int m = 0; m < controll.getMiters().size(); m++){	//loop through particle emiters
			for(int p = 0; p < controll.getMiters().get(m).getParticle().size(); p++){
				
				glEnable(GL_BLEND);
				Particle part = controll.getMiters().get(m).getParticle().get(p);
				glLoadIdentity();
				if(part.getTexture() != null){
					part.getTexture().bind();
					glTranslatef(camera.getX(), camera.getY(), camera.getZ());	//align cords with camera		
					glRotatef(camera.getXRot(), 1.0f, 0.0f, 0.0f);	//align x rot to camera
					glRotatef(camera.getYRot(), 0.0f, 1.0f, 0.0f);	//align y rot to camera
					glTranslatef(part.getX(), part.getY(), part.getZ());
					glRotatef(-camera.getYRot(), 0.0f, 1.0f, 0.0f);	//align y rot to camera
					glRotatef(-camera.getXRot(), 1.0f, 0.0f, 0.0f);	//align x rot to floor
					glScalef(part.getWidth(), part.getHeight(), 0.0f);
					
					glBegin(GL_QUADS);
						glTexCoord2f(part.getTextureX1(), part.getTextureY1());	glVertex3f(-1.0f, 1.0f, 0.0f); //upper left
						glTexCoord2f(part.getTextureX2(), part.getTextureY1());	glVertex3f(1.0f, 1.0f, 0.0f);	//upper right
						glTexCoord2f(part.getTextureX2(), part.getTextureY2());	glVertex3f(1.0f, -1.0f, 0.0f);	//lower right
						glTexCoord2f(part.getTextureX1(), part.getTextureY2());	glVertex3f(-1.0f, -1.0f, 0.0f);	//lower left
					glEnd();
				}else{
					
				}
				
				//System.out.println(controll.getMiters().get(m).getParticle().size());
			}
		}
	}
	
	private void drawspec(){
		//draw water
		glLoadIdentity();	//reset
		water.getTexture().bind();	//set texture
		glTranslatef(camera.getX(), camera.getY(), camera.getZ());	//align cords with camera		
		glRotatef(camera.getXRot(), 1.0f, 0.0f, 0.0f);	//align x rot to camera
		glRotatef(camera.getYRot(), 0.0f, 1.0f, 0.0f);	//align y rot to floor		
		glTranslatef(water.getX(), water.getY(), water.getZ());	//translate the object	
		float map = 100;
		//draw to screen
		glBegin(GL_QUADS);
			glNormal3f(0.0f, 1.0f, 0.0f);
			glTexCoord2f(0.0f, map);	glVertex3f(-10.0f, 0.0f, -10.0f);
			glTexCoord2f(0.0f, 0.0f);	glVertex3f(10.0f, 0.0f, -10.0f);
			glTexCoord2f(map, 0.0f);	glVertex3f(10.0f, 0.0f, 10.0f);
			glTexCoord2f(map, map);	glVertex3f(-10.0f, 0.0f, 10.0f);
		glEnd();
		
		//draw sky box
		glLoadIdentity();
		texture[0][1].bind();
		glTranslatef(camera.getX(), camera.getY(), camera.getZ());	//align cords with camera		
		glRotatef(camera.getXRot(), 1.0f, 0.0f, 0.0f);	//align x rot to camera
		glRotatef(camera.getYRot(), 0.0f, 1.0f, 0.0f);	//align y rot to floor		
		glTranslatef(water.getX(), water.getY(), water.getZ());	//translate the object	
		//draw to screen
		glBegin(GL_QUADS);
			//front
			glTexCoord2f(0, 0);	glVertex3f(-10.0f, 20.0f, 10.0f);	//top left
			glTexCoord2f(1, 0);	glVertex3f(10.0f, 20.0f, 10.0f);	//top right
			glTexCoord2f(1, 1);	glVertex3f(10.0f, 0.0f, 10.0f);	//bottom right
			glTexCoord2f(0, 1);	glVertex3f(-10.0f, 0.0f, 10.0f);	//bottom left
			
			//back
			glTexCoord2f(0, 0);	glVertex3f(-10.0f, 20.0f, -10.0f);	//top left
			glTexCoord2f(1, 0);	glVertex3f(10.0f, 20.0f, -10.0f);	//top right
			glTexCoord2f(1, 1);	glVertex3f(10.0f, 0.0f, -10.0f);	//bottom right
			glTexCoord2f(0, 1);	glVertex3f(-10.0f, 0.0f, -10.0f);	//bottom left
			
			//right
			glTexCoord2f(0, 0);	glVertex3f(10.0f, 20.0f, 10.0f);	//top left
			glTexCoord2f(1, 0);	glVertex3f(10.0f, 20.0f, -10.0f);	//top right
			glTexCoord2f(1, 1);	glVertex3f(10.0f, 0.0f, -10.0f);	//bottom right
			glTexCoord2f(0, 1);	glVertex3f(10.0f, 0.0f, 10.0f);	//bottom left
			
			//left
			glTexCoord2f(0, 0);	glVertex3f(-10.0f, 20.0f, 10.0f);	//top left
			glTexCoord2f(1, 0);	glVertex3f(-10.0f, 20.0f, -10.0f);	//top right
			glTexCoord2f(1, 1);	glVertex3f(-10.0f, 0.0f, -10.0f);	//bottom right
			glTexCoord2f(0, 1);	glVertex3f(-10.0f, 0.0f, 10.0f);	//bottom left
		glEnd();
	}
}

*/
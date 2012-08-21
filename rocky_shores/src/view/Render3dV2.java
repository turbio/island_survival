package view;

import static org.lwjgl.opengl.GL11.*;

import particles.Particle;

import world.Camera;
import model.Model;

public class Render3dV2 {
	private Model model;
	private Camera camera;
	private DrawModel draw;
	private float scale = 1.0f;
	
	public Render3dV2(Model m, Camera c){
		model = m;
		camera = c;
		draw = new DrawModel(model.getMaterial("pack").getTexture());
	}
	int test = 0;
	public void render(){
		//model.getMaterial("matr").getTexture().bind();
		
		/*
		glTranslatef(camera.getX(), camera.getY(), camera.getZ());	//align cords with floor		
		glRotatef(camera.getXRot(), 1.0f, 0.0f, 0.0f);	//align x rot to camera
		glRotatef(camera.getYRot(), 0.0f, 1.0f, 0.0f);	//align y rot to camera
		
		for(int i = 0; i < model.getMobList().size(); i++){
			glShadeModel(GL_SMOOTH);
			glDisable(GL_LIGHTING);
			glDisable(GL_BLEND);
			glLineWidth(0.5f);
			glColor3f(1.0f, 0.0f, 0.0f);
			
			glBegin(GL_LINES);
				glVertex3f(model.getMobList().get(i).getX(), 0.005f, model.getMobList().get(i).getZ());
				glVertex3f(model.getMobList().get(i).getDesX(), 0.005f, model.getMobList().get(i).getDesZ());
			glEnd();
		}
		glBegin(GL_LINES);
			glVertex3f(-1.0f, 0.005f, -0.3f);
			glVertex3f(1.0f, 0.005f, -0.3f);
		glEnd();
		*/
		glEnable(GL_LIGHTING);
		glEnable(GL_BLEND);
		for(int i = 0; i < model.getSpriteList().size(); i++){
			if(model.getSpriteList().get(i).cullFace()){
				glEnable(GL_CULL_FACE);
			}else{
				glDisable(GL_CULL_FACE);
			}
			
			glLoadIdentity();	//reset
			
			glRotatef(camera.getXRot(), 1.0f, 0.0f, 0.0f);	//align x rot to camera
			glRotatef(camera.getYRot(), 0.0f, 1.0f, 0.0f);	//align y rot to camera
			glTranslatef(camera.getX(), camera.getY(), camera.getZ());	//align cords with floor		
			
			glTranslatef(model.getSpriteList().get(i).getX() * scale, model.getSpriteList().get(i).getY() * scale, model.getSpriteList().get(i).getZ() * scale);	//translate the object
			if(!model.getSpriteList().get(i).hasMesh()){
				glRotatef(-camera.getYRot(), 0.0f, 1.0f, 0.0f);	//align y rot to camera
				glRotatef(-camera.getXRot(), 1.0f, 0.0f, 0.0f);	//align x rot to floor
			}
			glRotatef(model.getSpriteList().get(i).getYRot(), 0.0f, 1.0f, 0.0f);
			glRotatef(model.getSpriteList().get(i).getXRot(), 1.0f, 0.0f, 0.0f);
			glRotatef(model.getSpriteList().get(i).getZRot(), 0.0f, 0.0f, 1.0f);
			glScalef(model.getSpriteList().get(i).getWidth(), model.getSpriteList().get(i).getHeight(), model.getSpriteList().get(i).getDepth());
			
			glScalef(scale, scale, scale);
			
			if(model.getSpriteList().get(i).hasMesh() && model.getSpriteList().get(i).isVisible()){
				for(int m = 0; m < model.getSpriteList().get(i).getMesh().length; m++){
					if(model.getSpriteList().get(i).getMesh()[m].getIndex() != -1){
						glCallList(model.getSpriteList().get(i).getMesh()[m].getIndex());
						if(model.getSpriteList().get(i).getMesh()[m].getIndex() > test){
							test = model.getSpriteList().get(i).getMesh()[m].getIndex();
							System.out.println(test);
						}
					}else{
						if(model.getSpriteList().get(i).getTexture() == null){
							draw.Draw(model.getSpriteList().get(i).getMesh()[m], model.getMaterial("pack").getTexture());
						}else{
							draw.Draw(model.getSpriteList().get(i).getMesh()[m], model.getSpriteList().get(i).getTexture().getTexture());
						}
					}
				}
			}
		}
		
		glDisable(GL_CULL_FACE);
		for(int m = 0; m < model.getMiters().size(); m++){	//loop through particle emiters
			for(int p = 0; p < model.getMiters().get(m).getParticle().size(); p++){
				glEnable(GL_BLEND);
				Particle part = model.getMiters().get(m).getParticle().get(p);
				glLoadIdentity();
				if(part.getTexture() != null){
					part.getTexture().bind();
					
					glRotatef(camera.getXRot(), 1.0f, 0.0f, 0.0f);	//align x rot to camera
					glRotatef(camera.getYRot(), 0.0f, 1.0f, 0.0f);	//align y rot to camera
					glTranslatef(camera.getX(), camera.getY(), camera.getZ());	//align cords with camera		
					
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
				
				//System.out.println(model.getMiters().get(m).getParticle().size());
			}
		}
	}
}

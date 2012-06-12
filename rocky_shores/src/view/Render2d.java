package view;

import static org.lwjgl.opengl.GL11.*;

import gui.Gui;
import gui.GuiElementType;

public class Render2d {
	private Gui gui;
	
	
	public Render2d(Gui g){
		gui = g;
	}
	
	@SuppressWarnings("deprecation")
	public void render(int WIDTH, int HEIGHT){
		for(int i = 0; i < gui.getGuiList().size(); i++){
			glLoadIdentity();
			if(gui.getGuiList().get(i).getType() == GuiElementType.IMAGEICON){
				glScalef(gui.getScale(), gui.getScale(), gui.getScale());
				
				glTranslatef(gui.getGuiList().get(i).getX(), gui.getGuiList().get(i).getY(), 0);
				glRotatef(0.0f, 1.0f, 1.0f, gui.getGuiList().get(i).getRotation());
			}
			
			if(gui.getGuiList().get(i).getType() == GuiElementType.SOLIDE){
				glBlendFunc(GL_SRC_ALPHA, GL_SRC_COLOR);
				glColor3f(gui.getGuiList().get(i).getColor().getRed(), gui.getGuiList().get(i).getColor().getGreen(), gui.getGuiList().get(i).getColor().getBlue());
				glBegin(GL_QUADS);
					glVertex2f(gui.getGuiList().get(i).getX(), gui.getGuiList().get(i).getY());	//top left
					glVertex2f(gui.getGuiList().get(i).getWidth() + gui.getGuiList().get(i).getX(), gui.getGuiList().get(i).getY());	//top right
					glVertex2f(gui.getGuiList().get(i).getWidth() + gui.getGuiList().get(i).getX(), gui.getGuiList().get(i).getY() + gui.getGuiList().get(i).getHeight());	//bottom right
					glVertex2f(gui.getGuiList().get(i).getX(), gui.getGuiList().get(i).getY() + gui.getGuiList().get(i).getHeight());	//bottom left
				glEnd();
			}else if(gui.getGuiList().get(i).getType() == GuiElementType.LABEL){
				glEnable(GL_BLEND);
				glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
				gui.getGuiList().get(i).getFont().drawString(gui.getGuiList().get(i).getX(), gui.getGuiList().get(i).getY(), gui.getGuiList().get(i).getString(),
						gui.getGuiList().get(i).getColor());
				glDisable(GL_BLEND);
			}else if(gui.getGuiList().get(i).getType() == GuiElementType.IMAGEICON){
				glColor3f(1.0f, 1.0f, 1.0f);
				glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
				glEnable(GL_ALPHA_TEST);
				glEnable(GL_BLEND);
				gui.getGuiList().get(i).getTexture().bind();
				glBegin(GL_QUADS);
					glTexCoord2f(0, 0);	glVertex2f(0, 0);	//top left
					glTexCoord2f(1, 0);	glVertex2f(gui.getGuiList().get(i).getWidth(), 0);	//top right
					glTexCoord2f(1, 1);	glVertex2f(gui.getGuiList().get(i).getWidth(), gui.getGuiList().get(i).getHeight());	//bottom right
					glTexCoord2f(0, 1);	glVertex2f(0, gui.getGuiList().get(i).getHeight());	//bottom left
				glEnd();
			}
		}
	}
}

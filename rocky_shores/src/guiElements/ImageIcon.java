package guiElements;

import gui.GuiElement;
import gui.GuiElementType;

import org.newdawn.slick.opengl.Texture;

public class ImageIcon extends GuiElement{
	private Texture[] tex;
	private int activeTexture;
	
	public ImageIcon(int x, int y, int w, int h, Texture... t){
		super(x, y, w, h, GuiElementType.IMAGEICON);
		tex = t;
		super.setTex(t);
		super.setTex(0);
	}
	
	public ImageIcon(int x, int y, Texture... t){
		super(x, y, t[0].getImageWidth(), t[0].getImageHeight(), GuiElementType.IMAGEICON);
		tex = t;
		super.setTex(t);
		super.setTex(0);
	}
	
	public Texture getTex(){
		return tex[activeTexture];
	}
	
	public void setTex(int i){
		activeTexture = i;
	}
	
	public int getCurrentTex(){
		return activeTexture;
	}
}

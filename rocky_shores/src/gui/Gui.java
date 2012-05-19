package gui;

import java.awt.Font;
import java.io.InputStream;
import java.util.ArrayList;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

@SuppressWarnings("deprecation")
public class Gui {
	private ArrayList<GuiElement> elements;
	private int width, height;
	private TrueTypeFont font;
	
	public Gui(String fontPath, int w, int h){
		setWidth(w);
		setHeight(h);
		elements = new ArrayList<GuiElement>();
		try{
			InputStream inputStream = ResourceLoader.getResourceAsStream(fontPath);
			
			Font tempFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			tempFont = tempFont.deriveFont(16.0f);
			font = new TrueTypeFont(tempFont, true);
		}catch(Exception e){
			System.out.println("font load error");
		}
	}
	
	public TrueTypeFont getFont(){
		return font;
	}
	
	public int getWidth(String s){
		return font.getWidth(s);
		
	}
	
	public int getHeight(String s){
		return font.getHeight(s);
	}
	
	public void add(GuiElement e){
		elements.add(e);
	}
	
	public void add(ArrayList<GuiElement> e){
		for(int i = 0; i < e.size(); i++){
			elements.add(e.get(i));
		}
	}
	
	public ArrayList<GuiElement> getGuiList(){
		return elements;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}

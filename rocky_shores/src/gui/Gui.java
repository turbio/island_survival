package gui;

import java.awt.Font;
import java.io.InputStream;
import java.util.ArrayList;

import model.Model;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

@SuppressWarnings("deprecation")
public class Gui {
	private ArrayList<GuiElement> elements;
	private TrueTypeFont font;
	private int scale = 1;
	
	public Gui(String fontPath){
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
	
	public void add(GuiElement e){
		elements.add(e);
		resize();
	}
	
	public void add(ArrayList<GuiElement> e){
		for(int i = 0; i < e.size(); i++){
			elements.add(e.get(i));
		}
		resize();
	}
	
	public ArrayList<GuiElement> getGuiList(){
		return elements;
	}
	
	public void resize(){
		for(int i = 0; i < elements.size(); i++){
			elements.get(i).setPosition(Model.width, Model.height, scale);
		}
	}
	
	public int getScale(){
		return scale;
	}
	
	public void setScale(int f){
		scale = f;
	}
}

package gui;
import org.newdawn.slick.Color;


public class Solide extends GuiElement{
	public Solide(int x, int y, int w, int h){
		super(x, y, w, h, GuiElementType.SOLIDE);
		super.setColor(new Color(0.0f, 0.0f, 0.0f));
	}
	
	public Solide(int x, int y, int w, int h, Color c){
		super(x, y, w, h, GuiElementType.SOLIDE);
		super.setColor(c);
	}
}

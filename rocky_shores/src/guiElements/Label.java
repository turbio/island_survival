package guiElements;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import gui.GuiElement;
import gui.GuiElementType;

@SuppressWarnings("deprecation")
public class Label extends GuiElement{
	public Label(int x, int y, String s, TrueTypeFont f, Color c){
		super(x, y, s, f, c, GuiElementType.LABEL);
	}
}

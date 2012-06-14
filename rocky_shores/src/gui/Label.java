package gui;

import org.newdawn.slick.Color;

public class Label extends GuiElement{
	public Label(int x, int y, String s, Color c){
		super(x, y, s, c, GuiElementType.LABEL);
	}
}

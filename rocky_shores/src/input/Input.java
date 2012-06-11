package input;

import model.Model;
import gui.Gui;
import gui.GuiElement;
import guiElements.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Input {
	private int mouseX, mouseY;
	private float wheel;
	private boolean mouseDown;
	private Model model;
	
	private ImageIcon pauseGameMenuBg, mainGameMenuBg;
	
	public Input(Model m, Gui gui){
		model = m;
		
		gui.setScale(2);
		pauseGameMenuBg = new ImageIcon(0, 0, m.getMaterial("gui/in_game_options_bg").getTexture());
		pauseGameMenuBg.setPosition(GuiElement.POSITION_TOP_LEFT);
		
		mainGameMenuBg = new ImageIcon(0, 0, m.getMaterial("gui/main_in_game_menu_bg").getTexture());
		mainGameMenuBg.setPosition(GuiElement.POSITION_TOP);
		
		gui.add(pauseGameMenuBg);
		gui.add(mainGameMenuBg);
	}
	
	public void update(){
		while(Keyboard.next()){
			if(Keyboard.getEventKeyState()){
				model.keyPressed(Keyboard.getEventKey());
			}else{
				model.keyReleased(Keyboard.getEventKey());
			}
		}
		
		float i;
		if((i = (float)Mouse.getDWheel()) != wheel){
			wheel = i;
			model.mouseWheelEvent(i);
		}
		
		mouseX = Mouse.getX();
		mouseY = Mouse.getY();
	}
	
	//getMethods
	public boolean isMouseDown(){
		return mouseDown;
	}
	
	public int getMouseX(){
		return mouseX;
	}
	
	public int getMouseY(){
		return mouseY;
	}
}

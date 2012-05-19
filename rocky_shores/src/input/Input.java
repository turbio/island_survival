package input;

import model.Model;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Input {
	private int mouseX, mouseY;
	private float wheel;
	private boolean mouseDown;
	private Model model;
	
	public Input(Model m){
		model = m;
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

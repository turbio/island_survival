package gui;

import model.DayNightCycle;
import model.Model;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;

import world.World;

public class Input {
	private int mouseX, mouseY;
	private float wheel;
	private boolean mouseDown;
	private Model model;
	private DayNightCycle cycle;
	
	private Color textWhite = Color.white;
	
	private ImageIcon pauseGameMenuBg, mainGameMenuBg, timeDialHand;
	private Label foodCount, rockCount, woodCount, villagerCount, soldierCount;
	
	public Input(Model m, Gui gui, DayNightCycle c){
		model = m;
		cycle = c;
		
		gui.setScale(2);
		
		mainGameMenuBg = new ImageIcon(0, 0, m.getMaterial("gui/main_in_game_menu_bg").getTexture());
		mainGameMenuBg.setPosition(GuiElement.POSITION_TOP);
		
		pauseGameMenuBg = new ImageIcon(0, 0, m.getMaterial("gui/in_game_options_bg").getTexture());
		pauseGameMenuBg.setPosition(GuiElement.POSITION_TOP_LEFT);
		
		timeDialHand = new ImageIcon(mainGameMenuBg.getX() + 165, 29, m.getMaterial("gui/dial").getTexture());
		timeDialHand.setRotation(0);
		timeDialHand.setPivitPoint(GuiElement.PIVIT_CENTER);
		
		foodCount = new Label(mainGameMenuBg.getX() + 20, 9, "food", textWhite);
		rockCount = new Label(foodCount.getX(), 22, "rock", textWhite);
		woodCount = new Label(foodCount.getX(), 35, "wood", textWhite);
		villagerCount = new Label(mainGameMenuBg.getX() + 203, 9, "villager", textWhite);
		soldierCount = new Label(villagerCount.getX(), 22, "soldier", textWhite);
		
		gui.add(pauseGameMenuBg);
		gui.add(mainGameMenuBg);
		gui.add(timeDialHand);
		gui.add(foodCount);
		gui.add(rockCount);
		gui.add(woodCount);
		gui.add(villagerCount);
		gui.add(soldierCount);
	}
	
	public void updatePos(){
		timeDialHand.setX(mainGameMenuBg.getX() + 163);
		foodCount.setX(mainGameMenuBg.getX() + 20);
		rockCount.setX(foodCount.getX());
		woodCount.setX(foodCount.getX());
		villagerCount.setX(mainGameMenuBg.getX() + 203);
		soldierCount.setX(villagerCount.getX());
	}
	
	public void update(){
		//timeDialHand.setRotation();
		
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

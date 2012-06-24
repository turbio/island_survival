package gui;

import java.util.ArrayList;

import mob.Mob;
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
	private World world;
	
	private Color textWhite = Color.white;
	
	private ImageIcon pauseGameMenuBg, mainGameMenuBg, timeDialHand, listScrollBar;
	private Label foodCount, rockCount, woodCount, villagerCount, soldierCount, gameTime;
	
	//villager list bg
	private ImageIcon bottom, top;
	public ArrayList<GuiElement> bg;
	
	public Input(Model m, Gui gui, DayNightCycle c, World w){
		model = m;
		cycle = c;
		world = w;
		
		gui.setScale(2);
		
		mainGameMenuBg = new ImageIcon(0, 0, m.getMaterial("gui/main_in_game_menu_bg").getTexture());
		mainGameMenuBg.setPosition(GuiElement.POSITION_TOP);
		
		pauseGameMenuBg = new ImageIcon(0, 0, m.getMaterial("gui/in_game_options_bg").getTexture());
		pauseGameMenuBg.setPosition(GuiElement.POSITION_TOP_LEFT);
		
		timeDialHand = new ImageIcon(mainGameMenuBg.getX() + 165, 29, m.getMaterial("gui/dial").getTexture());
		timeDialHand.setRotation(0);
		timeDialHand.setPivitPoint(GuiElement.PIVIT_CENTER);
		
		gameTime = new Label(pauseGameMenuBg.getX() + 14, 28, "clock", textWhite);
		foodCount = new Label(mainGameMenuBg.getX() + 20, 9, "food", textWhite);
		rockCount = new Label(foodCount.getX(), 22, "rock", textWhite);
		woodCount = new Label(foodCount.getX(), 35, "wood", textWhite);
		villagerCount = new Label(mainGameMenuBg.getX() + 203, 9, "villager", textWhite);
		soldierCount = new Label(villagerCount.getX(), 22, "soldier", textWhite);
		
		//villagerList 
		listScrollBar = new ImageIcon(0, 0, m.getMaterial("gui/scrollbar").getTexture());
		listScrollBar.setPosition(GuiElement.POSITION_TOP_RIGHT);
		listScrollBar.setX(-100);
		
		top = new ImageIcon(0, 0, m.getMaterial("item_top").getTexture());
		top.setPosition(ImageIcon.POSITION_TOP_RIGHT);
		
		bottom = new ImageIcon(0, 0, m.getTexture("item_bottom"));
		bottom.setPosition(ImageIcon.POSITION_TOP_RIGHT);
		
		bg = new ArrayList<GuiElement>();
		for(int i = 0; i < m.villagerCount(Mob.PEASANT) + m.villagerCount(Mob.SOLDIER); i++){
			addBgObj();
		}
		
		bottom.setY(bottom.getHeight() * bg.size());
		
		//listScrollBar.setY(10);
		
		gui.add(mainGameMenuBg);
		gui.add(timeDialHand);
		gui.add(foodCount);
		gui.add(rockCount);
		gui.add(woodCount);
		gui.add(villagerCount);
		gui.add(soldierCount);
		gui.add(pauseGameMenuBg);
		gui.add(gameTime);
		//gui.add(listScrollBar);
		gui.add(bg);
		gui.add(top);
		gui.add(bottom);
	}
	
	private void addBgObj(){
		ImageIcon ico = new ImageIcon(0, 0, model.getMaterial("item_bg").getTexture());
		ico.setPosition(ImageIcon.POSITION_TOP_RIGHT);
		ico.setY(bg.size() * ico.getHeight() + (ico.getHeight() * 1));
		
		bg.add(ico);
	}
	
	public void updatePos(){
		timeDialHand.setX(mainGameMenuBg.getX() + 165);
		foodCount.setX(mainGameMenuBg.getX() + 20);
		rockCount.setX(foodCount.getX());
		woodCount.setX(foodCount.getX());
		villagerCount.setX(mainGameMenuBg.getX() + 203);
		soldierCount.setX(villagerCount.getX());
		gameTime.setX(pauseGameMenuBg.getX() + 14);
	}
	
	public double interpolate(float a, float b, double t){
		return (float) (a * (1 - t) + b * t);
	}
	
	public void update(){
		if(cycle.isMoring()){
			timeDialHand.setRotation((int) (interpolate(0, 90, cycle.getTime()) - 90));
		}else{
			timeDialHand.setRotation((int) -(interpolate(0, 90, cycle.getTime()) - 90));
		}
		
		gameTime.setString(cycle.gameTime());
		
		//update labels
		foodCount.setString("" + world.FOOD);
		rockCount.setString("" + world.ROCK);
		woodCount.setString("" + world.WOOD);
		villagerCount.setString("" + model.villagerCount(Mob.PEASANT));
		soldierCount.setString("" + model.villagerCount(Mob.SOLDIER));
		
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

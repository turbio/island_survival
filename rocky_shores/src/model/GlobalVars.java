package model;

public class GlobalVars {
	public boolean IS_DISPLAY_GUI;
	public float FOV, FOV_ZOOM_VEL;
	public final float FOV_NORM = 32.0f;
	public final int START_WOOD = 15, STAR_ROCK = 15, START_FOOD = 10;
	public int WOOD, ROCK, FOOD;
	
	//building stats
	public final int WORKSHOP_ROCK = 5, WORKSHOP_WOOD = 10, WORKSHOP_FOOD = 0;
	public final int MASONRY_ROCK = 10, MASONRY_WOOD = 5, MASONRY_FOOD = 0;
	public final int FARM_ROCK = 10, FARM_WOOD = 25, FARM_FOOD = 0;
	public final int MILL_ROCK = 15, MILL_WOOD = 20, MILL_FOOD = 0;
	public final int RES_ROCK = 10, RES_WOOD = 20, RES_FOOD  = 5;
	public final int BAR_ROCK = 20, BAR_WOOD = 10, BAR_FOOD = 0;
	public final int TOWNHALL_ROCK = 25, TOWNHALL_WOOD = 45, TOWNHALL_FOOD = 10;
	public final int SHIP_ROCK = 75, SHIP_WOOD = 150, SHIP_FOOD = 45;
	public final int DOCK_ROCK = 1, DOCK_WOOD = 15, DOCK_FOOD = 15;
	
	public final int SOLDIER_ROCK = 5, SOLDIER_WOOD = 0, SOLDIER_FOOD = 5;	//creat soldier
	public final int PEASANT_ROCK = 0, PEASANT_WOOD = 0, PEASANT_FOOD = 2;
	
	//intro vars
	public boolean IS_INTRO;
	public final float START_FOV = 50.0f, INTRO_ZOOM_START_VEL = 0.08f, INTRO_ZOOM_VEL_DEC = 0.0002f, INTRO_ZOOM_MIN_DEC = 0.01f;
}

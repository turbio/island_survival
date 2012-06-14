package gui;

import model.Model;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

@SuppressWarnings("deprecation")
public abstract class GuiElement {
	public static int POSITION_TOP_LEFT = 0, POSITION_TOP = 1, POSITION_TOP_RIGHT = 2, POSITION_RIGHT = 3,
			POSITION_BOTTOM_RIGHT = 4, POSITION_BOTTOM = 5, POSITION_BOTTOM_LEFT = 6, 
			POSITION_LEFT = 7;
	
	private int xPos, yPos, width, height, currentTex, rot;
	private Texture[] texture;
	private String text;
	private Color color;
	private String string;
	private GuiElementType type;
	private int positioning = 0;
	private TrueTypeFont font;
	
	public GuiElement(int x, int y, int w, int h, GuiElementType t){
		xPos = x;
		yPos = y;
		width = w;
		height = h;
		type = t;
	}
	
	public GuiElement(int x, int y, String s,  GuiElementType t){
		xPos = x;
		yPos = y;
		string = s;
		type = t;
	}
	
	public GuiElement(int x, int y, String s, Color c, GuiElementType t){
		xPos = x;
		yPos = y;
		string = s;
		type = t;
		color = c;
	}
	
	public void setPosition(int w, int h, int s){
		switch(positioning){
		case 0:{
			
		}break;
		case 1:{
			xPos = ((w / (2 * s)) - (width / 2));
		}break;
		case 2:{
			System.out.println(w);
			xPos = (w / s) - width;
		}break;
		default:{
			
		}break;
		}
	}
	
	//get methods
	public int getX(){
		return xPos;
	}
	
	public int getY(){
		return yPos;
	}
	
	public int getRotation(){
		return rot;
	}
	
	public void changeRotation(int i){
		rot += i;
	}
	
	public int getWidth(){
		if(font != null){
			width = font.getWidth(string);
		}
		return width;
	}
	
	public int getHeight(){
		if(font != null){
			height = font.getHeight(string);
		}
		return height;
	}
	
	public Texture[] getTextureList(){
		return texture;
	}
	
	public Texture getTexture(){
		return texture[currentTex];
	}
	
	public Texture getTexture(int index){
		return texture[index];
	}
	
	public String getText(){
		return text;
	}
	
	public Color getColor(){
		return color;
	}
	
	public String getString(){
		return string;
	}
	
	public GuiElementType getType(){
		return type;
	}
	
	public TrueTypeFont getFont(){
		return font;
	}
	
	//set methods
	public void setX(int x){
		xPos = x;
	}
	
	public void setY(int y){
		yPos = y;
	}
	
	public void setWidth(int w){
		width = w;
	}
	
	public void setHeight(int h){
		height = h;
	}
	
	public void setTex(Texture[] t){
		texture = t;
	}
	
	public void setTex(int index){
		currentTex = index;
	}
	
	public void setColor(Color c){
		color = c;
	}
	
	public void setString(String s){
		string = s;
	}
	
	public void setFont(TrueTypeFont f){
		font = f;
		if(type == GuiElementType.LABEL && text != null){
			width = font.getWidth(text);
			height = font.getHeight(text);
		}
	}
	
	public boolean isMouseOver(){
		if(isCollision(xPos, yPos, width, height, Mouse.getX(), Mouse.getY(), 1, 1)){
			return true;
		}
		return false;
	}
	
	public boolean isMouseDown(){
		if(isMouseOver() && Mouse.isButtonDown(0)){
			return true;
		}
		
		return false;
	}
	
	public boolean isCollision(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2){
		   if ( ( x1 > x2 + w2 - 1 ) 
		    	|| (y1 > y2 + h2 - 1) 
		    	|| (x2 > x1 + w1 - 1) 
		    	|| (y2 > y1 + h1 - 1)){
			   return false;
		   }else{
		   return true;
		}
	}
	
	public void setPosition(int i){
		positioning = i;
		setPosition(Model.width, Model.height, 2);
	}
}

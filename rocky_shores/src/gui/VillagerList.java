package gui;

import java.util.ArrayList;

import org.newdawn.slick.Color;

import mob.Mob;
import model.Model;

public class VillagerList {
	Model model;
	Gui gui;
	
	public ArrayList<GuiElement> backgroundList, Labels, icons;
	
	private ImageIcon bottom, top;
	
	public VillagerList(Model m, Gui g){
		model = m;
		gui = g;
		
		top = new ImageIcon(0, 0, m.getMaterial("item_top").getTexture());
		top.setPosition(ImageIcon.POSITION_TOP_RIGHT);
		
		bottom = new ImageIcon(0, 0, m.getTexture("item_bottom"));
		bottom.setPosition(ImageIcon.POSITION_TOP_RIGHT);
		
		backgroundList = new ArrayList<GuiElement>();
		Labels = new ArrayList<GuiElement>();
		icons = new ArrayList<GuiElement>();
		
		for(int i = 0; i < m.villagerCount(Mob.PEASANT) + m.villagerCount(Mob.SOLDIER); i++){
			addBgObj();
		}
		
		bottom.setY(bottom.getHeight() * backgroundList.size());
		
		gui.add(backgroundList);
		gui.add(top);
		gui.add(bottom);
		gui.add(Labels);
	}
	
	private void addBgObj(){
		ImageIcon ico = new ImageIcon(0, 0, model.getMaterial("item_bg").getTexture());
		ico.setPosition(ImageIcon.POSITION_TOP_RIGHT);
		ico.setY(backgroundList.size() * ico.getHeight() + (ico.getHeight() * 1));
		
		Label lable = new Label(10, 10, "name", new Color(255, 255, 255));
		lable.setPosition(Label.POSITION_TOP_RIGHT);
		
		Labels.add(lable);
		backgroundList.add(ico);
	}
}
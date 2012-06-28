package model;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

import resorsers.Rock;
import resorsers.Tree;
import sprite.SpriteTypes;


public class Generate {
	// classes
	private Model controll;

	// tree gen options
	private int growTimeMax = 5000, growTimeMin = 1000, lifeTimeMax = 10000,
		lifeTimeMin = 1000, reprodTimeMax = 3000, reprodTimeMin = 1000,
		maxReprodChance = 20, maxTrees = 1000, maxGenTrees = 300;
		
	
	// rock gen options
	private int maxGroupSize = 200, maxGroups = 5;

	private int treeCount = 0, genTrys = 0;;

	private Random random = new Random();
	private BufferedImage island;

	public Generate(BufferedImage i, Model con) {
		island = i;
		controll = con;
	}

	public void island() {
		treeCount = 0;
		for(int i = 0; i < controll.getSpriteList().size(); i++){
			if(controll.getSpriteList().get(i).getType() == SpriteTypes.TREE || controll.getSpriteList().get(i).getType() == SpriteTypes.ROCK){
				controll.getSpriteList().remove(i);
			}
		}
		flip(); // derp derp derp don't do dis

		// add rocks to island
		addRock();

		// add trees to island
		addTree();

		flip(); // derp derp derp don't do dis again
	}

	public void addRock() {
		// generate trees
		int trys = 0, groups = 0, groupRocks = 0;
		boolean addRock = false;

		while (maxGroups > groups) {
			groupRocks = 0;
			boolean colorDen = true;
			int groupX = 0, groupY = 0;
			while (colorDen) {
				groupX = random.nextInt(island.getWidth());
				groupY = random.nextInt(island.getHeight());
				Color color = new Color(island.getRGB(groupX, groupY));
				if (color.getRed() == 255 && color.getGreen() == 255
						&& color.getBlue() == 255) {
					colorDen = false;
				} else if (color.getRed() == 45 && color.getGreen() == 157
						&& color.getBlue() == 0) {

				} else {
					if(overGen()){
						return;
					}
				}
			}
			while (maxGroupSize > groupRocks) {
				int x = groupX + (random.nextInt(100) + 1), y = groupY
						+ (random.nextInt(100) + 1);

				Color color = Color.BLACK;
				try {
					color = new Color(island.getRGB(x, y));
				} catch (Exception e) {
				}

				if (color.getRed() == 255 && color.getGreen() == 255
						&& color.getBlue() == 255) {
					addRock = true;
				}else{
					if(overGen()){
						return;
					}
				}

				float xpos = ((((float) x * 2f) / (float) island.getWidth()) - 1f) * (float)island.getWidth() * (1.0f / 512.0f),
						zpos = -(((((float) y * 2f) / (float) island.getWidth()) + 1f) * (float)island.getHeight() * (1.0f / 512.0f) - ((((float)island.getHeight()) * (1.0f / 512.0f)) * 2));

				if (addRock) {
					controll.getSpriteList().add(new Rock(controll.getModel("rock"), xpos, 0.0f,zpos, controll));
					addRock = false;
					groupRocks++;
				}else{
					if(overGen()){
						return;
					}
				}
				if (trys > island.getWidth() * island.getHeight()) {
					break;
				}
				groupRocks++;
			}
			groups++;
		}
		int maxr = random.nextInt(10) + 5;
		for (int i = 0; i < maxr; i++) {
			controll.getSpriteList().add(new Rock(controll.getModel("rock"), 
					(controll.shipCrashX + 0.1f) + (random.nextFloat() % 0.2f) - 0.15f, 0.0f, 
					(controll.shipCrashY + (random.nextFloat() % 0.2f)) - 0.1f, controll));
			controll.getSpriteList().get(controll.getSpriteList().size() - 1).setHeight(0.015f);
			controll.getSpriteList().get(controll.getSpriteList().size() - 1).setY(-0.005f);
		}
	}
	
	public boolean overGen(){
		genTrys++;
		if(genTrys > Integer.MAX_VALUE / 1000){
			System.out.println("overgen!!!");
			genTrys = 0;
			return true;
		}
		return false;
	}

	public void addTree() {
		// generate trees
		boolean addTree = false;

		while (treeCount < maxGenTrees) {
			int x = random.nextInt(island.getWidth()), y = random
					.nextInt(island.getHeight());

			Color color = new Color(island.getRGB(x, y));
			float xpos = ((((float) x * 2f) / (float) island.getWidth()) - 1f) * (((float)island.getWidth()) * (1.0f / 512.0f)),
					zpos = -(((((float) y * 2f) / (float) island.getWidth()) + 1f) * (((float)island.getHeight()) * (1.0f / 512.0f)) - ((((float)island.getHeight()) * (1.0f / 512.0f)) * 2) );
			if (color.getRed() == 255 && color.getGreen() == 255
					&& color.getBlue() == 255) {
				addTree = true;
			} else if (color.getRed() == 45 && color.getGreen() == 157
					&& color.getBlue() == 0) {
				// addTree = true;
			} else {
				if(overGen()){
					return;
				}
			}

			boolean collision = false;
			
			for (int i = 0; i < controll.getSpriteList().size(); i++) {
				if (controll.getSpriteList().get(i).isCollision(
						xpos, zpos, 1.02f, 1.02f
						)) {
					collision = true;
				}
			}
			if (collision) {
				addTree = false;
			}

			if (addTree) {
				String[] spr = {"tree1", "tree2", "tree3"};
				controll.getSpriteList().add(
						new Tree(controll, spr, random.nextInt(4), xpos,
								0.0f, zpos, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
								random.nextInt(growTimeMax) + growTimeMin,
								random.nextInt(lifeTimeMax) + lifeTimeMin,
								random.nextInt(reprodTimeMax) + reprodTimeMin,
								maxReprodChance));
				addTree = false;
				treeCount++;

			}else{
				if(overGen()){
					return;
				}
			}
		}
	}
	
	public void addTree(Tree t) {
		update();
		flip();
		// generate trees
		int trys = 0;
		boolean addTree = false, treeAdded = false;

		while (!treeAdded && treeCount < maxTrees) {
			int x = random.nextInt(island.getWidth()), y = random.nextInt(island.getHeight());

			Color color = new Color(island.getRGB(x, y));
			
			float xpos = ((((float) x * 2f) / (float) island.getWidth()) - 1f), zpos = -(((float) y * 2f) / (float) island.getWidth()) + 1f;
			
			if (color.getRed() == 255 && color.getGreen() == 255
					&& color.getBlue() == 255) {
				addTree = true;
			} else if (color.getRed() == 45 && color.getGreen() == 157 && color.getBlue() == 0) {
				addTree = true;
			} else {
				trys++;
			}

			boolean collision = false;

			float Rockhitbox = 1.05f;

			for (int i = 0; i < controll.getSpriteList().size(); i++) {
				if (controll
						.getSpriteList()
						.get(i)
						.isCollision(controll.getSpriteList().get(i).getX(),
								controll.getSpriteList().get(i).getZ(), Rockhitbox,
								Rockhitbox, xpos, zpos, Rockhitbox, Rockhitbox)
						&& controll.getSpriteList().get(i).getType() == SpriteTypes.ROCK) {
					collision = true;
				}
			}

			if (collision) {
				addTree = false;
			}

			if (addTree) {
				String[] spr = {"tree1", "tree2", "tree3"};
				controll.getSpriteList().add(
						new Tree(controll, spr, 0, xpos,
								0.0f, zpos, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
								random.nextInt(growTimeMax) + growTimeMin,
								random.nextInt(lifeTimeMax) + lifeTimeMin,
								random.nextInt(reprodTimeMax) + reprodTimeMin, maxReprodChance));
				addTree = false;
				treeCount++;
				treeAdded = true;
			}
			if (trys > island.getWidth() * island.getHeight()) {
				System.out.println("caution sprites overwriten");
				break;
			}
		}
		flip();
	}
	
	public void removeTree(Tree t){
		update();
		for(int i = 0; i < controll.getSpriteList().size(); i++){
			if(controll.getSpriteList().get(i).equals(t)){
				controll.getSpriteList().remove(i);
				treeCount--;
			}
		}
	}
	
	public void flip(){
		island = new ImageManipulation().flipV(island);
	}
	
	public void update(){
		//System.out.println("treeCount: " + treeCount);
	}
}

package buildings;

import java.util.ArrayList;

import mesh.Material;
import mesh.Mesh;
import sprite.Sprite;
import sprite.SpriteTypes;
import model.Model;

public class Building extends Sprite{
	private boolean isBuilt = false;
	private double buildAmount = 0;	//0 = none, 1 = all
	private double buildSpeed = 0.0005f;
	private int workers = 0, que = 0;
	private Fence fence = null;
	private float padding = 0.02f;
	private BuildingTypes buildingTypes;
	private Model model;
	private ArrayList<Sprite> resource = new ArrayList<Sprite>();
	
	public Building(BuildingTypes t, Material tex[], float x, float z){
		super(tex, x, 0.0f, z);
		super.setType(SpriteTypes.BUILDING);
	}
	
	public Building(BuildingTypes t, Material tex, float x, float z){
		super(tex, x, 0.0f, z);
		super.setType(SpriteTypes.BUILDING);
	}
	
	public Building(BuildingTypes t, Mesh m, float x, float z, Model mod, float scale){
		super(m);
		super.setType(SpriteTypes.BUILDING);
		super.setMesh(m);
		super.setX(x);
		super.setZ(z);
		model = mod;
		
		fence = new Fence(m.getLeft() * scale - padding, m.getBack() * scale - padding,
				m.getRight() * scale + padding, m.getBack() * scale - padding,
				m.getRight() * scale + padding, m.getFront() * scale + padding,
				m.getLeft() * scale - padding, m.getFront() * scale + padding, model.getMaterial("fence"));
		model.getSpriteList().add(fence);
		fence.setX(super.getX());
		fence.setZ(super.getZ());
		
		fence.setY(-0.03f);
		fence.setYVel(0.001f);
		
		buildingTypes = t;
	}
	
	public Building(BuildingTypes t, float x, float z, float scale, Model mod, Mesh... m){
		super(m);
		super.setType(SpriteTypes.BUILDING);
		super.setX(x);
		super.setZ(z);
		model = mod;
		
		fence = new Fence(m[0].getLeft() * scale - padding, m[0].getBack() * scale - padding,
				m[0].getRight() * scale + padding, m[0].getBack() * scale - padding,
				m[0].getRight() * scale + padding, m[0].getFront() * scale + padding,
				m[0].getLeft() * scale - padding, m[0].getFront() * scale + padding, model.getMaterial("fence"));
		model.getSpriteList().add(fence);
		fence.setX(super.getX());
		fence.setZ(super.getZ());
		
		fence.setY(-0.03f);
		fence.setYVel(0.001f);
		
		buildingTypes = t;
	}
	
	public boolean isBuilt(){
		return isBuilt;
	}
	
	public double getBuildAmount(){
		return buildAmount;
	}
	
	public int getWorkers(){
		return workers;
	}
	
	public void addWorker(){
		workers++;
	}
	public void removeWorker(){
		workers--;
	}
	
	public void build(){
		if(buildAmount >= 1){
			buildAmount = 1;
			isBuilt = true;
		}else{
			buildAmount += buildSpeed;
		}
		if(!isBuilt){
			super.setY(interpolate(-(super.getMesh()[0].getTop() * super.getHeight()) - 0.002, 0.0, buildAmount));
		}else{
			super.setY(0.0f);
		}
	}
	
	public void update(){
		super.update();
		
		if(!isBuilt){
			super.setY(interpolate(-(super.getMesh()[0].getTop() * super.getHeight()) - 0.002, 0.0, buildAmount));
			
			if(fence != null){
				if(fence.getY() >= 0){
					fence.setYVel(0.0f);
					fence.setY(0.0f);
				}
			}
		}else{
			super.setY(0.0f);
			
			if(fence != null){
				if(fence.getY() >= 0){
					fence.setYVel(-0.0001f);
				}else if(fence.getY() < -0.05f){
					fence.setAlive(false);
					fence.setVisible(false);
				}
			}
		}
		
	}
	
	public float interpolate(double a, double b, double t){
		return (float)(a * (1 - t) + b * t);
	}
	
	public void setType(BuildingTypes t){
		buildingTypes = t;
	}
	
	public BuildingTypes getBuildingType(){
		return buildingTypes;
	}
	
	public ArrayList<Sprite> getResource(){
		return resource;
	}
	
	public void addResource(Sprite m){
		Sprite res;
		res = new Sprite(m.getMesh()[0]);
		res.setWidth(m.getWidth());
		res.setHeight(m.getHeight());
		res.setDepth(m.getDepth());
		
		res.setX(super.getX());
		res.setZ(super.getZ());
		
		model.getSpriteList().add(res);
		resource.add(res);
		//s.setParent(null);
	}
	
	public void addQue(){
		que++;
	}
	
	public Model getMod(){
		return model;
	}
	
	public int getQue(){
		return que;
	}
	
	public void removeQue(){
		if(que > 0){
			que--;
		}else{
			try {
				throw new Exception("cannot remove que item");
			} catch (Exception e) {}
		}
	}
}

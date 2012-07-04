package buildings;

import java.util.Random;
import mesh.Mesh;
import model.Model;
import sprite.Sprite;

public class Mill extends Building{
	private Sprite prop;
	private final static float scale = 0.024f;
	
	
	public Mill(Mesh m, Mesh p, float x, float z, Model mod){
		super(BuildingTypes.MILL, x, z, scale, mod, m);
		super.setWidth(scale);
		super.setHeight(scale);
		super.setDepth(scale);
		prop = new Sprite(p);
		prop.setX(x);
		prop.setZ(z);
		prop.setY(0.172f);
		prop.setZRotVel((new Random().nextFloat() % 3) + 2);
		prop.setWidth(scale);
		prop.setHeight(scale);
		prop.setDepth(scale);
		prop.cullFace(false);
		mod.getSpriteList().add(prop);
		
		prop.setWidth(0.0f);
		prop.setHeight(0.0f);
	}
	
	public void update(long d){
		super.update(d);
		
		if(super.isBuilt() && prop.getWidth() != scale){
			if(prop.getWidth() < scale){
				prop.setWidth(prop.getWidth() + 0.001f);
				prop.setHeight(prop.getWidth() + 0.001f);
			}else{
				prop.setWidth(scale);
				prop.setHeight(scale);
			}
		}
	}
}

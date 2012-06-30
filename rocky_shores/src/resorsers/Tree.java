package resorsers;


import java.util.Random;
import model.Model;
import particles.Emitter;
import sprite.SpriteTypes;

public class Tree extends Resource{
	private Model controll;
	
	private long growthTime, growth, lifeTime, reprodCool, reprodTime, reprodChance;
	private boolean maxGrow = false, alive = true;
	private Emitter miter;
	private int currentTex, maxTex = 4;
	//private Emitter miter;
	
	public Tree(Model c, String[] t, int startTex, float x, float y, float z, float xVel, float yVel, float zVel,float xr, float yr, float zr, long growTime, long life, long rtime, int rchance){
		//super(SpriteTypes.TREE, t, x, y, z, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.03f, 0.03f, 1.0f);
		super(c.getModel("tree" + (startTex + 1)), 0.01f);
		super.setX(x);
		super.setY(y);
		super.setZ(z);
		super.setBoundWidth(1.02f);
		super.setBoundDepth(1.02f);
		super.setY(0.0f);
		super.setYRot((float)Math.random() * 360);
		super.setType(SpriteTypes.TREE);
		growthTime = growTime;
		growth = growthTime;
		lifeTime = life;
		reprodCool = rtime;
		reprodChance = rchance;
		reprodTime = reprodCool;
		controll = c;
		miter = new Emitter(x, 0.01f, z, 0.0f, 0.005f, 0.0f, 0.003f, 0.003f, 1, 10, controll.getTexture("particles/tree"));
		miter.setActive(true);
		miter.setPhys(true);
		miter.setLife(25);
		miter.setDieOnAnimation(false);
		miter.setBounce(0.7f);
		miter.setGrav(0.0005f);
		miter.setSingelPix(true);
		miter.setRandomXPos(0.01f);
		miter.setRandomYPos(0.01f);
		miter.setRandomZPos(0.01f);
		controll.getMiters().add(miter);
		if(startTex == 0){
			super.setY(-super.getMesh()[0].getTop() * super.getWidth() - 0.01f);
			super.setYVel((new Random().nextFloat() % 0.0008f) + 0.0002f);
		}
		
		
	}
	
	public void update(long d){
		super.update(d);
		if(alive){
			if(super.getY() >= 0){
				super.setYVel(0);
				super.setZRotVel(0);
				super.setZRot(0);
				miter.setActive(false);
			}
			if(Math.abs(super.getYVel()) > 0){
				if(new Random().nextBoolean()){
					super.setZRotVel(2.0f);
				}else{
					super.setZRotVel(-2.0f);
				}
				
				if(Math.abs(super.getZRot()) > 25){
					super.setZRot(0.0f);
				}
			}
			
			
		}
		if(maxGrow){
			if(reprodTime > 0){
				reprodTime--;
			}else{
				reprodTime = reprodCool;
				Random random = new Random();
				if(random.nextInt(99) + reprodChance >= 100){
					controll.addTree(this);
				}
			}
		}
		if(maxGrow){
			lifeTime--;
			if(Math.abs(super.getZRot()) >= 90.0f){
				controll.removeTree(this);
				miter.setAlive(false);
			}
			if(lifeTime <= 0 && alive){
				miter.setRandomXPos(0.05f);
				miter.setActive(true);
				miter.setRate(1);
				miter.setInterval(10);
				super.setYVel(-0.0003f);
				if(new Random().nextBoolean()){
					super.setZRotVel(1.5f);
				}else{
					super.setZRotVel(-1.5f);
				}
				alive = false;
			}
		}else{
			if(growth == 0){
				growth = growthTime;
				currentTex++;
				super.setMesh(controll.getModel("tree" + (currentTex + 1)));
				if(currentTex >= maxTex){
					maxGrow = true;
				}
			}else{
				growth--;
			}
		}
	}
}

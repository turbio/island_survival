package model;


public class Loop implements Runnable{
	private Model controll;
	private boolean running = true;
	private long Delay;
	
	public Loop(Model c, long delay){
		controll = c;
		Delay = delay;
	}
	
	public void stop(){
		running = false;
	}
	
	public boolean isRunning(){
		return running;
	}
	
	public void run(){
		long startTime, endTime, delta, sleep, updateTime, renderTime;
		
		while(running){
			startTime = System.currentTimeMillis();
			
			controll.update();
			updateTime = System.currentTimeMillis() - startTime;
			
			controll.render();
			renderTime = (System.currentTimeMillis() - startTime) - updateTime;
			
			//System.out.println("update: " + updateTime + " render: " + renderTime);
			
			
			endTime = System.currentTimeMillis();
			
			delta = endTime - startTime;
			
			sleep = Delay - delta;
			
			if(sleep > 0){
				try{
					Thread.sleep(sleep);
				}catch(InterruptedException e){}
			}else{
				
			}
		}
		
		controll.loopEnded();
	}
}

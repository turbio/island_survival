package model;


public class Loop implements Runnable{
	private Model controll;
	private boolean running = true;
	
	public Loop(Model c){
		controll = c;
	}
	
	public void stop(){
		running = false;
	}
	
	public boolean isRunning(){
		return running;
	}
	
	public void run(){
		
		long startTime, endTime, delta = 1;
		
		while(running){
			startTime = System.currentTimeMillis();
			
			controll.update(delta);
			controll.render();
			
			endTime = System.currentTimeMillis();
			delta = endTime - startTime;
			
			/*
			sleep = Delay - delta;
			
			if(sleep > 0){
				try{
					Thread.sleep(sleep);
				}catch(InterruptedException e){}
			}else{
				
			}
			*/
		}
		
		controll.loopEnded();
	}
}

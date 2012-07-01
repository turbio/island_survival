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
		int rc = 0;
		
		long startTime, endTime, delta = 0;
		
		while(running){
			startTime = System.currentTimeMillis();
			
			controll.update(delta);
			
			if(rc > 10000){
				rc = 0;
					
				//controll.render();
			}
			
			rc++;
			
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

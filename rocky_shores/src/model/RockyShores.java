package model;

public class RockyShores {
	private boolean timeExcists = true, endOfWorld = false;

	public static void main(String [] args){
		new RockyShores();
	}
	
	@SuppressWarnings("unused")	//Because
	public RockyShores(){
		//run necessary checks
		if(true){
			if(timeExcists){	//make sure time exists, time is need for main loop
				if(2 + 2 == 5){	//make sure math works
					System.out.println("It looks like math isn't working or your version of math is out of date");
					try{
						throw new Exception("math doesn't work");
					}catch(Exception e){}
					System.exit(-1);
				}else{
					if(!endOfWorld){	//also make sure its not the end of the world
						new Model();
					}else{
						System.out.println("There was an error starting the program, it appears to be the end of the world\nIf you are still alive it is likely that you will die very shortly, please wait will you are killed");
						try{
							throw new Exception("math doesn't work");
						}catch(Exception e){}
						System.exit(-1);
					}
				}
			}else{
				System.out.println("unfortunately this program cannot start because time does not exist.\nPlease try again later, if the problem is not resolved please contact a time lord");
				try{
					throw new Exception("time does not exist");
				}catch(Exception e){}
				System.exit(-1);
			}
		}else{
			System.out.println("There appears to be a very serious issue.\nTroubleshooting checklist:\n1. does existence exist\n2. are you sure\n3. goto 1 and check again");
			try{
				throw new Exception("existence does not exist");
			}catch(Exception e){}
			System.exit(-1);
		}
	}
}

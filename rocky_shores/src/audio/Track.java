package audio;

public class Track {
	private String name;
	private boolean loop;
	
	public Track(String name, boolean b){
		this.name = name;
		loop = b;
	}
	
	public void pause(){
		
	}
	
	public void stop(){
		
	}
	
	public String getName(){
		return name;
	}
	
	public boolean loop(){
		return loop;
	}
}

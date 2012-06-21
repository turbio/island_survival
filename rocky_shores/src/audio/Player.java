package audio;

import java.util.ArrayList;

public class Player {
	private ArrayList<Track> nowPlaying;
	
	public Player(){
		
	}
	
	public void play(String s, boolean loop){
		Track track = new Track(s, loop);
		
		nowPlaying.add(track);
	}
	
	public void pause(String name){
		for(int i = 0; i < nowPlaying.size(); i++){
			if(nowPlaying.get(i).getName().equalsIgnoreCase(name)){
				nowPlaying.get(i).pause();
			}
		}
	}
	
	public void stop(String name){
		for(int i = 0; i < nowPlaying.size(); i++){
			if(nowPlaying.get(i).getName().equalsIgnoreCase(name)){
				nowPlaying.get(i).stop();
			}
		}
	}
}

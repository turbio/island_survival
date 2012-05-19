package mesh;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class TexMap {
	private String path;
	private HashMap<String, Integer[]> cords;
	
	public TexMap(String p){
		path = p;
		cords = new HashMap<String, Integer[]>();
		loadMap();
	}
	
	public void loadMap(){
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File(path));
		} catch (FileNotFoundException e) {}
		
		String line = "";
		Integer[] charset = new Integer[4];
		String name = "null";
		
		while(scanner.hasNext()){
			line = scanner.nextLine();
			
			charset = new Integer[4];
			
			name = line.split(" ")[0];
			
			charset[0] = Integer.parseInt(line.split(" ")[2]);
			charset[1] = Integer.parseInt(line.split(" ")[3]);
			charset[2] = Integer.parseInt(line.split(" ")[4]);
			charset[3] = Integer.parseInt(line.split(" ")[5]);
			
			cords.put(name, charset);
		}
	}
	
	public Integer[] getCord(String s){
		return cords.get(s);
	}
}

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Parser {
	public static void main(String [] args){
		new Parser();
	}
	
	public Parser(){
		File f = new File("src/town_hall.obj");
		Scanner s = null;
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException e) {
			System.out.println("unable to read file " + e);
		}
		
		System.out.println("reading: " + f.getAbsolutePath());
		while(s.hasNext()){
			System.out.println(s.nextLine());
		}
		
		//
		
		FileInputStream stream = null;
		try {
			stream = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int  count = 0;
		while(count < 100){
			count++;
			try {
				System.out.println((char)stream.read());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

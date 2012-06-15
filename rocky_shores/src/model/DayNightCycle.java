package model;
import org.lwjgl.opengl.Display;


public class DayNightCycle {
	public String DayMessage = "island survival", NightMessage = "zombie survival";
	//Time 1 = day/full bright || 0 = sundown/half bright || -1 = night/no bright
	//brightness 2 = no bright || 5 = full bright
	//Fog Color 0 = full dark || 1 = full bright
	private float minTime = -1, maxTime = 1;
	private float Time = 0, Brightness = 3, FogColor;
	private float iterator;
	private boolean Brighten =  true, PM = false, Night = false, Day = true;
	private long startTime;
	
	private int dayCount = 1;
	
	private float displayHour = 12, displayMin = 0;	//Display time
	
	public DayNightCycle(float i, float start){
		iterator = i;
		Time = start;
		startTime = System.currentTimeMillis();
	}
	
	public void update(){
		if(Brighten){
			Time += iterator;
		}else{
			Time -= iterator;
		}
		if(Time > maxTime && Brighten){
			Brighten = false;
			
		}
		if(Time < minTime && !Brighten){
			Brighten = true;
		}
		
		if(Time < -0.5f && Day == true){
			Day = false;
			Night = true;
			System.out.println("night");
			toNight();
		}
		if(Time > -0.5f && Day == false){
			Day = true;
			Night = false;
			System.out.println("day");
			toDay();
		}
		
		Brightness = ((Time * 4) + 7)/ 2;
		
		FogColor = (Time + 1) / 2;
	}
	
	private void toNight(){
		Display.setTitle(NightMessage + " day: " + dayCount);
	}
	
	private void toDay(){
		dayCount++;
		Display.setTitle(DayMessage + " day: "+ dayCount);
	}
	
	//setmethods
	public void setTime(float t){
		Time = t;
	}
	
	//get methods
	public float getBrightness(){
		return Brightness;
	}
	
	public float getFogColor(){
		return FogColor;
	}
	
	public float getTime(){
		return Time;
	}
	
	public float getHour(){
		return displayHour;
	}
	
	public float getMin(){
		return displayMin;
	}
	
	public String getDisplayTime(){
		return displayHour + ":" + displayMin + "  " + PM;
	}
	
	public boolean isDay(){
		return Day;
	}
	
	public boolean isNight(){
		return Night;
	}
	
	public int getDayCount(){
		return dayCount;
	}
	
	public boolean isMoring(){
		return Brighten;
	}
	
	public String gameTime(){
		long total = System.currentTimeMillis() - startTime;
		
		long hour, minute, second;
		
		total /= 1000;
		
		second = total % 60;
		
		minute = (total / 60) % 60;
		
		hour = ((total / 3600)) % 12;
		
		String time = "";
		
		if(hour > 0){
			if(hour < 10){
				time += "0" + hour;
			}else{
				time += hour;
			}
			time += ':';
		}
		
		if(minute < 10){
			time += "0" + minute;
		}else{
			time += minute;
		}
		
		time += ':';
		
		if(second < 10){
			time += "0" + second;
		}else{
			time += second;
		}
		
		return time;
	}
}

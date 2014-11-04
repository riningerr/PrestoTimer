/***********************************************************************************
 * 
 * Clock written by Michael Petro and Richard Rininger
 * 
 * (Mostly written by Michael Petro, military and date
 * functions by Richard Rininger)
 * 
 **********************************************************************************/

import java.text.SimpleDateFormat;
import java.util.*;


public class Clock {
	
	public Clock(){}
	
	static Calendar cal = Calendar.getInstance();
	static Date date = new Date();
	SimpleDateFormat mmddyy = new SimpleDateFormat(" MM      dd      yyyy                   E");
	
	private int clkHour;
	private int clkMin;
	private int clkSec;
	private boolean addDay=false;
	private boolean milit = false;
	private static int internal=0;
	
	public void military() {
		milit = !milit;
	}
	
	public void updateClock() {
		//Done by Maged
		//Manually adds a second to cal
		if(internal%10==0){
		cal.add(Calendar.SECOND, 1);
		}
		internal++;
		//Done by Richard R.
		//Switches between military/not military
		if(milit == false) {
			clkHour = cal.get(Calendar.HOUR);
		}
		else {
			clkHour = cal.get(Calendar.HOUR_OF_DAY);
		}
		clkMin = cal.get(Calendar.MINUTE);
		clkSec = cal.get(Calendar.SECOND);
		if(addDay){
			cal.roll(cal.DATE, 1);
			addDay=false;
		}
	}
	public String getTime() {	
		//Done by Richard R.
		String clkHourTxt;
		String clkMinTxt;
		String clkSecTxt;
	
		// If number of hours is 0-9, insert a leading zero
		if(clkHour <= 9) {
			clkHourTxt = "0" + clkHour;
		}
		// If number of hours is 10 or more, no leading zero is needed
		else {
			clkHourTxt = "" + clkHour;
		}
				// If number of minutes is 0-9, insert a leading zero
		if(clkMin <=9) {
			clkMinTxt = "0" + clkMin;
		}
		// If number of hours is 10 or more, no leading zero is needed
		else {
			clkMinTxt = "" + clkMin;
		}
	
		// If number of seconds is 0-9, insert a leading zero
		if(clkSec <= 9) {
			clkSecTxt = "0" + clkSec;
		}
		// If number of seconds is 10 or more, no leading zero is needed
		else {
			clkSecTxt = "" + clkSec;
		}

		String clockText = clkHourTxt + ":" + clkMinTxt + ":" + clkSecTxt;
		return clockText;
	}
	public String getDate() {
		//Done by Maged H.
		String day="SUNDAY";
		switch(cal.get(Calendar.DAY_OF_WEEK)){
		case 1:
			day="SUNDAY";
			break;
		case 2:
			day="MONDAY";
			break;
		case 3:
			day="TUESDAY";
			break;
		case 4:
			day="WEDNESDAY";
			break;
		case 5:
			day="THURSDAY";
			break;
		case 6:
			day="FRIDAY";
			break;
		case 7:
			day="SATURDAY";
			break;
		}
		String dateDay=""+cal.get(Calendar.DATE);
		if(cal.get(Calendar.DATE)<=9){
			dateDay = "0"+dateDay;
		}
		String dateMonth=""+(cal.get(Calendar.MONTH)+1);
		if(cal.get(Calendar.MONTH)<=8){
			dateMonth = "0"+dateMonth;
		}
		return ""+dateDay+"/"+dateMonth+"/"+cal.get(Calendar.YEAR)+"       "+day;
	}
	//Done By Maged H.
	public void addHour(){
		cal.add(Calendar.HOUR_OF_DAY, 1);
	}
	public void addMinute(){
		cal.add(Calendar.MINUTE, 1);
	}
	public void addSecond(){
		cal.add(Calendar.SECOND, 1);
	}
	public void addDay(){
		cal.add(Calendar.DATE,1);
	}
	public void addMonth(){
		cal.add(Calendar.MONTH,1);
	}
	public void addYear(){
		cal.add(Calendar.YEAR,1);
		System.out.println(cal.get(Calendar.YEAR));
	}
	public void removeHour(){
		cal.add(Calendar.HOUR_OF_DAY, -1);
	}
	public void removeMinute(){
		cal.add(Calendar.MINUTE, -1);
	}
	public void removeSecond(){
		cal.add(Calendar.SECOND, -1);
	}
	public void removeDay(){
		cal.roll(cal.DATE,-1);
	}
	public void removeMonth(){
		cal.roll(cal.MONTH,-1);
	}
	public void removeYear(){
		cal.roll(cal.YEAR,-1);
	}
	
}

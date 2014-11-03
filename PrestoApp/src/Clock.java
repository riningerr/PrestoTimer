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
	
	Calendar cal = Calendar.getInstance();
	Date date = new Date();
	SimpleDateFormat mmddyy = new SimpleDateFormat(" MM      dd      yyyy                   E");
	
	private int clkHour;
	private int clkMin;
	private int clkSec;
	private boolean milit = false;
	
	private int hourAdj;
	private int minAdj;
	private int secAdj;
	
	// Adjust Hour Up
	public void hourUp() {
		hourAdj++;
	}
	// Adjust Hour Down
	public void hourDown() {
		hourAdj--;
	}
	// Adjust Minute Up
	public void minUp() {
		minAdj++;
	}
	// Adjust Minute Down
	public void minDown() {
		minAdj--;
	}
	// Adjust Second Up
	public void secUp() {
		secAdj++;
	}
	// Adjust Second Down
	public void secDown() {
		secAdj--;
	}
	// Toggle Military Time
	public void military() {
		milit = !milit;
	}	
	
	public void updateClock() {
		cal = Calendar.getInstance();
		
		if(milit == false) {
			clkHour = cal.get(Calendar.HOUR) + hourAdj;
			//////////////////////////////////////////////////////////
		
			//////////////////////////////////////////////////////////
		}		
		else {
			clkHour = cal.get(Calendar.HOUR_OF_DAY) + hourAdj;
		}
		clkMin = cal.get(Calendar.MINUTE) + minAdj;
		clkSec = cal.get(Calendar.SECOND) + secAdj;
	}
	public String getTime() {	
		
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
		return mmddyy.format(date);
	}
	
}

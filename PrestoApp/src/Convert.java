/***********************************************************************************
 * 
 * This class is designed to convert seconds into hours, minutes, and seconds in the
 * hh:mm:ss format. ONLY USE THIS CLASS FOR hh:mm:ss format!
 * 
 * Each method is STATIC, so there is no need to instantiate an object.
 * 
 * Each method returns its unit of time. For example, getMinutes returns the number
 * of minutes, but Minutes will NEVER be more than 59, because at 60, "Hours" is
 * incremented and "Minutes" is restarted to 0.
 * 
 * EXAMPLE: Print Elapsed Time in hh:mm:ss format
 * 
 * int seconds = 5832;
 * 
 * System.out.println(Convert.getHours(seconds) 
 * 		+ ":" + Convert.getMinutes(seconds) 
 * 		+ ":" + Convert.getSeconds(seconds));
 * 
 * @author riningerr
 * 
 * CHANGELOG
 * ---------
 * 10/15/2014 07:51pm - Changed to return strings and add leading zeros if necessary
 * 10/26/2014 02:31am - Added method to return formatted time (use if individual
 * 						components not needed)
 *
 **********************************************************************************/

public class Convert {

// Return number of hours
	public static String getHours(int timeSec) {
		int hours = (int) (timeSec / 3600);
		String hourText;
		
		// If number of hours is 0-9, insert a leading zero
		if(hours <= 9) {
			hourText = "0" + hours;
			return hourText;
		}
		// If number of hours is 10 or more, no leading zero is needed
		else {
			hourText = "" + hours;
			return hourText;
		}
	}
	
	// Return number of minutes
	public static String getMinutes(int timeSec) {
		int minutes = (int) ((timeSec % 3600) / 60);
		String minuteText;
		
		// If number of minutes is 0-9, insert a leading zero
		if(minutes <=9) {
			minuteText = "0" + minutes;
			return minuteText;
		}
		// If number of hours is 10 or more, no leading zero is needed
		else {
			minuteText = "" + minutes;
			return minuteText;
		}
	}
	
	// Return number of seconds
	public static String getSeconds(int timeSec) {
		int seconds = (int)((timeSec % 3600) % 60);
		String secondText;
		
		// If number of seconds is 0-9, insert a leading zero
		if(seconds <= 9) {
			secondText = "0" + seconds;
			return secondText;
		}
		// If number of seconds is 10 or more, no leading zero is needed
		else {
			secondText = "" + seconds;
			return secondText;
		}
	}
	
	// Return formatted time
	public static String getFormatted(int timeSec) {
		String formattedTime = getHours(timeSec) +":"+ getMinutes(timeSec) +":"+ getSeconds(timeSec);
		return formattedTime;
	}
	
	// Return number of hours INTEGER FORMAT
	public static int getHoursInt(int timeSec) {
		int hours = (int) (timeSec / 3600);
		return hours;
	}
	
	// Return number of minutes INTEGER FORMAT
	public static int getMinutesInt(int timeSec) {
		int minutes = (int) ((timeSec % 3600) / 60);
	
		return minutes;
	}
	
	// Return number of seconds INTEGER FORMAT
	public static int getSecondsInt(int timeSec) {
		int seconds = (int)((timeSec % 3600) % 60);
	
		return seconds;
	}
}
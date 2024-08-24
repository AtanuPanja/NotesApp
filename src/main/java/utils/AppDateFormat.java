package utils;

import java.time.LocalDateTime;

public abstract class AppDateFormat {
	
	public static String convert(LocalDateTime noteDateTime) {
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		
		String output = "";
		
		// if year is same
		if(currentDateTime.getYear() == noteDateTime.getYear()) {
			
			// when day of year is same, it means date, month are same
			if (currentDateTime.getDayOfYear() == noteDateTime.getDayOfYear()) {
				// the output is just the time of the day
				
				int minute = noteDateTime.getMinute();
				String minuteStr = String.valueOf(minute);
				// padding with 0 for one-digit value of minutes
				if (minute < 10) {
					minuteStr = "0" + minuteStr;
				}
				output = noteDateTime.getHour()+":"+minuteStr;
			}
			else if(currentDateTime.getDayOfYear() - 1 == noteDateTime.getDayOfYear()) {
				// when note date is one day before current date
				// the output is 'Yesterday'
				output = "Yesterday";
			}
			else {
				// else when note date has more difference from current date
				int dayOfMonth = noteDateTime.getDayOfMonth();
				
				// Default suffix is `th`
				String suffix = "th";
				
				// 1st, 11th, 21st, 31st
				if (dayOfMonth % 10 == 1 && dayOfMonth != 11) {
						suffix = "st";
				}
				// 2nd, 12th, 22nd
				else if (dayOfMonth % 10 == 2 && dayOfMonth != 12) {
					suffix = "nd";
				}
				// 3rd, 13th, 23rd
				else if (dayOfMonth % 10 == 3 && dayOfMonth != 13) {
					suffix = "rd";
				}
				// 17th Aug
				String month = noteDateTime.getMonth().toString();
				month = month.substring(0, 1) + month.substring(1,3).toLowerCase();
				output = dayOfMonth + suffix + " " + month.substring(0, 3);
			}
		}
		
		return output;
	}
}

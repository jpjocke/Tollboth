package com.tollboth.model;

import java.util.Calendar;

/**
 * Helper class to keep track of costs during a day.
 * 
 * @author Joakim
 *
 */
public class DayCostHelper {

	/**
	 * Gets the cost for the given calendar.
	 * 
	 * @param cal
	 *            Calendar to use.
	 * @return Cost in SEK for the timestamp in the calendar.
	 */
	public static int getCostForTime(Calendar cal) {
		/*
		 * This is the cost I get when reading the old code and removing some faulty calculations.
		 * 0:00 - 6:00 = 0
		 * 6:00 - 6:30 = 8
		 * 6:30 - 7:00 = 13
		 * 7:00 - 8:00 = 18
		 * 8:00 - 8:30 = 13
		 * 8:30 - 15:00 = 8
		 * 15:00 - 15:30 = 13
		 * 15:30 - 17:00 = 18
		 * 17:00 - 18:00 = 13
		 * 18:00 - 18:30 = 8
		 * 18:30 - 24:00 = 0
		 */
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);

		if (hour < 6) {
			return 0;
		}

		if (hour < 7) {
			if (minute < 30) {
				return 8;
			} else {
				return 13;
			}
		}
		if (hour < 8) {
			return 18;
		}

		if (hour < 15) {
			if (hour == 8) {
				if (minute < 30) {
					return 13;
				}
			}
			return 8;
		}

		if (hour < 17) {
			if (hour == 15) {
				if (minute < 30) {
					return 13;
				}
			}
			return 18;
		}
		if (hour < 18) {
			return 13;
		}
		if (hour < 19) {
			if (minute < 30) {
				return 8;
			}
		}
		return 0;

	}
}

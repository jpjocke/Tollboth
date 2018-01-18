package com.tollboth.model;

import java.util.Calendar;

/**
 * Helper class to handle days that are free to pass. Weekends and holidays.
 * 
 * @author Joakim
 *
 *
 *         TODO This class should be loading dates from a file instead of having
 *         hardcoded values.
 */
public class FreedayHelper {

	public static boolean isDayFree(Calendar cal) {
		if (isWeekend(cal)) {
			return true;
		}

		int year = cal.get(Calendar.YEAR);
		if (year == 2013) {
			return is2013Holiday(cal);
		}
		// TODO all the other years must be implemnted.

		return false;
	}

	private static boolean isWeekend(Calendar cal) {
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == Calendar.SATURDAY) {
			return true;
		}
		if (dayOfWeek == Calendar.SUNDAY) {
			return true;
		}
		return false;
	}

	/**
	 * Holidays in sweden during 2013. source:
	 * https://www.kalender.se/helgdagar/2013
	 * 
	 * @param cal
	 *            Calendar to use.
	 * @return true if it is a holiday.
	 */
	private static boolean is2013Holiday(Calendar cal) {

		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);

		// Nyårsdagen, Trettondagen
		if (month == Calendar.JANUARY) {
			if ((day == 1 || day == 6)) {
				return true;
			}
		}
		// Långfredag, Påskdagen
		if (month == Calendar.MARCH) {
			if (day == 29 || day == 31) {
				return true;
			}
		}
		// Annandag påsk
		if (month == Calendar.APRIL) {
			if (day == 1) {
				return true;
			}
		}
		// Första maj, Kristi himmelsfärd, Pingstdagen
		if (month == Calendar.MAY) {
			if (day == 1 || day == 9 || day == 19) {
				return true;
			}
		}
		// Nationaldag, Midsommar
		if (month == Calendar.JUNE) {
			if (day == 6 || day == 22) {
				return true;
			}
		}
		// Alla helgonsdag
		// This is actually a weekend, will be caught earlier.
		if (month == Calendar.NOVEMBER) {
			if (day == 2) {
				return true;
			}
		}
		// Juldagen, Annandag jul
		if (month == Calendar.DECEMBER) {
			if (day == 25 || day == 26) {
				return true;
			}
		}
		return false;
	}
}

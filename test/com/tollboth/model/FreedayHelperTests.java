package com.tollboth.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;


public class FreedayHelperTests {
	

	@Test
	public void whenGettingFreedayGivenSaturdayThenReturnIsTrue(){
		boolean expected = true;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		boolean actual = FreedayHelper.isDayFree(cal);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void whenGettingFreedayGivenSundayThenReturnIsTrue(){
		boolean expected = true;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		boolean actual = FreedayHelper.isDayFree(cal);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void whenGettingFreedayGivenMondayThenReturnIsFalse(){
		boolean expected = false;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		boolean actual = FreedayHelper.isDayFree(cal);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void whenGettingFreedayGiven2013_01_01ThenReturnIsTrue(){
		boolean expected = true;
		int year = 2013;
		int month = Calendar.JANUARY;
		int day = 1;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.YEAR, year);
		boolean actual = FreedayHelper.isDayFree(cal);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void whenGettingFreedayGiven2013_01_06ThenReturnIsTrue(){
		boolean expected = true;
		int year = 2013;
		int month = Calendar.JANUARY;
		int day = 6;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.YEAR, year);
		boolean actual = FreedayHelper.isDayFree(cal);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void whenGettingFreedayGiven2013_03_29ThenReturnIsTrue(){
		boolean expected = true;
		int year = 2013;
		int month = Calendar.MARCH;
		int day = 29;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.YEAR, year);
		boolean actual = FreedayHelper.isDayFree(cal);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void whenGettingFreedayGiven2013_03_31ThenReturnIsTrue(){
		boolean expected = true;
		int year = 2013;
		int month = Calendar.MARCH;
		int day = 31;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.YEAR, year);
		boolean actual = FreedayHelper.isDayFree(cal);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void whenGettingFreedayGiven2013_04_01ThenReturnIsTrue(){
		boolean expected = true;
		int year = 2013;
		int month = Calendar.APRIL;
		int day = 1;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.YEAR, year);
		boolean actual = FreedayHelper.isDayFree(cal);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void whenGettingFreedayGiven2013_05_01ThenReturnIsTrue(){
		boolean expected = true;
		int year = 2013;
		int month = Calendar.MAY;
		int day = 1;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.YEAR, year);
		boolean actual = FreedayHelper.isDayFree(cal);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void whenGettingFreedayGiven2013_05_91ThenReturnIsTrue(){
		boolean expected = true;
		int year = 2013;
		int month = Calendar.MAY;
		int day = 9;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.YEAR, year);
		boolean actual = FreedayHelper.isDayFree(cal);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void whenGettingFreedayGiven2013_05_19ThenReturnIsTrue(){
		boolean expected = true;
		int year = 2013;
		int month = Calendar.MAY;
		int day = 19;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.YEAR, year);
		boolean actual = FreedayHelper.isDayFree(cal);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void whenGettingFreedayGiven2013_06_06ThenReturnIsTrue(){
		boolean expected = true;
		int year = 2013;
		int month = Calendar.JUNE;
		int day = 6;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.YEAR, year);
		boolean actual = FreedayHelper.isDayFree(cal);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void whenGettingFreedayGiven2013_06_22ThenReturnIsTrue(){
		boolean expected = true;
		int year = 2013;
		int month = Calendar.JUNE;
		int day = 22;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.YEAR, year);
		boolean actual = FreedayHelper.isDayFree(cal);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void whenGettingFreedayGiven2013_11_02ThenReturnIsTrue(){
		boolean expected = true;
		int year = 2013;
		int month = Calendar.NOVEMBER;
		int day = 2;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.YEAR, year);
		boolean actual = FreedayHelper.isDayFree(cal);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void whenGettingFreedayGiven2013_12_25ThenReturnIsTrue(){
		boolean expected = true;
		int year = 2013;
		int month = Calendar.DECEMBER;
		int day = 25;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.YEAR, year);
		boolean actual = FreedayHelper.isDayFree(cal);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void whenGettingFreedayGiven2013_12_26ThenReturnIsTrue(){
		boolean expected = true;
		int year = 2013;
		int month = Calendar.DECEMBER;
		int day = 26;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.YEAR, year);
		boolean actual = FreedayHelper.isDayFree(cal);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void whenGettingFreedayGiven2013_12_27ThenReturnIsFalse(){
		boolean expected = false;
		int year = 2013;
		int month = Calendar.DECEMBER;
		int day = 27;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.YEAR, year);
		boolean actual = FreedayHelper.isDayFree(cal);
		Assert.assertEquals(expected, actual);
	}
}

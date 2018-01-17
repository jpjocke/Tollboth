package com.tollboth.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;


public class DayCostHelperTests {
	

	@Test
	public void whenGettingCostGivenClock00_01_ThenReturnIs0(){
		int expectedCost = 0;
		int hour = 0;
		int min = 1;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		int actualCost = DayCostHelper.getCostForTime(cal);
		Assert.assertEquals(expectedCost, actualCost);
	}
	
	@Test
	public void whenGettingCostGivenClock05_59_ThenReturnIs0(){
		int expectedCost = 0;
		int hour = 5;
		int min = 59;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		int actualCost = DayCostHelper.getCostForTime(cal);
		Assert.assertEquals(expectedCost, actualCost);
	}
	
	@Test
	public void whenGettingCostGivenClock06_00_ThenReturnIs8(){
		int expectedCost = 8;
		int hour = 6;
		int min = 0;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		int actualCost = DayCostHelper.getCostForTime(cal);
		Assert.assertEquals(expectedCost, actualCost);
	}
	
	@Test
	public void whenGettingCostGivenClock06_29_ThenReturnIs8(){
		int expectedCost = 8;
		int hour = 6;
		int min = 29;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		int actualCost = DayCostHelper.getCostForTime(cal);
		Assert.assertEquals(expectedCost, actualCost);
	}
	
	@Test
	public void whenGettingCostGivenClock06_30_ThenReturnIs13(){
		int expectedCost = 13;
		int hour = 6;
		int min = 30;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		int actualCost = DayCostHelper.getCostForTime(cal);
		Assert.assertEquals(expectedCost, actualCost);
	}
	
	@Test
	public void whenGettingCostGivenClock06_59_ThenReturnIs13(){
		int expectedCost = 13;
		int hour = 6;
		int min = 59;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		int actualCost = DayCostHelper.getCostForTime(cal);
		Assert.assertEquals(expectedCost, actualCost);
	}
	
	@Test
	public void whenGettingCostGivenClock07_00_ThenReturnIs18(){
		int expectedCost = 18;
		int hour = 7;
		int min = 0;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		int actualCost = DayCostHelper.getCostForTime(cal);
		Assert.assertEquals(expectedCost, actualCost);
	}
	
	@Test
	public void whenGettingCostGivenClock07_59_ThenReturnIs18(){
		int expectedCost = 18;
		int hour = 7;
		int min = 59;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		int actualCost = DayCostHelper.getCostForTime(cal);
		Assert.assertEquals(expectedCost, actualCost);
	}
	
	@Test
	public void whenGettingCostGivenClock08_00_ThenReturnIs13(){
		int expectedCost = 13;
		int hour = 8;
		int min = 0;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		int actualCost = DayCostHelper.getCostForTime(cal);
		Assert.assertEquals(expectedCost, actualCost);
	}
	
	@Test
	public void whenGettingCostGivenClock08_29_ThenReturnIs13(){
		int expectedCost = 13;
		int hour = 8;
		int min = 29;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		int actualCost = DayCostHelper.getCostForTime(cal);
		Assert.assertEquals(expectedCost, actualCost);
	}
	
	@Test
	public void whenGettingCostGivenClock08_30_ThenReturnIs8(){
		int expectedCost = 8;
		int hour = 8;
		int min = 30;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		int actualCost = DayCostHelper.getCostForTime(cal);
		Assert.assertEquals(expectedCost, actualCost);
	}
	
	@Test
	public void whenGettingCostGivenClock14_59_ThenReturnIs8(){
		int expectedCost = 8;
		int hour = 14;
		int min = 59;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		int actualCost = DayCostHelper.getCostForTime(cal);
		Assert.assertEquals(expectedCost, actualCost);
	}
	
	@Test
	public void whenGettingCostGivenClock15_00_ThenReturnIs13(){
		int expectedCost = 13;
		int hour = 15;
		int min = 00;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		int actualCost = DayCostHelper.getCostForTime(cal);
		Assert.assertEquals(expectedCost, actualCost);
	}
	
	@Test
	public void whenGettingCostGivenClock15_29_ThenReturnIs13(){
		int expectedCost = 13;
		int hour = 15;
		int min = 29;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		int actualCost = DayCostHelper.getCostForTime(cal);
		Assert.assertEquals(expectedCost, actualCost);
	}
	
	@Test
	public void whenGettingCostGivenClock15_30_ThenReturnIs18(){
		int expectedCost = 18;
		int hour = 15;
		int min = 30;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		int actualCost = DayCostHelper.getCostForTime(cal);
		Assert.assertEquals(expectedCost, actualCost);
	}
	
	@Test
	public void whenGettingCostGivenClock16_59_ThenReturnIs18(){
		int expectedCost = 18;
		int hour = 16;
		int min = 59;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		int actualCost = DayCostHelper.getCostForTime(cal);
		Assert.assertEquals(expectedCost, actualCost);
	}
	
	@Test
	public void whenGettingCostGivenClock17_00_ThenReturnIs13(){
		int expectedCost = 13;
		int hour = 17;
		int min = 0;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		int actualCost = DayCostHelper.getCostForTime(cal);
		Assert.assertEquals(expectedCost, actualCost);
	}
	
	@Test
	public void whenGettingCostGivenClock17_59_ThenReturnIs13(){
		int expectedCost = 13;
		int hour = 17;
		int min = 59;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		int actualCost = DayCostHelper.getCostForTime(cal);
		Assert.assertEquals(expectedCost, actualCost);
	}
	
	@Test
	public void whenGettingCostGivenClock18_00_ThenReturnIs8(){
		int expectedCost = 8;
		int hour = 18;
		int min = 0;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		int actualCost = DayCostHelper.getCostForTime(cal);
		Assert.assertEquals(expectedCost, actualCost);
	}
	
	@Test
	public void whenGettingCostGivenClock18_29_ThenReturnIs8(){
		int expectedCost = 8;
		int hour = 18;
		int min = 29;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		int actualCost = DayCostHelper.getCostForTime(cal);
		Assert.assertEquals(expectedCost, actualCost);
	}
	
	@Test
	public void whenGettingCostGivenClock18_30_ThenReturnIs0(){
		int expectedCost = 0;
		int hour = 18;
		int min = 30;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		int actualCost = DayCostHelper.getCostForTime(cal);
		Assert.assertEquals(expectedCost, actualCost);
	}
	
	@Test
	public void whenGettingCostGivenClock23_59_ThenReturnIs0(){
		int expectedCost = 0;
		int hour = 23;
		int min = 59;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		int actualCost = DayCostHelper.getCostForTime(cal);
		Assert.assertEquals(expectedCost, actualCost);
	}
}

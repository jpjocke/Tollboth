package com.tollboth.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tollboth.Database;
import com.tollboth.ExternalApiForVehicleInformation;
import com.tollboth.Tollboth;
import com.tollboth.Vehicle;
import com.tollboth.Vehicle.Type;
import com.tollboth.mock.DatabaseImplMock;
import com.tollboth.mock.SystemClockMockImpl;
import com.tollboth.mock.VehicleApiMock;
import com.tollboth.model.Bill;
import com.tollboth.model.DayCostHelper;
import com.tollboth.model.VehicleImpl;

public class TollBothImplTests {
	private Database mDb;
	private ExternalApiForVehicleInformation mVehicleApi;
	private Vehicle mMotorcycle;
	private Vehicle mCar;
	private Tollboth mUnitUnderTests;
	private SystemClockMockImpl mClock;
	
	@Before
	public void setup(){
		mDb = new DatabaseImplMock();
		mVehicleApi = new VehicleApiMock();
		mMotorcycle = new VehicleImpl("QWE876", Type.MOTORBIKE);
		((VehicleApiMock)mVehicleApi).addVehicle(mMotorcycle);
		mCar = new VehicleImpl("ASD456", Type.CAR);
		((VehicleApiMock)mVehicleApi).addVehicle(mCar);
		mClock = new SystemClockMockImpl();
		mUnitUnderTests = new TollBothImpl(mDb, mVehicleApi, mClock);
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenConstructingGivenNullDbThenExceptionIsThrown(){
		new TollBothImpl(null, mVehicleApi, mClock);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenConstructingGivenNullApiThenExceptionIsThrown(){
		new TollBothImpl(mDb, null, mClock);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenConstructingGivenNullClockThenExceptionIsThrown(){
		new TollBothImpl(mDb, mVehicleApi, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenRegisteringGivenNullLicensePlateThenExceptionIsThrown(){
		mUnitUnderTests.registerPassing(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenCalculatingCostsGivenNullLicensePlateThenExceptionIsThrown(){
		mUnitUnderTests.calculateCost(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenCalculatingCostsGivenInvalidLicensePlateThenExceptionIsThrown(){
		mUnitUnderTests.calculateCost("MNBGOJ");
	}
	
	@Test
	public void whenRegisteringMultiplePassingsGivenMotorcycleThenCostIsZero() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.MINUTE, 32);
		cal.set(Calendar.HOUR_OF_DAY, 12);
		mClock.setClock(cal.getTimeInMillis());
		
		mUnitUnderTests.registerPassing(mMotorcycle.getLicensePlate());
		
		cal.set(Calendar.MINUTE, 44);
		mClock.setClock(cal.getTimeInMillis());
		
		mUnitUnderTests.registerPassing(mMotorcycle.getLicensePlate());
		
		cal.set(Calendar.MINUTE, 58);
		mClock.setClock(cal.getTimeInMillis());
		
		mUnitUnderTests.registerPassing(mMotorcycle.getLicensePlate());
		
		Bill b = mUnitUnderTests.calculateCost(mMotorcycle.getLicensePlate());
		
		Assert.assertEquals(0, b.getTotalCost());
		
		System.out.println(b.toString());
	}
	
	@Test
	public void whenRegisteringPassingOnWeekendGivenCarThenCostIsZero() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		cal.set(Calendar.MINUTE, 32);
		cal.set(Calendar.HOUR_OF_DAY, 12);
		mClock.setClock(cal.getTimeInMillis());

		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		Bill b = mUnitUnderTests.calculateCost(mCar.getLicensePlate());
		Assert.assertEquals(0, b.getTotalCost());
		System.out.println(b.toString());
	}
	
	@Test
	public void whenRegisteringPassingOnFreedayGivenCarThenCostIsZero() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, 2013);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MINUTE, 32);
		cal.set(Calendar.HOUR_OF_DAY, 12);
		mClock.setClock(cal.getTimeInMillis());

		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		Bill b = mUnitUnderTests.calculateCost(mCar.getLicensePlate());
		Assert.assertEquals(0, b.getTotalCost());
		System.out.println(b.toString());
	}
	
	@Test
	public void whenRegisteringPassingGivenCarAndPayDayThenCostIsCorrect() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, 2013);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 3);
		cal.set(Calendar.MINUTE, 32);
		cal.set(Calendar.HOUR_OF_DAY, 12);
		mClock.setClock(cal.getTimeInMillis());
		
		int expected = DayCostHelper.getCostForTime(cal);

		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		Bill b = mUnitUnderTests.calculateCost(mCar.getLicensePlate());
		Assert.assertEquals(expected, b.getTotalCost());
		System.out.println(b.toString());
	}
	
	@Test
	public void whenRegisteringMultiplePassingsGivenCarAndPayDayThenCostIsCorrect() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, 2013);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 3);
		cal.set(Calendar.MINUTE, 32);
		cal.set(Calendar.HOUR_OF_DAY, 8);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		
		int expected = DayCostHelper.getCostForTime(cal);

		cal.set(Calendar.MINUTE, 22);
		cal.set(Calendar.HOUR_OF_DAY, 15);
		mClock.setClock(cal.getTimeInMillis());
		
		expected += DayCostHelper.getCostForTime(cal);

		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		Bill b = mUnitUnderTests.calculateCost(mCar.getLicensePlate());
		Assert.assertEquals(expected, b.getTotalCost());
		System.out.println(b.toString());
	}
	
	@Test
	public void whenRegisteringMultiplePassingsGivenCarAndPayDayUnderOneHourThenCostIsCorrect() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, 2013);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 3);
		cal.set(Calendar.MINUTE, 32);
		cal.set(Calendar.HOUR_OF_DAY, 12);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		
		int expected = DayCostHelper.getCostForTime(cal);

		cal.set(Calendar.MINUTE, 22);
		cal.set(Calendar.HOUR_OF_DAY, 12);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		

		cal.set(Calendar.MINUTE, 45);
		cal.set(Calendar.HOUR_OF_DAY, 12);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());

		cal.set(Calendar.MINUTE, 21);
		cal.set(Calendar.HOUR_OF_DAY, 13);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		

		cal.set(Calendar.MINUTE, 23);
		cal.set(Calendar.HOUR_OF_DAY, 13);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		expected += DayCostHelper.getCostForTime(cal);
		

		Bill b = mUnitUnderTests.calculateCost(mCar.getLicensePlate());
		Assert.assertEquals(expected, b.getTotalCost());
		System.out.println(b.toString());
	}
	
	@Test
	public void whenCalculatingCostsTwiceGivenPassingsFirstThenSecondBillIsEmpty(){
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, 2013);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 3);
		cal.set(Calendar.MINUTE, 32);
		cal.set(Calendar.HOUR_OF_DAY, 12);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		
		int expected = DayCostHelper.getCostForTime(cal);

		cal.set(Calendar.MINUTE, 35);
		cal.set(Calendar.HOUR_OF_DAY, 13);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		expected += DayCostHelper.getCostForTime(cal);

		Bill b = mUnitUnderTests.calculateCost(mCar.getLicensePlate());
		Assert.assertEquals(expected, b.getTotalCost());

		b = mUnitUnderTests.calculateCost(mCar.getLicensePlate());
		Assert.assertEquals(0, b.getTotalCost());
	}
	
	@Test
	public void whenRegisteringMultiplePassingsGivenCarAndMultipleDaysThenCostIsCorrect() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, 2013);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		// holiday = free
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MINUTE, 32);
		cal.set(Calendar.HOUR_OF_DAY, 12);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		

		cal.set(Calendar.DAY_OF_MONTH, 3);
		cal.set(Calendar.MINUTE, 14);
		cal.set(Calendar.HOUR_OF_DAY, 8);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		int expected = DayCostHelper.getCostForTime(cal);
		

		// weekend = free
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MINUTE, 10);
		cal.set(Calendar.HOUR_OF_DAY, 17);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		

		cal.set(Calendar.DAY_OF_MONTH, 24);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 15);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());

		expected += DayCostHelper.getCostForTime(cal);

		Bill b = mUnitUnderTests.calculateCost(mCar.getLicensePlate());
		Assert.assertEquals(expected, b.getTotalCost());
		System.out.println(b.toString());
	}
	
	@Test
	public void whenRegisteringMultiplePassingsGivenSameDayAndTollIsOver60ThenCostIsMax() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, 2013);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 3);
		cal.set(Calendar.MINUTE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 6);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		
		cal.set(Calendar.MINUTE, 2);
		cal.set(Calendar.HOUR_OF_DAY, 7);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());

		cal.set(Calendar.MINUTE, 3);
		cal.set(Calendar.HOUR_OF_DAY, 8);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());

		cal.set(Calendar.MINUTE, 4);
		cal.set(Calendar.HOUR_OF_DAY, 9);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		

		cal.set(Calendar.MINUTE, 5);
		cal.set(Calendar.HOUR_OF_DAY, 10);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		

		cal.set(Calendar.MINUTE, 6);
		cal.set(Calendar.HOUR_OF_DAY, 11);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		

		cal.set(Calendar.MINUTE, 7);
		cal.set(Calendar.HOUR_OF_DAY, 12);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		

		cal.set(Calendar.MINUTE, 8);
		cal.set(Calendar.HOUR_OF_DAY, 13);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		

		cal.set(Calendar.MINUTE, 9);
		cal.set(Calendar.HOUR_OF_DAY, 14);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		

		cal.set(Calendar.MINUTE, 10);
		cal.set(Calendar.HOUR_OF_DAY, 15);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		

		cal.set(Calendar.MINUTE, 11);
		cal.set(Calendar.HOUR_OF_DAY, 16);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		Bill b = mUnitUnderTests.calculateCost(mCar.getLicensePlate());
		
		int expected = 60;
		Assert.assertEquals(expected, b.getTotalCost());
		System.out.println(b.toString());
	}
	
	@Test
	public void whenRegisteringMultiplePassingsGivenMultipleDaysAndTollIsOver60ThenCostIsMax() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(Calendar.YEAR, 2013);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 3);
		cal.set(Calendar.MINUTE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 6);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		
		cal.set(Calendar.MINUTE, 2);
		cal.set(Calendar.HOUR_OF_DAY, 7);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());

		cal.set(Calendar.MINUTE, 3);
		cal.set(Calendar.HOUR_OF_DAY, 8);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());

		cal.set(Calendar.MINUTE, 4);
		cal.set(Calendar.HOUR_OF_DAY, 9);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		

		cal.set(Calendar.MINUTE, 5);
		cal.set(Calendar.HOUR_OF_DAY, 10);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		

		cal.set(Calendar.MINUTE, 6);
		cal.set(Calendar.HOUR_OF_DAY, 11);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		

		cal.set(Calendar.MINUTE, 7);
		cal.set(Calendar.HOUR_OF_DAY, 12);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		

		cal.set(Calendar.DAY_OF_MONTH, 4);
		cal.set(Calendar.MINUTE, 7);
		cal.set(Calendar.HOUR_OF_DAY, 12);
		mClock.setClock(cal.getTimeInMillis());
		mUnitUnderTests.registerPassing(mCar.getLicensePlate());
		
		int expected = 60;
		expected += DayCostHelper.getCostForTime(cal);

		Bill b = mUnitUnderTests.calculateCost(mCar.getLicensePlate());
		Assert.assertEquals(expected, b.getTotalCost());
		System.out.println(b.toString());
	}
}

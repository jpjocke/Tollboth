package com.tollboth.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tollboth.Vehicle;
import com.tollboth.Vehicle.Type;


public class BillTests {
	private Vehicle mVehicle;
	
	@Before
	public void setup(){
		mVehicle = new VehicleImpl("ABC123", Type.MOTORBIKE);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenConstructingGivenNullVehicleThenExceptionIsThrown(){
		new Bill(null);
	}

	@Test
	public void whenAddingMultiplePassingsThenTotalIsCorrect(){
		int sum1 = 40;
		int sum2 = 56;
		int expected = sum1 + sum2;
		Bill b = new Bill(mVehicle);
		b.registerPassing(System.currentTimeMillis(), sum1);
		b.registerPassing(System.currentTimeMillis(), sum2);
		Assert.assertEquals(expected, b.getTotalCost());
	}
	
	@Test
	public void whenAddingMultiplePassingsThenToStringIsPrintedAsExpected(){
		Bill b = new Bill(mVehicle);
		b.registerPassing(System.currentTimeMillis(), 60);
		b.registerPassing(System.currentTimeMillis(), 12);
		// TODO, this is just a visual representation of how a bill can look.
		System.out.println(b.toString());
	}
}

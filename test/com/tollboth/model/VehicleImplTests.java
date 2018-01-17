package com.tollboth.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tollboth.Vehicle;
import com.tollboth.Vehicle.Type;


public class VehicleImplTests {
	private static final String LP = "QWE098";
	private static final Type TYPE = Type.EMERGENCY;
	

	@Test(expected = IllegalArgumentException.class)
	public void whenConstructingGivenNullLicensPlateThenExceptionIsThrown(){
		new VehicleImpl(null, TYPE);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenConstructingGivenNullTypeThenExceptionIsThrown(){
		new VehicleImpl(LP, null);
	}
	
	@Test
	public void whenConstructingGivenValidValuesThenNothingHappens(){
		new VehicleImpl(LP, TYPE);
	}
	
	@Test
	public void whenConstructingGivenLicensePlateThenReturnIsSameLicensePlate(){
		Vehicle v = new VehicleImpl(LP, TYPE);
		Assert.assertEquals(LP, v.getLicensePlate());
	}
	
	@Test
	public void whenConstructingGivenTypeThenReturnIsSameType(){
		Vehicle v = new VehicleImpl(LP, TYPE);
		Assert.assertEquals(TYPE, v.getType());
	}
}

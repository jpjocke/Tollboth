package com.tollboth;

import org.junit.Assert;
import org.junit.Test;

import com.tollboth.Vehicle.Type;


public class VehicleTests {
	@Test
	public void whenCheckingIfFreeGivenTypeCarThenReturnIsFalse(){
		Assert.assertEquals(false, Type.CAR.isFree());
	}
	
	@Test
	public void whenCheckingIfFreeGivenTypeMotorcycleThenReturnIsTrue(){
		Assert.assertEquals(true, Type.MOTORBIKE.isFree());
	}
	
	@Test
	public void whenCheckingIfFreeGivenTypeTractorThenReturnIsTrue(){
		Assert.assertEquals(true, Type.TRACTOR.isFree());
	}
	
	@Test
	public void whenCheckingIfFreeGivenTypeEmergencyThenReturnIsTrue(){
		Assert.assertEquals(true, Type.EMERGENCY.isFree());
	}
	
	@Test
	public void whenCheckingIfFreeGivenTypeDiplomatThenReturnIsTrue(){
		Assert.assertEquals(true, Type.DIPLOMAT.isFree());
	}
	
	@Test
	public void whenCheckingIfFreeGivenTypeForeignThenReturnIsTrue(){
		Assert.assertEquals(true, Type.FOREIGN.isFree());
	}
	
	@Test
	public void whenCheckingIfFreeGivenTypeMilitaryThenReturnIsTrue(){
		Assert.assertEquals(true, Type.MILITARY.isFree());
	}
}

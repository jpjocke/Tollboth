package com.tollboth.model;

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

	@Test
	public void test(){
		//TODO
		Bill b = new Bill(mVehicle);
		b.registerPassing(System.currentTimeMillis(), 60);
		b.registerPassing(System.currentTimeMillis(), 12);
		System.out.println(b.toString());
	}
}

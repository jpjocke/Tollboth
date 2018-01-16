package com.tollboth.impl;

import org.junit.Before;
import org.junit.Test;

import com.tollboth.Database;
import com.tollboth.ExternalApiForVehicleInformation;
import com.tollboth.mock.DatabaseImplMock;
import com.tollboth.mock.VehicleApiMock;

public class TollBothImplTests {
	private Database mDb;
	private ExternalApiForVehicleInformation mVehicleApi;
	
	@Before
	public void setup(){
		mDb = new DatabaseImplMock();
		mVehicleApi = new VehicleApiMock();
	}

	@Test(expected = IllegalArgumentException.class)
	public void whenConstructingGivenNullDbThenExceptionIsThrown(){
		new TollBothImpl(null, mVehicleApi);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void whenConstructingGivenNullApiThenExceptionIsThrown(){
		new TollBothImpl(mDb, null);
	}
}

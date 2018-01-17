package com.tollboth.mock;

import java.util.HashMap;
import java.util.Map;

import com.tollboth.ExternalApiForVehicleInformation;
import com.tollboth.Vehicle;
import com.tollboth.Vehicle.Type;
import com.tollboth.model.VehicleImpl;

/**
 * Mock API for vehicles.
 * 
 * @author Joakim
 *
 */
public class VehicleApiMock implements ExternalApiForVehicleInformation {
	public static String CAR_LP_1 = "ABC123";
	public static String MC_LP_1 = "DER987";
	private Map<String, Vehicle> mMockedVehicles;

	public VehicleApiMock() {
		mMockedVehicles = new HashMap<>();
		mMockedVehicles.put(CAR_LP_1, new VehicleImpl(CAR_LP_1, Type.CAR));
		mMockedVehicles.put(CAR_LP_1, new VehicleImpl(MC_LP_1, Type.MOTORBIKE));
	}

	@Override
	public Vehicle getVehicleForLicensePlate(String licensePlate) {
		Vehicle v = mMockedVehicles.get(licensePlate);
		return v;
	}

	/**
	 * Adds a vehicle to the mock.
	 * 
	 * @param v
	 *            Vehicle to mock.
	 */
	public void addVehicle(Vehicle v) {
		mMockedVehicles.put(v.getLicensePlate(), v);
	}
}

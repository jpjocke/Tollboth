package com.tollboth.impl;

import com.tollboth.Database;
import com.tollboth.ExternalApiForVehicleInformation;
import com.tollboth.Tollboth;
import com.tollboth.model.Bill;

public class TollBothImpl implements Tollboth{
	private final Database mDb;
	private final ExternalApiForVehicleInformation mVehicleApi;

	public TollBothImpl(Database db, ExternalApiForVehicleInformation vehicleApi) {
		if (db == null) {
			throw new IllegalArgumentException("db may not be null");
		}
		
		if (vehicleApi == null) {
			throw new IllegalArgumentException("vehicleApi may not be null");
		}
		
		mDb = db;
		mVehicleApi = vehicleApi;
	}

	@Override
	public boolean register(String licensePlate) {
		long timestamp = System.currentTimeMillis();
		
		// we will register all passings for statistics.
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Bill calculateCost(String licensePLate) {
		// TODO Auto-generated method stub
		// get vehicle
		// is vehicle free?
		// for each timestamp
		// is day free?
		// keep last billable passing
		// is this time 1h after last?
		// calculate cost for time of day
		return null;
	}

}

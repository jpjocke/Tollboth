package com.tollboth.impl;

import java.util.List;

import com.tollboth.Database;
import com.tollboth.ExternalApiForVehicleInformation;
import com.tollboth.Tollboth;
import com.tollboth.Vehicle;
import com.tollboth.model.Bill;

public class TollBothImpl implements Tollboth {
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
	public boolean registerPassing(String licensePlate) throws IllegalArgumentException {
		if (licensePlate == null) {
			throw new IllegalArgumentException("licensePlate may not be null");
		}

		// we will register all passings for statistics.
		long timestamp = System.currentTimeMillis();
		return mDb.registerVehicle(licensePlate, timestamp);
	}

	@Override
	public Bill calculateCost(String licensePlate) throws IllegalArgumentException {
		if (licensePlate == null) {
			throw new IllegalArgumentException("licensePlate may not be null");
		}

		Vehicle v = mVehicleApi.getVehicleForLicensePlate(licensePlate);
		List<Long> passings = mDb.getPassingsForVehicle(licensePlate);
		Bill bill = new Bill(v);
		if (v.getType().isFree()) {
			// Add all passings with a cost of zero since this is a free vehicle.
			for (long timestamp : passings) {
				bill.registerPassing(timestamp, 0);
			}
		} else {
			for (long timestamp : passings) {
				bill.registerPassing(timestamp, 0);
			}
		}
		// for each timestamp
		// is day free?
		// keep last billable passing
		// is this time 1h after last?
		// calculate cost for time of day

		// clear the passings in the db
		return null;
	}

	private boolean isVehicleFree(Vehicle v) {
		/*
		 * Business requirements taken from original code.
		 */
		switch (v.getType()) {
		case MOTORBIKE:
			return true;

		case TRACTOR:
			return true;

		case EMERGENCY:
			return true;

		case DIPLOMAT:
			return true;

		case FOREIGN:
			return true;

		case MILITARY:
			return true;

		default:
			return false;
		}
	}
}

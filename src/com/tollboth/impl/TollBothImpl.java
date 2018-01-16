package com.tollboth.impl;

import java.util.Calendar;
import java.util.List;

import com.tollboth.Database;
import com.tollboth.ExternalApiForVehicleInformation;
import com.tollboth.Tollboth;
import com.tollboth.Vehicle;
import com.tollboth.model.Bill;
import com.tollboth.model.FreedayHelper;

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
		if (v == null) {
			throw new IllegalArgumentException("licensePlate is not valid");
		}
		
		List<Long> passings = mDb.getPassingsForVehicle(licensePlate);
		Bill bill = new Bill(v);
		
		if (v.getType().isFree()) {
			// Add all passings with a cost of zero since this is a free
			// vehicle.
			for (long timestamp : passings) {
				bill.registerPassing(timestamp, 0);
			}
		} else {
			long lastPayedTimestamp = -1;
			for (long timestamp : passings) {
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(timestamp);
				if (FreedayHelper.isDayFree(cal)) {
					bill.registerPassing(timestamp, 0);
				} else {

				}
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

}

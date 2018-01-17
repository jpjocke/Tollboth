package com.tollboth.impl;

import java.util.Calendar;
import java.util.List;

import com.tollboth.Database;
import com.tollboth.ExternalApiForVehicleInformation;
import com.tollboth.SystemClock;
import com.tollboth.Tollboth;
import com.tollboth.Vehicle;
import com.tollboth.model.Bill;
import com.tollboth.model.DayCostHelper;
import com.tollboth.model.FreedayHelper;

/**
 * Implementation for the Tollboth.
 * 
 * @author Joakim
 *
 */
public class TollBothImpl implements Tollboth {
	private static final long FREE_HOUR = 1000l * 60 * 60;
	private final Database mDb;
	private final ExternalApiForVehicleInformation mVehicleApi;
	private final SystemClock mClock;

	/**
	 * Constructor.
	 * 
	 * @param db
	 *            Database to use.
	 * @param vehicleApi
	 *            Vehicle API to use.
	 * @param clock
	 *            Clock to use
	 * @throws IllegalArgumentException
	 *             If the arguments are invalid.
	 */
	public TollBothImpl(Database db, ExternalApiForVehicleInformation vehicleApi, SystemClock clock)
			throws IllegalArgumentException {
		if (db == null) {
			throw new IllegalArgumentException("db may not be null");
		}

		if (vehicleApi == null) {
			throw new IllegalArgumentException("vehicleApi may not be null");
		}

		if (clock == null) {
			throw new IllegalArgumentException("clock may not be null");
		}

		mDb = db;
		mVehicleApi = vehicleApi;
		mClock = clock;
	}

	@Override
	public boolean registerPassing(String licensePlate) throws IllegalArgumentException {
		if (licensePlate == null) {
			throw new IllegalArgumentException("licensePlate may not be null");
		}

		// we will register all passings for statistics.
		long timestamp = mClock.currentTimeMillis();
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
			return bill;
		}

		calculatePassingsForPayingVehicles(passings, bill);

		// clear the passings in the db
		mDb.clearPassings(licensePlate);
		return bill;
	}

	private void calculatePassingsForPayingVehicles(List<Long> passings, Bill bill) {
		// keep track of the last payed passing since we can only debit once a
		// hour.
		long lastPayedTimestamp = -1;
		for (long timestamp : passings) {
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(timestamp);
			if (FreedayHelper.isDayFree(cal)) {
				// Free days costs zero.
				bill.registerPassing(timestamp, 0);
			} else {
				boolean isSameHour = false;
				if (lastPayedTimestamp == -1) {
					lastPayedTimestamp = timestamp;
				} else {
					if (lastPayedTimestamp + FREE_HOUR > timestamp) {
						isSameHour = true;
					} else {
						lastPayedTimestamp = timestamp;
					}
				}
				if (!isSameHour) {
					bill.registerPassing(timestamp, DayCostHelper.getCostForTime(cal));
				} else {
					// If the vehicle has passed within one hour the cost is
					// zero.
					bill.registerPassing(timestamp, 0);
				}
			}
		}
	}

}

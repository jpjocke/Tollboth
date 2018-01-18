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
	private static final int MAX_COST_PER_DAY = 60;
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
		Calendar cal = Calendar.getInstance();
		MaxDayCalculator mdc = null;
		for (long timestamp : passings) {

			cal.setTimeInMillis(timestamp);
			if (mdc == null || !mdc.isSameDay(cal)) {
				mdc = new MaxDayCalculator(cal);
			}

			if (FreedayHelper.isDayFree(cal)) {
				// Free days costs zero.
				bill.registerPassing(timestamp, 0);
				continue;
			}

			if (lastPayedTimestamp == -1) {
				lastPayedTimestamp = timestamp;
				int cost = DayCostHelper.getCostForTime(cal);
				bill.registerPassing(timestamp, mdc.registerCost(cost));
				continue;
			}

			if (isPassingWithinFreeHour(lastPayedTimestamp, timestamp)) {
				// If the vehicle has passed within one hour the cost is
				// zero.
				bill.registerPassing(timestamp, 0);
			} else {
				lastPayedTimestamp = timestamp;
				int cost = DayCostHelper.getCostForTime(cal);
				bill.registerPassing(timestamp, mdc.registerCost(cost));
			}

		}
	}

	private boolean isPassingWithinFreeHour(long lastPayedTimestamp, long timestamp) {
		return lastPayedTimestamp + FREE_HOUR > timestamp;
	}

	/**
	 * Helper class to keep track of maximum cost per day.
	 * 
	 * @author Joakim
	 *
	 */
	private class MaxDayCalculator {
		private final Calendar mDay;
		private int mCost;

		/**
		 * Constructor.
		 * 
		 * @param cal
		 *            Calender with the day to use.
		 */
		public MaxDayCalculator(Calendar cal) {
			mDay = (Calendar) cal.clone();
			mCost = 0;
		}

		/**
		 * Checks if this is the same day as the given calendar.
		 * 
		 * @param cal
		 *            Calendar to use.
		 * @return true if it is the same day.
		 */
		public boolean isSameDay(Calendar cal) {
			if (cal.get(Calendar.YEAR) == mDay.get(Calendar.YEAR)) {
				if (cal.get(Calendar.DAY_OF_YEAR) == mDay.get(Calendar.DAY_OF_YEAR)) {
					return true;
				}
			}
			return false;
		}

		/**
		 * Registers the cost and returns the actual cost based on if todays
		 * cost has reached the max cost.
		 * 
		 * @param cost
		 *            cost for passing.
		 * @return actual cost of the passing.
		 */
		public int registerCost(int cost) {
			mCost += cost;
			if (mCost > MAX_COST_PER_DAY) {
				// Calculate the rest product by reversing the cost
				int rest = MAX_COST_PER_DAY - mCost + cost;
				return Math.max(0, rest);
			}
			return cost;
		}
	}
}

package com.tollboth.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tollboth.Vehicle;

/**
 * Object that can be passed to other systems for Billing. Keeps track of a
 * vehicles passings and each cost for them.
 * 
 * @author Joakim
 *
 */
public class Bill {
	private static final String LINE_SEPARATOR = "line.separator";
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final Vehicle mVehicle;
	private final List<Passing> mPassings;
	private int mTotalCost;

	/**
	 * Constructor
	 * 
	 * @param vehicle
	 *            Vehicle that the bill is for.
	 * @throws IllegalArgumentException
	 *             if Vehicle is null.
	 */
	public Bill(Vehicle vehicle) throws IllegalArgumentException {
		if (vehicle == null) {
			throw new IllegalArgumentException("vehicle must be set");
		}
		mVehicle = vehicle;
		mPassings = new ArrayList<>();
	}

	/**
	 * Registers a passing with time and cost.
	 * 
	 * @param timestamp
	 *            Time of passing.
	 * @param cost
	 *            Cost for the passing.
	 */
	public void registerPassing(long timestamp, int cost) {
		mPassings.add(new Passing(timestamp, cost));
		mTotalCost += cost;
	}

	/**
	 * Gets the total cost for all passings.
	 * 
	 * @return Total cost of passings.
	 */
	public int getTotalCost() {
		return mTotalCost;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("--------");
		sb.append(System.getProperty(LINE_SEPARATOR));
		sb.append(mVehicle.toString());
		sb.append(System.getProperty(LINE_SEPARATOR));
		sb.append(System.getProperty(LINE_SEPARATOR));
		for (Passing p : mPassings) {
			sb.append(sdf.format(new Date(p.mTimestamp)) + " " + p.mCost + " kr");
			sb.append(System.getProperty(LINE_SEPARATOR));
		}
		sb.append(System.getProperty(LINE_SEPARATOR));
		sb.append("Total kostnad: " + mTotalCost + " kr");
		sb.append(System.getProperty(LINE_SEPARATOR));
		sb.append("--------");
		sb.append(System.getProperty(LINE_SEPARATOR));
		return sb.toString();

	}

	private class Passing {
		final long mTimestamp;
		final int mCost;

		public Passing(long timestamp, int cost) {
			mTimestamp = timestamp;
			mCost = cost;
		}
	}
}

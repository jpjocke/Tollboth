package com.tollboth.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tollboth.Vehicle;

public class Bill {
	private static final String LINE_SEPARATOR = "line.separator";
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final Vehicle mVehicle;
	private final List<Passing> mPassings;
	private int mTotalCost;

	public Bill(Vehicle vehicle) {
		if (vehicle == null) {
			throw new IllegalArgumentException("vehicle must be set");
		}
		mVehicle = vehicle;
		mPassings = new ArrayList<>();
	}

	public void registerPassing(long timestamp, int cost) {
		mPassings.add(new Passing(timestamp, cost));
		mTotalCost += cost;
	}

	public int getTotalCost() {
		return mTotalCost;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(mVehicle.toString());
		sb.append(System.getProperty(LINE_SEPARATOR));
		sb.append(System.getProperty(LINE_SEPARATOR));
		for (Passing p : mPassings) {
			sb.append(sdf.format(new Date(p.mTimestamp)) + " " + p.mCost + " kr");
			sb.append(System.getProperty(LINE_SEPARATOR));
		}
		sb.append(System.getProperty(LINE_SEPARATOR));
		sb.append("Total kostnad: " + mTotalCost + " kr");
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

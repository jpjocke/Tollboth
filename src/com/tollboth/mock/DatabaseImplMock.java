package com.tollboth.mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tollboth.Database;

/**
 * Mock implementation of the database layer
 * 
 * @author Joakim
 *
 */
public class DatabaseImplMock implements Database {
	private final Map<String, List<Long>> mDatastructure;

	public DatabaseImplMock() {
		mDatastructure = new HashMap<String, List<Long>>();
	}

	@Override
	public boolean registerVehicle(String licensePlate, long timestamp) {
		List<Long> data = mDatastructure.get(licensePlate);
		if (data == null) {
			data = new ArrayList<>();
			mDatastructure.put(licensePlate, data);
		}
		data.add(timestamp);
		return true;
	}

	@Override
	public List<Long> getPassingsForVehicle(String licensePlate) {
		List<Long> data = mDatastructure.get(licensePlate);
		if (data == null) {
			data = new ArrayList<>();
		}
		
		Collections.sort(data);

		return data;
	}

	@Override
	public void clearPassings(String licensePlate) {
		List<Long> data = mDatastructure.get(licensePlate);
		if (data == null) {
			return;
		}
		data.clear();
	}

}

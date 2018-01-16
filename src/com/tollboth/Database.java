package com.tollboth;

import java.util.List;

public interface Database {
	/**
	 * Register a passing vehicle with the exact timestamp.
	 * 
	 * @param licensePlate
	 *            ID of vehicle.
	 * @param timestamp
	 *            Timestamp of pass.
	 * @return true if registration went ok, otherwise false.
	 */
	public boolean registerVehicle(String licensePlate, long timestamp);

	/**
	 * Gets a collection of timestamps when the vehicle have passed the
	 * tollboth.
	 * 
	 * @param licensePlate
	 *            ID of vehicle.
	 * @return Sorted list of passings that the vehicle has done since last
	 *         clearPassing().
	 */
	public List<Long> getPassingsForVehicle(String licensePlate);

	/**
	 * Clears the data of passings for the vehicle.
	 * 
	 * @param licensePlate
	 *            ID of vehicle.
	 */
	public void clearPassings(String licensePlate);
}

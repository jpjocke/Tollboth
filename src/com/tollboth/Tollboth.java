package com.tollboth;

import com.tollboth.model.Bill;

public interface Tollboth {
	/**
	 * Register a passing.
	 * 
	 * @param licensePlate
	 *            ID of the vehicle.
	 * @return true if the registration went successful, otherwise false.
	 * @throws IllegalArgumentException
	 *             If the ID is null.
	 */
	public boolean registerPassing(String licensePlate) throws IllegalArgumentException;

	/**
	 * Calculates the cost of all passings for the given Vehicle. Will clear the
	 * old passings.
	 * 
	 * @param licensePlate
	 *            ID of the vehicle.
	 * @return A bill with the cost specification for the given Vehicle.
	 * @throws IllegalArgumentException
	 *             if the ID is null.
	 */
	public Bill calculateCost(String licensePlate) throws IllegalArgumentException;
}

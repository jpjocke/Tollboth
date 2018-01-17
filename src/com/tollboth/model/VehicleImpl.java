package com.tollboth.model;

import com.tollboth.Vehicle;

/**
 * Implementation for a vehicle.
 * 
 * @author Joakim
 *
 */
public class VehicleImpl implements Vehicle {
	private final String mLicensePlate;
	private final Type mType;

	/**
	 * Constructor.
	 * 
	 * @param licensePlate
	 *            License plate for the vehicle.
	 * @param vehicleType
	 *            Type for the vehicle.
	 * @throws IllegalArgumentException
	 *             If arguments are invalid.
	 */
	public VehicleImpl(String licensePlate, Type vehicleType) throws IllegalArgumentException {
		if (licensePlate == null) {
			throw new IllegalArgumentException("licensePlate must be set");
		}
		if (vehicleType == null) {
			throw new IllegalArgumentException("vehicleType must be set");
		}
		this.mLicensePlate = licensePlate;
		this.mType = vehicleType;
	}

	@Override
	public Type getType() {
		return mType;
	}

	@Override
	public String getLicensePlate() {
		return mLicensePlate;
	}

	@Override
	public String toString() {
		return mLicensePlate + " of type: " + mType;
	}
}

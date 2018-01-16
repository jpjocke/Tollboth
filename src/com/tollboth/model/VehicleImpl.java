package com.tollboth.model;

import com.tollboth.Vehicle;

public class VehicleImpl implements Vehicle {
	private final String mLicensePlate;
	private final Type mType;

	public VehicleImpl(String licensePlate, Type vehicleType) {
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
	public String toString(){
		return mLicensePlate + " of type: " + mType;
	}
}

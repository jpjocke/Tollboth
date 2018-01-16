package com.tollboth.model;

import com.tollboth.Vehicle;

public class VehicleImpl implements Vehicle {
	private final String mLicensePlate;
	private final Type mType;

	public VehicleImpl(String licensePlate, Type vehicleType) {
		this.mLicensePlate = licensePlate;
		this.mType = vehicleType;
	}

	@Override
	public Type getType() {
		return mType;
	}

}

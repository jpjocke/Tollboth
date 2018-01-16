package com.tollboth.model;

public class Bill {
	private final String mLicensePlate;

	public Bill(String licensePlate) {
		if (licensePlate == null) {
			throw new IllegalArgumentException("licensePlate must be set");
		}
		mLicensePlate = licensePlate;
	}
}

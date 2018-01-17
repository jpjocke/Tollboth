package com.tollboth;

/**
 * Interface for all vehicles.
 * 
 * @author Joakim
 *
 */
public interface Vehicle {
	/**
	 * Gets the license plate for the vehicle.
	 * 
	 * @return ID of the vehicle.
	 */
	public String getLicensePlate();

	/**
	 * Gets the type of the vehicle.
	 * 
	 * @return Type of vehicle.
	 */
	public Type getType();

	/**
	 * All possible types a vehicle can have.
	 * 
	 * @author Joakim
	 *
	 */
	public enum Type {
		CAR, MOTORBIKE, TRACTOR, EMERGENCY, DIPLOMAT, FOREIGN, MILITARY;

		public boolean isFree() {
			/*
			 * Business requirements taken from original code.
			 */
			switch (this) {
			case MOTORBIKE:
				return true;

			case TRACTOR:
				return true;

			case EMERGENCY:
				return true;

			case DIPLOMAT:
				return true;

			case FOREIGN:
				return true;

			case MILITARY:
				return true;

			default:
				return false;
			}
		}
	}
}

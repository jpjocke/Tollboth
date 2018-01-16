package com.tollboth;

public interface Vehicle {
	public String getLicensePlate();

	public Type getType();

	public enum Type {
		CAR,
		MOTORBIKE,
	    TRACTOR,
	    EMERGENCY,
	    DIPLOMAT,
	    FOREIGN,
	    MILITARY;
		
	    public boolean isFree(){
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

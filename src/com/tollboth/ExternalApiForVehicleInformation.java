package com.tollboth;

/**
 * API for getting Vehicle information from a license plate. This API is though
 * to be external for now.
 * 
 * @author Joakim
 *
 */
public interface ExternalApiForVehicleInformation {
	/**
	 * Gets a Vehicle from the given license plate.
	 * 
	 * @param licensePlate
	 *            ID of vehicle.
	 * @return Vehicle with the gicen license plate. Null if no Vehicle has this
	 *         license plate.
	 */
	public Vehicle getVehicleForLicensePlate(String licensePlate);
}

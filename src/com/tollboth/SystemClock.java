package com.tollboth;

/**
 * Representing time on the system. Mockable.
 * 
 * @author Joakim
 *
 */
public interface SystemClock {
	/**
	 * Gets the current time in milliseconds from the system.
	 * 
	 * @return Current milliseconds.
	 */
	public long currentTimeMillis();
}

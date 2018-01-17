package com.tollboth.mock;

import com.tollboth.SystemClock;

/**
 * Mock implementation for the systemclock.
 * 
 * @author Joakim
 *
 */
public class SystemClockMockImpl implements SystemClock {
	private long mClock;

	@Override
	public long currentTimeMillis() {
		return mClock;
	}

	/**
	 * Sets the clock.
	 * 
	 * @param time
	 *            Time to set.
	 */
	public void setClock(long time) {
		mClock = time;
	}
}

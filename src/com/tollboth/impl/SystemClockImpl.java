package com.tollboth.impl;

import com.tollboth.SystemClock;

/*
 * Default clock implementation.
 * Will return the systems current time.
 */
public class SystemClockImpl implements SystemClock{

	@Override
	public long currentTimeMillis() {
		return System.currentTimeMillis();
	}

}

package com.tollboth;

import com.tollboth.model.Bill;

public interface Tollboth {
	public boolean register(String licensePlate);
	public Bill calculateCost(String licensePLate);
}

package com.tollboth;

public interface Vehicle {
	public Type getType();
	
	public enum Type {
		CAR,
		MOTORBIKE,
	    TRACTOR,
	    EMERGENCY,
	    DIPLOMAT,
	    FOREIGN,
	    MILITARY
		/*
		private enum TollFreeVehicles {
		    MOTORBIKE("Motorbike"),
		    TRACTOR("Tractor"),
		    EMERGENCY("Emergency"),
		    DIPLOMAT("Diplomat"),
		    FOREIGN("Foreign"),
		    MILITARY("Military");
		    private final String type;

		    TollFreeVehicles(String type) {
		      this.type = type;
		    }

		    public String getType() {
		      return type;
		    }
		  }
		  */
	}
}

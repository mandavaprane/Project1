package com.hotel.roomdesign;

import java.math.BigDecimal;

public class SimpleRoom implements Room {

	@Override
	public BigDecimal getBookingCost() {
		// TODO Auto-generated method stub
		
		
		return RATE_FACTORY.getSimpleRoomRate();
	}

	
	
}

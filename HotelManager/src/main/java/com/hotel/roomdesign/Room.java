package com.hotel.roomdesign;

import java.math.BigDecimal;

import com.hotel.managedbean.RoomRatesManagerBean;
import com.hotel.util.SessionFactoryImpl;

public interface Room {

	RoomRatesManagerBean RATE_FACTORY=SessionFactoryImpl.getApplicationManagerBean();
	public BigDecimal getBookingCost();
	
}

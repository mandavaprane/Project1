package com.hotel.roomdesign;

import java.math.BigDecimal;

public class ACDecorator extends RoomDecorator{

	public ACDecorator(Room specialRoom){
		super(specialRoom);
	}

	@Override
	public BigDecimal getBookingCost() {
		// TODO Auto-generated method stub
		return specialRoom.getBookingCost().add(RATE_FACTORY.getAcDecorationRate());
	}
}

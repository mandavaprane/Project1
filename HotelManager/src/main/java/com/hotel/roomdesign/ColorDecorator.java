package com.hotel.roomdesign;

import java.math.BigDecimal;

public class ColorDecorator extends RoomDecorator{

	
	public ColorDecorator(Room specialRoom){
		super(specialRoom);
	}

	@Override
	public BigDecimal getBookingCost() {
		// TODO Auto-generated method stub
		return specialRoom.getBookingCost().add(RATE_FACTORY.getColorDecorationRate());
	}
}

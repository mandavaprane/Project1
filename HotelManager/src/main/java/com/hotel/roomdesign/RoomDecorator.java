package com.hotel.roomdesign;

abstract class RoomDecorator implements Room {
	
	protected Room specialRoom;
	
	public RoomDecorator(Room specialRoom){
		this.specialRoom=specialRoom;
	}

}

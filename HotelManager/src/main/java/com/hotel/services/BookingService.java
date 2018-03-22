package com.hotel.services;

import java.util.List;

import com.hotel.entity.BookingOption;
import com.hotel.entity.HotelRequest;
import com.hotel.entity.RoomPool;
import com.hotel.roomdesign.Room;

public interface BookingService {

	
	
	public HotelRequest bookRoom(HotelRequest hotelRequest, RoomPool roomPool);
	
		
	public HotelRequest getLastBooking(); 
	
	public boolean updateBookingRoom(HotelRequest hotelRequest);
	
	public boolean addBookingOption(BookingOption bookOption,HotelRequest hotelRequest);
	
	
	public boolean cancelBooking(HotelRequest hotelRequest);
	
	
	public List<HotelRequest> getCurrentBookings();
	
	
	public Room createSpecialRoom( int bedSize, String[] speciality);
	
	
	public BookingOption getOptions( int bedSize, String[] speciality, BookingOption bookOption);
	
	public boolean updateRoomPool(RoomPool roomPool, int noOfRooms);
	
	
	
	public RoomPool checkRoomPoolExists(String roomType, int roomQuantity, char flowerDecoration, char curtain, char sqlWallColor, char acFacility );
	
	
}

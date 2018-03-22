package com.hotel.roomdesign;

public class TestRoomDesign {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Simple Room Rate is:"+new SimpleRoom().getBookingCost());
		
		
		
		System.out.println("FlowerDecorated Room Rate is:"+new FlowerDecorator(new SimpleRoom()).getBookingCost());
		
		
		System.out.println("Curtain Decorated Room Rate is:"+new CurtainDecorator(new SimpleRoom()).getBookingCost());
		
		
		System.out.println("Color Decorated Room Rate is:"+new ColorDecorator(new SimpleRoom()).getBookingCost());
		
		
		System.out.println("All Decorated Room Rate is:"+new CurtainDecorator(new FlowerDecorator(new ColorDecorator(new SimpleRoom()))).getBookingCost());
	}

}

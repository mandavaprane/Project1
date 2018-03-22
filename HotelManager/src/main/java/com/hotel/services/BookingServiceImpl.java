package com.hotel.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hotel.entity.BookingOption;
import com.hotel.entity.HotelRequest;
import com.hotel.entity.RoomPool;
import com.hotel.managedbean.RoomRatesManagerBean;
import com.hotel.roomdesign.ACDecorator;
import com.hotel.roomdesign.ColorDecorator;
import com.hotel.roomdesign.CurtainDecorator;
import com.hotel.roomdesign.DoubleBedRoom;
import com.hotel.roomdesign.FlowerDecorator;
import com.hotel.roomdesign.Room;
import com.hotel.roomdesign.SimpleRoom;
import com.hotel.util.SessionFactoryImpl;

public class BookingServiceImpl implements BookingService {


	@Override
	public HotelRequest bookRoom(HotelRequest hotelRequest,RoomPool roomPool) {
		// TODO Auto-generated method stub
		
		if(hotelRequest!=null){
			try{
				Session session = SessionFactoryImpl.returnService()
			
					.getCurrentSession();
			Transaction tx = session.beginTransaction();
			hotelRequest.setRoomPool(roomPool);
			session.save(hotelRequest);
			
			tx.commit();

			
			
			}catch(Exception e){
				e.printStackTrace();
				return null;
				}
			
			
			
			return getLastBooking();
			}
			else
				return null;
	}

	
	
	
	
	
	@Override
	public boolean updateBookingRoom(HotelRequest hotelRequest) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cancelBooking(HotelRequest hotelRequest) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<HotelRequest> getCurrentBookings() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Session session= SessionFactoryImpl.returnService().getCurrentSession();
		Transaction tx= session.beginTransaction();
		List<HotelRequest> list=session.createQuery("from HotelRequest").list();
		tx.commit();		
		return 	list;
		
	}
	
	
	

	@Override
	public HotelRequest getLastBooking() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Session session= SessionFactoryImpl.returnService().getCurrentSession();
		Transaction tx= session.beginTransaction();
		List<HotelRequest> list=session.createQuery("from HotelRequest where request_id = (select max(request_id) from HotelRequest) ").list();
		tx.commit();		
		return 	list.get(0);
		
	}
	
	

	@Override
	public Room createSpecialRoom( int bedSize, String[] speciality) {
		// TODO Auto-generated method stub
		
		Room specialRoom;
		
		
		
		
		if(bedSize==2) {
			specialRoom= new DoubleBedRoom();
			
		}
		else {
			specialRoom= new SimpleRoom();
			
		}
		
		if(speciality!=null && speciality.length>0){
			System.out.println("speciality:"+ speciality[0]);
			
			
			
		Set<String> qualitySet= new HashSet(Arrays.asList(speciality));
		
		
		
		System.out.println("Quality Set is:"+qualitySet);
		
		
		if(qualitySet.contains("1")) 
			specialRoom= new FlowerDecorator(specialRoom);
			
		
		if(qualitySet.contains("2")) 
			specialRoom= new CurtainDecorator(specialRoom);
			
		
		if(qualitySet.contains("3"))
			specialRoom= new ColorDecorator(specialRoom);
			
		
		if(qualitySet.contains("4")) 
			specialRoom= new ACDecorator(specialRoom);
			
		
		}
		System.out.println("Booking Cost:"+specialRoom.getBookingCost());
		return specialRoom;
	}
	
	
	
	
	@Override
	public BookingOption getOptions( int bedSize, String[] speciality, BookingOption opt) {
		// TODO Auto-generated method stub
	RoomRatesManagerBean appBean = SessionFactoryImpl.getApplicationManagerBean();
		if(bedSize==2) {
			
			opt.setRoomType("doubleroom");
			opt.setRoomTypeCost(appBean.getDoubleBedRoomRate().intValue());
		}
		else {
			
			opt.setRoomType("singleroom");
			opt.setRoomTypeCost(appBean.getSimpleRoomRate().intValue());
		}
		
		if(speciality!=null && speciality.length>0){
			System.out.println("speciality:"+ speciality[0]);
			
			
		Set<String> qualitySet= new HashSet(Arrays.asList(speciality));
				
		System.out.println("Quality Set is:"+qualitySet);
		
		
		if(qualitySet.contains("1")) { 
			
			opt.setFlowerDecoration('Y');
			opt.setFlowerCost(appBean.getFlowerDecorationRate().intValue());
		}
		else {
			opt.setFlowerDecoration('N');
			opt.setFlowerCost(0);
		}
		if(qualitySet.contains("2")) { 
			
			opt.setNeedCurtains('Y');
			opt.setCurtainCost(appBean.getCurtainDecorationRate().intValue());
		}
		else {
			opt.setNeedCurtains('N');
			opt.setCurtainCost(0);
		}
		
		if(qualitySet.contains("3")) {
			
			opt.setNeedToChangeWallColor('Y');
			opt.setWallColorCost(appBean.getColorDecorationRate().intValue());
		}
		else {
			opt.setNeedToChangeWallColor('N');
			opt.setWallColorCost(0);
		}
		if(qualitySet.contains("4")) {
			
			opt.setNeedAcFacility('Y');
			opt.setAcFacilityCost(appBean.getAcDecorationRate().intValue());
		}
		else {
			opt.setNeedAcFacility('N');
			opt.setAcFacilityCost(0);
		}
		}
		
		return opt;
	}

	
	public RoomPool checkRoomPoolExists(String roomType, int roomQuantity, char flowerDecoration, char curtain, char sqlWallColor, char acFacility ) {
		System.out.println(roomType+" "+roomQuantity+" "+flowerDecoration+" "+curtain+" "+sqlWallColor+" "+acFacility );
		Session session= SessionFactoryImpl.returnService().getCurrentSession();
		Transaction tx= session.beginTransaction();
		String query= "from RoomPool  where roomType= :roomtype and "
				+ "roomQuantity>= :roomqty and flowerDecorationReq = :flowerReq and "
				+ "curtainAvailable = :curtainNeeded and splWallColor= :wallColor and acFacility= :needAC";
		Query q= session.createQuery(query);
		
		q.setParameter("roomtype", roomType);
		q.setParameter("roomqty", roomQuantity);
		q.setParameter("flowerReq", flowerDecoration );
		q.setParameter("curtainNeeded", curtain );
		q.setParameter("wallColor", sqlWallColor );
		q.setParameter("needAC", acFacility );
		
		
		List<RoomPool> list=q.list();
		tx.commit();
		
		if(list.size()==0) {
			return null;
		}
		else if(list.get(0).getRoomVacant()<5) {
			return null;
		}
		
				
		return 	list.get(0);
	}
	
	
	
	
	public boolean updateRoomPool(RoomPool roomPool, int noOfRooms) {
		
		
		if(roomPool!=null){
			try{
				Session session = SessionFactoryImpl.returnService()
			
					.getCurrentSession();
			Transaction tx = session.beginTransaction();
			int vacantRoom=roomPool.getRoomVacant();
			int bookedRoom= roomPool.getRoomBooked();
			vacantRoom= vacantRoom-noOfRooms;
			bookedRoom=bookedRoom+noOfRooms;
			roomPool.setRoomVacant(vacantRoom);
			roomPool.setRoomBooked(bookedRoom);
			
			
			session.update(roomPool);
			
			tx.commit();

			return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;
				}
			}
			else
				return false;
		
		
	}
	
	
	
	
	
	@Override
	public boolean addBookingOption(BookingOption bookOption,HotelRequest hotelRequest) {
		// TODO Auto-generated method stub
		
		if(bookOption!=null){
			try{
				Session session = SessionFactoryImpl.returnService()
			
					.getCurrentSession();
			Transaction tx = session.beginTransaction();
			bookOption.setHotelRequest(hotelRequest);
			session.save(bookOption);
			
			tx.commit();

			
			return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;
				}
			
			
			
			
			}
			else
				return false;
	}

	
	
}

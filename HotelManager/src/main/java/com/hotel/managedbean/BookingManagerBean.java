package com.hotel.managedbean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.hibernate.Session;

import com.hotel.entity.BookingOption;
import com.hotel.entity.HotelRequest;
import com.hotel.entity.RoomPool;
import com.hotel.roomdesign.ACDecorator;
import com.hotel.roomdesign.ColorDecorator;
import com.hotel.roomdesign.CurtainDecorator;
import com.hotel.roomdesign.DoubleBedRoom;
import com.hotel.roomdesign.FlowerDecorator;
import com.hotel.roomdesign.Room;
import com.hotel.roomdesign.SimpleRoom;
import com.hotel.services.BookingService;
import com.hotel.services.BookingServiceImpl;
import com.hotel.util.SessionFactoryImpl;

@ManagedBean(name="bookingBean")
@ViewScoped
public class BookingManagerBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HotelRequest bookingRequest= new HotelRequest();
	
	Room specialRoom;
	
	private String[] speciality; 
	
	private int roomType=1;
	
	private BookingOption bookOption;
	
	private transient UIComponent successMessage;
	
	@PostConstruct
	public void init(){
		bookingRequest.setPostingDate(new Date());
		bookOption= new BookingOption();
		bookingRequest.setNoOfRoom("1");
		bookingRequest.setBookingRate(""+SessionFactoryImpl.getApplicationManagerBean().getSimpleRoomRate());
		updateBookingCost();
	}
	
	public void bookRoom(){
		/*if(roomType==1)
		bookingRequest.setRoomType("Single Bed Room");
		else
			bookingRequest.setRoomType("Double Bed Room");*/
		
		
		
		BookingService bookingService= new BookingServiceImpl();
		
		RoomPool roomPool= bookingService.checkRoomPoolExists(bookOption.getRoomType(), Integer.parseInt(bookingRequest.getNoOfRoom()),
				bookOption.getFlowerDecoration(), bookOption.getNeedCurtains(), bookOption.getNeedToChangeWallColor(),
				bookOption.getNeedAcFacility());
		
		if(roomPool == null) {
			FacesContext.getCurrentInstance().addMessage(
					successMessage.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "",
							"Requested booking is not available right now."));
			return;
		}
		boolean done=false;
		bookingRequest=bookingService.bookRoom(bookingRequest,roomPool);
		if(bookingRequest != null) {
			done= bookingService.addBookingOption(bookOption, bookingRequest);
			if(done)
				done= bookingService.updateRoomPool(roomPool, Integer.parseInt(bookingRequest.getNoOfRoom()));
		}
		if(done){
			System.out.println("Booking Confirmed!");
			
			bookingRequest= new HotelRequest();
			bookOption= new BookingOption();
			bookingRequest.setPostingDate(new Date());
			bookingRequest.setNoOfRoom("1");
			bookingRequest.setBookingRate(""+SessionFactoryImpl.getApplicationManagerBean().getSimpleRoomRate());
			speciality= new String[10];
			roomType=1;
			SessionManagerBean sessionBean= SessionFactoryImpl.getSessionManagerBean();
			if(!sessionBean.isIsuserloggedin()){
			FacesContext.getCurrentInstance().addMessage(
					successMessage.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_INFO, "",
							"Your booking has been confirmed. For more details please connect with our Front Desk cleark."));
		}
			else{
				FacesContext.getCurrentInstance().addMessage(
						successMessage.getClientId(),
						new FacesMessage(FacesMessage.SEVERITY_INFO, "",
								"Booking has Been confirmed."));
			}
		}
		else{
			System.out.println("Could not complete Booking.");
			FacesContext.getCurrentInstance().addMessage(
					successMessage.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "",
							"Could Not Complete Booking."));
		}
		
		
	}

	public HotelRequest getBookingRequest() {
		return bookingRequest;
	}

	public void setBookingRequest(HotelRequest bookingRequest) {
		this.bookingRequest = bookingRequest;
	}

	public String[] getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String[] speciality) {
		this.speciality = speciality;
	}
	
	
	public int getRoomType() {
		return roomType;
	}

	public void setRoomType(int roomType){
		this.roomType = roomType;
	}

	
	
	public UIComponent getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(UIComponent successMessage) {
		this.successMessage = successMessage;
	}

	public void updateBookingCost(){
		BookingService bookingService=new BookingServiceImpl();
		Room specialRoom= bookingService.createSpecialRoom(roomType, speciality);
		 bookOption=bookingService.getOptions(roomType, speciality, bookOption);
		
		bookingRequest.setBookingRate(""+specialRoom.getBookingCost().multiply(new BigDecimal(bookingRequest.getNoOfRoom())) ); 
	}
	

}

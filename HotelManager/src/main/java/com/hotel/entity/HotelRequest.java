package com.hotel.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table
public class HotelRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long request_id;
	
	
	
	@Column
	private String foodType;
	
	@Column
	private String requesterName;
	
	@Column
	private String requesterEmail;
	
	@Column
	private String bookingRate;
	
	@Column
	private Date bookingDate;
	
	@Column
	private String custumerAddress;
	
	@Column
	private String NoOfRoom;
	
	
	@Column
	private Date postingDate;
	
	@Column
	private String requestingTaxiService;

	@Column
	private String mobileNo;
	
	
	@OneToMany(mappedBy="hotelRequest", cascade = CascadeType.ALL)
	private Set<BookingOption> bookingOptionSet;
	
	
	@ManyToOne
	@JoinColumn(name="roomPool_id", nullable=false)
	private RoomPool roomPool;
	
	public Long getRequest_id() {
		return request_id;
	}

	public void setRequest_id(Long request_id) {
		this.request_id = request_id;
	}

	
	

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public String getRequesterName() {
		return requesterName;
	}

	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}

	public String getRequesterEmail() {
		return requesterEmail;
	}

	public void setRequesterEmail(String requesterEmail) {
		this.requesterEmail = requesterEmail;
	}

	public String getBookingRate() {
		return bookingRate;
	}

	public void setBookingRate(String bookingRate) {
		this.bookingRate = bookingRate;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getCustumerAddress() {
		return custumerAddress;
	}

	public void setCustumerAddress(String custumerAddress) {
		this.custumerAddress = custumerAddress;
	}

	public String getNoOfRoom() {
		return NoOfRoom;
	}

	public void setNoOfRoom(String noOfRoom) {
		NoOfRoom = noOfRoom;
	}

	public Date getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}

	public String getRequestingTaxiService() {
		return requestingTaxiService;
	}

	public void setRequestingTaxiService(String requestingTaxiService) {
		this.requestingTaxiService = requestingTaxiService;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Set<BookingOption> getBookingOptionSet() {
		return bookingOptionSet;
	}

	public void setBookingOptionSet(Set<BookingOption> bookingOptionSet) {
		this.bookingOptionSet = bookingOptionSet;
	}

	public RoomPool getRoomPool() {
		return roomPool;
	}

	public void setRoomPool(RoomPool roomPool) {
		this.roomPool = roomPool;
	}

	
	
	
	
	
	
}

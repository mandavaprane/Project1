package com.hotel.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class RoomPool {

	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long roomPool_id;
	
	@Column
	private String roomType;
	
	
	@Column
	private int roomQuantity;
	
	
	@Column
	private char flowerDecorationReq;
	
	
	@Column
	private char curtainAvailable;
	
	
	@Column
	private char splWallColor;
	
	@Column
	private char acFacility;
	
	@Column
	private int roomBooked;
	
	
	@Column
	private int roomVacant;

	
	@OneToMany(mappedBy="roomPool", cascade = CascadeType.ALL)
	private Set<HotelRequest> bookingDetailsSet;

	public Long getRoomPool_id() {
		return roomPool_id;
	}


	public void setRoomPool_id(Long roomPool_id) {
		this.roomPool_id = roomPool_id;
	}


	public String getRoomType() {
		return roomType;
	}


	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}


	public int getRoomQuantity() {
		return roomQuantity;
	}


	public void setRoomQuantity(int roomQuantity) {
		this.roomQuantity = roomQuantity;
	}


	public char getFlowerDecorationReq() {
		return flowerDecorationReq;
	}


	public void setFlowerDecorationReq(char flowerDecorationReq) {
		this.flowerDecorationReq = flowerDecorationReq;
	}


	public char getCurtainAvailable() {
		return curtainAvailable;
	}


	public void setCurtainAvailable(char curtainAvailable) {
		this.curtainAvailable = curtainAvailable;
	}


	public char getSplWallColor() {
		return splWallColor;
	}


	public void setSplWallColor(char splWallColor) {
		this.splWallColor = splWallColor;
	}


	public char getAcFacility() {
		return acFacility;
	}


	public void setAcFacility(char acFacility) {
		this.acFacility = acFacility;
	}


	public int getRoomBooked() {
		return roomBooked;
	}


	public void setRoomBooked(int roomBooked) {
		this.roomBooked = roomBooked;
	}


	public int getRoomVacant() {
		return roomVacant;
	}


	public void setRoomVacant(int roomVacant) {
		this.roomVacant = roomVacant;
	}
	
	
}

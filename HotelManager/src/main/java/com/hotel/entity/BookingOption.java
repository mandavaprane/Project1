package com.hotel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class BookingOption {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long optionRequestId;
		
	
	
	@ManyToOne
	@JoinColumn(name="request_id", nullable=false)
	private HotelRequest hotelRequest;
	
	
	@Column
	private String roomType;
	
	
	@Column
	private int roomTypeCost;
	
	@Column
	private char flowerDecoration;
	
	
	@Column
	private int flowerCost;
	
	
	@Column
	private char needCurtains;
	
	@Column
	private int curtainCost;
	
	
	@Column
	private char needToChangeWallColor;
	
	
	@Column
	private int wallColorCost;
	
	
	@Column
	private char needAcFacility;
	
	
	@Column
	private int acFacilityCost;


	public Long getOptionRequestId() {
		return optionRequestId;
	}


	public void setOptionRequestId(Long optionRequestId) {
		this.optionRequestId = optionRequestId;
	}


	public String getRoomType() {
		return roomType;
	}


	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}


	public char getFlowerDecoration() {
		return flowerDecoration;
	}


	public void setFlowerDecoration(char flowerDecoration) {
		this.flowerDecoration = flowerDecoration;
	}


	public int getFlowerCost() {
		return flowerCost;
	}


	public void setFlowerCost(int flowerCost) {
		this.flowerCost = flowerCost;
	}


	public char getNeedCurtains() {
		return needCurtains;
	}


	public void setNeedCurtains(char needCurtains) {
		this.needCurtains = needCurtains;
	}


	public int getCurtainCost() {
		return curtainCost;
	}


	public void setCurtainCost(int curtainCost) {
		this.curtainCost = curtainCost;
	}


	public char getNeedToChangeWallColor() {
		return needToChangeWallColor;
	}


	public void setNeedToChangeWallColor(char needToChangeWallColor) {
		this.needToChangeWallColor = needToChangeWallColor;
	}


	public int getWallColorCost() {
		return wallColorCost;
	}


	public void setWallColorCost(int wallColorCost) {
		this.wallColorCost = wallColorCost;
	}


	public char getNeedAcFacility() {
		return needAcFacility;
	}


	public void setNeedAcFacility(char needAcFacility) {
		this.needAcFacility = needAcFacility;
	}


	public int getAcFacilityCost() {
		return acFacilityCost;
	}


	public void setAcFacilityCost(int acFacilityCost) {
		this.acFacilityCost = acFacilityCost;
	}


	public HotelRequest getHotelRequest() {
		return hotelRequest;
	}


	public void setHotelRequest(HotelRequest hotelRequest) {
		this.hotelRequest = hotelRequest;
	}


	public int getRoomTypeCost() {
		return roomTypeCost;
	}


	public void setRoomTypeCost(int roomTypeCost) {
		this.roomTypeCost = roomTypeCost;
	}
	
	
	
	
	
	
	
	
	
	

}

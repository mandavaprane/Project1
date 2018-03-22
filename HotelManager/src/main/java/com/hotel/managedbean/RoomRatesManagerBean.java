package com.hotel.managedbean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.hotel.entity.Cusers;
import com.hotel.services.CUserService;
import com.hotel.services.CUserServiceImpl;


@ManagedBean(name="RateManagerBean")
@ApplicationScoped
public class RoomRatesManagerBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private BigDecimal simpleRoomRate=new BigDecimal("500.0");
	
	
	private BigDecimal doubleBedRoomRate=new BigDecimal("800.0");
	
	
	private BigDecimal FlowerDecorationRate=new BigDecimal("100.0");
	
	
	private BigDecimal  curtainDecorationRate=new BigDecimal("80.0");
	
	
	private BigDecimal colorDecorationRate=new BigDecimal("90.0");
	
	
	private BigDecimal  acDecorationRate=new BigDecimal("200.0");
	
	
	private BigDecimal taxiPickUpRate=new BigDecimal("100.0");
	
	
	public RoomRatesManagerBean(){
		
		CUserService userService= new CUserServiceImpl();
		List<Cusers> adminUserList= userService.getAdminUsers();
		if(adminUserList.size()<1){
			Cusers adminUser= new Cusers();
			adminUser.setFirstname("admin");
			adminUser.setLastname("admin");
			adminUser.setUsername("admin");
			adminUser.setPassword("admin@123");
			adminUser.setRoletype("administrator");
			adminUser.setEmail("admin@hotelmanager.com");
			Cusers updated= userService.createUser(adminUser);
			if(updated!=null)
				System.out.println("Default User Created!");
			else
				System.out.println("We are not able to create default user! please check for this.");
		}
	}
	


	public BigDecimal getSimpleRoomRate() {
		return simpleRoomRate;
	}


	public void setSimpleRoomRate(BigDecimal simpleRoomRate) {
		this.simpleRoomRate = simpleRoomRate;
	}


	public BigDecimal getDoubleBedRoomRate() {
		return doubleBedRoomRate;
	}


	public void setDoubleBedRoomRate(BigDecimal doubleBedRoomRate) {
		this.doubleBedRoomRate = doubleBedRoomRate;
	}


	public BigDecimal getFlowerDecorationRate() {
		return FlowerDecorationRate;
	}


	public void setFlowerDecorationRate(BigDecimal flowerDecorationRate) {
		FlowerDecorationRate = flowerDecorationRate;
	}


	public BigDecimal getCurtainDecorationRate() {
		return curtainDecorationRate;
	}


	public void setCurtainDecorationRate(BigDecimal curtainDecorationRate) {
		this.curtainDecorationRate = curtainDecorationRate;
	}


	public BigDecimal getColorDecorationRate() {
		return colorDecorationRate;
	}


	public void setColorDecorationRate(BigDecimal colorDecorationRate) {
		this.colorDecorationRate = colorDecorationRate;
	}


	public BigDecimal getAcDecorationRate() {
		return acDecorationRate;
	}


	public void setAcDecorationRate(BigDecimal acDecorationRate) {
		this.acDecorationRate = acDecorationRate;
	}


	public BigDecimal getTaxiPickUpRate() {
		return taxiPickUpRate;
	}


	public void setTaxiPickUpRate(BigDecimal taxiPickUpRate) {
		this.taxiPickUpRate = taxiPickUpRate;
	}


	
	
	
	
}

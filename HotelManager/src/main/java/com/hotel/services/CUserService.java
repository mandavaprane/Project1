package com.hotel.services;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import com.hotel.entity.Cusers;
import com.hotel.managedbean.LoginBean;
import com.hotel.util.SessionFactoryImpl;

public interface CUserService {

	SessionFactory SESSION_FACTORY= SessionFactoryImpl.returnService();
	
	public Cusers createUser(Cusers user);
	
	
	public boolean setLastLogin(Date  date);
	
	public List<Cusers> getUserList();
	
	
	public boolean isUserValidOrNot(List<Cusers> list , LoginBean  current);
	
	
	public Cusers  updateUser( Cusers user, String oldPassword, String newPassword);
	
	public String isUserNameOrEmailExist(Cusers user);
	
	public List<Cusers> getAdminUsers();
	
}

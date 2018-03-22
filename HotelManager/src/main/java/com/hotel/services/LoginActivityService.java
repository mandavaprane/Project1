package com.hotel.services;

import java.util.Date;

import org.hibernate.SessionFactory;

import com.hotel.entity.Cusers;
import com.hotel.entity.UserLogTable;
import com.hotel.util.SessionFactoryImpl;

public interface LoginActivityService {

	
	
	SessionFactory SESSION_FACTORY= SessionFactoryImpl.returnService();

	
	
	public UserLogTable addActivityLogEntry(String activityType, String activityStatus, Date activityTimeStamp, Cusers user);
}


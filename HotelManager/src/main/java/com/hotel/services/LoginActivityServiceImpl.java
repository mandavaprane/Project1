package com.hotel.services;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hotel.entity.Cusers;
import com.hotel.entity.UserLogTable;
import com.hotel.util.SessionFactoryImpl;

public class LoginActivityServiceImpl implements LoginActivityService{
	
	
	
	public UserLogTable addActivityLogEntry(String activityType, String activityStatus, Date activityTimeStamp, Cusers user) {
		
		
		Cusers thisUser=new CUserServiceImpl().getUserByUserName(user.getUsername());
		
		Session session= SESSION_FACTORY.getCurrentSession();
		Transaction tx= session.beginTransaction();
		
		UserLogTable logTb= new UserLogTable();
		logTb.setActivityType(activityType);
		logTb.setActivityStatus(activityStatus);
		logTb.setUserid(thisUser);
		logTb.setActivityTimeStamp(activityTimeStamp);
		
		
		session.save(logTb);
		tx.commit();
		
		return logTb;
	}

}

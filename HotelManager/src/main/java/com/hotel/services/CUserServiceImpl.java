package com.hotel.services;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hotel.entity.Cusers;
import com.hotel.managedbean.LoginBean;
import com.hotel.util.SessionFactoryImpl;

public class CUserServiceImpl implements CUserService{

	@Override
	public Cusers createUser(Cusers current) {
		Session session= SESSION_FACTORY.getCurrentSession();
		Transaction tx= session.beginTransaction();
		
		Cusers emp = new Cusers();
		emp.setUsername(current.getUsername());
		emp.setPassword(current.getPassword());
		emp.setFirstname(current.getFirstname());
		emp.setLastname(current.getLastname());
		emp.setEmail(current.getEmail());
		emp.setRoletype(current.getRoletype());
		emp.setLastLogin(new Date());
		session.save(emp);
		tx.commit();
		
		return emp;
	}

	@Override
	public boolean setLastLogin(Date activityDate) {
		// TODO Auto-generated method stub
		Session session= SESSION_FACTORY.getCurrentSession();
		Transaction tx= session.beginTransaction();
		
		try{
		Cusers user=(Cusers)session.get(Cusers.class, SessionFactoryImpl.getSessionManagerBean().getCurrentCuser().getUser_id());
	     user.setLastLogin(activityDate);
	 	session.update(user);
		 SessionFactoryImpl.getSessionManagerBean().setCurrentCuser((Cusers)session.get(Cusers.class, SessionFactoryImpl.getSessionManagerBean().getCurrentCuser().getUser_id()));
		}catch (NullPointerException e) {
			 
		}
		tx.commit();
		return true;
	}

	public Cusers getUserByUserName(String userName) {
	
		Session session= SESSION_FACTORY.getCurrentSession();
		
		Transaction tx= session.beginTransaction();
		List<Cusers> list=session.createQuery("from Cusers").list();
		tx.commit();		
		return 	list.get(0);
	}
	
	
	@Override
	public List<Cusers> getUserList() {
		// TODO Auto-generated method stub
		Session session= SESSION_FACTORY.getCurrentSession();
		Transaction tx= session.beginTransaction();
		List<Cusers> list=session.createQuery("from Cusers").list();
		tx.commit();		
		return list;
	}

	@Override
	public boolean isUserValidOrNot(List<Cusers> list, LoginBean current) {
		// TODO Auto-generated method stub
		
		ListIterator<Cusers> userit =list.listIterator();
		LoginActivityService addLoginLogData= new LoginActivityServiceImpl();
		if(userit!=null){	
			
			while(userit.hasNext())
				{
				Cusers  user=userit.next();
					
			
					
					if(user.getUsername().equalsIgnoreCase(current.getUsername()) && user.getPassword().equals(current.getLogpass()))
					{
						
						
						current.setLoggedinuser(user);
						
						SessionFactoryImpl.getSessionManagerBean().setCurrentCuser(user);
						
						addLoginLogData.addActivityLogEntry("Login", "Success", new Date(),user);
						
						return true;
					
					}
					else 
					if(user.getUsername().equalsIgnoreCase(current.getUsername())) {
					
					addLoginLogData.addActivityLogEntry("Login", "Failed", new Date(),user);
					
					}
					
				}
			}
				
		
		return false;
	}
	
	
	
	@Override
	public Cusers  updateUser( Cusers user, String oldPassword, String newPassword){
		Session session= SESSION_FACTORY.getCurrentSession();
		Transaction tx= session.beginTransaction();
		
	Query query = session.createQuery("from Cusers where email=:uemail AND password=:passwd");
		query.setParameter("uemail", user.getEmail());
		query.setParameter("passwd", user.getPassword());
		
		if( query.list().size()<1)
		{
			if(!tx.wasCommitted()){
				tx.commit();
			}
			
			return null;
		}
		Cusers actualUser=	(Cusers) query.list().get(0);
		actualUser.setPassword(newPassword);
		session.update(actualUser);
		

		
		
	 query = session.createQuery("from Cusers where email=:uemail AND password=:passwd");
		query.setParameter("uemail", user.getEmail());
		query.setParameter("passwd", newPassword);
		//actual
		
		if( query.list().size()<1)
		{
			if(!tx.wasCommitted()){
				tx.commit();
			}
			return null;
		}
		 actualUser=	(Cusers) query.list().get(0);
		
		if(!tx.wasCommitted()){
			tx.commit();
		}
				
		return actualUser;
		
	}

	@Override
	public String isUserNameOrEmailExist(Cusers user) {
		// TODO Auto-generated method stub
		List<Cusers> employeelist=getUserList();
		
		ListIterator<Cusers> userit =employeelist.listIterator();
		while(userit.hasNext())
		{
			Cusers currentUser=userit.next();
			System.out.println("current user email:"+currentUser.getEmail());
			System.out.println("database user email:"+user.getUsername());
			if(currentUser.getUsername().equalsIgnoreCase(user.getUsername()))
			{
			
				return "Invalidname";
				
			}
			else
			if( currentUser.getEmail().equalsIgnoreCase(user.getEmail()))
			{
		
				return "invalidid";
			}
			
		}
		return "success";
		
	}

	@Override
	public List<Cusers> getAdminUsers() {
		// TODO Auto-generated method stub
		Session session= SESSION_FACTORY.getCurrentSession();
		
		Transaction tx= session.beginTransaction();
		
		List<Cusers> list=session.createQuery("from Cusers where roletype='administrator'").list();
		
		tx.commit();		
		
		return 	list;
		
	}
	

	
	
}

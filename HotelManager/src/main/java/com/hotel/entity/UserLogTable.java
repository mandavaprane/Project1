package com.hotel.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_log_tb")
public class UserLogTable {

	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long logId;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private Cusers userid;
	
	
	@Column
	private String activityType;
	
	
	@Column
	private String activityStatus;
	
	
	@Column
	private Date activityTimeStamp;


	public Long getLogId() {
		return logId;
	}


	public void setLogId(Long logId) {
		this.logId = logId;
	}


	public Cusers getUserid() {
		return userid;
	}


	public void setUserid(Cusers userid) {
		this.userid = userid;
	}


	public String getActivityType() {
		return activityType;
	}


	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}


	public String getActivityStatus() {
		return activityStatus;
	}


	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}


	public Date getActivityTimeStamp() {
		return activityTimeStamp;
	}


	public void setActivityTimeStamp(Date activityTimeStamp) {
		this.activityTimeStamp = activityTimeStamp;
	}
	
	
	
	
	
	
	
		
}

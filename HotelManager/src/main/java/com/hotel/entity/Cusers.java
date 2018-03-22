package com.hotel.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author praneetha.
 * 
 */

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@XmlRootElement
public class Cusers implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int user_id;

	@Column
	private String username;

	@Column
	private String password;
	@Column
	private String firstname;

	@Column
	private String lastname;

	@Column
	private String email;

	@Column
	private String roletype;

	@Column
	private Date lastLogin;

	
	@OneToMany(mappedBy="userid", cascade = CascadeType.ALL)
	private Set<UserLogTable> logDataSet;
	
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int userId) {
		this.user_id = userId;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the roletype
	 */
	public String getRoletype() {
		return roletype;
	}

	/**
	 * @param roletype
	 *            the roletype to set
	 */
	public void setRoletype(String roletype) {
		this.roletype = roletype;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * @return the lastLogin
	 */
	public Date getLastLogin() {
		
		return lastLogin;
	}

	/**
	 * @param lastLogin
	 *            the lastLogin to set
	 */
	public void setLastLogin(Date lastLogin) {
	 	Calendar calendar = Calendar.getInstance();
		//calendar.setTimeZone(ConvertionStandard.TIME_ZONE_FOR_APPLICATION);
		calendar.setTime(lastLogin);
		this.lastLogin = calendar.getTime();
	}

	public Set<UserLogTable> getLogDataSet() {
		return logDataSet;
	}

	public void setLogDataSet(Set<UserLogTable> logDataSet) {
		this.logDataSet = logDataSet;
	}

	

	
	 
	
	
}

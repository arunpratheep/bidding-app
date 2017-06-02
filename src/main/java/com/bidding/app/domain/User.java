package com.bidding.app.domain;

import javax.persistence.*;

/**
 * Created by arun on 17/5/17.
 */
@Entity
@Table( name = "user" )
public class User {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "user_id" )
	private int userId;
	@Column( name = "user_name" )
	private String userName;
	@Column( name = "user_password" )
	private String userPassword;
	@Column( name = "user_email" )
	private String userEmail;
	@Column( name = "user_is_admin" )
	private boolean isAdmin;
	@Column( name = "user_is_active" )
	private boolean isActive;

	public int getUserId()
	{
		return userId;
	}

	public void setUserId( int userId )
	{
		this.userId = userId;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName( String userName )
	{
		this.userName = userName;
	}

	public String getUserPassword()
	{
		return userPassword;
	}

	public void setUserPassword( String userPassword )
	{
		this.userPassword = userPassword;
	}

	public String getUserEmail()
	{
		return userEmail;
	}

	public void setUserEmail( String userEmail )
	{
		this.userEmail = userEmail;
	}

	public boolean isAdmin()
	{
		return isAdmin;
	}

	public void setAdmin( boolean admin )
	{
		isAdmin = admin;
	}

	public boolean isActive()
	{
		return isActive;
	}

	public void setActive( boolean active )
	{
		isActive = active;
	}
}

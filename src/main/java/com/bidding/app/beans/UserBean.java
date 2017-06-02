package com.bidding.app.beans;

/**
 * Bean class for User
 * @author ARUN P P
 */
public class UserBean {

	private int userId;
	private String userName;
	private String userPassword;
	private String userEmail;
	private boolean isAdmin;
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

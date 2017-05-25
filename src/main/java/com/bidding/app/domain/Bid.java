package com.bidding.app.domain;

import javax.persistence.*;

/**
 * Bean class for Bid
 * @author ARUN P P
 */
@Entity
@Table( name = "bid" )
public class Bid {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "bid_id" )
	private int bidId;
	@OneToOne
	@JoinColumn( name = "bid_user" )
	private User user;
	@Column( name = "bid_amount" )
	private float bidAmount;
	@Column( name = "bid_is_active" )
	private boolean isActive;

	public int getBidId()
	{

		return bidId;
	}

	public void setBidId( int bidId )
	{
		this.bidId = bidId;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser( User user )
	{
		this.user = user;
	}

	public float getBidAmount()
	{
		return bidAmount;
	}

	public void setBidAmount( float bidAmount )
	{
		this.bidAmount = bidAmount;
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

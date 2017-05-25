package com.bidding.app.beans;

/**
 * Bean class for Bid
 * @author ARUN P P
 */
public class BidBean {

	private int bidId;
	private int userId;
	private float bidAmount;
	private boolean isActive;

	public int getBidId()
	{
		return bidId;
	}

	public void setBidId( int bidId )
	{
		this.bidId = bidId;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId( int userId )
	{
		this.userId = userId;
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

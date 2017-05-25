package com.bidding.app.common;

/**
 * Response class
 * @author ARUN P P
 */
public class Response {

	private Object object;
	private String errorMessage;

	public Object getObject()
	{
		return object;
	}

	public void setObject( Object object )
	{
		this.object = object;
	}

	public String getErrorMessage()
	{
		return errorMessage;
	}

	public void setErrorMessage( String errorMessage )
	{
		this.errorMessage = errorMessage;
	}

}


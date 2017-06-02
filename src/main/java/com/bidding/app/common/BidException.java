package com.bidding.app.common;

import org.springframework.http.HttpStatus;

/**
 * Custom exception class
 * @author ARUN P P
 *
 */
public class BidException extends Exception {

	private static final long serialVersionUID = -7370395548630060079L;
	private final String errorMessage;
	private final HttpStatus httpStatus;

	/**
	 * Constructor to create custom exception
	 * @param errorMessage details of the error
	 * @param httpStatus status of the error
	 */
	public BidException( String errorMessage, HttpStatus httpStatus )
	{
		super();
		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
	}

	public String getErrorMessage()
	{
		return errorMessage;
	}

	public HttpStatus getHttpStatus()
	{
		return httpStatus;
	}
}

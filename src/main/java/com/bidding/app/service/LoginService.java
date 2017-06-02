package com.bidding.app.service;

import com.bidding.app.beans.Credential;
import com.bidding.app.common.BidException;

/**
 * Class to perform all the login operations
 * @author ARUN P P
 */
public interface LoginService {

	/**
	 * Method to do login
	 * @param credential the data for login
	 * @return true if credential matches with db else returns false
	 * @throws BidException if user with that particular name does not exists
	 */
	boolean login( Credential credential ) throws BidException;
}

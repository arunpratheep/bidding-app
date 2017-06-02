package com.bidding.app.service;

import com.bidding.app.beans.UserBean;
import com.bidding.app.common.BidException;

import java.util.List;

/**
 * Class to perform all the user operations
 * @author ARUN P P
 */
public interface UserService {

	/**
	 * Function to save the user
	 *
	 * @param userBean the user data that is to be saved
	 * @throws BidException If user with particular name or email exists
	 */
	void saveUser( UserBean userBean ) throws BidException;

	/**
	 * Function to update the user
	 *
	 * @param userBean the user data that is to be updated
	 * @throws BidException If user with particular id does not exists
	 */
	void updateUser( UserBean userBean ) throws BidException;

	/**
	 * Function to delete the user
	 *
	 * @param userBean the user data that is to be deleted
	 * @throws BidException If user with particular id does not exists
	 */
	void deleteUser( UserBean userBean ) throws BidException;

	/**
	 * Function to get user by userId
	 *
	 * @param userId the id of the user to be fetched
	 * @return user the user bean for that particular id
	 * @throws BidException if user with particular id does not exists
	 */
	UserBean getUser( int userId ) throws BidException;

	/**
	 * Function to get all users in the db
	 *
	 * @return the list of all the users in the db
	 * @throws BidException if no user exists in db
	 */
	List<UserBean> getAllUsers() throws BidException;
}

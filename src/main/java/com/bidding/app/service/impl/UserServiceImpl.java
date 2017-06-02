package com.bidding.app.service.impl;

import com.bidding.app.beans.UserBean;
import com.bidding.app.common.BidException;
import com.bidding.app.common.EncryptData;
import com.bidding.app.domain.User;
import com.bidding.app.repository.UserRepo;
import com.bidding.app.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation class to perform all the user operations
 * @author ARUN P P
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepo userRepo;

	@Override
	public void saveUser( UserBean userBean ) throws BidException
	{
		if( userRepo.findByUserName(userBean.getUserName()) != null )
		{
			LOGGER.error("User with name : " + userBean.getUserName() + " already exists");
			throw new BidException("User with name : " + userBean.getUserName() + " already exists",
					HttpStatus.NOT_ACCEPTABLE);
		}
		else if( userRepo.findByUserEmail(userBean.getUserEmail()) != null )
		{
			LOGGER.error("User with email : " + userBean.getUserEmail() + " already exists");
			throw new BidException("User with email : " + userBean.getUserEmail() + " already exists",
					HttpStatus.NOT_ACCEPTABLE);
		}
		userRepo.save(createUser(userBean));
	}

	@Override
	public void updateUser( UserBean userBean ) throws BidException
	{
		User user = checkUserExists(userBean.getUserId());
		User updatedUser = createUser(userBean);
		updatedUser.setUserId(user.getUserId());
		userRepo.save(user);
	}

	@Override
	public void deleteUser( UserBean userBean ) throws BidException
	{
		User user = checkUserExists(userBean.getUserId());
		user.setActive(false);
		userRepo.save(user);
	}

	@Override
	public UserBean getUser( int userId ) throws BidException
	{
		return createUserBean(checkUserExists(userId));
	}

	@Override
	public List<UserBean> getAllUsers() throws BidException
	{
		List<UserBean> userBeanList = new ArrayList<>();
		final List<User> allUsers = userRepo.findAllUsers();
		if( allUsers == null || allUsers.isEmpty() )
		{
			LOGGER.error("No users exist in db");
			throw new BidException("No users exist in db", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		for( User user : allUsers )
		{
			userBeanList.add(createUserBean(user));
		}
		return userBeanList;
	}

	/**
	 * Method to create user from user bean
	 * @param userBean data to create new user object
	 * @return new user object
	 * @throws BidException if any encryption error occurs
	 */
	private User createUser( UserBean userBean ) throws BidException
	{
		User user = new User();
		user.setUserName(userBean.getUserName());
		//Encrypt the password
		user.setUserPassword(EncryptData.encryptPassword(userBean.getUserPassword()));
		user.setUserEmail(userBean.getUserEmail());
		user.setAdmin(userBean.isAdmin());
		user.setActive(userBean.isActive());
		return user;
	}

	/**
	 * Method to create user bean from user
	 * @param user data to create user bean object
	 * @return new user  bean object
	 */
	private UserBean createUserBean( User user )
	{
		UserBean userBean = new UserBean();
		userBean.setUserName(user.getUserName());
		userBean.setActive(user.isActive());
		userBean.setUserEmail(user.getUserEmail());
		userBean.setUserId(user.getUserId());
		userBean.setAdmin(user.isAdmin());
		return userBean;
	}

	/**
	 * Method to check if a user exists or not
	 * @param userId the id to check whether the user exits in the db
	 * @return user if the data exists in the db
	 * @throws BidException if user with given id does not exists in the db
	 */
	private User checkUserExists( int userId ) throws BidException
	{
		//Check if user with particular id exists or not
		User userData = userRepo.findByUserId(userId);
		if( userData == null )
		{
			LOGGER.error("User with id - " + userId + " does not exists");
			throw new BidException("User with id - " + userId + " does not exists", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return userData;

	}
}

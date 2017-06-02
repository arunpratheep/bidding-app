package com.bidding.app.service.impl;

import com.bidding.app.beans.Credential;
import com.bidding.app.common.BidException;
import com.bidding.app.common.EncryptData;
import com.bidding.app.domain.User;
import com.bidding.app.repository.UserRepo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Implementation class to perform all the login operations
 * @author ARUN P P
 */
@Service
public class LoginServiceImpl implements com.bidding.app.service.LoginService {

	private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepo userRepo;

	@Override
	public boolean login( Credential credential ) throws BidException
	{
		//Check if user with particular id exists or not
		User userData = userRepo.findByUserName(credential.getUserName());
		if( userData == null )
		{
			LOGGER.error("User with name - " + credential.getUserName() + " does not exists");
			throw new BidException("User with id - " + credential.getUserName() + " does not exists",
					HttpStatus.FORBIDDEN);
		}
		String password = EncryptData.encryptPassword(credential.getPassword());
		if( password.equals(userData.getUserPassword()) )
		{
			return true;
		}
		return false;
	}
}

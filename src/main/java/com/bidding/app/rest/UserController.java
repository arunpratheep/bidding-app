package com.bidding.app.rest;

import com.bidding.app.beans.UserBean;
import com.bidding.app.common.BidException;
import com.bidding.app.common.Response;
import com.bidding.app.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for User
 * @author ARUN P P
 */
@RestController()
@RequestMapping( "/rest/user" )
public class UserController {

	private static final Logger LOGGER = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping( value = "/save", method = RequestMethod.POST )
	public ResponseEntity<Response> saveOrUpdateUser( @RequestBody UserBean userBean )
	{
		Response response = new Response();
		try
		{
			//If id is zero create a new user
			if( userBean.getUserId() == 0 )
			{
				userService.saveUser(userBean);
			}
			else if( userBean.isActive() )
			{
				userService.updateUser(userBean);
			}
			else
			{
				userService.deleteUser(userBean);
			}
			response.setObject(null);
			response.setErrorMessage(null);
			return new ResponseEntity(response, HttpStatus.OK);
		}
		catch( BidException e )
		{
			LOGGER.error(e.getMessage());
			response.setErrorMessage(e.getErrorMessage());
			response.setObject(null);
			return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

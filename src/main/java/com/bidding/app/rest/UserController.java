package com.bidding.app.rest;

import com.bidding.app.beans.UserBean;
import com.bidding.app.common.BidException;
import com.bidding.app.common.Response;
import com.bidding.app.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
			UserBean user = null;
			//If id is zero create a new user
			if( userBean.getUserId() == 0 )
			{
				user = userService.saveUser(userBean);
			}
			else if( userBean.isActive() )
			{
				user = userService.updateUser(userBean);
			}
			else
			{
				userService.deleteUser(userBean);
			}
			response.setObject(user);
			response.setErrorMessage(null);
			return new ResponseEntity(response, HttpStatus.OK);
		}
		catch( BidException e )
		{
			LOGGER.error(e.getMessage());
			response.setErrorMessage(e.getErrorMessage());
			response.setObject(null);
			return new ResponseEntity(response, e.getHttpStatus());
		}
	}

	@RequestMapping( value = "/get-user/{userId}", method = RequestMethod.GET )
	public ResponseEntity<Response> getUser( @PathVariable( "userId" ) int userId )
	{
		Response response = new Response();
		try
		{
			System.out.println(userId);
			UserBean user = userService.getUser(userId);
			response.setObject(user);
			response.setErrorMessage(null);
			return new ResponseEntity(response, HttpStatus.OK);
		}
		catch( BidException e )
		{
			LOGGER.error(e.getMessage());
			response.setErrorMessage(e.getErrorMessage());
			response.setObject(null);
			return new ResponseEntity(response, e.getHttpStatus());
		}
	}

	@RequestMapping( value = "/get-all-users", method = RequestMethod.GET )
	public ResponseEntity<Response> getAllUsers()
	{
		Response response = new Response();
		try
		{
			List<UserBean> users = userService.getAllUsers();
			response.setObject(users);
			response.setErrorMessage(null);
			return new ResponseEntity(response, HttpStatus.OK);
		}
		catch( BidException e )
		{
			LOGGER.error(e.getMessage());
			response.setErrorMessage(e.getErrorMessage());
			response.setObject(null);
			return new ResponseEntity(response, e.getHttpStatus());
		}
	}
}

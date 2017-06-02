package com.bidding.app.rest;

import com.bidding.app.beans.Credential;
import com.bidding.app.common.BidException;
import com.bidding.app.common.Response;
import com.bidding.app.service.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for Login
 * @author ARUN P P
 */
@RestController()
@RequestMapping( "/rest/login" )
public class LoginController {

	private static final Logger LOGGER = Logger.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	@RequestMapping( value = "/dologin", method = RequestMethod.POST )
	public ResponseEntity<Response> doLogin( @RequestBody Credential credential )
	{
		System.out.println("Here");
		Response response = new Response();
		try
		{
			if( loginService.login(credential) )
			{
				response.setObject(null);
				response.setErrorMessage(null);
				System.out.println("Success");
				return new ResponseEntity(response, HttpStatus.OK);
			}
			else
			{
				response.setObject(null);
				response.setErrorMessage("Invalid Credentials");
				return new ResponseEntity(response, HttpStatus.FORBIDDEN);
			}
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

package com.bidding.app.rest;

import com.bidding.app.beans.BidBean;
import com.bidding.app.common.BidException;
import com.bidding.app.common.Response;
import com.bidding.app.service.BidService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller for Bid
 * @author ARUN P P
 */
@RestController()
@RequestMapping( "/rest/bid" )
public class BidController {

	private static final Logger LOGGER = Logger.getLogger(BidController.class);

	@Autowired
	private BidService bidService;

	@RequestMapping( value = "/save", method = RequestMethod.POST )
	public ResponseEntity<Response> saveOrUpdateUser( @RequestBody BidBean bidBean )
	{
		Response response = new Response();
		try
		{
			//If id is zero create a new bid
			//If id is not zero and isActive is true then it will update the bid
			if( bidBean.isActive() )
			{
				bidService.saveBid(bidBean);
			}
			else
			{
				bidService.deleteBid(bidBean);
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
			return new ResponseEntity(response, e.getHttpStatus());
		}
	}

	@RequestMapping( value = "/get-bid/{bidId}", method = RequestMethod.GET )
	public ResponseEntity<Response> getBid( @PathVariable( "bidId" ) int bidId )
	{
		Response response = new Response();
		try
		{
			BidBean bid = bidService.getBid(bidId);
			response.setObject(bid);
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

	@RequestMapping( value = "/get-all-bids", method = RequestMethod.GET )
	public ResponseEntity<Response> getAllBids()
	{
		Response response = new Response();
		try
		{
			List<BidBean> bids = bidService.getAllBids();
			response.setObject(bids);
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

	@RequestMapping( value = "/get-lowest-bid", method = RequestMethod.POST )
	public ResponseEntity<Response> getLowestBid( @RequestBody BidBean bidBean )
	{
		Response response = new Response();
		try
		{
			BidBean bid = bidService.getTheLowestBid(bidBean);
			response.setObject(bid);
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

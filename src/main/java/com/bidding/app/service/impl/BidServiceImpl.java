package com.bidding.app.service.impl;

import com.bidding.app.beans.BidBean;
import com.bidding.app.common.BidException;
import com.bidding.app.domain.Bid;
import com.bidding.app.domain.User;
import com.bidding.app.repository.BidRepo;
import com.bidding.app.repository.UserRepo;
import com.bidding.app.service.BidService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation class to perform all bid operations
 * @author ARUN P P
 */
@Service
public class BidServiceImpl implements BidService {

	private static final Logger LOGGER = Logger.getLogger(BidServiceImpl.class);

	@Autowired
	private BidRepo bidRepo;
	@Autowired
	private UserRepo userRepo;

	@Override
	public void saveBid( BidBean bidBean )
	{
		//Check if bid for particular user already exists or not
		Bid bid = bidRepo.findByUserId(bidBean.getUserId());
		//If bid is present update the info
		if( bid != null )
		{
			bid.setBidAmount(bidBean.getBidAmount());
			bidRepo.save(bid);
		}
		else
		{
			bidRepo.save(createBid(bidBean));
		}
	}

	@Override
	public void deleteBid( BidBean bidBean ) throws BidException
	{
		Bid bid = checkIfBidExists(bidBean.getBidId());
		bid.setActive(false);
		bidRepo.save(bid);
	}

	@Override
	public BidBean getBid( BidBean bidBean ) throws BidException
	{
		return createBidBean(checkIfBidExists(bidBean.getBidId()));
	}

	@Override
	public BidBean getTheLowestBid( BidBean bidBean ) throws BidException
	{
		User user = userRepo.findByUserId(bidBean.getUserId());
		//Check if user has the privilege to access the data
		if( user == null || (user != null && !user.isAdmin()) )
		{
			throw new BidException("User unauthorized", HttpStatus.FORBIDDEN);
		}
		return createBidBean(bidRepo.getTheSmallestBid().get(0));
	}

	@Override
	public List<BidBean> getAllBids()
	{
		List<BidBean> bidBeanList = new ArrayList<>();
		for( Bid bid : bidRepo.getAllBids() )
		{
			bidBeanList.add(createBidBean(bid));
		}
		return bidBeanList;
	}

	/**
	 * Method to create bid from bid bean
	 * @param bidBean data to create bid object
	 * @return new bid object
	 */
	private Bid createBid( BidBean bidBean )
	{
		Bid bid = new Bid();
		bid.setBidAmount(bidBean.getBidAmount());
		bid.setActive(bidBean.isActive());
		bid.setUser(userRepo.findByUserId(bidBean.getUserId()));
		return bid;
	}

	/**
	 * Method to create bid bean from bid
	 * @param bid date to create bid bean
	 * @return new bid bean object
	 */
	private BidBean createBidBean( Bid bid )
	{
		BidBean bidBean = new BidBean();
		bidBean.setBidId(bid.getBidId());
		bidBean.setBidAmount(bid.getBidAmount());
		bidBean.setUserId(bid.getUser().getUserId());
		bidBean.setActive(bid.isActive());
		return bidBean;

	}

	/**
	 * Method to check if bid exists or not
	 * @param bidId the id to check whether the bid exits in the db
	 * @return bid if data exists in the db
	 * @throws Exception if bid with given id does not exists in the db
	 */
	private Bid checkIfBidExists( int bidId ) throws BidException
	{
		//Check if bid with particular id exists or not
		Bid bid = bidRepo.findByBidId(bidId);
		if( bid == null )
		{
			LOGGER.error("Bid with id - " + bidId + " does not exists");
			throw new BidException("Bid with id - " + bidId + " does not exists", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return bid;

	}
}

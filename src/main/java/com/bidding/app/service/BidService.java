package com.bidding.app.service;

import com.bidding.app.beans.BidBean;
import com.bidding.app.common.BidException;

import java.util.List;

/**
 * Class to perform all the bid operations
 * @author ARUN P P
 */
public interface BidService {

	/**
	 * Function to save bid
	 *
	 * @param bidBean the bid data that is to be saved
	 */
	void saveBid( BidBean bidBean );

	/**
	 * Function to delete bid
	 *
	 * @param bidBean the bid data that is to be deleted
	 * @throws BidException if bid with particular id does not exist
	 */
	void deleteBid( BidBean bidBean ) throws BidException;

	/**
	 * Function to get bid by bidId
	 *
	 * @param bidId the id for the bid to be fetched
	 * @return bidBean the bean for the porticular id
	 * @throws BidException if bid with paricular id does not exists
	 */
	BidBean getBid( int bidId ) throws BidException;

	/**
	 * Function to get the lowest bid
	 *
	 * @param bidBean the details of the user requesting the lowest bid
	 * @return lowest bid
	 * @throws BidException if the user is not authorized or there is no smallest bid
	 */
	BidBean getTheLowestBid( BidBean bidBean ) throws BidException;

	/**
	 * Function to get all the bids in the db
	 *
	 * @return list of all the bids in the db
	 * @throws BidException if no bid exists in db
	 */
	List<BidBean> getAllBids() throws BidException;
}

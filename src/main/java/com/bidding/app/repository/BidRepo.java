package com.bidding.app.repository;

import com.bidding.app.domain.Bid;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Repository for Bid
 * @author ARUN P P
 */
@Transactional
public interface BidRepo extends CrudRepository<Bid, Integer> {

	@Query( "FROM Bid b WHERE b.user.userId = ?0 and b.isActive = true" )
	Bid findByUserId( int userId );

	@Query( "FROM Bid b WHERE b.isActive = true GROUP BY b.bidAmount having count (b.bidAmount) < 1 ORDER BY b.bidAmount" )
	List<Bid> getTheSmallestBid();

	@Query( "FROM Bid b WHERE b.bidId = ?0 and b.isActive = true" )
	Bid findByBidId( int bidId );

	@Query( "FROM Bid b WHERE b.isActive =true" )
	List<Bid> getAllBids();
}

package com.bidding.app.repository;

import com.bidding.app.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Repository for User
 * @author ARUN P P
 */
@Transactional
public interface UserRepo extends CrudRepository<User, Integer> {

	@Query( "FROM User u WHERE u.userId = ?1 AND u.isActive = true" )
	User findByUserId( int userId );

	@Query( "FROM User u WHERE u.userName = ?1 AND u.isActive = true" )
	User findByUserName( String userName );

	@Query( "FROM User u WHERE u.userEmail = ?1 AND u.isActive = true" )
	User findByUserEmail( String userEmail );

	@Query( "FROM User u WHERE u.isActive = true" )
	List<User> findAllUsers();
}

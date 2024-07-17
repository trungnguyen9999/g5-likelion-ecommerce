package com.likelion.ecommerce.repository;

import com.likelion.ecommerce.dto.UserDetailsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.likelion.ecommerce.entities.User;

import java.util.List;

/**
 * Repository is an interface that provides access to data in a database
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findFirstByAccountId(Integer accountId);

	@Query("select u.accountId as accountId, u.userId as userId, u.addressLine1 as addressLine1, u.addressLine2 as addressLine2, " +
			"u.apartment as apartment,  u.avatar as avatar, u.birthdate as birthdate, u.city as city, u.email as email, " +
			"u.fullName as fullName, u.phoneNumber as phoneNumber, u.region as region, u.suburb as suburb, " +
			"a.username as username, a.password as password, a.type as type, a.status as status, a.createdAt as created_at " +
			"from Account a " +
			"join User u on a.accountId = u.accountId " +
			"where :accountId is null or a.accountId = :accountId")
	List<UserDetailsDto> getUserInfoDetails(@Param("accountId") Integer accountId);

	User findByEmail(String email);

}

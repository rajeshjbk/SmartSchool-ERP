package com.raj.schoolerp.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.raj.schoolerp.model.UserRole;
import com.raj.schoolerp.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

	public Optional<Users> findByEmail(String email);

//	List<Users> findByRole(UserRole role);

	// Get User By Username
	@Query("SELECT u FROM Users u " + "WHERE u.userName = :userName")
	Optional<Users> findUserByUserName(@Param("userName") String userName);

	// Get Users By Role
	@Query("SELECT u FROM Users u " + "WHERE u.role = :role")
	List<Users> findUsersByRole(@Param("role") UserRole role);

	// Get Users By Ids
	@Query("SELECT u FROM Users u " + "WHERE u.userId IN :userIds")
	List<Users> findUsersByIds(@Param("userIds") List<Long> userIds);
}

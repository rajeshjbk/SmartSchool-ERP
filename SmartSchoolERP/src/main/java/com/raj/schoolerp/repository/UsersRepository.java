package com.raj.schoolerp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.raj.schoolerp.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

	public Optional<Users> findByEmail(String email);
}

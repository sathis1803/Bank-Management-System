package com.stg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stg.entity.Account;
import com.stg.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	@Query(value = "select * from user where username = ?",nativeQuery = true)
	public abstract User findByUserName(String username);
	
	public abstract Account findById(int accNo);
}

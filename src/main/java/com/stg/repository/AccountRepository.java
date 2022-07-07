package com.stg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.stg.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{

	public boolean existsByPanNo(String panNo);
	
	public Account findByPanNo(String panNo);
	
	public Account findByPanNoAndMobileNo(String panNo,String mobileNo);
	
	@Query(value = "select * from accounts inner join branches on accounts.branch_id = branches.branch_id",nativeQuery = true)
	public List<Account> getAllFromAccounts();
}

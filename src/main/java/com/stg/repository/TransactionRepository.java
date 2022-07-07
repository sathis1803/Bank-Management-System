package com.stg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stg.entity.Transaction;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

	@Query(value = "select * from transactions where accno = ?",nativeQuery = true)
	public List<Transaction> findAllByAccNo(int accNo);
	
//	@Query(value = "select * from transactions",nativeQuery = true)
//	public List<Transaction> getAllTransactions();
	
	
	
	
}

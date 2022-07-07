package com.stg.service;

import java.util.List;

import com.stg.entity.Transaction;

public interface TransactionService {
	
	public List<Transaction> allTransaction();
	
	public long getCountAllTransaction();
	
	public List<Transaction> allTransactionByAccNo(int accNo);
}

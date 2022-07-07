package com.stg.service;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stg.entity.Transaction;
import com.stg.repository.TransactionRepository;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public List<Transaction> allTransactionByAccNo(int accNo) {

		return transactionRepository.findAllByAccNo(accNo);
	}

	@Override
	public List<Transaction> allTransaction() {
		
		return transactionRepository.findAll();
	}

	@Override
	public long getCountAllTransaction() {
		
		return transactionRepository.count();
	}
	
	/*
	 * @Override public Transaction createTrans(Transaction transaction) { if
	 * (accountRepository.existsById(transaction.getAccount().getAccNo())) {
	 * Optional<Account> optional =
	 * accountRepository.findById(transaction.getAccount().getAccNo()); Account
	 * account = optional.get(); if (account.isAccStatus() == true) { if
	 * (transaction.getTransAmount() > 0) { if (transaction.isTransType() == false)
	 * { if (account.getBalance() >= transaction.getTransAmount()) {
	 * account.setBalance(account.getBalance() - transaction.getTransAmount());
	 * accountRepository.save(account);
	 * transaction.setLocalDateTime(LocalDateTime.now());
	 * transactionRepository.save(transaction); } else { throw new
	 * CustomException("Balance is low"); } } else {
	 * account.setBalance(transaction.getTransAmount());
	 * accountRepository.save(account);
	 * transaction.setLocalDateTime(LocalDateTime.now());
	 * transactionRepository.save(transaction); } } else { throw new
	 * CustomException("Enter Valid Amount"); } } else { throw new
	 * CustomException("Activate Your Account"); } } else { throw new
	 * CustomException("Account Does not Exist!"); } return transaction; }
	 */

}

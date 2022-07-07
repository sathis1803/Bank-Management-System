package com.stg.service;

import java.util.List;

import com.stg.entity.Account;

public interface AccountService {
	
	public abstract List<Account> getAllAccount();
	
	public abstract Account getAccount(String panNo, String mobileNo);
	
	public abstract String getMessage(String panNo, String mobileNo,double amount,int accNo);

	public abstract Account addAccount(Account account);
	
	public abstract Account getAccById(int accNo);
	
	public abstract long countSavingsAcc();
	
	public abstract int deleteAccById(int accNo);
	
	public abstract Account updateMobile(int accNo, String mobileno);
	
	public abstract Account updateStatus(int accNo, boolean accStatus);
	
	public abstract Account deposit(int accNo,double depositamount);
	
	public abstract Account depositSend(int accNo,double depositamount);
	
	public abstract Account withdraw(int accNo,double withdrawamount);
	
	public abstract String deposited(Account account,int accNo,int depositamount);
	
}

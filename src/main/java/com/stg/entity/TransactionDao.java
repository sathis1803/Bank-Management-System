package com.stg.entity;

import java.time.LocalDateTime;

public class TransactionDao {

	private long transId;

	private boolean transType;

	private double transAmount;

	private LocalDateTime localDateTime;

	private int accNo;

	public TransactionDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransactionDao(long transId, boolean transType, double transAmount, LocalDateTime localDateTime, int accNo) {
		super();
		this.transId = transId;
		this.transType = transType;
		this.transAmount = transAmount;
		this.localDateTime = localDateTime;
		this.accNo = accNo;
	}

	public long getTransId() {
		return transId;
	}

	public void setTransId(long transId) {
		this.transId = transId;
	}

	public boolean isTransType() {
		return transType;
	}

	public void setTransType(boolean transType) {
		this.transType = transType;
	}

	public double getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(double transAmount) {
		this.transAmount = transAmount;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public int getAccNo() {
		return accNo;
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

}

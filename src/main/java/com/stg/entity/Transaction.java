package com.stg.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long transId;

	@Column(name = "transtype", nullable = false)
	private boolean transType;

	//@Pattern(regexp = "(/^[0-9]+$/)",message = "Required Numeric Input")
	@Column(name = "transamount", nullable = false)
	private double transAmount;

	@Column(name = "transdate", nullable = false)
	private LocalDateTime localDateTime;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "accno",referencedColumnName = "accNo")
	@JsonBackReference(value = "trans")
	private Account account;

	public Transaction() {
		super();
	}

	public Transaction(long transId, boolean transType, double transAmount, LocalDateTime localDateTime,
			Account account) {
		super();
		this.transId = transId;
		this.transType = transType;
		this.transAmount = transAmount;
		this.localDateTime = localDateTime;
		this.account = account;
	}
	
	public int getAccNo() {
		return account.getAccNo();
	}
	
	public int getBranchId() {
		return account.getBranchId();
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}

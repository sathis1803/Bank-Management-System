package com.stg.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "accounts")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accNo;

	@NotEmpty(message = "Required Name")
	@Column(name = "accholdername",length = 20,nullable = false)
	private String accHolderName;

	@Column(name = "balance",nullable = false)
	private double balance;
	
	@Pattern(regexp = "^(?:(\\d{2})\\s?)?(?:(\\d{3})\\s?)?(\\d{7,10})$",message = "Required Numeric Input")
	@Column(name = "mobileno",nullable = false)
	private String mobileNo;
	
	@NotEmpty(message = "Required Pan No")
	@Column(name = "panno",length = 15, nullable = false)
	private String panNo;

	@Column(name = "accopeningdate", nullable = false)
	private LocalDate accOpenDate;

	@NotEmpty(message = "Required Account Type")
	@Column(name = "acctype", length = 10, nullable = false)
	private String accType;

	@Column(name = "accstatus",nullable = false)	
	private boolean accStatus;
	
	@ManyToOne
	@JoinColumn(name = "branch_id",referencedColumnName = "branch_id", nullable = false)
	@JsonBackReference
	private Branch branch;
	
	@JsonManagedReference(value = "user")
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "account")
	private User user;

	@JsonManagedReference(value = "trans")
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "account")
	private List<Transaction> transactions;

	public Account() {
		super();
	}
	

//	public Account(int accNo, String accHolderName, double balance, String mobileNo, String panNo,
//			LocalDate accOpenDate, String accType, boolean accStatus, Branch branch) {
//		super();
//		this.accNo = accNo;
//		this.accHolderName = accHolderName;
//		this.balance = balance;
//		this.mobileNo = mobileNo;
//		this.panNo = panNo;
//		this.accOpenDate = accOpenDate;
//		this.accType = accType;
//		this.accStatus = accStatus;
//		this.branch = branch;
//	}


	public Account(int accNo, String accHolderName, double balance, String mobileNo, String panNo,
			LocalDate accOpenDate, String accType, boolean accStatus, Branch branch, List<Transaction> transactions) {
		super();
		this.accNo = accNo;
		this.accHolderName = accHolderName;
		this.balance = balance;
		this.mobileNo = mobileNo;
		this.panNo = panNo;
		this.accOpenDate = accOpenDate;
		this.accType = accType;
		this.accStatus = accStatus;
		this.branch = branch;
		this.transactions = transactions;
	}
	
	

	public Account(int accNo, @NotEmpty(message = "Required Name") String accHolderName, double balance,
			@Pattern(regexp = "^(?:(\\d{2})\\s?)?(?:(\\d{3})\\s?)?(\\d{7,10})$", message = "Required Numeric Input") String mobileNo,
			@NotEmpty(message = "Required Pan No") String panNo, LocalDate accOpenDate,
			@NotEmpty(message = "Required Account Type") String accType, boolean accStatus, Branch branch, User user,
			List<Transaction> transactions) {
		super();
		this.accNo = accNo;
		this.accHolderName = accHolderName;
		this.balance = balance;
		this.mobileNo = mobileNo;
		this.panNo = panNo;
		this.accOpenDate = accOpenDate;
		this.accType = accType;
		this.accStatus = accStatus;
		this.branch = branch;
		this.user = user;
		this.transactions = transactions;
	}


	public int getAccNo() {
		return accNo;
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	public String getAccHolderName() {
		return accHolderName;
	}

	public void setAccHolderName(String accHolderName) {
		this.accHolderName = accHolderName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public LocalDate getAccOpenDate() {
		return accOpenDate;
	}

	public void setAccOpenDate(LocalDate accOpenDate) {
		this.accOpenDate = accOpenDate;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public boolean isAccStatus() {
		return accStatus;
	}

	public void setAccStatus(boolean accStatus) {
		this.accStatus = accStatus;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

}

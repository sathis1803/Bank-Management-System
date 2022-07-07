package com.stg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "user")
public class User {	
	
	@Id	
	@Column(name = "userid",unique = true,nullable = false)
	private int userId;
	
	@NotEmpty(message = "Required username")
	@Column(name = "username",nullable = false,unique = true)
	private String userName;
	
	@Column(name = "password",nullable = false)
	private String password;
	
	@OneToOne
	@JoinColumn(name = "accNo",referencedColumnName = "accNo",nullable = false)
	@JsonBackReference(value = "user")
	private Account account;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public User(int userId, String userName, String password, Account account) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.account = account;
	}


	public Account getAccount() {
		return account;
	}

	public void setAccountone(Account account) {
		this.account = account;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}

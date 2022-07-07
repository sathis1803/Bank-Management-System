package com.stg.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "branches")
public class Branch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "branch_id")
	private int branchId;
	
	@NotEmpty(message = "Provide Branch Name")
	@Column(name = "branch_name", length = 25, nullable = false)
	private String branchName;
	
	@NotEmpty(message = "Provide Branch City")
	@Column(name = "branch_city", length = 25, nullable = false)
	private String branchCity;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "branch")
	private Set<Account> accounts;
	
//	@JsonManagedReference
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "branchl")
//	private Set<BranchAdmin> branchAdmins;

	public Branch() {
		super();
	}

	public Branch(int branchId, String branchName, String branchCity) {
		super();
		this.branchId = branchId;
		this.branchName = branchName;
		this.branchCity = branchCity;
	}

	public Branch(int branchId, String branchName, String branchCity, Set<Account> accounts) {
		super();
		this.branchId = branchId;
		this.branchName = branchName;
		this.branchCity = branchCity;
		this.accounts = accounts;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchCity() {
		return branchCity;
	}

	public void setBranchCity(String branchCity) {
		this.branchCity = branchCity;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

}

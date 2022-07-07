package com.stg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "admintable")
public class BranchAdmin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;

	@NotEmpty(message = "Required firstName")
	@Column(name = "firstName", nullable = false)
	private String firstName;

	@NotEmpty(message = "Required lastName")
	@Column(name = "lastName", nullable = false)
	private String lastName;

	@NotEmpty(message = "Required gmail")
	@Column(name = "gmail", nullable = false, unique = true)
	private String gmail;

	@NotEmpty(message = "Required username")
	@Column(name = "username", nullable = false)
	private String userName;

	@NotEmpty(message = "Required password")
	@Column(name = "password", nullable = false)
	private String password;

	@NotEmpty(message = "Required Role")
	@Column(name = "role", nullable = false)
	private String role;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "branch_id", nullable = false)
//	@JsonBackReference
//	private Branch branchl;

	public BranchAdmin() {
		super();
	}

	public BranchAdmin(int adminId, String firstName, String lastName, String gmail, String userName, String password,
			String role) {
		super();
		this.adminId = adminId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gmail = gmail;
		this.userName = userName;
		this.password = password;
		this.role = role;
	
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

//	public Branch getBranchl() {
//		return branchl;
//	}
//
//	public void setBranchl(Branch branchl) {
//		this.branchl = branchl;
//	}

}

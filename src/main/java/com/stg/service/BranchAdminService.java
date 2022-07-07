package com.stg.service;

import com.stg.entity.BranchAdmin;

public interface BranchAdminService {

	public BranchAdmin createBranchAdmin(BranchAdmin branchAdmin);
	
	public BranchAdmin checkBranchAdmin(String userName, String password);
	
	public String changePassword(String username, String oldpassword, String newpassword);
}

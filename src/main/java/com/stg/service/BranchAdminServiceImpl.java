
package com.stg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stg.entity.BranchAdmin;
import com.stg.exception.CustomException;
import com.stg.repository.BranchAdminRepository;

@Service
public class BranchAdminServiceImpl implements BranchAdminService {

	@Autowired
	private BranchAdminRepository branchAdminRepository;

	@Override
	public BranchAdmin createBranchAdmin(BranchAdmin branchAdmin) {
		BranchAdmin ret = null;
		if (branchAdminRepository.count() > 0) {
			BranchAdmin tempBranch = branchAdminRepository.findByUserName(branchAdmin.getUserName());
			if (tempBranch == null) {
				if(branchAdmin.getPassword().length()<8) {
					throw new CustomException("Password should be 8 character or above!");
				}else {
					ret = branchAdminRepository.save(branchAdmin);
				}				
			} else {
				if (branchAdmin.getUserName().equals(tempBranch.getUserName())) {
					throw new CustomException("Choose Another Username");

				} else if (branchAdmin.getGmail().equals(tempBranch.getGmail())) {
					throw new CustomException("Already Registered Mail");
				} 
			}
		}
		return ret;

	}

	@Override
	public BranchAdmin checkBranchAdmin(String userName, String password) {

		BranchAdmin admin = null;

		if (branchAdminRepository.count() > 0) {
			admin = branchAdminRepository.findByUserName(userName);
			if (admin != null) {
				if (admin.getPassword().equals(password)) {
					return admin;
				} else {
					throw new CustomException("Incorrect Password!");
				}
			} else {
				throw new CustomException("Username Does Not Exist!");
			}
		} else {
			throw new CustomException("Admins Does Not Exist!");
		}

	}

	public String changePassword(String username, String oldpassword, String newpassword) {

		String msg = null;
		BranchAdmin tempAdmin = branchAdminRepository.findByUserName(username);

		if (tempAdmin != null) {
			if (tempAdmin.getPassword().equals(oldpassword)) {
				if (newpassword.length() >= 8) {
					tempAdmin.setPassword(newpassword);
					branchAdminRepository.save(tempAdmin);
					msg = "Password Changed!";
					// tempUser.getAccount();
				} else {
					throw new CustomException("Password should be 8 character or above!");
				}
			} else {
				throw new CustomException("Wrong Password!");
			}
		} else {
			throw new CustomException("User Not Found!");
		}
		return msg;
	}

}

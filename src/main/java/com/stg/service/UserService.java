package com.stg.service;

import com.stg.entity.Account;
import com.stg.entity.User;

public interface UserService {

	public abstract String generateUserNameAndPassword(User accNo);
	
	public abstract Account login(String username,String password);
	
	public abstract String changePassword(String username, String oldpassword,String newpassword);
}

package com.stg.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stg.entity.Account;
import com.stg.entity.User;
import com.stg.exception.CustomException;
import com.stg.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public String generateUserNameAndPassword(User user) {
		if(userRepository.existsById(user.getAccount().getAccNo())) {
			throw new CustomException("Already credentials Generated!");
		}else {			
			if (user.getAccount().isAccStatus()) {
				String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghi" + "jklmnopqrstuvwxyz";
				Random rnd = new Random();
				StringBuilder sb = new StringBuilder(8);
				for (int i = 0; i < 8; i++) {
					sb.append(chars.charAt(rnd.nextInt(chars.length())));
				}

				String chars1 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghi" + "jklmnopqrstuvwxyz!@$";
				Random rnd1 = new Random();
				StringBuilder sbs = new StringBuilder(10);
				for (int i = 0; i < 10; i++) {
					sbs.append(chars1.charAt(rnd1.nextInt(chars1.length())));
				}
				user.setUserName(sb.toString());
				user.setPassword(sbs.toString());
				user.setUserId(user.getAccount().getAccNo());
				userRepository.save(user);
				return "User Credentials Generated!";
			} else {
				throw new CustomException("Activate Your Account!");
			}
		}		

		
	}

	@Override
	public Account login(String username, String password) {

		System.out.println(username+"///////////////////");
		if(username!=null && password != null) {
			User tempUser = userRepository.findByUserName(username);

			Account tempAcc = tempUser.getAccount();
			System.out.println("send Password"+password);
			System.out.println("database password"+tempUser.getPassword());
			if (password.equals(tempUser.getPassword())) {

				return tempAcc;
			} else {
				throw new CustomException("Wrong Password!");
			}
		}else {
			throw new CustomException("Required Input");
		}
		

	}

	@Override
	public String changePassword(String username, String oldpassword, String newpassword) {

		String msg = null;
		User tempUser = userRepository.findByUserName(username);

		if (tempUser != null) {
			if (tempUser.getPassword().equals(oldpassword)) {
				if (newpassword.length() >= 8) {
					tempUser.setPassword(newpassword);
					userRepository.save(tempUser);
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

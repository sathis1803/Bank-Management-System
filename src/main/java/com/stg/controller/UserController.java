package com.stg.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stg.entity.Account;
import com.stg.entity.Constants;
import com.stg.entity.User;
import com.stg.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "create")
	public String create(@RequestBody User acc) {
		
		return userService.generateUserNameAndPassword(acc);
	}
	
	@GetMapping(value = "login/{username}/{password}")
	public ResponseEntity<Account> loginn(@PathVariable String username,@PathVariable String password) {
		
		return new ResponseEntity<Account>(userService.login(username, password), HttpStatus.FOUND) ;
	}
	
	@GetMapping(value = "login")
	public Account login(@RequestParam String username,@RequestParam String password) {
		
		return userService.login(username, password);
	}
	
	@PostMapping(value = "change/{username}/{oldpassword}/{newpassword}")
	public String changePassword(@PathVariable String username,@PathVariable String oldpassword,@PathVariable String newpassword) {
		
		return userService.changePassword(username, oldpassword, newpassword);
	}
	
	@PostMapping(value = "/authenticate")
	public Map<String, String> generateToken(@RequestBody User user){
		Map<String, String> map = new HashMap<>();
//		Account tempUser = userService.login(user.getUserName(), user.getPassword());
		
//		if(tempUser!=null) {
			long timestamp=System.currentTimeMillis();
			
			String token=Jwts.builder().signWith(SignatureAlgorithm.HS256,Constants.API_SECRET_KEY)
				.setIssuedAt(new Date(timestamp))
				.setExpiration(new Date(timestamp+Constants.TOKEN_VALIDITY))
				.claim("userName", user.getUserName())
				.claim("password", user.getPassword())
//				.claim("accHolderName", tempUser.getAccHolderName())

				.compact();
			
			
			map.put("JWT", token);
		
//		}
		return map;
	}
}

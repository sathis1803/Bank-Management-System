package com.stg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stg.entity.Account;
import com.stg.service.AccountService;


@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4300"})
@RequestMapping(value = "account")
public class AccountController {

	@Autowired
	private AccountService accountService; 

	@PostMapping(value = "accounts")
	public Account createAccount(@RequestBody Account account) {
		// accountService.addAccount(account);
		return accountService.addAccount(account);
	}

	@GetMapping(value = "accounts")
	public List<Account> getAll() {

		return accountService.getAllAccount();
	}

	@GetMapping(value = "accounts/{accNo}")
	public Account getAccById(@PathVariable int accNo) {
		return accountService.getAccById(accNo);
	}

	@GetMapping(value = "getAccount/{panNo}/{mobileNo}")
	public ResponseEntity<Account> getAccount(@PathVariable String panNo, @PathVariable String mobileNo) {
		// accountService.getAccount(panNo, mobileNo)
		return new ResponseEntity<Account>(accountService.getAccount(panNo, mobileNo), HttpStatus.FOUND);
	}

	@PostMapping(value = "send/{panNo}/{mobileNo}/{amount}/{recipientaccNo}")
	public String getMessage(@PathVariable String panNo, @PathVariable String mobileNo, @PathVariable double amount,
			@PathVariable int recipientaccNo) {
		return accountService.getMessage(panNo, mobileNo, amount, recipientaccNo);
	}

	@GetMapping(value = "countAcc")
	public long countAccount() {
		return accountService.countSavingsAcc();
	}

	@PutMapping(value = "updatemobile/{accNo}/{mobileNo}")
	public Account updateMobile(@PathVariable int accNo, @PathVariable String mobileNo) {
		return accountService.updateMobile(accNo, mobileNo);
	}

	@PutMapping(value = "updatestatus/{accNo}/{accStatus}")
	public Account updateStatus(@PathVariable int accNo, @PathVariable boolean accStatus) {

		return accountService.updateStatus(accNo, accStatus);
	}

	@PutMapping(value = "deposit/{accNo}/{depositamount}")
	public Account updateBalance(@PathVariable int accNo, @PathVariable double depositamount) {
		return accountService.deposit(accNo, depositamount);
	}

	@PutMapping(value = "withdraw/{accNo}/{withdrawamount}")
	public Account withdraw(@PathVariable int accNo, @PathVariable double withdrawamount) {
		return accountService.withdraw(accNo, withdrawamount);
	}

	@DeleteMapping(value = "accounts/{accNo}")
	public int deleteAccount(@PathVariable int accNo) {
		return accountService.deleteAccById(accNo);
	}
	
	@PostMapping(value = "deposited/{accNo}/{depositamount}")
	public String deposited(@RequestBody Account account,@PathVariable int accNo,@PathVariable int depositamount) {		
		return accountService.deposited(account, accNo, depositamount);
	}
	
	

//	@PostMapping(value = "/sendSMS")
//     public ResponseEntity<String> sendSMS() {
//		 final String AUTH_TOKEN ="2e0924d72cafb9d9b59dbdcb8f97137f";
//		 String SID = "ACee89b2b62806a0cbbba231b88c15b05e";
//
//		Twilio.init(SID,AUTH_TOKEN);
//
//	Message.creator(new PhoneNumber("+917708951273"),
//	new PhoneNumber("+918667039869"), "Hello from Sathis 📞").create();
//	 return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
//	
//	}
         

}

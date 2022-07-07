package com.stg.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stg.entity.Account;
import com.stg.entity.Transaction;
import com.stg.exception.CustomException;
import com.stg.repository.AccountRepository;
import com.stg.repository.TransactionRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public Account addAccount(Account account) {
		if (accountRepository.count() > 0) {
			if (accountRepository.existsByPanNo(account.getPanNo())) {
				throw new CustomException("Duplicate Account found");
			} else if (accountRepository.existsById(account.getAccNo())) {
				throw new CustomException("Account Already Exist!");
			} else {
				account.setAccOpenDate(LocalDate.now());
				accountRepository.save(account);
			}
		} else {
			account.setAccOpenDate(LocalDate.now());
			accountRepository.save(account);
		}

		return account;
	}

	@Override
	public Account getAccById(int accNo) {
		Optional<Account> optional = accountRepository.findById(accNo);
		Account tempAccount = null;
		if (optional.isPresent()) {
			tempAccount = optional.get();
			tempAccount.setUser(null);
		} else {
			throw new CustomException("Account No Does not Exist!");
		}
		return tempAccount;
	}

	@Override
	public int deleteAccById(int accNo) {
		if (accountRepository.existsById(accNo)) {
			accountRepository.deleteById(accNo);

		} else {
			throw new CustomException("Account Does not Exist!");
		}

		return accNo;
	}

	@Override
	public long countSavingsAcc() {
		return accountRepository.count();
	}

	@Override
	public Account updateMobile(int accNo, String mobileno) {
		Optional<Account> optional = accountRepository.findById(accNo);
		Account tempAcc = optional.get();
		if (tempAcc != null) {
			tempAcc.setMobileNo(mobileno);
			accountRepository.save(tempAcc);
		} else {
			throw new CustomException("Account Does Not Exist!");
		}

		return tempAcc;
	}

	@Override
	public Account updateStatus(int accNo, boolean accStatus) {

		Optional<Account> optional = accountRepository.findById(accNo);
		Account account = optional.get();
		if (account.isAccStatus() != true) {
			account.setAccStatus(true);
			accountRepository.save(account);
		} else {

			throw new CustomException("Already in Active ");
		}
		return account;
	}

	@Override
	public Account getAccount(String panNo, String mobileNo) {
		Account tempAccount = accountRepository.findByPanNo(panNo);
		if (tempAccount != null) {
			if (tempAccount.getPanNo().equals(panNo)) {
				if (mobileNo.equals(tempAccount.getMobileNo())) {
					Optional<Account> optional = accountRepository.findById(tempAccount.getAccNo());
					tempAccount = optional.get();
				} else {
					throw new CustomException("Mobile No Wrong!");
				}
			} else {
				throw new CustomException("Pan No Wrong!");
			}
		} else {
			throw new CustomException("Account Does not Exist!");
		}
		return tempAccount;
	}

	@Override
	public String getMessage(String panNo, String mobileNo, double amount, int accNo) {
		Account tempAcc = accountRepository.findByPanNoAndMobileNo(panNo, mobileNo);
		String tempString = null;
		if (tempAcc != null) {
			if (accountRepository.existsById(accNo)) {
				if (tempAcc.getBalance() > amount) {
					tempAcc.setBalance(tempAcc.getBalance() - amount);
					accountRepository.save(tempAcc);
					Optional<Account> optional = accountRepository.findById(accNo);
					Account depositAcc = optional.get();
					depositAcc.setBalance(amount + depositAcc.getBalance());
					accountRepository.save(depositAcc);
					

				} else {
					throw new CustomException("You have Low Balance!");
				}
			} else {
				throw new CustomException("Account Does not Exist!");
			}
		}

		return tempString;
	}

	@Override
	public Account deposit(int accNo, double depositamount) {
		Account tempAccount = null;		
		if(depositamount>0) {
			if (accountRepository.existsById(accNo)) {
				Optional<Account> optional = accountRepository.findById(accNo);
				tempAccount = optional.get();
				if (tempAccount.isAccStatus()) {
					tempAccount.setBalance(tempAccount.getBalance() + depositamount);
					accountRepository.save(tempAccount);
					Transaction transaction = new Transaction();
					transaction.setLocalDateTime(LocalDateTime.now());
					transaction.setTransAmount(depositamount);
					transaction.setTransType(true);
					transaction.setAccount(tempAccount);
					transactionRepository.save(transaction);					
				} else {
					throw new CustomException("Activate Your Account");
				}
			} else {
				throw new CustomException("Account Not Found!");
			}
		}else {
			throw new CustomException("Wrong Input!");
		}
		
		return tempAccount;
	}

	@Override
	public Account withdraw(int accNo, double withdrawamount) {
		Account tempAccount = null;
		if(withdrawamount>0) {
			if (accountRepository.existsById(accNo)) {
				Optional<Account> optional = accountRepository.findById(accNo);
				tempAccount = optional.get();
				if (tempAccount.isAccStatus()) {
					if (tempAccount.getBalance() > 0 && tempAccount.getBalance() >= withdrawamount) {
						tempAccount.setBalance(tempAccount.getBalance() - withdrawamount);
						accountRepository.save(tempAccount);
						Transaction transaction = new Transaction();
						transaction.setLocalDateTime(LocalDateTime.now());
						transaction.setTransAmount(withdrawamount);
						transaction.setTransType(false);
						transaction.setAccount(tempAccount);
						transactionRepository.save(transaction);
						

					} else {
						throw new CustomException("Insufficient Balance");
					}

				} else {
					throw new CustomException("Activate Your Account");
				}
			} else {
				throw new CustomException("Account Not Found!");
			}
		}else {
			throw new CustomException("Wrong Input!");
		}
		
		return tempAccount;
	}

	@Override
	public List<Account> getAllAccount() {
		
		List<Account> tempList = accountRepository.getAllFromAccounts();
//		return accountRepository.findAll();
		return tempList;
	}

	@Override
	public String deposited(Account account, int accNo, int depositamount) {

		String msg = null;
		Account tempSenderAcc ;
		Account tempRecieverAcc;
		System.out.println("///////////"+account.getAccNo());
		if (accountRepository.existsById(account.getAccNo())) {
		 tempSenderAcc = accountRepository.getById(account.getAccNo());		
			if (tempSenderAcc.isAccStatus()) {
				if (tempSenderAcc.getBalance() > 0 && tempSenderAcc.getBalance() >= depositamount) {
					if (accountRepository.existsById(accNo)) {
						tempRecieverAcc = accountRepository.getById(accNo);
						depositSend(tempRecieverAcc.getAccNo(), depositamount);
						//deposit(tempRecieverAcc.getAccNo(),depositamount);								
						withdraw(tempSenderAcc.getAccNo(),depositamount);									
						msg = "Rs." + depositamount + " Payment Successful";
					} else {
						throw new CustomException("Receiver Account Not Found!");
					}
				} else {
					throw new CustomException("Insufficient Balance");
				}
			} else {
				throw new CustomException("Activate Your Account");
			}
		} else {
			throw new CustomException("Check Your Account!");
		}

		return msg;
	}

	@Override
	public Account depositSend(int accNo, double depositamount) {
		Account tempAccount = null;		
		if(depositamount>0) {
			if (accountRepository.existsById(accNo)) {
				Optional<Account> optional = accountRepository.findById(accNo);
				tempAccount = optional.get();				
					tempAccount.setBalance(tempAccount.getBalance() + depositamount);
					accountRepository.save(tempAccount);
					Transaction transaction = new Transaction();
					transaction.setLocalDateTime(LocalDateTime.now());
					transaction.setTransAmount(depositamount);
					transaction.setTransType(true);
					transaction.setAccount(tempAccount);
					transactionRepository.save(transaction);					
				
			} else {
				throw new CustomException("Account Not Found!");
			}
		}else {
			throw new CustomException("Wrong Input!");
		}
		return tempAccount;
	}

}
	
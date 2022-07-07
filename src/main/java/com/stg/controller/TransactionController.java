package com.stg.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stg.entity.Transaction;
import com.stg.entity.TransactionDao;
import com.stg.service.TransactionService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "transaction")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	
	@GetMapping(value = "getbyaccno")
	public List<Transaction> allTransactionByAccNo(int accNo){
		return transactionService.allTransactionByAccNo(accNo);
	}
	
	@GetMapping(value = "getCount")
	public long countAllTransaction(){		
		return transactionService.getCountAllTransaction();
	}
	
	@GetMapping(value = "getall")
	public List<Transaction> allTransactionByAccNo(){	
		
		return transactionService.allTransaction();
	}
	
	@GetMapping(value = "getallwithaccno")
	public List<TransactionDao> allTransactionWithAccNo(){
		List<Transaction> list =transactionService.allTransaction();
		List<TransactionDao> dao = new ArrayList<>();
		for (Transaction trans : list) {
			TransactionDao transactionDao = new TransactionDao();
			transactionDao.setAccNo(trans.getAccount().getAccNo());
			transactionDao.setLocalDateTime(trans.getLocalDateTime());
			transactionDao.setTransAmount(trans.getTransAmount());
			transactionDao.setTransId(trans.getTransId());
			transactionDao.setTransType(trans.isTransType());
			
			dao.add(transactionDao);
		}
		return dao;
	}
	
}

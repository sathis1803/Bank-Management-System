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

import com.stg.entity.Branch;
import com.stg.service.BranchService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "branch")
public class BranchController {

	@Autowired
	private BranchService branchService;

	@PostMapping(value = "branches")
	public ResponseEntity<Branch> createBranch(@RequestBody Branch branch) {
		return new ResponseEntity<Branch>(branchService.addBranch(branch), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "branches/count")
	public long getBranchCount() {
		return branchService.getBranchCount();
	}

	@GetMapping(value = "branches")
	public List<Branch> getAll() {
		return branchService.getAll();
	}

	@GetMapping(value = "branches/{branchid}")
	public Branch getById(@PathVariable int branchid) {

		return branchService.getOneBranchById(branchid);
	}
	
	@PutMapping(value = "update")
	public Branch updateBranch(@RequestBody Branch branch) {
		return branchService.updateBranch(branch);
	}
	
	@DeleteMapping(value="branches/{branchid}")
	public String deleteById(@PathVariable int branchid){
		
		return branchService.deleteById(branchid);
	}
}

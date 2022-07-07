package com.stg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.stg.entity.BranchAdmin;
import com.stg.service.BranchAdminService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(name = "branchadmin")
public class BranchAdminController {
	
	@Autowired
	private BranchAdminService branchAdminService;

	@PostMapping(value = "create")
	public BranchAdmin createBranchAdmin(@RequestBody BranchAdmin branchAdmin) {
		
		return branchAdminService.createBranchAdmin(branchAdmin);
	}
	
	@GetMapping(value = "check")
	public BranchAdmin checkAdmin(@RequestParam String username,@RequestParam String password) {
		//branchAdminService.checkBranchAdmin(username, password)
		return branchAdminService.checkBranchAdmin(username, password);
	}
	
	@PostMapping(value = "change/{username}/{oldpassword}/{newpassword}")
	public String changePassword(@PathVariable String username,@PathVariable String oldpassword,@PathVariable String newpassword) {
		
		return branchAdminService.changePassword(username, oldpassword, newpassword);
	}
	
	
}

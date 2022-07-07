package com.stg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stg.entity.Branch;
import com.stg.exception.CustomException;
import com.stg.repository.BranchRepository;

@Service
public class BranchServiceImpl implements BranchService {

	@Autowired
	private BranchRepository branchRepository;
	
	@Override
	public Branch addBranch(Branch branch) {
		if(branchRepository.existsByBranchName(branch.getBranchName())) {
			throw new CustomException("Branch Name Already Exist!");
		}else {
			return branchRepository.save(branch);
		}
		
	}

	@Override
	public List<Branch> getAll() {
		return branchRepository.findAll();
	}

	@Override
	public Branch getOneBranchById(int branchId) {
		Optional<Branch> optional = branchRepository.findById(branchId);
		Branch tempBranch = null;
		if (optional.isPresent()) {
			// success
			tempBranch = optional.get();
		} else {
			// failure
			throw new CustomException("Branch Does not Exist!");
		}
		return tempBranch;
	}

	@Override
	public Branch updateBranch(Branch branch) {
		if(branchRepository.existsById(branch.getBranchId())) {
			branchRepository.save(branch);
		}else {
			throw new CustomException("Branch Does not Exist!");
		}
		return branch;
	}

	@Override
	public String deleteById(int branchId) {
		String temp = null;
		if(branchRepository.existsById(branchId)) {
			branchRepository.deleteById(branchId);
			temp = "Successfully Deleted";
		}else {
			throw new CustomException("Branch ID Does not Exist!");
		}
		
		return temp;
	}

	@Override
	public long getBranchCount() {
		
		return branchRepository.count();
	}

}

package com.stg.service;

import java.util.List;

import com.stg.entity.Branch;

public interface BranchService {

	public abstract Branch addBranch(Branch branch);
	
	public abstract List<Branch> getAll();
	
	public abstract Branch getOneBranchById(int branchId);
	
	public abstract Branch updateBranch(Branch branch);
	
	public abstract String deleteById(int branchId);
	
	public abstract long getBranchCount();
}

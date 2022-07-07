package com.stg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stg.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch, Integer>{

	public abstract boolean existsByBranchName(String branchName);
}

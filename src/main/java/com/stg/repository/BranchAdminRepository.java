package com.stg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stg.entity.BranchAdmin;

public interface BranchAdminRepository extends JpaRepository<BranchAdmin, Integer> {

	public BranchAdmin findByUserName(String userName);
}

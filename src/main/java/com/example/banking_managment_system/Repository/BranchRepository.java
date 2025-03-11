package com.example.banking_managment_system.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.banking_managment_system.Models.Branch;

public interface BranchRepository extends JpaRepository<Branch,Integer> {
	
	
}

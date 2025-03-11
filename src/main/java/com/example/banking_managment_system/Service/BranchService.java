package com.example.banking_managment_system.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banking_managment_system.Models.Branch;
import com.example.banking_managment_system.Repository.BranchRepository;

@Service
public class BranchService {

	@Autowired
	private BranchRepository repository;
	
	 public List<Branch> findAll() {
	        return repository.findAll(); 
	    }
	
	public void save(Branch branch) {
		repository.save(branch);
	}
	
	public Branch findById(Integer id) {
        return repository.findById(id).orElse(null); 
    }
	
	public void delete(Integer id) {
       repository.deleteById(id);
    }
}

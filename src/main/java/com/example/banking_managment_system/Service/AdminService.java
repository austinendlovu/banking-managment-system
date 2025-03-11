package com.example.banking_managment_system.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banking_managment_system.Models.Admin;
import com.example.banking_managment_system.Repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository repository;
	
	public void save(Admin admin) {
		repository.save(admin);
	}
	
	public Optional<Admin> finduser(String email){
		return repository.findByEmail(email);
	}

	
	
}

package com.example.banking_managment_system.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.banking_managment_system.Models.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
	
	Optional<Admin> findByEmail(String email);
}

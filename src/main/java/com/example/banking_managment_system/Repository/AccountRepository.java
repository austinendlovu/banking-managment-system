package com.example.banking_managment_system.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.banking_managment_system.Models.Account;

public interface AccountRepository extends JpaRepository<Account,Long> {
	
	boolean existsBycardNo(String cardNo);
	
	boolean existsByCardNo(String cardNo);

    Account findByCardNo(String cardNo); 
	
    Account findByCustomerId(Long customerid);
    

}

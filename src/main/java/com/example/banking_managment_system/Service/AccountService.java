package com.example.banking_managment_system.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banking_managment_system.Models.Account;
import com.example.banking_managment_system.Models.Customer;
import com.example.banking_managment_system.Repository.AccountRepository;

import jakarta.validation.Valid;

@Service
public class AccountService {
	
	  @Autowired
	 private AccountRepository accountRepository;
	  
	  public Account createAccount(@Valid Account account) {
	        if (accountRepository.existsBycardNo(account.getCardNo())) {
	            throw new IllegalArgumentException("Card number already exists");
	        }
	        return accountRepository.save(account);
	    }
	  
	  public List<Account> findAll() {
	        return accountRepository.findAll();
	    }
	  
	  public void delete(Long id) {
	        accountRepository.deleteById(id); 
	    }

	public boolean existsByCardNo(String cardNo) {
		return accountRepository.existsByCardNo(cardNo);
	}
	public Account findByCardNo(String cardNo) {
        return accountRepository.findByCardNo(cardNo); 
    }
	
    public void updateAccount(Account account) {
        accountRepository.save(account); // Save changes to the database
    }

    public Account findByCustomerId(Long id) {
        return accountRepository.findByCustomerId(id);
    }
 
 



}

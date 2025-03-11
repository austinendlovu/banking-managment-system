package com.example.banking_managment_system.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banking_managment_system.Models.Customer;
import com.example.banking_managment_system.Models.Transaction;
import com.example.banking_managment_system.Repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {

	@Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }
    
	 public List<Transaction> findAll() {
	        return transactionRepository.findAll();
	    }

	 
}

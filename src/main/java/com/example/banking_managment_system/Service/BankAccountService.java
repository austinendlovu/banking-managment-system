package com.example.banking_managment_system.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banking_managment_system.Models.BankAccount;
import com.example.banking_managment_system.Repository.BankAccountRepository;

@Service
public class BankAccountService {
	
    private final BankAccountRepository bankAccountRepository;
    
    @Autowired
    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }
    public String withdraw(String accountNumber, double amount) {
        BankAccount account = bankAccountRepository.findByAccountNumber(accountNumber);
        
        if (account == null) {
            return "Account not found.";
        }

        if (amount <= 0) {
            return "Withdrawal amount must be greater than zero.";
        }

        if (account.getBalance() < amount) {
            return "Insufficient funds.";
        }

        account.setBalance(account.getBalance() - amount);
        bankAccountRepository.save(account);
        return "Withdrawal successful. New balance: " + account.getBalance();
    }
    

	
	
    
    
}

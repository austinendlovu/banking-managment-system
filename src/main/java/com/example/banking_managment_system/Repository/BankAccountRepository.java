package com.example.banking_managment_system.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.banking_managment_system.Models.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
	BankAccount findByAccountNumber(String accountNumber);
}

package com.example.banking_managment_system.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banking_managment_system.Models.Account;
import com.example.banking_managment_system.Models.Admin;
import com.example.banking_managment_system.Models.Customer;
import com.example.banking_managment_system.Repository.AccountRepository;
import com.example.banking_managment_system.Repository.CustomerRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountRepository accountRepository ;
	
	@Autowired
    private HttpSession httpSession;
	
	 public Customer save(Customer customer) {
	        return customerRepository.save(customer); 
	    }
	 
	 public List<Customer> findAll() {
	        return customerRepository.findAll();
	    }
	 
	 public Customer findById(Long id) {
	        return customerRepository.findById(id).orElse(null); 
	    }
	 
	 public void delete(Long id) {
	        customerRepository.deleteById(id); 
	    }
	 
	 public Optional<Customer> finduser(String email){
			return customerRepository.findByEmail(email);
		}
	 
	 public Account findByCustomerId(Long id) {
	        return accountRepository.findByCustomerId(id);
	    }


	 public Customer getLoggedInCustomer() {
	        return (Customer) httpSession.getAttribute("loggedInCustomer"); 
	    }



}

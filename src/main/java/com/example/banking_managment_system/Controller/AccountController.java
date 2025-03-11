package com.example.banking_managment_system.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.banking_managment_system.Models.Account;
import com.example.banking_managment_system.Models.Customer;
import com.example.banking_managment_system.Service.AccountService;
import com.example.banking_managment_system.Service.CustomerService;

import jakarta.persistence.Id;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/admin/addAccount")
	public String showAddAccountForm(Model model) {
	    List<Customer> customers = customerService.findAll(); 
	    model.addAttribute("customers", customers); 
	    model.addAttribute("account", new Account()); 
	    return "add-account"; 
	}

	@PostMapping("/admin/addAccount")
	public String addAccount(@ModelAttribute Account account, @RequestParam Long customerId, RedirectAttributes redirectAttributes) {
	    try {
	        Customer customer = customerService.findById(customerId); 
	        if (customer == null) {
	            throw new IllegalArgumentException("Customer not found.");
	        }

	        account.setCustomer(customer); 
	        accountService.createAccount(account); 

	        redirectAttributes.addFlashAttribute("successMessage", "Account added successfully!");
	    } catch (IllegalArgumentException e) {
	        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
	    }
	    return "redirect:/admin/addAccount"; 
	}
	 
	 @GetMapping("/admin/accounts")
	    public String listCustomers(Model model) {
	        List<Account> accounts = accountService.findAll(); 
	        model.addAttribute("accounts", accounts); 
	        return "total-accounts"; 
	    }
	
	

}

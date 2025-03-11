package com.example.banking_managment_system.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.banking_managment_system.Models.Branch;
import com.example.banking_managment_system.Models.Customer;
import com.example.banking_managment_system.Service.BranchService;
import com.example.banking_managment_system.Service.CustomerService;

@Controller
public class CustomerController {
	
	  @Autowired
	    private CustomerService customerService; 

	    @Autowired
	    private BranchService branchService; 

	    @GetMapping("/admin/addUser")
	    public String showCustomerRegistrationForm(Model model) {
	        List<Branch> branches = branchService.findAll();
	        model.addAttribute("branches", branches); 
	        model.addAttribute("customer", new Customer()); 
	        return "add-user";
	    }

	    @PostMapping("/admin/registerCustomer")
	    public String registerCustomer(@ModelAttribute("customer") Customer customer, RedirectAttributes redirectAttributes) {
	        customerService.save(customer);
	        redirectAttributes.addFlashAttribute("message", "Customer registered successfully!");
	        return "redirect:/admin/home";
	    }
	    
	    @GetMapping("/admin/customers")
	    public String listCustomers(Model model) {
	        List<Customer> customers = customerService.findAll(); 
	        model.addAttribute("customers", customers); 
	        return "total-customers"; 
	    }
}

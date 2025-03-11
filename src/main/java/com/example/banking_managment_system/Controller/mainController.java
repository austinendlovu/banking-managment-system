package com.example.banking_managment_system.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.banking_managment_system.Models.Admin;
import com.example.banking_managment_system.Models.Customer;
import com.example.banking_managment_system.Service.AdminService;
import com.example.banking_managment_system.Service.CustomerService;

import jakarta.servlet.http.HttpSession;

@Controller
public class mainController {

	 @Autowired
	    private AdminService adminService;
	 @Autowired
	    private CustomerService customerService;

	    @GetMapping("/")
	    public String home() {
	        return "index";
	    }

	    @GetMapping("/admin/index")
	    public String returnHome() {
	        return "index";
	    }
	   
	   
	    
	    
	    @GetMapping("/admin/register")
	    public String registration() {
	        return "admin-registration";
	    }

	    @PostMapping("/admin/register")
	    public String register(@ModelAttribute("admin") Admin admin, RedirectAttributes redirectAttributes) {
	        adminService.save(admin);
	        redirectAttributes.addFlashAttribute("message", "Registration Successful! Please login to continue.");
	        return "redirect:/admin/login"; 
	    }

	    @GetMapping("/admin/login")
	    public String showAdminLoginPage(Model model) {
	        return "admin-login"; 
	    }

	    @PostMapping("/admin/login")
	    public String handleLogin(@RequestParam String email, @RequestParam String password, 
	                              RedirectAttributes redirectAttributes, HttpSession session, Model model) {
	        Optional<Admin> adminOpt = adminService.finduser(email);

	        if (adminOpt.isPresent()) {
	            Admin admin = adminOpt.get();
	            if (admin.getPassword().equals(password)) {
	                session.setAttribute("admin", admin); 
	                redirectAttributes.addFlashAttribute("successMessage", "Successfully logged in!");
	                return "redirect:/admin/home"; 
	            }
	        }

	        model.addAttribute("error", "Invalid email or password !!");
	        return "admin-login"; 
	    }

	    @GetMapping("/admin/home")
	    public String adminHome(Model model, HttpSession session) {
	        Admin admin = (Admin) session.getAttribute("admin"); 
	        if (admin != null) {
	            model.addAttribute("admin", admin);
	            return "admin-home"; 
	        }
	        return "redirect:/admin/login"; 
	    }
	    
	    @GetMapping("customer/login")
	    public String customerLogin(Model model) {
	    	
	    	return "customer-login";
	    }
	    
	    @GetMapping("/customer/home")
	    public String customerHome(Model model, HttpSession session) {
	        Customer customer = (Customer) session.getAttribute("customer");
	        if (customer != null) {
	            String firstName = customer.getFirstName(); 
	            model.addAttribute("customerFirstName", firstName);
	            return "customer-home"; 
	        }
	        return "redirect:/customer/login"; 
	    }

	    @PostMapping("/customer/login")
	    public String customerLogin(@RequestParam String email, @RequestParam String password, 
	                                RedirectAttributes redirectAttributes, HttpSession session, Model model) {
	        Optional<Customer> customerOpt = customerService.finduser(email);
	        
	        if (customerOpt.isPresent()) {
	            Customer customer = customerOpt.get();
	            if (customer.getPassword().equals(password)) {
	                session.setAttribute("customer", customer);  
	                redirectAttributes.addFlashAttribute("successMessage", "Successfully logged in!");
	                return "redirect:/customer/home"; 
	            }
	        }
	        
	        model.addAttribute("error", "Customer is not registered in the system !!");
	        return "customer-login"; 
	    }
}
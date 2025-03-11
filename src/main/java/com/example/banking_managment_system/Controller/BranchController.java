package com.example.banking_managment_system.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.banking_managment_system.Models.Branch;
import com.example.banking_managment_system.Repository.BranchRepository;

@Controller
public class BranchController {
	
	@Autowired
	BranchRepository branchRepository;
	
	@GetMapping("/admin/addbranch")
    public String showAddBranchForm(Model model) {
        model.addAttribute("branch", new Branch());
        return "add-branch"; 
    }

    @PostMapping("/admin/addbranch")
    public String addBranch(@ModelAttribute("branch") Branch branch, RedirectAttributes redirectAttributes) {
    	branchRepository.save(branch);
        redirectAttributes.addFlashAttribute("message", "Branch added successfully!");
        return "redirect:/admin/home"; 
    }
}
 
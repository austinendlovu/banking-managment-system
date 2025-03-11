package com.example.banking_managment_system.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.banking_managment_system.Models.Account;
import com.example.banking_managment_system.Models.Customer;
import com.example.banking_managment_system.Models.Transaction;
import com.example.banking_managment_system.Service.AccountService;
import com.example.banking_managment_system.Service.BankAccountService;
import com.example.banking_managment_system.Service.CustomerService;
import com.example.banking_managment_system.Service.TransactionService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BankAcccountController {
	
	private final BankAccountService bankAccountService;

    @Autowired
    private AccountService accountService; 
    
    @Autowired
    private TransactionService transactionService;
    
    @Autowired
    private HttpSession httpSession;
    
    @Autowired
    private CustomerService customerService;

    @Autowired
    public BankAcccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/deposit") 
    public String getDepositForm(Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/customer/login";
        }

        Account account = accountService.findByCustomerId(customer.getId());
        if (account == null) {
            model.addAttribute("message", "Account does not exist.");
            return "deposit-amount"; 
        }

        model.addAttribute("account", account); 
        return "deposit-amount"; 
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam double amount, 
                          @RequestParam String atmPin, 
                          RedirectAttributes redirectAttributes, 
                          HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            redirectAttributes.addFlashAttribute("message", "You must be logged in to perform this action.");
            return "redirect:/customer/login"; 
        }

        Account account = accountService.findByCustomerId(customer.getId());
        if (account == null) {
            redirectAttributes.addFlashAttribute("message", "Account does not exist.");
            return "redirect:/deposit"; 
        }

        if (!account.verifyPin(atmPin)) {
            redirectAttributes.addFlashAttribute("message", "Invalid PIN. Please try again.");
            return "redirect:/withdraw"; 
        }

        account.setBalance(account.getBalance() + amount);
        accountService.updateAccount(account); 

        redirectAttributes.addFlashAttribute("message", "Amount Deposited successfully! New balance: " + account.getBalance());
        return "redirect:/customer/home"; 
    }
    
    @GetMapping("/withdraw")
    public String getWithdrawalForm(Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/customer/login";
        }

        Account account = accountService.findByCustomerId(customer.getId());
        if (account == null) {
            model.addAttribute("message", "Account does not exist.");
            return "withdraw-amount";
        }

        model.addAttribute("account", account);
        return "withdraw-amount";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam double amount, 
                          @RequestParam String atmPin, 
                          RedirectAttributes redirectAttributes, 
                          HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            redirectAttributes.addFlashAttribute("message", "You must be logged in to perform this action.");
            return "redirect:/customer/login"; 
        }

        Account account = accountService.findByCustomerId(customer.getId());
        if (account == null) {
            redirectAttributes.addFlashAttribute("message", "Account does not exist.");
            return "redirect:/withdraw"; 
        }

        if (!account.verifyPin(atmPin)) {
            redirectAttributes.addFlashAttribute("message", "Invalid PIN. Please try again.");
            return "redirect:/withdraw"; 
        }

        account.setBalance(account.getBalance() - amount);
        accountService.updateAccount(account); 

        redirectAttributes.addFlashAttribute("message", "Withdrawal successful! New balance: " + account.getBalance());
        return "redirect:/customer/home"; 
    }
    @GetMapping("/balance")
    public String balance(HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/customer/login";
        }

        Account account = accountService.findByCustomerId(customer.getId());
        if (account != null) {
            model.addAttribute("account", account);
            model.addAttribute("message", "Account Number: " + account.getCardNo()  + 
            		" "+
            		", Balance: " + account.getBalance());
        } else {
            model.addAttribute("message", "Account does not exist.");
        }

        return "balance"; 
    }
    @GetMapping("/transfer")
    public String transferPage(HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/customer/login"; 
        }

        Account account = accountService.findByCustomerId(customer.getId());
        if (account == null) {
            model.addAttribute("error", "Account not found.");
            return "transfer"; 
        }

        model.addAttribute("account", account); 
        return "transfer"; 
    }

    @PostMapping("/transfer")
    public String transferMoney(HttpSession session, String recipientCardNo, double amount, String atmPin, RedirectAttributes redirectAttributes) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/customer/login"; 
        }

        Account senderAccount = accountService.findByCustomerId(customer.getId());
        Account recipientAccount = accountService.findByCardNo(recipientCardNo);
        
        if (recipientAccount == null) {
            redirectAttributes.addFlashAttribute("error", "Recipient account does not exist.");
            return "redirect:/transfer"; 
        }

        if (recipientAccount.getId().equals(senderAccount.getId())) {
            redirectAttributes.addFlashAttribute("error", "You cannot transfer money to your own account.");
            return "redirect:/transfer"; 
        }

        if (amount <= 0) {
            redirectAttributes.addFlashAttribute("error", "Amount must be greater than zero.");
            return "redirect:/transfer"; 
        }

        if (senderAccount.getBalance() < amount) {
            redirectAttributes.addFlashAttribute("error", "Insufficient balance.");
            return "redirect:/transfer"; 
        }

        if (!atmPin.equals(senderAccount.getAtmPin())) { 
            redirectAttributes.addFlashAttribute("error", "Incorrect ATM PIN.");
            return "redirect:/transfer"; 
        }

       
        senderAccount.setBalance(senderAccount.getBalance() - amount);
        recipientAccount.setBalance(recipientAccount.getBalance() + amount);
        
        accountService.updateAccount(senderAccount);
        accountService.updateAccount(recipientAccount);

        Transaction transaction = new Transaction(senderAccount.getId(), recipientAccount.getId(), amount);
        transactionService.saveTransaction(transaction);

        
        redirectAttributes.addFlashAttribute("message", "Transfer successful! Your remaining balance is: " + senderAccount.getBalance());
        
        return "redirect:/customer/home"; 
    }
    
    @GetMapping("/transactionHistory")
    public String listTransaction(Model model) {
        List<Transaction> transactions = transactionService.findAll(); 
        model.addAttribute("transactions", transactions); 
        return "transaction-history"; 
    }
    @GetMapping("/accountDetails") 
    public String getAccountDetails(Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/customer/login"; 
        }

        Account account = accountService.findByCustomerId(customer.getId());
        if (account == null) {
            model.addAttribute("message", "Account does not exist."); 
        } else {
            model.addAttribute("customer", customer);
            model.addAttribute("account", account);
        }
        
        return "account-details"; 
    }
    @GetMapping("/admin/transactions")
    public String Transactions(Model model) {
        List<Transaction> transactions = transactionService.findAll(); 
        model.addAttribute("transactions", transactions); 
        return "total-transactions"; 
    }

}

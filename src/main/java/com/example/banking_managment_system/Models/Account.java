package com.example.banking_managment_system.Models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @NotNull
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false) 
    private Customer customer; 

    @NotNull
    @Column(unique = true)
    @Size(min = 16, max = 16)
    private String cardNo;

    @NotNull
    private String accountType;

    @NotNull
    private Double balance;

    @NotNull
    private String accountStatus;

    @NotNull
    @Size(min = 4, max = 4)
    private String atmPin;

    // Default constructor
    public Account() {
        super();
    }

    // Constructor with customer reference
    public Account(Customer customer, @NotNull @Size(min = 16, max = 16) String cardNo,
                   @NotNull String accountType, @NotNull Double balance,
                   @NotNull @Size(min = 4, max = 4) String atmPin, @NotNull String accountStatus) {
        this.customer = customer; 
        this.cardNo = cardNo;
        this.accountType = accountType;
        this.balance = balance;
        this.atmPin = atmPin;
        this.accountStatus = accountStatus;
    }


    public Long getId() {
        return id; 
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer; // Get the associated customer
    }

    public void setCustomer(Customer customer) {
        this.customer = customer; // Set the associated customer
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getAtmPin() {
        return atmPin;
    }

    
    public void setAtmPin(String atmPin) {
        this.atmPin = atmPin; 
    }

  
    public boolean verifyPin(String enteredPin) {
        return enteredPin.equals(this.atmPin);
    }
  
}


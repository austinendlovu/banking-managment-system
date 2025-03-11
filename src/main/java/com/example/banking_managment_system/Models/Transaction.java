package com.example.banking_managment_system.Models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "transactions")
public class Transaction {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long senderAccountId;
    private Long recipientAccountId;
    private double amount;

    @Column(name = "transaction_date")
    private LocalDateTime timestamp;

    @Version
    private Long version;

   
    public Transaction() {
        this.timestamp = LocalDateTime.now(); 
    }

   
    public Transaction(Long senderAccountId, Long recipientAccountId, double amount) {
        this.senderAccountId = senderAccountId;
        this.recipientAccountId = recipientAccountId;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(Long senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public Long getRecipientAccountId() {
        return recipientAccountId;
    }

    public void setRecipientAccountId(Long recipientAccountId) {
        this.recipientAccountId = recipientAccountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}

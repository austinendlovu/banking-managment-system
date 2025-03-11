package com.example.banking_managment_system.Models;

import java.util.Optional;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="customers")
public class Customer {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String firstName;
	    private String lastName;
	    private String dob; 
	    private String mobile;
	    private String email;
	    private String gender;
	    private String password;
	    private String presentAddress;
	    
	    @ManyToOne 
	    @JoinColumn(name = "branch_id", nullable = false)
	    private Branch branch;

	    // Constructors
	    public Customer() {}

		public Customer(Long id, String firstName, String lastName, String dob, String mobile, String email,
				String gender, String password, String presentAddress, Branch branch) {
			super();
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.dob = dob;
			this.mobile = mobile;
			this.email = email;
			this.gender = gender;
			this.password = password;
			this.presentAddress = presentAddress;
			this.branch = branch;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getDob() {
			return dob;
		}

		public void setDob(String dob) {
			this.dob = dob;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getPresentAddress() {
			return presentAddress;
		}

		public void setPresentAddress(String presentAddress) {
			this.presentAddress = presentAddress;
		}

		public Branch getBranch() {
			return branch;
		}

		public void setBranch(Branch branch) {
			this.branch = branch;
		}



		

}

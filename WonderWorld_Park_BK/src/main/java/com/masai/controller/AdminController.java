
package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.AdminException;
import com.masai.Exception.CustomerException;
import com.masai.model.Admin;
import com.masai.model.Customer;
import com.masai.service.AdminService;
import com.masai.service.CustomerService;



import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/admin")


public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CustomerService customerService;
	

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@PostMapping("/registerAdmin")
	public ResponseEntity<Admin> createAdmin(@Valid @RequestBody Admin admin) throws AdminException{
		admin.setIsDeleted(false);
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		Admin ad = adminService.insertAdmin(admin);
		return new ResponseEntity<Admin>(ad, HttpStatus.CREATED);
	}
	
	@GetMapping("/signin")
	public ResponseEntity<String> logInUserHandler(Authentication auth) throws AdminException {
		Admin admin = adminService.findByEmail(auth.getName()).get();
		return new ResponseEntity<>(admin.getEmail() + " Logged In Successfully", HttpStatus.ACCEPTED);
	}	
	
	
	@DeleteMapping("/delete/{adminId}")
	public ResponseEntity<Admin> deleteAdmin(@PathVariable Integer adminId)throws AdminException{
		return new ResponseEntity<Admin>(adminService.deleteAdmin(adminId),HttpStatus.OK);
	}
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomer()throws CustomerException{
		return new ResponseEntity<List<Customer>>(customerService.viewAllCustomer(),HttpStatus.OK);
	}
	
	@GetMapping("/customers/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Integer customerId)throws CustomerException{
		return new ResponseEntity<Customer>(customerService.viewCustomerById(customerId),HttpStatus.OK);
	}
}


package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.AdminException;
import com.masai.Exception.CustomerException;
import com.masai.model.Admin;
import com.masai.model.Customer;
import com.masai.service.AdminService;
import com.masai.service.CustomerService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/wonderWorld/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/register")
	public ResponseEntity<Admin> registerAdmin(@Valid @RequestBody Admin admin)throws AdminException{
		return new ResponseEntity<Admin>(adminService.insertAdmin(admin),HttpStatus.CREATED);
	}
	
	@PutMapping("/delete/{adminId}")
	public ResponseEntity<Admin> deleteAdmin(@PathVariable Integer adminId)throws AdminException{
		return new ResponseEntity<Admin>(adminService.deleteAdmin(adminId),HttpStatus.OK);
	}
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomer()throws CustomerException{
		return new ResponseEntity<List<Customer>>(customerService.viewAllCustomer(),HttpStatus.FOUND);
	}
	
	@GetMapping("/customers/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Integer customerId)throws CustomerException{
		return new ResponseEntity<Customer>(customerService.viewCustomerById(customerId),HttpStatus.FOUND);
	}
}

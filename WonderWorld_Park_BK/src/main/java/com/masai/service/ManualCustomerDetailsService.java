package com.masai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.masai.model.Admin;
import com.masai.model.Customer;
import com.masai.repository.AdminRepository;
import com.masai.repository.CustomerRepository;

@Service
public class ManualCustomerDetailsService implements UserDetailsService {
	
	@Autowired
	 private CustomerRepository customerRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	public boolean isAdmin(String email) {
	   Optional<Admin> admin = adminRepository.findByEmail(email);
		if(admin.isPresent()) return true;
		else return false;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		if(isAdmin(email)) {
			Optional<Admin> admin = adminRepository.findByEmail(email);
			 
			 if(admin.isEmpty()) throw new UsernameNotFoundException("Admin not found");
			 Admin us = admin.get();			
			 
			List<GrantedAuthority> authorities = new ArrayList<>() ;
			SimpleGrantedAuthority autho = new SimpleGrantedAuthority(us.getEmail()) ;
			authorities.add(autho) ;
			User secUser = new User(us.getEmail(), us.getPassword(),  authorities) ;
			return secUser ;
		}else {
			
			Optional<Customer> myCustomer = customerRepository.findByEmail(email);
				 
				 if(myCustomer.isEmpty()) throw new UsernameNotFoundException("User not found");
				 Customer us = myCustomer.get();
				 
	 
				List<GrantedAuthority> authorities = new ArrayList<>() ;
				SimpleGrantedAuthority autho = new SimpleGrantedAuthority(us.getEmail()) ;
				authorities.add(autho) ;
				User secUser = new User(us.getEmail(), us.getPassword(),  authorities) ;
				return secUser ;

				
			}
		}


}

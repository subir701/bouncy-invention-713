package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.DTO.TicketDTO;
import com.masai.model.Customer;
import com.masai.repository.CustomerRepository;



@RestController

@RequestMapping("/wonderWorld/tickets")

public class TicketController {

	
	
//	TicketService ticketService;
//
//	
//	@Autowired
//	public TicketController(TicketService ticketService) {
//		
//		this.ticketService = ticketService;
//	}
	
	@PostMapping("/{customerId}/{activityId}")
	public ResponseEntity<TicketDTO> createTicket(
			@RequestBody TicketDTO ticketDTO,
			@PathVariable Integer customerId,
			@PathVariable Integer activityId
			) 
	{
		System.out.print(ticketDTO.toString());
//		Ticket ticket = ticketService.createTicket(customerId, activityId, ticketDTO);
//		Ticket ticket = null;
		return new ResponseEntity<TicketDTO>(ticketDTO, HttpStatus.CREATED);
	}
	
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@GetMapping("/")
	public ResponseEntity<List<Customer>> createTicket()
	{
		
		
		return new ResponseEntity<List<Customer>>(customerRepo.findAll(),HttpStatus.OK);
	}
	
	
	
}

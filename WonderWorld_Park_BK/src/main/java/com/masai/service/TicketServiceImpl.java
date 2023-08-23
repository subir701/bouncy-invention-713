package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.masai.model.Ticket;
import com.masai.repository.ActivityRepository;
import com.masai.repository.CustomerRepository;
import com.masai.repository.TicketRepository;

public class TicketServiceImpl implements TicketService {

	
	private TicketRepository ticketRepo;
	private ActivityRepository activityRepo;
	private CustomerRepository customerRepo;
	
	
	
	
	
	
	
	@Autowired
	public TicketServiceImpl(TicketRepository ticketRepo, ActivityRepository activityRepo,
			CustomerRepository customerRepo) {
		super();
		this.ticketRepo = ticketRepo;
		this.activityRepo = activityRepo;
		this.customerRepo = customerRepo;
	}

	@Override
	public Ticket createTicket(Integer customerId, Integer activityId, Ticket ticketDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ticket updateTicket(Integer customerId, Integer ticketId, Ticket ticketDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ticket getTicketById(Integer customerId, Integer ticketId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteTicket(Integer customerId, Integer ticketId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ticket> viewAllTickets(Integer pageNumber, Integer itemsPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ticket> getTicketBookingHistory(Integer customerId, Integer pageNumber, Integer itemsPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

}

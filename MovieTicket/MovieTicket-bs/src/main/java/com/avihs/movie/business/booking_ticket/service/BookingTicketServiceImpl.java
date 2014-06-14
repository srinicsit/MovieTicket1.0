package com.avihs.movie.business.booking_ticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avihs.movie.business.booking_ticket.dao.BookingTicketDao;
import com.avihs.movie.business.booking_ticket.model.BookingTicket;

@Service
public class BookingTicketServiceImpl implements BookingTicketService {

	@Autowired
	BookingTicketDao<BookingTicket> bookingTicketDao;
}

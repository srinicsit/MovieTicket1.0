package com.avihs.movie.business.booking_ticket.service;

import java.util.List;

import com.avihs.movie.business.booking_ticket.model.BookingTicket;

public interface BookingTicketService {
	public void save(BookingTicket bookingTicket);

	public void update(BookingTicket bookingTicket);

	public List<BookingTicket> getBookingTickets(Integer transactionId);
}

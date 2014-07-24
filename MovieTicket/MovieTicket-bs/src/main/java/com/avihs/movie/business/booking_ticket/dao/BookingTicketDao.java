package com.avihs.movie.business.booking_ticket.dao;

import java.util.List;

import com.avihs.movie.business.booking_ticket.model.BookingTicket;
import com.avihs.movie.business.dao.CommonDao;

public interface BookingTicketDao extends CommonDao {
	public List<BookingTicket> getBookingTickets(Integer transactionId);
	
	public void update(BookingTicket bookingTicket);
}

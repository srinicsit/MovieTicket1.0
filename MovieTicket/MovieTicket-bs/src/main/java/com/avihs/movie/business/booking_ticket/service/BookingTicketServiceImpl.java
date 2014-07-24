package com.avihs.movie.business.booking_ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avihs.movie.business.booking_ticket.dao.BookingTicketDao;
import com.avihs.movie.business.booking_ticket.model.BookingTicket;

@Service
public class BookingTicketServiceImpl implements BookingTicketService {

	@Autowired
	BookingTicketDao bookingTicketDao;

	@Override
	@Transactional
	public void save(BookingTicket bookingTicket) {
		bookingTicketDao.save(bookingTicket);
	}

	public List<BookingTicket> getBookingTickets(Integer transactionId) {
		return bookingTicketDao.getBookingTickets(transactionId);
	}

	@Override
	public void update(BookingTicket bookingTicket) {
		bookingTicketDao.update(bookingTicket);
	}
}

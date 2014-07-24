package com.avihs.movie.business.booking_ticket.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.avihs.movie.business.booking_ticket.model.BookingTicket;
import com.avihs.movie.business.dao.CommonDaoImpl;

@Repository
public class BookingTicketDaoImpl extends CommonDaoImpl implements
		BookingTicketDao {

	public List<BookingTicket> getBookingTickets(Integer transactionId) {
		List<BookingTicket> bookingTickets = null;
		Query query = getCurrentSession().getNamedQuery("getTicketsForTx");
		query.setInteger("transactionId", transactionId);
		bookingTickets = query.list();
		return bookingTickets;
	}

	@Override
	public void update(BookingTicket bookingTicket) {
		getCurrentSession().update(bookingTicket);
	}
}

package com.avihs.movie.business.booking_ticket.dao;

import org.springframework.stereotype.Repository;

import com.avihs.movie.business.booking_ticket.model.BookingTicket;
import com.avihs.movie.business.dao.CommonDaoImpl;

@Repository
public class BookingTicketDaoImpl extends CommonDaoImpl<BookingTicket>
		implements BookingTicketDao<BookingTicket> {

}

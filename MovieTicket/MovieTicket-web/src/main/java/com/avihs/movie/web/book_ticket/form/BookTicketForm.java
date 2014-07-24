package com.avihs.movie.web.book_ticket.form;

import java.util.ArrayList;
import java.util.List;

import com.avihs.movie.business.seats_status.model.SeatsStatus;

public class BookTicketForm {

	private String movie;

	private String bookingSeats;
	
	private List<SeatsStatus> seatsStatus = new ArrayList<SeatsStatus>(0);

	public String getBookingSeats() {
		return bookingSeats;
	}

	public void setBookingSeats(String bookingSeats) {
		this.bookingSeats = bookingSeats;
	}

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public List<SeatsStatus> getSeatsStatus() {
		return seatsStatus;
	}

}

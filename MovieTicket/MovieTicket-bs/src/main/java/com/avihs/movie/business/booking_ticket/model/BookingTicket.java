package com.avihs.movie.business.booking_ticket.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.avihs.movie.business.model.BaseModel;
import com.avihs.movie.business.model.BookingStatus;
import com.avihs.movie.business.movie_screen.model.MovieScreen;
import com.avihs.movie.business.seats_layout.model.SeatsLayout;
import com.avihs.movie.business.user.model.User;

@Entity
@Table(name = "BOOKING_TICKET")
@DynamicUpdate
@DynamicInsert
public class BookingTicket extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "REFERENCE_ID")
	private Integer referenceId;

	@JoinColumn(name = "SEATS_LAYOUT_ID")
	@ManyToOne
	private SeatsLayout seatsLayout;

	@JoinColumn(name = "USER_PK_ID")
	@ManyToOne
	private User user;

	@JoinColumn(name = "MOVIE_SCREEN_ID")
	@ManyToOne
	private MovieScreen movieScreen;

	@Column(name = "BOOKING_STATUS")
	private BookingStatus bookingStatus;

	public Integer getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Integer referenceId) {
		this.referenceId = referenceId;
	}

	public SeatsLayout getSeatsLayout() {
		return seatsLayout;
	}

	public void setSeatsLayout(SeatsLayout seatsLayout) {
		this.seatsLayout = seatsLayout;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MovieScreen getMovieScreen() {
		return movieScreen;
	}

	public void setMovieScreen(MovieScreen movieScreen) {
		this.movieScreen = movieScreen;
	}

	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

}

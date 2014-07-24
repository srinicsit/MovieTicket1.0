package com.avihs.movie.business.booking_ticket.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.avihs.movie.business.model.BaseModel;
import com.avihs.movie.business.model.BookingStatus;
import com.avihs.movie.business.movie_screen.model.MovieScreen;
import com.avihs.movie.business.seats.model.Seats;
import com.avihs.movie.business.seats_status.model.SeatsStatus;
import com.avihs.movie.business.transaction.model.Transaction;
import com.avihs.movie.business.user.model.User;

@Entity
@Table(name = "BOOKING_TICKET")
@DynamicUpdate
@DynamicInsert
@NamedQueries({ @NamedQuery(name = "getTicketsForTx", query = "from BookingTicket where transaction=:transactionId") })
public class BookingTicket extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "REFERENCE_ID")
	private Integer referenceId;

	@JoinColumn(name = "USER_PK_ID")
	@ManyToOne
	private User user;

	@JoinColumn(name = "SEAT_STATUS_ID")
	@ManyToOne
	private SeatsStatus seatStatus;

	@Column(name = "BOOKING_STATUS")
	private BookingStatus bookingStatus;

	@JoinColumn(name = "TRANSACTION_ID")
	@ManyToOne(cascade = CascadeType.ALL)
	private Transaction transaction;

	public Integer getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Integer referenceId) {
		this.referenceId = referenceId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public SeatsStatus getSeatStatus() {
		return seatStatus;
	}

	public void setSeatStatus(SeatsStatus seatStatus) {
		this.seatStatus = seatStatus;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

}

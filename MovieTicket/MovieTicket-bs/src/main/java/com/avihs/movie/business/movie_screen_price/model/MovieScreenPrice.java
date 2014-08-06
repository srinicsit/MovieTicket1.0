package com.avihs.movie.business.movie_screen_price.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.avihs.movie.business.model.BaseModel;
import com.avihs.movie.business.model.SeatTypes;
import com.avihs.movie.business.movie_screen.model.MovieScreen;

@Entity
@Table(name = "MOVIE_SCREEN_PRICE")
@DynamicUpdate
@DynamicInsert
@NamedQueries({ @NamedQuery(name = "getMovieScreenPrice", query = "from MovieScreenPrice where movieScreen=:movieScreenId") })
public class MovieScreenPrice extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "MOVIE_SCREEN_ID")
	@JsonIgnore
	private MovieScreen movieScreen;

	@ManyToOne
	@JoinColumn(name = "SEAT_TYPES_ID")
	private SeatTypes seatType;

	@Column(name = "TICKET_COST")
	private Float ticketCost;

	public MovieScreen getMovieScreen() {
		return movieScreen;
	}

	public void setMovieScreen(MovieScreen movieScreen) {
		this.movieScreen = movieScreen;
	}

	public Float getTicketCost() {
		return ticketCost;
	}

	public void setTicketCost(Float ticketCost) {
		this.ticketCost = ticketCost;
	}

	public SeatTypes getSeatType() {
		return seatType;
	}

	public void setSeatType(SeatTypes seatType) {
		this.seatType = seatType;
	}

}

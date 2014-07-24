package com.avihs.movie.business.movie_screen_price.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.avihs.movie.business.model.BaseModel;
import com.avihs.movie.business.movie_screen.model.MovieScreen;

@Entity
@Table(name = "MOVIE_SCREEN_PRICE")
@DynamicUpdate
@DynamicInsert
public class MovieScreenPrice extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "MOVIE_SCREEN_ID")
	private MovieScreen movieScreen;

	@Column(name = "SEAT_CLASS_TYPE")
	private String seatClassType;

	@Column(name = "TICKET_COST")
	private Float ticketCost;

	public MovieScreen getMovieScreen() {
		return movieScreen;
	}

	public void setMovieScreen(MovieScreen movieScreen) {
		this.movieScreen = movieScreen;
	}

	public String getSeatClassType() {
		return seatClassType;
	}

	public void setSeatClassType(String seatClassType) {
		this.seatClassType = seatClassType;
	}

	public Float getTicketCost() {
		return ticketCost;
	}

	public void setTicketCost(Float ticketCost) {
		this.ticketCost = ticketCost;
	}

}

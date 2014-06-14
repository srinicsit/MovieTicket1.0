package com.avihs.movie.business.seats_layout.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.avihs.movie.business.model.BaseModel;
import com.avihs.movie.business.model.SeatType;
import com.avihs.movie.business.screen.model.Screen;

@Entity
@Table(name = "SEATS_LAYOUT")
public class SeatsLayout extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ROW_NUM")
	private String rowNum;

	@Column(name = "COL_NUM")
	private String colNum;
	
	@Column(name = "SEAT_TYPE")
	private SeatType seatType;
	
	@ManyToOne
	@JoinColumn(name = "SCREEN_ID")
	private Screen screen;
	
	

	public String getRowNum() {
		return rowNum;
	}

	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}

	public String getColNum() {
		return colNum;
	}

	public void setColNum(String colNum) {
		this.colNum = colNum;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public SeatType getSeatType() {
		return seatType;
	}

	public void setSeatType(SeatType seatType) {
		this.seatType = seatType;
	}

}

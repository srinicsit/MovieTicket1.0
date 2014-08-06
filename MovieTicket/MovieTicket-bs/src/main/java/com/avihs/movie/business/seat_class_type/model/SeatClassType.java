package com.avihs.movie.business.seat_class_type.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.avihs.movie.business.model.BaseModel;
import com.avihs.movie.business.model.SeatTypes;
import com.avihs.movie.business.rows.model.Rows;
import com.avihs.movie.business.screen.model.Screen;

@Entity
@Table(name = "SEAT_CLASS_TYPE")
@DynamicUpdate
@DynamicInsert
@JsonSerialize(include = Inclusion.NON_NULL)
@NamedQueries({
		@NamedQuery(name = "getSeatClassTypesForScreen", query = " from SeatClassType sc where sc.screen=:screen_id"),
		@NamedQuery(name = "delSeatClassTypesForScreen", query = " delete from SeatClassType sc where sc.screen=:screen_id"),
		@NamedQuery(name = "getSeatClassTypeIdsForScreen", query = "select sc.id from SeatClassType sc where sc.screen=:screen_id") })
public class SeatClassType extends BaseModel {

	private static final long serialVersionUID = 1L;

	@Column(name = "ROWS")
	private Integer rows;

	@Column(name = "COLS")
	private Integer cols;

	@Column(name = "TICKET_COST")
	private Float ticketCost;

	@ManyToOne
	@JoinColumn(name = "SCREEN_ID")
	@JsonIgnore
	private Screen screen;

	@ManyToOne
	@JoinColumn(name = "SEAT_TYPES_ID")
	private SeatTypes seatType;

	@OneToMany(mappedBy = "seatClassType", cascade = CascadeType.ALL)
	private List<Rows> rowsList = new ArrayList<Rows>(0);

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getCols() {
		return cols;
	}

	public void setCols(Integer cols) {
		this.cols = cols;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public List<Rows> getRowsList() {
		return rowsList;
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

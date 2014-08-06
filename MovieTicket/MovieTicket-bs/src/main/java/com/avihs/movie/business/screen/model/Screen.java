package com.avihs.movie.business.screen.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import com.avihs.movie.business.seat_class_type.model.SeatClassType;
import com.avihs.movie.business.theater.model.Theater;

@Entity
@Table(name = "SCREEN")
@DynamicUpdate
@DynamicInsert
@NamedQueries({
		@NamedQuery(name = "getScreensForTheater", query = " from Screen s where s.theater=:theaterId"),
		@NamedQuery(name = "isScreenExists", query = "select s.id,s.name from Screen s where s.theater=:theaterId and s.name=:name"),
		@NamedQuery(name = "deleteScreenr", query = " delete from Screen s where id=:screenId") })
@JsonSerialize(include = Inclusion.NON_NULL)
public class Screen extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ROWS")
	private Integer rows;

	@Column(name = "COLS")
	private Integer cols;

	@ManyToOne
	@JoinColumn(name = "THEATER_ID")
	private Theater theater;

	@OneToMany(mappedBy = "screen", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<SeatClassType> seatClassTypes = new ArrayList<SeatClassType>(0);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

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

	public List<SeatClassType> getSeatClassTypes() {
		return seatClassTypes;
	}

}

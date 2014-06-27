package com.avihs.movie.business.rows.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.avihs.movie.business.model.BaseModel;
import com.avihs.movie.business.seat_class_type.model.SeatClassType;
import com.avihs.movie.business.seats.model.Seats;

@Table(name = "rows")
@Entity
@DynamicUpdate
@DynamicInsert
@JsonSerialize(include = Inclusion.NON_NULL)
public class Rows extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ROW_NAME")
	private String rowName;

	@Column(name = "ROW_NUM")
	private Integer rowNum;

	@ManyToOne
	@JoinColumn(name = "SEAT_CLASS_TYPE_ID")
	@JsonIgnore
	private SeatClassType seatClassType;

	@OneToMany(mappedBy = "row",cascade=CascadeType.ALL)
	private List<Seats> seats = new ArrayList<Seats>(0);

	public Integer getRowNum() {
		return rowNum;
	}

	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	public String getRowName() {
		return rowName;
	}

	public void setRowName(String rowName) {
		this.rowName = rowName;
	}

	public SeatClassType getSeatClassType() {
		return seatClassType;
	}

	public void setSeatClassType(SeatClassType seatClassType) {
		this.seatClassType = seatClassType;
	}

	public List<Seats> getSeats() {
		return seats;
	}

}

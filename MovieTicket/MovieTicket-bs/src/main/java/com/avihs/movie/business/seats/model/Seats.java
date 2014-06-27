package com.avihs.movie.business.seats.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.avihs.movie.business.model.BaseModel;
import com.avihs.movie.business.rows.model.Rows;

@Entity
@Table(name = "SEATS")
@DynamicUpdate
@DynamicInsert
@JsonSerialize(include = Inclusion.NON_NULL)
public class Seats extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "COL_NUM")
	private String colNum;

	@Column(name = "STATUS")
	private String status;

	@ManyToOne
	@JoinColumn(name = "ROW_ID")
	@JsonIgnore
	private Rows row;

	public String getColNum() {
		return colNum;
	}

	public void setColNum(String colNum) {
		this.colNum = colNum;
	}

	public Rows getRow() {
		return row;
	}

	public void setRow(Rows row) {
		this.row = row;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

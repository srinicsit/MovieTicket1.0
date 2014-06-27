package com.avihs.movie.web.screen.form;

public class ScreenForm {

	private String location = "Guntur";

	private String name;

	private Integer theaterId;

	private String theaterName;

	private Integer screenId;

	private Integer rows;

	private Integer columns;

	private String seatsInfo;

	public static final String ROW_NAME = "rowName";

	public static final String ROW = "row";

	public static final String CLS_NAME = "clsName";

	public static final String COL = "col";

	public static final String STATUS = "status";

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(Integer theaterId) {
		this.theaterId = theaterId;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public Integer getScreenId() {
		return screenId;
	}

	public void setScreenId(Integer screenId) {
		this.screenId = screenId;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getSeatsInfo() {
		return seatsInfo;
	}

	public void setSeatsInfo(String seatsInfo) {
		this.seatsInfo = seatsInfo;
	}

	public Integer getColumns() {
		return columns;
	}

	public void setColumns(Integer columns) {
		this.columns = columns;
	}

}

package com.avihs.movie.business.model;

public class SeatsStatusCount {

	private Long count;

	private String classType;
	
	private Integer classTypeId;
	
	private Float cost;

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public Integer getClassTypeId() {
		return classTypeId;
	}

	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}
}

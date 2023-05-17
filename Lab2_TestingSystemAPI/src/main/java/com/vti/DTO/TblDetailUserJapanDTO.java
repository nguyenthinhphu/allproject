package com.vti.DTO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TblDetailUserJapanDTO {

	private String nameLevel;
	
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date startDate;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date endDate;
	
	private String total;

	
	
	public TblDetailUserJapanDTO() {
	
	}



	public String getNameLevel() {
		return nameLevel;
	}



	public void setNameLevel(String nameLevel) {
		this.nameLevel = nameLevel;
	}


	

	public Date getStartDate() {
		return startDate;
	}



	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	public Date getEndDate() {
		return endDate;
	}



	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	public String getTotal() {
		return total;
	}



	public void setTotal(String total) {
		this.total = total;
	}
}

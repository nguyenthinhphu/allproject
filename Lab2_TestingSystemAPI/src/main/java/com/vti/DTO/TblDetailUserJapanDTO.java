package com.vti.DTO;

import java.util.Date;

public class TblDetailUserJapanDTO {

	private String nameLevel;
	
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

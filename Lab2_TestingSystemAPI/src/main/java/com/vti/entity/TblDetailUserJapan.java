package com.vti.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author nguyenthinhphu
 */
@Entity
@Table(name = "tbl_detail_user_japan")
public class TblDetailUserJapan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DETAIL_USER_JAPAN_ID")
	private int detailUserJapanId;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private TblUser user;

	@ManyToOne
	@JoinColumn(name = "CODE_LEVEL")
	private MstJapan codeLevel;
	
	@Column(name = "START_DATE")
	private Date startDate;

	@Column(name = "END_DATE")
	private Date endDate;

	@Column(name = "TOTAL")
	private int total;

	public TblDetailUserJapan() {

	}

	
	public TblDetailUserJapan(int detailUserJapanId, TblUser user, MstJapan codeLevel, Date startDate, Date endDate,
			int total) {
		super();
		this.detailUserJapanId = detailUserJapanId;
		this.user = user;
		this.codeLevel = codeLevel;
		this.startDate = startDate;
		this.endDate = endDate;
		this.total = total;
	}


	public int getDetailUserJapanId() {
		return detailUserJapanId;
	}

	public void setDetailUserJapanId(int detailUserJapanId) {
		this.detailUserJapanId = detailUserJapanId;
	}

	public TblUser getUserId() {
		return user;
	}

	public void setUserId(TblUser user) {
		this.user = user;
	}

	public MstJapan getCodeLevel() {
		return codeLevel;
	}

	public void setCodeLevel(MstJapan codeLevel) {
		this.codeLevel = codeLevel;
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

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}


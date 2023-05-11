package com.vti.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.vti.utils.ChangeFormatDate;

/**
 * TblUser.java
 * @author nguyenthinhphu
 * Map voi tbl_user
 */
@Entity
@Table(name = "tbl_user")
public class TblUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private int userId;
	
	@ManyToOne
	@JoinColumn(name = "GROUP_ID")
	private MstGroup group;

	@Column(name = "LOGIN_NAME")
	private String loginName;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "FULL_NAME")
	private String fullName;

	@Column(name = "FULL_NAME_KANA")
	private String fullNameKana;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "TEL")
	private String tel;

	@Column(name = "BIRTHDAY")
	private Date birthday;
	
	@OneToMany(mappedBy = "user")
	private List<TblDetailUserJapan> tblDetailUserJapan;
	
	public TblUser(){
		
	}
	
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}



	public MstGroup getGroup() {
		return group;
	}



	public void setGroup(MstGroup group) {
		this.group = group;
	}



	public String getLoginName() {
		return loginName;
	}



	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getFullName() {
		return fullName;
	}



	public void setFullName(String fullName) {
		this.fullName = fullName;
	}



	public String getFullNameKana() {
		return fullNameKana;
	}



	public void setFullNameKana(String fullNameKana) {
		this.fullNameKana = fullNameKana;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getTel() {
		return tel;
	}



	public void setTel(String tel) {
		this.tel = tel;
	}



	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date birthday) {
		
		this.birthday = birthday;
	}



	public List<TblDetailUserJapan> getTblDetailUserJapan() {
		return tblDetailUserJapan;
	}



	public void setTblDetailUserJapan(List<TblDetailUserJapan> detailUserJapan) {
		this.tblDetailUserJapan = detailUserJapan;
	}

	
}


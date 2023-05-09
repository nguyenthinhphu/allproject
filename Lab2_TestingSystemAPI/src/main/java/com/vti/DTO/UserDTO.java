package com.vti.DTO;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {

	@JsonProperty("userId")
	private int id;
	private String loginName;
	private String fullName;
	private String fullNameKana;
	private String email;
	private String tel;
	private String groupName;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date birthday;
	
	private List<TblDetailUserJapanDTO> tblDetailUserJapan;
	
	public UserDTO() {
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getLoginName() {
		return loginName;
	}


	public void setLoginName(String loginName) {
		this.loginName = loginName;
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


	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public List<TblDetailUserJapanDTO> getTblDetailUserJapan() {
		return tblDetailUserJapan;
	}


	public void setTblDetailUserJapan(List<TblDetailUserJapanDTO> detailJapans) {
		
		this.tblDetailUserJapan = detailJapans;
	}
	
	
}

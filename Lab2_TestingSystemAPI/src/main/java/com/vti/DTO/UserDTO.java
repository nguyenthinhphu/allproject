package com.vti.DTO;

import java.util.Date;
import java.util.List;

import com.vti.entity.TblDetailUserJapan;

public class UserDTO {

	private int id;
	private String loginName;
	private String fullName;
	private String fullNameKana;
	private String email;
	private String tel;
	private String groupName;
	private List<TblDetailUserJapan> detailJapan;
	private Date birthday;
	
	
	public UserDTO() {
		super();
	}


	public UserDTO(int id, String loginName, String fullName, String fullNameKana, String email, String tel, String groupName,
			Date birthday) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.fullName = fullName;
		this.fullNameKana = fullNameKana;
		this.email = email;
		this.tel = tel;
		this.groupName = groupName;
		this.birthday = birthday;
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


	public List<TblDetailUserJapan> getDetailJapan() {
		return detailJapan;
	}


	public void setDetailJapan(List<TblDetailUserJapan> detailJapan) {
		
		this.detailJapan = detailJapan;
	}
	
	
}

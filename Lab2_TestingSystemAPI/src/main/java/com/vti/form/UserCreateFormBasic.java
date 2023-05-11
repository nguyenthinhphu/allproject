package com.vti.form;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserCreateFormBasic {
	
	private String loginName;
	private int groupId;
	private String fullName;
	private String fullNameKana;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	private String email;
	private String tel;
	private String password;
	public UserCreateFormBasic() {
		super();
	}
	public UserCreateFormBasic(String loginName, int groupId, String fullName, String fullNameKana, Date birthday,
			String email, String tel, String password) {
		super();
		this.loginName = loginName;
		this.groupId = groupId;
		this.fullName = fullName;
		this.fullNameKana = fullNameKana;
		this.birthday = birthday;
		this.email = email;
		this.tel = tel;
		this.password = password;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
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
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}

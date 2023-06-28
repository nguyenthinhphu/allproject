package com.vti.form;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserCreateFormBasic {

	@NotBlank(message = "Login Name is not a blank")
	@Length(max = 15, message = "Login Name is max 15 characters")
	private String loginName;
	private int groupId;
	private String fullName;
	private String fullNameKana;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	
	@Email
	private String email;
	private String tel;
	private String password;
	private String codeLevel;
	@PastOrPresent
	private Date startDate;
	@FutureOrPresent
	private Date endDate;
	
	@NotNull(message = "Total is not be null")
	@PositiveOrZero(message = "The total must be greater than or equal 0")
	@Range(min=1, max = 180, message = "Total is range 1 to 180")
	private int total;

	public String getCodeLevel() {
		return codeLevel;
	}

	public void setCodeLevel(String codeLevel) {
		this.codeLevel = codeLevel;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {

		if (startDate != null) {
			this.startDate = startDate;
		}
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {

		if (endDate != null) {
			this.endDate = endDate;
		}
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

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

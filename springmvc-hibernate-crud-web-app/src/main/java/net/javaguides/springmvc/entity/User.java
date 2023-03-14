package net.javaguides.springmvc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbl_user")
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "GROUP_ID")
    private int groupId;

    @Column(name = "LOGIN_NAME")
    @NotEmpty(message = "{loginName.not.empty}")
    private String loginName;

    @Column(name = "PASSWORD")
    @NotEmpty(message = "{password.not.empty}")
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
    private Date birthDay;

	public User() {
	}

	public User(int userId, int groupId, String loginName, String password, String fullName, String fullNameKana,
			String email, String tel, Date birthDay) {
		this.userId = userId;
		this.groupId = groupId;
		this.loginName = loginName;
		this.password = password;
		this.fullName = fullName;
		this.fullNameKana = fullNameKana;
		this.email = email;
		this.tel = tel;
		this.birthDay = birthDay;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
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

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
}

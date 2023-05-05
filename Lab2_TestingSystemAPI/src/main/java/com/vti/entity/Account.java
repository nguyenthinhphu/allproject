package com.vti.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "Account", catalog = "vti_database")
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "AccountID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "Email", length = 30, nullable = false, unique = true)
	private String email;

	@Column(name = "UserName", length = 30, nullable = false, unique = true)
	private String userName;

	@Column(name = "FullName", length = 30, nullable = false, unique = true)
	private String fullName;

	@ManyToOne
	@JoinColumn(name = "DepartmentID")
	private Department department;

	@ManyToOne
	@JoinColumn(name = "PositionID")
	private Position position;

	@Column(name = "CreateDate")
	@Temporal(TemporalType.DATE)
	@CreationTimestamp
	private Date createDate;

	public Account() {
		// TODO Auto-generated constructor stub
	}

	public Account(String email, String userName, String fullName, Department department, Position position) {
		super();
		this.email = email;
		this.userName = userName;
		this.fullName = fullName;
		this.department = department;
		this.position = position;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Account { " + "ID = " + id + " | Email: " + email + " | UserName: " + userName + " | FullName: "
				+ fullName + " | Department: " + department + " | Position: " + position + " | CreateDate: "
				+ createDate + " }";
	}
}

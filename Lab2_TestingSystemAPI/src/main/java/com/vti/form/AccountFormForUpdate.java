package com.vti.form;

public class AccountFormForUpdate {
	private String fullName;
	private int departmentId;
	private int positionId;

	public AccountFormForUpdate() {
		// TODO Auto-generated constructor stub
	}

	public AccountFormForUpdate(String email, String fullName, int department, int position) {
		super();
		this.fullName = fullName;
		this.departmentId = department;
		this.positionId = position;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int department) {
		this.departmentId = department;
	}

	public int getPositionId() {
		return positionId;
	}

	public void setPositionId(int position) {
		this.positionId = position;
	}

}

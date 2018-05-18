package net.luvina.insurance.beanentity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import net.luvina.insurance.utility.ChangeFormatDate;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_internal_id")
	private int userInternalId;
	
	@Column(name = "company_internal_id")
	private int companyInternalId;

	@Column(name = "insurance_internal_id")
	private int insuranceInternalId;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "user_full_name")
	private String userFullname;

	@Column(name = "user_sex_division")
	private String userSexDivision;

	@Column(name = "birthdate")
	private Date birthdate;
	
	@OneToOne(fetch = FetchType.EAGER, optional = true, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "insurance_internal_id", nullable = false, insertable = false, updatable = false)
	private TblInsurance tblInsurance;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_internal_id", nullable = false, insertable = false, updatable = false)
	private TblCompany tblCompany;
	
	public TblUser(){
		// Khởi tạo giá trị birthdate là ngày hiện tại
		Calendar calendar = Calendar.getInstance();
		Date dateCurrent = calendar.getTime();

		this.userInternalId = 0;
		this.companyInternalId = 0;
		this.insuranceInternalId = 0;
		this.username = "";
		this.password = "";
		this.userFullname = "";
		this.userSexDivision = "";
		this.birthdate = dateCurrent;
		this.tblCompany = new TblCompany();
		this.tblInsurance = new TblInsurance();
	}

	public TblUser(int userInternalId, int companyInternalId, int insuranceInternalId, String username, String password,
			String userFullname, String userSexDivision, Date birthdate, TblCompany tblCompany, TblInsurance tblInsurance) {
		
		this.userInternalId = userInternalId;
		this.companyInternalId = companyInternalId;
		this.insuranceInternalId = insuranceInternalId;
		this.username = username;
		this.password = password;
		this.userFullname = userFullname;
		this.userSexDivision = userSexDivision;
		this.birthdate = birthdate;		
		this.tblCompany = tblCompany;
		this.tblInsurance = tblInsurance;
	}

	public int getUserInternalId() {
		return userInternalId;
	}

	public void setUserInternalId(int userInternalId) {
		this.userInternalId = userInternalId;
	}

	public int getCompanyInternalId() {
		return companyInternalId;
	}

	public void setCompanyInternalId(int companyInternalId) {
		this.companyInternalId = companyInternalId;
	}

	public int getInsuranceInternalId() {
		return insuranceInternalId;
	}

	public void setInsuranceInternalId(int insuranceInternalId) {
		this.insuranceInternalId = insuranceInternalId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserFullname() {
		return userFullname;
	}

	public void setUserFullname(String userFullname) {
		this.userFullname = userFullname;
	}

	public String getUserSexDivision() {
		return userSexDivision;
	}

	public void setUserSexDivision(String userSexDivision) {
		this.userSexDivision = userSexDivision;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		
		this.birthdate = ChangeFormatDate.changeFormatDate(birthdate);
	}

	public TblInsurance getTblInsurance() {
		return tblInsurance;
	}

	public void setTblInsurance(TblInsurance tblInsurance) {
		this.tblInsurance = tblInsurance;
	}

	public TblCompany getTblCompany() {
		return tblCompany;
	}

	public void setTblCompany(TblCompany tblCompany) {
		this.tblCompany = tblCompany;
	}

}

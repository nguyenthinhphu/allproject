package net.luvina.insurance.beanentity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.luvina.insurance.utility.ChangeFormatDate;;
/**
 * TblInsurance.java
 * @author nguyenthinhphu
 * Map voi tbl_insurance
 */
@Entity
@Table(name = "tbl_insurance")
public class TblInsurance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "insurance_internal_id")
	private int insuranceInternalId;

	@Column(name = "insurance_number")
	private String insuranceNumber;

	@Column(name = "insurance_start_date")
	private Date insuranceStartDate;

	@Column(name = "insurance_end_date")
	private Date insuranceEndDate;

	@Column(name = "place_of_register")
	private String placeOfRegister;

	public TblInsurance() {
		// Khởi tạo giá trị birthdate là ngày hiện tại
		Calendar calendar = Calendar.getInstance();
		Date dateCurrent = calendar.getTime();

		this.insuranceInternalId = 0;
		this.insuranceNumber = "";
		this.insuranceStartDate = dateCurrent;
		this.insuranceEndDate = dateCurrent;
		this.placeOfRegister = "";
	}

	public TblInsurance(int insuranceInternalId, String insuranceNumber, Date insuranceStartDate, Date insuranceEndDate,
			String placeOfRegister) {
		this.insuranceInternalId = insuranceInternalId;
		this.insuranceNumber = insuranceNumber;
		this.insuranceStartDate = insuranceStartDate;
		this.insuranceEndDate = insuranceEndDate;
		this.placeOfRegister = placeOfRegister;
	}

	public int getInsuranceInternalId() {
		return insuranceInternalId;
	}

	public void setInsuranceInternalId(int insuranceInternalId) {
		this.insuranceInternalId = insuranceInternalId;
	}

	public String getInsuranceNumber() {
		return insuranceNumber;
	}

	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}

	public Date getInsuranceStartDate() {
		return insuranceStartDate;
	}

	public void setInsuranceStartDate(String insuranceStartDate) {
		this.insuranceStartDate = ChangeFormatDate.changeFormatDate(insuranceStartDate);
		;
	}

	public Date getInsuranceEndDate() {
		return insuranceEndDate;
	}

	public void setInsuranceEndDate(String insuranceEndDate) {
		this.insuranceEndDate = ChangeFormatDate.changeFormatDate(insuranceEndDate);
	}

	public String getPlaceOfRegister() {
		return placeOfRegister;
	}

	public void setPlaceOfRegister(String placeOfRegister) {
		this.placeOfRegister = placeOfRegister;
	}

}

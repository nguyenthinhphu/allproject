package net.luvina.insurance.beanentity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * TblCompany.java
 * @author nguyenthinhphu
 * Map voi tbl_company
 */
@Entity
@Table(name = "tbl_company")
public class TblCompany implements Serializable,Comparable<TblCompany>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "company_internal_id")
	private int companyInternalId;
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "telephone")
	private String telephone;
	
	/*@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "company_internal_id", nullable=false, insertable = false, updatable=false) 
	private TblUser tblUser;*/

	public TblCompany() {
		this.companyInternalId = 0;
		this.companyName = "";
		this.address = "";
		this.email = "";
		this.telephone = "";
	}

	public TblCompany(int companyInternalId, String companyName, String address, String email, String telephone) {
		super();
		this.companyInternalId = companyInternalId;
		this.companyName = companyName;
		this.address = address;
		this.email = email;
		this.telephone = telephone;
	}

	public int getCompanyInternalId() {
		return companyInternalId;
	}

	public void setCompanyInternalId(int companyInternalId) {
		this.companyInternalId = companyInternalId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public int compareTo(TblCompany o) {
		// TODO Auto-generated method stub
		 int value = this.companyName.compareTo(o.companyName);
	          return value;
	}
}

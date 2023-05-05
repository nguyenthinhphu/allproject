package com.vti.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * MstJapan.java
 * @author nguyenthinhphu
 * Map voi tbl_company
 */
@Entity
@Table(name = "mst_japan")
public class MstJapan implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CODE_LEVEL")
	private String codeLevel;
	
	@Column(name = "NAME_LEVEL")
	private String nameLevel;

	@OneToMany(mappedBy = "codeLevel")
	private List<TblDetailUserJapan> detailUserJapan;
	
	
	public MstJapan() {
		super();
	}

	public MstJapan(String codeLevel, String nameLevel) {
		super();
		this.codeLevel = codeLevel;
		this.nameLevel = nameLevel;
	}

	public String getCodeLevel() {
		return codeLevel;
	}

	public void setCodeLevel(String codeLevel) {
		this.codeLevel = codeLevel;
	}

	public String getNameLevel() {
		return nameLevel;
	}

	public void setNameLevel(String nameLevel) {
		this.nameLevel = nameLevel;
	}

	public List<TblDetailUserJapan> getDetailUserJapan() {
		return detailUserJapan;
	}

	public void setDetailUserJapan(List<TblDetailUserJapan> detailUserJapan) {
		this.detailUserJapan = detailUserJapan;
	}

}


package com.vti.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Position", catalog = "vti_database")
public class Position implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "PositionID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "PositionName", length = 30, nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private EPositionName name;

	@OneToMany(mappedBy = "position")
	private List<Account> accounts;

	public Position() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EPositionName getName() {
		return name;
	}

	public void setName(EPositionName name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Position { " + "ID = " + id + " | Name: " + name + " }";
	}
}

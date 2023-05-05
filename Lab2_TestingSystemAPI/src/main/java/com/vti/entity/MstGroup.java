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

@Entity
@Table(name = "mst_group")
public class MstGroup implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GROUP_ID")
	private int groupId;
	
	@Column(name = "GROUP_NAME")
	private String groupName;
	
	@OneToMany(mappedBy = "group")
	private List<TblUser> users;

	public MstGroup() {
	}
	
	

	public MstGroup(int groupId, String groupName) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
	}



	public int getGroupId() {
		return groupId;
	}



	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}



	public String getGroupName() {
		return groupName;
	}



	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
}

package net.luvina.insurance.utility;

import java.util.List;

import net.luvina.insurance.beanentity.TblCompany;
import net.luvina.insurance.beanentity.TblUser;

/**
 * ListView xu ly thong tin du lieu Angular
 * @author nguyenthinhphu
 *
 */
public class ListView {

	public List<TblUser> listUser; // list User
	public List<TblCompany> listCompany; // list Company
	public ListView(){}
	
	/**
	 * Contructor
	 * @param listUser
	 * @param listCompany
	 * @param totalPage
	 */
	public ListView(List<TblUser> listUser, List<TblCompany> listCompany) {
		super();
		this.listUser = listUser;
		this.listCompany = listCompany;
	}
	/**
	 * GetList
	 * @return
	 */
	public List<TblUser> getListUser() {
		return listUser;
	}
	/**
	 * set List
	 * @param listUser
	 */
	public void setListUser(List<TblUser> listUser) {
		this.listUser = listUser;
	}
	/**
	 * get List company
	 * @return
	 */
	public List<TblCompany> getListCompany() {
		return listCompany;
	}
	/**
	 * set list Company
	 * @param listCompany
	 */
	public void setListCompany(List<TblCompany> listCompany) {
		this.listCompany = listCompany;
	}
}

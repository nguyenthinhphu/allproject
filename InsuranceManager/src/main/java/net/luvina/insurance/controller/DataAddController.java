package net.luvina.insurance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.luvina.insurance.beanentity.TblCompany;
import net.luvina.insurance.beanentity.TblUser;
import net.luvina.insurance.daoimplservice.CompanyRepository;
import net.luvina.insurance.daoimplservice.UserRepository;
import net.luvina.insurance.utility.ListView;
import net.luvina.insurance.utility.Validate;
/**
 * DataAddController.java
 * @author nguyenthinhphu
 * Xử lý khi add data
 */
@Controller
public class DataAddController {
	TblUser tblUser;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired UserRepository userRepository;
	/**
	 * listAllUsers
	 * @return
	 * Xử lý tìm kiếm trả về danh sách user
	 */
	@RequestMapping(value = "/getdata", method = RequestMethod.GET)
	public ResponseEntity<ListView> listAllUsers() {
		if (tblUser == null) {
			tblUser = new TblUser();
		}
		List<TblCompany> company = companyRepository.findAllByOrderByCompanyNameAsc(); // lấy danh sách company theo tên
		//selectCompanyName = company.get(0).getCompanyName();
		if(tblUser.getTblCompany().getCompanyName().equals("")){
		tblUser.getTblCompany().setCompanyName(company.get(0).getCompanyName());
		}
		List<TblUser> listUser = null;
		// lấy danh sách user theo điều kiện tìm kiếm
		listUser = userRepository
				.searchDataUsers(
						Validate.escapeHTML(tblUser.getUserFullname()), Validate.escapeHTML(tblUser.getTblInsurance().getInsuranceNumber()),
						Validate.escapeHTML(tblUser.getTblInsurance().getPlaceOfRegister()), tblUser.getTblCompany().getCompanyName());
		// trả về ListView dữ liệu dạng Jssom để xử lý angular js
		ListView listView = new ListView(listUser, company);
		return new ResponseEntity<ListView>(listView, HttpStatus.OK);
	}
	/**
	 * get company
	 * @param id
	 * @return
	 * Xử lý lấy data bằng Ajax
	 */
	@RequestMapping(value = {"/getDataAdd"}, method = RequestMethod.GET)
	public @ResponseBody TblCompany getCompany(@RequestParam String id){
		TblCompany tblCompany = null;
		tblCompany = companyRepository.findByCompanyName(id);
		return tblCompany;
	}
}

package net.luvina.insurance.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.velocity.tools.generic.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
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
 * ListInsuranceCardController.java
 * @author nguyenthinhphu
 * Xử lý tại màn hình list
 */
@Controller
public class ListInsuranceCardController {

	// khai báo các dữ liệu trên MH02
	String selectCompanyName = "", userFullName = "", number = "", placeOfRegister = "";
	List<TblUser> listUsers;
	List<TblCompany> listCompanys;
	TblUser tblUser;
	@Autowired
	HttpSession session;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CompanyRepository companyRepository;
	
	@RequestMapping(value = "/listMH02", method = RequestMethod.GET)
	public String listUser(Model model){
		if ("".equals(Validate.checkLogin(session))) {
		return "MH02";
		}
		else{
			return Validate.checkLogin(session);
		}
	}
	
	/**
	 * listAllUsers
	 * @return
	 * Trả về danh sách user dạng dữ liệu JSON
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ResponseEntity<ListView> listAllUsers() {
		if (tblUser == null) {
			tblUser = new TblUser();
		}
		// lấy công ty theo tên
		List<TblCompany> company = companyRepository.findAllByOrderByCompanyNameAsc();
		//selectCompanyName = company.get(0).getCompanyName();
		if(tblUser.getTblCompany().getCompanyName().equals("")){
		tblUser.getTblCompany().setCompanyName(company.get(0).getCompanyName());
		} // lấy tên đầu tiên trong danh sách sort
		List<TblUser> listUser = null;
		// lấy danh sách user
		listUser = userRepository
				.searchDataUsers(
						Validate.escapeHTML(tblUser.getUserFullname()), Validate.escapeHTML(tblUser.getTblInsurance().getInsuranceNumber()),
						Validate.escapeHTML(tblUser.getTblInsurance().getPlaceOfRegister()), tblUser.getTblCompany().getCompanyName());
		//totalPages = listUser.getTotalPages();
		session.setAttribute("listUser", listUser); // lưu vào session
		// lấy thông tin công ty theo tên
		TblCompany tblCom = companyRepository.findByCompanyName(tblUser.getTblCompany().getCompanyName());
		session.setAttribute("companyDetail", tblCom);
	 // trả về listview để xử lý angular js
		ListView listView = new ListView(listUser, company);
		return new ResponseEntity<ListView>(listView, HttpStatus.OK);
	}

	/**
	 * listSbumit
	 * @param tblUser
	 * @return
	 * Xử lý khi nhấn tìm kiếm
	 */
//	@RequestMapping(value = { "/list" }, method = RequestMethod.POST)
//	public ResponseEntity<Void> listSubmit(@RequestBody TblUser tblUser) {
//		this.tblUser = tblUser; // lấy tblUser truyền lên gán vào tblUser Controller để xử lý
//		return new ResponseEntity<Void>(HttpStatus.OK);
//	}
	
	@RequestMapping(value = { "/listall" }, method = RequestMethod.POST)
	public @ResponseBody ListView listSubmit(@RequestBody TblUser tblUser) {
		this.tblUser = tblUser; // lấy tblUser truyền lên gán vào tblUser Controller để xử lý
		// lấy công ty theo tên
		List<TblCompany> company = companyRepository.findAllByOrderByCompanyNameAsc();
		List<TblUser> listUser = null;
		// lấy danh sách user
		listUser = userRepository
				.searchDataUsers(
						Validate.escapeHTML(tblUser.getUserFullname()), Validate.escapeHTML(tblUser.getTblInsurance().getInsuranceNumber()),
						Validate.escapeHTML(tblUser.getTblInsurance().getPlaceOfRegister()), tblUser.getTblCompany().getCompanyName());
	 // trả về listview để xử lý angular js
		ListView listView = new ListView(listUser, company);
		return listView;
		
	}
	/**
	 * Export CSV
	 * @param response
	 * @return
	 * @throws IOException
	 * Xử lý export file CSV
	 */
	@RequestMapping(value = {"/exportCSV"}, method = RequestMethod.GET)
	public String exportCSV(HttpServletResponse response) throws IOException{
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		// lấy list user từ session khi search
		List<TblUser> listUser = (List<TblUser>) session.getAttribute("listUser");
		// lấy công ty từ session khi search
		TblCompany company = (TblCompany) session.getAttribute("companyDetail");
		response.setContentType("text/csv;charset= UTF-8");
		String reportName = "ListInsurance.csv";
		response.setHeader("Content-disposition", "attachment;filename="+reportName);
 
		// thực hiện add vào list
		ArrayList<String> rows = new ArrayList<String>();
		rows.add("Danh sách thông tin thẻ bảo hiểm");
		rows.add("\n");
		rows.add("\n");
		rows.add("Tên công ty");
		rows.add(",");
		rows.add(company.getCompanyName());
		rows.add("\n");
		rows.add("Địa chỉ");
		rows.add(",");
		rows.add(company.getAddress());
		rows.add("\n");
		rows.add("Email");
		rows.add(",");
		rows.add(company.getEmail());
		rows.add("\n");
		rows.add("Số điện thoại");
		rows.add(",");
		rows.add(company.getTelephone());
		rows.add("\n");
		rows.add("\n");
		rows.add("Họ và tên,Giới tính,Ngày sinh,Mã số thẻ bảo hiểm, Ngày bắt đầu, Ngày kết thúc,Nơi đăng ký KCB");
		rows.add("\n");
 
		// add các dữ liệu vào list để chuẩn bị ghi
		for (int i = 0; i < listUser.size(); i++) {
			rows.add(listUser.get(i).getUserFullname());
			rows.add(",");
			if(listUser.get(i).getUserSexDivision().equals("01")){
				rows.add("Nam");
			}
			else {
				rows.add("Nữ");
			}
			rows.add(",");
			rows.add(format.format((listUser.get(i).getBirthdate())));
			rows.add(",");
			rows.add(listUser.get(i).getTblInsurance().getInsuranceNumber());
			rows.add(",");
			rows.add(format.format(listUser.get(i).getTblInsurance().getInsuranceStartDate()));
			rows.add(",");
			rows.add(format.format(listUser.get(i).getTblInsurance().getInsuranceEndDate()));
			rows.add(",");
			rows.add(listUser.get(i).getTblInsurance().getPlaceOfRegister());
			rows.add("\n");
		}
 
		// ghi list ra file
		Iterator<String> iter = rows.iterator();
		while (iter.hasNext()) {
			
			String outputString = (String) iter.next();
			byte[] ptext = outputString.getBytes("ISO-8859-1");
			response.setCharacterEncoding("utf-8");
			String toWrite = new String(ptext,"ISO-8859-1");
//			response.getOutputStream().write(0xEF);
//			response.getOutputStream().write(0xBB);
//			   //last byte of BOM
//			response.getOutputStream().write(0xBF);
			response.getOutputStream().print(toWrite);
		}
 
		response.getOutputStream().flush();
		response.getOutputStream().close();
		return "MH02";
	}
}

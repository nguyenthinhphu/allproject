package net.luvina.insurance.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.velocity.tools.generic.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.luvina.insurance.beanentity.TblCompany;
import net.luvina.insurance.beanentity.TblInsurance;
import net.luvina.insurance.beanentity.TblUser;
import net.luvina.insurance.daoimplservice.CompanyRepository;
import net.luvina.insurance.daoimplservice.InsuranceRepository;
import net.luvina.insurance.daoimplservice.UserRepository;
import net.luvina.insurance.utility.Constant;
import net.luvina.insurance.utility.Validate;
/**
 * AddInsuranceCartController.java
 * @author nguyenthinhphu
 * Xử lý logic khi Add Mới User
 */
@Controller
public class AddInsuranceCardController {
	List<TblCompany> listCompanys;
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private InsuranceRepository insuranceRepository;
	@Autowired
	private UserRepository userRepository;
	/**
	 * addPage
	 * @param userModel
	 * @param session
	 * @return MH04
	 * Xử lý khi vào màn hình add
	 */
	@RequestMapping(value = { "/add" }, method = RequestMethod.GET)
	public String addPage(Model userModel, HttpSession session) {
		if ("".equals(Validate.checkLogin(session))) {
			TblUser tblUser = new TblUser(); // lay user chua co du lieu trong truong hop add moi
			userModel.addAttribute(tblUser); // truyen tbluser xuong views
			String errorMessage = " "; // tao message loi
			if (session.getAttribute("errorMessage") != null && !session.getAttribute("errorMessage").equals("")) {
				errorMessage = (String) session.getAttribute("errorMessage");
			} // lay error message khi có lỗi
			if(session.getAttribute("companyDetail") != null){
			TblCompany company = (TblCompany) session.getAttribute("companyDetail");
			userModel.addAttribute("companyMH02", company.getCompanyName());
			}
			listCompanys = companyRepository.findAllByOrderByCompanyNameAsc();
			userModel.addAttribute("listCompanys", listCompanys);
			userModel.addAttribute("date", new DateTool());
			userModel.addAttribute("companyNameNew", " ");
			userModel.addAttribute("errorMessage", errorMessage);
			return "MH04";
		} else {
			return Validate.checkLogin(session);
		}
	}

	/**
	 * addSubmit
	 * @param session
	 * @param model
	 * @param tblUser
	 * @param companyName
	 * @param selectCompanyName
	 * @param companyNameNew
	 * @param userSexDivision
	 * @param btnHuy
	 * @param btnDangky
	 * @return
	 * Xử lý khi xử lý các sự kiện trên màn hình Add
	 */
	@Transactional
	@RequestMapping(value = { "/add" }, method = RequestMethod.POST)
	public String addSubmit(HttpSession session, Model model, TblUser tblUser,
			@RequestParam("companyName") String companyName,
			@RequestParam("selectCompanyName") String selectCompanyName,
			@RequestParam("companyNameNew") String companyNameNew,
			@RequestParam("userSexDivision") String userSexDivision,
			@RequestParam(value = "btnHuy", required = false) String btnHuy,
			@RequestParam(value = "btnDangky", required = false) String btnDangky) {
		
		String path = "";
		String errorMessage = "";
		List<TblInsurance> listInsuranceNumber;
		TblCompany tblCompany = new TblCompany();
		TblInsurance tblInsurance = new TblInsurance();

		try {
			if (btnHuy != null) { // nhấn nút hủy
				path = "redirect:/listMH02";
			} else if (btnDangky != null) { // nhấn nút đăng ký
				
				// tìm kiếm danh sách insurance theo insurance number để validate
				listInsuranceNumber = insuranceRepository
						.findByInsuranceNumber(tblUser.getTblInsurance().getInsuranceNumber());
				// Escape HTML
				Validate.escapeHTML(tblUser.getTblInsurance().getInsuranceNumber());
				Validate.escapeHTML(tblUser.getUserFullname());
				Validate.escapeHTML(tblUser.getTblInsurance().getPlaceOfRegister());
				// Validate
				errorMessage = Validate.checkInformartionAdd(
						tblUser.getTblInsurance().getInsuranceNumber(), listInsuranceNumber,
						tblUser.getUserFullname(),
						tblUser.getTblInsurance().getPlaceOfRegister());
				if (errorMessage.equals("")) {
					if (companyName.equals("1")) {
						int insuranceId = 0; // khởi tạo giá trị insurance id
						insuranceId = insuranceRepository.getMaxTblInsurance(); // lấy giá trị max id trong tbl_insurance
						insuranceId = insuranceId + 1; // tăng id lên 1
						// insert vào tbl_insurance
						insuranceRepository.insertTblInsurance(insuranceId,
								tblUser.getTblInsurance().getInsuranceNumber(),
								tblUser.getTblInsurance().getInsuranceStartDate(),
								tblUser.getTblInsurance().getInsuranceEndDate(),
								tblUser.getTblInsurance().getPlaceOfRegister());

						// lay id the vua them
						tblInsurance.setInsuranceInternalId(insuranceRepository.getMaxTblInsurance());
						// lay companyid
						tblCompany.setCompanyInternalId(
								companyRepository.getIdByCompanyName(selectCompanyName).get(0).getCompanyInternalId());
						// add user
						userRepository.insertUser(tblCompany.getCompanyInternalId(),
								tblInsurance.getInsuranceInternalId(), Validate.formatName(tblUser.getUserFullname()),
								userSexDivision, tblUser.getBirthdate());
					} else if (companyName.equals("2")) {
						// escape HTML
						Validate.escapeHTML(companyNameNew);
						Validate.escapeHTML(tblUser.getTblCompany().getAddress());
						// validate truong hop 2
						errorMessage = Validate.checkAddNew(companyNameNew, tblUser.getTblCompany().getAddress());
						if (errorMessage.equals("")) {
							int insuranceId = 0;
							// lấy giá trị max trong bảng tbl_insurance
							insuranceId = insuranceRepository.getMaxTblInsurance();
							insuranceId = insuranceId + 1; // tăng lên 1
							// insert thông tin vào CSDL
							insuranceRepository.insertTblInsurance(insuranceId,
									tblUser.getTblInsurance().getInsuranceNumber(),
									tblUser.getTblInsurance().getInsuranceStartDate(),
									tblUser.getTblInsurance().getInsuranceEndDate(),
									tblUser.getTblInsurance().getPlaceOfRegister());
							// lấy giá trị id lớn nhất trong tbl_insurance
							tblInsurance.setInsuranceInternalId(insuranceRepository.getMaxTblInsurance());
							int companyId = 0; // khởi tạo company id
							companyId = companyRepository.getMaxCompanyId(); // lấy max id trong bảng tbl_company
							companyId = companyId + 1; // tăng giá trị lên 1
							// insert vào bảng tbl_company
							companyRepository.insertCompany(companyId, Validate.formatName(companyNameNew),
									tblUser.getTblCompany().getAddress(), tblUser.getTblCompany().getEmail(),
									tblUser.getTblCompany().getTelephone());

							// lay id cong ty cua them
							tblCompany.setCompanyInternalId(companyRepository.getMaxCompanyId());

							// them vao bang user
							userRepository.insertUser(tblCompany.getCompanyInternalId(),
									tblInsurance.getInsuranceInternalId(),
									Validate.formatName(tblUser.getUserFullname()), userSexDivision,
									tblUser.getBirthdate());
						} else {
							// truyển dữ liệu xuống view khi có lỗi
							model.addAttribute("errorMessage", errorMessage);
							model.addAttribute(tblUser);
							listCompanys = companyRepository.findAllByOrderByCompanyNameAsc();
							model.addAttribute("listCompanys", listCompanys);
							// add dữ liệu xuoong view
							model.addAttribute("date", new DateTool());
							model.addAttribute("selectCompanyName", selectCompanyName);
							model.addAttribute("userSexDivision", userSexDivision);
							model.addAttribute("companyNameNew", companyNameNew);
							return "MH04";
						}

					}
					// nếu đúng hết chuyển về MH2
					session.removeAttribute("companyDetail");
					path = "redirect:/listMH02";
				} else {
					// truyền dữ liệu xuống model
					model.addAttribute("errorMessage", errorMessage);
					model.addAttribute(tblUser);
					// lấy lại danh sách company
					listCompanys = companyRepository.findAllByOrderByCompanyNameAsc();
					model.addAttribute("listCompanys", listCompanys);
					model.addAttribute("selectCompanyName", selectCompanyName);
					model.addAttribute("date", new DateTool());
					model.addAttribute("userSexDivision", userSexDivision);
					model.addAttribute("companyNameNew", " ");
					// trả về màn hình 
					path = "MH04";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}
	


}

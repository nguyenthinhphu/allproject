package net.luvina.insurance.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.velocity.tools.generic.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import net.luvina.insurance.utility.Validate;
/**
 * UpdateInsuranceCardController.java
 * @author nguyenthinhphu
 * Xử lý MH05
 */
@Controller
public class UpdateInsuranceCardController {

	// tạo 2 list lưu dữ liệu
	List<TblUser> listUsers;
	List<TblCompany> listCompanys;

	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private InsuranceRepository insuranceRepository;
	@Autowired
	private UserRepository userRepository;

	/**
	 * updatePage
	 * @param userModel
	 * @param session
	 * @return
	 * Xử lý khi vào màn hình update
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/update" }, method = RequestMethod.GET)
	public String updatePage(Model userModel, HttpSession session) {
		if ("".equals(Validate.checkLogin(session))) {
		String errorMessageUpdate = " ";
		TblUser tblUser = new TblUser();
		int userId = (int) session.getAttribute("userId");
		tblUser = userRepository.findByUserInternalId(userId);

		// set cac gia tri khi add cong ty moi
		tblUser.getTblCompany().setAddress("");
		tblUser.getTblCompany().setEmail("");
		tblUser.getTblCompany().setTelephone("");

		userModel.addAttribute(tblUser);
		listCompanys = companyRepository.findAllByOrderByCompanyNameAsc();

		userModel.addAttribute("date", new DateTool());
		// userModel.addAttribute("listUsers", listUsers);
		userModel.addAttribute("listCompanys", listCompanys);
		userModel.addAttribute("errorMessageUpdate", errorMessageUpdate);
		userModel.addAttribute("companyNameNew", " ");

		return "MH05";
		}
		else {
			return Validate.checkLogin(session);
		}
	}

	/**
	 * updateSbumit
	 * @param model
	 * @param tblUser
	 * @param session
	 * @param companyName
	 * @param selectCompanyName
	 * @param companyNameNew
	 * @param userSexDivision
	 * @param btnHuy
	 * @param btnCapnhat
	 * @return
	 * Xử lý khi nhấn submit
	 */
	@RequestMapping(value = { "/update" }, method = RequestMethod.POST)
	public String updateSubmit(Model model, TblUser tblUser, HttpSession session,
			@RequestParam("companyName") String companyName,
			@RequestParam("selectCompanyName") String selectCompanyName,
			@RequestParam("companyNameNew") String companyNameNew,
			@RequestParam("userSexDivision") String userSexDivision,
			@RequestParam(value = "btnHuy", required = false) String btnHuy,
			@RequestParam(value = "btnCapnhat", required = false) String btnCapnhat) {

		String path = "";
		String errorMessageUpdate = "";
		String checkSessionUpdate = "";
		List<TblInsurance> listInsuranceNumber;
		TblCompany tblCompany = new TblCompany();

		try {
			if (btnHuy != null) {
				path = "redirect:/listMH02";
			} else if (btnCapnhat != null) {
				int userId = (int) session.getAttribute("userId");
				int insuranceId = (int) session.getAttribute("insuranceId");

				// lay danh sach insurance de kiem tra trung
				listInsuranceNumber = insuranceRepository
						.getListInsuranceNotId(tblUser.getTblInsurance().getInsuranceNumber(), insuranceId);

				// escape HTML
				Validate.escapeHTML(tblUser.getTblInsurance().getInsuranceNumber());
				Validate.escapeHTML( tblUser.getUserFullname());
				Validate.escapeHTML(tblUser.getTblInsurance().getPlaceOfRegister());
				// Validate
				errorMessageUpdate = Validate.checkInformartionAdd(tblUser.getTblInsurance().getInsuranceNumber(),
						listInsuranceNumber, tblUser.getUserFullname(), tblUser.getTblInsurance().getPlaceOfRegister());

				if (errorMessageUpdate.equals("")) {

					// khi chon cong ty da co
					if (companyName.equals("1")) {
						// update data vào bảng tbl_insurance
						insuranceRepository.updateTblInsurance(insuranceId,
								tblUser.getTblInsurance().getInsuranceNumber(),
								tblUser.getTblInsurance().getInsuranceStartDate(),
								tblUser.getTblInsurance().getInsuranceEndDate(),
								tblUser.getTblInsurance().getPlaceOfRegister());

						// get Id cong ty
						tblCompany.setCompanyInternalId(
								companyRepository.getIdByCompanyName(selectCompanyName).get(0).getCompanyInternalId());

						// cap nhat vao tbluser
						userRepository.updateTblUser(userId, tblCompany.getCompanyInternalId(), insuranceId,
								Validate.formatName(tblUser.getUserFullname()), userSexDivision, tblUser.getBirthdate());

						// khi tao cong ty moi
					} else if (companyName.equals("2")) {
						
						// validate truong hop 2
						errorMessageUpdate = Validate.checkAddNew(companyNameNew,
								tblUser.getTblCompany().getAddress());
						if (errorMessageUpdate.equals("")) {
						// cap nhat vao tbl_insurance
						insuranceRepository.updateTblInsurance(insuranceId,
								tblUser.getTblInsurance().getInsuranceNumber(),
								tblUser.getTblInsurance().getInsuranceStartDate(),
								tblUser.getTblInsurance().getInsuranceEndDate(),
								tblUser.getTblInsurance().getPlaceOfRegister());

						// insert vao tbl__company
						int companyId = companyRepository.getMaxCompanyId(); // lấy giá trị id max trong bảng tbl-company
						companyId = companyId + 1; // tăng giá trị lên 1
						companyRepository.insertCompany(companyId, Validate.formatName(companyNameNew), tblUser.getTblCompany().getAddress(),
								tblUser.getTblCompany().getEmail(), tblUser.getTblCompany().getTelephone());

						// lay id vua them vao company
						tblCompany.setCompanyInternalId(companyRepository.getMaxCompanyId());

						// cap nhat vao tbl_user
						userRepository.updateTblUser(userId, tblCompany.getCompanyInternalId(), insuranceId,
								Validate.formatName(tblUser.getUserFullname()), userSexDivision, tblUser.getBirthdate());
					}
					else {
						// truyền dữ liệu xuống view khi lỗi
						model.addAttribute("errorMessageUpdate", errorMessageUpdate);
						model.addAttribute(tblUser);
						// lấy danh sách company khi có lỗi
						listCompanys = companyRepository.findAllByOrderByCompanyNameAsc();
						// truyền dữ liệu xuống views
						model.addAttribute("listCompanys", listCompanys);
						model.addAttribute("date", new DateTool());
						model.addAttribute("selectCompanyName", selectCompanyName);
						model.addAttribute("companyNameNew",companyNameNew);
						return "MH05";
					}
				}
					// trả về MH02 khi không có lỗi
					path = "redirect:/listMH02";
				} else {
					// truyền dữ liệu xuống view khi lỗi
					model.addAttribute("errorMessageUpdate", errorMessageUpdate);
					model.addAttribute(tblUser);
					// lấy danh sách company khi có lỗi
					listCompanys = companyRepository.findAllByOrderByCompanyNameAsc();
					model.addAttribute("listCompanys", listCompanys);
					model.addAttribute("selectCompanyName", selectCompanyName);
					model.addAttribute("date", new DateTool());
					model.addAttribute("companyNameNew", " ");
					// o trang hien tai
					path = "MH05";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return path;
	}
}

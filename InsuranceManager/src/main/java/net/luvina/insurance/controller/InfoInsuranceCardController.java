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

import net.luvina.insurance.beanentity.TblUser;
import net.luvina.insurance.daoimplservice.UserRepository;
/**
 * InfoInsuranceCardController.java
 * @author nguyenthinhphu
 * Xử lý thông tin mà hình MH03
 */
@Controller
public class InfoInsuranceCardController {
	List<TblUser> listUsers;
	@Autowired
	private UserRepository userRepository;
	@Autowired HttpSession session;
	/**
	 * infoPage
	 * @param userModel
	 * @param session
	 * @param uid
	 * @return
	 * Xử lý khi vào màn hình MH03
	 */
	@RequestMapping(value = { "/info" }, method = RequestMethod.GET)
	public String infoPage(Model userModel, HttpSession session,
			@RequestParam("uid") int uid) {
		// tìm user theo id
		TblUser user = userRepository.findByUserInternalId(uid);
		// truyền dữ liệu user xuống view
		userModel.addAttribute("userDetail", user);
		userModel.addAttribute("date", new DateTool());
		session.setAttribute("userId", uid);
		session.setAttribute("insuranceId", user.getTblInsurance().getInsuranceInternalId());
		return "MH03";
	}
	/**
	 * 
	 * @param userModel
	 * @param session
	 * @param btnQuaylai
	 * @param btnXoa
	 * @param btnCapnhat
	 * @return
	 * Xử lý các sự kiện trên Mh03
	 */
	@RequestMapping(value = { "/info" }, method = RequestMethod.POST)
	public String infoSubmit(Model userModel, HttpSession session,			
			@RequestParam(value = "btnQuaylai", required = false) String btnQuaylai,
			@RequestParam(value = "btnXoa", required = false) String btnXoa,
			@RequestParam(value = "btnCapnhat", required = false) String btnCapnhat){
		
		String path = "";
		try {
			// lấy các giá trị id từ session
			int idUserDelete = (int) session.getAttribute("userId");
			int insuranceIdDelete = (int) session.getAttribute("insuranceId");
			
			if (btnQuaylai != null) {
				path = "MH02";
			} else if (btnXoa != null) {
				// nếu ấn nút xóa thì xóa user theo id
				userRepository.deleteUser(idUserDelete);
				// xoa session
				session.removeAttribute("companyDetail");
				path = "redirect:/listMH02";
			} else if (btnCapnhat != null) {
				// câp nhật thì chuyển sang MH05
				path = "redirect:/update";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return path;
	}
}

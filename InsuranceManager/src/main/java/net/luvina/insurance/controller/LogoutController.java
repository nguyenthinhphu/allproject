package net.luvina.insurance.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * LogoutController.java
 * @author nguyenthinhphu
 * Xử lý khi logout
 */
@Controller
public class LogoutController {

	@Autowired HttpSession session;
	/**
	 * logout
	 * @return
	 */
	@RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
	public String logout(){
		// Xóa hết session
		// TRở về màn login
		session.removeAttribute("username");
		session.removeAttribute("password");
		// Remove session companyDetail
		session.removeAttribute("companyDetail");
		return "redirect:/login";
	}
}

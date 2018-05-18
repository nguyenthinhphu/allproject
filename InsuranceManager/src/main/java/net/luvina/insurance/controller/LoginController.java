package net.luvina.insurance.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.luvina.insurance.beanentity.TblUser;
import net.luvina.insurance.daoimplservice.UserRepository;
import net.luvina.insurance.utility.Validate;
/**
 * LoginController.java
 * @author nguyenthinhphu
 * Xử lý khi login
 */
@Controller
public class LoginController {

	/**
	 * loginPage
	 * @param userModel
	 * @return
	 * Xử lý khi vào màn hình login
	 */
	@RequestMapping(value = { "/login", "/" }, method = RequestMethod.GET)
	public String loginPage(Model userModel) {
		TblUser tblUser = new TblUser();
		userModel.addAttribute(tblUser);
		userModel.addAttribute("errorMessageMH01", "");
		return "MH01";
	}

	@Autowired
	private UserRepository userRepository;

	/**
	 * Xử lý khi click submit
	 * @param userModel
	 * @param tblUser
	 * @param session
	 * @return
	 */
	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public String loginSubmit(Model userModel, TblUser tblUser, HttpSession session) {
		String errorMessage = "";
		String path = "";
		//String passMD5;		
		
		try {			
			//passMD5 = ConvertPasswordToMD5.encryptMD5(tblUser.getPassword());		
			List<TblUser> listUser = userRepository.findByUsernameAndPassword(Validate.escapeHTML(tblUser.getUsername()), tblUser.getPassword());
			// validate
			errorMessage = Validate.checkUserNameAndPassword(tblUser.getUsername(), tblUser.getPassword(), listUser);	
			if (errorMessage.equals("")) {
				session.setAttribute("username", tblUser.getUsername());
				session.setAttribute("password", tblUser.getPassword());
				path = "redirect:/listMH02";				
			} else {			
				path = "MH01";
			}
			userModel.addAttribute(tblUser);
			userModel.addAttribute("errorMessageMH01", errorMessage);			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return path;

	}
}
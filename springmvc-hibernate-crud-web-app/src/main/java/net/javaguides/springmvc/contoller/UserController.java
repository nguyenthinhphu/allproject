package net.javaguides.springmvc.contoller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springmvc.constant.MessageCodeConstant;
import net.javaguides.springmvc.dao.ResultDTO;
import net.javaguides.springmvc.entity.Customer;
import net.javaguides.springmvc.entity.User;
import net.javaguides.springmvc.service.MessageService;
import net.javaguides.springmvc.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private MessageService messageService;

	@GetMapping(value = { "/", "/login" })
	public String loginPage(Model theModel) {
		User theUser = new User();
		theModel.addAttribute("user", theUser);
		theModel.addAttribute("errorMessage", "");
		return "MH01";
	}

	@PostMapping(value = { "/", "/login" })
	public String loginPagePost( Model theModel, @ModelAttribute("user") User theUser) {

		String loginName = theUser.getLoginName();
		String password = theUser.getPassword();
		System.out.println(loginName);
		System.out.println(password);
		String result = "";
		ResultDTO resultMessage = new ResultDTO();
		if (loginName.isEmpty() && password.isEmpty()) {
			resultMessage = responseError(MessageCodeConstant.MSG_403);
		} else {
			System.out.println("Vao else nay");
			if (password.isEmpty()) {
				resultMessage = responseError(MessageCodeConstant.MSG_404);
			} else if (password.isEmpty()) {
				resultMessage = responseError(MessageCodeConstant.MSG_405);
			} else {
				System.out.println("Vao else 2");
				if (loginName.equals("admin") && password.equals("admin"))
				{
					System.out.println("Vao return sang list");
					return "redirect:/customer/list";
				} else
				{
					System.out.println("other");
					resultMessage = responseError(MessageCodeConstant.MSG_406);
				}
			}
		}
		result = resultMessage.getMessageJp();
		if (!result.isEmpty())
		{
			System.out.println("Vao set error message");
			theModel.addAttribute("errorMessage", result);
		}
		System.out.println(result);
		theModel.addAttribute("user", theUser);
		return "MH01";
	}
	
	@GetMapping(value = {"/list" })
	public String listPage(Model theModel) {
		User theUser = new User();
		theModel.addAttribute("user", theUser);
		return "MH02";
	}

	private ResultDTO responseError(String errorCode) {
		return messageService.getResponseMessage(errorCode);
	}

}

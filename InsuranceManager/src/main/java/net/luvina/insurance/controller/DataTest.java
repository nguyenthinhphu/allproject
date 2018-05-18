package net.luvina.insurance.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import net.luvina.insurance.beanentity.TblCompany;
import net.luvina.insurance.beanentity.TblUser;
import net.luvina.insurance.daoimplservice.CompanyRepository;
import net.luvina.insurance.daoimplservice.InsuranceRepository;
import net.luvina.insurance.daoimplservice.UserRepository;

@Controller
public class DataTest {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	CompanyRepository comRepository;
	@Autowired
	InsuranceRepository insuranceRes;

	// list All data
	@RequestMapping(value = "/listdata", method = RequestMethod.GET)
	public @ResponseBody List<TblUser> getListUser() {
		List<TblUser> listUsers = userRepository.findAll();
		return listUsers;
	}

	// get all company fill the selectbox
	@RequestMapping(value = "/listcom", method = RequestMethod.GET)
	public @ResponseBody List<TblCompany> getCompany() {
		List<TblCompany> listCom = comRepository.findAllByOrderByCompanyNameAsc();
		return listCom;
	}

	// get User by id. sử dụng query string truyền id lên.
	@RequestMapping(value = "/getid", method = RequestMethod.GET)
	public @ResponseBody TblUser getUserId(@RequestParam("id") Integer id) {
		TblUser tblUser = userRepository.findByUserInternalId(id);
		return tblUser;
	}

	// create user, truyen len la user khai bao o angular js
	@RequestMapping(value = "/getid", method = RequestMethod.POST)
	public @ResponseBody void createUser(@RequestBody TblUser user) {
		Random rd = new Random();
		user.setCompanyInternalId(rd.nextInt(5));
		user.setInsuranceInternalId(rd.nextInt(5));
		System.out.println("AAACCCC");
		System.out.println(user.getUserFullname());
		userRepository.insertUser(user.getCompanyInternalId(), user.getInsuranceInternalId(), user.getUserFullname(),
				user.getUserSexDivision(), user.getBirthdate());
		System.out.println("Innsert Completed");

	}

	// Xóa user, Truyển lên là 1 id
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public @ResponseBody void deleteUser(@RequestBody int id) {
		System.out.println(id);
		userRepository.deleteUser(id);
		System.out.println("Delete Completed");

	}

	// Tìm kiểm user trả về Response body là List User
	// Find
	@RequestMapping(value = "/findCom", method = RequestMethod.POST)
	public @ResponseBody List<TblUser> findCom(@RequestBody int comId) {
		System.out.println(comId);
		List<TblUser> listUser = userRepository.findByCompanyInternalId(comId);
		return listUser;

	}

	/**
	 * Tìm kiếm User
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/searchuser", method = RequestMethod.POST)
	public @ResponseBody List<TblUser> searchUser(@RequestBody TblUser user) {
		System.out.println(user.getUserFullname());
		System.out.println(user.getTblInsurance().getPlaceOfRegister());
		System.out.println(user.getTblInsurance().getInsuranceNumber());
//		List<TblUser> listUser = userRepository
//				.findByUserFullnameContainsAndTblInsurancePlaceOfRegisterContainsAndTblInsuranceInsuranceNumberContains(
//						user.getUserFullname(), user.getTblInsurance().getPlaceOfRegister(),
//						user.getTblInsurance().getInsuranceNumber());
		return null;

	}

	/**
	 * Find by user id
	 */
	@RequestMapping(value = "/finduser", method = RequestMethod.POST)
	public @ResponseBody TblUser findUser(@RequestBody int userId) {
		System.out.println(userId);
		TblUser tblUser = userRepository.findByUserInternalId(userId);
		return tblUser;

	}

	// Update user, Truyển lên là 1 id
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public @ResponseBody List<TblUser> updateUser(@RequestBody TblUser userupdate) {
		System.out.println(userupdate.getInsuranceInternalId());
		System.out.println(userupdate.getUserFullname());
		insuranceRes.updateTblInsurance(userupdate.getInsuranceInternalId(),
				userupdate.getTblInsurance().getInsuranceNumber(), userupdate.getTblInsurance().getInsuranceStartDate(),
				userupdate.getTblInsurance().getInsuranceEndDate(), userupdate.getTblInsurance().getPlaceOfRegister());
		userRepository.updateTblUser(userupdate.getUserInternalId(), userupdate.getCompanyInternalId(), userupdate.getInsuranceInternalId(),
				userupdate.getUserFullname(), userupdate.getUserSexDivision(), userupdate.getBirthdate());
		System.out.println("Update Completed");
		List<TblUser> listUsers = userRepository.findAll();
		return listUsers;

	}
}

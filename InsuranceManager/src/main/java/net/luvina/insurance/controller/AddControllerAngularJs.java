package net.luvina.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.luvina.insurance.beanentity.TblCompany;
import net.luvina.insurance.beanentity.TblUser;
import net.luvina.insurance.daoimplservice.CompanyRepository;
import net.luvina.insurance.daoimplservice.InsuranceRepository;
import net.luvina.insurance.daoimplservice.UserRepository;

@Controller
public class AddControllerAngularJs {

	@Autowired
	CompanyRepository comRepository;

	@Autowired
	UserRepository userRepository;
	@Autowired
	InsuranceRepository insuranceRes;

	@RequestMapping(value = { "/USER-ADD" }, method = RequestMethod.GET)
	public String UserAdd() {
		return "MH04AngularJS";
	}

	@RequestMapping(value = { "/findCompany" }, method = RequestMethod.POST)
	public @ResponseBody TblCompany findCom(@RequestBody int id) {
		TblCompany company = comRepository.findByCompanyInternalId(id);
		return company;

	}

	@RequestMapping(value = { "/addUserAngular" }, method = RequestMethod.POST)
	public @ResponseBody void addUser(@RequestBody TblUser userAdd) {

			int comIdNew = comRepository.getMaxCompanyId() + 1;
			comRepository.insertCompany(comIdNew, userAdd.getTblCompany().getCompanyName(), userAdd.getTblCompany().getAddress(), userAdd.getTblCompany().getEmail(),
					userAdd.getTblCompany().getTelephone());
			int insuranceIdNew = insuranceRes.getMaxTblInsurance() + 1;
			insuranceRes.insertTblInsurance(insuranceIdNew, userAdd.getTblInsurance().getInsuranceNumber(),
					userAdd.getTblInsurance().getInsuranceStartDate(), userAdd.getTblInsurance().getInsuranceEndDate(),
					userAdd.getTblInsurance().getPlaceOfRegister());
			userRepository.insertUser(comIdNew, insuranceIdNew, userAdd.getUserFullname(), userAdd.getUserSexDivision(),
					userAdd.getBirthdate());
			int insuranceIdNew2 = insuranceRes.getMaxTblInsurance() + 1;
			insuranceRes.insertTblInsurance(insuranceIdNew, userAdd.getTblInsurance().getInsuranceNumber(),
					userAdd.getTblInsurance().getInsuranceStartDate(), userAdd.getTblInsurance().getInsuranceEndDate(),
					userAdd.getTblInsurance().getPlaceOfRegister());
			userRepository.insertUser(userAdd.getTblCompany().getCompanyInternalId(), insuranceIdNew,
					userAdd.getUserFullname(), userAdd.getUserSexDivision(), userAdd.getBirthdate());

	}

}

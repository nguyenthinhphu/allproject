package com.vti.backend.controllerlayer;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.DTO.TblDetailUserJapanDTO;
import com.vti.DTO.UserDTO;
import com.vti.backend.servicelayer.IUserService;
import com.vti.entity.TblUser;
import com.vti.form.UserCreateFormBasic;
import com.vti.form.UserFilterForm;

@RestController
@RequestMapping(value = "api/v1/users")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private IUserService service;

	@Autowired
	private ModelMapper modelMapper;

	// Get All Users
	@GetMapping()
	public Page<UserDTO> getAllUsers(Pageable pageable, UserFilterForm filterForm) {
		Page<TblUser> listUsers = service.getAllUsers(pageable, filterForm);
		List<UserDTO> listUserDTOs = modelMapper.map(listUsers.getContent(), new TypeToken<List<UserDTO>>() {
		}.getType());

		Page<UserDTO> userDTOPages = new PageImpl<>(listUserDTOs, pageable, listUsers.getTotalElements());
		return userDTOPages;
	}
	
	// Get All Users
	
	@PostMapping()
	public void createUser(@RequestBody UserCreateFormBasic userform) {
		service.createUserBasic(userform);
	}
	
	@GetMapping("/{id}")
	public UserDTO getUserById(@PathVariable(name = "id") int idInput)
	{
		TblUser user = service.getUserById(idInput);
		
		UserDTO userDTO = new UserDTO();
		userDTO.setId(idInput);
		userDTO.setLoginName(user.getLoginName());
		userDTO.setGroupName(user.getGroup().getGroupName());
		userDTO.setFullName(user.getFullName());
		userDTO.setFullNameKana(user.getFullNameKana());
		userDTO.setBirthday(user.getBirthday());
		userDTO.setEmail(user.getEmail());
		userDTO.setTel(user.getTel());
		
		if (user.getTblDetailUserJapan() != null || user.getTblDetailUserJapan().size() > 0)
		{
			List<TblDetailUserJapanDTO> detailJapan = modelMapper.map(user.getTblDetailUserJapan(), new TypeToken<List<TblDetailUserJapanDTO>>() {}.getType());
			userDTO.setTblDetailUserJapan(detailJapan);
		}
		return userDTO;
	}
}

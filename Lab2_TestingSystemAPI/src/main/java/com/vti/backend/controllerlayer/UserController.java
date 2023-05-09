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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.DTO.UserDTO;
import com.vti.backend.servicelayer.IUserService;
import com.vti.entity.TblUser;
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
		List<UserDTO> listUserDTOs = modelMapper.map(listUsers.getContent(), new TypeToken<List<UserDTO>>(){}.getType());

		Page<UserDTO> userDTOPages = new PageImpl<>(listUserDTOs, pageable, listUsers.getTotalElements());
		return userDTOPages;
}
	}

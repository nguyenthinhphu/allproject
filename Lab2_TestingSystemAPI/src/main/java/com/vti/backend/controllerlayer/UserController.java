package com.vti.backend.controllerlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.DTO.UserDTO;
import com.vti.backend.servicelayer.IUserService;
import com.vti.entity.TblDetailUserJapan;
import com.vti.entity.TblUser;

@RestController
@RequestMapping(value = "api/v1/users")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private IUserService service;

	// Get All Account
	@GetMapping()
	public ResponseEntity<?> getAllUsers(Pageable pageable, @RequestParam(required = false) String search) {
		Page<TblUser> listUsers = service.getAllUsers(pageable, search);
		Page<UserDTO> listUserDTOs = listUsers.map(new Function<TblUser, UserDTO>() {
			@Override
			public UserDTO apply(TblUser user) {
				UserDTO userDTO = new UserDTO();
				userDTO.setId(user.getUserId());
				userDTO.setLoginName(user.getLoginName());
				userDTO.setFullName(user.getFullName());
				userDTO.setFullNameKana(user.getFullNameKana());
				userDTO.setEmail(user.getEmail());
				userDTO.setTel(user.getTel());
				userDTO.setGroupName(user.getGroup().getGroupName());
				userDTO.setBirthday(user.getBirthday());
				userDTO.setDetailJapan(user.getDetailUserJapan());
				return userDTO;
			}
		});

		return new ResponseEntity<>(listUserDTOs, HttpStatus.OK);
}
	}

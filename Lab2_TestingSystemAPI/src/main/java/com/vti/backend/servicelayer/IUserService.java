package com.vti.backend.servicelayer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.entity.TblUser;

public interface IUserService {
	
	public Page<TblUser> getAllUsers(Pageable pageable, String search);

}

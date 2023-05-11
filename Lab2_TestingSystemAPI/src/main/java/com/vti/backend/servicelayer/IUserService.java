package com.vti.backend.servicelayer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.entity.TblUser;
import com.vti.form.UserCreateFormBasic;
import com.vti.form.UserFilterForm;

public interface IUserService {
	
	public Page<TblUser> getAllUsers(Pageable pageable, UserFilterForm filterForm);
	
	public void createUserBasic(UserCreateFormBasic form);

}

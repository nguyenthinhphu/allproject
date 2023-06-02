package com.vti.backend.servicelayer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.vti.entity.TblUser;
import com.vti.form.UserCreateFormBasic;
import com.vti.form.UserFilterForm;
import com.vti.form.UserFormForUpdate;

public interface IUserService extends UserDetailsService {
	
	public Page<TblUser> getAllUsers(Pageable pageable, UserFilterForm filterForm);
	
	public TblUser getUserById(int idInput);
	public void createUserBasic(UserCreateFormBasic form);
	public void updateUser(int id, UserFormForUpdate userForm);

}

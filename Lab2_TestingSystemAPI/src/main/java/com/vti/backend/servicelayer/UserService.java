package com.vti.backend.servicelayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vti.backend.datalayer.IUserRepository;
import com.vti.entity.TblUser;
import com.vti.form.UserFilterForm;
import com.vti.specification.UserSpecification;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserRepository userRepository;
	@Override
	public Page<TblUser> getAllUsers(Pageable pageable, UserFilterForm filterForm) {
		
		Specification<TblUser> where = UserSpecification.buildWhere(filterForm);

		return userRepository.findAll(where, pageable);
	}

}

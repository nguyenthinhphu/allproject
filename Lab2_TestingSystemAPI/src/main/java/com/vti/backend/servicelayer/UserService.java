package com.vti.backend.servicelayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vti.backend.datalayer.IUserRepository;
import com.vti.entity.TblUser;
import com.vti.specification.UserSpecification;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserRepository userRepository;
	@Override
	public Page<TblUser> getAllUsers(Pageable pageable, String search) {
		Specification<TblUser> whereUser = null;

		if (search!= null && !search.isEmpty()) {
			UserSpecification fullNameSpecification = new UserSpecification("fullName", "LIKE", search);
			UserSpecification groupSpecification = new UserSpecification("group", "=", search);

			whereUser = Specification.where(fullNameSpecification)
					.or(groupSpecification);
		}

		return userRepository.findAll(whereUser, pageable);
	}

}

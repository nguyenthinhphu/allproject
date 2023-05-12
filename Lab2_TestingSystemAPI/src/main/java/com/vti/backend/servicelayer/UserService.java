package com.vti.backend.servicelayer;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vti.backend.datalayer.IDetailUserJapanRepository;
import com.vti.backend.datalayer.IUserRepository;
import com.vti.entity.TblDetailUserJapan;
import com.vti.entity.TblUser;
import com.vti.form.UserCreateFormBasic;
import com.vti.form.UserFilterForm;
import com.vti.specification.UserSpecification;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IDetailUserJapanRepository detailJapanRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Page<TblUser> getAllUsers(Pageable pageable, UserFilterForm filterForm) {

		Specification<TblUser> where = UserSpecification.buildWhere(filterForm);

		return userRepository.findAll(where, pageable);
	}

	@Override
	@Transactional
	public void createUserBasic(UserCreateFormBasic form) {

		// omit id field
		TypeMap<UserCreateFormBasic, TblUser> typeMap = modelMapper.getTypeMap(UserCreateFormBasic.class,
				TblUser.class);
		if (typeMap == null) { // if not already added
			// skip field
			modelMapper.addMappings(new PropertyMap<UserCreateFormBasic, TblUser>() {
				@Override
				protected void configure() {
					skip(destination.getUserId());
				}
			});
		}

		if (form.getCodeLevel() != null)
		{
			// omit id field
			TypeMap<UserCreateFormBasic, TblDetailUserJapan> typeMapDetailJapan = modelMapper.getTypeMap(UserCreateFormBasic.class,
					TblDetailUserJapan.class);
			if (typeMap == null) { // if not already added
				// skip field
				modelMapper.addMappings(new PropertyMap<UserCreateFormBasic, TblDetailUserJapan>() {
					@Override
					protected void configure() {
						skip(destination.getDetailUserJapanId());
					}
				});
			}
		}
			// convert form to entity
			TblUser account = modelMapper.map(form, TblUser.class);
			userRepository.save(account);
			
			if (form.getCodeLevel() != null)
			{
				// convert form to entity
				TblDetailUserJapan detaiJapan = modelMapper.map(form, TblDetailUserJapan.class);
				detaiJapan.setUserId(account);
				detailJapanRepository.save(detaiJapan);
			}
	}
}

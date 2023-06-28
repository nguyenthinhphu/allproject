package com.vti.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vti.backend.servicelayer.IGroupService;

public class GroupNameNotExistsValidator implements ConstraintValidator<GroupNameNotExists, String> {

	@Autowired
	private IGroupService service;

	@SuppressWarnings("deprecation")
	@Override
	public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {

		if (StringUtils.isEmpty(name)) {
			return true;
		}

		return !service.isGroupExistsByName(name);
	}
}
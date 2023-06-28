package com.vti.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupFormForCreating {

	@NotBlank(message = "Name of Group not be null value")
	@Length(max = 255, message = "Max of name is 255 characters")
	private String name;

}

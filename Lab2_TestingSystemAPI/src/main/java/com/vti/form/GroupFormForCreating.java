package com.vti.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.vti.validation.GroupNameNotExists;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupFormForCreating {

	@NotBlank(message = "{Group.createGroup.form.name.NotBlank}")
	@Length(max = 255, message = "{Group.createGroup.form.name.Length}")
	@GroupNameNotExists(message = "{Group.createGroup.form.name.NotExists}")
	private String name;

}

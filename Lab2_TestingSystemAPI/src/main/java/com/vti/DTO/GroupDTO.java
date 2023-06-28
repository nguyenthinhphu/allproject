package com.vti.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupDTO {

	@JsonProperty(value = "groupId", access = Access.READ_ONLY)
	private int id;

	private String groupName;
	
	
}

package com.vti.backend.controllerlayer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.DTO.GroupDTO;
import com.vti.backend.servicelayer.IGroupService;
import com.vti.entity.MstGroup;
import com.vti.form.GroupFormForCreating;
import com.vti.form.GroupFormForUpdate;

@RestController
@RequestMapping(value = "api/v1/groups")
@CrossOrigin("*")
@Validated
public class GroupController {

	@Autowired
	private IGroupService groupService;

	// Get all Department
	@GetMapping()
	public ResponseEntity<?> getAllDepartment() {
		List<MstGroup> listGroups = groupService.getAllGroup();

		List<GroupDTO> groupDTOs = new ArrayList<>();

		for (MstGroup group : listGroups) {
			GroupDTO groupDTO = new GroupDTO();
			groupDTO.setId(group.getGroupId());
			groupDTO.setGroupName(group.getGroupName());
			groupDTOs.add(groupDTO);
		}

		return new ResponseEntity<>(groupDTOs, HttpStatus.OK);
	}

	// Get Department By Id
	@GetMapping("/{id}")
	public ResponseEntity<?> getGroupById(@PathVariable(name = "id") int idInput) {
		MstGroup group = groupService.getGroupById(idInput);

		GroupDTO groupDTO = new GroupDTO();
		groupDTO.setId(group.getGroupId());
		groupDTO.setGroupName(group.getGroupName());

		return new ResponseEntity<>(groupDTO, HttpStatus.OK);
	}

	// Create Department
	@PostMapping()
	public ResponseEntity<?> createGroup(@RequestBody @Valid GroupFormForCreating form) {
		groupService.createGroup(form);
		return new ResponseEntity<String>("Create successfully!", HttpStatus.CREATED);
	}

	// Update Department
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateDepartmentById(@PathVariable(name = "id") int id,
			@RequestBody GroupFormForUpdate form) {
		groupService.updateGroupById(id, form);
		return new ResponseEntity<String>("Update successfully!", HttpStatus.OK);
	}

	// Get Department By Name
	@GetMapping("/name/{name}")
	public ResponseEntity<?> getDepartmentByName(@PathVariable(name = "name") String nameDep) {
		MstGroup group = groupService.getGroupByName(nameDep);

		GroupDTO groupDTO = new GroupDTO();
		groupDTO.setId(group.getGroupId());
		groupDTO.setGroupName(group.getGroupName());

		return new ResponseEntity<>(groupDTO, HttpStatus.OK);
	}

	@GetMapping("/exception")
	public void exceptionHandle() throws Exception {
		// ... Other Logic
		throw new EntityNotFoundException("..... Infomation of Exception!!!!");

		// ... Other Code
	}

}

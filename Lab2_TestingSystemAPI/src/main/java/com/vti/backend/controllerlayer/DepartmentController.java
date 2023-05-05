package com.vti.backend.controllerlayer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.DTO.DepartmentDTO;
import com.vti.backend.servicelayer.IDepartmentService;
import com.vti.entity.Department;
import com.vti.form.DepartmentFormForCreating;
import com.vti.form.DepartmentFormForUpdate;

@RestController
@RequestMapping(value = "api/v1/departments")
@CrossOrigin("*")
public class DepartmentController {

	@Autowired
	private IDepartmentService departmentService;

	// Get all Department
	@GetMapping()
	public ResponseEntity<?> getAllDepartment() {
		List<Department> listDepartments = departmentService.getAllDepartment();

		List<DepartmentDTO> departmentDTOs = new ArrayList<>();

		for (Department department : listDepartments) {
			DepartmentDTO departmentDTO = new DepartmentDTO();
			departmentDTO.setId(department.getId());
			departmentDTO.setName(department.getName());
			departmentDTOs.add(departmentDTO);
		}

		return new ResponseEntity<>(departmentDTOs, HttpStatus.OK);
	}

	// Get Department By Id
	@GetMapping("/{id}")
	public ResponseEntity<?> getDepartmentById(@PathVariable(name = "id") int idInput) {
		Department department = departmentService.getDepartmentById(idInput);

		DepartmentDTO departmentDTO = new DepartmentDTO();
		departmentDTO.setId(department.getId());
		departmentDTO.setName(department.getName());

		return new ResponseEntity<>(departmentDTO, HttpStatus.OK);
	}

	// Create Department
	@PostMapping()
	public ResponseEntity<?> createDepartment(@RequestBody DepartmentFormForCreating form) {
		departmentService.createDepartment(form);
		return new ResponseEntity<String>("Create successfully!", HttpStatus.CREATED);
	}

	// Update Department
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateDepartmentById(@PathVariable(name = "id") int id,
			@RequestBody DepartmentFormForUpdate form) {
		departmentService.updateDepartmentById(id, form);
		return new ResponseEntity<String>("Update successfully!", HttpStatus.OK);
	}

	// Get Department By Name
	@GetMapping("/name/{name}")
	public ResponseEntity<?> getDepartmentByName(@PathVariable(name = "name") String nameDep) {
		Department department = departmentService.getDepartmentByName(nameDep);

		DepartmentDTO departmentDTO = new DepartmentDTO();
		departmentDTO.setId(department.getId());
		departmentDTO.setName(department.getName());

		return new ResponseEntity<>(departmentDTO, HttpStatus.OK);
	}

}

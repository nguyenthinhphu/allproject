package com.vti.backend.servicelayer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.backend.datalayer.IDepartmentRepository;
import com.vti.entity.Department;
import com.vti.form.DepartmentFormForCreating;
import com.vti.form.DepartmentFormForUpdate;

@Service
public class DepartmentService implements IDepartmentService {

	@Autowired
	private IDepartmentRepository departmentRepository;

	@Override
	public List<Department> getAllDepartment() {
		// TODO Auto-generated method stub
		return departmentRepository.findAll();
	}

	@Override
	public Department getDepartmentById(int idInput) {

		return departmentRepository.findById(idInput).get();
	}

	@Override
	public void createDepartment(DepartmentFormForCreating form) {
		Department department = new Department(form.getName());

		departmentRepository.save(department);
	}

	@Override
	public void updateDepartmentById(int id, DepartmentFormForUpdate form) {
		Department department = getDepartmentById(id);
		department.setName(form.getName());

		departmentRepository.save(department);
	}

	@Override
	public Department getDepartmentByName(String nameDep) {

		return departmentRepository.findByName(nameDep);
	}

}

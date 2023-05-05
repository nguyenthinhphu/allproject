package com.vti.backend.servicelayer;

import java.util.List;

import com.vti.entity.Department;
import com.vti.form.DepartmentFormForCreating;
import com.vti.form.DepartmentFormForUpdate;

public interface IDepartmentService {
	public List<Department> getAllDepartment();

	public Department getDepartmentById(int idInput);

	public void createDepartment(DepartmentFormForCreating form);

	public void updateDepartmentById(int id, DepartmentFormForUpdate form);

	public Department getDepartmentByName(String nameDep);
}

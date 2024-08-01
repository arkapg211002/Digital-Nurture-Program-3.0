package com.example.EmployeeManagementSystem.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.EmployeeManagementSystem.dto.DepartmentNameDto;
import com.example.EmployeeManagementSystem.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	List<Department> findByNameContaining(String name);
//	List<DepartmentNameDto> findAllDepartmentNameDto();
}

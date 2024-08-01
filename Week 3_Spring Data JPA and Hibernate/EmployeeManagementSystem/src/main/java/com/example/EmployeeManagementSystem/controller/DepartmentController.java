package com.example.EmployeeManagementSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EmployeeManagementSystem.model.Department;
import com.example.EmployeeManagementSystem.repo.DepartmentRepository;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

	@Autowired
	private DepartmentRepository departmentRepository;

	@PostMapping("/addDepartment")
	public Department addDepartment(@RequestBody Department department) {
		return departmentRepository.save(department);
	}

	@GetMapping("/getAllDepartments")
	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}

	@GetMapping("/getDepartment/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
		Optional<Department> department = departmentRepository.findById(id);
		return department.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/updateDepartment/{id}")
	public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department dpt) {
		try {
			Optional<Department> dptData = departmentRepository.findById(id);
			if (dptData.isPresent()) {
				Department updatedDptData = dptData.get();
				updatedDptData.setName(dpt.getName());

				Department dptObj = departmentRepository.save(updatedDptData);
				return new ResponseEntity<>(dptObj, HttpStatus.CREATED);
			}

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/deleteDepartment/{id}")
	public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
		try {
			departmentRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

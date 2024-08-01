package com.example.EmployeeManagementSystem.controller;

import java.awt.print.Pageable;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.PageRequest;

import com.example.EmployeeManagementSystem.dto.EmployeeNameEmailDto;
import com.example.EmployeeManagementSystem.model.Employee;
import com.example.EmployeeManagementSystem.repo.EmployeeRepository;
import com.example.EmployeeManagementSystem.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/getAllEmployees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		try {
			List<Employee> empList = new ArrayList<>();
			employeeRepository.findAll().forEach(empList::add);

			if (empList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(empList, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getEmployeeById/{id}")
	public ResponseEntity<Employee> getBookById(@PathVariable Long id) {
		Optional<Employee> empObj = employeeRepository.findById(id);
		if (empObj.isPresent()) {
			return new ResponseEntity<>(empObj.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/addEmployee")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee book) {
		try {
			Employee empObj = employeeRepository.save(book);
			return new ResponseEntity<>(empObj, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<Employee> updateBook(@PathVariable Long id, @RequestBody Employee emp) {
		try {
			Optional<Employee> empData = employeeRepository.findById(id);
			if (empData.isPresent()) {
				Employee updatedEmpData = empData.get();
				updatedEmpData.setName(emp.getName());
				updatedEmpData.setEmail(emp.getEmail());
				updatedEmpData.setDepartment(emp.getDepartment());

				Employee empObj = employeeRepository.save(updatedEmpData);
				return new ResponseEntity<>(empObj, HttpStatus.CREATED);
			}

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/deleteEmpById/{id}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id) {
		try {
			employeeRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/department/{departmentId}")
    public List<Employee> getEmployeesByDepartment(@PathVariable Long departmentId) {
        return employeeRepository.findByDepartment(departmentId);
    }

	


	@PostMapping("/batch")
    public ResponseEntity<Void> saveAllEmployees(@RequestBody List<Employee> employees) {
        employeeService.saveAllEmployees(employees);
        return ResponseEntity.ok().build();
    }
	
}
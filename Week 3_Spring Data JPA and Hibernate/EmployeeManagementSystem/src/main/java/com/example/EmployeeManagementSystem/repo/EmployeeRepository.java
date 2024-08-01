package com.example.EmployeeManagementSystem.repo;

import java.awt.print.Pageable;

import java.util.List;

import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.EmployeeManagementSystem.dto.EmployeeNameEmailDto;
import com.example.EmployeeManagementSystem.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByEmail(String email);
	
	@Query("SELECT e FROM Employee e WHERE e.email = :email")
    Employee findByEmailAddress(@Param("email") String email);
	
	@Query(name = "Employee.findByDepartment")
    List<Employee> findByDepartment(@Param("departmentId") Long departmentId);

    @Query(name = "Employee.findByNamePattern")
    List<Employee> findByNamePattern(@Param("namePattern") String namePattern);
    
//   List<EmployeeNameEmailDto> findAllEmployeeNameEmailDto();

}
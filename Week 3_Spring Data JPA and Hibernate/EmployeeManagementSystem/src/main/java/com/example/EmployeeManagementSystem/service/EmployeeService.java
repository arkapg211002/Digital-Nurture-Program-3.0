package com.example.EmployeeManagementSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.EmployeeManagementSystem.model.Employee;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

public class EmployeeService {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void saveAllEmployees(List<Employee> employees) {
        int batchSize = 20;
        for (int i = 0; i < employees.size(); i++) {
            entityManager.persist(employees.get(i));
            if (i % batchSize == 0 && i > 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
    }

}

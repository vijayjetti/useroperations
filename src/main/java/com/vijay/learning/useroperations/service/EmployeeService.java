package com.vijay.learning.useroperations.service;

import com.vijay.learning.useroperations.model.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployee(Long id);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Long id, Employee updatedEmployee);

    Optional<Employee> isExistingEmployee(Long id);

    void deleteEmployee(Long id);
}
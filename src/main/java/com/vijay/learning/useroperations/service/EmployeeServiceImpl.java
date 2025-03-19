package com.vijay.learning.useroperations.service;

import com.vijay.learning.useroperations.exception.ResourceNotFoundException;
import com.vijay.learning.useroperations.model.Employee;
import com.vijay.learning.useroperations.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee getEmployee(Long id) {
        log.debug("Fetching employee with id: {}", id);
        return employeeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
    }

    @Override
    public List<Employee> getAllEmployees() {
        log.debug("Fetching all employees");
        return employeeRepository.findAll();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        log.debug("Creating new employee: {}", employee);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        log.debug("Updating employee with id: {}", id);
        Employee existingEmployee = employeeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        
        existingEmployee.setFirstName(updatedEmployee.getFirstName());
        existingEmployee.setLastName(updatedEmployee.getLastName());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setDepartment(updatedEmployee.getDepartment());
        
        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        log.debug("Deleting employee with id: {}", id);
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee", "id", id);
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public Optional<Employee> isExistingEmployee(Long id) {
        log.debug("Checking if employee exists with id: {}", id);
        return employeeRepository.findById(id);
    }
}
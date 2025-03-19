package com.vijay.learning.useroperations.service;

import com.vijay.learning.useroperations.model.Employee;
import com.vijay.learning.useroperations.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getEmployee_ShouldReturnEmployee_WhenEmployeeExists() {
        Employee employee = Employee.builder().id(1L).name("John Smith").department("IT").build();
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployee(1L);

        assertNotNull(result);
        assertEquals("John Smith", result.getName());
        assertEquals("IT", result.getDepartment());
    }

    @Test
    void getAllEmployees_ShouldReturnListOfEmployees() {
        List<Employee> employees = Arrays.asList(
            Employee.builder().id(1L).name("John Smith").department("IT").build(),
            Employee.builder().id(2L).name("Jane Smith").department("HR").build()
        );
        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();

        assertEquals(2, result.size());
        assertEquals("John Smith", result.get(0).getName());
        assertEquals("Jane Smith", result.get(1).getName());
    }

    @Test
    void createEmployee_ShouldReturnCreatedEmployee() {
        Employee employeeToCreate = Employee.builder().name("John Smith").department("IT").build();
        Employee savedEmployee = Employee.builder().id(1L).name("John Smith").department("IT").build();
        when(employeeRepository.save(any(Employee.class))).thenReturn(savedEmployee);

        Employee result = employeeService.createEmployee(employeeToCreate);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Smith", result.getName());
        assertEquals("IT", result.getDepartment());
    }

    @Test
    void updateEmployee_ShouldUpdateExistingEmployee() {
        Employee existingEmployee = Employee.builder().id(1L).name("John Smith").department("IT").build();
        Employee updatedEmployee = Employee.builder().id(1L).name("John Updated").department("Finance").build();
        
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

        Employee result = employeeService.updateEmployee(1L, updatedEmployee);

        assertNotNull(result);
        assertEquals("John Updated", result.getName());
        assertEquals("Finance", result.getDepartment());
    }

    @Test
    void deleteEmployee_ShouldDeleteExistingEmployee() {
        doNothing().when(employeeRepository).deleteById(1L);

        employeeService.deleteEmployee(1L);

        verify(employeeRepository, times(1)).deleteById(1L);
    }

    @Test
    void isExistingEmployee_ShouldReturnTrue_WhenEmployeeExists() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(new Employee()));

        Optional<Employee> result = employeeService.isExistingEmployee(1L);

        assertTrue(result.isPresent());
    }

    @Test
    void isExistingEmployee_ShouldReturnFalse_WhenEmployeeDoesNotExist() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Employee> result = employeeService.isExistingEmployee(1L);

        assertFalse(result.isPresent());
    }
}
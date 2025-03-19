package com.vijay.learning.useroperations.controller;

import com.vijay.learning.useroperations.model.Employee;
import com.vijay.learning.useroperations.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class EmployeeControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void createEmployee_ShouldCreateNewEmployee() throws Exception {
        Employee employee = Employee.builder().id(1L).firstName("John").lastName("Smith").department("IT").build();
        when(employeeService.createEmployee(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(post("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk());

        verify(employeeService, times(1)).createEmployee(any(Employee.class));
    }

    @Test
    void getEmployee_ShouldReturnEmployee() throws Exception {
        Employee employee = Employee.builder().id(1L).firstName("John").lastName("Smith").department("IT").build();
        when(employeeService.getEmployee(1L)).thenReturn(employee);

        mockMvc.perform(get("/api/v1/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Smith"))
                .andExpect(jsonPath("$.department").value("IT"));
    }

    @Test
    void getAllEmployees_ShouldReturnListOfEmployees() throws Exception {
        Employee employee1 = Employee.builder().id(1L).firstName("John").lastName("Smith").department("IT").build();
        Employee employee2 = Employee.builder().id(2L).firstName("John").lastName("Smith").department("HR").build();
        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employee1, employee2));

        mockMvc.perform(get("/api/v1/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Smith"))
                .andExpect(jsonPath("$[1].name").value("Jane Smith"));
    }

    @Test
    void updateEmployee_ShouldUpdateExistingEmployee() throws Exception {
        Employee updatedEmployee = Employee.builder().id(1L).firstName("John").lastName("Updated").department("Finance").build();
        when(employeeService.updateEmployee(eq(1L), any(Employee.class))).thenReturn(updatedEmployee);

        mockMvc.perform(put("/api/v1/employees/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Updated"))
                .andExpect(jsonPath("$.department").value("Finance"));
    }

    @Test
    void deleteEmployee_ShouldDeleteEmployee() throws Exception {
        doNothing().when(employeeService).deleteEmployee(1L);

        mockMvc.perform(delete("/api/v1/employees/1"))
                .andExpect(status().isOk());

        verify(employeeService, times(1)).deleteEmployee(1L);
    }

    @Test
    void isExistingEmployee_ShouldReturnTrueForExistingEmployee() throws Exception {
        when(employeeService.isExistingEmployee(1L)).thenReturn(Optional.of(new Employee()));

        mockMvc.perform(get("/api/v1/employees/1/exists"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}
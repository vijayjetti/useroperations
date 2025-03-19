package com.vijay.learning.useroperations.controller;

import com.vijay.learning.useroperations.model.Employee;
import com.vijay.learning.useroperations.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.vijay.learning.useroperations.constants.ApiEndPoints.*;

@RestController
@RequestMapping(value = BASE_URL)
@Slf4j
@Validated
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = ADD_EMPLOYEE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@Valid @RequestBody Employee employee) {
        employeeService.createEmployee(employee);
    }

    @GetMapping(value = GET_EMPLOYEE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Employee> getEmployee(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    @PutMapping(value = UPDATE_EMPLOYEE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Employee> updateEmployee(@PathVariable @NotNull Long id, @Valid @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
    }

    @DeleteMapping(value = DELETE_EMPLOYEE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable @NotNull Long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping(value = GET_EMPLOYEE_LIST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping(value = CHECK_EXISTING_EMPLOYEE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> isExistingEmployee(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(employeeService.isExistingEmployee(id).isPresent());
    }
}
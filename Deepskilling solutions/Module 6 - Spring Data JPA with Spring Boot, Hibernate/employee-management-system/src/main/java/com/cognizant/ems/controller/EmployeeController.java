package com.cognizant.ems.controller;

import com.cognizant.ems.entity.Employee;
import com.cognizant.ems.projection.EmployeeProjection;
import com.cognizant.ems.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Employee Controller - REST endpoints for employee operations
 * Supports CRUD, filtering, pagination, sorting, and projections
 */
@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    /**
     * Create a new employee
     */
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        LOGGER.info("Creating new employee");
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    /**
     * Get all employees
     */
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        LOGGER.info("Fetching all employees");
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    /**
     * Get employee by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        LOGGER.info("Fetching employee with ID: {}", id);
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Update an employee
     */
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, 
                                                    @RequestBody Employee employee) {
        LOGGER.info("Updating employee with ID: {}", id);
        employee.setEmpId(id);
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    /**
     * Delete an employee
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        LOGGER.info("Deleting employee with ID: {}", id);
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Exercise 6: Get employees with pagination and sorting
     */
    @GetMapping("/pagination")
    public ResponseEntity<Page<Employee>> getEmployeesWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "empId") String sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction) {
        LOGGER.info("Fetching employees with pagination: page={}, size={}", page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<Employee> employees = employeeService.getAllEmployeesWithPagination(pageable);
        return ResponseEntity.ok(employees);
    }

    /**
     * Exercise 6: Get employees by department with pagination
     */
    @GetMapping("/department/{deptId}/pagination")
    public ResponseEntity<Page<Employee>> getEmployeesByDepartmentWithPagination(
            @PathVariable Integer deptId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        LOGGER.info("Fetching employees for department {} with pagination", deptId);
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employees = employeeService.getEmployeesByDepartmentWithPagination(deptId, pageable);
        return ResponseEntity.ok(employees);
    }

    /**
     * Exercise 5: Get employees by department
     */
    @GetMapping("/department/{deptId}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable Integer deptId) {
        LOGGER.info("Fetching employees for department: {}", deptId);
        List<Employee> employees = employeeService.findByDepartmentId(deptId);
        return ResponseEntity.ok(employees);
    }

    /**
     * Exercise 5: Get employees by designation
     */
    @GetMapping("/designation/{designation}")
    public ResponseEntity<List<Employee>> getByDesignation(@PathVariable String designation) {
        LOGGER.info("Fetching employees with designation: {}", designation);
        List<Employee> employees = employeeService.findByDesignation(designation);
        return ResponseEntity.ok(employees);
    }

    /**
     * Exercise 5: Get active employees
     */
    @GetMapping("/active")
    public ResponseEntity<List<Employee>> getActiveEmployees() {
        LOGGER.info("Fetching active employees");
        List<Employee> employees = employeeService.findActiveEmployees();
        return ResponseEntity.ok(employees);
    }

    /**
     * Exercise 5: Find employee by email
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<Employee> getByEmail(@PathVariable String email) {
        LOGGER.info("Searching employee by email: {}", email);
        return employeeService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Exercise 5: Search employees by name pattern
     */
    @GetMapping("/search/{name}")
    public ResponseEntity<List<Employee>> searchByName(@PathVariable String name) {
        LOGGER.info("Searching employees by name: {}", name);
        List<Employee> employees = employeeService.searchByName(name);
        return ResponseEntity.ok(employees);
    }

    /**
     * Exercise 6: Search employees by name with pagination
     */
    @GetMapping("/search/{name}/pagination")
    public ResponseEntity<Page<Employee>> searchByNameWithPagination(
            @PathVariable String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        LOGGER.info("Searching employees by name '{}' with pagination", name);
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employees = employeeService.searchByNameWithPagination(name, pageable);
        return ResponseEntity.ok(employees);
    }

    /**
     * Exercise 5: Find employees by salary range
     */
    @GetMapping("/salary-range")
    public ResponseEntity<List<Employee>> findBySalaryRange(
            @RequestParam Double minSalary,
            @RequestParam Double maxSalary) {
        LOGGER.info("Searching employees with salary range: {} - {}", minSalary, maxSalary);
        List<Employee> employees = employeeService.findBySalaryRange(minSalary, maxSalary);
        return ResponseEntity.ok(employees);
    }

    /**
     * Exercise 5: Find employees with higher salary
     */
    @GetMapping("/high-salary")
    public ResponseEntity<List<Employee>> findWithHigherSalary(@RequestParam Double salary) {
        LOGGER.info("Searching employees with salary higher than: {}", salary);
        List<Employee> employees = employeeService.findWithHigherSalary(salary);
        return ResponseEntity.ok(employees);
    }

    /**
     * Exercise 5: Find employees joined after date
     */
    @GetMapping("/joined-after")
    public ResponseEntity<List<Employee>> findJoinedAfter(@RequestParam String date) {
        LOGGER.info("Searching employees joined after: {}", date);
        List<Employee> employees = employeeService.findJoinedAfter(LocalDate.parse(date));
        return ResponseEntity.ok(employees);
    }

    /**
     * Exercise 8: Get employees as projections (optimized queries)
     */
    @GetMapping("/projections")
    public ResponseEntity<List<EmployeeProjection>> getAllEmployeesAsProjection() {
        LOGGER.info("Fetching all employees as projections");
        List<EmployeeProjection> projections = employeeService.getAllEmployeesAsProjection();
        return ResponseEntity.ok(projections);
    }

    /**
     * Exercise 8: Get employees as projections with pagination
     */
    @GetMapping("/projections/pagination")
    public ResponseEntity<Page<EmployeeProjection>> getAllEmployeesAsProjectionWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        LOGGER.info("Fetching employees as projections with pagination");
        Pageable pageable = PageRequest.of(page, size);
        Page<EmployeeProjection> projections = employeeService.getAllEmployeesAsProjectionWithPagination(pageable);
        return ResponseEntity.ok(projections);
    }

    /**
     * Exercise 10: Get total salary by department
     */
    @GetMapping("/salary-by-department")
    public ResponseEntity<List<Object[]>> getTotalSalaryByDepartment() {
        LOGGER.info("Fetching total salary by department");
        List<Object[]> results = employeeService.getTotalSalaryByDepartment();
        return ResponseEntity.ok(results);
    }

    /**
     * Exercise 10: Count employees in a department
     */
    @GetMapping("/count/department/{deptId}")
    public ResponseEntity<Long> countEmployeesInDepartment(@PathVariable Integer deptId) {
        LOGGER.info("Counting employees in department: {}", deptId);
        long count = employeeService.countEmployeesInDepartment(deptId);
        return ResponseEntity.ok(count);
    }
}

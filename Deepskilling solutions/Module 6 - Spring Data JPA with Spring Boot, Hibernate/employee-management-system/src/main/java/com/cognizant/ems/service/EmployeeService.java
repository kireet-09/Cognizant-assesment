package com.cognizant.ems.service;

import com.cognizant.ems.entity.Employee;
import com.cognizant.ems.projection.EmployeeProjection;
import com.cognizant.ems.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Exercise 4: Employee Service - Implementing CRUD operations
 * Exercise 6: Pagination and Sorting support
 * Exercise 8: Projections for optimized data retrieval
 */
@Service
@Transactional
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Exercise 4: Create a new employee
     */
    public Employee saveEmployee(Employee employee) {
        LOGGER.info("Saving employee: {}", employee.getEmpName());
        return employeeRepository.save(employee);
    }

    /**
     * Exercise 4: Read all employees
     */
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        LOGGER.info("Fetching all employees");
        return employeeRepository.findAll();
    }

    /**
     * Exercise 4: Read an employee by ID
     */
    @Transactional(readOnly = true)
    public Optional<Employee> getEmployeeById(Integer empId) {
        LOGGER.info("Fetching employee with ID: {}", empId);
        return employeeRepository.findById(empId);
    }

    /**
     * Exercise 4: Update an employee
     */
    public Employee updateEmployee(Employee employee) {
        LOGGER.info("Updating employee: {}", employee.getEmpName());
        return employeeRepository.save(employee);
    }

    /**
     * Exercise 4: Delete an employee
     */
    public void deleteEmployee(Integer empId) {
        LOGGER.info("Deleting employee with ID: {}", empId);
        employeeRepository.deleteById(empId);
    }

    /**
     * Exercise 5: Find employee by email
     */
    @Transactional(readOnly = true)
    public Optional<Employee> findByEmail(String email) {
        LOGGER.info("Searching employee by email: {}", email);
        return employeeRepository.findByEmail(email);
    }

    /**
     * Exercise 5: Find all employees in a department
     */
    @Transactional(readOnly = true)
    public List<Employee> findByDepartmentId(Integer deptId) {
        LOGGER.info("Fetching employees for department ID: {}", deptId);
        return employeeRepository.findByDepartmentDeptId(deptId);
    }

    /**
     * Exercise 5: Find employees by designation
     */
    @Transactional(readOnly = true)
    public List<Employee> findByDesignation(String designation) {
        LOGGER.info("Searching employees by designation: {}", designation);
        return employeeRepository.findByDesignation(designation);
    }

    /**
     * Exercise 5: Find all active employees
     */
    @Transactional(readOnly = true)
    public List<Employee> findActiveEmployees() {
        LOGGER.info("Fetching all active employees");
        return employeeRepository.findByIsActiveTrue();
    }

    /**
     * Exercise 5: Find employees by salary range
     */
    @Transactional(readOnly = true)
    public List<Employee> findBySalaryRange(Double minSalary, Double maxSalary) {
        LOGGER.info("Searching employees with salary range: {} - {}", minSalary, maxSalary);
        return employeeRepository.findEmployeesBySalaryRange(minSalary, maxSalary);
    }

    /**
     * Exercise 5: Search employees by name pattern
     */
    @Transactional(readOnly = true)
    public List<Employee> searchByName(String name) {
        LOGGER.info("Searching employees by name: {}", name);
        return employeeRepository.searchByName(name);
    }

    /**
     * Exercise 5: Find employees with higher salary
     */
    @Transactional(readOnly = true)
    public List<Employee> findWithHigherSalary(Double salary) {
        LOGGER.info("Searching employees with salary higher than: {}", salary);
        return employeeRepository.findEmployeesWithHigherSalary(salary);
    }

    /**
     * Exercise 5: Find employees joined after a specific date
     */
    @Transactional(readOnly = true)
    public List<Employee> findJoinedAfter(LocalDate date) {
        LOGGER.info("Searching employees joined after: {}", date);
        return employeeRepository.findByDateOfJoiningAfter(date);
    }

    /**
     * Exercise 6: Get all employees with pagination
     */
    @Transactional(readOnly = true)
    public Page<Employee> getAllEmployeesWithPagination(Pageable pageable) {
        LOGGER.info("Fetching employees with pagination: page={}, size={}", 
                    pageable.getPageNumber(), pageable.getPageSize());
        return employeeRepository.findAll(pageable);
    }

    /**
     * Exercise 6: Get employees in a department with pagination
     */
    @Transactional(readOnly = true)
    public Page<Employee> getEmployeesByDepartmentWithPagination(Integer deptId, Pageable pageable) {
        LOGGER.info("Fetching employees for department {} with pagination", deptId);
        return employeeRepository.findByDepartmentDeptId(deptId, pageable);
    }

    /**
     * Exercise 6: Search employees by name with pagination
     */
    @Transactional(readOnly = true)
    public Page<Employee> searchByNameWithPagination(String name, Pageable pageable) {
        LOGGER.info("Searching employees by name '{}' with pagination", name);
        return employeeRepository.searchByNameWithPagination(name, pageable);
    }

    /**
     * Exercise 8: Get employees as projections (optimized queries)
     */
    @Transactional(readOnly = true)
    public List<EmployeeProjection> getAllEmployeesAsProjection() {
        LOGGER.info("Fetching all employees as projections");
        return employeeRepository.findAllProjectedBy();
    }

    /**
     * Exercise 8: Get employees as projections with pagination
     */
    @Transactional(readOnly = true)
    public Page<EmployeeProjection> getAllEmployeesAsProjectionWithPagination(Pageable pageable) {
        LOGGER.info("Fetching employees as projections with pagination");
        return employeeRepository.findAllProjectedBy(pageable);
    }

    /**
     * Exercise 10: Get total salary by department
     */
    @Transactional(readOnly = true)
    public List<Object[]> getTotalSalaryByDepartment() {
        LOGGER.info("Fetching total salary by department");
        return employeeRepository.getTotalSalaryByDepartment();
    }

    /**
     * Exercise 10: Count employees in a department
     */
    @Transactional(readOnly = true)
    public long countEmployeesInDepartment(Integer deptId) {
        LOGGER.info("Counting employees in department: {}", deptId);
        return employeeRepository.countByDepartmentDeptId(deptId);
    }
}

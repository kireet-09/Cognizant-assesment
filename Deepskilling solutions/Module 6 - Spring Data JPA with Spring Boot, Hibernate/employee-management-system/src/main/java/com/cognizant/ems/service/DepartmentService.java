package com.cognizant.ems.service;

import com.cognizant.ems.entity.Department;
import com.cognizant.ems.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Exercise 4: Department Service - Implementing CRUD operations
 */
@Service
@Transactional
public class DepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentService.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * Exercise 4: Create a new department
     */
    public Department saveDepartment(Department department) {
        LOGGER.info("Saving department: {}", department.getDeptName());
        return departmentRepository.save(department);
    }

    /**
     * Exercise 4: Read all departments
     */
    @Transactional(readOnly = true)
    public List<Department> getAllDepartments() {
        LOGGER.info("Fetching all departments");
        return departmentRepository.findAll();
    }

    /**
     * Exercise 4: Read a department by ID
     */
    @Transactional(readOnly = true)
    public Department getDepartmentById(Integer deptId) {
        LOGGER.info("Fetching department with ID: {}", deptId);
        return departmentRepository.findById(deptId).orElse(null);
    }

    /**
     * Exercise 4: Update a department
     */
    public Department updateDepartment(Department department) {
        LOGGER.info("Updating department: {}", department.getDeptName());
        return departmentRepository.save(department);
    }

    /**
     * Exercise 4: Delete a department
     */
    public void deleteDepartment(Integer deptId) {
        LOGGER.info("Deleting department with ID: {}", deptId);
        departmentRepository.deleteById(deptId);
    }

    /**
     * Exercise 5: Find department by name
     */
    @Transactional(readOnly = true)
    public Optional<Department> findByDeptName(String deptName) {
        LOGGER.info("Searching department by name: {}", deptName);
        return departmentRepository.findByDeptName(deptName);
    }

    /**
     * Exercise 5: Find department by code
     */
    @Transactional(readOnly = true)
    public Optional<Department> findByDeptCode(String deptCode) {
        LOGGER.info("Searching department by code: {}", deptCode);
        return departmentRepository.findByDeptCode(deptCode);
    }

    /**
     * Exercise 5: Find departments by location
     */
    @Transactional(readOnly = true)
    public List<Department> findByLocation(String location) {
        LOGGER.info("Searching departments by location: {}", location);
        return departmentRepository.findByLocation(location);
    }

    /**
     * Exercise 5: Search departments by code pattern
     */
    @Transactional(readOnly = true)
    public List<Department> searchByDeptCode(String code) {
        LOGGER.info("Searching departments with code pattern: {}", code);
        return departmentRepository.searchByDeptCode(code);
    }
}

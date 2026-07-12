package com.cognizant.ems.controller;

import com.cognizant.ems.entity.Department;
import com.cognizant.ems.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Department Controller - REST endpoints for department operations
 */
@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "*")
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    /**
     * Create a new department
     */
    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        LOGGER.info("Creating new department");
        Department savedDepartment = departmentService.saveDepartment(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDepartment);
    }

    /**
     * Get all departments
     */
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        LOGGER.info("Fetching all departments");
        List<Department> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    /**
     * Get department by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Integer id) {
        LOGGER.info("Fetching department with ID: {}", id);
        Department department = departmentService.getDepartmentById(id);
        if (department != null) {
            return ResponseEntity.ok(department);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Update a department
     */
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Integer id, 
                                                        @RequestBody Department department) {
        LOGGER.info("Updating department with ID: {}", id);
        department.setDeptId(id);
        Department updatedDepartment = departmentService.updateDepartment(department);
        return ResponseEntity.ok(updatedDepartment);
    }

    /**
     * Delete a department
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Integer id) {
        LOGGER.info("Deleting department with ID: {}", id);
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Search department by name
     */
    @GetMapping("/search/name/{name}")
    public ResponseEntity<Department> searchByName(@PathVariable String name) {
        LOGGER.info("Searching department by name: {}", name);
        Optional<Department> department = departmentService.findByDeptName(name);
        return department.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Search department by code
     */
    @GetMapping("/search/code/{code}")
    public ResponseEntity<Department> searchByCode(@PathVariable String code) {
        LOGGER.info("Searching department by code: {}", code);
        Optional<Department> department = departmentService.findByDeptCode(code);
        return department.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Get departments by location
     */
    @GetMapping("/location/{location}")
    public ResponseEntity<List<Department>> getByLocation(@PathVariable String location) {
        LOGGER.info("Fetching departments in location: {}", location);
        List<Department> departments = departmentService.findByLocation(location);
        return ResponseEntity.ok(departments);
    }

    /**
     * Search departments by code pattern
     */
    @GetMapping("/search/pattern/{pattern}")
    public ResponseEntity<List<Department>> searchByCodePattern(@PathVariable String pattern) {
        LOGGER.info("Searching departments with code pattern: {}", pattern);
        List<Department> departments = departmentService.searchByDeptCode(pattern);
        return ResponseEntity.ok(departments);
    }
}

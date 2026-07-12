package com.cognizant.ems.projection;

/**
 * Exercise 8: Employee Projection - Fetch specific data subset
 * Returns only essential employee information without loading full entity
 */
public interface EmployeeProjection {

    Integer getEmpId();

    String getEmpName();

    String getEmail();

    String getDesignation();

    Double getSalary();

    // Nested projection for department
    DepartmentInfo getDepartment();

    interface DepartmentInfo {
        Integer getDeptId();
        String getDeptName();
    }
}

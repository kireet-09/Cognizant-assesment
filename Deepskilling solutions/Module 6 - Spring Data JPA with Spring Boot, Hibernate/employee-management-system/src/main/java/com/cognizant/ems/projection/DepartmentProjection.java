package com.cognizant.ems.projection;

/**
 * Exercise 8: Department Projection - Fetch specific data subset
 * Returns only essential department information
 */
public interface DepartmentProjection {

    Integer getDeptId();

    String getDeptName();

    String getDeptCode();

    String getLocation();

    long getEmployeeCount();
}

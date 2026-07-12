package com.cognizant.ems.repository;

import com.cognizant.ems.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Exercise 3: Department Repository with CRUD operations
 * Exercise 5: Query methods for custom searches
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    /**
     * Exercise 5: Custom Query Method - Find by department name
     */
    Optional<Department> findByDeptName(String deptName);

    /**
     * Exercise 5: Custom Query Method - Find by department code
     */
    Optional<Department> findByDeptCode(String deptCode);

    /**
     * Exercise 5: Custom Query Method - Find all departments in a location
     */
    List<Department> findByLocation(String location);

    /**
     * Exercise 5: Named Query using @Query annotation
     */
    @Query("SELECT d FROM Department d WHERE d.deptCode LIKE %:code%")
    List<Department> searchByDeptCode(@Param("code") String code);

    /**
     * Exercise 5: Check if department exists by code
     */
    boolean existsByDeptCode(String deptCode);

    /**
     * Exercise 5: Count departments by location
     */
    long countByLocation(String location);
}

package com.cognizant.ems.repository;

import com.cognizant.ems.entity.Employee;
import com.cognizant.ems.projection.EmployeeProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Exercise 3: Employee Repository with CRUD operations
 * Exercise 5: Query methods for custom searches
 * Exercise 6: Pagination and Sorting
 * Exercise 8: Projections for optimized data retrieval
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    /**
     * Exercise 5: Custom Query Method - Find by email
     */
    Optional<Employee> findByEmail(String email);

    /**
     * Exercise 5: Custom Query Method - Find all employees in a department
     */
    List<Employee> findByDepartmentDeptId(Integer deptId);

    /**
     * Exercise 5: Custom Query Method - Find employees by designation
     */
    List<Employee> findByDesignation(String designation);

    /**
     * Exercise 5: Custom Query Method - Find active employees
     */
    List<Employee> findByIsActiveTrue();

    /**
     * Exercise 5: Custom Query Method with multiple criteria
     */
    List<Employee> findByDepartmentDeptIdAndIsActiveTrue(Integer deptId);

    /**
     * Exercise 6: Pagination - Find all employees with pagination
     */
    Page<Employee> findAll(Pageable pageable);

    /**
     * Exercise 6: Pagination - Find employees by department with pagination
     */
    Page<Employee> findByDepartmentDeptId(Integer deptId, Pageable pageable);

    /**
     * Exercise 5: Named Query - Find employees by salary range
     */
    @Query("SELECT e FROM Employee e WHERE e.salary BETWEEN :minSalary AND :maxSalary")
    List<Employee> findEmployeesBySalaryRange(@Param("minSalary") Double minSalary, 
                                               @Param("maxSalary") Double maxSalary);

    /**
     * Exercise 5: Named Query - Find employees by name pattern
     */
    @Query("SELECT e FROM Employee e WHERE LOWER(e.empName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Employee> searchByName(@Param("name") String name);

    /**
     * Exercise 6: Pagination - Search employees by name with pagination
     */
    @Query("SELECT e FROM Employee e WHERE LOWER(e.empName) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Employee> searchByNameWithPagination(@Param("name") String name, Pageable pageable);

    /**
     * Exercise 5: Find employees joined after a date
     */
    List<Employee> findByDateOfJoiningAfter(LocalDate date);

    /**
     * Exercise 5: Count employees in a department
     */
    long countByDepartmentDeptId(Integer deptId);

    /**
     * Exercise 5: Find employees with salary greater than specified amount
     */
    @Query("SELECT e FROM Employee e WHERE e.salary > :salary ORDER BY e.salary DESC")
    List<Employee> findEmployeesWithHigherSalary(@Param("salary") Double salary);

    /**
     * Exercise 8: Projections - Get employee data without full entity load
     */
    List<EmployeeProjection> findAllProjectedBy();

    /**
     * Exercise 8: Projections with pagination
     */
    Page<EmployeeProjection> findAllProjectedBy(Pageable pageable);

    /**
     * Exercise 10: Native SQL Query - Custom Hibernate feature
     * Get total salary by department
     */
    @Query(value = "SELECT d.dept_name, SUM(e.salary) FROM employees e " +
                   "JOIN departments d ON e.dept_id = d.dept_id " +
                   "GROUP BY d.dept_name", nativeQuery = true)
    List<Object[]> getTotalSalaryByDepartment();
}

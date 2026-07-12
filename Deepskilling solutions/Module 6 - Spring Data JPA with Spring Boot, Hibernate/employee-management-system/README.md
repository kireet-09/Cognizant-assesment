# Employee Management System - Spring Boot with Spring Data JPA & Hibernate

## Overview
A comprehensive Employee Management System built with Spring Boot, Spring Data JPA, and Hibernate that demonstrates all 10 exercises of the module.

## Project Location
`Module 6 - Spring Data JPA with Spring Boot, Hibernate/employee-management-system/`

## Build & Run

### Build
```bash
mvn clean package
```

### Run
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### H2 Console
Access the H2 in-memory database console at: `http://localhost:8080/h2-console`
- URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: `password`

---

## Exercise Implementations

### Exercise 1: Employee Management System - Overview and Setup ✅
**Files:** `pom.xml`, `application.properties`, `EmployeeManagementSystemApplication.java`

**Key Features:**
- Spring Boot 2.7.18 with Spring Data JPA
- H2 in-memory database configuration
- Maven project structure
- Comprehensive logging configuration
- Hibernat batch processing configuration

**Application Properties:**
- Data source: H2 in-memory database
- DDL strategy: create-drop (recreates tables on each startup)
- Hibernate dialect: H2Dialect
- Batch size: 20 for optimized queries

---

### Exercise 2: Employee Management System - Creating Entities ✅
**Files:** `entity/Department.java`, `entity/Employee.java`

**Department Entity:**
- `@Entity` and `@Table` annotations
- `@Id` with `@GeneratedValue` for auto-increment
- `@Column` annotations for database mapping
- `@OneToMany` relationship with Employee
- Auditing fields: `createdAt`, `modifiedAt`

**Employee Entity:**
- `@Entity` and `@Table` annotations
- Multiple `@Column` annotations (empName, email, salary, designation, etc.)
- `@ManyToOne` relationship with Department (LAZY loading)
- Auditing fields: `createdAt`, `modifiedAt`
- `@BatchSize` for Hibernate optimization
- Supports active/inactive employees

**Relationships:**
- Department (1) ↔ (Many) Employee
- Cascade operations configured

---

### Exercise 3: Employee Management System - Creating Repositories ✅
**Files:** `repository/DepartmentRepository.java`, `repository/EmployeeRepository.java`

**DepartmentRepository:**
- Extends `JpaRepository<Department, Integer>`
- CRUD operations inherited from JpaRepository
- Custom query methods

**EmployeeRepository:**
- Extends `JpaRepository<Employee, Integer>`
- CRUD operations inherited from JpaRepository
- Custom query methods with pagination support

---

### Exercise 4: Employee Management System - Implementing CRUD Operations ✅
**Files:** `service/DepartmentService.java`, `service/EmployeeService.java`

**CRUD Operations Implemented:**
1. **Create (Save)**
   - `saveDepartment(Department)`
   - `saveEmployee(Employee)`

2. **Read (Retrieve)**
   - `getAllDepartments()`
   - `getDepartmentById(Integer)`
   - `getAllEmployees()`
   - `getEmployeeById(Integer)`

3. **Update**
   - `updateDepartment(Department)`
   - `updateEmployee(Employee)`

4. **Delete**
   - `deleteDepartment(Integer)`
   - `deleteEmployee(Integer)`

**Service Layer Features:**
- `@Transactional` annotation for transaction management
- `@Transactional(readOnly = true)` for read operations
- Logging at each operation level
- Proper exception handling

---

### Exercise 5: Employee Management System - Defining Query Methods ✅
**Files:** `repository/EmployeeRepository.java`, Service classes

**Custom Query Methods:**

**DepartmentRepository:**
```java
Optional<Department> findByDeptName(String deptName)
Optional<Department> findByDeptCode(String deptCode)
List<Department> findByLocation(String location)
@Query("SELECT d FROM Department d WHERE d.deptCode LIKE %:code%")
List<Department> searchByDeptCode(String code)
boolean existsByDeptCode(String deptCode)
long countByLocation(String location)
```

**EmployeeRepository:**
```java
Optional<Employee> findByEmail(String email)
List<Employee> findByDepartmentDeptId(Integer deptId)
List<Employee> findByDesignation(String designation)
List<Employee> findByIsActiveTrue()
List<Employee> findByDepartmentDeptIdAndIsActiveTrue(Integer deptId)

@Query("SELECT e FROM Employee e WHERE e.salary BETWEEN :minSalary AND :maxSalary")
List<Employee> findEmployeesBySalaryRange(Double minSalary, Double maxSalary)

@Query("SELECT e FROM Employee e WHERE LOWER(e.empName) LIKE LOWER(CONCAT('%', :name, '%'))")
List<Employee> searchByName(String name)

@Query("SELECT e FROM Employee e WHERE e.salary > :salary ORDER BY e.salary DESC")
List<Employee> findEmployeesWithHigherSalary(Double salary)

List<Employee> findByDateOfJoiningAfter(LocalDate date)
long countByDepartmentDeptId(Integer deptId)
```

**Service Layer Query Methods:**
- `findByDeptName()`, `findByDeptCode()`, `findByLocation()`
- `findByEmail()`, `findByDesignation()`, `findActiveEmployees()`
- `findBySalaryRange()`, `searchByName()`, `findWithHigherSalary()`
- `findJoinedAfter()`, `countEmployeesInDepartment()`

---

### Exercise 6: Employee Management System - Implementing Pagination and Sorting ✅
**Files:** `repository/EmployeeRepository.java`, `controller/EmployeeController.java`

**Pagination Methods:**
```java
// Repository
Page<Employee> findAll(Pageable pageable)
Page<Employee> findByDepartmentDeptId(Integer deptId, Pageable pageable)
Page<Employee> searchByNameWithPagination(String name, Pageable pageable)

// Service
Page<Employee> getAllEmployeesWithPagination(Pageable pageable)
Page<Employee> getEmployeesByDepartmentWithPagination(Integer deptId, Pageable pageable)
Page<Employee> searchByNameWithPagination(String name, Pageable pageable)
```

**REST Endpoints with Pagination:**
```
GET /api/employees/pagination?page=0&size=10&sortBy=empId&direction=ASC
GET /api/employees/department/{deptId}/pagination?page=0&size=10
GET /api/employees/search/{name}/pagination?page=0&size=10
```

**Sorting Features:**
- Sort by any field (empId, empName, salary, designation, etc.)
- Sort direction: ASC (Ascending) or DESC (Descending)
- Multiple sort criteria support

---

### Exercise 7: Employee Management System - Enabling Entity Auditing ✅
**Files:** `config/AuditingConfig.java`, `entity/Department.java`, `entity/Employee.java`

**Auditing Features:**
- `@CreatedDate` annotation on `createdAt` field
- `@LastModifiedDate` annotation on `modifiedAt` field
- `@EntityListeners(AuditingEntityListener.class)` on entities
- `@EnableJpaAuditing` annotation in configuration

**Auditing Configuration:**
```java
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditingConfig {
    @Bean(name = "auditorProvider")
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("SYSTEM");
    }
}
```

**Audited Timestamps:**
- `createdAt`: Auto-populated when entity is created
- `modifiedAt`: Auto-updated when entity is modified

---

### Exercise 8: Employee Management System - Creating Projections ✅
**Files:** `projection/EmployeeProjection.java`, `projection/DepartmentProjection.java`

**EmployeeProjection Interface:**
```java
public interface EmployeeProjection {
    Integer getEmpId();
    String getEmpName();
    String getEmail();
    String getDesignation();
    Double getSalary();
    DepartmentInfo getDepartment();
    
    interface DepartmentInfo {
        Integer getDeptId();
        String getDeptName();
    }
}
```

**DepartmentProjection Interface:**
```java
public interface DepartmentProjection {
    Integer getDeptId();
    String getDeptName();
    String getDeptCode();
    String getLocation();
    long getEmployeeCount();
}
```

**Projection Methods in Repository:**
```java
List<EmployeeProjection> findAllProjectedBy()
List<EmployeeProjection> findByDepartmentDeptIdProjectedBy(Integer deptId)
Page<EmployeeProjection> findAllProjectedBy(Pageable pageable)
```

**Benefits:**
- Fetch only required fields
- Reduce data transfer
- Improve query performance
- Nested projections support

---

### Exercise 9: Employee Management System - Customizing Data Source Configuration ✅
**Files:** `application.properties`

**Data Source Configuration:**
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

**JPA/Hibernate Configuration:**
```properties
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
```

**Connection Pool (HikariCP):**
```properties
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=2
spring.datasource.hikari.connection-timeout=20000
spring.datasource.hikari.idle-timeout=300000
spring.datasource.hikari.max-lifetime=1200000
```

---

### Exercise 10: Employee Management System - Hibernate-Specific Features ✅
**Files:** `entity/Employee.java`, `application.properties`, `repository/EmployeeRepository.java`

**Hibernate-Specific Annotations:**
```java
@BatchSize(size = 20)
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "dept_id")
```

**Batch Processing Configuration:**
```properties
spring.jpa.properties.hibernate.jdbc.batch_size=20
spring.jpa.properties.hibernate.order_inserts=true
spring.jpa.properties.hibernate.order_updates=true
```

**Native SQL Queries:**
```java
@Query(value = "SELECT d.dept_name, SUM(e.salary) FROM employees e " +
               "JOIN departments d ON e.dept_id = d.dept_id " +
               "GROUP BY d.dept_name", nativeQuery = true)
List<Object[]> getTotalSalaryByDepartment()
```

**Hibernate Features Demonstrated:**
- Lazy loading with `FetchType.LAZY`
- Batch processing for performance optimization
- Native SQL queries with `nativeQuery=true`
- Hibernate dialect configuration (H2Dialect)
- Statement batch processing
- Order insert/update optimization

---

## REST API Endpoints

### Department Endpoints
```
POST   /api/departments                          - Create department
GET    /api/departments                          - Get all departments
GET    /api/departments/{id}                     - Get department by ID
PUT    /api/departments/{id}                     - Update department
DELETE /api/departments/{id}                     - Delete department
GET    /api/departments/search/name/{name}       - Search by name
GET    /api/departments/search/code/{code}       - Search by code
GET    /api/departments/location/{location}      - Get by location
GET    /api/departments/search/pattern/{pattern} - Search by code pattern
```

### Employee Endpoints
```
POST   /api/employees                                    - Create employee
GET    /api/employees                                    - Get all employees
GET    /api/employees/{id}                              - Get employee by ID
PUT    /api/employees/{id}                              - Update employee
DELETE /api/employees/{id}                              - Delete employee
GET    /api/employees/pagination                        - Get with pagination
GET    /api/employees/department/{deptId}               - Get by department
GET    /api/employees/department/{deptId}/pagination    - Get by dept (paginated)
GET    /api/employees/designation/{designation}        - Get by designation
GET    /api/employees/email/{email}                     - Get by email
GET    /api/employees/active                            - Get active employees
GET    /api/employees/search/{name}                     - Search by name
GET    /api/employees/search/{name}/pagination          - Search by name (paginated)
GET    /api/employees/salary-range?minSalary=X&maxSalary=Y - Get by salary range
GET    /api/employees/high-salary?salary=X              - Get with salary > X
GET    /api/employees/joined-after?date=YYYY-MM-DD      - Get joined after date
GET    /api/employees/projections                       - Get projections
GET    /api/employees/department/{deptId}/projections   - Get dept projections
GET    /api/employees/projections/pagination            - Get projections (paginated)
GET    /api/employees/salary-by-department              - Get total salary by dept
GET    /api/employees/count/department/{deptId}         - Count employees in dept
```

---

## Sample Data

The `data.sql` file initializes the database with:
- **5 Departments**: IT, HR, Finance, Operations, Marketing
- **10 Employees**: Distributed across departments with various designations and salaries

Access sample data in H2 console after running the application.

---

## Database Schema

### DEPARTMENTS Table
```sql
CREATE TABLE departments (
    dept_id INT PRIMARY KEY AUTO_INCREMENT,
    dept_name VARCHAR(100) NOT NULL UNIQUE,
    dept_code VARCHAR(10) NOT NULL UNIQUE,
    location VARCHAR(100),
    created_at TIMESTAMP NOT NULL,
    modified_at TIMESTAMP
);
```

### EMPLOYEES Table
```sql
CREATE TABLE employees (
    emp_id INT PRIMARY KEY AUTO_INCREMENT,
    emp_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone_number VARCHAR(15),
    salary DOUBLE,
    designation VARCHAR(100),
    date_of_joining DATE,
    is_active BOOLEAN DEFAULT TRUE,
    dept_id INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    modified_at TIMESTAMP,
    FOREIGN KEY (dept_id) REFERENCES departments(dept_id)
);
```

---

## Technology Stack

- **Framework**: Spring Boot 2.7.18
- **ORM**: Hibernate with Spring Data JPA
- **Database**: H2 (In-memory)
- **Build Tool**: Maven 3.6+
- **Java Version**: 1.8
- **Lombok**: For reducing boilerplate code
- **Logging**: SLF4J with Logback

---

## Key Features

✅ Complete CRUD operations  
✅ Custom query methods with various search criteria  
✅ Pagination and sorting support  
✅ Entity auditing with creation and modification timestamps  
✅ Projections for optimized data retrieval  
✅ Configurable data source and Hibernate properties  
✅ Hibernate-specific features (batch processing, lazy loading, native queries)  
✅ REST API endpoints for all operations  
✅ Comprehensive logging and error handling  
✅ Transaction management with @Transactional  
✅ One-to-Many relationships with cascading operations  

---

## Testing the Application

### Example: Create Department
```bash
curl -X POST http://localhost:8080/api/departments \
  -H "Content-Type: application/json" \
  -d '{
    "deptName": "Research",
    "deptCode": "RES",
    "location": "Seattle"
  }'
```

### Example: Get Employees with Pagination
```bash
curl http://localhost:8080/api/employees/pagination?page=0&size=5&sortBy=salary&direction=DESC
```

### Example: Search Employees by Name
```bash
curl http://localhost:8080/api/employees/search/John/pagination?page=0&size=10
```

### Example: Get Employee Projections
```bash
curl http://localhost:8080/api/employees/projections
```

---

## Notes

- The application uses an in-memory H2 database, so all data is lost when the application restarts
- To persist data, change the data source to MySQL or PostgreSQL in `application.properties`
- Lombok is configured to auto-generate getters, setters, and constructors
- All service methods are transactional with appropriate isolation levels
- Lazy loading is configured for relationships to improve performance

---

## Author
Cognizant DeepSkilling Program - Module 6: Spring Data JPA with Spring Boot and Hibernate

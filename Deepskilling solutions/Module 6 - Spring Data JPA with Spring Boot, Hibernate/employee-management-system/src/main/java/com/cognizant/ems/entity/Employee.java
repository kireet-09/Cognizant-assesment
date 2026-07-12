package com.cognizant.ems.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Exercise 2: Employee Entity with JPA mapping, auditing, and Hibernate-specific features
 * Demonstrates @Entity, @Table, @ManyToOne relationships, auditing, and batch processing
 */
@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@BatchSize(size = 20)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Integer empId;

    @Column(name = "emp_name", nullable = false)
    private String empName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "designation")
    private String designation;

    @Column(name = "date_of_joining")
    private LocalDate dateOfJoining;

    @Column(name = "is_active")
    private Boolean isActive = true;

    /**
     * Exercise 7: Entity Auditing - Track creation timestamp
     */
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Exercise 7: Entity Auditing - Track modification timestamp
     */
    @LastModifiedDate
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    /**
     * Exercise 2: Many-to-One relationship with Department
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id", nullable = false)
    private Department department;

    /**
     * Exercise 10: Hibernate-specific feature - Batch Size for optimized queries
     * Convenience constructor for creating employees without ID
     */
    public Employee(String empName, String email, String designation, Double salary) {
        this.empName = empName;
        this.email = email;
        this.designation = designation;
        this.salary = salary;
    }
}

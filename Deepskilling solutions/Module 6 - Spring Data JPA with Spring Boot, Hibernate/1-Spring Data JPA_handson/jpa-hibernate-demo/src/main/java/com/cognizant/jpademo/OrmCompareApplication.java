package com.cognizant.jpademo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cognizant.jpademo.model.Employee;
import com.cognizant.jpademo.service.EmployeeService;

@SpringBootApplication
public class OrmCompareApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmCompareApplication.class);

    @Autowired
    private EmployeeService employeeService;

    public static void main(String[] args) {
        SpringApplication.run(OrmCompareApplication.class, args);
        LOGGER.info("Application started");
    }

    @Override
    public void run(String... args) {
        LOGGER.info("Demo start");

        Employee e1 = new Employee();
        e1.setName("Alice");
        e1.setSalary(50000.0);

        // Save using Hibernate-style API (via unwrapped Session)
        Integer id1 = employeeService.addEmployeeWithHibernate(e1);
        LOGGER.info("Added (hibernate) employee id={}", id1);

        Employee e2 = new Employee();
        e2.setName("Bob");
        e2.setSalary(60000.0);

        // Save using Spring Data JPA
        Employee saved = employeeService.addEmployeeWithSpringData(e2);
        LOGGER.info("Added (spring-data) employee id={}", saved.getId());

        List<Employee> all = employeeService.getAllEmployees();
        LOGGER.info("All employees: {}", all);

        LOGGER.info("Demo end");
    }
}

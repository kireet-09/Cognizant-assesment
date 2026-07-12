package com.cognizant.jpademo.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.jpademo.model.Employee;
import com.cognizant.jpademo.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public Integer addEmployeeWithHibernate(Employee employee) {
        Session session = entityManager.unwrap(Session.class);
        Integer id = (Integer) session.save(employee);
        LOGGER.debug("Saved with Hibernate session, id={}", id);
        return id;
    }

    @Transactional
    public Employee addEmployeeWithSpringData(Employee employee) {
        Employee saved = employeeRepository.save(employee);
        LOGGER.debug("Saved with Spring Data JPA, id={}", saved.getId());
        return saved;
    }

    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}

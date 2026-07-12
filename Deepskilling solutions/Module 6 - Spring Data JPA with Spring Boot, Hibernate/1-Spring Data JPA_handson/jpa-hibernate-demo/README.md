JPA vs Hibernate demo

This small demo shows two approaches to persisting an `Employee`:

- using the Hibernate `Session` obtained by unwrapping the JPA `EntityManager`
- using Spring Data JPA `EmployeeRepository`

Run with:

```bash
mvn clean package
mvn spring-boot:run
```

The application uses an in-memory H2 database so no external DB is required.

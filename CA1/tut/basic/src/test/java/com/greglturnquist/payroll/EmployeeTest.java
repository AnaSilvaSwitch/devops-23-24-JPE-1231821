package com.greglturnquist.payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeTest {
    @Test
    void createEmployee_Success() throws InstantiationException {
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "job";
        String jobTitle = "Job Title";
        int jobYears = 1;
        Employee employee = new Employee(firstName, lastName, description, jobTitle, jobYears);
        assertEquals(firstName, employee.getFirstName());
        assertEquals(lastName, employee.getLastName());
        assertEquals(description, employee.getDescription());
        assertEquals(jobTitle, employee.getJobTitle());
        assertEquals(jobYears, employee.getJobYears());
    }

    @Test
    void createEmployee_InvalidFirstName() {
        String firstName = "";
        String lastName = "Baggins";
        String description = "ring bearer";
        String jobTitle = "Job Title";
        int jobYears = 1;
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobTitle, jobYears));
    }

    @Test
    void createEmployee_InvalidLastName() {
        String firstName = "Frodo";
        String lastName = "";
        String description = "ring bearer";
        String jobTitle = "Job Title";
        int jobYears = 1;
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobTitle, jobYears));
    }

    @Test
    void createEmployee_InvalidJobTitle() {
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        String jobTitle = "";
        int jobYears = 1;
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobTitle, jobYears));
    }

    @Test
    void createEmployee_InvalidJobYears() {
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        String jobTitle = "Job Title";
        int jobYears = -1;
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobTitle, jobYears));
    }


    @Test
    void createEmployee_NullParameters() {
        String firstName = null;
        String lastName = null;
        String description = null;
        String jobTitle = null;
        int jobYears = 0;
        assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobTitle, jobYears));
    }

}

package com.mindex.challenge.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.mindex.challenge.data.Compensation;

import java.util.List;

public class Employee {
    @Id
    private String employeeId;

    private String firstName;
    private String lastName;
    private String position;
    private String department;
    private List<Employee> directReports;

    // New field to store compensation data
    private Compensation compensation;

    public Employee() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Employee> getDirectReports() {
        return directReports;
    }

    public void setDirectReports(List<Employee> directReports) {
        this.directReports = directReports;
    }

    // Get the compensation
    public Compensation getCompensation() {
        return compensation;
    }

    // Set the compensation
    public void setCompensation(Compensation compensation) {
        this.compensation = compensation;
    }
}

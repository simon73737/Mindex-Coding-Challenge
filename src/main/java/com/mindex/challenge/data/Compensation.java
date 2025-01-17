package com.mindex.challenge.data;

public class Compensation {
    private String employeeId;
    private double salary;
    private String effectiveDate;
    private String paymentStructure;

    // Constructor
    public Compensation (String employeeId, double salary, String effectiveDate, String paymentStructure) {
        this.employeeId = employeeId;
        this.salary = salary;
        this.effectiveDate = effectiveDate;
        this.paymentStructure = paymentStructure;
    }

    // Get the employee Id
    public String getEmployeeId() {
        return employeeId;
    }

    // Set the employee Id
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    // Get the salary
    public double getSalary() {
        return salary;
    }

    // Set the salary
    public void setSalary(int salary) {
        this.salary = salary;
    }

    // Get the effective date
    public String getEffectiveDate() {
        return effectiveDate;
    }

    // Set the effective date
    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    // Get the payment structure
    public String getPaymentStructure() {
        return paymentStructure;
    }

    // Set the payment structure
    public void setPaymentStructure(String paymentStructure) {
        this.paymentStructure = paymentStructure;
    }
}
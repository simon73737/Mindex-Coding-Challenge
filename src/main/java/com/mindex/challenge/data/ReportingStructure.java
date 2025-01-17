package com.mindex.challenge.data;

public class ReportingStructure {
    private String employeeId;
    private int totalReports;

    // Constructor
    public ReportingStructure(String employeeId, int totalReports) {
        this.employeeId = employeeId;
        this.totalReports = totalReports;
    }

    // Get the employee Id
    public String getEmployeeId() {
        return employeeId;
    }

    // Set the employee Id
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    // Get the total direct reports
    public int getTotalReports() {
        return totalReports;
    }

    // Set the number of total reports
    public void setTotalReports(int totalReports) {
        this.totalReports = totalReports;
    }
}

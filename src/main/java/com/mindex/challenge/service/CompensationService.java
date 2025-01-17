package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

public interface CompensationService {
    // Method to read the compensation for an employee
    Compensation read(String employeeId);
    // Method to create a compensation object for an employee
    Compensation create(String employeeId, Compensation compensation);
    // Method to update a compensation object for an employee
    Compensation update(String employeeId, Compensation compensation);
}
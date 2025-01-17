package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensationServiceImpl implements CompensationService {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;

    @Override
    public Compensation create(String employeeId, Compensation compensation) {
        LOG.debug("Creating compensation for employee [{}]", employeeId);

        // Get all the information for the given employee
        Employee employee = employeeRepository.findByEmployeeId(employeeId);

        // Throw an exception if the employee does not exist
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + employeeId);
        }

        // Set the compensation for the employee
        employee.setCompensation(compensation);

        // Save the employee document with the new compensation
        employeeService.update(employee);

        return compensation;
    }

    @Override
    public Compensation read(String employeeId) {
        LOG.debug("Fetching compensation for employee [{}]", employeeId);

        // Get the employee by employeeId
        Employee employee = employeeRepository.findByEmployeeId(employeeId);

        // Throw an exception if the employee does not exist
        if (employee == null) {
            throw new RuntimeException("Invalid employee: " + employeeId);
        }

        // Return the compensation for the given employee
        return employee.getCompensation();
    }

    @Override
    public Compensation update(String employeeId, Compensation compensation) {
        LOG.debug("Updating compensation for employee [{}]", employeeId);

        // Get the employee by employeeId
        Employee employee = employeeRepository.findByEmployeeId(employeeId);

        // Throw an exception if the employee does not exist
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + employeeId);
        }

        // Update the compensation for the employee
        employee.setCompensation(compensation);

        // Save the updated employee document with the new compensation
        employeeService.update(employee);

        return compensation;
    }
}
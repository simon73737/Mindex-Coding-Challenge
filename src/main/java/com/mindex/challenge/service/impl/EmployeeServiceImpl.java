package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String employeeId) {
        LOG.debug("Getting information for employee with employeeId [{}]", employeeId);

        Employee employee = employeeRepository.findByEmployeeId(employeeId);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + employeeId);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee.getEmployeeId());

        Employee findEmployee = employeeRepository.findByEmployeeId(employee.getEmployeeId());

        if (findEmployee == null) {
            throw new RuntimeException("Invalid employeeId: " + employee.getEmployeeId());
        }

        return employeeRepository.save(employee);
    }
}

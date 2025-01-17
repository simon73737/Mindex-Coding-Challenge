package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
// New Imports
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.dao.EmployeeRepository;
import java.util.regex.Pattern;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ReportingStructureService reportingStructureService;
    @Autowired
    private CompensationService compensationService;

    @PostMapping("/employee")
    public Employee create(@RequestBody Employee employee) {
        LOG.debug("Received employee create request for [{}]", employee);

        return employeeService.create(employee);
    }

    @GetMapping("/employee/{employeeId}")
    public Employee read(@PathVariable String employeeId) {
        LOG.debug("Received employee create request for id [{}]", employeeId);

        return employeeService.read(employeeId);
    }

    @PutMapping("/employee/{employeeId}")
    public Employee update(@PathVariable String employeeId, @RequestBody Employee employee) {
        LOG.debug("Received employee create request for id [{}] and employee [{}]", employeeId, employee);

        employee.setEmployeeId(employeeId);
        return employeeService.update(employee);
    }

    // New endpoint for getting the reporting structure for an employee
    @GetMapping("/employee/{employeeId}/reportingStructure")
    public ReportingStructure getReportingStrucutre(@PathVariable String employeeId) {
        LOG.debug("Received reporting structure request for employee id [{}]", employeeId);

        return reportingStructureService.calculateReportingStructure(employeeId);
    }

    // New endpoint for adding compensation to an employee
    @PostMapping("/employee/{employeeId}/compensation")
    public Compensation createCompensation(@PathVariable String employeeId, 
                                     @RequestParam double salary, 
                                     @RequestParam String effectiveDate,
                                     @RequestParam String paymentStructure) {
        LOG.debug("Received compensation create request for employee id [{}]", employeeId);

        // Validate request parameters
        String dateRegex = "^(0[1-9]|1[0-2])/([0-2][0-9]|3[01])/\\d{4}$";
        Pattern pattern = Pattern.compile(dateRegex);
        if (!pattern.matcher(effectiveDate).matches()) {
            throw new RuntimeException("Date is invalid: " + effectiveDate);
        }
        paymentStructure = paymentStructure.toLowerCase();
        if(!paymentStructure.equals("hourly")) {
            paymentStructure = "salary";
        }

        Compensation compensation = new Compensation(employeeId, salary, effectiveDate, paymentStructure);

        return compensationService.create(employeeId, compensation);
    }

    // New endpoint for updating an employee's compensation
    @PutMapping("/employee/{employeeId}/compensation")
    public Compensation updateCompensation(@PathVariable String employeeId, 
                                     @RequestParam double salary, 
                                     @RequestParam String effectiveDate,
                                     @RequestParam String paymentStructure) {
        LOG.debug("Received compensation create request for employee id [{}]", employeeId);

        // Validate request parameters
        String dateRegex = "^(0[1-9]|1[0-2])/([0-2][0-9]|3[01])/\\d{4}$";
        Pattern pattern = Pattern.compile(dateRegex);
        if (!pattern.matcher(effectiveDate).matches()) {
            throw new RuntimeException("Date is invalid: " + effectiveDate);
        }
        paymentStructure = paymentStructure.toLowerCase();
        if(!paymentStructure.equals("hourly")) {
            paymentStructure = "salary";
        }

        // Set the employeeId in compensation to associate with the right employee
        Compensation compensation = new Compensation(employeeId, salary, effectiveDate, paymentStructure);
        return compensationService.update(employeeId, compensation);
    }

    // New endpoint for reading the compensation for an employee
    @GetMapping("/employee/{employeeId}/compensation")
    public Compensation getCompensation(@PathVariable String employeeId) {
        LOG.debug("Received compensation read request for employee id [{}]", employeeId);

        return compensationService.read(employeeId);
    }
}

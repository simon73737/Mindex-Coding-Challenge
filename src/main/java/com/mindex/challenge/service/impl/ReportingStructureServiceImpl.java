package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure calculateReportingStructure(String id) {
        LOG.debug("Getting reporting structure for employee: [{}]", id);
        
        // Get all of the information for the given employee
        Employee employee = employeeRepository.findByEmployeeId(id);

        // Throw an exception if the employee does not exist
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        // Call the method which generates teh total direct reports
        int totalReports = calculateReports(employee);

        // Return the reporting structure object
        return new ReportingStructure(employee.getEmployeeId(), totalReports);
    }

    /**
     * Returns the number of direct reports for an employee
     * @param Employee The employee to find direct reports for
     * @return The number of direct reports for an employee
     */
    private int calculateReports(Employee employee) {
        int totalReports = 0;

        // If the employee doesnt have any direct reports, return 0
        if (employee.getDirectReports() == null || employee.getDirectReports().isEmpty()) {
            return totalReports;
        }

        // Recursively check each employee that reports to the given employee
        for (Employee directReport : employee.getDirectReports()) {
            Employee detailedReport = employeeRepository.findByEmployeeId(directReport.getEmployeeId());
            // Increment our counter for each direct report
            totalReports += 1 + calculateReports(detailedReport);
        }

        return totalReports;
    }
}

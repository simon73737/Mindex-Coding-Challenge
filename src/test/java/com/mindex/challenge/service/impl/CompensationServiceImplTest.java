package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    // Set up the URL for testing
    private String compensationUrl;

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CompensationService compensationService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() {
       compensationUrl = "http://localhost:" + port + "/employee/{id}/compensation";
    }

    @Test
    public void testCreateCompensation_Success() {
        Employee employee = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        
        // Create a new compensation object
        Compensation testCompensation = new Compensation();
        testCompensation.setEmployeeId(employee.getEmployeeId());
        testCompensation.setSalary(90000.0);
        testCompensation.setEffectiveDate("01/01/2025");
        testCompensation.setPaymentStructure("salary");


        employee.setCompensation(testCompensation);

        // Validate the compensation is set
        assertNotNull(employee.getCompensation());
        assertEquals(employee.getCompensation().getPaymentStructure(), "salary");
        assertEquals(employee.getCompensation().getEffectiveDate(), "01/01/2025");
    }

    @Test
    public void testReadCompensation_Success() {
        Employee employee = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        
        // Create a new compensation object
        Compensation testCompensation = new Compensation();
        testCompensation.setEmployeeId(employee.getEmployeeId());
        testCompensation.setSalary(90000.0);
        testCompensation.setEffectiveDate("01/01/2025");
        testCompensation.setPaymentStructure("salary");

        employee.setCompensation(testCompensation);
        employeeRepository.save(employee);

        // Retrieve the compensation for the employee
        Compensation retrievedCompensation = restTemplate.getForEntity(compensationUrl, Compensation.class, employee.getEmployeeId()).getBody();

        // Validate that the compensation is correctly retrieved
        assertNotNull(retrievedCompensation);
        assertEquals(testCompensation.getEmployeeId(), retrievedCompensation.getEmployeeId());
        assertEquals(testCompensation.getEffectiveDate(), retrievedCompensation.getEffectiveDate());
        assertEquals(testCompensation.getPaymentStructure(), retrievedCompensation.getPaymentStructure());
    }

    @Test
    public void testUpdateCompensation_Success() {
        Employee employee = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        
        // Create a new compensation object
        Compensation testCompensation = new Compensation();
        testCompensation.setEmployeeId(employee.getEmployeeId());
        testCompensation.setSalary(90000.0);
        testCompensation.setEffectiveDate("01/01/2025");
        testCompensation.setPaymentStructure("salary");

        employee.setCompensation(testCompensation);
        employeeRepository.save(employee);

        // Update compensation details
        testCompensation.setEffectiveDate("02/01/2025");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Compensation updatedCompensation = restTemplate.exchange(compensationUrl,
                HttpMethod.PUT,
                new HttpEntity<>(testCompensation, headers),
                Compensation.class,
                employee.getEmployeeId()).getBody();

        // Validate that the compensation was updated
        assertNotNull(updatedCompensation);
    }

}

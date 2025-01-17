package com.mindex.challenge.service;

import com.mindex.challenge.data.ReportingStructure;

public interface ReportingStructureService {
    // Method to get the reporting strucutre for an employee
    ReportingStructure calculateReportingStructure(String id);
}

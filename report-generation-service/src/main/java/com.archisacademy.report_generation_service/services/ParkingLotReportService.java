package com.archisacademy.report_generation_service.services;

import com.archisacademy.report_generation_service.apiResponse.ApiResponse;
import com.archisacademy.report_generation_service.dtos.ParkingLotReportResponse;
import com.archisacademy.report_generation_service.model.ParkingLotReport;

public interface ParkingLotReportService {
    ParkingLotReportResponse generateParkingLotReport();

    void generateCSVReport(ParkingLotReport report, String filePath);

    void generatePDFReport(ParkingLotReport report, String filePath);
}

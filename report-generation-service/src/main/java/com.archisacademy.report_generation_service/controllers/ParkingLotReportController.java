package com.archisacademy.report_generation_service.controllers;

import com.archisacademy.report_generation_service.apiResponse.ApiResponse;
import com.archisacademy.report_generation_service.dtos.ParkingLotReportResponse;
import com.archisacademy.report_generation_service.model.ParkingLotReport;
import com.archisacademy.report_generation_service.services.ParkingLotReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reports")
public class ParkingLotReportController {

    private final ParkingLotReportService parkingLotReportService;

    public ParkingLotReportController(ParkingLotReportService parkingLotReportService) {
        this.parkingLotReportService = parkingLotReportService;
    }

    @GetMapping("/parking-lot")
    public ResponseEntity<ApiResponse<ParkingLotReportResponse>> generateParkingLotReport() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Parking lot report generated successfully", parkingLotReportService.generateParkingLotReport()));
    }

    @GetMapping("/parking-lot/csv")
    public ResponseEntity<ApiResponse<String>> generateCSVReport(@RequestParam(required = true) String filePath) {
        ParkingLotReport report = new ParkingLotReport(); // Populate this with actual data
        parkingLotReportService.generateCSVReport(report, filePath);
        return ResponseEntity.ok(new ApiResponse<>(true, "CSV report generated successfully", filePath));
    }

    @GetMapping("/parking-lot/pdf")
    public ResponseEntity<ApiResponse<String>> generatePDFReport(@RequestParam(required = true) String filePath) {
        ParkingLotReport report = new ParkingLotReport();
        parkingLotReportService.generatePDFReport(report, filePath);
        return ResponseEntity.ok(new ApiResponse<>(true, "PDF report generated successfully", filePath));
    }
}

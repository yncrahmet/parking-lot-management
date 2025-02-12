package com.archisacademy.report_generation_service.services.Impl;

import com.archisacademy.report_generation_service.dtos.ParkingLotReportResponse;
import com.archisacademy.report_generation_service.model.ParkingLotReport;
import com.archisacademy.report_generation_service.services.ParkingLotReportService;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class ParkingLotReportServiceImpl implements ParkingLotReportService {

    @Override
    public ParkingLotReportResponse generateParkingLotReport() {
        ParkingLotReportResponse response = new ParkingLotReportResponse();
        response.setParkingLotName("Parking Lot 1");
        response.setLocation("Location 1");
        response.setTotalCapacity(100L);
        response.setCurrentUsage(50L);
        response.setRevenue(Double.valueOf(5000));
        return response;
    }

    // Method to generate CSV report
    @SneakyThrows
    @Override
    public void generateCSVReport(ParkingLotReport report, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("Parking Lot Name,Location,Total Capacity,Current Usage,Revenue\n");
            writer.append(report.getParkingLotName()).append(",")
                    .append(report.getLocation()).append(",")
                    .append(String.valueOf(report.getTotalCapacity())).append(",")
                    .append(String.valueOf(report.getCurrentUsage())).append(",")
                    .append(String.valueOf(report.getRevenue())).append("\n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    // Method to generate PDF report

    @SneakyThrows
    @Override
    public void generatePDFReport(ParkingLotReport report, String filePath) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            document.add(new Paragraph("Parking Lot Report"));
            document.add(new Paragraph("Parking Lot Name: " + report.getParkingLotName()));
            document.add(new Paragraph("Location: " + report.getLocation()));
            document.add(new Paragraph("Total Capacity: " + report.getTotalCapacity()));
            document.add(new Paragraph("Current Usage: " + report.getCurrentUsage()));
            document.add(new Paragraph("Revenue: " + report.getRevenue()));

            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
    }


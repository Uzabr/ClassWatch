package org.classwatch.controller;

import org.classwatch.dto.StatisticsResponse;
import org.classwatch.service.ExcelAnalysisService;
import org.classwatch.service.ExcelReaderService;
import org.classwatch.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class StudentController {


    private final ExcelAnalysisService excelAnalysisService;

    public StudentController(ExcelAnalysisService excelAnalysisService) {
        this.excelAnalysisService = excelAnalysisService;
    }

    @PostMapping("/upload")
    public ResponseEntity<StatisticsResponse> upload(@RequestParam("file") MultipartFile file) {
        StatisticsResponse statics = excelAnalysisService.analyze(file);
        return ResponseEntity.ok(statics);
    }

}
